package cn.xinbee.crawler.statistics;

import cn.xinbee.crawler.service.model.StatisticsFieldNames;
import cn.xinbee.crawler.data.domain.MailChannelAgentDomainQualityMonitoring;
import cn.xinbee.crawler.statistics.util.StatisticsUtils;
import java.util.Objects;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultCrawDataStatisticsHandler implements CrawDataStatisticsHandler<MailChannelAgentDomainQualityMonitoring> {

    @Override
    public Map<String, Map<String, Integer>> handle(Collection<MailChannelAgentDomainQualityMonitoring> source,Integer channelCode) {
        Map<String, Map<String, Integer>> result = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(source)) {
            for (MailChannelAgentDomainQualityMonitoring madm : source) {
                if(Objects.equals(madm.getMailAgentDomain().getMailDeliveryChannel().getChannelCode(), channelCode)){
                    String dateKey = StatisticsUtils.extractDateKey(madm.getCreatedDate());
                    Map<String, Integer> fields = new LinkedHashMap<>();
                    fields.put(StatisticsFieldNames.BLACK_LIST_COUNT, madm.getIpBlackListSummary());
                    fields.put(StatisticsFieldNames.REPUTATION_LEVEL, madm.ipReputationLevel());
                    fields.put(StatisticsFieldNames.SPAM_LEVEL, madm.ipSpamLevel());
                    result.put(dateKey, fields);
                }
            }
        }
        return result;
    }
}
