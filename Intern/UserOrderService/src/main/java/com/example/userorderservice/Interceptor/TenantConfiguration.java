package com.example.userorderservice.Interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class TenantConfiguration {
    @Value("${defaultTenant}")
    private String defaultTenant;

    @Bean
//    @ConfigurationProperties(prefix = "tenants")
    public DataSource dataSource() {
        File[] files = Paths.get("src/main/resources/tenant").toFile().listFiles();
        Map<Object, Object> dataSource = new HashMap<>();
        for (File file : files) {
            Properties properties = new Properties();
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            try {
                properties.load(new FileInputStream(file));
                String tenantId = properties.getProperty("name");
                dataSourceBuilder.url(properties.getProperty("datasource.url"));
                dataSourceBuilder.username(properties.getProperty("datasource.username"));
                dataSourceBuilder.password(properties.getProperty("datasource.password"));
                dataSourceBuilder.driverClassName(properties.getProperty("datasource.driverClassName"));
                dataSource.put(tenantId, dataSourceBuilder.build());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        AbstractRoutingDataSource routingDataSource = new TenantDataSource();
        routingDataSource.setDefaultTargetDataSource(dataSource.get(defaultTenant));
        routingDataSource.setTargetDataSources(dataSource);
        routingDataSource.afterPropertiesSet();
        return routingDataSource;
    }
}
