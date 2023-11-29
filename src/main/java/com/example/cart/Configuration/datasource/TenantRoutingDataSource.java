package com.example.cart.Configuration.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

	public static final String TENANT_HEADER = "X-tenant";

	@Override
	protected Object determineCurrentLookupKey() {
		return ThreadTenantStorage.getCache().get(TENANT_HEADER);
	}
}