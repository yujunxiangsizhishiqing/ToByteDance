package demoClass;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TraSxfInterfaceDetailsT {
    /**
     * 主键
     * */
    //内部主键
    private String interfaceId;
    //批次号
    private String batchId;
    /**
     * 交易相关信息
     * */
    //来源系统代码
    private String transactionSource;
    //交易类型
    private String transactionType;
    //业务交易发生时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date transactionDate;
    //业务号码
    private String transactionNo;
    //渠道代码
    private String channelCode;
    //渠道名称
    private String channelDesc;
    //结算单类型代码
    private String settlementTypeCode;
    //结算单类型名称
    private String settlementTypeName;
    //手续费类型编码
    private String serviceFeeCategoryCode;
    //手续费类型名称
    private String serviceFeeCategoryName;
    /**
     * 手续费信息
     * */
    //代理人编码
    private String agencyCode;
    //代理人姓名
    private String agencyName;
    //代理公司编码
    private String agencyCompanyCode;
    //代理公司名称
    private String agencyCompanyName;
    //基本手续费比例
    private Double fBasicChargeRate;
    //首期佣金
    private Double fBasicCharge;
    //月度奖金比例
    private Double monthChargeRate;
    //月度奖金
    private Double monthCharge;
    //年度奖金比例
    private Double yearChargeRate;
    //年度奖金
    private Double yearCharge;
    //新单手续费合计
    private Double newChargeSum;
    private Double newOrderChargeRate;	//新单业务推广费比例
    private Double newOrderCharge;	//新单业务推广费
    private Double busMotivateChargeRate;	//业务推动费比例
    private Double busMotivateCharge;	//业务推动费
    private Double specialChargeRate;	//专项奖励费比例
    private Double specialCharge;	//专项奖励费
    //补充手续费比例
    private Double supPlementChargeRate;
    //补充手续费
    private Double supPlementCharge;
    //续期手续费比例（03、04）
    private Double rBasicChargeRate;
    //续期手续费（03、04）
    private Double rBasicCharge;
    //续期服务津贴比例
    private Double rServiceChargeRate;
    //续期服务津贴
    private Double rServiceCharge;
    //手续费合计
    private Double rChargeSum;
    //年期换算系数
    private Double conversionChargeRate;
    //实收保费*年期换算系数合计
    private Double conversionCharge;
    //继续率
    private Double continuationRate;
    //继续率奖金比例
    private Double continuationChargeRate;
    //继续率奖金
    private Double continuationCharge;
    //计提金额
    private Double apMoney;
    //结算金额
    private Double commissionMoney;
    //结算单号
    private String commissionNo;
    //结算日期
    private Date commissionDate;
    //结算年月起期
    private String startMonth;
    //结算年月止期
    private String endMonth;
    //合同编号
    private String agreementCode;
    //合同名称
    private String agreementName;
    //合同有效期起
    private Date agreementStartDate;
    //合同有效期止
    private Date agreementEndDate;
    //手续费状态
    private String serviceFeeStatus;
    //手续期手续费标志
    private String initialYearDesc;//首年、续年
    //手续期手续费标志
    private String initialPeriodDesc;//首期，续期
    /**
     * 保单数据信息
     * */
    //保单号
    private String policyNo;
    //投保单号
    private String applicationNo;
    //投保人姓名
    private String policyHolderName;
    //保单管理机构
    private String policyOrgCode;
    //产品代码
    private String productCode;
    //产品名称
    private String productName;
    //缴别名称
    private String paymentFrequencyDesc;
    //保单承保日期
    private Date signDate;
    //保单实收日期
    private Date enterAccDate;
    //保单生效日期
    private Date cvaliDate;
    //险种报费
    private Double prem;
    //标准保费
    private Double transStandMoney;
    //保单状态
    private String contState;
    /**
     * 支付信息数据
     * */
    //收付费机构
    private String billingPayOrgCode;
    //银行账号
    private String bankAccountNo;
    //账户名称
    private String bankAccountName;
    //对方银行账号
    private String targetBankAccountNo;
    //对方账户名称
    private String targetBankAccountName;
    //币种
    private String currencyCode;
    //手续费支付金额
    private Double serviceFeeAmount;
    //价额
    private Double totalAmount;
    //税额
    private Double taxAmount;
    //支付成功日期
    private Date getAccDate;
    //退票日期
    private Date returnAccDate;
    //是否退票后重新支付
    private String returnFlag;
    /**
     * 系统表通用规范
     * */
    //备用字段1
    private String attribute1;
    //备用字段2
    private String attribute2;
    //备用字段3
    private String attribute3;
    //备用字段4
    private String attribute4;
    //备用字段5
    private String attribute5;
    //备用字段6
    private String attribute6;
    //备用字段7
    private String attribute7;
    //备用字段8
    private String attribute8;
    //备用字段9
    private String attribute9;
    //备用字段10
    private String attribute10;
    //批次状态
    private String batchFlag;
    //创建日期
    private Date batchMakeDate;
    //创建时间
    private String batchMakeTime;
    //创建人
    private String batchCreatUser;
    //最后更新日期
    private Date batchModifyDate;
    //最后更新时间
    private String batchModifyTime;
    //最后更新人
    private String batchModifyUser;
    //剩余预算金额
    private String beLeftApMoney;//非数据库字段
    //冲销金额
    private String chargeMoney;
    //合同状态
    private String agreementStatus;
    //业绩信息流水号
    private String performInfoSriNo;

    public TraSxfInterfaceDetailsT() {
    }

    public TraSxfInterfaceDetailsT(String interfaceId, String batchId, String transactionSource, String transactionType, Date transactionDate, String transactionNo, String channelCode, String channelDesc, String settlementTypeCode, String settlementTypeName, String serviceFeeCategoryCode, String serviceFeeCategoryName, String agencyCode, String agencyName, String agencyCompanyCode, String agencyCompanyName, Double fBasicChargeRate, Double fBasicCharge, Double monthChargeRate, Double monthCharge, Double yearChargeRate, Double yearCharge, Double newChargeSum, Double newOrderChargeRate, Double newOrderCharge, Double busMotivateChargeRate, Double busMotivateCharge, Double specialChargeRate, Double specialCharge, Double supPlementChargeRate, Double supPlementCharge, Double rBasicChargeRate, Double rBasicCharge, Double rServiceChargeRate, Double rServiceCharge, Double rChargeSum, Double conversionChargeRate, Double conversionCharge, Double continuationRate, Double continuationChargeRate, Double continuationCharge, Double apMoney, Double commissionMoney, String commissionNo, Date commissionDate, String startMonth, String endMonth, String agreementCode, String agreementName, Date agreementStartDate, Date agreementEndDate, String serviceFeeStatus, String initialYearDesc, String initialPeriodDesc, String policyNo, String applicationNo, String policyHolderName, String policyOrgCode, String productCode, String productName, String paymentFrequencyDesc, Date signDate, Date enterAccDate, Date cvaliDate, Double prem, Double transStandMoney, String contState, String billingPayOrgCode, String bankAccountNo, String bankAccountName, String targetBankAccountNo, String targetBankAccountName, String currencyCode, Double serviceFeeAmount, Double totalAmount, Double taxAmount, Date getAccDate, Date returnAccDate, String returnFlag, String attribute1, String attribute2, String attribute3, String attribute4, String attribute5, String attribute6, String attribute7, String attribute8, String attribute9, String attribute10, String batchFlag, Date batchMakeDate, String batchMakeTime, String batchCreatUser, Date batchModifyDate, String batchModifyTime, String batchModifyUser, String beLeftApMoney, String chargeMoney, String agreementStatus, String performInfoSriNo) {
        this.interfaceId = interfaceId;
        this.batchId = batchId;
        this.transactionSource = transactionSource;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionNo = transactionNo;
        this.channelCode = channelCode;
        this.channelDesc = channelDesc;
        this.settlementTypeCode = settlementTypeCode;
        this.settlementTypeName = settlementTypeName;
        this.serviceFeeCategoryCode = serviceFeeCategoryCode;
        this.serviceFeeCategoryName = serviceFeeCategoryName;
        this.agencyCode = agencyCode;
        this.agencyName = agencyName;
        this.agencyCompanyCode = agencyCompanyCode;
        this.agencyCompanyName = agencyCompanyName;
        this.fBasicChargeRate = fBasicChargeRate;
        this.fBasicCharge = fBasicCharge;
        this.monthChargeRate = monthChargeRate;
        this.monthCharge = monthCharge;
        this.yearChargeRate = yearChargeRate;
        this.yearCharge = yearCharge;
        this.newChargeSum = newChargeSum;
        this.newOrderChargeRate = newOrderChargeRate;
        this.newOrderCharge = newOrderCharge;
        this.busMotivateChargeRate = busMotivateChargeRate;
        this.busMotivateCharge = busMotivateCharge;
        this.specialChargeRate = specialChargeRate;
        this.specialCharge = specialCharge;
        this.supPlementChargeRate = supPlementChargeRate;
        this.supPlementCharge = supPlementCharge;
        this.rBasicChargeRate = rBasicChargeRate;
        this.rBasicCharge = rBasicCharge;
        this.rServiceChargeRate = rServiceChargeRate;
        this.rServiceCharge = rServiceCharge;
        this.rChargeSum = rChargeSum;
        this.conversionChargeRate = conversionChargeRate;
        this.conversionCharge = conversionCharge;
        this.continuationRate = continuationRate;
        this.continuationChargeRate = continuationChargeRate;
        this.continuationCharge = continuationCharge;
        this.apMoney = apMoney;
        this.commissionMoney = commissionMoney;
        this.commissionNo = commissionNo;
        this.commissionDate = commissionDate;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.agreementCode = agreementCode;
        this.agreementName = agreementName;
        this.agreementStartDate = agreementStartDate;
        this.agreementEndDate = agreementEndDate;
        this.serviceFeeStatus = serviceFeeStatus;
        this.initialYearDesc = initialYearDesc;
        this.initialPeriodDesc = initialPeriodDesc;
        this.policyNo = policyNo;
        this.applicationNo = applicationNo;
        this.policyHolderName = policyHolderName;
        this.policyOrgCode = policyOrgCode;
        this.productCode = productCode;
        this.productName = productName;
        this.paymentFrequencyDesc = paymentFrequencyDesc;
        this.signDate = signDate;
        this.enterAccDate = enterAccDate;
        this.cvaliDate = cvaliDate;
        this.prem = prem;
        this.transStandMoney = transStandMoney;
        this.contState = contState;
        this.billingPayOrgCode = billingPayOrgCode;
        this.bankAccountNo = bankAccountNo;
        this.bankAccountName = bankAccountName;
        this.targetBankAccountNo = targetBankAccountNo;
        this.targetBankAccountName = targetBankAccountName;
        this.currencyCode = currencyCode;
        this.serviceFeeAmount = serviceFeeAmount;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.getAccDate = getAccDate;
        this.returnAccDate = returnAccDate;
        this.returnFlag = returnFlag;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.attribute6 = attribute6;
        this.attribute7 = attribute7;
        this.attribute8 = attribute8;
        this.attribute9 = attribute9;
        this.attribute10 = attribute10;
        this.batchFlag = batchFlag;
        this.batchMakeDate = batchMakeDate;
        this.batchMakeTime = batchMakeTime;
        this.batchCreatUser = batchCreatUser;
        this.batchModifyDate = batchModifyDate;
        this.batchModifyTime = batchModifyTime;
        this.batchModifyUser = batchModifyUser;
        this.beLeftApMoney = beLeftApMoney;
        this.chargeMoney = chargeMoney;
        this.agreementStatus = agreementStatus;
        this.performInfoSriNo = performInfoSriNo;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public String getSettlementTypeCode() {
        return settlementTypeCode;
    }

    public void setSettlementTypeCode(String settlementTypeCode) {
        this.settlementTypeCode = settlementTypeCode;
    }

    public String getSettlementTypeName() {
        return settlementTypeName;
    }

    public void setSettlementTypeName(String settlementTypeName) {
        this.settlementTypeName = settlementTypeName;
    }

    public String getServiceFeeCategoryCode() {
        return serviceFeeCategoryCode;
    }

    public void setServiceFeeCategoryCode(String serviceFeeCategoryCode) {
        this.serviceFeeCategoryCode = serviceFeeCategoryCode;
    }

    public String getServiceFeeCategoryName() {
        return serviceFeeCategoryName;
    }

    public void setServiceFeeCategoryName(String serviceFeeCategoryName) {
        this.serviceFeeCategoryName = serviceFeeCategoryName;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyCompanyCode() {
        return agencyCompanyCode;
    }

    public void setAgencyCompanyCode(String agencyCompanyCode) {
        this.agencyCompanyCode = agencyCompanyCode;
    }

    public String getAgencyCompanyName() {
        return agencyCompanyName;
    }

    public void setAgencyCompanyName(String agencyCompanyName) {
        this.agencyCompanyName = agencyCompanyName;
    }

    public Double getfBasicChargeRate() {
        return fBasicChargeRate;
    }

    public void setfBasicChargeRate(Double fBasicChargeRate) {
        this.fBasicChargeRate = fBasicChargeRate;
    }

    public Double getfBasicCharge() {
        return fBasicCharge;
    }

    public void setfBasicCharge(Double fBasicCharge) {
        this.fBasicCharge = fBasicCharge;
    }

    public Double getMonthChargeRate() {
        return monthChargeRate;
    }

    public void setMonthChargeRate(Double monthChargeRate) {
        this.monthChargeRate = monthChargeRate;
    }

    public Double getMonthCharge() {
        return monthCharge;
    }

    public void setMonthCharge(Double monthCharge) {
        this.monthCharge = monthCharge;
    }

    public Double getYearChargeRate() {
        return yearChargeRate;
    }

    public void setYearChargeRate(Double yearChargeRate) {
        this.yearChargeRate = yearChargeRate;
    }

    public Double getYearCharge() {
        return yearCharge;
    }

    public void setYearCharge(Double yearCharge) {
        this.yearCharge = yearCharge;
    }

    public Double getNewChargeSum() {
        return newChargeSum;
    }

    public void setNewChargeSum(Double newChargeSum) {
        this.newChargeSum = newChargeSum;
    }

    public Double getNewOrderChargeRate() {
        return newOrderChargeRate;
    }

    public void setNewOrderChargeRate(Double newOrderChargeRate) {
        this.newOrderChargeRate = newOrderChargeRate;
    }

    public Double getNewOrderCharge() {
        return newOrderCharge;
    }

    public void setNewOrderCharge(Double newOrderCharge) {
        this.newOrderCharge = newOrderCharge;
    }

    public Double getBusMotivateChargeRate() {
        return busMotivateChargeRate;
    }

    public void setBusMotivateChargeRate(Double busMotivateChargeRate) {
        this.busMotivateChargeRate = busMotivateChargeRate;
    }

    public Double getBusMotivateCharge() {
        return busMotivateCharge;
    }

    public void setBusMotivateCharge(Double busMotivateCharge) {
        this.busMotivateCharge = busMotivateCharge;
    }

    public Double getSpecialChargeRate() {
        return specialChargeRate;
    }

    public void setSpecialChargeRate(Double specialChargeRate) {
        this.specialChargeRate = specialChargeRate;
    }

    public Double getSpecialCharge() {
        return specialCharge;
    }

    public void setSpecialCharge(Double specialCharge) {
        this.specialCharge = specialCharge;
    }

    public Double getSupPlementChargeRate() {
        return supPlementChargeRate;
    }

    public void setSupPlementChargeRate(Double supPlementChargeRate) {
        this.supPlementChargeRate = supPlementChargeRate;
    }

    public Double getSupPlementCharge() {
        return supPlementCharge;
    }

    public void setSupPlementCharge(Double supPlementCharge) {
        this.supPlementCharge = supPlementCharge;
    }

    public Double getrBasicChargeRate() {
        return rBasicChargeRate;
    }

    public void setrBasicChargeRate(Double rBasicChargeRate) {
        this.rBasicChargeRate = rBasicChargeRate;
    }

    public Double getrBasicCharge() {
        return rBasicCharge;
    }

    public void setrBasicCharge(Double rBasicCharge) {
        this.rBasicCharge = rBasicCharge;
    }

    public Double getrServiceChargeRate() {
        return rServiceChargeRate;
    }

    public void setrServiceChargeRate(Double rServiceChargeRate) {
        this.rServiceChargeRate = rServiceChargeRate;
    }

    public Double getrServiceCharge() {
        return rServiceCharge;
    }

    public void setrServiceCharge(Double rServiceCharge) {
        this.rServiceCharge = rServiceCharge;
    }

    public Double getrChargeSum() {
        return rChargeSum;
    }

    public void setrChargeSum(Double rChargeSum) {
        this.rChargeSum = rChargeSum;
    }

    public Double getConversionChargeRate() {
        return conversionChargeRate;
    }

    public void setConversionChargeRate(Double conversionChargeRate) {
        this.conversionChargeRate = conversionChargeRate;
    }

    public Double getConversionCharge() {
        return conversionCharge;
    }

    public void setConversionCharge(Double conversionCharge) {
        this.conversionCharge = conversionCharge;
    }

    public Double getContinuationRate() {
        return continuationRate;
    }

    public void setContinuationRate(Double continuationRate) {
        this.continuationRate = continuationRate;
    }

    public Double getContinuationChargeRate() {
        return continuationChargeRate;
    }

    public void setContinuationChargeRate(Double continuationChargeRate) {
        this.continuationChargeRate = continuationChargeRate;
    }

    public Double getContinuationCharge() {
        return continuationCharge;
    }

    public void setContinuationCharge(Double continuationCharge) {
        this.continuationCharge = continuationCharge;
    }

    public Double getApMoney() {
        return apMoney;
    }

    public void setApMoney(Double apMoney) {
        this.apMoney = apMoney;
    }

    public Double getCommissionMoney() {
        return commissionMoney;
    }

    public void setCommissionMoney(Double commissionMoney) {
        this.commissionMoney = commissionMoney;
    }

    public String getCommissionNo() {
        return commissionNo;
    }

    public void setCommissionNo(String commissionNo) {
        this.commissionNo = commissionNo;
    }

    public Date getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(Date commissionDate) {
        this.commissionDate = commissionDate;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public Date getAgreementStartDate() {
        return agreementStartDate;
    }

    public void setAgreementStartDate(Date agreementStartDate) {
        this.agreementStartDate = agreementStartDate;
    }

    public Date getAgreementEndDate() {
        return agreementEndDate;
    }

    public void setAgreementEndDate(Date agreementEndDate) {
        this.agreementEndDate = agreementEndDate;
    }

    public String getServiceFeeStatus() {
        return serviceFeeStatus;
    }

    public void setServiceFeeStatus(String serviceFeeStatus) {
        this.serviceFeeStatus = serviceFeeStatus;
    }

    public String getInitialYearDesc() {
        return initialYearDesc;
    }

    public void setInitialYearDesc(String initialYearDesc) {
        this.initialYearDesc = initialYearDesc;
    }

    public String getInitialPeriodDesc() {
        return initialPeriodDesc;
    }

    public void setInitialPeriodDesc(String initialPeriodDesc) {
        this.initialPeriodDesc = initialPeriodDesc;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public void setPolicyHolderName(String policyHolderName) {
        this.policyHolderName = policyHolderName;
    }

    public String getPolicyOrgCode() {
        return policyOrgCode;
    }

    public void setPolicyOrgCode(String policyOrgCode) {
        this.policyOrgCode = policyOrgCode;
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

    public String getPaymentFrequencyDesc() {
        return paymentFrequencyDesc;
    }

    public void setPaymentFrequencyDesc(String paymentFrequencyDesc) {
        this.paymentFrequencyDesc = paymentFrequencyDesc;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getEnterAccDate() {
        return enterAccDate;
    }

    public void setEnterAccDate(Date enterAccDate) {
        this.enterAccDate = enterAccDate;
    }

    public Date getCvaliDate() {
        return cvaliDate;
    }

    public void setCvaliDate(Date cvaliDate) {
        this.cvaliDate = cvaliDate;
    }

    public Double getPrem() {
        return prem;
    }

    public void setPrem(Double prem) {
        this.prem = prem;
    }

    public Double getTransStandMoney() {
        return transStandMoney;
    }

    public void setTransStandMoney(Double transStandMoney) {
        this.transStandMoney = transStandMoney;
    }

    public String getContState() {
        return contState;
    }

    public void setContState(String contState) {
        this.contState = contState;
    }

    public String getBillingPayOrgCode() {
        return billingPayOrgCode;
    }

    public void setBillingPayOrgCode(String billingPayOrgCode) {
        this.billingPayOrgCode = billingPayOrgCode;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getTargetBankAccountNo() {
        return targetBankAccountNo;
    }

    public void setTargetBankAccountNo(String targetBankAccountNo) {
        this.targetBankAccountNo = targetBankAccountNo;
    }

    public String getTargetBankAccountName() {
        return targetBankAccountName;
    }

    public void setTargetBankAccountName(String targetBankAccountName) {
        this.targetBankAccountName = targetBankAccountName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getServiceFeeAmount() {
        return serviceFeeAmount;
    }

    public void setServiceFeeAmount(Double serviceFeeAmount) {
        this.serviceFeeAmount = serviceFeeAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Date getGetAccDate() {
        return getAccDate;
    }

    public void setGetAccDate(Date getAccDate) {
        this.getAccDate = getAccDate;
    }

    public Date getReturnAccDate() {
        return returnAccDate;
    }

    public void setReturnAccDate(Date returnAccDate) {
        this.returnAccDate = returnAccDate;
    }

    public String getReturnFlag() {
        return returnFlag;
    }

    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    public String getAttribute8() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8 = attribute8;
    }

    public String getAttribute9() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9 = attribute9;
    }

    public String getAttribute10() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) {
        this.attribute10 = attribute10;
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

    public String getBeLeftApMoney() {
        return beLeftApMoney;
    }

    public void setBeLeftApMoney(String beLeftApMoney) {
        this.beLeftApMoney = beLeftApMoney;
    }

    public String getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getPerformInfoSriNo() {
        return performInfoSriNo;
    }

    public void setPerformInfoSriNo(String performInfoSriNo) {
        this.performInfoSriNo = performInfoSriNo;
    }

    @Override
    public String toString() {
        return "TraSxfInterfaceDetailsT{" +
                "interfaceId='" + interfaceId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", transactionSource='" + transactionSource + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionNo='" + transactionNo + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", channelDesc='" + channelDesc + '\'' +
                ", settlementTypeCode='" + settlementTypeCode + '\'' +
                ", settlementTypeName='" + settlementTypeName + '\'' +
                ", serviceFeeCategoryCode='" + serviceFeeCategoryCode + '\'' +
                ", serviceFeeCategoryName='" + serviceFeeCategoryName + '\'' +
                ", agencyCode='" + agencyCode + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", agencyCompanyCode='" + agencyCompanyCode + '\'' +
                ", agencyCompanyName='" + agencyCompanyName + '\'' +
                ", fBasicChargeRate=" + fBasicChargeRate +
                ", fBasicCharge=" + fBasicCharge +
                ", monthChargeRate=" + monthChargeRate +
                ", monthCharge=" + monthCharge +
                ", yearChargeRate=" + yearChargeRate +
                ", yearCharge=" + yearCharge +
                ", newChargeSum=" + newChargeSum +
                ", newOrderChargeRate=" + newOrderChargeRate +
                ", newOrderCharge=" + newOrderCharge +
                ", busMotivateChargeRate=" + busMotivateChargeRate +
                ", busMotivateCharge=" + busMotivateCharge +
                ", specialChargeRate=" + specialChargeRate +
                ", specialCharge=" + specialCharge +
                ", supPlementChargeRate=" + supPlementChargeRate +
                ", supPlementCharge=" + supPlementCharge +
                ", rBasicChargeRate=" + rBasicChargeRate +
                ", rBasicCharge=" + rBasicCharge +
                ", rServiceChargeRate=" + rServiceChargeRate +
                ", rServiceCharge=" + rServiceCharge +
                ", rChargeSum=" + rChargeSum +
                ", conversionChargeRate=" + conversionChargeRate +
                ", conversionCharge=" + conversionCharge +
                ", continuationRate=" + continuationRate +
                ", continuationChargeRate=" + continuationChargeRate +
                ", continuationCharge=" + continuationCharge +
                ", apMoney=" + apMoney +
                ", commissionMoney=" + commissionMoney +
                ", commissionNo='" + commissionNo + '\'' +
                ", commissionDate=" + commissionDate +
                ", startMonth='" + startMonth + '\'' +
                ", endMonth='" + endMonth + '\'' +
                ", agreementCode='" + agreementCode + '\'' +
                ", agreementName='" + agreementName + '\'' +
                ", agreementStartDate=" + agreementStartDate +
                ", agreementEndDate=" + agreementEndDate +
                ", serviceFeeStatus='" + serviceFeeStatus + '\'' +
                ", initialYearDesc='" + initialYearDesc + '\'' +
                ", initialPeriodDesc='" + initialPeriodDesc + '\'' +
                ", policyNo='" + policyNo + '\'' +
                ", applicationNo='" + applicationNo + '\'' +
                ", policyHolderName='" + policyHolderName + '\'' +
                ", policyOrgCode='" + policyOrgCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", paymentFrequencyDesc='" + paymentFrequencyDesc + '\'' +
                ", signDate=" + signDate +
                ", enterAccDate=" + enterAccDate +
                ", cvaliDate=" + cvaliDate +
                ", prem=" + prem +
                ", transStandMoney=" + transStandMoney +
                ", contState='" + contState + '\'' +
                ", billingPayOrgCode='" + billingPayOrgCode + '\'' +
                ", bankAccountNo='" + bankAccountNo + '\'' +
                ", bankAccountName='" + bankAccountName + '\'' +
                ", targetBankAccountNo='" + targetBankAccountNo + '\'' +
                ", targetBankAccountName='" + targetBankAccountName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", serviceFeeAmount=" + serviceFeeAmount +
                ", totalAmount=" + totalAmount +
                ", taxAmount=" + taxAmount +
                ", getAccDate=" + getAccDate +
                ", returnAccDate=" + returnAccDate +
                ", returnFlag='" + returnFlag + '\'' +
                ", attribute1='" + attribute1 + '\'' +
                ", attribute2='" + attribute2 + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                ", attribute4='" + attribute4 + '\'' +
                ", attribute5='" + attribute5 + '\'' +
                ", attribute6='" + attribute6 + '\'' +
                ", attribute7='" + attribute7 + '\'' +
                ", attribute8='" + attribute8 + '\'' +
                ", attribute9='" + attribute9 + '\'' +
                ", attribute10='" + attribute10 + '\'' +
                ", batchFlag='" + batchFlag + '\'' +
                ", batchMakeDate=" + batchMakeDate +
                ", batchMakeTime='" + batchMakeTime + '\'' +
                ", batchCreatUser='" + batchCreatUser + '\'' +
                ", batchModifyDate=" + batchModifyDate +
                ", batchModifyTime='" + batchModifyTime + '\'' +
                ", batchModifyUser='" + batchModifyUser + '\'' +
                ", beLeftApMoney='" + beLeftApMoney + '\'' +
                ", chargeMoney='" + chargeMoney + '\'' +
                ", agreementStatus='" + agreementStatus + '\'' +
                ", performInfoSriNo='" + performInfoSriNo + '\'' +
                '}';
    }
}
