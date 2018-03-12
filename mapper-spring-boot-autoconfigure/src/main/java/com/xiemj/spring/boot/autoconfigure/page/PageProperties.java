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

package com.xiemj.spring.boot.autoconfigure.page;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiemingjie
 */
@ConfigurationProperties(prefix =  "xiemj.pagehelper")
public class PageProperties  {

    public String enableRun;

    private Properties properties = new Properties();


    public String getEnableRun() {
        return enableRun;
    }

    public void setEnableRun(String enableRun) {
        this.enableRun = enableRun;
    }

    public Properties getProperties() {
        return properties;
    }

    public String getPageSizeZero() {
        return properties.getProperty("pageSizeZero");
    }


    public void setPageSizeZero(String pageSizeZero) {
        properties.setProperty("pageSizeZero", pageSizeZero);
    }

    public String getReasonable() {
        return properties.getProperty("reasonable");
    }

    public void setReasonable(String reasonable) {
        properties.setProperty("reasonable", reasonable);
    }


    public String getDialect() {
        return properties.getProperty("dialect");
    }

    public void setDialect(String dialect) {
        properties.setProperty("dialect", dialect);
    }

    public String getHelperDialect() {
        return properties.getProperty("helperDialect");
    }

    public void setHelperDialect(String helperDialect) {
        properties.setProperty("helperDialect", helperDialect);
    }

    public String getAutoRuntimeDialect() {
        return properties.getProperty("autoRuntimeDialect");
    }

    public String getCloseConn() {
        return properties.getProperty("closeConn");
    }

    public void setCloseConn(String closeConn) {
        properties.setProperty("closeConn", closeConn);
    }

}
