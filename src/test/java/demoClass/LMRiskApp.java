package demoClass;

import java.util.Date;

public class LMRiskApp {
    private static final long serialVersionUID = -41410824194876778L;
    /**
     * 险种编码
     */
    private String riskcode;
    /**
     * 险种版本
     */
    private String riskver;
    /**
     * 险种名称
     */
    private String riskname;
    /**
     * 险类编码
     */
    private String kindcode;
    /**
     * 险种分类
     */
    private String risktype;
    /**
     * 险种分类1
     */
    private String risktype1;
    /**
     * 险种分类2
     */
    private String risktype2;
    /**
     * 险种性质
     */
    private String riskprop;
    /**
     * 险种类别
     */
    private String riskperiod;
    /**
     * 险种细分
     */
    private String risktypedetail;
    /**
     * 险种标记
     */
    private String riskflag;
    /**
     * 保单类型
     */
    private String poltype;
    /**
     * 投资标记
     */
    private String investflag;
    /**
     * 分红标记
     */
    private String bonusflag;
    /**
     * 红利领取方式
     */
    private String bonusmode;
    /**
     * 有无名单标记
     */
    private String listflag;
    /**
     * 主附险标记
     */
    private String subriskflag;
    /**
     * 计算精确位
     */
    private Integer caldigital;
    /**
     * 计算取舍方法
     */
    private String calchomode;
    /**
     * 风险保额倍数
     */
    private Integer riskamntmult;
    /**
     * 保险期限标志
     */
    private String insuperiodflag;
    /**
     * 保险期间上限
     */
    private Integer maxendperiod;
    /**
     * 满期截至年龄
     */
    private Integer agelmt;
    /**
     * 签单日算法
     */
    private Integer signdatecalmode;
    /**
     * 协议险标记
     */
    private String protocolflag;
    /**
     * 协议险给付金改变标记
     */
    private String getchgflag;
    /**
     * 协议缴费标记
     */
    private String protocolpayflag;
    /**
     * 保障计划标记
     */
    private String ensuplanflag;
    /**
     * 保障计划调整标记
     */
    private String ensuplanadjflag;
    /**
     * 开办日期
     */
    private Date startdate;
    /**
     * 停办日期
     */
    private Date enddate;
    /**
     * 最小投保人年龄
     */
    private Integer minappntage;
    /**
     * 最大投保人年龄
     */
    private Integer maxappntage;
    /**
     * 最大被保人年龄
     */
    private Integer maxinsuredage;
    /**
     * 最小被保人年龄
     */
    private Integer minunsuredage;
    /**
     * 投保使用利率
     */
    private Double appinterest;
    /**
     * 投保使用费率
     */
    private Double apppremrate;
    /**
     * 多被保人标记
     */
    private String insuredflag;
    /**
     * 共保标记
     */
    private String shareflag;
    /**
     * 受益人标记
     */
    private String bnfflag;
    /**
     * 录入缴费项标记
     */
    private String inppayplan;
    /**
     * 告知标记
     */
    private String impartflag;
    /**
     * 保险经历标记
     */
    private String insuexpeflag;
    /**
     * 提供借款标记
     */
    private String loanfalg;
    /**
     * 抵押标记
     */
    private String mortagageflag;
    /**
     * 备注
     */
    private String idifreturnflag;
    /**
     * 减额缴清标记
     */
    private String cutamntstoppay;
    /**
     * 分保率
     */
    private Double rinsrate;
    /**
     * 销售标记
     */
    private String saleflag;
    /**
     * 磁盘文件投保标记
     */
    private String fileappflag;
    /**
     * 管理部门
     */
    private String mngcom;
    /**
     * 自动垫缴标志
     */
    private String autopayflag;
    /**
     * 是否打印医院列表标记
     */
    private String needprinthospital;
    /**
     * 是否打印伤残给付表标记
     */
    private String needprintget;
    /**
     * 险种分类3
     */
    private String risktype3;
    /**
     * 险种分类4
     */
    private String risktype4;
    /**
     * 险种分类5
     */
    private String risktype5;
    /**
     * 签单后不需要打印
     */
    private String notprintpol;
    /**
     * 录单时是否需要设置保单送达日期
     */
    private String needgetpoldate;
    /**
     * 是否从暂缴费表中读取银行的账号和户名
     */
    private String needrereadbank;
    /**
     * 特殊险种标记
     */
    private String specflag;
    /**
     * 利差返还标记
     */
    private String interestdifflag;
    /**
     * 暂缴费标记
     */
    private String temppatflag;

