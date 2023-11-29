package com.example.cart.Configuration.datasource;

import java.util.HashMap;

public class ThreadTenantStorage extends HashMap<String, String> {

	private static InheritableThreadLocal<ThreadTenantStorage> currentTenant = new InheritableThreadLocal<ThreadTenantStorage>() {
		public ThreadTenantStorage initialValue() {
			return new ThreadTenantStorage();
		}
	};

	public static void setTenantId(ThreadTenantStorage tenantId) {
		currentTenant.set(tenantId);
	}

	public static ThreadTenantStorage getCache() {
		return currentTenant.get();
	}
	
	public static void clearCache() {
		currentTenant.remove();
	}

}
