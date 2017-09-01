package cn.xinbee.rcs.webapp.config;

import cn.tendata.crawler.web.convert.StringToDatetimeConverter;
import cn.xinbee.rcs.statistics.CrawDataStatisticsHandler;
import cn.xinbee.rcs.statistics.DefaultCrawDataStatisticsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "cn.tendata.crawler.web.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    public static final String LINK_ACCOUNTS_ROOT = "link.accounts.root";

    @Autowired
    private Environment env;

    @Autowired
    public void setThymeleafViewResolver(ThymeleafViewResolver thymeleafViewResolver) {
        Map<String, String> vars = new HashMap<>();
        vars.put("LINK_ACCOUNTS_ROOT", env.getRequiredProperty(LINK_ACCOUNTS_ROOT));
        thymeleafViewResolver.setStaticVariables(vars);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDatetimeConverter());
    }

    @Bean
    public SimpleClientHttpRequestFactory requestFactory() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(8000);
        requestFactory.setReadTimeout(8000);
        return requestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory requestFactory) {
        return new RestTemplate(requestFactory);
    }

    @Bean
    public CrawDataStatisticsHandler crawDataStatisticsHandler() {
        return new DefaultCrawDataStatisticsHandler();
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

/*  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(crossDomainFilter());
    registrationBean.setOrder(1);
    return registrationBean;
  }

  @Bean
  public CrossDomainFilter crossDomainFilter() {
    return new CrossDomainFilter();
  }*/

  /*  @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDatetimeConverter());
    }
*/
}
