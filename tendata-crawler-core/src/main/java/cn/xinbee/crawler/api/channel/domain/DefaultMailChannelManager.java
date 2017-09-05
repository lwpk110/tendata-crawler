package cn.xinbee.crawler.api.channel.domain;

import cn.xinbee.crawler.util.JsonUtils;
import cn.xinbee.crawler.api.model.ServiceResponse;
import cn.xinbee.crawler.data.domain.MailChannelCrawlerAgentDomain;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/9/4.
 */
public class DefaultMailChannelManager implements MailChannelManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    public DefaultMailChannelManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MailChannelCrawlerAgentDomain> getAllDomains() {
        ServiceResponse response = restTemplate.getForObject("http://localhost:8091/interior/xinbee/scs/domains/list_all",
                ServiceResponse.class);
        if (response.getSuccess()) {
           String jsonResult = JsonUtils.serialize(response.getResult());
            return JsonUtils.deserialize(jsonResult,List.class,MailChannelCrawlerAgentDomain.class);
        }else{
            logger.error("获取通道所有域名记录失败：{}",response.getErrmsg());
        }
        return null;
    }

    @Override
    public List<MailChannelCrawlerAgentDomain> getDomains(Integer channelCode) {
        ServiceResponse response = restTemplate.getForObject("http://localhost:8091/interior/xinbee/scs/domains/channel/{channelId}",
            ServiceResponse.class,channelCode);
        if (response.getSuccess()) {
            String jsonResult = JsonUtils.serialize(response.getResult());
            return JsonUtils.deserialize(jsonResult,List.class,MailChannelCrawlerAgentDomain.class);
        }else{
            logger.error("获取通道所有域名记录失败：{}",response.getErrmsg());
        }
        return null;
    }
}
