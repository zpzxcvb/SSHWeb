package com.zhangpan.util;

import java.io.UnsupportedEncodingException;
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
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {

	/**
	 * @param session	会话对象
	 * @param username	发送人
	 * @param tos	收件人
	 * @param ccs	抄送人
	 * @param content	邮件内容
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session,String address,List<String> tos,List<String> ccs, String content) throws Exception{
		MimeMessage message = new MimeMessage(session);// 创建邮件对象
		InternetAddress addr=new InternetAddress(address);//发送人邮箱, 显示的昵称,昵称的字符集编码
		message.setFrom(addr);
		for(String to : tos){
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//收件
		}
		for(String cc : ccs){
			message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cc));//抄送
		}
//		message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));//密送
		message.setSubject("这是一个测试发送邮件程序");//邮件主题
		
		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();// 创建消息部分
//        messageBodyPart.setContent(content,"text/html;charset=UTF-8");//邮件内容
        //图片部分
        String htmlText = "<H1>Hello</H1><img src='cid:image'>";
        messageBodyPart.setContent(htmlText, "text/html");
        multipart.addBodyPart(messageBodyPart);
        
        messageBodyPart = new MimeBodyPart();
        String filename = "F:\\icon.png";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        //附件部分
        /*messageBodyPart = new MimeBodyPart();
        String filename = "F:\\1.txt";
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);*/
        
		message.setSentDate(new Date());
		return message;
	}
	
	public static void checkMail(){
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		final String username="zpzxcvb@126.com";
		final String password="527517062";
		String personal="张攀";
		String myEmailSMTPHost = "smtp.126.com";
		String to="527517062@qq.com";
		List<String> tos=new ArrayList<String>();
		tos.add(to);
		List<String> ccs=new ArrayList<String>();
//		ccs.add("zhangpan31584@billjc.com");
		String content="<h>hello world</h>";
		
		Properties props = new Properties();//连接邮件服务器的参数配置
		props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.host", myEmailSMTPHost);        // 发件人的邮箱的 SMTP 服务器地址
//        props.setProperty("mail.smtp.auth", "true");            // 请求认证
        
		Session session = Session.getDefaultInstance(props);//创建会话对象
		session.setDebug(true);// 设置为debug模式, 可以查看详细的发送 log
		
		MimeMessage message = createMimeMessage(session, username, tos, ccs, content);
		
        
        
        
		
		Transport transport = session.getTransport();
		transport.connect(username, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		System.out.println("Sent message successfully....");
	}

}
