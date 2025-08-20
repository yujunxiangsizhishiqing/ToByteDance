package demoPackage.demoInterface;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface SalMgtService {

    final String PRIKEY_ABNORMAL = "主键异常";

    /**
     * 销管配置表名称
     * */
    final String SM_CFGTABLE_LACOM = "lacom";
    final String SM_CFGTABLE_LDCOMF = "ldcomf";
    final String SM_CFGTABLE_LMRISKAPP = "lmriskapp";
    final String SM_CFGTABLE_LAGXJDBANKCOMPACT = "lagxjdbankcompact";
    final String SM_CFGTABLE_LAGXJDRATECHARGE = "lagxjdratecharge";

    /**
     * druid数据源配置文件的路径[druid.properties]
     * */
    final String DRUID_PROPERTIES_PARH = "src\\main\\resources\\druid.properties";//暂且用final String,这种配置操作应该有个统一管理的方式。

    /**
     * 用户规定的JSON对象名称
     * */
    final static String JSON_OBJECT_NAME = "jsonobject";

    /**
     * 使用的txt解析的规则
     * */
    final static String PARSE_BY_JSON = "0";//JSON
    final static String PARSE_BY_SEPARATION_CHAR = "1";//分隔符

    /**
     * 文件类后缀符
     * */
    final static String FILE_TXT = ".txt";

    /**
     * 分隔符,用于String.split方法，内部添加了‘//’转义符。
     * */
    final static String SEPARATION_CHAR_COMMA = "\\,";
    final static String SEPARATION_CHAR_VERTICAL_LINE = "\\|";
    final static String SEPARATION_CHAR_DOUBLE_VERTICAL_LINE = "\\|\\|";
    final static String SEPARATION_CHAR_STAR = "\\*";
    final static String SEPARATION_CHAR_MIDLINE = "\\-";

    /**
     * SqlServer数据配置表的查询语句，语句未添加限制条件。
     * LACOM-代理机构表
     * LDComF-直销管理机构信息表
     * LMRiskApp-险种信息表
     * LAGXJDBANKCOMPACT-合同信息表
     * LAGXJDRATECHARGE-手续费率表
     * */
    //Count结果的字段名称
    final String SQLSEERVER_COUNT_RESULT_COLUMN_NAME = "TOTAL";
    //LACOM-代理机构表
    final String SQLSERVER_QUERY_DATA_FROM_LACOMD = "SELECT A.* from LACOM A";
    final String SQLSERVER_COUNT_LACOMD = "SELECT COUNT(1) "+SQLSEERVER_COUNT_RESULT_COLUMN_NAME+" FROM LACOM";//SELECT COUNT(1) TOTAL FROM LACOM
    //LDComF-直销管理机构信息表
    final String SQLSERVER_QUERY_DATA_FROM_LDCOMF = "SELECT A.* from LDComF A";
    final String SQLSERVER_COUNT_LDCOMF = "SELECT COUNT(1) "+SQLSEERVER_COUNT_RESULT_COLUMN_NAME+" FROM LDComF";//SELECT COUNT(1) TOTAL FROM LDComF
    //LMRiskApp-险种信息表
    final String SQLSERVER_QUERY_DATA_FROM_LMRISKAPP = "SELECT A.* from LMRiskApp A";
    final String SQLSERVER_COUNT_LMRISKAPP = "SELECT COUNT(1) "+SQLSEERVER_COUNT_RESULT_COLUMN_NAME+" FROM LMRiskApp";//SELECT COUNT(1) TOTAL FROM LMRiskApp
    //LAGXJDBANKCOMPACT-合同信息表
    final String SQLSERVER_QUERY_DATA_FROM_LAGXJDBANKCOMPACT = "SELECT A.* from LAGXJDBANKCOMPACT A";
    final String SQLSERVER_COUNT_LAGXJDBANKCOMPACT = "SELECT COUNT(1) "+SQLSEERVER_COUNT_RESULT_COLUMN_NAME+" FROM LAGXJDBANKCOMPACT";//SELECT COUNT(1) TOTAL FROM LAGXJDBANKCOMPACT
    //LAGXJDRATECHARGE-手续费率表
    final String SQLSERVER_QUERY_DATA_FROM_LAGXJDRATECHARGE = "SELECT A.* from LAGXJDRATECHARGE A";
    final String SQLSERVER_COUNT_LAGXJDRATECHARGE = "SELECT COUNT(1) "+SQLSEERVER_COUNT_RESULT_COLUMN_NAME+" FROM LAGXJDRATECHARGE";//SELECT COUNT(1) TOTAL FROM LAGXJDRATECHARGE

    /**
     * 本次操作的数据类型:(1)业务数据;(2)配置表数据
     * */
    final static String DATA_BY_BUSINESS = "Business";
    final static String DATA_BY_CFG = "Cfg";


    /**
     * 当前使用的txt解析方式
     * */
    final static String PARSE_BY_DEFAULT = PARSE_BY_JSON;
    /**
     * 当前使用的分隔符
     * */
    final static String SEPARATION_CHAR_DEFAULT = SEPARATION_CHAR_DOUBLE_VERTICAL_LINE;


    /**
     * @apiNote 销管系统业务数据导入：根据传入得JSON格式配置信息，获取数据源并将业务数据导入‘tra_sxf_interface_details_t’表中。
     * @param salMgtCfgInfo JSON格式包装的配置信息，包括数据源文件路径等信息。
     * @return boolean [true]导入成功;[false]导入失败
     * */
    boolean importBusinessData(String salMgtCfgInfo) throws Exception;

    /**
     * @note 需求不需要该接口，未实现。日后按需拓展。
     * @author wx
     * @date 2023/01/09
     * */
    /*
     * @apiNote 销管配置表数据的导入：根据传入得JSON格式配置信息，获取数据源并将四种配置数据导入对应的配置表中
     * @param salMgtCfgInfo JSON格式包装的配置信息，包括数据源文件路径等信息。
     * @return boolean [true]导入成功;[false]导入失败
     * */
    boolean importCfgTableData(String salMgtCfgInfo) throws Exception;

    /**
     * @apiNote 销管配置表数据的导入：销管给予业财配置表的查询权限，业财定期查询，查到之后同步到业财数据库中。业财的数据库配置表结构应和销管的数据库表数据结构保持一致。
     * @table [LACOM-代理机构表];[LDComF-直销管理机构信息表];[LAGXJDBANKCOMPACT-合同信息表];[LAGXJDRATECHARGE-手续费率表];[LMRiskApp-险种信息表]
     * */
    boolean importCfgTableData() throws Exception;

    /**
     * @apiNote 业财数据库五张配置表的数据备份，防止导入过程中出现问题,无法恢复
     * */
    boolean backupCfgTableData();
    /**
     * @apiNote 清空业财数据库五张配置表的数据
     * */
    boolean truncateCfgTable();
    /**
     * @apiNote 清空业财数据库五张配置备份表的数据
     * */
    boolean truncateBackupCfgTable();

    /**
     * @apiNote 文件完整路径拼接
     * */
//    default String concatCompleteFilePath(@NotNull String dirPath, @NotNull String fileName, @NotNull String fileType){
//        switch (fileType){
//            case FILE_TXT:
//                return dirPath + "/" + fileName + FILE_TXT;
//            default:
//                return null;
//        }
//    }

    /**
     * @apiNote 获取当前系统时间字符串
     * @return 返回的是String类，格式[yyyy-MM-dd HH:mm:ss],24h
     * */
    default String getCurrentDateTimeStr(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

//    /**
//     * @apiNote 获取当前系统时间
//     * @return 返回的是Date类
//     * */
//    default Date getCurrentDateTime(){
//        return new Date();
//    }

}
