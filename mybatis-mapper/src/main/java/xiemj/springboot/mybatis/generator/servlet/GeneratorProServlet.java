package xiemj.springboot.mybatis.generator.servlet;

import java.io.*;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import xiemj.springboot.mybatis.generator.GeneratorSqlUtils;
import xiemj.springboot.mybatis.generator.model.BaseInfo;
import xiemj.springboot.mybatis.pagetool.util.StringUtil;

/**
 * @author xiemingjie
 * @version 1.0.0
 * @Type MybatisGeneratorServlet
 * @Desc
 * @Date 2018/3/2
 */
public class GeneratorProServlet extends HttpServlet{


    private String propertiesPath;
    private String url;
    private String username;
    private String password;

    public GeneratorProServlet(){
        super();

    }
    public GeneratorProServlet(String url,String username,String password,String propertiesPath){
        super();
        this.propertiesPath=propertiesPath;
        this.url=url;
        this.password=password;
        this.username=username;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties pro = new Properties();
        FileInputStream in = new FileInputStream(this.propertiesPath);
        pro.load(in);
        in.close();
        FileOutputStream oFile = new FileOutputStream(new File(this.propertiesPath));
        if(StringUtil.isEmpty(pro.getProperty("jdbc.url"))) {
            if (StringUtil.isNotEmpty(this.url)) {
                pro.setProperty("jdbc.url", this.url);
            }
        }
        if(StringUtil.isEmpty(pro.getProperty("jdbc.username"))) {
            if(StringUtil.isNotEmpty(this.username)){
                pro.setProperty("jdbc.username",this.username);
            }
        }
        if(StringUtil.isEmpty(pro.getProperty("jdbc.password"))) {
            if(StringUtil.isNotEmpty(this.password)){
                pro.setProperty("jdbc.password",this.password);
            }
        }
        pro.store(oFile, "sqlGenerator");
        oFile.close();
        BaseInfo baseInfo= GeneratorSqlUtils.ProToBaseInfo(pro);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSONString(baseInfo));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
