package org.nb.mail;

import java.util.Properties;

import org.nb.tool.Constant;

public class MailEntity {
	private String mailSmtpHost;
	private String mailSmtpPort;
	private String fromAddress;
	private String toAddress;
	private String userName;
	private String password;
	private boolean validate = false;
	private String subject;
	private String content = Constant.VERIFICATION_URL;
	private String[] attachFileNames;
	private static MailEntity mail;
	static {
		initEntity();
	}

	private static void initEntity() {
		mail = new MailEntity();
		mail.setValidate(true);
		mail.setMailSmtpHost(MailProperties.getProperty("mailSmtpHost"));
		mail.setMailSmtpPort(MailProperties.getProperty("mailSmtpPort", "25"));
		mail.setFromAddress(MailProperties.getProperty("fromAddress"));
		mail.setUserName(MailProperties.getProperty("userName"));
		mail.setPassword(MailProperties.getProperty("password"));
	}

	public static MailEntity getEmail() {
		return mail;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailSmtpHost);
		p.put("mail.smtp.port", this.mailSmtpPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}

	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public String getMailSmtpPort() {
		return mailSmtpPort;
	}

	public void setMailSmtpPort(String mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
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

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
}
