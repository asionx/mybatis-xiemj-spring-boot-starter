package xiemj.springboot.mybatis.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.cglib.beans.BeanCopier.Generator;

import xiemj.springboot.mybatis.generator.model.BaseInfo;

public class GeneratorSqlUtils {

    public static List<String> generator(String propertiesPath,boolean isTk) throws IOException {
        Properties properties = new Properties();
        //GeneratorSqlUtils.class.getClassLoader().getResourceAsStream(propertiesPath);
        InputStream in = new FileInputStream(new File(propertiesPath));
        // 使用properties对象加载输入流
        properties.load(in);
        return generator(properties,isTk);
    }

    public static List<String> generator(Properties properties,boolean isTk) {
        String generatorPath=isTk?"/tkGeneratorConfig.xml":"/generatorConfig.xml";
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            ConfigurationParser cp = new ConfigurationParser(properties, warnings);
            InputStream inconfig=Generator.class.getResourceAsStream(generatorPath);
            Configuration config = cp
                    .parseConfiguration(inconfig);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            return warnings;
        }catch (Exception e){
            e.printStackTrace();
            return Arrays.asList("生成异常"+e.getMessage());
        }
    }

    public static BaseInfo ProToBaseInfo(Properties pro) {
        BaseInfo baseInfo=new BaseInfo();
        baseInfo.setConnectionURL(pro.getProperty("jdbc.url"));
        baseInfo.setUserId(pro.getProperty("jdbc.username"));
        baseInfo.setPassword(pro.getProperty("jdbc.password"));
        baseInfo.setJavaClient(pro.getProperty("targetMapperPackage"));
        baseInfo.setJavaModel(pro.getProperty("targetModelPackage"));
        baseInfo.setSqlMap(pro.getProperty("targetXMLPackage"));
        baseInfo.setTableName(pro.getProperty("tablename"));
        baseInfo.setTableAlis(pro.getProperty("tableAlis"));
        return baseInfo;
    }

    public static Properties BaseInfoToPro(BaseInfo baseInfo) {
        Properties pro = new Properties();
        pro.setProperty("jdbc.url",baseInfo.getConnectionURL());
        pro.setProperty("jdbc.username",baseInfo.getUserId());
        pro.setProperty("jdbc.password",baseInfo.getPassword());
        pro.setProperty("targetMapperPackage",baseInfo.getJavaClient());
        pro.setProperty("targetModelPackage",baseInfo.getJavaModel());
        pro.setProperty("targetXMLPackage",baseInfo.getSqlMap());
        pro.setProperty("tablename",baseInfo.getTableName());
        pro.setProperty("tableAlis",baseInfo.getTableAlis());
        return pro;
    }
    public static void main(String[] args) throws IOException {
//        List<String> warnings= GeneratorSqlUtils.generator("config.properties",false);
//        System.out.println(JSON.toJSONString(warnings));


    }
}