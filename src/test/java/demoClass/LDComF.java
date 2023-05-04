package demoClass;

import java.util.Date;

public class LDComF {
    private static final long serialVersionUID = -69364655018182026L;
    /**
     * 管理机构
     */
    private String comcode;
    /**
     * 还原编码
     */
    private String outcomcode;
    /**
     * 直销类营业部还原 机构代码
     */
    private String zoutcomcode;
    /**
     * 还原渠道类型 渠道类型（电商6）
     */
    private String branchtype;
    /**
     * T0编码
     */
    private String t0code;
    /**
     * T2编码
     */
    private String t2code;
    /**
     * 备用编码
     */
    private String t1code;
    /**
     * 机构级别
     */
    private String comgrade;
    /**
     * 还原顺序号
     */
    private String organno;
    /**
     * 备用1 机构名称
     */
    private String v1;
    /**
     * 备用2 资格证类型 渠道信息
     */
    private String v2;
    /**
     * 备用3
     */
    private String v3;
    /**
     * 备用4 真实的停业状态
     */
    private String v4;
    /**
     * 备用5
     */
    private Date d5;
    /**
     * 备用6 备注状态（1-新增  2-修改  3-审核）
     */
    private String v6;
    /**
     * 操作员代码
     */
    private String operator;
    /**
     * 入机日期
     */
    private Date makedate;
    /**
     * 入机时间
     */
    private String maketime;
    /**
     * 公司性质
     */
    private String zipcode;
    /**
     * 成立日期
     */
    private String founddate;
    /**
     * 电话
     */
    private String phone;
    /**
     * 停业标志
     */
    private String state;
    /**
     * 停业日期
     */
    private String enddate;
    /**
     * 地址
     */
    private String address;
    /**
     * 是否代收保费
     */
    private String fax;
    /**
     * 出单性质(0-线上/1-线下)
     */
    private String webaddresss;
    /**
     * 备注
     */
    private String noti;
    /**
     * 审核状态(0-待审核，1-审核通过，2-审核不通过)
     */
    private String calflag;
    /**
     * 是否通过审核
     */
    private String insureid;
    /**
     * 修改日期
     */
    private Date modifydate;
    /**
     * 修改时间
     */
    private String modifytime;
    /**
     * GrpNature
     * */
    private String grpNature;
    /**
     * source
     */
    private String businesscode;
    /**
     * 处理渠道信息contractNumber
     */
    private String socialcode;
    /**
     * 中介机构证件类型 1-统一社会信用代码，2-组织机构代码证，3-税务登记证，4-营业执照，5-事业单位法人证书，6-社会团体法人证书，7-民办非企业单位登记证书，8-基金会法人登记证书，9-工商注册号码，10-其他证件。
     */
    private String socialtype;
    /**
     * 中介业务许可证号（经营许可证号码）
     */
    private String businesslicensenumber;
    /**
     * 中介业务许可证名称(存放编码) 保险兼业代理业务许可证
     */
    private String businesslicensenamecode;
    /**
     * 协议到期日期 银保私行
     */
    private String expirydate;
    /**
     * 签约日期 银保私行
     */
    private String contractdate;
    /**
     * 业务范围
     */
    private String businessscope;
    /**
     * 经营区域
     */
    private String businessarea;
    /**
     * 负责人姓名
     */
    private String managername;
    /**
     * 违法违规记录
     */
    private String illegalrecord;

    public LDComF(){

    }

    public LDComF(String comcode, String outcomcode, String zoutcomcode, String branchtype, String t0code, String t2code, String t1code,
                  String comgrade, String organno, String v1, String v2, String v3, String v4, Date d5, String v6, String operator,
                  Date makedate, String maketime, String zipcode, String founddate, String phone, String state, String enddate,
                  String address, String fax, String webaddresss, String noti, String calflag, String insureid, Date modifydate,
                  String modifytime, String grpNature, String businesscode, String socialcode, String socialtype, String businesslicensenumber,
                  String businesslicensenamecode, String expirydate, String contractdate, String businessscope, String businessarea,
                  String managername, String illegalrecord) {
        this.comcode = comcode;
        this.outcomcode = outcomcode;
        this.zoutcomcode = zoutcomcode;
        this.branchtype = branchtype;
        this.t0code = t0code;
        this.t2code = t2code;
        this.t1code = t1code;
        this.comgrade = comgrade;
        this.organno = organno;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.d5 = d5;
        this.v6 = v6;
        this.operator = operator;
        this.makedate = makedate;
        this.maketime = maketime;
        this.zipcode = zipcode;
        this.founddate = founddate;
        this.phone = phone;
        this.state = state;
        this.enddate = enddate;
        this.address = address;
        this.fax = fax;
        this.webaddresss = webaddresss;
        this.noti = noti;
        this.calflag = calflag;
        this.insureid = insureid;
        this.modifydate = modifydate;
        this.modifytime = modifytime;
        this.grpNature = grpNature;
        this.businesscode = businesscode;
        this.socialcode = socialcode;
        this.socialtype = socialtype;
        this.businesslicensenumber = businesslicensenumber;
        this.businesslicensenamecode = businesslicensenamecode;
        this.expirydate = expirydate;
        this.contractdate = contractdate;
        this.businessscope = businessscope;
        this.businessarea = businessarea;
        this.managername = managername;
        this.illegalrecord = illegalrecord;
    }

