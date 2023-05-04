package demoClass;

import java.util.Date;

public class LagxjdbankCompact {
    private static final long serialVersionUID = -53049758796808708L;
    /**
     * 合同编码
     */
    private String compactcode;
    /**
     * 合同名称
     */
    private String compactname;
    /**
     * 中介机构级别 lacom表关联中介机构级别 01-总公司；02-分公司
     */
    private String banklevel;
    /**
     * 中介机构编码 lacom表关联 lDcomF表关联
     */
    private String agentcom;
    /**
     * 展业类型 固定为“7” 固定为“6”
     */
    private String branchtype;
    /**
     * 渠道
     */
    private String branchtype2;
    /**
     * 合同有效起期
     */
    private Date startdate;
    /**
     * 合同有效止期
     */
    private Date enddate;
    /**
     * 合同中止日期
     */
    private Date suspenddate;
    /**
     * 所属管理机构
     */
    private String managecom;
    /**
     * 合同性质 合同性质：1-主协议；2-产品补充协议；3-推动协议3-手续费补充协议；4-其他
     */
    private String compactnature;
    /**
     * 合同审核状态
     */
    private String reviewstatus;
    /**
     * 公司性质
     */
    private String v1;
    /**
     * 公司性质
     */
    private String v2;
    /**
     * 公司性质
     */
    private String v3;
    /**
     * 公司性质
     */
    private String v4;
    /**
     * 公司性质
     */
    private String v5;
    /**
     * 是否限制固定期间回执回访 Y-是；N-否
     */
    private String v6;
    /**
     * 入机时间
     */
    private Date d1;
    /**
     * 入机时间
     */
    private Date d2;
    /**
     * 入机时间
     */
    private Date d3;
    /**
     * 入机时间
     */
    private Date d4;
    /**
     * 入机时间
     */
    private Date d5;
    /**
     * 入机时间
     */
    private Date d6;
    /**
     * 是否代收保费
     */
    private Integer i1;
    /**
     * 出单性质(0-线上/1-线下)
     */
    private Integer i2;
    /**
     * 备注
     */
    private Integer i3;
    /**
     * 审核状态(0-待审核，1-审核通过，2-审核不通过)
     */
    private Integer i4;
    /**
     * 是否通过审核
     */
    private Integer i5;
    /**
     * 修改日期
     */
    private Integer i6;
    /**
     * 修改时间
     */
    private Double dc1;
    /**
     * source
     */
    private Double dc2;
    /**
     * 处理渠道信息contractNumber
     */
    private Double dc3;
    /**
     * 中介机构证件类型 1-统一社会信用代码，2-组织机构代码证，3-税务登记证，4-营业执照，5-事业单位法人证书，6-社会团体法人证书，7-民办非企业单位登记证书，8-基金会法人登记证书，9-工商注册号码，10-其他证件。
     */
    private Double dc4;
    /**
     * 中介业务许可证号（经营许可证号码）
     */
    private Double dc5;
    /**
     * 中介业务许可证名称(存放编码) 保险兼业代理业务许可证
     */
    private Double dc6;
    /**
     * 入机日期
     */
    private Date makedate;
    /**
     * 入机时间
     */
    private String maketime;
    /**
     * 上一次修改日期
     */
    private Date modifydate;
    /**
     * 上一次修改时间
     */
    private String modifytime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 上一次修改时间
     */
    private String noti;

    public LagxjdbankCompact(){

    }

    public LagxjdbankCompact(String compactcode, String compactname, String banklevel, String agentcom, String branchtype, String branchtype2,
                             Date startdate, Date enddate, Date suspenddate, String managecom, String compactnature, String reviewstatus,
                             String v1, String v2, String v3, String v4, String v5, String v6, Date d1, Date d2, Date d3, Date d4, Date d5,
                             Date d6, Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Double dc1, Double dc2, Double dc3,
                             Double dc4, Double dc5, Double dc6, Date makedate, String maketime, Date modifydate, String modifytime,
                             String operator, String noti) {
        this.compactcode = compactcode;
        this.compactname = compactname;
        this.banklevel = banklevel;
        this.agentcom = agentcom;
        this.branchtype = branchtype;
        this.branchtype2 = branchtype2;
        this.startdate = startdate;
        this.enddate = enddate;
        this.suspenddate = suspenddate;
        this.managecom = managecom;
        this.compactnature = compactnature;
        this.reviewstatus = reviewstatus;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.d5 = d5;
        this.d6 = d6;
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
        this.i5 = i5;
        this.i6 = i6;
        this.dc1 = dc1;
        this.dc2 = dc2;
        this.dc3 = dc3;
        this.dc4 = dc4;
        this.dc5 = dc5;
        this.dc6 = dc6;
        this.makedate = makedate;
        this.maketime = maketime;
        this.modifydate = modifydate;
        this.modifytime = modifytime;
        this.operator = operator;
        this.noti = noti;
    }

    public String getCompactcode() {
        return compactcode;
    }

    public void setCompactcode(String compactcode) {
        this.compactcode = compactcode;
    }

    public String getCompactname() {
        return compactname;
    }

