package com.lotus.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by liuzhiqiang on 2018/5/10.
 */
public class EmailUtils {

    @Autowired
    private static JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private static String username; //读取配置文件中的参数

    /**
     * 发送简单文本邮件
     * @param setTo
     */
    public static void sendSimpleMail(String setTo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setTo);
        message.setTo(setTo); //自己给自己发送邮件
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        mailSender.send(message);
    }


    /**
     * html格式邮件
     * @throws MessagingException
     */
    public static void sendHtmlMail(String setTo){
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setSubject("标题：发送Html内容");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @param setTo
     * @param path
     */
    public static void sendAttachmentsMail(String setTo,String path){
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper=new MimeMessageHelper(message,true);
            helper.setFrom(username);
            helper.setTo("3436833404@qq.com");
            helper.setSubject("带附件的邮件");
            helper.setText("带附件的邮件");
            FileSystemResource file = new FileSystemResource(new File(path));
            //加入邮件
            helper.addAttachment("图片.jpg", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
