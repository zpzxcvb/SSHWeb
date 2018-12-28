package com.zhangpan.mail;

import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.zhangpan.util.mail.MailSenderInfo;

/**
 * @author zhangpan
 * @date 2018年12月28日
 */
@Component
public class EmailTool {
    
    private Logger logger = LoggerFactory.getLogger(EmailTool.class);
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    public boolean sendSimpleMail(MailSenderInfo mailInfo){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailInfo.getFromAddress());
            helper.setTo(mailInfo.getToAddress());
            helper.setCc(mailInfo.getCcAddress());
            helper.setBcc(mailInfo.getBccAddress());
            helper.setSubject(mailInfo.getSubject());
            helper.setText(mailInfo.getContent(), true);
            //附件
            List<DataSource> attachments = mailInfo.getAttachments();
            if(attachments != null) {
                for(DataSource attach : attachments) {
                    // 添加附件
                    helper.addAttachment(attach.getName(), attach);
                }
            }
            javaMailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败", e.getMessage());
            return false;
        }
    }
}
