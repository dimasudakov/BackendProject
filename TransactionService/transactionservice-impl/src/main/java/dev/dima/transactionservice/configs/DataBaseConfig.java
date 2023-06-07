package dev.dima.transactionservice.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.dima.transactionservice.configs.properties.DatabaseProperties;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "dev.dima.transactionservice.models")
@RequiredArgsConstructor
public class DataBaseConfig {

    private final DatabaseProperties databaseProperties;

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseProperties.getUrl());
        hikariConfig.setMaximumPoolSize(databaseProperties.getPoolSize());
        hikariConfig.setUsername(databaseProperties.getUsername());
        hikariConfig.setPassword(databaseProperties.getPassword());
        hikariConfig.setDriverClassName(databaseProperties.getDriverClassName());
        return hikariConfig;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/changelog-master.yaml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

}

