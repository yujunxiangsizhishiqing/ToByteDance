package demoClass;

import java.util.Date;

public class Lacom {
    private static final long serialVersionUID = -29883391297823401L;
    /**
     * 代理机构
     */
    private String agentcom;
    /**
     * 管理机构
     */
    private String managecom;
    /**
     * 上级管理机构
     */
    private String upManageCom;
    /**
     * 基本法版本
     */
    private String areatype;
    /**
     * 渠道类型
     */
    private String channeltype;
    /**
     * 上级代理机构
     */
    private String upagentcom;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构注册地址
     */
    private String address;
    /**
     * 机构邮编
     */
    private String zipcode;
    /**
     * 机构电话
     */
    private String phone;
    /**
     * 机构传真
     */
    private String fax;
    /**
     * EMail
     */
    private String email;
    /**
     * 网址\监管机构名称
     */
    private String webaddress;
    /**
     * 负责人
     */
    private String linkman;
    /**
     * 密码
     */
    private String password;
    /**
     * 法人
     */
    private String corporation;
    /**
     * 银行编码
     */
    private String bankcode;
    /**
     * 银行帐号
     */
    private String bankaccno;
    /**
     * 行业分类
     */
    private String businesstype;
    /**
     * 单位性质
     */
    private String grpnature;
    /**
     * 中介机构类别
     */
    private String actype;
    /**
     * 销售资格
     */
    private String sellflag;
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
     * 最后一次修改日期
     */
    private Date modifydate;
    /**
     * 最后一次修改时间
     */
    private String modifytime;
    /**
     * 银行级别
     */
    private String banktype;
    /**
     * 是否统计网点合格率
     */
    private String calflag;
    /**
     * 工商执照编码
     */
    private String busilicensecode;
    /**
     * 保险公司ID
     */
    private String insureid;
    /**
     * 保险公司负责人
     */
    private String insureprincipal;
    /**
     * 主营业务
     */
    private String chiefbusiness;
    /**
     * 营业地址
     */
    private String busiaddress;
    /**
     * 签署人
     */
    private String subscribeman;
    /**
     * 签署人职务
     */
    private String subscribemanduty;
    /**
     * 许可证号码(证书编号)
     */
    private String licenseno;
    /**
     * 行政区划代码
     */
    private String regionalismcode;
    /**
     * 上报代码
     */
    private String appagentcom;
    /**
     * 机构状态
     */
    private String state;
    /**
     * 相关说明
     */
    private String noti;
    /**
     * 行业代码
     */
    private String businesscode;
    /**
     * 许可证登记日期(资格证发放日期)
     */
    private Date licensestartdate;
    /**
     * 许可证截至日期(资格证失效日期)
     */
    private Date licenseenddate;
    /**
     * 展业类型
     */
    private String branchtype;
    /**
     * 渠道
     */
    private String branchtype2;
    /**
     * 资产
     */
    private Double assets;
    /**
     * 营业收入
     */
    private Double income;
    /**
     * 营业利润
     */
    private Double profits;
    /**
     * 机构人数
     */
    private Long personnalsum;
    /**
     * 合同编码
     */
    private String protocalno;
    /**
     * 所属总行
     */
    private String headoffice;
    /**
     * 成立日期
     */
    private Date founddate;
    /**
     * 停业日期
     */
    private Date enddate;
    /**
     * 投连销售资格
     */
    private String touliansellflag;
    /**
     * 许可证属性
     */
    private String licenseowner;
    /**
     * 批量操作标识
     */
    private String batchflag;
    /**
     * 批量操作标识添加日期
     */
    private Date batchmakedate;
    /**
     * 批量操作标识添加时间
     */
    private String batchmaketime;
    /**
     * 统一社会信用代码（中介机构证件号码）
     */
    private String socialcode;
    /**
     * 中介机构证件类型
     * 1-统一社会信用代码，
     * 2-组织机构代码证，
     * 3-税务登记证，
     * 4-营业执照，
     * 5-事业单位法人证书，
     * 6-社会团体法人证书，
     * 7-民办非企业单位登记证书，
     * 8-基金会法人登记证书，
     * 9-工商注册号码，
     * 10-其他证件。
     */
    private String socialtype;
    /**
     * 协议到期日期
     * 银保私行
     */
    private String expirydate;
    /**
     * 签约日期银保私行
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
    /**
     * 中介业务许可证号
     */
    private String businesslicensenumber;
    /**
     * 中介业务许可证名称(存放编码)
     * <p>
     * 保险兼业代理业务许可证
     */
    private String businesslicensenamecode;

