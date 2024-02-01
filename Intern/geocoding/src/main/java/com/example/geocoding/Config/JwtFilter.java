package com.example.geocoding.Config;

import com.example.geocoding.Exception.BadRequestException;
import com.example.geocoding.Model.Company;
import com.example.geocoding.Repository.CompanyRepository;
import com.example.geocoding.Repository.RequestResponseLogRepository;
import com.example.geocoding.Repository.Service.CompanyRepoService;
import com.example.geocoding.Service.Services;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CompanyRepoService companyRepoService;
    private final RequestResponseLogRepository requestResponseLogRepository;
    private final Services services;

    public JwtFilter(JwtService jwtService, CompanyRepoService companyRepoService, RequestResponseLogRepository requestResponseLogRepository, Services services) {
        this.jwtService = jwtService;
        this.companyRepoService = companyRepoService;
        this.requestResponseLogRepository = requestResponseLogRepository;
        this.services = services;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        System.out.println(SecurityContextHolder.getContext().getAuthentication()+"  authentication");
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Company> company = companyRepoService.findByCompanyName(username);
            if (company.isEmpty())
                throw new UsernameNotFoundException("username not found");
            if (jwtService.isTokenValid(jwt, company.get()) && services.isSubscribed(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(company, null, new ArrayList<>());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else
                throw new BadRequestException("token invalid or not subscribed");
        }
        System.out.println(SecurityContextHolder.getContext().getAuthentication()+"  authentication");
        filterChain.doFilter(requestWrapper, responseWrapper);
        services.saveRequestResponse(requestWrapper, request, responseWrapper, response, jwt);
        responseWrapper.copyBodyToResponse();
    }
}
