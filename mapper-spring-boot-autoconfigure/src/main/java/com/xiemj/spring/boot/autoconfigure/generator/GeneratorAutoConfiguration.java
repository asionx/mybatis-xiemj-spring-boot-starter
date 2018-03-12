/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.xiemj.spring.boot.autoconfigure.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.alibaba.druid.util.StringUtils;
import xiemj.springboot.mybatis.pagetool.util.StringUtil;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xiemj.springboot.mybatis.generator.servlet.GeneratorHtmlServlet;
import xiemj.springboot.mybatis.generator.servlet.GeneratorProServlet;
import xiemj.springboot.mybatis.generator.servlet.MybatisGeneratorServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author xiemingjie
 */
@Configuration
@ConditionalOnBean(DataSource.class)
@EnableConfigurationProperties({DataSourceProperties.class,GeneratorProperties.class})
@ConditionalOnProperty(name = "guahao.generator.isNeedWeb",havingValue = "true",matchIfMissing = true)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class GeneratorAutoConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private GeneratorProperties generatorProperties;

    private String proPath;

    private String targetPath;

    //建立基础目录和配置文件
    @PostConstruct
    public void setProPath() throws IOException {
        String path=generatorProperties.getTargetPath();
        if(StringUtils.isEmpty(path)){
            path=System.getProperty("user.dir")+"/target";
        }
        String basePath=path+"/mybatisGenerator";
        if(StringUtil.isEmpty(targetPath)){
            targetPath=basePath;
        }
        File baseMkdir =new File(basePath);
        if(!baseMkdir.exists()){
            baseMkdir.mkdir();
        }
        File propertiesFile=new File(basePath+"/mybatisGenerator.properties");
        if(!propertiesFile.exists()){
            propertiesFile.createNewFile();
            Properties properties=new Properties();
            //设置生成路径
            properties.setProperty("targetPath",baseMkdir.getPath());
            properties.store(new FileOutputStream(propertiesFile),"create");
        }
        this.proPath=propertiesFile.getPath();

    }



    @Bean
    public ServletRegistrationBean generatorHtmlRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new GeneratorHtmlServlet(),"/generator/*");
        return registration;
    }

    @Bean
    public ServletRegistrationBean generatorProRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new GeneratorProServlet(
                dataSourceProperties.getUrl(),dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword(),proPath),"/mybatis/generator/pros");
        return registration;
    }

    @Bean
    public ServletRegistrationBean generatorRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new MybatisGeneratorServlet(proPath,targetPath),
                "/mybatis/generator/build");
        return registration;
    }
}
