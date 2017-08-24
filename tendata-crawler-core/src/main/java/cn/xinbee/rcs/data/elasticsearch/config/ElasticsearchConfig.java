package cn.xinbee.rcs.data.elasticsearch.config;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories(basePackages = "cn.xinbee.rcs.data.elasticsearch.repository")
public class ElasticsearchConfig {

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        NodeClient nodeClient = (NodeClient) nodeBuilder().settings(Settings.builder()
                .put("http.enabled", "false")
                .put("path.data", "build/elasticsearch")
                .put("path.home", "data"))
                .clusterName(UUID.randomUUID().toString()).local(true).node()
                .client();
        return new ElasticsearchTemplate(nodeClient);
    }
}
