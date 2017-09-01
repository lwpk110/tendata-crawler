package cn.xinbee.rcs.statistics;

import cn.xinbee.rcs.data.domain.MailAgentDomainQualityMonitoring;
import cn.xinbee.rcs.service.model.StatisticsFieldNames;
import cn.xinbee.rcs.statistics.util.StatisticsUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultCrawDataStatisticsHandler implements CrawDataStatisticsHandler<MailAgentDomainQualityMonitoring> {

    @Override
    public Map<String, Map<String, Integer>> handle(Collection<MailAgentDomainQualityMonitoring> source) {
        Map<String, Map<String, Integer>> result = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(source)) {
            for (MailAgentDomainQualityMonitoring madm : source) {
                String dateKey = StatisticsUtils.extractDateKey(madm.getCreatedDate());
                Map<String, Integer> fields = new LinkedHashMap<>();
                fields.put(StatisticsFieldNames.BLACK_LIST_COUNT, madm.getIpBlackListSummary());
                fields.put(StatisticsFieldNames.REPUTATION_LEVEL, madm.ipReputationLevel());
                fields.put(StatisticsFieldNames.SPAM_LEVEL, madm.ipSpamLevel());
                result.put(dateKey, fields);
            }
        }
        return result;
    }
}
