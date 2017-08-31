package cn.tendata.crawler.webmagic.Item.processror.talos;

import cn.tendata.crawler.webmagic.Item.AbstractWebMagicPageProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.KEY_EMAIL_REPUTATION;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.KEY_LAST_DAY_SPAM_LEVEL;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.TALOS_DISPATCH_PATH;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.TALOS_QUALITY_TARGET_PATH;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.TALOS_QUALITY_TARGET_URL_REGEX;
import static cn.tendata.crawler.webmagic.Item.processror.talos.TalosData.TALOS_REQUEST_URL_REGEX;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.EMAIL_REPUTATION;
import static cn.tendata.crawler.webmagic.core.AbstractDomainIpQualityCrawler.LAST_DAY_SPAM_LEVEL;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/8/24.
 */
public class TalosIpQualityProcessor extends AbstractWebMagicPageProcessor {

    private static List<String> paths = new ArrayList<>(3);
    private ObjectMapper mapper = new ObjectMapper(); //转换器

    private int index;
    String ip;

    static {
        paths.add("");
        paths.add(TALOS_DISPATCH_PATH);
        paths.add(TALOS_QUALITY_TARGET_PATH);
    }

    @Override
    public void process(Page page) {
        if(CollectionUtils.isNotEmpty(page.getUrl().regex(TALOS_REQUEST_URL_REGEX).nodes())){
            initProcessor(page);
        }
        if(CollectionUtils.isNotEmpty(page.getUrl().regex(TALOS_QUALITY_TARGET_URL_REGEX).nodes())){
            processTarget(page);
            return;
        }
        index ++;
        addNextRequest(page);
    }

    private void  initProcessor(Page page){
        index = 0;
        ip = page.getHtml().xpath("//input[@id=rep-lookup]/@value").get();
    }

    private void processTarget(Page page){
        final String rawText = page.getRawText();
        try {
            Map map = mapper.readValue(rawText, Map.class);
            String emailReputation = (String) map.get(KEY_EMAIL_REPUTATION);
            String dailySpamName = (String) map.get(KEY_LAST_DAY_SPAM_LEVEL);
            page.putField(EMAIL_REPUTATION,emailReputation);
            page.putField(LAST_DAY_SPAM_LEVEL,dailySpamName);
            if(StringUtils.isEmpty(emailReputation)&&StringUtils.isEmpty(dailySpamName)){
                page.setSkip(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        index =0;
    }

    private void addNextRequest(Page page){
        String docResponseSession;
        try {
            docResponseSession = page.getHeaders().get("Set-Cookie").get(1);
        } catch (Exception e) {
            docResponseSession=page.getHeaders().get("Set-Cookie").get(0);
        }
        if(StringUtils.hasText(ip)){
            String value = docResponseSession.split("=")[1];
            Request lookupRequest = new Request();
            lookupRequest.setMethod("GET");
            lookupRequest.setUrl("https://talosintelligence.com/sb_api/query_lookup?query="+paths.get(index)+"&query_entry="+ip+"&offset=0&order=ip asc");
            lookupRequest.addHeader(":authority","talosintelligence.com");
            lookupRequest.addHeader(":method","talosintelligence.com");
            lookupRequest.addHeader(":path","/sb_api/query_lookup?query="+paths.get(index).replaceAll("/","%2F")+"&query_entry="+ip+"&offset=0&order=ip+asc");
            lookupRequest.addHeader(":authority","talosintelligence.com");
            lookupRequest.addHeader(":scheme","https");
            lookupRequest.addHeader("accept","application/json, text/javascript, */*; q=0.01");
            lookupRequest.addHeader("accept-encoding","gzip, deflate, br");
            lookupRequest.addHeader("accept-language","zh-CN,zh;q=0.8");
            lookupRequest.addHeader("cache-control","no-cache");
            lookupRequest.addHeader("cookie","__cfduid=d4cfa927d50a28fb57a2b1fed5734108e1503026430; _ga=GA1.2.2048204172.1503026433; _gid=GA1.2.1114022087.1503281539; _talos_website_session="+ value);
            lookupRequest.addHeader("pragma","no-cache");
            lookupRequest.addHeader("referer","https://talosintelligence.com/reputation_center/lookup?search="+ip);
            lookupRequest.addHeader("user-agent","Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
            page.addTargetRequest(lookupRequest);
        }

    }

}
