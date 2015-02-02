package com.sannong.project.presentation.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Bright Huang on 1/29/15.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ConfigurationProperties
public class DatabaseConfig {


    @Value("${db.driver-class-name}")
    private String DB_DRIVER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.username}")
    private String DB_USERNAME;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }


//    @Bean
//    public SqlSessionFactoryBean getSqlSessionFactory(){
//        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
//        <property name="dataSource" ref="dataSource" />
//        <property name="configLocation"  value="classpath:/com/sannong/project/infrastructure/persistence/mybatis/config/mybatis-config.xml" />
//        </bean>
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource();
//        sqlSessionFactoryBean.setConfiLocation();
//    }







    //@ConfigurationProperties(prefix = DataSourceAutoConfiguration.CONFIGURATION_PREFIX)
    /*
    @Bean
    @ConfigurationProperties(prefix="datasource.mine")
    public DataSource dataSource() {
        DataSourceBuilder factory = DataSourceBuilder
                .create(this.properties.getClassLoader())
                .driverClassName(this.properties.getDriverClassName())
                .url(this.properties.getUrl())
                .username(this.properties.getUsername())
                .password(this.properties.getPassword());
        return factory.build();
    }
    */
//    @Value("${db.driver}")
//    private String DB_DRIVER;
//
//    @Value("${db.password}")
//    private String DB_PASSWORD;
//
//    @Value("${db.url}")
//    private String DB_URL;
//
//    @Value("${db.username}")
//    private String DB_USERNAME;
//
//    @Value("${hibernate.dialect}")
//    private String HIBERNATE_DIALECT;
//
//    @Value("${hibernate.show_sql}")
//    private String HIBERNATE_SHOW_SQL;
//
//    @Value("${hibernate.hbm2ddl.auto}")
//    private String HIBERNATE_HBM2DDL_AUTO;
//
//    @Value("${entitymanager.packagesToScan}")
//    private String ENTITYMANAGER_PACKAGES_TO_SCAN;
//
//    @Bean
//    public DataSource dataSource() {
//        DataSource dataSource = new DataSource();
//        dataSource.setDriverClassName(DB_DRIVER);
//        dataSource.setUrl(DB_URL);
//        dataSource.setUsername(DB_USERNAME);
//        dataSource.setPassword(DB_PASSWORD);
//        return dataSource;
//    }
//
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource());
//        sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
//        hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
//        hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
//        sessionFactoryBean.setHibernateProperties(hibernateProperties);
//
//        return sessionFactoryBean;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//        HibernateTransactionManager transactionManager =
//                new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }

} // class DatabaseConfig
