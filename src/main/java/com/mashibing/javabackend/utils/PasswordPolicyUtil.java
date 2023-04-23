package com.mashibing.javabackend.utils;

import com.sun.istack.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 密码策略
 *
 * 密码策略要求：
 * 1、长度：至少8位
 * 2、复杂度：字符、数字、大小写字母组成
 * 3、有效期：90天
 * 4、试错次数：用户连续认证失败次数超过5次，必须锁定该用户使用的账号15分钟。
 * 5、密码历史：不能使用过去曾使用过的3个密码
 * 6、用户首次登录需要强制修改密码
 * */
public class PasswordPolicyUtil {

    public final static String DEFAULT_REGEX = ".*[a-zA-Z]*[0-9]*";//正则表达式
    public final static int EFFECTIVE_DURATION = 90;//有效期
    public final static int MAX_PWD_LEN = 20;//最大长度
    public final static int MIN_PWD_LEN = 8;//最短长度
    public final static int MAX_LOGIN_PWD_ERR_TIMES = 5;//试错次数
    public final static int MAX_LIMIT_PWD_UESD_TIMES = 3;//密码历史:不能使用过去曾使用过的N个密码

    private static final String UPPER_REGEX = "[A-Z]+";//大写字母验证
    private static final String LOWER_REGEX = "[a-z]+";//小写字母验证
    private static final String DIGIT_REGEX = "[0-9]+";//数字验证
    private static final String CHARATER_REGEX = "[`~!@#$%^&*()_\\-+=<>?:\"{}|,.\\/;'\\[\\]]+";//字符验证

    public final static int LOCK_TIME=15;//账户锁定时间
    /**
     * @apiNote 最大长度校验
     * @return true:长度符合规范 false：长度不符合规范
     * */
    public boolean validateMaxLen(@NotNull String password,int/*long*/ maxLen){
        return password.length()<=maxLen;
    }

    /**
     * @apiNote 最大长度校验
     * @return true:长度符合规范 false：长度不符合规范
     * */
    public boolean validateMaxLen(@NotNull String password){
        return validateMaxLen(password,MAX_PWD_LEN);
    }

    /**
     * @apiNote 最短长度校验
     * @return true:长度符合规范 false：长度不符合规范
     * */
    public boolean validateMinLen(@NotNull String password,int/*long*/ minLen){
        return password.length()>=minLen;
    }

    /**
     * @apiNote 最短长度校验
     * @return true:长度符合规范 false：长度不符合规范
     * */
    public boolean validateMinLen(@NotNull String password){
        return validateMinLen(password,MIN_PWD_LEN);
    }

    /**
     * @apiNote 复杂度校验
     * @return String 通过校验返回null；未通过校验则返回string的错误提示
     * */
    public String complexityCheck2(@NotNull String password){
        return checkPattern(password);
    }

    /**
     * @apiNote 复杂度校验
     * @return true：密码复杂度合适 false：密码复杂度不符合要求
     * */
    public boolean complexityCheck(@NotNull String password,String regex){
        return password.matches(regex);
    }

    /**
     * @apiNote 复杂度校验
     * @return true：密码复杂度合适 false：密码复杂度不符合要求
     * */
    public boolean complexityCheck(@NotNull String password){
        //default:符、数字、大小写字母组成
        return complexityCheck(password,DEFAULT_REGEX);
    }

    /**
     * @apiNote 有效期验证
     * @param currDate
     * @return true：密码还在有效期内 false：密码已失效
     * */
    public boolean timeValidityVerification(Date currDate,Date pwdExpirydate/*过期时间*/){
        return differentDays(currDate,pwdExpirydate)>=0;
    }

    /**
     * @apiNote 有效期验证：n天
     * @param date
     * @return true：密码还在有效期内 false：密码已失效
     * */
    public boolean timeValidityVerification(Date date,int effectiveDuration){
        return differentDays(date,new Date())<=effectiveDuration;
    }

    /**
     * @apiNote 有效期验证：90天
     * @return true：密码还在有效期内 false：密码已失效
     * */
    public boolean timeValidityVerification(Date date){
        return timeValidityVerification(date,EFFECTIVE_DURATION);
    }

