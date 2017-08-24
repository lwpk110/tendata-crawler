package cn.tendata.crawler.webmagic.Item.processror.multirbl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class BlackListProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(300);

    @Override
    public void process(Page page) {

    }

    @Override public Site getSite() {
        return site;
    }
}
