package cn.tendata.crawler.webmagic.Item.processror.multirbl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.BLACKLIST_SUMMARY;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/25.
 */
public class MultiblCrawlerPipeline implements Pipeline {

    private volatile int blackListSummary;

    @Override
    public void process(ResultItems resultItems, Task task) {
        blackListSummary =  resultItems.get(BLACKLIST_SUMMARY) == null ? blackListSummary : resultItems.get(BLACKLIST_SUMMARY) ;
    }

    public Integer getBlackListSummary() {
        return blackListSummary;
    }
}