    /**
     * @apiNote 有效期验证：n天
     * @param dateStr 日期的字符串类型
     * @param format 日期转换格式
     * @return true：密码还在有效期内 false：密码已失效
     * */
    public boolean timeValidityVerification(@NotNull String dateStr ,@NotNull String format,int effectiveDuration){
        try {
            Date date = new SimpleDateFormat(format).parse(dateStr);
            return timeValidityVerification(date,effectiveDuration);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 有效期验证：90天
     * @param dateStr 日期的字符串类型 2022-01-01 2022/02/02 ...
     * @param format 日期转换格式 "yyyy-MM-dd" "yyyy/MM/dd" ...
     * @return true：密码还在有效期内 false：密码已失效
     * */
    public boolean timeValidityVerification(@NotNull String dateStr,@NotNull String format){
        return timeValidityVerification(dateStr,format,EFFECTIVE_DURATION);
    }

    /**
     * @apiNote 连续错误次数校验
     * @return true：用户连续输入密码错误次数超过上限，账户需要被锁定 false：用户可以登录
     * */
    public boolean errorTimesValidate(int curErrTimes,int maxErrTimes){
        return curErrTimes>maxErrTimes;
    }

    /**
     * @apiNote 连续错误次数校验
     * @return true：用户连续输入密码错误次数超过上限，账户需要被锁定 false：用户可以登录
     * */
    public boolean errorTimesValidate(int curErrTimes){
        return errorTimesValidate(curErrTimes,MAX_LOGIN_PWD_ERR_TIMES);
    }

    /**
     * @apiNote 密码轨迹校验，曾使用的密码可能不可以再次使用。
     * @return true:新密码是历史密码 false：新密码是历史密码
     * */
    public boolean newPasswordValidate(@NotNull String newPassword,@NotNull String historyPassword){
        return historyPassword.equals(newPassword);
    }

    /**
     * @apiNote 历史密码校验，用户不能使用前n个历史密码
     * */
    public boolean newPasswordValidate(@NotNull String newpassword, int historyPwdLimit,@NotNull List<String> historyPwdList){
        boolean result = true;
        historyPwdLimit = (historyPwdList.size()<historyPwdLimit)?historyPwdList.size():historyPwdLimit;
        for(int index = 0;index<historyPwdLimit;index++){
            result = result && newPasswordValidate(newpassword,historyPwdList.get(index));
        }
        return result;
    }

    /**
     * @apiNote 历史密码校验，用户不能使用前3个历史密码
     * */
    public boolean newPasswordValidate(@NotNull String newpassword,@NotNull List<String> historyPwdList){
        return newPasswordValidate(newpassword,MAX_LIMIT_PWD_UESD_TIMES,historyPwdList);
    }

    /**
     * @apiNote 用户是否首次登录
     * @return true:是首次登录 false：不是首次登录
     * */
    public boolean isFirstLogin(boolean isFirstLogin){
        return isFirstLogin;
    }

    /**
     * @apiNote 获取过期时间
     * 基于当前系统时间为起始时间，计算过期日期
     * */
    public Date getExpirydate(int ExpirydateType/*Calendar.MINUTE....*/,int Expirydate){
        Calendar cal = Calendar.getInstance();
        final String formatStr = "yyyy-MM-dd HH:mm:ss";
        cal.add(ExpirydateType,Expirydate);
        return cal.getTime();
    }

    /**
     * @apiNote 计算日期之间间隔的天数
     * */
    private static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) {
            //同一年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++){
                if(i%4==0 && i%100!=0 || i%400==0){
                    //闰年
                    timeDistance += 366;
                }else{
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        } else {// 不同年
            //System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }

    private String checkPattern(String password){

        Matcher upperMatcher=Pattern.compile(UPPER_REGEX).matcher(password);
        if(!upperMatcher.find()){
            return "密码需包含大写字母！";
        }

        Matcher lowerMatcher=Pattern.compile(LOWER_REGEX).matcher(password);
        if(!lowerMatcher.find()){
            return "密码需包含小写字母！";
        }

        Matcher digitMatcher=Pattern.compile(DIGIT_REGEX).matcher(password);
        if(!digitMatcher.find()){
            return "密码需包含数字！";
        }

        Matcher characterMatcher=Pattern.compile(CHARATER_REGEX).matcher(password);
        if(!characterMatcher.find()){
            return "密码需包含字符！";
        }
        return null;//校验通过
    }
}

