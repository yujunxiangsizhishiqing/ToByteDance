package demoPackage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @apiNote 邮件发送
 * */
public class SendEmail {
    //private static final Logger logger = Logger.getLogger(SendEmail.class.getName());
    public static void main(String[] args) {
        //logger.info("123");
        SendEmail sendEmail = new SendEmail();
        //sendEmail.send1();
        sendEmail.send2();
    }

    /**
     * @apiNote qq邮箱
     * */
    private void send1(){

        String host = "smtp.qq.com"; // SMTP服务器地址
        String username = "970894639@qq.com"; // SMTP服务器用户名
        String password = "kixewnlhjktkbgag"; // SMTP服务器密码

        String to = "970894639@qq.com"; // 收件人电子邮件地址
        String from = "970894639@qq.com"; // 发件人电子邮件地址
        String subject = "Email Subject"; // 邮件主题
        String messageText = "This is an email message."; // 邮件正文

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            System.out.println("Email sent successfully!");
            //logger.info("Email sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    /**
     * @apiNote 企业邮箱
     * */
    private void send2(){

        String host = "smtp.exmail.qq.com"; // SMTP服务器地址
        String username = "wangxu05@sinosoft.com.cn"; // SMTP服务器用户名
        String password = "Wx970623."; // SMTP服务器密码

        String to = "wangxu05@sinosoft.com.cn"; // 收件人电子邮件地址
        String from = "wangxu05@sinosoft.com.cn"; // 发件人电子邮件地址
        String subject = "Email Subject"; // 邮件主题
        String messageText = "This is an email message."; // 邮件正文

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageText);

            Transport.send(message);
            System.out.println("Email sent successfully!");
            //logger.info("Email sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}


/**============================================================================================*/

//package com.jeeplus.sbptapi.upload.task;
//
//
//        import cn.hutool.extra.spring.SpringUtil;
//        import com.jeeplus.quartz.domain.ScheduleJob;
//        import com.jeeplus.quartz.domain.Task;
//        import com.jeeplus.sbptapi.jkapi.service.SbptDataUploadApiService;
//        import com.jeeplus.sbptapi.upload.service.dto.UploadResultsDTO;
//        import com.jeeplus.sys.domain.SysConfig;
//        import com.jeeplus.sys.service.SysConfigService;
//        import lombok.extern.slf4j.Slf4j;
//        import org.apache.poi.ss.usermodel.Row;
//        import org.apache.poi.ss.usermodel.Sheet;
//        import org.apache.poi.ss.usermodel.Workbook;
//        import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//        import org.quartz.DisallowConcurrentExecution;
//        import org.springframework.beans.factory.annotation.Autowired;
//
//        import javax.mail.*;
//        import javax.mail.internet.InternetAddress;
//        import javax.mail.internet.MimeBodyPart;
//        import javax.mail.internet.MimeMessage;
//        import javax.mail.internet.MimeMultipart;
//        import java.io.File;
//        import java.io.FileOutputStream;
//        import java.io.IOException;
//        import java.text.SimpleDateFormat;
//        import java.util.List;
//        import java.util.Properties;
//
///**
// * 定时通过邮箱反馈上传结果
// */
//@Slf4j
//@DisallowConcurrentExecution
//public class DataUploadResultsTask extends Task {
//    @Autowired
//    private SbptDataUploadApiService sbptDataUploadApiService;
//
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//
//    /**
//     * 30分钟执行一次
//     * 定时执行上传接口
//     */
////    @Scheduled(cron="0 1 * * * ?")
//    @Override
//    public void run(ScheduleJob scheduleJob) {
//        try {
//
//            /**1、获取excel数据清单*/
//            //获取写入excel的数据
//            List<UploadResultsDTO> uploadResults = sbptDataUploadApiService.getUploadResults();
//
//            /**2、新建excel文件并将上述数据写入其中*/
//            // 创建Excel文件
//            String filePath = "上报结果报告.xlsx";
//            createExcel(uploadResults, filePath);
//
//            /**3、发送带附件的邮件*/
//            // 发送邮件
//            String toEmail = scheduleJob.getRemarks(); // 收件人
//            String subject = "上报结果报告"; // 邮件标题
//            sendEmailWithAttachment(toEmail, subject, filePath);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
//
//
//    /**
//     * 获取结果数据
//     * @param dataList  结果数据
//     * @param filePath  文件信息
//     * @throws IOException
//     */
//    public static void createExcel(List<UploadResultsDTO> dataList, String filePath) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Data");
//
//        // 创建表头
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("GS");
//        headerRow.createCell(1).setCellValue("RQ");
//        headerRow.createCell(2).setCellValue("FWZT");
//        // ...
//
//        // 写入数据
//        int rowNum = 1;
//        for (UploadResultsDTO data : dataList) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(data.getGs());
//            row.createCell(1).setCellValue(data.getRq());
//            row.createCell(2).setCellValue(data.getFwzt());
//        }
//
//        // 保存Excel文件
//        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
//            workbook.write(outputStream);
//        }
//    }
//
//
//    /**
//     *  发送邮件
//     * @param toEmail           收件人
//     * @param subject           邮件标题
//     * @param attachmentPath    附件
//     */
//    public static void sendEmailWithAttachment(String toEmail, String subject, String attachmentPath) {
//        SysConfig config = SpringUtil.getBean(SysConfigService.class).getById("1");
//        String from = config.getMailName(); // 发件人
//        String host = config.getSmtp(); // 邮箱服务器地址
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        properties.put("mail.smtp.auth", "true");
//
//
//        Session session = Session.getDefaultInstance(properties, new Authenticator() {
//            @Override
//            public PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, config.getMailPassword());
//            }
//        });
//
//
//        File attachment = new File(attachmentPath);
//
//        try {
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setFrom(new InternetAddress(from));
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
//            mimeMessage.setSubject(subject);
//
//            // 创建邮件附件
//            MimeBodyPart attachmentPart = new MimeBodyPart();
//            attachmentPart.attachFile(attachment);
//
//            // 创建多部分消息
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(attachmentPart);
//
//            // 设置邮件内容
//            mimeMessage.setContent(multipart);
//
//
//            Transport.send(mimeMessage);
//            System.out.println("发送邮件成功");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 删除临时文件
//            attachment.delete();
//        }
//    }
//
//}