    private String branchtype;

    public LMRiskApp(){

    }

    public LMRiskApp(String riskcode, String riskver, String riskname, String kindcode, String risktype, String risktype1, String risktype2,
                     String riskprop, String riskperiod, String risktypedetail, String riskflag, String poltype, String investflag,
                     String bonusflag, String bonusmode, String listflag, String subriskflag, Integer caldigital, String calchomode,
                     Integer riskamntmult, String insuperiodflag, Integer maxendperiod, Integer agelmt, Integer signdatecalmode,
                     String protocolflag, String getchgflag, String protocolpayflag, String ensuplanflag, String ensuplanadjflag,
                     Date startdate, Date enddate, Integer minappntage, Integer maxappntage, Integer maxinsuredage, Double appinterest,
                     Double apppremrate, String insuredflag, String shareflag, String bnfflag, String inppayplan, String impartflag,
                     String insuexpeflag, String loanfalg, String mortagageflag, String idifreturnflag, String cutamntstoppay, Double rinsrate,
                     String saleflag, String fileappflag, String mngcom, String autopayflag, String needprinthospital, String needprintget,
                     String risktype3, String risktype4, String risktype5, String notprintpol, String needgetpoldate, String needrereadbank,
                     String specflag, String interestdifflag, Integer minunsuredage, String temppatflag, String branchtype) {
        this.riskcode = riskcode;
        this.riskver = riskver;
        this.riskname = riskname;
        this.kindcode = kindcode;
        this.risktype = risktype;
        this.risktype1 = risktype1;
        this.risktype2 = risktype2;
        this.riskprop = riskprop;
        this.riskperiod = riskperiod;
        this.risktypedetail = risktypedetail;
        this.riskflag = riskflag;
        this.poltype = poltype;
        this.investflag = investflag;
        this.bonusflag = bonusflag;
        this.bonusmode = bonusmode;
        this.listflag = listflag;
        this.subriskflag = subriskflag;
        this.caldigital = caldigital;
        this.calchomode = calchomode;
        this.riskamntmult = riskamntmult;
        this.insuperiodflag = insuperiodflag;
        this.maxendperiod = maxendperiod;
        this.agelmt = agelmt;
        this.signdatecalmode = signdatecalmode;
        this.protocolflag = protocolflag;
        this.getchgflag = getchgflag;
        this.protocolpayflag = protocolpayflag;
        this.ensuplanflag = ensuplanflag;
        this.ensuplanadjflag = ensuplanadjflag;
        this.startdate = startdate;
        this.enddate = enddate;
        this.minappntage = minappntage;
        this.maxappntage = maxappntage;
        this.maxinsuredage = maxinsuredage;
        this.appinterest = appinterest;
        this.apppremrate = apppremrate;
        this.insuredflag = insuredflag;
        this.shareflag = shareflag;
        this.bnfflag = bnfflag;
        this.inppayplan = inppayplan;
        this.impartflag = impartflag;
        this.insuexpeflag = insuexpeflag;
        this.loanfalg = loanfalg;
        this.mortagageflag = mortagageflag;
        this.idifreturnflag = idifreturnflag;
        this.cutamntstoppay = cutamntstoppay;
        this.rinsrate = rinsrate;
        this.saleflag = saleflag;
        this.fileappflag = fileappflag;
        this.mngcom = mngcom;
        this.autopayflag = autopayflag;
        this.needprinthospital = needprinthospital;
        this.needprintget = needprintget;
        this.risktype3 = risktype3;
        this.risktype4 = risktype4;
        this.risktype5 = risktype5;
        this.notprintpol = notprintpol;
        this.needgetpoldate = needgetpoldate;
        this.needrereadbank = needrereadbank;
        this.specflag = specflag;
        this.interestdifflag = interestdifflag;
        this.minunsuredage = minunsuredage;
        this.temppatflag = temppatflag;
        this.branchtype = branchtype;
    }

    public String getRiskcode() {
        return riskcode;
    }

    public void setRiskcode(String riskcode) {
        this.riskcode = riskcode;
    }

    public String getRiskver() {
        return riskver;
    }

    public void setRiskver(String riskver) {
        this.riskver = riskver;
    }

    public String getRiskname() {
        return riskname;
    }

