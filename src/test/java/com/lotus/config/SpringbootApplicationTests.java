package com.lotus.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String username; //读取配置文件中的参数
	@Test
	public void contextLoads() {
	}

	@Test
	public void testSendHtml() {
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(username);
			helper.setTo("3436833404@qq.com");
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

	@Test
	public void sendAttachmentsMail(){
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper=new MimeMessageHelper(message,true);
			helper.setFrom(username);
			helper.setTo("3436833404@qq.com");
			helper.setSubject("带附件的邮件");
			helper.setText("带附件的邮件");
			FileSystemResource file = new FileSystemResource(new File("C:/Users/liuzhiqiang/Desktop/供应材料产品-供应商-材巴巴_files/12.jpg"));
			//加入邮件
			helper.addAttachment("图片.jpg", file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

}
