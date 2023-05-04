package demoPackage;

import com.alibaba.fastjson2.JSONObject;

import java.io.*;

public class DT_IOStream {

    //private static final Logger logger = Logger.getLogger("DemoTest_IOStream");

    public DT_IOStream(){

    }

    public static void main(String[] args) throws IOException {
        String jsonpath="D:\\Job\\ZKR\\Data\\ChangShengRenShou\\wangxu\\SMB.txt";
        ReadGeojsonFile(jsonpath);


        //String jsonpath="D:\\Job\\ZKR\\Data\\ChangShengRenShou\\wangxu\\channel_7.txt";
//        String jsonpath="D:\\Job\\ZKR\\Data\\ChangShengRenShou\\wangxu\\SMB20230201.txt";
//        File file=new File(jsonpath);
//        FileInputStream fileInputStream = new FileInputStream(file);
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
//        BufferedReader bufReader = new BufferedReader(inputStreamReader);
//        try {
//            String  line="";
//            StringBuffer sb=new StringBuffer();
//            while ((line=bufReader.readLine())!=null){
//                sb.append(line);
//            }
//            String sbreplace = sb.toString().replace(" ", "");
//            //JSONObject jsonObj = JSONObject.parseObject(sbreplace);
//            JSONArray jArr = JSON.parseArray(sbreplace);
//            System.out.println("size: " + jArr.size());
//            for (int i = 0 ;i<jArr.size();i++){
//                System.out.println("第"+i+"个： "/* + jArr.get(i)*/);
//                JSONObject o = (JSONObject)jArr.get(i);
//                System.out.println(o.get("manageCom"));
//            }
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    public static void ReadGeojsonFile(String jsonpath) throws IOException {
        //读取txt文件流
        File file=new File(jsonpath);
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
        BufferedReader bufReader = new BufferedReader(inputStreamReader);

        try {
            String  line="";
            //读取每行内容
            StringBuffer sb=new StringBuffer();
            while ((line=bufReader.readLine())!=null){
                sb.append(line);
                System.out.println(line);
            }
            //去除空格
            String sbreplace = sb.toString().replace(" ", "");
            System.out.println(sbreplace);
            //转换成为JSONObject对象
            JSONObject jsonObj = JSONObject.parseObject(sbreplace);
            System.out.println(jsonObj.get("transactionType"));//01
            jsonObj.put("transactionType","02");
            System.out.println(jsonObj.get("transactionType"));
//            System.out.println(jsonObj.get("TRANSACTION_SOURCE"));
//            System.out.println(jsonObj.get("TRANSACTION_TYPE"));
//            System.out.println(jsonObj.get("CHANNEL_CODE"));
//            System.out.println(jsonObj.get("CHANNEL_DESC"));
//            System.out.println(jsonObj.get("SETTLEMENT_TYPE_CODE"));
//            System.out.println(jsonObj.get("SETTLEMENT_TYPE_NAME"));
//            System.out.println(jsonObj.get("SERVICE_FEE_CATEGORY_CODE"));
//            System.out.println(jsonObj.get("SERVICE_FEE_CATEGORY_NAME"));
//            System.out.println(jsonObj.get("TRANSACTION_DATE"));
//            System.out.println(jsonObj.get("AGENCY_CODE"));
//            System.out.println(jsonObj.get("AGENCY_NAME"));
//            System.out.println(jsonObj.get("AGENCY_COMPANY_CODE"));
//            System.out.println(jsonObj.get("AGENCY_COMPANY_NAME"));
//            System.out.println(jsonObj.get("AP_MONEY"));
//            System.out.println(jsonObj.get("COMMISSION_MONEY"));
//            System.out.println(jsonObj.get("COMMISSION_NO"));
//            System.out.println(jsonObj.get("COMMISSION_DATE"));
//            System.out.println(jsonObj.get("AGREEMENT_CODE"));
//            System.out.println(jsonObj.get("AGREEMENT_NAME"));
//            System.out.println(jsonObj.get("AGREEMENT_STARTDATE"));
//            System.out.println(jsonObj.get("AGREEMENT_ENDDATE"));
//            System.out.println(jsonObj.get("SERVICE_FEE_STATUS"));
//            System.out.println(jsonObj.get("INITIAL_YEAR_DESC"));
//            System.out.println(jsonObj.get("POLICY_NO"));
//            System.out.println(jsonObj.get("APPLICATION_NO"));
//            System.out.println(jsonObj.get("POLICY_HOLDER_NAME"));
//            System.out.println(jsonObj.get("POLICY_ORG_CODE"));
//            System.out.println(jsonObj.get("PRODUCT_CODE"));
//            System.out.println(jsonObj.get("PRODUCT_NAME"));
//            System.out.println(jsonObj.get("PAYMENT_FREQUENCY_DESC"));
//            System.out.println(jsonObj.get("SIGNDATE"));
//            System.out.println(jsonObj.get("ENTERACCDATE"));
//            System.out.println(jsonObj.get("CVALIDATE"));
//            System.out.println(jsonObj.get("PREM"));
//            System.out.println(jsonObj.get("CONTSTATE"));
//            System.out.println(jsonObj.get("BILLING_PAY_ORG_CODE"));
//            System.out.println(jsonObj.get("BANK_ACCOUNT_NO"));
//            System.out.println(jsonObj.get("BANK_ACCOUNT_NAME"));
//            System.out.println(jsonObj.get("TARGET_BANK_ACCOUNT_NO"));
//            System.out.println(jsonObj.get("TARGET_BANK_ACCOUNT_NAME"));
//            System.out.println(jsonObj.get("CURRENCY_CODE"));
//            System.out.println(jsonObj.get("SERVICE_FEE_AMOUNT"));
//            System.out.println(jsonObj.get("TOTAL_AMOUNT"));
//            System.out.println(jsonObj.get("TAX_AMOUNT"));
//            System.out.println(jsonObj.get("GETACCDATE"));
//            System.out.println(jsonObj.get("RETURNACCDATE"));
//            System.out.println(jsonObj.get("RETURNFLAG"));
//            System.out.println(jsonObj.get("ATTRIBUTE1"));
//            System.out.println(jsonObj.get("ATTRIBUTE2"));
//            System.out.println(jsonObj.get("ATTRIBUTE3"));
//            System.out.println(jsonObj.get("ATTRIBUTE4"));
//            System.out.println(jsonObj.get("ATTRIBUTE5"));
//            System.out.println(jsonObj.get("ATTRIBUTE6"));
//            System.out.println(jsonObj.get("ATTRIBUTE7"));
//            System.out.println(jsonObj.get("ATTRIBUTE8"));
//            System.out.println(jsonObj.get("ATTRIBUTE9"));
//            System.out.println(jsonObj.get("ATTRIBUTE10"));
//            System.out.println(jsonObj.get("BATCH_FLAG"));
//            System.out.println(jsonObj.get("BATCH_MAKE_DATE"));
//            System.out.println(jsonObj.get("BATCH_MAKE_TIME"));
//            System.out.println(jsonObj.get("BATCH_CREAT_USER"));
//            System.out.println(jsonObj.get("BATCH_MODIFY_DATE"));
//            System.out.println(jsonObj.get("BATCH_MODIFY_TIME"));
//            System.out.println(jsonObj.get("BATCH_MODIFY_USER"));
//            JSONArray geometry =(JSONArray) jsonObj.get("array");
//            System.out.println(geometry.get(0));
//            System.out.println(geometry.get(1));
//            System.out.println(geometry.get(2));
        } catch (IOException  e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        bufReader.close();
    }

}
