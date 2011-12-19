package com.aqua.test;

import java.io.File;
import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author xinxuan.wang
 * 
 */
public class MailTest {
    static ApplicationContext ctx;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void sendMail() {
        JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hzhzhf@163.com");
        message.setTo("hzhzhf@163.com");
        message.setCc("chouchoucat@163.com");
        message.setSubject("测试Spring发送邮件");
        message.setText("测试Spring发送邮件内容");
        
        System.out.println(message);
        sender.send(message);
    }

    @Test
    public void sendMimeMail() throws MailException, FileNotFoundException, MessagingException {
        JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");
        
        File testFile = new File("/home/wangxinxuan/php.ini.zip");
        
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");// true代表可以发送附件
        messageHelper.setFrom("hzhzhf@163.com");
        messageHelper.setTo("hzhzhf@163.com");
        messageHelper.setCc("chouchoucat@163.com");
        messageHelper.setSubject("测试Spring发送邮件");
        messageHelper.setText("<h1>测试Spring发送邮件内容</h1>", true);// true代表发送的是Html内容
        messageHelper.addAttachment("php.ini.zip", testFile);
        
        sender.send(message);
    }
    
}