    public void setRiskname(String riskname) {
        this.riskname = riskname;
    }

    public String getKindcode() {
        return kindcode;
    }

    public void setKindcode(String kindcode) {
        this.kindcode = kindcode;
    }

    public String getRisktype() {
        return risktype;
    }

    public void setRisktype(String risktype) {
        this.risktype = risktype;
    }

    public String getRisktype1() {
        return risktype1;
    }

    public void setRisktype1(String risktype1) {
        this.risktype1 = risktype1;
    }

    public String getRisktype2() {
        return risktype2;
    }

    public void setRisktype2(String risktype2) {
        this.risktype2 = risktype2;
    }

    public String getRiskprop() {
        return riskprop;
    }

    public void setRiskprop(String riskprop) {
        this.riskprop = riskprop;
    }

    public String getRiskperiod() {
        return riskperiod;
    }

    public void setRiskperiod(String riskperiod) {
        this.riskperiod = riskperiod;
    }

    public String getRisktypedetail() {
        return risktypedetail;
    }

    public void setRisktypedetail(String risktypedetail) {
        this.risktypedetail = risktypedetail;
    }

    public String getRiskflag() {
        return riskflag;
    }

    public void setRiskflag(String riskflag) {
        this.riskflag = riskflag;
    }

    public String getPoltype() {
        return poltype;
    }

    public void setPoltype(String poltype) {
        this.poltype = poltype;
    }

    public String getInvestflag() {
        return investflag;
    }

    public void setInvestflag(String investflag) {
        this.investflag = investflag;
    }

    public String getBonusflag() {
        return bonusflag;
    }

    public void setBonusflag(String bonusflag) {
        this.bonusflag = bonusflag;
    }

    public String getBonusmode() {
        return bonusmode;
    }

    public void setBonusmode(String bonusmode) {
        this.bonusmode = bonusmode;
    }

    public String getListflag() {
        return listflag;
    }

    public void setListflag(String listflag) {
        this.listflag = listflag;
    }

    public String getSubriskflag() {
        return subriskflag;
    }

    public void setSubriskflag(String subriskflag) {
        this.subriskflag = subriskflag;
    }

    public Integer getCaldigital() {
        return caldigital;
    }

    public void setCaldigital(Integer caldigital) {
        this.caldigital = caldigital;
    }

    public String getCalchomode() {
        return calchomode;
    }

    public void setCalchomode(String calchomode) {
        this.calchomode = calchomode;
    }

    public Integer getRiskamntmult() {
        return riskamntmult;
    }

    public void setRiskamntmult(Integer riskamntmult) {
        this.riskamntmult = riskamntmult;
    }

    public String getInsuperiodflag() {
        return insuperiodflag;
    }

    public void setInsuperiodflag(String insuperiodflag) {
        this.insuperiodflag = insuperiodflag;
    }

    public Integer getMaxendperiod() {
        return maxendperiod;
    }

    public void setMaxendperiod(Integer maxendperiod) {
        this.maxendperiod = maxendperiod;
    }

    public Integer getAgelmt() {
        return agelmt;
    }

    public void setAgelmt(Integer agelmt) {
        this.agelmt = agelmt;
    }

    public Integer getSigndatecalmode() {
        return signdatecalmode;
    }

    public void setSigndatecalmode(Integer signdatecalmode) {
        this.signdatecalmode = signdatecalmode;
    }

    public String getProtocolflag() {
        return protocolflag;
    }

    public void setProtocolflag(String protocolflag) {
        this.protocolflag = protocolflag;
    }

    public String getGetchgflag() {
        return getchgflag;
    }

    public void setGetchgflag(String getchgflag) {
        this.getchgflag = getchgflag;
    }

    public String getProtocolpayflag() {
        return protocolpayflag;
    }

    public void setProtocolpayflag(String protocolpayflag) {
        this.protocolpayflag = protocolpayflag;
    }

    public String getEnsuplanflag() {
        return ensuplanflag;
    }

    public void setEnsuplanflag(String ensuplanflag) {
        this.ensuplanflag = ensuplanflag;
    }

    public String getEnsuplanadjflag() {
        return ensuplanadjflag;
    }

