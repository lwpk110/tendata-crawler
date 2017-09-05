package cn.tendata.crawler.webmagic.Item.processror.talos;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.EMAIL_REPUTATION;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.LAST_DAY_SPAM_LEVEL;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/25.
 */
public class TalosCrawlerPipeline implements Pipeline {

    private volatile String emailReputation;
    private volatile String lastDaySpamLevel;


    @Override
    public void process(ResultItems resultItems, Task task) {
        emailReputation =  resultItems.get(EMAIL_REPUTATION);
        lastDaySpamLevel =  resultItems.get(LAST_DAY_SPAM_LEVEL);
    }

    public String getEmailReputation() {
        return emailReputation;
    }

    public String getLastDaySpamLevel() {
        return lastDaySpamLevel;
    }
}
