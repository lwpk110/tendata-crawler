package cn.xinbee.crawler.statistics;

import java.util.Collection;
import java.util.Map;

public interface CrawDataStatisticsHandler<S> {

    Map<String, Map<String, Integer>> handle(Collection<S> source,Integer channelCode);
}