    public void setEnsuplanadjflag(String ensuplanadjflag) {
        this.ensuplanadjflag = ensuplanadjflag;
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

    public Integer getMinappntage() {
        return minappntage;
    }

    public void setMinappntage(Integer minappntage) {
        this.minappntage = minappntage;
    }

    public Integer getMaxappntage() {
        return maxappntage;
    }

    public void setMaxappntage(Integer maxappntage) {
        this.maxappntage = maxappntage;
    }

    public Integer getMaxinsuredage() {
        return maxinsuredage;
    }

    public void setMaxinsuredage(Integer maxinsuredage) {
        this.maxinsuredage = maxinsuredage;
    }

    public Double getAppinterest() {
        return appinterest;
    }

    public void setAppinterest(Double appinterest) {
        this.appinterest = appinterest;
    }

    public Double getApppremrate() {
        return apppremrate;
    }

    public void setApppremrate(Double apppremrate) {
        this.apppremrate = apppremrate;
    }

    public String getInsuredflag() {
        return insuredflag;
    }

    public void setInsuredflag(String insuredflag) {
        this.insuredflag = insuredflag;
    }

    public String getShareflag() {
        return shareflag;
    }

    public void setShareflag(String shareflag) {
        this.shareflag = shareflag;
    }

    public String getBnfflag() {
        return bnfflag;
    }

    public void setBnfflag(String bnfflag) {
        this.bnfflag = bnfflag;
    }

    public String getInppayplan() {
        return inppayplan;
    }

    public void setInppayplan(String inppayplan) {
        this.inppayplan = inppayplan;
    }

    public String getImpartflag() {
        return impartflag;
    }

    public void setImpartflag(String impartflag) {
        this.impartflag = impartflag;
    }

    public String getInsuexpeflag() {
        return insuexpeflag;
    }

    public void setInsuexpeflag(String insuexpeflag) {
        this.insuexpeflag = insuexpeflag;
    }

    public String getLoanfalg() {
        return loanfalg;
    }

    public void setLoanfalg(String loanfalg) {
        this.loanfalg = loanfalg;
    }

    public String getMortagageflag() {
        return mortagageflag;
    }

    public void setMortagageflag(String mortagageflag) {
        this.mortagageflag = mortagageflag;
    }

    public String getIdifreturnflag() {
        return idifreturnflag;
    }

    public void setIdifreturnflag(String idifreturnflag) {
        this.idifreturnflag = idifreturnflag;
    }

    public String getCutamntstoppay() {
        return cutamntstoppay;
    }

    public void setCutamntstoppay(String cutamntstoppay) {
        this.cutamntstoppay = cutamntstoppay;
    }

    public Double getRinsrate() {
        return rinsrate;
    }

    public void setRinsrate(Double rinsrate) {
        this.rinsrate = rinsrate;
    }

    public String getSaleflag() {
        return saleflag;
    }

    public void setSaleflag(String saleflag) {
        this.saleflag = saleflag;
    }

    public String getFileappflag() {
        return fileappflag;
    }

    public void setFileappflag(String fileappflag) {
        this.fileappflag = fileappflag;
    }

    public String getMngcom() {
        return mngcom;
    }

    public void setMngcom(String mngcom) {
        this.mngcom = mngcom;
    }

    public String getAutopayflag() {
        return autopayflag;
    }

    public void setAutopayflag(String autopayflag) {
        this.autopayflag = autopayflag;
    }

    public String getNeedprinthospital() {
        return needprinthospital;
    }

    public void setNeedprinthospital(String needprinthospital) {
        this.needprinthospital = needprinthospital;
    }

    public String getNeedprintget() {
        return needprintget;
    }

    public void setNeedprintget(String needprintget) {
        this.needprintget = needprintget;
    }

    public String getRisktype3() {
        return risktype3;
    }

    public void setRisktype3(String risktype3) {
        this.risktype3 = risktype3;
    }

    public String getRisktype4() {
        return risktype4;
    }

    public void setRisktype4(String risktype4) {
        this.risktype4 = risktype4;
    }

    public String getRisktype5() {
        return risktype5;
    }

    public void setRisktype5(String risktype5) {
        this.risktype5 = risktype5;
    }

    public String getNotprintpol() {
        return notprintpol;
    }

    public void setNotprintpol(String notprintpol) {
        this.notprintpol = notprintpol;
    }

    public String getNeedgetpoldate() {
        return needgetpoldate;
    }

    public void setNeedgetpoldate(String needgetpoldate) {
        this.needgetpoldate = needgetpoldate;
    }

    public String getNeedrereadbank() {
        return needrereadbank;
    }

    public void setNeedrereadbank(String needrereadbank) {
        this.needrereadbank = needrereadbank;
    }

    public String getSpecflag() {
        return specflag;
    }

    public void setSpecflag(String specflag) {
        this.specflag = specflag;
    }

    public String getInterestdifflag() {
        return interestdifflag;
    }

    public void setInterestdifflag(String interestdifflag) {
        this.interestdifflag = interestdifflag;
    }

    public Integer getMinunsuredage() {
        return minunsuredage;
    }

    public void setMinunsuredage(Integer minunsuredage) {
        this.minunsuredage = minunsuredage;
    }

    public String getTemppatflag() {
        return temppatflag;
    }

    public void setTemppatflag(String temppatflag) {
        this.temppatflag = temppatflag;
    }

    public String getBranchtype() {
        return branchtype;
    }

    public void setBranchtype(String branchtype) {
        this.branchtype = branchtype;
    }

    @Override
    public String toString() {
        return "LMRiskApp{" +
                "riskcode='" + riskcode + '\'' +
                ", riskver='" + riskver + '\'' +
                ", riskname='" + riskname + '\'' +
                ", kindcode='" + kindcode + '\'' +
                ", risktype='" + risktype + '\'' +
                ", risktype1='" + risktype1 + '\'' +
                ", risktype2='" + risktype2 + '\'' +
                ", riskprop='" + riskprop + '\'' +
                ", riskperiod='" + riskperiod + '\'' +
                ", risktypedetail='" + risktypedetail + '\'' +
                ", riskflag='" + riskflag + '\'' +
                ", poltype='" + poltype + '\'' +
                ", investflag='" + investflag + '\'' +
                ", bonusflag='" + bonusflag + '\'' +
                ", bonusmode='" + bonusmode + '\'' +
                ", listflag='" + listflag + '\'' +
                ", subriskflag='" + subriskflag + '\'' +
                ", caldigital=" + caldigital +
                ", calchomode='" + calchomode + '\'' +
                ", riskamntmult=" + riskamntmult +
                ", insuperiodflag='" + insuperiodflag + '\'' +
                ", maxendperiod=" + maxendperiod +
                ", agelmt=" + agelmt +
                ", signdatecalmode=" + signdatecalmode +
                ", protocolflag='" + protocolflag + '\'' +
                ", getchgflag='" + getchgflag + '\'' +
                ", protocolpayflag='" + protocolpayflag + '\'' +
                ", ensuplanflag='" + ensuplanflag + '\'' +
                ", ensuplanadjflag='" + ensuplanadjflag + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", minappntage=" + minappntage +
                ", maxappntage=" + maxappntage +
                ", maxinsuredage=" + maxinsuredage +
                ", appinterest=" + appinterest +
                ", apppremrate=" + apppremrate +
                ", insuredflag='" + insuredflag + '\'' +
                ", shareflag='" + shareflag + '\'' +
                ", bnfflag='" + bnfflag + '\'' +
                ", inppayplan='" + inppayplan + '\'' +
                ", impartflag='" + impartflag + '\'' +
                ", insuexpeflag='" + insuexpeflag + '\'' +
                ", loanfalg='" + loanfalg + '\'' +
                ", mortagageflag='" + mortagageflag + '\'' +
                ", idifreturnflag='" + idifreturnflag + '\'' +
                ", cutamntstoppay='" + cutamntstoppay + '\'' +
                ", rinsrate=" + rinsrate +
                ", saleflag='" + saleflag + '\'' +
                ", fileappflag='" + fileappflag + '\'' +
                ", mngcom='" + mngcom + '\'' +
                ", autopayflag='" + autopayflag + '\'' +
                ", needprinthospital='" + needprinthospital + '\'' +
                ", needprintget='" + needprintget + '\'' +
                ", risktype3='" + risktype3 + '\'' +
                ", risktype4='" + risktype4 + '\'' +
                ", risktype5='" + risktype5 + '\'' +
                ", notprintpol='" + notprintpol + '\'' +
                ", needgetpoldate='" + needgetpoldate + '\'' +
                ", needrereadbank='" + needrereadbank + '\'' +
                ", specflag='" + specflag + '\'' +
                ", interestdifflag='" + interestdifflag + '\'' +
                ", minunsuredage=" + minunsuredage +
                ", temppatflag='" + temppatflag + '\'' +
                ", branchtype='" + branchtype + '\'' +
                '}';
    }
}
