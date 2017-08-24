package cn.xinbee.rcs.data.elasticsearch.repository;

import cn.xinbee.rcs.data.elasticsearch.domain.MailRecipientActionDocument;
import cn.xinbee.rcs.mail.MailRecipientAction.MailRecipientActionStatus;
import cn.xinbee.rcs.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ernest on 2017/2/22.
 */
public class MailRecipientActionRepositoryTest extends ElasticsearchRepositoryTestCase {
    @Autowired
    MailRecipientActionRepository mailRecipientActionRepository;

    private MailRecipientActionDocument createMailRecipientActionDocument(DateTime actionTime,
        MailRecipientActionStatus actionStatus,
        String mail, String taskId, String taskName) {
        MailRecipientActionDocument mailRecipientActionDocument = new MailRecipientActionDocument();

        mailRecipientActionDocument.setActionDate(actionTime);
        mailRecipientActionDocument.setActionStatus(actionStatus);
        mailRecipientActionDocument.setEmail(mail);
        mailRecipientActionDocument.setTaskId(taskId);
        mailRecipientActionDocument.setTaskName(taskName);

        return mailRecipientActionDocument;
    }

    @Test
    public void deleteByTaskIdAndEmailAndActionStatus() throws Exception {
        List<MailRecipientActionDocument> list = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            list.add(createMailRecipientActionDocument(new DateTime(),
                MailRecipientActionStatus.SYSTEM_UNSUBSCRIBE,
                "lwpk110@163.com",
                "taskId1",
                "taskName"));
        }

        Iterable<MailRecipientActionDocument> res = mailRecipientActionRepository.save(list);
        List<MailRecipientActionDocument> resList = CollectionUtils.toList(res);

        Assert.assertEquals(2L, resList.size());

        long count = mailRecipientActionRepository.deleteByTaskIdAndEmailAndActionStatus("taskId1",
            "lwpk110@163.com",
            MailRecipientActionStatus.SYSTEM_UNSUBSCRIBE);
        Assert.assertEquals(0L, count);
    }

    @Test
    public void save() {
        List<MailRecipientActionDocument> list = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            list.add(createMailRecipientActionDocument(new DateTime(),
                MailRecipientActionStatus.CLICK,
                "lwpk11" + i + "@163.com",
                "taskId" + i,
                "taskName" + i));
        }

        mailRecipientActionRepository.save(list);

        Iterable<MailRecipientActionDocument> res = mailRecipientActionRepository.findAll();
        List<MailRecipientActionDocument> resList = CollectionUtils.toList(res);

        Assert.assertEquals(2L, resList.size());
    }

    @Test
    public void saveDto() {
        MailRecipientActionDocument recipientActionDocument = new MailRecipientActionDocument();
        recipientActionDocument.setEmail("lwpk110@163.com");
        recipientActionDocument.setActionDate(new DateTime());
        recipientActionDocument.setTaskId("taskId1");
        MailRecipientActionDocument recipientActionDocumentNew =
            this.mailRecipientActionRepository.save(recipientActionDocument);
        System.out.println("----> 生成的doc 的id 为:" + recipientActionDocumentNew.getId());
    }
}


