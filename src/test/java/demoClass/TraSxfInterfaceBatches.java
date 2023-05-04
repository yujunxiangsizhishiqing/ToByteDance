package demoClass;

import java.util.Date;

public class TraSxfInterfaceBatches {

    //BATCH_ID:批次号,接口批次表主键
    private String batchId;
    //BATCH_SOURCE:CMS-销管系统
    private String batchSource;
    //BATCH_START_DATE:批次数据业务日期起期,格式要求：YYYY/MM/DD
    private Date batchStartDate;
    //BATCH_END_DATE:批次数据业务日期止期,格式要求：YYYY/MM/DD
    private Date batchEndDate;
    //BATCH_FLAG:批次状态.[P]处理中（销管系统默认值）;[S]批次处理成功;[E]批次错误;[C]作废
    private String batchFlag;
    //BATCH_MAKE_DATE:创建日期[YYYY/MM/DD]
    private Date batchMakeDate;
    //BATCH_MAKE_TIME:创建时间[HH24:MI:SS]
    private String batchMakeTime;
    //BATCH_CREAT_USER:创建人
    private String batchCreatUser;
    //BATCH_MODIFY_DATE:最后修改日期[YYYY/MM/DD]
    private Date batchModifyDate;
    //BATCH_MODIFY_TIME:最后修改时间[HH24:MI:SS]
    private String batchModifyTime;
    //BATCH_MODIFY_USER:最后更新人
    private String batchModifyUser;

    public TraSxfInterfaceBatches(){
    }

    public TraSxfInterfaceBatches(String batchId, String batchSource, Date batchStartDate, Date batchEndDate, String batchFlag, Date batchMakeDate, String batchMakeTime, String batchCreatUser, Date batchModifyDate, String batchModifyTime, String batchModifyUser) {
        this.batchId = batchId;
        this.batchSource = batchSource;
        this.batchStartDate = batchStartDate;
        this.batchEndDate = batchEndDate;
        this.batchFlag = batchFlag;
        this.batchMakeDate = batchMakeDate;
        this.batchMakeTime = batchMakeTime;
        this.batchCreatUser = batchCreatUser;
        this.batchModifyDate = batchModifyDate;
        this.batchModifyTime = batchModifyTime;
        this.batchModifyUser = batchModifyUser;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchSource() {
        return batchSource;
    }

    public void setBatchSource(String batchSource) {
        this.batchSource = batchSource;
    }

    public Date getBatchStartDate() {
        return batchStartDate;
    }

    public void setBatchStartDate(Date batchStartDate) {
        this.batchStartDate = batchStartDate;
    }

    public Date getBatchEndDate() {
        return batchEndDate;
    }

    public void setBatchEndDate(Date batchEndDate) {
        this.batchEndDate = batchEndDate;
    }

    public String getBatchFlag() {
        return batchFlag;
    }

    public void setBatchFlag(String batchFlag) {
        this.batchFlag = batchFlag;
    }

    public Date getBatchMakeDate() {
        return batchMakeDate;
    }

    public void setBatchMakeDate(Date batchMakeDate) {
        this.batchMakeDate = batchMakeDate;
    }

    public String getBatchMakeTime() {
        return batchMakeTime;
    }

    public void setBatchMakeTime(String batchMakeTime) {
        this.batchMakeTime = batchMakeTime;
    }

    public String getBatchCreatUser() {
        return batchCreatUser;
    }

    public void setBatchCreatUser(String batchCreatUser) {
        this.batchCreatUser = batchCreatUser;
    }

    public Date getBatchModifyDate() {
        return batchModifyDate;
    }

    public void setBatchModifyDate(Date batchModifyDate) {
        this.batchModifyDate = batchModifyDate;
    }

    public String getBatchModifyTime() {
        return batchModifyTime;
    }

    public void setBatchModifyTime(String batchModifyTime) {
        this.batchModifyTime = batchModifyTime;
    }

    public String getBatchModifyUser() {
        return batchModifyUser;
    }

    public void setBatchModifyUser(String batchModifyUser) {
        this.batchModifyUser = batchModifyUser;
    }

    @Override
    public String toString() {
        return "TraSxfInterfaceBatches{" +
                "batchId='" + batchId + '\'' +
                ", batchSource='" + batchSource + '\'' +
                ", batchStartDate=" + batchStartDate +
                ", batchEndDate=" + batchEndDate +
                ", batchFlag='" + batchFlag + '\'' +
                ", batchMakeDate=" + batchMakeDate +
                ", batchMakeTime='" + batchMakeTime + '\'' +
                ", batchCreatUser='" + batchCreatUser + '\'' +
                ", batchModifyDate=" + batchModifyDate +
                ", batchModifyTime='" + batchModifyTime + '\'' +
                ", batchModifyUser='" + batchModifyUser + '\'' +
                '}';
    }
}
