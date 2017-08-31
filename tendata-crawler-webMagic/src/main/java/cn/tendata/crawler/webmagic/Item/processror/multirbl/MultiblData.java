package cn.tendata.crawler.webmagic.Item.processror.multirbl;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MultiblData {
    public static String MULTIBL_REQUEST_URL = "http://multirbl.valli.org/lookup/{IP}.html";

    public static String MULTIBL_REQUEST_URL_REGEX = "http://multirbl.valli.org/lookup/.+.html";
    public static String MULTIBL_AJAX_URL_REGEX = "http://multirbl.valli.org/json-lookup.php.+";

    public static String MULTIBL_PAGE_SESSION_HASH = "asessionHash";
}
