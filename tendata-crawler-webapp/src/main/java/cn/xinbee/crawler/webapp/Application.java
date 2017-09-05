package cn.xinbee.crawler.webapp;

import cn.tendata.crawler.webmagic.config.MailAgentDomainQualityConfig;
import cn.xinbee.crawler.service.EntityService;
import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Configuration
    @EnableTransactionManagement
    @EnableJpaRepositories(basePackages = "cn.xinbee.crawler.data.repository",
        repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
    @EnableJpaAuditing
    @EntityScan(basePackages = "cn.xinbee.crawler.data.domain")
    static class JpaConfig {
    }

    @Configuration
    @ComponentScan(basePackageClasses = {EntityService.class})
    static class ServiceConfig {
    }

    @Configuration
    @Import({MailAgentDomainQualityConfig.class})
    static class Crawler {
    }
}