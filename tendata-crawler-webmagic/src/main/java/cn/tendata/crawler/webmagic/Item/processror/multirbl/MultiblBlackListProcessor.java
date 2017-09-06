package cn.tendata.crawler.webmagic.Item.processror.multirbl;

import cn.tendata.crawler.webmagic.Item.AbstractWebMagicPageProcessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.selector.Selectable;

import static cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblData.MULTIBL_AJAX_URL_REGEX;
import static cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblData.MULTIBL_PAGE_SESSION_HASH;
import static cn.tendata.crawler.webmagic.Item.processror.multirbl.MultiblData.MULTIBL_REQUEST_URL_REGEX;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.BLACKLIST_SUMMARY;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class MultiblBlackListProcessor extends AbstractWebMagicPageProcessor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private volatile int blackList_blackListed = 0;
    private volatile int combinedlist_blackListed = 0;
    private volatile int whitelist_blackListed = 0;
    private volatile int informationallist__blackListed = 0;

    private volatile int blackListSummary = 0;

    private volatile Map map = new HashMap();

    private ObjectMapper mapper = new ObjectMapper(); //转换器

    @Override
    public Site getSite() {
        return super.getSite().setTimeOut(10000).setCycleRetryTimes(3);
    }

    @Override
    public void process(Page page) {
        if (CollectionUtils.isNotEmpty(page.getUrl().regex(MULTIBL_REQUEST_URL_REGEX).nodes())) {
            initProcessor();
            loadMainHtml(page);
        }
        if (CollectionUtils.isNotEmpty(page.getUrl().regex(MULTIBL_AJAX_URL_REGEX).nodes())) {
            ajaxRequest(page);
        }
    }

    private void loadMainHtml(Page page) {
        Selectable selectable = page.getHtml().$("table[id=dnsbl_data] tr:has(td)");
        final List<Selectable> selectableList = selectable.nodes();
        String data = null;
        try {
            data = page.getHtml().getDocument().head().getAllElements().get(12).childNodes().get(0).attr("data").replaceAll("\\s+", "");
        } catch (Exception e) {
            logger.warn("########超过网站限制，返回文档：{}", page.getHtml().getDocument().head().getAllElements().toString());
            e.printStackTrace();
        }
        String json = data.substring(data.indexOf('{'), data.lastIndexOf("}") + 1);
        String oasessionHash = "";
        try {
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            map = mapper.readValue(json, Map.class);
            oasessionHash = (String) map.get(MULTIBL_PAGE_SESSION_HASH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        addNextRequest(selectableList,page,oasessionHash);
        logger.info("---> page request size ：" + page.getTargetRequests().size());
    }

    private void ajaxRequest(Page page) {
        try {
            final Map resMap = mapper.readValue(page.getRawText(), Map.class);
            String rid = (String) resMap.get("rid");
            String testName = rid.split("_")[0];
            boolean failed = (Boolean) ((Map) resMap.get("data")).get("failed");
            if (!failed) {
                String resultColor = (String) resMap.get("result_color");
                if ("black".equals(resultColor)) {
                    switch (testName) {
                        case "DNSBLBlacklistTest":
                            blackList_blackListed++;
                            break;
                        case "DNSBLCombinedlistTest":
                            combinedlist_blackListed++;
                            break;
                        case "DNSBLWhitelistTest":
                            whitelist_blackListed++;
                            break;
                        case "DNSBLInformationallistTest":
                            informationallist__blackListed++;
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int blackListSummary = blackList_blackListed
            + combinedlist_blackListed
            + whitelist_blackListed
            + informationallist__blackListed;
        page.putField(BLACKLIST_SUMMARY, blackListSummary);
    }

    private void initProcessor(){
        blackListSummary = 0;
        blackList_blackListed = 0;
        combinedlist_blackListed = 0;
        whitelist_blackListed = 0;
        informationallist__blackListed = 0;
    }

    private void addNextRequest(List<Selectable> selectableList, Page page, String oasessionHash){
        for (Selectable selectable1 : selectableList) {
            PlainText id = (PlainText) selectable1.$("tr", "id");
            PlainText l_id = (PlainText) selectable1.$("tr td.l_id", "text");
            PlainText l_qhost = (PlainText) selectable1.$("tr td.l_qhost", "text");
            PlainText dns_zone = (PlainText) selectable1.$("tr td.dns_zone", "text");
            String reqData =
                "ash=" + oasessionHash + "&rid=" + id + "&lid=" + l_id + "&q=" + l_qhost;
            page.addTargetRequest("http://multirbl.valli.org/json-lookup.php?" + reqData);
        }
    }

}
