package com.empik.restservice.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "application.db")
@EnableTransactionManagement
@EnableJpaRepositories
@Slf4j
@Data
public class DatabaseConfig {

    private String driverClassName;
    private String connectionString;
    private String applicationName;
    private String host;
    private Integer port;
    private String name;
    private String user;
    private String password;
    private Integer maxPoolSize;
    private Integer minimumIdle;
    private Integer connectionTimeout;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariDataSource d = new HikariDataSource();
        d.setDriverClassName(driverClassName);
        d.setJdbcUrl(connectionString);
        d.setUsername(user);
        d.setPassword(password);
        d.setPoolName(applicationName);
        d.setMaximumPoolSize(maxPoolSize);
        d.setMinimumIdle(minimumIdle);
        d.setConnectionTimeout(connectionTimeout);
        return d;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
