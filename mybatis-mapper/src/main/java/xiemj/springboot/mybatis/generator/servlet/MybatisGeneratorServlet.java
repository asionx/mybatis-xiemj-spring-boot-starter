package xiemj.springboot.mybatis.generator.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class MybatisGeneratorServlet extends HttpServlet{

    private String propertiesPath;
    private String targetPath;

    public MybatisGeneratorServlet(String propertiesPath,String targetPath){
        super();
        this.propertiesPath=propertiesPath;
        this.targetPath=targetPath;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String datastr=req.getParameter("datastr");
        BaseInfo base= JSON.toJavaObject(JSON.parseObject(datastr),BaseInfo.class);
          //如果为表别名为空，表别名默认驼峰
        if(StringUtil.isEmpty(base.getTableAlis())){
            base.setTableAlis(StringUtil.camelCaseName(base.getTableName()));
        }
        Properties pro= GeneratorSqlUtils.BaseInfoToPro(base);
        pro.setProperty("targetPath",targetPath);
        if(StringUtil.isEmpty(base.getJavaModel())){
            base.setJavaModel("com.demo.model");
        }
        if(StringUtil.isEmpty(base.getJavaClient())){
            base.setJavaModel("com.demo.mapper");
        }
        if(StringUtil.isEmpty(base.getSqlMap())){
            base.setJavaModel("mapper");
        }
        FileOutputStream oFile = new FileOutputStream(new File(this.propertiesPath));
        //存储配置
        pro.store(oFile,"sqlGenerator");
        boolean isTk=false;
        if(base.getType()!=null&&base.getType()==1){
            isTk=true;
        }
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        try {
            List<String> warning = GeneratorSqlUtils.generator(this.propertiesPath, isTk);
            boolean hasError = false;
            StringBuffer errorStr = new StringBuffer();
            if (warning.size() > 0) {
                for (int i = 0; i < warning.size(); i++) {
                    if (warning.get(i).startsWith("Existing")) {
                        warning.remove(i);
                    }
                }
                if (warning.size() > 0) {
                    hasError = true;
                    for (String key : warning) {
                        errorStr.append(key + ",");
                    }
                }
            }
            Map<String,String> result=new HashMap<>();
            if(hasError) {
                result.put("code","1");
                result.put("message",errorStr.substring(0,errorStr.length()-1).toString());
                out.println(JSON.toJSONString(result));
            }else{
                result.put("code","0");
                out.println(JSON.toJSONString(result));
            }
        }catch (Exception e){
            Map<String,String> result=new HashMap<>();
            result.put("code","1");
            result.put("message",e.getMessage());
            out.println(JSON.toJSONString(result));
        }finally {
            out.close();
        }


    }


}
