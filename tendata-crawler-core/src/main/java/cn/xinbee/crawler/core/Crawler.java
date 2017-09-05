package cn.xinbee.crawler.core;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public interface Crawler<T> {

    void crawl(T t);
}
