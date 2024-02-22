package com.example.userorderservice.Interceptor;

public class TenantContext {
    private static final ThreadLocal<String> currentTenant=new InheritableThreadLocal<>();

    public static String getCurrentTenant(){
        return currentTenant.get();
    }
    public static void setCurrentTenant(String tenant){
        currentTenant.set(tenant);
    }
}
