package cn.tendata.crawler.webmagic.Item;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.codec.CharEncoding;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public abstract class AbstractWebMagicPageProcessor  implements PageProcessor{

    protected Site site = Site.me().setCharset(CharEncoding.UTF_8);

    @Override
    public Site getSite() {
        Set<Integer> stateCodeSet = new HashSet<>();
        stateCodeSet.add(201);
        stateCodeSet.add(200);
        site.setAcceptStatCode(stateCodeSet);
        return site;
    }
}
