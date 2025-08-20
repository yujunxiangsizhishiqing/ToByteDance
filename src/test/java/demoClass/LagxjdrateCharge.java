package demoClass;

import java.util.Date;

public class LagxjdrateCharge {
    private static final long serialVersionUID = 964978522910258890L;
    /**
     * 手续费率流水号
     */
    private Integer ratesn;
    /**
     * 合同号
     */
    private String compactcode;
    /**
     * 费率类型：01-基本手续费率、02-补充手续费率、03-月奖费率、04-年奖费率、05-续期服务津贴费率、06-继续率奖金费率/费率类型(01-新单业务推广费；02-首年及续年经纪费；03-月度奖金；04-继续率奖金)	费率类型(05-阶段业务推动费)	费率类型(06-技术服务费)	费率类型(07-重疾服务费)	费率类型(08-专项奖励费)
     */
    private String ratetype;
    /**
     * 险种编码
     */
    private String riskcode;
    /**
     * 缴费年期
     */
    private Integer payyears;
    /**
     * 缴费年期起期/总人数下线
     */
    private Integer payyearsstart;
    /**
     * 缴费年期止期/总人数上线
     */
    private Integer payyearsend;
    /**
     * 保单年度起期
     */
    private Integer payyearstart;
    /**
     * 保单年度止期
     */
    private Integer payyearend;
    /**
     * 新单标保合计上限
     */
    private Double newfypsumup;
    /**
     * 新单标保合计下限
     */
    private Double newfypsumdown;
    /**
     * 继续率上限:单位为“%” /继续率达成上限
     */
    private Double continuerateup;
    /**
     * 继续率下限:单位为“%” /继续率达成下限
     */
    private Double continueratedown;
    /**
     * 手续费率:单位为“%”
     */
    private Double rate;
    /**
     * 手续费
     */
    private Double charge;
    /**
     * 手续费上限
     */
    private Double chargeup;

    private Double chargedown;
    /**
     * 生效日期起期/承保日期起期
     */
    private Date startdate;
    /**
     * 生效日期止期/承保日期止期
     */
    private Date enddate;
    /**
     * 审核状态:审核状态：01-待审核、02-已审核、03-展期中/审核状态（01-待审核、02-已审核、03-已中止、04-展期中）
     */
    private String reviewstatus;

    private String branchtype;

    private String branchtype2;
    /**
     * 保障类型（0-年、1-岁）/结算方式（0-按月，1-按区间）
     */
    private String v1;
    /**
     * 是否回访成功(Y：表示只有回访成功的才纳入手续费结算，N：表示回访成功的、未回访成功的都纳入手续费结算)
     */
    private String v2;
    /**
     * 中止原因
     */
    private String v3;

    private String v4;

    private String v5;
    /**
     * 计算公式
     */
    private String v6;
    /**
     * 中止时间
     */
    private Date d1;

    private Date d2;

    private Date d3;

    private Date d4;

    private Date d5;

    private Date d6;
    /**
     * 保险期间/组号
     */
    private Integer i1;

    private Integer i2;

    private Integer i3;

    private Integer i4;

    private Integer i5;

    private Integer i6;
    /**
     * 单价(元)
     */
    private Double dc1;
    /**
     * 奖金金额
     */
    private Double dc2;

    private Double dc3;

    private Double dc4;

    private Double dc5;

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
     * 继续率类型：0-13续
     * 1-25续
     * 2-37续
     */
    private String continuerate;
    /**
     * 新单保费合计上限
     */
    private Double newpcprmsumup;
    /**
     * 新单保费合计下限
     */
    private Double newpcprmsumdown;
    /**
     * 续期实收保费上限
     */
    private Double rpaidpcprmup;
    /**
     * 续期实收保费下限
     */
    private Double rpaidpcprmdown;

    public LagxjdrateCharge(){

    }

    public LagxjdrateCharge(Integer ratesn, String compactcode, String ratetype, String riskcode, Integer payyears, Integer payyearsstart,
                            Integer payyearsend, Integer payyearstart, Integer payyearend, Double newfypsumup, Double newfypsumdown,
                            Double continuerateup, Double continueratedown, Double rate, Double charge, Double chargeup, Double chargedown,
                            Date startdate, Date enddate, String reviewstatus, String branchtype, String branchtype2, String v1, String v2,
                            String v3, String v4, String v5, String v6, Date d1, Date d2, Date d3, Date d4, Date d5, Date d6, Integer i1,
                            Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Double dc1, Double dc2, Double dc3, Double dc4,
                            Double dc5, Double dc6, Date makedate, String maketime, Date modifydate, String modifytime, String operator,
                            String continuerate, Double newpcprmsumup, Double newpcprmsumdown, Double rpaidpcprmup, Double rpaidpcprmdown) {
        this.ratesn = ratesn;
        this.compactcode = compactcode;
        this.ratetype = ratetype;
        this.riskcode = riskcode;
        this.payyears = payyears;
        this.payyearsstart = payyearsstart;
        this.payyearsend = payyearsend;
        this.payyearstart = payyearstart;
        this.payyearend = payyearend;
        this.newfypsumup = newfypsumup;
        this.newfypsumdown = newfypsumdown;
        this.continuerateup = continuerateup;
        this.continueratedown = continueratedown;
        this.rate = rate;
        this.charge = charge;
        this.chargeup = chargeup;
        this.chargedown = chargedown;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reviewstatus = reviewstatus;
        this.branchtype = branchtype;
        this.branchtype2 = branchtype2;
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
        this.continuerate = continuerate;
        this.newpcprmsumup = newpcprmsumup;
        this.newpcprmsumdown = newpcprmsumdown;
        this.rpaidpcprmup = rpaidpcprmup;
        this.rpaidpcprmdown = rpaidpcprmdown;
    }