    /**
     * 非实体字段，暂时这样写，后续敲定具体字段再做映射
     */
    private String channel;//合作渠道名称
    private String csName;//长生分公司名称
    private String productCode;//产品编码
    private String productName;//产品名称
    private String date;//缴费期限
    private String proportion;//协议汇总比例


    private String channelCooperation;//合作渠道名称
    private String agencyCompanyName;//合作渠道总/分公司机构
    private String zCompany;//总公司-协议汇总比例
    private String shCompany;//上海分公司-协议汇总比例
    private String zjCompany;//浙江分公司-协议汇总比例
    private String jsCompany;//江苏分公司-协议汇总比例
    private String bjCompany;//北京分公司-协议汇总比例
    private String scCompany;//四川分公司-协议汇总比例
    private String sdCompany;//山东分公司-协议汇总比例
    private String hnCompany;//河南分公司-协议汇总比例

    public String getParentcompanyname() {
        return parentcompanyname;
    }

    public void setParentcompanyname(String parentcompanyname) {
        this.parentcompanyname = parentcompanyname;
    }

    private String parentcompanyname; //分公司对应的总公司名字

    public Lacom(){

    }

    public Lacom(String agentcom, String managecom, String upManageCom, String areatype, String channeltype, String upagentcom, String name, String address, String zipcode, String phone, String fax, String email, String webaddress, String linkman, String password, String corporation, String bankcode, String bankaccno, String businesstype, String grpnature, String actype, String sellflag, String operator, Date makedate, String maketime, Date modifydate, String modifytime, String banktype, String calflag, String busilicensecode, String insureid, String insureprincipal, String chiefbusiness, String busiaddress, String subscribeman, String subscribemanduty, String licenseno, String regionalismcode, String appagentcom, String state, String noti, String businesscode, Date licensestartdate, Date licenseenddate, String branchtype, String branchtype2, Double assets, Double income, Double profits, Long personnalsum, String protocalno, String headoffice, Date founddate, Date enddate, String touliansellflag, String licenseowner, String batchflag, Date batchmakedate, String batchmaketime, String socialcode, String socialtype, String expirydate, String contractdate, String businessscope, String businessarea, String managername, String illegalrecord, String businesslicensenumber, String businesslicensenamecode, String channel, String csName, String productCode, String productName, String date, String proportion, String channelCooperation, String agencyCompanyName, String zCompany, String shCompany, String zjCompany, String jsCompany, String bjCompany, String scCompany, String sdCompany, String hnCompany, String parentcompanyname) {
        this.agentcom = agentcom;
        this.managecom = managecom;
        this.upManageCom = upManageCom;
        this.areatype = areatype;
        this.channeltype = channeltype;
        this.upagentcom = upagentcom;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.webaddress = webaddress;
        this.linkman = linkman;
        this.password = password;
        this.corporation = corporation;
        this.bankcode = bankcode;
        this.bankaccno = bankaccno;
        this.businesstype = businesstype;
        this.grpnature = grpnature;
        this.actype = actype;
        this.sellflag = sellflag;
        this.operator = operator;
        this.makedate = makedate;
        this.maketime = maketime;
        this.modifydate = modifydate;
        this.modifytime = modifytime;
        this.banktype = banktype;
        this.calflag = calflag;
        this.busilicensecode = busilicensecode;
        this.insureid = insureid;
        this.insureprincipal = insureprincipal;
        this.chiefbusiness = chiefbusiness;
        this.busiaddress = busiaddress;
        this.subscribeman = subscribeman;
        this.subscribemanduty = subscribemanduty;
        this.licenseno = licenseno;
        this.regionalismcode = regionalismcode;
        this.appagentcom = appagentcom;
        this.state = state;
        this.noti = noti;
        this.businesscode = businesscode;
        this.licensestartdate = licensestartdate;
        this.licenseenddate = licenseenddate;
        this.branchtype = branchtype;
        this.branchtype2 = branchtype2;
        this.assets = assets;
        this.income = income;
        this.profits = profits;
        this.personnalsum = personnalsum;
        this.protocalno = protocalno;
        this.headoffice = headoffice;
        this.founddate = founddate;
        this.enddate = enddate;
        this.touliansellflag = touliansellflag;
        this.licenseowner = licenseowner;
        this.batchflag = batchflag;
        this.batchmakedate = batchmakedate;
        this.batchmaketime = batchmaketime;
        this.socialcode = socialcode;
        this.socialtype = socialtype;
        this.expirydate = expirydate;
        this.contractdate = contractdate;
        this.businessscope = businessscope;
        this.businessarea = businessarea;
        this.managername = managername;
        this.illegalrecord = illegalrecord;
        this.businesslicensenumber = businesslicensenumber;
        this.businesslicensenamecode = businesslicensenamecode;
        this.channel = channel;
        this.csName = csName;
        this.productCode = productCode;
        this.productName = productName;
        this.date = date;
        this.proportion = proportion;
        this.channelCooperation = channelCooperation;
        this.agencyCompanyName = agencyCompanyName;
        this.zCompany = zCompany;
        this.shCompany = shCompany;
        this.zjCompany = zjCompany;
        this.jsCompany = jsCompany;
        this.bjCompany = bjCompany;
        this.scCompany = scCompany;
        this.sdCompany = sdCompany;
        this.hnCompany = hnCompany;
        this.parentcompanyname = parentcompanyname;
    }