    public void setCompactname(String compactname) {
        this.compactname = compactname;
    }

    public String getBanklevel() {
        return banklevel;
    }

    public void setBanklevel(String banklevel) {
        this.banklevel = banklevel;
    }

    public String getAgentcom() {
        return agentcom;
    }

    public void setAgentcom(String agentcom) {
        this.agentcom = agentcom;
    }

    public String getBranchtype() {
        return branchtype;
    }

    public void setBranchtype(String branchtype) {
        this.branchtype = branchtype;
    }

    public String getBranchtype2() {
        return branchtype2;
    }

    public void setBranchtype2(String branchtype2) {
        this.branchtype2 = branchtype2;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getSuspenddate() {
        return suspenddate;
    }

    public void setSuspenddate(Date suspenddate) {
        this.suspenddate = suspenddate;
    }

    public String getManagecom() {
        return managecom;
    }

    public void setManagecom(String managecom) {
        this.managecom = managecom;
    }

    public String getCompactnature() {
        return compactnature;
    }

    public void setCompactnature(String compactnature) {
        this.compactnature = compactnature;
    }

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getV4() {
        return v4;
    }

    public void setV4(String v4) {
        this.v4 = v4;
    }

    public String getV5() {
        return v5;
    }

    public void setV5(String v5) {
        this.v5 = v5;
    }

    public String getV6() {
        return v6;
    }

    public void setV6(String v6) {
        this.v6 = v6;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    public Date getD3() {
        return d3;
    }

    public void setD3(Date d3) {
        this.d3 = d3;
    }

    public Date getD4() {
        return d4;
    }

    public void setD4(Date d4) {
        this.d4 = d4;
    }

    public Date getD5() {
        return d5;
    }

    public void setD5(Date d5) {
        this.d5 = d5;
    }

    public Date getD6() {
        return d6;
    }

    public void setD6(Date d6) {
        this.d6 = d6;
    }

    public Integer getI1() {
        return i1;
    }

    public void setI1(Integer i1) {
        this.i1 = i1;
    }

    public Integer getI2() {
        return i2;
    }

    public void setI2(Integer i2) {
        this.i2 = i2;
    }

    public Integer getI3() {
        return i3;
    }

    public void setI3(Integer i3) {
        this.i3 = i3;
    }

    public Integer getI4() {
        return i4;
    }

    public void setI4(Integer i4) {
        this.i4 = i4;
    }

    public Integer getI5() {
        return i5;
    }

    public void setI5(Integer i5) {
        this.i5 = i5;
    }

    public Integer getI6() {
        return i6;
    }

    public void setI6(Integer i6) {
        this.i6 = i6;
    }

    public Double getDc1() {
        return dc1;
    }

    public void setDc1(Double dc1) {
        this.dc1 = dc1;
    }

    public Double getDc2() {
        return dc2;
    }

    public void setDc2(Double dc2) {
        this.dc2 = dc2;
    }

    public Double getDc3() {
        return dc3;
    }

    public void setDc3(Double dc3) {
        this.dc3 = dc3;
    }

    public Double getDc4() {
        return dc4;
    }

    public void setDc4(Double dc4) {
        this.dc4 = dc4;
    }

    public Double getDc5() {
        return dc5;
    }

    public void setDc5(Double dc5) {
        this.dc5 = dc5;
    }

    public Double getDc6() {
        return dc6;
    }

    public void setDc6(Double dc6) {
        this.dc6 = dc6;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public String getMaketime() {
        return maketime;
    }

    public void setMaketime(String maketime) {
        this.maketime = maketime;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    @Override
    public String toString() {
        return "LagxjdbankCompact{" +
                "compactcode='" + compactcode + '\'' +
                ", compactname='" + compactname + '\'' +
                ", banklevel='" + banklevel + '\'' +
                ", agentcom='" + agentcom + '\'' +
                ", branchtype='" + branchtype + '\'' +
                ", branchtype2='" + branchtype2 + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", suspenddate=" + suspenddate +
                ", managecom='" + managecom + '\'' +
                ", compactnature='" + compactnature + '\'' +
                ", reviewstatus='" + reviewstatus + '\'' +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", v3='" + v3 + '\'' +
                ", v4='" + v4 + '\'' +
                ", v5='" + v5 + '\'' +
                ", v6='" + v6 + '\'' +
                ", d1=" + d1 +
                ", d2=" + d2 +
                ", d3=" + d3 +
                ", d4=" + d4 +
                ", d5=" + d5 +
                ", d6=" + d6 +
                ", i1=" + i1 +
                ", i2=" + i2 +
                ", i3=" + i3 +
                ", i4=" + i4 +
                ", i5=" + i5 +
                ", i6=" + i6 +
                ", dc1=" + dc1 +
                ", dc2=" + dc2 +
                ", dc3=" + dc3 +
                ", dc4=" + dc4 +
                ", dc5=" + dc5 +
                ", dc6=" + dc6 +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", modifydate=" + modifydate +
                ", modifytime='" + modifytime + '\'' +
                ", operator='" + operator + '\'' +
                ", noti='" + noti + '\'' +
                '}';
    }
}
