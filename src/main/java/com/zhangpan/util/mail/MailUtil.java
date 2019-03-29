package com.zhangpan.util.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {
	
	public static boolean sendEmail(MailSenderInfo mailInfo) {
		try {
			// 判断是否需要身份认证
			MyAuthenticator authenticator = null;
			// 如果需要身份认证，则创建一个密码验证器
			if (mailInfo.isValidate()) {
				authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
			}
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session
			Session session = Session.getDefaultInstance(mailInfo.getProps(), authenticator);
			session.setDebug(true); 
			
			MimeMessage mailMessage = createMimeMessage(session, mailInfo);
			
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param session	会话对象
	 * @param username	发送人
	 * @param tos	收件人
	 * @param ccs	抄送人
	 * @param content	邮件内容
	 * @return
	 * @throws MessagingException 
	 * @throws Exception
	 */
	private static MimeMessage createMimeMessage(Session session, MailSenderInfo mailInfo) throws MessagingException {
		MimeMessage message = new MimeMessage(session);// 创建邮件对象
		//发送人邮箱, 显示的昵称,昵称的字符集编码
		message.setFrom(new InternetAddress(mailInfo.getFromAddress()));
		//收件人
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailInfo.getToAddress()));
		//抄送人
		if(mailInfo.getCcAddress() != null) {
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailInfo.getCcAddress()));
		}
		//密送人
		if(mailInfo.getBccAddress() != null) {
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(mailInfo.getBccAddress()));
		}
		//主题
		message.setSubject(mailInfo.getSubject());//邮件主题
		//发送时间
		message.setSentDate(new Date());
		//设置邮件内容
		Multipart multipart = new MimeMultipart();
		// 创建消息部分
		BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(mailInfo.getContent(),"text/html;charset=UTF-8");
        multipart.addBodyPart(messageBodyPart);
        //附件
        List<DataSource> attachments = mailInfo.getAttachments();
        for(DataSource attach : attachments) {
            // 添加附件
            BodyPart msgAttach = new MimeBodyPart();
            // 添加附件的内容
            msgAttach.setDataHandler(new DataHandler(attach));
            // 添加附件的标题
            msgAttach.setFileName(attach.getName());
            multipart.addBodyPart(msgAttach);
        }
        
        message.setContent(multipart);
        
        message.saveChanges();
		return message;
	}
	
	public static void main(String[] args) throws Exception {
	    //获得邮件会话属性
	    Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.host", "smtp.126.com");
        props.put("mail.smtp.auth", true);
        
		final String username="zpzxcvb@126.com";
		final String password="527517062";
		String tos="527517062@qq.com";
		String ccAddress="17691125766@163.com";
		String bccAddress="3412003909@qq.com";
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setProps(props);
		mailInfo.setValidate(true);
		mailInfo.setUserName(username);
		mailInfo.setPassword(password);
		mailInfo.setFromAddress(username);
		mailInfo.setToAddress(tos);//收件人
//		mailInfo.setCcAddress(ccAddress);//抄送
//		mailInfo.setBccAddress(bccAddress);//密送
		mailInfo.setSubject("测试");
		mailInfo.setContent("<h1>hello world！</h1>");
		List<DataSource> attachments = new ArrayList<>();
//        DataSource dataSource1 = new FileDataSource("F:/create.xlsx");
//        attachments.add(dataSource1);
        mailInfo.setAttachments(attachments);//附件
		boolean bool=sendEmail(mailInfo);
		if(bool) {
			System.out.println("Sent message successfully....");
		}else {
			System.out.println("Sent message error....");
		}
	}

}
