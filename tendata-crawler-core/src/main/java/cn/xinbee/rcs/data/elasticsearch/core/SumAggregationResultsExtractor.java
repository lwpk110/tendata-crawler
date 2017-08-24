package cn.xinbee.rcs.data.elasticsearch.core;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.springframework.data.elasticsearch.core.ResultsExtractor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumAggregationResultsExtractor implements ResultsExtractor<Map<String, Object>> {



    @Override
    public Map<String, Object> extract(SearchResponse response) {
        Map<String, Object> result = new HashMap<>();

        List<Aggregation> aggregations = response.getAggregations().asList();
        for (Aggregation aggr : aggregations) {
            String name = aggr.getName();
            Sum sum = response.getAggregations().get(name);
            result.put(name, sum.getValue());
        }
        return result;
    }
}