    public String getComcode() {
        return comcode;
    }

    public void setComcode(String comcode) {
        this.comcode = comcode;
    }

    public String getOutcomcode() {
        return outcomcode;
    }

    public void setOutcomcode(String outcomcode) {
        this.outcomcode = outcomcode;
    }

    public String getZoutcomcode() {
        return zoutcomcode;
    }

    public void setZoutcomcode(String zoutcomcode) {
        this.zoutcomcode = zoutcomcode;
    }

    public String getBranchtype() {
        return branchtype;
    }

    public void setBranchtype(String branchtype) {
        this.branchtype = branchtype;
    }

    public String getT0code() {
        return t0code;
    }

    public void setT0code(String t0code) {
        this.t0code = t0code;
    }

    public String getT2code() {
        return t2code;
    }

    public void setT2code(String t2code) {
        this.t2code = t2code;
    }

    public String getT1code() {
        return t1code;
    }

    public void setT1code(String t1code) {
        this.t1code = t1code;
    }

    public String getComgrade() {
        return comgrade;
    }

    public void setComgrade(String comgrade) {
        this.comgrade = comgrade;
    }

    public String getOrganno() {
        return organno;
    }

    public void setOrganno(String organno) {
        this.organno = organno;
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

    public Date getD5() {
        return d5;
    }

    public void setD5(Date d5) {
        this.d5 = d5;
    }

    public String getV6() {
        return v6;
    }

    public void setV6(String v6) {
        this.v6 = v6;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getFounddate() {
        return founddate;
    }

    public void setFounddate(String founddate) {
        this.founddate = founddate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebaddresss() {
        return webaddresss;
    }

    public void setWebaddresss(String webaddresss) {
        this.webaddresss = webaddresss;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    public String getCalflag() {
        return calflag;
    }

    public void setCalflag(String calflag) {
        this.calflag = calflag;
    }

    public String getInsureid() {
        return insureid;
    }

    public void setInsureid(String insureid) {
        this.insureid = insureid;
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

    public String getGrpNature() {
        return grpNature;
    }

    public void setGrpNature(String grpNature) {
        this.grpNature = grpNature;
    }

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    public String getSocialcode() {
        return socialcode;
    }

    public void setSocialcode(String socialcode) {
        this.socialcode = socialcode;
    }

    public String getSocialtype() {
        return socialtype;
    }

    public void setSocialtype(String socialtype) {
        this.socialtype = socialtype;
    }

    public String getBusinesslicensenumber() {
        return businesslicensenumber;
    }

    public void setBusinesslicensenumber(String businesslicensenumber) {
        this.businesslicensenumber = businesslicensenumber;
    }

    public String getBusinesslicensenamecode() {
        return businesslicensenamecode;
    }

    public void setBusinesslicensenamecode(String businesslicensenamecode) {
        this.businesslicensenamecode = businesslicensenamecode;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getContractdate() {
        return contractdate;
    }

    public void setContractdate(String contractdate) {
        this.contractdate = contractdate;
    }

    public String getBusinessscope() {
        return businessscope;
    }

    public void setBusinessscope(String businessscope) {
        this.businessscope = businessscope;
    }

    public String getBusinessarea() {
        return businessarea;
    }

    public void setBusinessarea(String businessarea) {
        this.businessarea = businessarea;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getIllegalrecord() {
        return illegalrecord;
    }

    public void setIllegalrecord(String illegalrecord) {
        this.illegalrecord = illegalrecord;
    }

    @Override
    public String toString() {
        return "LDComF{" +
                "comcode='" + comcode + '\'' +
                ", outcomcode='" + outcomcode + '\'' +
                ", zoutcomcode='" + zoutcomcode + '\'' +
                ", branchtype='" + branchtype + '\'' +
                ", t0code='" + t0code + '\'' +
                ", t2code='" + t2code + '\'' +
                ", t1code='" + t1code + '\'' +
                ", comgrade='" + comgrade + '\'' +
                ", organno='" + organno + '\'' +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                ", v3='" + v3 + '\'' +
                ", v4='" + v4 + '\'' +
                ", d5=" + d5 +
                ", v6='" + v6 + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", founddate='" + founddate + '\'' +
                ", phone='" + phone + '\'' +
                ", state='" + state + '\'' +
                ", enddate='" + enddate + '\'' +
                ", address='" + address + '\'' +
                ", fax='" + fax + '\'' +
                ", webaddresss='" + webaddresss + '\'' +
                ", noti='" + noti + '\'' +
                ", calflag='" + calflag + '\'' +
                ", insureid='" + insureid + '\'' +
                ", modifydate=" + modifydate +
                ", modifytime='" + modifytime + '\'' +
                ", grpNature='" + grpNature + '\'' +
                ", businesscode='" + businesscode + '\'' +
                ", socialcode='" + socialcode + '\'' +
                ", socialtype='" + socialtype + '\'' +
                ", businesslicensenumber='" + businesslicensenumber + '\'' +
                ", businesslicensenamecode='" + businesslicensenamecode + '\'' +
                ", expirydate='" + expirydate + '\'' +
                ", contractdate='" + contractdate + '\'' +
                ", businessscope='" + businessscope + '\'' +
                ", businessarea='" + businessarea + '\'' +
                ", managername='" + managername + '\'' +
                ", illegalrecord='" + illegalrecord + '\'' +
                '}';
    }
}
