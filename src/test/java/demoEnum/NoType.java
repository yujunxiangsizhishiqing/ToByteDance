package demoEnum;

/**
 * 号码类型
 */
public enum NoType {

    /** 测试用的业务流水号 */
    TEST_BUS_NO("TEST_BUS_NO", 20, "test", null, '0', PadMode.L_PAD, 100),

    /** 交易流水号 */
    TRADE_SERI("TRADE_SERI", 20, "TS", null, '0', PadMode.L_PAD, 500),

    /** 交易明细条目流水号 */
    LINE_ITEM_SERI("LINE_ITEM_SERI", 20, "LIS", null, '0', PadMode.L_PAD, 1000),

    /** 凭证流水号 */
    ACC_NO("ACC_NO", 20, "AN", null, '0', PadMode.L_PAD, 500),

    /** 凭证分录流水号 */
    ACC_ENTRIE_SERI("ACC_ENTRIE_SERI", 20, "AEN", null, '0', PadMode.L_PAD, 2000),

    /**
     * 业务类别编码
     */
    BUS_TYPE_CODE("BUS_TYPE_CODE", 5, null, null, '0', PadMode.L_PAD, 1),

    /**
     * 业务类别主键
     */
    BUS_TYPE_PK("BUS_TYPE_PK", 20, "BTPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 交易种类编码
     */
    TRADE_TYPE_NO("TRADE_TYPE_NO", 5, null, null, '0', PadMode.L_PAD, 1),

    /**
     * 业务种类主键
     */
    TRADE_TYPE_PK("TRADE_TYPE_PK", 20, "TTPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 交易数据弹性字段编码
     */
    BUS_FIELD_USE_NO("BUS_FIELD_USE_NO", 5, null, null, '0', PadMode.L_PAD, 1),

    /**
     * 交易数据弹性字段主键
     */
    BUS_FIELD_USE_PK("BUS_FIELD_USE_PK", 20, "BFUPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 交易数据信息段编号
     */
    BUS_FIELD_DEF_NO("BUS_FIELD_DEF_NO", 5, null, null, '0', PadMode.L_PAD, 1),

    /**
     * 交易数据信息段主键
     */
    BUS_FIELD_DEF_PK("BUS_FIELD_DEF_PK", 20, "BFDPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 凭证数据信息段编码
     */
    ACC_FIELD_NO("ACC_FIELD_NO", 5, null, null, '0', PadMode.L_PAD, 1),

    /**
     * 凭证数据信息段主键
     */
    ACC_FIELD_PK("ACC_FIELD_PK", 20, "AFPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 帐务规则版本号
     */
    RULES_VER_NO("RULES_VER_NO", 5, null, null, '0', PadMode.L_PAD, 1),

    /**
     * 业务模型主键
     */
    BUS_TEMP_PK("BUS_TEMP_PK", 20, "BTPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 凭证模型主键
     */
    ACC_TEMP_PK("ACC_TEMP_PK", 20, "ATPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 数据字典主键
     */
    DATA_DIC_PK("DATA_DIC_PK", 20, "DDPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 记账批次号
     */
    BATCH_NO("BATCH_NO", 20, "BAT", null, '0', PadMode.L_PAD, 1),

    /**
     * 交易明细数据与业务模型明细条目匹配关系表主键
     */
    DETAIL_TO_LINE_ITEM_PK("DETAIL_TO_LINE_ITEM_PK", 20, "DTLI", null, '0', PadMode.L_PAD, 1),
    /**
     * 业务采集规则数据表主键
     */
    AQ_TRADE_PK("AQ_TRADE_PK", 20, "AQTPK", null, '0', PadMode.L_PAD, 1),
    /**
     * 明细信息采集规则表主键
     */
    AQ_DETAIL_PK("AQ_DETAIL_PK", 20, "AQDPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 差异规则表主键
     */
    RULE_GATHER_PK("RULE_GATHER_PK", 20, "AQDPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 差异对账汇总字段意义定义表主键
     */
    ENTRIE_FIELD_PK("ENTRIE_FIELD_PK", 20, "AQDPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 差异对账结果字段意义表主键
     */
    RESULT_ENTRIE_FIELD_PK("RESULT_ENTRIE_FIELD_PK", 20, "AQDPK", null, '0', PadMode.L_PAD, 1),

    /**
     * 差异对账轨迹表批次号
     */
    ADIFF_BATCH_NO("ADIFF_BATCH_NO", 20, "BAT", null, '0', PadMode.L_PAD, 1),
    /**
     * 差异对账业财表主键
     */
    ADIFF_BALANCE_AES_GATHER_PK("ADIFF_BALANCE_AES_GATHER_PK", 20, "DAGPK", null, '0', PadMode.L_PAD, 1000),
    /**
     * 差异对账业务表主键
     */

    ADIFF_BALANCE_BUS_GATHER_PK("ADIFF_BALANCE_BUS_GATHER_PK", 20, "DBGPK", null, '0', PadMode.L_PAD, 1000),

    /**
     * 交易数据主表流水号
     */
    AD_TRADE_DATA_MAIN_SERI("AD_TRADE_DATA_MAIN_SERI", 20, "TS", null, '0', PadMode.L_PAD, 1000),

    /**
     * 新业财-系统类型主键
     */
    AES_CFG_SYS_DEF_SID("AES_CFG_SYS_DEF_SID", 30, "SYS_SID", null, '0', PadMode.L_PAD, 10),

    /**
     * 新业财-业务类别主键
     */
    AES_CFG_BUS_TYPE_DEF_SID("AES_CFG_BUS_TYPE_DEF_SID", 30, "BUS_TYPE_SID", null, '0', PadMode.L_PAD, 50),
    /**
     * 新业财-版本定义主键
     */
    AES_CFG_VERSION_DEF_SID("AES_CFG_VERSION_DEF_SID", 30, "VERSION_SID", null, '0', PadMode.L_PAD, 1),
    /**
     * 新业财-业务字段定义主键
     */
    AES_CFG_BUS_CLM_DEF_SID("AES_CFG_BUS_CLM_DEF_SID", 30, "BUS_CLM_SID", null, '0', PadMode.L_PAD, 100),

    /**
     * 新业财-账务字段定义主键
     */
    AES_CFG_ACCOUNT_CLM_DEF_SID("AES_CFG_ACCOUNT_CLM_DEF_SID", 30, "ACC_CLM_SID", null, '0', PadMode.L_PAD, 100),
    /**
     * 新业财-业务数据字典主键
     */
    AES_CFG_BUS_CLM_DICT_DEF_SID("AES_CFG_BUS_CLM_DICT_DEF_SID", 30, "BUS_DICT_SID", null, '0', PadMode.L_PAD, 100),
    /**
     * 新业财-业务采集规则主键
     */
    AES_CFG_ACQU_DEF_SID("AES_CFG_ACQU_DEF_SID", 30, "ACQU_SID", null, '0', PadMode.L_PAD, 100),
    /**
     * 新业财-明细信息采集规则主键
     */
    AES_CFG_ACQU_DTL_DEF_SID("AES_CFG_ACQU_DTL_DEF_SID", 30, "ACQU_DTL_SID", null, '0', PadMode.L_PAD, 100),
    /**
     * 新业财-财务凭证业务组合定义主键
     */
    AES_CFG_CERTIFICATE_UNIT_DEF_SID("AES_CFG_CER_UNIT_DEF_SID", 30, "CTF_UNIT_SID", null, '0', PadMode.L_PAD, 200),
    /**
     * 新业财-财务凭证业务组合字段定义主键
     */
    AES_CFG_CTF_UNIT_CLM_DEF_SID("AES_CFG_CTF_UNIT_CLM_DEF_SID", 30, "CTF_CLM_SID", null, '0', PadMode.L_PAD, 100),
    /**
     * 新业财-财务凭证转换模板定义主键
     */
    AES_CFG_CERTIFICATE_TEMP_DEF_SID("AES_CFG_CER_TEMP_DEF_SID", 30, "CTF_TEMP_SID", null, '0', PadMode.L_PAD, 500),

    /** 新业财-财务凭证转换模板字段定义主键 */
    AES_CFG_CTF_TEMP_CLM_DEF_SID("AES_CFG_CTF_TEMP_CLM_DEF_SID", 30, "CTF_TEMP_CLM_SID", null, '0', PadMode.L_PAD,
            5000),
    /**
     * 新业财-财务凭证转换模板字段定义主键
     */
    AES_CFG_DIFF_COLUMN_DEF_SID("AES_CFG_DIFF_COLUMN_DEF_SID", 30, "DIFF_CLM_SID", null, '0', PadMode.L_PAD, 1000),
    /**
     * 新业财-差异校验结果导出字段定义主键
     */
    AES_CFG_DIFF_EXP_COLUMN_SID("AES_CFG_DIFF_EXP_COLUMN_SID", 30, "EXP_CLM_SID", null, '0', PadMode.L_PAD, 1000),
    /**
     * 新业财-差异导出规则主键
     */
    AES_CFG_DIFF_CHECK_EXP_DEF_SID("AES_CFG_DIFF_CHECK_EXP_DEF_SID", 30, "CK_EXP_SID", null, '0', PadMode.L_PAD, 100),
    /**
     * 新业财-差异校验规则主键
     */
    AES_CFG_DIFF_CHECK_DEF_SID("AES_CFG_DIFF_CHECK_DEF_SID", 30, "DIFF_CK_SID", null, '0', PadMode.L_PAD, 100),

    /** 接口表主键 */
    OF_INTERFACE_ROWID("OF_INTERFACE_ROWID", 1000),

    /** 版本号NOTYPE */
    AES_CFG_VERSION_NO("AES_CFG_VERSION_NO", 10, "Ver", null, '0', PadMode.L_PAD, 1),

    /**
     * 汇总记账凭证编码表主键
     */
    AES_CFG_CTF_CODE_DEF_SID("AES_CFG_CTF_CODE_DEF_SID", 30, "CTF_CODE_SID", null, '0', PadMode.L_PAD, 10),

    /**
     * 差异校验批次号
     */
    AES_DIFF_CHECK_BATCH_NO("AES_DIFF_CHECK_BATCH_NO", 20, "BAT", null, '0', PadMode.L_PAD, 1),

    /** 差异校验 业财汇总数据表主键 */
    AES_DIFF_CHECK_AES_DATA_SID("AES_DIFF_CHECK_AES_DATA", 20, "DCASID", null, '0', PadMode.L_PAD, 300),

    /** 差异校验 业务汇总数据表主键 */
    AES_DIFF_CHECK_BUS_DATA_SID("AES_DIFF_CHECK_BUS_DATA", 20, "DCASID", null, '0', PadMode.L_PAD, 300),

    /** 批处理计划任务配置表主键 */
    AES_CFG_BATCH_DEF("AES_CFG_BATCH_DEF", 20, "TASK", null, '0', PadMode.L_PAD, 1),

    /** 批处理轨迹表主键 */
    AES_CFG_BATCH_TRACE_SID("AES_CFG_BATCH_TRACE_SID", 20, "BTE", null, '0', PadMode.L_PAD, 1),

    /** 批处理记录主键 */
    AES_BATCH_RECORD_SID("AES_BATCH_RECORD_SID", 10, "", null, '0', PadMode.L_PAD, 1),

    /** 批处理参数 */
    AES_BATCH_PARAMER_SID("AES_BATCH_PARAMER_SID", 10, "", null, '0', PadMode.L_PAD, 1),

    /** 业财平账跟踪流水号 */
    AES_MANUAL_GLOBAL_ID("AES_MANUAL_GLOBAL_ID", 10, "", null, '0', PadMode.L_PAD, 1),

    /**
     * 业务数据明细表主键
     */
    TRA_SXF_INTERFACE_DETAILS_T("TRA_SXF_INTERFACE_DETAILS_T",10,"1",null,'0',PadMode.L_PAD, 1),
    /**
     * 业务数据批次表主键
     */
    TRA_SXF_INTERFACE_BATCHES("TRA_SXF_INTERFACE_BATCHES",2,null,null,'0',PadMode.L_PAD, 1)
    ;
    /**
     * 构造函数
     *
     * @param type
     *            号码类别
     * @param length
     *            号码长度
     * @param prefix
     *            前缀
     * @param suffix
     *            后缀
     * @param pad
     *            填充字符
     * @param padMode
     *            填充方式
     * @param number
     *            一次性获得号码数量（未来避免频繁访问数据库）
     */
    private NoType(String type, int length, String prefix, String suffix, char pad, PadMode padMode, int number) {
        this.type = type;
        this.length = length;
        this.prefix = prefix == null ? "" : prefix;
        this.suffix = suffix == null ? "" : suffix;
        this.pad = pad;
        this.padMode = padMode;
        this.number = number;
    }

    private NoType(String type, int number) {
        this.type = type;
        this.number = number;
        this.length = 0;
        this.prefix = null;
        this.suffix = null;
        this.pad = ' ';
        this.padMode = null;
    }

    /**
     * 号码类型
     */
    private final String type;

    /**
     * 号码长度
     */
    private final int length;

    /**
     * 前缀
     */
    private final String prefix;

    /**
     * 后缀
     */
    private final String suffix;

    /**
     * 填充字符
     */
    private final char pad;

    /**
     * 填充方式:1-左填充，2-右填充
     */
    private final PadMode padMode;

    /**
     * 一次性获得几个号码：为了减少数据库访问，通过该值一次性获得一个号段（比如1000个号码）
     */
    private final int number;

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public char getPad() {
        return pad;
    }

    public PadMode getPadMode() {
        return padMode;
    }

    public int getNumber() {
        return number;
    }
}
