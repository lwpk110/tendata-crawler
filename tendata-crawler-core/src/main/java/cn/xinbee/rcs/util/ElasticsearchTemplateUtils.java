package cn.xinbee.rcs.util;

import cn.xinbee.rcs.util.ElasticsearchTemplateUtils.AggregationListDto.AggregationDto;
import cn.xinbee.rcs.util.ElasticsearchTemplateUtils.AggregationListDto.InternalSumDto;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.InternalTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.sum.InternalSum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ernest on 2017/2/27.
 */
public class ElasticsearchTemplateUtils {

    public static AggregationListDto.AggregationDto convertForTerm(Aggregations aggregations,
                                                                   String key) {
        List<Terms.Bucket> list = (((InternalTerms) aggregations.asMap().get(key))).getBuckets();
        for (Terms.Bucket bucket : list) {
            AggregationDto
                    aggregationDto = new AggregationDto();
            aggregationDto.setKey(bucket.getKeyAsString());
            aggregationDto.setDocCount(bucket.getDocCount());
            aggregationDto.setAggregations(bucket.getAggregations());
            return aggregationDto;
        }
        return null;
    }

    public static List<AggregationListDto> convertForTerm(Aggregations aggregations) {
        List<Aggregation> list = aggregations.asList();
        List<AggregationListDto> aggregationListDtoList = new ArrayList<>();
        for (Aggregation aggregation : list) {
            AggregationListDto aggregationListDto = new AggregationListDto();
            InternalTerms aggregationLong = (InternalTerms) aggregation;
            String key = aggregationLong.getName();
            aggregationListDto.setKey(key);
            List<Terms.Bucket> list1 = aggregationLong.getBuckets();
            List<AggregationDto> aggregationDtoList = new ArrayList<>();
            if (list1.size() > 0) {
                for (Terms.Bucket bucket : list1) {
                    AggregationDto aggregationDto = new AggregationDto();
                    aggregationDto.setKey(bucket.getKeyAsString());
                    aggregationDto.setDocCount(bucket.getDocCount());
                    aggregationDto.setAggregations(bucket.getAggregations());
                    aggregationDtoList.add(aggregationDto);
                    aggregationListDto.setAggregationDtos(aggregationDtoList);
                }
                aggregationListDtoList.add(aggregationListDto);
            }
            return aggregationListDtoList;
        }
        return null;
    }

    public static List<InternalSumDto> convertForSum(Aggregations aggregations) {
        List<Aggregation> list = aggregations.asList();
        List<InternalSumDto> internalSumDtoList = new ArrayList<>();

        for (Aggregation aggregation : list) {
            InternalSumDto internalSumDto = new InternalSumDto();
            InternalSum aggregationSum = (InternalSum) aggregation;
            String key = aggregationSum.getName();
            double sumValue = aggregationSum.getValue();
            internalSumDto.setKey(key);
            internalSumDto.setValue(sumValue);

            internalSumDtoList.add(internalSumDto);
        }
        return internalSumDtoList;
    }

    public static class AggregationListDto {
        private String key;
        private long value;
        private List<AggregationDto> aggregationDtos;

        public String getKey() {
            return key;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<AggregationDto> getAggregationDtos() {
            return aggregationDtos;
        }

        public void setAggregationDtos(
                List<AggregationDto> aggregationDtos) {
            this.aggregationDtos = aggregationDtos;
        }

        public static class AggregationDto {
            private String key;
            private Long docCount;
            private Aggregations aggregations;

            public Aggregations getAggregations() {
                return aggregations;
            }

            public void setAggregations(Aggregations aggregations) {
                this.aggregations = aggregations;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public Long getDocCount() {
                return docCount;
            }

            public void setDocCount(Long docCount) {
                this.docCount = docCount;
            }
        }

        public static class InternalSumDto {
            private String key;
            private double value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }
    }
}
