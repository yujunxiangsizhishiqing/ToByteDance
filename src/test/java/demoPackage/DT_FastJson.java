package demoPackage;

import com.alibaba.fastjson2.JSONObject;

public class DT_FastJson {

    public static void main(String[] args) {
        String jsonStr = "{\"transactionSource\":\"CMS\",\"transactionType\":\"02\"}";


        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.get("transactionSource"));
    }





}
