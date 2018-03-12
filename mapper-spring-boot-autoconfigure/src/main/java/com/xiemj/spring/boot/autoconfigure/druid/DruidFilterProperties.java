package com.xiemj.spring.boot.autoconfigure.druid;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="xiemj.druid")
public class DruidFilterProperties {

    private List<String> proxyFilters;

    public List<String> getProxyFilters() {
        return proxyFilters;
    }

    public void setProxyFilters(List<String> proxyFilters) {
        this.proxyFilters = proxyFilters;
    }
}
