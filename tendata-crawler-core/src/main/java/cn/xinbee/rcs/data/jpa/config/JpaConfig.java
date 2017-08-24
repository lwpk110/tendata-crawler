package cn.xinbee.rcs.data.jpa.config;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="cn.xinbee.rcs.data.repository",
    repositoryFactoryBeanClass=EntityGraphJpaRepositoryFactoryBean.class)
@EnableJpaAuditing
@PropertySource("classpath:application.properties")
public class JpaConfig {
	
	@Autowired Environment env;
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("cn.xinbee.rcs.data.domain");
		factory.setJpaProperties(additionalProperties());
		return factory;
	}
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("jadira.usertype.autoRegisterUserTypes", env.getProperty("jadira.usertype.autoRegisterUserTypes", "true"));
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	    properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
	    properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql", "true"));
	    properties.setProperty("hibernate.id.new_generator_mappings", env.getProperty("hibernate.id.new_generator_mappings", "false"));
	    properties.setProperty("hibernate.implicit_naming_strategy", env.getProperty("hibernate.implicit_naming_strategy", 
	            CustomImplicitNamingStrategy.class.getName()));
	    properties.setProperty("hibernate.physical_naming_strategy", env.getProperty("hibernate.physical_naming_strategy", 
	            CustomPhysicalNamingStrategy.class.getName()));
		return properties;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
	
	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator(){
		return new HibernateExceptionTranslator();
	}
}
