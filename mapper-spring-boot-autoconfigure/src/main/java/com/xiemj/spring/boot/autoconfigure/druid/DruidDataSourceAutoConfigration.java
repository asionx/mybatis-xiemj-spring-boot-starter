package com.xiemj.spring.boot.autoconfigure.druid;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author xiemingjie
 * @version 1.0.0
 * @Type DruidDataSourceAutoConfigration
 * @Desc
 * @Date 2018/1/25
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({DataSourceProperties.class,DruidFilterProperties.class})
public class DruidDataSourceAutoConfigration {

    @Configuration
    @ConditionalOnMissingBean({ DataSource.class, XADataSource.class })
    @Import({ DruidDataSourceConfiguration.durid.class })
    @SuppressWarnings("deprecation")
    protected static class PooledDataSourceConfiguration {

    }




}
