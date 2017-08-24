package cn.xinbee.rcs.webapp;

import cn.tendata.crawler.webmagic.config.MailAgentDomainIpQualityConfig;
import cn.xinbee.rcs.data.elasticsearch.core.CustomEntityMapper;
import cn.xinbee.rcs.service.EntityService;
import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.elasticsearch.client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
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
    @EnableJpaRepositories(basePackages = "cn.xinbee.rcs.data.repository",
        repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class)
    @EnableJpaAuditing
    @EntityScan(basePackages = "cn.xinbee.rcs.data.domain")
    static class JpaConfig {
    }

    @Configuration
    @EnableElasticsearchRepositories(basePackages = "cn.xinbee.rcs.data.elasticsearch.repository")
    static class ElasticsearchConfig {

        @Bean
        public ElasticsearchTemplate elasticsearchTemplate(Client client,
            ElasticsearchConverter converter) {
            try {
                return new ElasticsearchTemplate(
                    client, converter, new DefaultResultMapper(converter.getMappingContext(),
                    new CustomEntityMapper()));
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

    @Configuration
    @ComponentScan(basePackageClasses = {EntityService.class})
    static class ServiceConfig {
    }

    @Configuration
    @Import({MailAgentDomainIpQualityConfig.class})
    static class Crawler {
    }
}
