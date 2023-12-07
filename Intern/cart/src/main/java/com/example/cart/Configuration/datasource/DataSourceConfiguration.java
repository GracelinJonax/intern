package com.example.cart.Configuration.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

	@Value("${tenants.default}")
	private String defaultTenant;

	private final DataSourceProperties dataSourceProperties;

	public DataSourceConfiguration(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

	@Bean
	public DataSource dataSource() {
		TenantRoutingDataSource customDataSource = new TenantRoutingDataSource();
		customDataSource.setTargetDataSources(dataSourceProperties.getDatasources());
		customDataSource.setDefaultTargetDataSource(dataSourceProperties.getDatasources().get(defaultTenant));
		return customDataSource;
	}

}
