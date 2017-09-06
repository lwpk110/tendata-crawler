package cn.xinbee.crawler.statistics;

import cn.xinbee.crawler.data.domain.MailChannelAgentDomainQualityMonitoring;
import cn.xinbee.crawler.statistics.util.StatisticsUtils;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import static cn.xinbee.crawler.service.model.StatisticsFieldNames.BLACK_LIST_COUNT;
import static cn.xinbee.crawler.service.model.StatisticsFieldNames.REPUTATION_LEVEL;
import static cn.xinbee.crawler.service.model.StatisticsFieldNames.SPAM_LEVEL;

public class DefaultCrawDataStatisticsHandler implements CrawDataStatisticsHandler<MailChannelAgentDomainQualityMonitoring> {

    @Override
    public Map<String, Map<String, Integer>> handle(Collection<MailChannelAgentDomainQualityMonitoring> source,Integer channelCode) {
        Map<String, Map<String, Integer>> result = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(source)) {
            for (MailChannelAgentDomainQualityMonitoring madm : source) {
                if(Objects.equals(madm.getMailAgentDomain().getMailDeliveryChannel().getChannelCode(), channelCode)){ //添加channelCode筛选条件
                    String dateKey = StatisticsUtils.extractDateKey(madm.getCreatedDate());
                    getValid(dateKey,result,madm);
                }
            }
        }
        return result;
    }

    private static void  getValid(String dateKey,Map<String,Map<String,Integer>> result,MailChannelAgentDomainQualityMonitoring madm){
        Map<String,Integer> fields = result.get(dateKey);
        if(!CollectionUtils.isEmpty(fields)){
             Integer storedBlackListSummary = StringUtils.isEmpty(fields.get(BLACK_LIST_COUNT)) ? 0 : fields.get(BLACK_LIST_COUNT);
            if(madm.getIpBlackListSummary() > storedBlackListSummary){
                fields.put(BLACK_LIST_COUNT,madm.getIpBlackListSummary());
            }
            if(madm.ipReputationLevel() > fields.get(REPUTATION_LEVEL)){
                fields.put(REPUTATION_LEVEL , madm.ipReputationLevel());
            }
            if(madm.ipSpamLevel() > fields.get(SPAM_LEVEL)){
                fields.put(SPAM_LEVEL , madm.ipSpamLevel());
            }
        }else {
            fields = new LinkedHashMap<>();
            fields.put(BLACK_LIST_COUNT, madm.getIpBlackListSummary());
            fields.put(REPUTATION_LEVEL, madm.ipReputationLevel());
            fields.put(SPAM_LEVEL, madm.ipSpamLevel());
        }
        result.put(dateKey, fields);
    }
}
