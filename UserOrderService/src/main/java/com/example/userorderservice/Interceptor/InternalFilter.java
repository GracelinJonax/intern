package com.example.userorderservice.Interceptor;

import com.example.userorderservice.service.UserOrderService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class InternalFilter extends OncePerRequestFilter {
    @Autowired
    UserOrderService userOrderService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tenantId = request.getHeader("tenantId");

        if (request.getServletPath().equalsIgnoreCase("/tenant")
                || request.getServletPath().equalsIgnoreCase("/loginTenant"))
            filterChain.doFilter(request, response);

        else if (tenantId != null && userOrderService.isTenantValid(tenantId)) {
            TenantContext.setCurrentTenant(tenantId);

            filterChain.doFilter(request, response);
        }
        else
        throw new RuntimeException("tenantId not present or not valid");

    }
}
