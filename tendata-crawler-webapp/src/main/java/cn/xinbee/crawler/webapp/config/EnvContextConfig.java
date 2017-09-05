package cn.xinbee.crawler.webapp.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;

import static org.springframework.beans.factory.config.PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE;

/**
 * {@inheritDoc}
 *
 * Created by ernest on 2017/9/5.
 */
//@Configuration
public class EnvContextConfig {
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setSystemPropertiesMode(SYSTEM_PROPERTIES_MODE_OVERRIDE);
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        propertyPlaceholderConfigurer.setLocations();
        return propertyPlaceholderConfigurer;
    }
}
