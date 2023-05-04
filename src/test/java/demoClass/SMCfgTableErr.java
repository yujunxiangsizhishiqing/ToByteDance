package demoClass;


import java.util.Date;

/**
 * 销管导入异常数据存储表
 * 业财在销管配置数据导入过程中的异常数据需要被存储下来。
 * */
public class SMCfgTableErr {

    /**
     * 主键ID
     * */
    private String id;
    /**
     * 销管导入功能配置表的名称
     * */
    private String tableName;
    /**
     * 销管导入功能各配置表的主键名称
     * */
    private String primaryKeyName;
    /**
     * 销管导入功能各配置表的主键值
     * */
    private String primaryKeyValue;
    /**
     * 异常提示信息
     * */
    private String abnormalTips;
    /**
     * 数据创建日期：yyyy-MM-dd HH:mm:ss
     * */
    private Date createDate;

    public SMCfgTableErr() {
    }

    public SMCfgTableErr(String id, String tableName, String primaryKeyName, String primaryKeyValue, String abnormalTips, Date createDate) {
        this.id = id;
        this.tableName = tableName;
        this.primaryKeyName = primaryKeyName;
        this.primaryKeyValue = primaryKeyValue;
        this.abnormalTips = abnormalTips;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }

    public String getPrimaryKeyValue() {
        return primaryKeyValue;
    }

    public void setPrimaryKeyValue(String primaryKeyValue) {
        this.primaryKeyValue = primaryKeyValue;
    }

    public String getAbnormalTips() {
        return abnormalTips;
    }

    public void setAbnormalTips(String abnormalTips) {
        this.abnormalTips = abnormalTips;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SMCfgTableErr{" +
                "id='" + id + '\'' +
                ", tableName='" + tableName + '\'' +
                ", primaryKeyName='" + primaryKeyName + '\'' +
                ", primaryKeyValue='" + primaryKeyValue + '\'' +
                ", abnormalTips='" + abnormalTips + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
