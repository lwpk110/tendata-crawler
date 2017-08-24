package cn.xinbee.rcs.data.elasticsearch.repository;

import org.junit.runner.RunWith;
import cn.xinbee.rcs.data.elasticsearch.config.ElasticsearchConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ElasticsearchConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public abstract class ElasticsearchRepositoryTestCase {

}
