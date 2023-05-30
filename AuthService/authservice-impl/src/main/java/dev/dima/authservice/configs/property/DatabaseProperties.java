package dev.dima.authservice.configs.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DatabaseProperties {

    private final String url;
    private final String username;
    private final String password;
    private final int poolSize;
    private final String driverClassName;

    public DatabaseProperties(@Value("${spring.datasource.url}") String url,
                              @Value("${spring.datasource.username}") String username,
                              @Value("${spring.datasource.password}") String password,
                              @Value("${spring.datasource.hikari.maximum-pool-size}") int poolSize,
                              @Value("${spring.datasource.driver-class-name}") String driverClassName) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
        this.driverClassName = driverClassName;
    }
}
