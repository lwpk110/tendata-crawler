package cn.xinbee.rcs.data.elasticsearch.repository;

import cn.xinbee.rcs.data.elasticsearch.domain.MailDeliveryChannelDocument;
import cn.xinbee.rcs.data.elasticsearch.domain.MailDeliveryTaskReportDocument;
import cn.xinbee.rcs.data.elasticsearch.domain.UserDocument;
import cn.xinbee.rcs.service.model.MailChannelToSendDto;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ernest on 2017/2/24.
 */
public class MailDeliveryTaskReportRepositoryImplTest extends ElasticsearchRepositoryTestCase {
    @Autowired
    MailDeliveryTaskReportRepository mailDeliveryTaskReportRepository;

    List<MailDeliveryTaskReportDocument> taskReportDocuments = new ArrayList<>();

    @Before
    public void init() {

        UserDocument userDocument1 = new UserDocument(1, "user 1", 1, "parent user 1");
        MailDeliveryChannelDocument channelDocument1 =
                new MailDeliveryChannelDocument(1, "channel1", 1, "channel node 1");
        taskReportDocuments.add(
                createReport("id1", "task name 1", userDocument1, channelDocument1, "subject 1",
                        "send1@163.com",
                        "sender 1", "replier 1", "replier1@163.com", 10, 10, 8, 4, 2, 3, 1, 1, 1, 1, 1));

        UserDocument userDocument2 = new UserDocument(2, "user 2", 2, "parent user 2");
        MailDeliveryChannelDocument channelDocument2 =
                new MailDeliveryChannelDocument(2, "channel2", 2, "channel node 2");
        taskReportDocuments.add(
                createReport("id2", "task name 2", userDocument2, channelDocument2, "subject 2",
                        "send2@163.com",
                        "sender 2", "replier 2", "replier2@163.com", 10, 10, 8, 4, 2, 3, 1, 1, 1, 1, 1));

        UserDocument userDocument3 = new UserDocument(3, "user 3", 3, "parent user 3");
        MailDeliveryChannelDocument channelDocument3 =
                new MailDeliveryChannelDocument(2, "channel2", 2, "channel node 2");

        taskReportDocuments.add(
                createReport("id3", "task name 3", userDocument3, channelDocument3, "subject 3",
                        "send3@163.com",
                        "sender 3", "replier 3", "replier3@163.com", 10, 10, 8, 4, 2, 3, 1, 1, 1, 1, 1));

        mailDeliveryTaskReportRepository.save(taskReportDocuments);
    }

    @Test
    public void aggregations() throws Exception {
        long[] userId = {1, 2, 3};
        DateTime startTime = new DateTime();
        List<MailChannelToSendDto> channelToSendDtoList =
                mailDeliveryTaskReportRepository.aggregations(userId, startTime.toLocalDate(), getEndTime(startTime));

        int total = 0, open = 0, sent = 0, click = 0;
        for (MailChannelToSendDto mailChannelToSendDto : channelToSendDtoList) {
            total += mailChannelToSendDto.getTotal();
            open += mailChannelToSendDto.getOpen();
            sent += mailChannelToSendDto.getSend();
            click += mailChannelToSendDto.getClick();
        }

        Assert.assertEquals("通道个数不一致！！", 2, channelToSendDtoList.size());
        Assert.assertEquals(" 任务总数不一致！！", 2, channelToSendDtoList.size());
        Assert.assertEquals(" 发送邮件总数不一致！！", 30, total);
        Assert.assertEquals(" 发送成功总数不一致！！", 30, sent);
        Assert.assertEquals(" 邮件点击总数不一致！！", 12, click);
    }

    @Test
    public void getMailDeliveryTaskReportDocumentBymonthAndUser() throws Exception {

    }

    private MailDeliveryTaskReportDocument createReport(String id, String taskName,
                                                        UserDocument userDocument,
                                                        MailDeliveryChannelDocument channelDocument, String subject, String sendMail,
                                                        String sender, String replyMail, String replier,
                                                        int total, int sent, int allClicked, int mailClicked, int uniqueOpen, int openCount,
                                                        int linkClicked,
                                                        int hardBounce, int softBounce, int unsub, int spamComplain) {
        MailDeliveryTaskReportDocument reportDocument = new MailDeliveryTaskReportDocument();
        reportDocument.setReplyEmail(replyMail);
        reportDocument.setReplyName(replier);
        reportDocument.setTaskName(taskName);
        reportDocument.setCreatedDate(new DateTime());
        reportDocument.setAllClicked(allClicked);
        reportDocument.setDeliveryChannel(channelDocument);
        reportDocument.setFinishDate(new DateTime());
        reportDocument.setHardBounce(hardBounce);
        reportDocument.setLastModifiedDate(new DateTime());
        reportDocument.setMailClicked(mailClicked);
        reportDocument.setOpenCount(openCount);
        reportDocument.setLinkClicked(linkClicked);
        reportDocument.setSendDate(new DateTime());
        reportDocument.setSenderEmail(sendMail);
        reportDocument.setSenderName(sender);
        reportDocument.setSent(sent);
        reportDocument.setSoftBounce(softBounce);
        reportDocument.setSubject(subject);
        reportDocument.setTotal(total);
        reportDocument.setUniqueOpenCount(uniqueOpen);
        reportDocument.setUnsubscribe(unsub);
        reportDocument.setSpamComplain(spamComplain);
        reportDocument.setUser(userDocument);
        reportDocument.setId(id);
        return reportDocument;
    }

    private LocalDate getEndTime(DateTime startDateTime) {
        if (startDateTime.monthOfYear().get() == DateTime.now().monthOfYear().get()) {
            return new DateTime().plus(-1).toLocalDate();
        }
        return startDateTime.plusMonths(1).plusDays(-1).toLocalDate();
    }

    @After
    public void clear() {
        mailDeliveryTaskReportRepository.deleteAll();
    }
}