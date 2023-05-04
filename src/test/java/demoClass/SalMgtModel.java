package demoClass;

import java.util.Date;

/**
 * 销管数据导入时使用的类，相关的信息都存储在这里
 * */
public class SalMgtModel {

    /**
     * 业务数据JSON报文结构
     * */
    //BATCH_ID:批次号
    private String batchId;
    //BATCH_SOURCE:批次数据来源
    private String batchSource;
    //BATCH_START_DATE:批次数据业务日期起期,格式要求：YYYY/MM/DD
    private Date batchStartDate;
    //BATCH_END_DATE:批次数据业务日期止期,格式要求：YYYY/MM/DD
    private Date batchEndDate;
    //BATCH_COUNT:批次数据条数
    private long batchCount;
    //BATCH_FLAG:批次状态.[P]处理中（销管系统默认值）;[S]批次处理成功;[E]批次错误;[C]作废
    private String batchFlag;
    //file:文件绝对路径,包括文件名[/FILE/2022/12/11/fileName.txt]
    private String filePath;
    //name:文件名
    private String fileName;


    public SalMgtModel() {

    }

    public SalMgtModel(String batchId, String batchSource, Date batchStartDate, Date batchEndDate, long batchCount, String batchFlag,
                       String filePath, String fileName) {
        this.batchId = batchId;
        this.batchSource = batchSource;
        this.batchStartDate = batchStartDate;
        this.batchEndDate = batchEndDate;
        this.batchCount = batchCount;
        this.batchFlag = batchFlag;
        this.filePath = filePath;
        this.fileName = fileName;
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

    public long getBatchCount() {
        return batchCount;
    }

    public void setBatchCount(long batchCount) {
        this.batchCount = batchCount;
    }

    public String getBatchFlag() {
        return batchFlag;
    }

    public void setBatchFlag(String batchFlag) {
        this.batchFlag = batchFlag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "SalMgtModel{" +
                "batchId='" + batchId + '\'' +
                ", batchSource='" + batchSource + '\'' +
                ", batchStartDate=" + batchStartDate +
                ", batchEndDate=" + batchEndDate +
                ", batchCount=" + batchCount +
                ", batchFlag='" + batchFlag + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
