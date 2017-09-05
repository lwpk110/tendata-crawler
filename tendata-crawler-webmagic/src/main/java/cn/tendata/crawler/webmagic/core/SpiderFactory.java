package cn.tendata.crawler.webmagic.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import us.codecraft.webmagic.Spider;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class SpiderFactory {
    private Map<String,Spider> spiderMap = new HashMap<>();

    public SpiderFactory(Collection<Spider> spiderCollection) {
        for (Spider spider : spiderCollection){
            spiderMap.put(spider.getUUID(),spider);
            spider.run();
        }
    }

    public Spider  getSpider(String spiderKey){
        if(spiderMap.containsKey(spiderKey)){
            return spiderMap.get(spiderKey);
        }
        return null;
    }
}
