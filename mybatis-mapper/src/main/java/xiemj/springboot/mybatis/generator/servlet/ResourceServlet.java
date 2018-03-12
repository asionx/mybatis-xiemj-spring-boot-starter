/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xiemj.springboot.mybatis.generator.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.http.util.IPAddress;
import com.alibaba.druid.support.http.util.IPRange;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.druid.util.Utils;

@SuppressWarnings("serial")
public abstract class ResourceServlet extends HttpServlet {

    private final static Log   LOG                 = LogFactory.getLog(ResourceServlet.class);
    public static final String PARAM_NAME_ALLOW    = "allow";
    public static final String PARAM_NAME_DENY     = "deny";
    public static final String PARAM_REMOTE_ADDR   = "remoteAddress";
    protected List<IPRange>    allowList           = new ArrayList<IPRange>();
    protected List<IPRange>    denyList            = new ArrayList<IPRange>();

    protected final String     resourcePath;

    protected String           remoteAddressHeader = null;

    public ResourceServlet(String resourcePath){
        this.resourcePath = resourcePath;
    }

    public void init() throws ServletException {
        initAuthEnv();
    }

    private void initAuthEnv() {
        String paramRemoteAddressHeader = getInitParameter(PARAM_REMOTE_ADDR);
        if (!StringUtils.isEmpty(paramRemoteAddressHeader)) {
            this.remoteAddressHeader = paramRemoteAddressHeader;
        }
        try {
            String param = getInitParameter(PARAM_NAME_ALLOW);
            if (param != null && param.trim().length() != 0) {
                param = param.trim();
                String[] items = param.split(",");

                for (String item : items) {
                    if (item == null || item.length() == 0) {
                        continue;
                    }
                    IPRange ipRange = new IPRange(item);
                    allowList.add(ipRange);
                }
            }
        } catch (Exception e) {
            String msg = "initParameter config error, allow : " + getInitParameter(PARAM_NAME_ALLOW);
            LOG.error(msg, e);
        }

        try {
            String param = getInitParameter(PARAM_NAME_DENY);
            if (param != null && param.trim().length() != 0) {
                param = param.trim();
                String[] items = param.split(",");

                for (String item : items) {
                    if (item == null || item.length() == 0) {
                        continue;
                    }

                    IPRange ipRange = new IPRange(item);
                    denyList.add(ipRange);
                }
            }
        } catch (Exception e) {
            String msg = "initParameter config error, deny : " + getInitParameter(PARAM_NAME_DENY);
            LOG.error(msg, e);
        }
    }

    public boolean isPermittedRequest(String remoteAddress) {
        boolean ipV6 = remoteAddress != null && remoteAddress.indexOf(':') != -1;

        if (ipV6) {
            return "0:0:0:0:0:0:0:1".equals(remoteAddress) || (denyList.size() == 0 && allowList.size() == 0);
        }
        IPAddress ipAddress = new IPAddress(remoteAddress);
        for (IPRange range : denyList) {
            if (range.isIPAddressInRange(ipAddress)) {
                return false;
            }
        }

        if (allowList.size() > 0) {
            for (IPRange range : allowList) {
                if (range.isIPAddressInRange(ipAddress)) {
                    return true;
                }
            }

            return false;
        }

        return true;
    }

    protected String getFilePath(String fileName) {
        return resourcePath + fileName;
    }

    //加载资源
    protected void returnResourceFile(String fileName, String uri, HttpServletResponse response)
            throws ServletException, IOException {
        String filePath = getFilePath(fileName);
        
        if (filePath.endsWith(".html")) {
            response.setContentType("text/html; charset=utf-8");
        }
        if (fileName.endsWith(".jpg")) {
            byte[] bytes = Utils.readByteArrayFromResource(filePath);
            if (bytes != null) {
                response.getOutputStream().write(bytes);
            }

            return;
        }

        String text = Utils.readFromResource(filePath);
        if (text == null) {
            response.sendRedirect(uri + "/index.html");
            return;
        }
        if (fileName.endsWith(".css")) {
            response.setContentType("text/css;charset=utf-8");
        } else if (fileName.endsWith(".js")) {
            response.setContentType("text/javascript;charset=utf-8");
        }
        response.getWriter().write(text);
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();
        response.setCharacterEncoding("utf-8");
        if (contextPath == null) { // root context
            contextPath = "";
        }
        String uri = contextPath + servletPath;
        String path = requestURI.substring(contextPath.length() + servletPath.length());

        if (!isPermittedRequest(request)) {
            path = "/nopermit.html";
            returnResourceFile(path, uri, response);
            return;
        }

        if ("".equals(path)) {
            if (contextPath.equals("") || contextPath.equals("/")) {
                response.sendRedirect("/generator/index.html");
            } else {
                response.sendRedirect("generator/index.html");
            }
            return;
        }
        if ("/".equals(path)) {
            response.sendRedirect("index.html");
            return;
        }
        // find file in resources path
        returnResourceFile(path, uri, response);
    }


    public boolean isPermittedRequest(HttpServletRequest request) {
        String remoteAddress = getRemoteAddress(request);
        return isPermittedRequest(remoteAddress);
    }

    protected String getRemoteAddress(HttpServletRequest request) {
        String remoteAddress = null;
        
        if (remoteAddressHeader != null) {
            remoteAddress = request.getHeader(remoteAddressHeader);
        }
        
        if (remoteAddress == null) {
            remoteAddress = request.getRemoteAddr();
        }
        
        return remoteAddress;
    }

}
