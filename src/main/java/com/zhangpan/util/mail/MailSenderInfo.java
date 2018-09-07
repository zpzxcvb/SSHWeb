package com.zhangpan.util.mail;

import java.io.Serializable;
import java.util.List;
/**
 * 发送邮件需要使用的基本信息 
 * @author Test
 * 修改时间：2015年7月27日
 * @version 2.0
 */
import java.util.Properties;

import javax.activation.DataSource;

public class MailSenderInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	// 发送邮件的服务器的session协议等
	private Properties props;
	// 邮件发送者的地址
	private String fromAddress;
	// 邮件接收者的地址,多个用','隔开
	private String toAddress;
	// 邮件抄送者的地址,多个用','隔开
	private String ccAddress;
	// 邮件密送者的地址,多个用','隔开
	private String bccAddress;
	// 登陆邮件发送服务器的用户名和密码
	private String userName;
	private String password;
	// 是否需要身份验证
	private boolean validate = false;
	// 邮件主题
	private String subject;
	// 邮件的文本内容
	private String content;
	// 邮件附件的文件名
	private List<DataSource> attachments;

	public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public List<DataSource> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<DataSource> attachments) {
        this.attachments = attachments;
    }

}