    public String getUpManageCom() {
        return upManageCom;
    }

    public void setUpManageCom(String upManageCom) {
        this.upManageCom = upManageCom;
    }

    public String getBusinesslicensenumber() {
        return businesslicensenumber;
    }

    public void setBusinesslicensenumber(String businesslicensenumber) {
        this.businesslicensenumber = businesslicensenumber;
    }

    public String getAgentcom() {
        return agentcom;
    }

    public void setAgentcom(String agentcom) {
        this.agentcom = agentcom;
    }

    public String getManagecom() {
        return managecom;
    }

    public void setManagecom(String managecom) {
        this.managecom = managecom;
    }

    public String getAreatype() {
        return areatype;
    }

    public void setAreatype(String areatype) {
        this.areatype = areatype;
    }

    public String getChanneltype() {
        return channeltype;
    }

    public void setChanneltype(String channeltype) {
        this.channeltype = channeltype;
    }

    public String getUpagentcom() {
        return upagentcom;
    }

    public void setUpagentcom(String upagentcom) {
        this.upagentcom = upagentcom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebaddress() {
        return webaddress;
    }

    public void setWebaddress(String webaddress) {
        this.webaddress = webaddress;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankaccno() {
        return bankaccno;
    }

    public void setBankaccno(String bankaccno) {
        this.bankaccno = bankaccno;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getGrpnature() {
        return grpnature;
    }

    public void setGrpnature(String grpnature) {
        this.grpnature = grpnature;
    }

    public String getActype() {
        return actype;
    }

    public void setActype(String actype) {
        this.actype = actype;
    }

    public String getSellflag() {
        return sellflag;
    }

    public void setSellflag(String sellflag) {
        this.sellflag = sellflag;
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

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
    }

    public String getCalflag() {
        return calflag;
    }

    public void setCalflag(String calflag) {
        this.calflag = calflag;
    }

    public String getBusilicensecode() {
        return busilicensecode;
    }

    public void setBusilicensecode(String busilicensecode) {
        this.busilicensecode = busilicensecode;
    }

    public String getInsureid() {
        return insureid;
    }

    public void setInsureid(String insureid) {
        this.insureid = insureid;
    }

    public String getInsureprincipal() {
        return insureprincipal;
    }

    public void setInsureprincipal(String insureprincipal) {
        this.insureprincipal = insureprincipal;
    }

    public String getChiefbusiness() {
        return chiefbusiness;
    }

    public void setChiefbusiness(String chiefbusiness) {
        this.chiefbusiness = chiefbusiness;
    }

    public String getBusiaddress() {
        return busiaddress;
    }

    public void setBusiaddress(String busiaddress) {
        this.busiaddress = busiaddress;
    }

    public String getSubscribeman() {
        return subscribeman;
    }

    public void setSubscribeman(String subscribeman) {
        this.subscribeman = subscribeman;
    }

    public String getSubscribemanduty() {
        return subscribemanduty;
    }

    public void setSubscribemanduty(String subscribemanduty) {
        this.subscribemanduty = subscribemanduty;
    }

    public String getLicenseno() {
        return licenseno;
    }

    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }

    public String getRegionalismcode() {
        return regionalismcode;
    }

    public void setRegionalismcode(String regionalismcode) {
        this.regionalismcode = regionalismcode;
    }

    public String getAppagentcom() {
        return appagentcom;
    }

    public void setAppagentcom(String appagentcom) {
        this.appagentcom = appagentcom;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    public Date getLicensestartdate() {
        return licensestartdate;
    }

    public void setLicensestartdate(Date licensestartdate) {
        this.licensestartdate = licensestartdate;
    }

    public Date getLicenseenddate() {
        return licenseenddate;
    }

    public void setLicenseenddate(Date licenseenddate) {
        this.licenseenddate = licenseenddate;
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

    public Double getAssets() {
        return assets;
    }

    public void setAssets(Double assets) {
        this.assets = assets;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getProfits() {
        return profits;
    }

    public void setProfits(Double profits) {
        this.profits = profits;
    }

    public Long getPersonnalsum() {
        return personnalsum;
    }

    public void setPersonnalsum(Long personnalsum) {
        this.personnalsum = personnalsum;
    }

    public String getProtocalno() {
        return protocalno;
    }

    public void setProtocalno(String protocalno) {
        this.protocalno = protocalno;
    }

    public String getHeadoffice() {
        return headoffice;
    }

    public void setHeadoffice(String headoffice) {
        this.headoffice = headoffice;
    }

    public Date getFounddate() {
        return founddate;
    }

    public void setFounddate(Date founddate) {
        this.founddate = founddate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getTouliansellflag() {
        return touliansellflag;
    }

    public void setTouliansellflag(String touliansellflag) {
        this.touliansellflag = touliansellflag;
    }

    public String getLicenseowner() {
        return licenseowner;
    }

    public void setLicenseowner(String licenseowner) {
        this.licenseowner = licenseowner;
    }

    public String getBatchflag() {
        return batchflag;
    }

    public void setBatchflag(String batchflag) {
        this.batchflag = batchflag;
    }

    public Date getBatchmakedate() {
        return batchmakedate;
    }

    public void setBatchmakedate(Date batchmakedate) {
        this.batchmakedate = batchmakedate;
    }

    public String getBatchmaketime() {
        return batchmaketime;
    }

    public void setBatchmaketime(String batchmaketime) {
        this.batchmaketime = batchmaketime;
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

    public String getBusunesslicensenumber() {
        return businesslicensenumber;
    }

    public void setBusunesslicensenumber(String busunesslicensenumber) {
        this.businesslicensenumber = busunesslicensenumber;
    }

    public String getBusinesslicensenamecode() {
        return businesslicensenamecode;
    }

    public void setBusinesslicensenamecode(String businesslicensenamecode) {
        this.businesslicensenamecode = businesslicensenamecode;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getChannelCooperation() {
        return channelCooperation;
    }

    public void setChannelCooperation(String channelCooperation) {
        this.channelCooperation = channelCooperation;
    }

    public String getAgencyCompanyName() {
        return agencyCompanyName;
    }

    public void setAgencyCompanyName(String agencyCompanyName) {
        this.agencyCompanyName = agencyCompanyName;
    }

    public String getzCompany() {
        return zCompany;
    }

    public void setzCompany(String zCompany) {
        this.zCompany = zCompany;
    }

    public String getShCompany() {
        return shCompany;
    }

    public void setShCompany(String shCompany) {
        this.shCompany = shCompany;
    }

    public String getZjCompany() {
        return zjCompany;
    }

    public void setZjCompany(String zjCompany) {
        this.zjCompany = zjCompany;
    }

    public String getJsCompany() {
        return jsCompany;
    }

    public void setJsCompany(String jsCompany) {
        this.jsCompany = jsCompany;
    }

    public String getBjCompany() {
        return bjCompany;
    }

    public void setBjCompany(String bjCompany) {
        this.bjCompany = bjCompany;
    }

    public String getScCompany() {
        return scCompany;
    }

    public void setScCompany(String scCompany) {
        this.scCompany = scCompany;
    }

    public String getSdCompany() {
        return sdCompany;
    }

    public void setSdCompany(String sdCompany) {
        this.sdCompany = sdCompany;
    }

    public String getHnCompany() {
        return hnCompany;
    }

    public void setHnCompany(String hnCompany) {
        this.hnCompany = hnCompany;
    }

    @Override
    public String toString() {
        return "Lacom{" +
                "agentcom='" + agentcom + '\'' +
                ", managecom='" + managecom + '\'' +
                ", upManageCom='" + upManageCom + '\'' +
                ", areatype='" + areatype + '\'' +
                ", channeltype='" + channeltype + '\'' +
                ", upagentcom='" + upagentcom + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", webaddress='" + webaddress + '\'' +
                ", linkman='" + linkman + '\'' +
                ", password='" + password + '\'' +
                ", corporation='" + corporation + '\'' +
                ", bankcode='" + bankcode + '\'' +
                ", bankaccno='" + bankaccno + '\'' +
                ", businesstype='" + businesstype + '\'' +
                ", grpnature='" + grpnature + '\'' +
                ", actype='" + actype + '\'' +
                ", sellflag='" + sellflag + '\'' +
                ", operator='" + operator + '\'' +
                ", makedate=" + makedate +
                ", maketime='" + maketime + '\'' +
                ", modifydate=" + modifydate +
                ", modifytime='" + modifytime + '\'' +
                ", banktype='" + banktype + '\'' +
                ", calflag='" + calflag + '\'' +
                ", busilicensecode='" + busilicensecode + '\'' +
                ", insureid='" + insureid + '\'' +
                ", insureprincipal='" + insureprincipal + '\'' +
                ", chiefbusiness='" + chiefbusiness + '\'' +
                ", busiaddress='" + busiaddress + '\'' +
                ", subscribeman='" + subscribeman + '\'' +
                ", subscribemanduty='" + subscribemanduty + '\'' +
                ", licenseno='" + licenseno + '\'' +
                ", regionalismcode='" + regionalismcode + '\'' +
                ", appagentcom='" + appagentcom + '\'' +
                ", state='" + state + '\'' +
                ", noti='" + noti + '\'' +
                ", businesscode='" + businesscode + '\'' +
                ", licensestartdate=" + licensestartdate +
                ", licenseenddate=" + licenseenddate +
                ", branchtype='" + branchtype + '\'' +
                ", branchtype2='" + branchtype2 + '\'' +
                ", assets=" + assets +
                ", income=" + income +
                ", profits=" + profits +
                ", personnalsum=" + personnalsum +
                ", protocalno='" + protocalno + '\'' +
                ", headoffice='" + headoffice + '\'' +
                ", founddate=" + founddate +
                ", enddate=" + enddate +
                ", touliansellflag='" + touliansellflag + '\'' +
                ", licenseowner='" + licenseowner + '\'' +
                ", batchflag='" + batchflag + '\'' +
                ", batchmakedate=" + batchmakedate +
                ", batchmaketime='" + batchmaketime + '\'' +
                ", socialcode='" + socialcode + '\'' +
                ", socialtype='" + socialtype + '\'' +
                ", expirydate='" + expirydate + '\'' +
                ", contractdate='" + contractdate + '\'' +
                ", businessscope='" + businessscope + '\'' +
                ", businessarea='" + businessarea + '\'' +
                ", managername='" + managername + '\'' +
                ", illegalrecord='" + illegalrecord + '\'' +
                ", businesslicensenumber='" + businesslicensenumber + '\'' +
                ", businesslicensenamecode='" + businesslicensenamecode + '\'' +
                ", channel='" + channel + '\'' +
                ", csName='" + csName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", date='" + date + '\'' +
                ", proportion='" + proportion + '\'' +
                ", channelCooperation='" + channelCooperation + '\'' +
                ", agencyCompanyName='" + agencyCompanyName + '\'' +
                ", zCompany='" + zCompany + '\'' +
                ", shCompany='" + shCompany + '\'' +
                ", zjCompany='" + zjCompany + '\'' +
                ", jsCompany='" + jsCompany + '\'' +
                ", bjCompany='" + bjCompany + '\'' +
                ", scCompany='" + scCompany + '\'' +
                ", sdCompany='" + sdCompany + '\'' +
                ", hnCompany='" + hnCompany + '\'' +
                ", parentcompanyname='" + parentcompanyname + '\'' +
                '}';
    }
}
