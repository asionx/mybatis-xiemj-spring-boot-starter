package com.xiemj.spring.boot.autoconfigure.druid;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xiemingjie
 * @version 1.0.0
 * @Type DruidDataSourceConfiguration
 * @Desc druid加载
 * @Date 2018/1/25
 */
abstract class DruidDataSourceConfiguration {

    @SuppressWarnings("unchecked")
    protected <T> T createDataSource(DataSourceProperties properties,
                                     Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
    static class durid extends DruidDataSourceConfiguration {

        @Bean
        public DruidDataSource dataSource(DataSourceProperties properties,DruidFilterProperties druidFilterProperties) {
            DruidDataSource dataSource = createDataSource(properties, DruidDataSource.class);
            //引入过滤器
            List<Filter> filterList=new ArrayList<Filter>();
            try {
                if(druidFilterProperties.getProxyFilters()!=null&&druidFilterProperties.getProxyFilters().size()>0) {
                    for(String filter:druidFilterProperties.getProxyFilters()) {
                        Class clazz;
                        if(filter.contains(".")) {
                            clazz= Class.forName(filter);
                        }else{
                            clazz= Class.forName("com.xiemj.specification.mybatis.filter."+filter);
                        }
                        filterList.add((Filter) clazz.newInstance());
                    }
                }

            } catch (Exception e) {

            }
            if(filterList.size()>0) {
                dataSource.setProxyFilters(filterList);
            }
            return dataSource;
        }

    }

}
