package cn.tendata.crawler.webmagic.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.SpiderListener;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/25.
 */
public class MultoblSpiderListener implements SpiderListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onSuccess(Request request) {
        logger.info("--> request success:"+request.toString());
    }

    @Override public void onError(Request request) {
        logger.info("--> request err:"+request.toString());
    }
}
