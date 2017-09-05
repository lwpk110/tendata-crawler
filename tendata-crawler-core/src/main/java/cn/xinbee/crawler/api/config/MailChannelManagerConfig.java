package cn.xinbee.crawler.api.config;

import cn.xinbee.crawler.api.channel.domain.DefaultMailChannelManager;
import cn.xinbee.crawler.api.channel.domain.MailChannelManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/9/4.
 */
@Configuration
public class MailChannelManagerConfig {
    @Bean
    public MailChannelManager mailChannelManager(RestTemplate restTemplate) {
        return new DefaultMailChannelManager(restTemplate);
    }
}
