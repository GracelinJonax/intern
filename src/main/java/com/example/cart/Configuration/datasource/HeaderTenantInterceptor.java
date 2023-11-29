package com.example.cart.Configuration.datasource;


import com.example.cart.Exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

@Slf4j
@Component
public class HeaderTenantInterceptor implements WebRequestInterceptor {

	public static final String TENANT_HEADER = "X-tenant";

	@Value("${tenants.default}")
	String defaultTenant;
	
	@Autowired
	DataSourceProperties dataSourceProperties;

	@Override
	public void preHandle(WebRequest request) throws Exception {
		String tenantHeader = "X-tentant";
		log.info(request.getHeader(TENANT_HEADER));
		
		if (null == tenantHeader) {
			tenantHeader = request.getHeader(TENANT_HEADER);
		}else if(tenantHeader.contains("\"")){
			tenantHeader = tenantHeader.substring(1, tenantHeader.length() - 1);
		}
		
		if (tenantHeader != null && !dataSourceProperties.getDatasources().containsKey(tenantHeader)) {
			throw new BadRequestException("tenant Id is invalid");
		}
		if(null == tenantHeader) {
			tenantHeader = defaultTenant;
		}
		ThreadTenantStorage.getCache().put(TENANT_HEADER, tenantHeader);
	}

	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		// TO-DO
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
		ThreadTenantStorage.clearCache();
	}
}
