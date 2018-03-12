package xiemj.springboot.mybatis.generator.model;

/**
 * @author xiemingjie
 * @version 1.0.0
 * @Type BaseInfo
 * @Desc
 * @Date 2018/3/2
 */
public class BaseInfo {
    private String connectionURL;
    private String userId;
    private String password;
    private String javaModel;
    private String sqlMap;
    private String javaClient;
    private String tableName;
    private String tableAlis;
    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getConnectionURL() {

        return connectionURL;
    }

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJavaModel() {
        return javaModel;
    }

    public void setJavaModel(String javaModel) {
        this.javaModel = javaModel;
    }

    public String getSqlMap() {
        return sqlMap;
    }

    public void setSqlMap(String sqlMap) {
        this.sqlMap = sqlMap;
    }

    public String getJavaClient() {
        return javaClient;
    }

    public void setJavaClient(String javaClient) {
        this.javaClient = javaClient;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableAlis() {
        return tableAlis;
    }

    public void setTableAlis(String tableAlis) {
        this.tableAlis = tableAlis;
    }
}