    public Integer getRatesn() {
        return ratesn;
    }

    public void setRatesn(Integer ratesn) {
        this.ratesn = ratesn;
    }

    public String getCompactcode() {
        return compactcode;
    }

    public void setCompactcode(String compactcode) {
        this.compactcode = compactcode;
    }

    public String getRatetype() {
        return ratetype;
    }

    public void setRatetype(String ratetype) {
        this.ratetype = ratetype;
    }

    public String getRiskcode() {
        return riskcode;
    }

    public void setRiskcode(String riskcode) {
        this.riskcode = riskcode;
    }

    public Integer getPayyears() {
        return payyears;
    }

    public void setPayyears(Integer payyears) {
        this.payyears = payyears;
    }

    public Integer getPayyearsstart() {
        return payyearsstart;
    }

    public void setPayyearsstart(Integer payyearsstart) {
        this.payyearsstart = payyearsstart;
    }

    public Integer getPayyearsend() {
        return payyearsend;
    }

    public void setPayyearsend(Integer payyearsend) {
        this.payyearsend = payyearsend;
    }

    public Integer getPayyearstart() {
        return payyearstart;
    }

    public void setPayyearstart(Integer payyearstart) {
        this.payyearstart = payyearstart;
    }

    public Integer getPayyearend() {
        return payyearend;
    }

    public void setPayyearend(Integer payyearend) {
        this.payyearend = payyearend;
    }

    public Double getNewfypsumup() {
        return newfypsumup;
    }

    public void setNewfypsumup(Double newfypsumup) {
        this.newfypsumup = newfypsumup;
    }

    public Double getNewfypsumdown() {
        return newfypsumdown;
    }

    public void setNewfypsumdown(Double newfypsumdown) {
        this.newfypsumdown = newfypsumdown;
    }

    public Double getContinuerateup() {
        return continuerateup;
    }

    public void setContinuerateup(Double continuerateup) {
        this.continuerateup = continuerateup;
    }

    public Double getContinueratedown() {
        return continueratedown;
    }

    public void setContinueratedown(Double continueratedown) {
        this.continueratedown = continueratedown;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Double getChargeup() {
        return chargeup;
    }

    public void setChargeup(Double chargeup) {
        this.chargeup = chargeup;
    }

    public Double getChargedown() {
        return chargedown;
    }

    public void setChargedown(Double chargedown) {
        this.chargedown = chargedown;
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

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus;
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

    public String getContinuerate() {
        return continuerate;
    }

    public void setContinuerate(String continuerate) {
        this.continuerate = continuerate;
    }

    public Double getNewpcprmsumup() {
        return newpcprmsumup;
    }

    public void setNewpcprmsumup(Double newpcprmsumup) {
        this.newpcprmsumup = newpcprmsumup;
    }

    public Double getNewpcprmsumdown() {
        return newpcprmsumdown;
    }

    public void setNewpcprmsumdown(Double newpcprmsumdown) {
        this.newpcprmsumdown = newpcprmsumdown;
    }

    public Double getRpaidpcprmup() {
        return rpaidpcprmup;
    }

    public void setRpaidpcprmup(Double rpaidpcprmup) {
        this.rpaidpcprmup = rpaidpcprmup;
    }

    public Double getRpaidpcprmdown() {
        return rpaidpcprmdown;
    }

    public void setRpaidpcprmdown(Double rpaidpcprmdown) {
        this.rpaidpcprmdown = rpaidpcprmdown;
    }

    @Override
    public String toString() {
        return "LagxjdrateCharge{" +
                "ratesn=" + ratesn +
                ", compactcode='" + compactcode + '\'' +
                ", ratetype='" + ratetype + '\'' +
                ", riskcode='" + riskcode + '\'' +
                ", payyears=" + payyears +
                ", payyearsstart=" + payyearsstart +
                ", payyearsend=" + payyearsend +
                ", payyearstart=" + payyearstart +
                ", payyearend=" + payyearend +
                ", newfypsumup=" + newfypsumup +
                ", newfypsumdown=" + newfypsumdown +
                ", continuerateup=" + continuerateup +
                ", continueratedown=" + continueratedown +
                ", rate=" + rate +
                ", charge=" + charge +
                ", chargeup=" + chargeup +
                ", chargedown=" + chargedown +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", reviewstatus='" + reviewstatus + '\'' +
                ", branchtype='" + branchtype + '\'' +
                ", branchtype2='" + branchtype2 + '\'' +
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
                ", continuerate='" + continuerate + '\'' +
                ", newpcprmsumup=" + newpcprmsumup +
                ", newpcprmsumdown=" + newpcprmsumdown +
                ", rpaidpcprmup=" + rpaidpcprmup +
                ", rpaidpcprmdown=" + rpaidpcprmdown +
                '}';
    }
}
