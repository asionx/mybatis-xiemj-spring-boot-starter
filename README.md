# mybatis-guahao-spring-boot-starter
about mybatis tool 

使用说明，引入maven包（私服）mapper-spring-boot-autoconfigure 依赖于mybatis-mapper，mybatis-mapper可以独立引用
        <p><dependency>
            <groupId>com.xiemj.spring.boot</groupId>
            <artifactId>mapper-spring-boot-autoconfigure</artifactId>
            <version>0.0.2-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.xiemj.spring.boot</groupId>
            <artifactId>mybatis-mapper</artifactId>
            <version>0.0.2-SNAPSHOT</version>
        </dependency></p>
        
mapper-spring-boot-autoconfigure  application.properties配置说明：
#启用插件分页,此配置主要是对pagehepler count sql生成去除了select count(*) from (select) 形式分页，如果不支持生成，直接返回异常
#其余添加了部分返回结果转化为公司指定分页page
xiemj.pagehelper.enable-run=true
#数据库dialect
xiemj.pagehelper.helper-dialect=mysql
#sql合理化，其他配置同pagehepler,
xiemj.pagehelper.reasonable=true

#启动web 进行基于数据表生成对应xml ,model, mapper, 生成地址:web项目路径/generator    生成工具类:GeneratorSqlUtils.java
xiemj.generator.is-need-web=true

#当指定连接池管理为druid时，可通过配置自定多个filter进行sql的监控，以“，”分隔
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
xiemj.druid.proxy-filters=com.demo.PersonFilter    PersonFilter继承FilterEventAdapter

