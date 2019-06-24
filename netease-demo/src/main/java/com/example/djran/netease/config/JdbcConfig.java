package com.example.djran.netease.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.example.djran.netease.dao.PostDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created on 2018/12/28
 * 数据源配置文件
 * @author d.djran@gmail.com
 */
@Slf4j
@Configuration()
@EnableJpaRepositories(basePackageClasses = PostDao.class)
public class JdbcConfig {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean("dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        DruidDataSource basicDataSource = new DruidDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxActive(50);
        basicDataSource.setFilters("stat,wall");
        log.info("使用本地数据源：" + basicDataSource.getUrl());
        return basicDataSource;
    }

    @Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) throws SQLException {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws SQLException {
        return builder.dataSource(this.dataSource())
                //设置实体类所在位置
                .packages("com.example.djran.netease.model")
                .persistenceUnit("persistenceUnit")
                .build();
    }
    @Bean(name = "transactionManager")
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws SQLException {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }
}