package cn.xinbee.rcs.data.elasticsearch.core;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalHistogram;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;
import org.springframework.data.elasticsearch.core.ResultsExtractor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by minjay on 2017/2/22.
 */
public class DayQualityAggregationResultsExtractor implements ResultsExtractor<Map<String, Object>> {

    private String DateByKey;

    public DayQualityAggregationResultsExtractor(String dateByKey) {
        DateByKey = dateByKey;
    }

    @Override
    public Map<String, Object> extract(SearchResponse response) {
        Map<String, Object> result = new LinkedHashMap<>();
        InternalHistogram dh = response.getAggregations().get("by_day");
        for (InternalHistogram.Bucket bucket : (List<InternalHistogram.Bucket>) dh.getBuckets()) {
            List<Aggregation> aggs = bucket.getAggregations().asList();
            Map<String, Object> dayData = new LinkedHashMap<>();
            for (Aggregation a : aggs) {
                InternalSum sum = (InternalSum) a;
                dayData.put(sum.getName(), sum.getValue());
            }
            result.put(bucket.getKeyAsString(), dayData);
        }
        return result;
    }
}
