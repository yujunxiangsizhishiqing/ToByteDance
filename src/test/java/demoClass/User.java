package demoClass;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private String id;
    private String name;
    private String pwd;
    private String roleNames;
    private String roleName;
    private Date createdatetime;
    private Date modifydatetime;
    private String jobNumber;
    private String phone;
    private String userStates;
    private String email;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdatetimeStart;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdatetimeEnd;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifydatetimeStart;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date modifydatetimeEnd;
    private Date pwdExpirydate;
    private String deptIdList;
    private String employeEnum;
    private String memployeStatus;
    private String usort;
    private String businessStatus;
    private String accountStatus;//0：首次登录；1：账户正常；2：账户被锁
    private String roleIds;
    private String ip;
    private String loginLocation;
    private String browser;
    private String sessionId;
    private Date loginDate;
    private String loginTime;
    private BigDecimal errTimes;
    private Date accountUnlockTime;
    private String historyPwd;

    private int page;
    private int rows;



    public User() {
    }

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public User(String id, String name, String pwd, Date createdatetime, Date modifydatetime, String jobNumber, String phone, String userStates,
                String email) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.createdatetime = createdatetime;
        this.modifydatetime = modifydatetime;
        this.jobNumber = jobNumber;
        this.phone = phone;
        this.userStates = userStates;
        this.email = email;
    }

    public User(String id, String name, String pwd, String roleNames, String roleName, Date createdatetime, Date modifydatetime, String jobNumber,
                String phone, String userStates, String email, Date createdatetimeStart, Date createdatetimeEnd, Date modifydatetimeStart,
                Date modifydatetimeEnd, Date pwdExpirydate, String deptIdList, String employeEnum, String memployeStatus, String usort,
                String businessStatus, String accountStatus, String roleIds, String ip, String loginLocation, String browser, String sessionId,
                Date loginDate, String loginTime, BigDecimal errTimes, Date accountUnlockTime, String historyPwd, int page, int rows) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.roleNames = roleNames;
        this.roleName = roleName;
        this.createdatetime = createdatetime;
        this.modifydatetime = modifydatetime;
        this.jobNumber = jobNumber;
        this.phone = phone;
        this.userStates = userStates;
        this.email = email;
        this.createdatetimeStart = createdatetimeStart;
        this.createdatetimeEnd = createdatetimeEnd;
        this.modifydatetimeStart = modifydatetimeStart;
        this.modifydatetimeEnd = modifydatetimeEnd;
        this.pwdExpirydate = pwdExpirydate;
        this.deptIdList = deptIdList;
        this.employeEnum = employeEnum;
        this.memployeStatus = memployeStatus;
        this.usort = usort;
        this.businessStatus = businessStatus;
        this.accountStatus = accountStatus;
        this.roleIds = roleIds;
        this.ip = ip;
        this.loginLocation = loginLocation;
        this.browser = browser;
        this.sessionId = sessionId;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
        this.errTimes = errTimes;
        this.accountUnlockTime = accountUnlockTime;
        this.historyPwd = historyPwd;
        this.page = page;
        this.rows = rows;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public Date getModifydatetime() {
        return modifydatetime;
    }

    public void setModifydatetime(Date modifydatetime) {
        this.modifydatetime = modifydatetime;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserStates() {
        return userStates;
    }

    public void setUserStates(String userStates) {
        this.userStates = userStates;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedatetimeStart() {
        return createdatetimeStart;
    }

    public void setCreatedatetimeStart(Date createdatetimeStart) {
        this.createdatetimeStart = createdatetimeStart;
    }

    public Date getCreatedatetimeEnd() {
        return createdatetimeEnd;
    }

    public void setCreatedatetimeEnd(Date createdatetimeEnd) {
        this.createdatetimeEnd = createdatetimeEnd;
    }

    public Date getModifydatetimeStart() {
        return modifydatetimeStart;
    }

    public void setModifydatetimeStart(Date modifydatetimeStart) {
        this.modifydatetimeStart = modifydatetimeStart;
    }

    public Date getModifydatetimeEnd() {
        return modifydatetimeEnd;
    }

    public void setModifydatetimeEnd(Date modifydatetimeEnd) {
        this.modifydatetimeEnd = modifydatetimeEnd;
    }

    public Date getPwdExpirydate() {
        return pwdExpirydate;
    }

    public void setPwdExpirydate(Date pwdExpirydate) {
        this.pwdExpirydate = pwdExpirydate;
    }

    public String getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(String deptIdList) {
        this.deptIdList = deptIdList;
    }

    public String getEmployeEnum() {
        return employeEnum;
    }

    public void setEmployeEnum(String employeEnum) {
        this.employeEnum = employeEnum;
    }

    public String getMemployeStatus() {
        return memployeStatus;
    }

    public void setMemployeStatus(String memployeStatus) {
        this.memployeStatus = memployeStatus;
    }

    public String getUsort() {
        return usort;
    }

    public void setUsort(String usort) {
        this.usort = usort;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public BigDecimal getErrTimes() {
        return errTimes;
    }

    public void setErrTimes(BigDecimal errTimes) {
        this.errTimes = errTimes;
    }

    public Date getAccountUnlockTime() {
        return accountUnlockTime;
    }

    public void setAccountUnlockTime(Date accountUnlockTime) {
        this.accountUnlockTime = accountUnlockTime;
    }

    public String getHistoryPwd() {
        return historyPwd;
    }

    public void setHistoryPwd(String historyPwd) {
        this.historyPwd = historyPwd;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", roleNames='" + roleNames + '\'' +
                ", roleName='" + roleName + '\'' +
                ", createdatetime=" + createdatetime +
                ", modifydatetime=" + modifydatetime +
                ", jobNumber='" + jobNumber + '\'' +
                ", phone='" + phone + '\'' +
                ", userStates='" + userStates + '\'' +
                ", email='" + email + '\'' +
                ", createdatetimeStart=" + createdatetimeStart +
                ", createdatetimeEnd=" + createdatetimeEnd +
                ", modifydatetimeStart=" + modifydatetimeStart +
                ", modifydatetimeEnd=" + modifydatetimeEnd +
                ", pwdExpirydate=" + pwdExpirydate +
                ", deptIdList='" + deptIdList + '\'' +
                ", employeEnum='" + employeEnum + '\'' +
                ", memployeStatus='" + memployeStatus + '\'' +
                ", usort='" + usort + '\'' +
                ", businessStatus='" + businessStatus + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", roleIds='" + roleIds + '\'' +
                ", ip='" + ip + '\'' +
                ", loginLocation='" + loginLocation + '\'' +
                ", browser='" + browser + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", loginDate=" + loginDate +
                ", loginTime='" + loginTime + '\'' +
                ", errTimes=" + errTimes +
                ", accountUnlockTime=" + accountUnlockTime +
                ", historyPwd='" + historyPwd + '\'' +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
