package com.example.cart.Configuration.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tenants")
public class DataSourceProperties {
	
	private static final Log LOG = LogFactory.getLog(DataSourceProperties.class);

	private Map<Object, Object> datasources = new LinkedHashMap<>();

	public Map<Object, Object> getDatasources() {
		return datasources;
	}

	public void setDatasources(Map<String, Map<String, String>> datasources) {
		datasources.forEach((key, value) -> {
			LOG.info("*** Key *** "+key);
			this.datasources.put(key, convert(value));
			});
		}

	public DataSource convert(Map<String, String> source) {
		return DataSourceBuilder.create().url(source.get("jdbcUrl")).driverClassName(source.get("driverClassName"))
				.username(source.get("username")).password(source.get("password")).build();
	}
}
