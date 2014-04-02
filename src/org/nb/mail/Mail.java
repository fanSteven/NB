package org.nb.mail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
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

import org.apache.log4j.Logger;
import org.nb.resource.user.UserActivityInfo;
import org.nb.resource.user.UsersResource;
import org.nb.tool.Constant;

public class Mail {
	private static final Logger logger = Logger.getLogger(UsersResource.class);

	public static boolean sendMail(UserActivityInfo activityInfo) {
		return sendMail(activityInfo, true);
	}

	private static Message initMessage(UserActivityInfo activityInfo,
			Boolean isMine) {
		Message message = null;
		NBAuthenticator authenticator = null;
		MailEntity mailEntity = MailEntity.getEmail();
		if (mailEntity.isValidate()) {
			authenticator = new NBAuthenticator(mailEntity.getUserName(),
					mailEntity.getPassword());
		}
		Properties pro = mailEntity.getProperties();

		Session session = Session.getDefaultInstance(pro, authenticator);
		try {
			message = new MimeMessage(session);
			Address addressFrom = new InternetAddress(
					mailEntity.getFromAddress());
			Address addressTo = new InternetAddress(activityInfo.getUserName());
			message.setFrom(addressFrom);
			message.setRecipient(Message.RecipientType.TO, addressTo);

			message.setSubject(mailEntity.getSubject());

			message.setSentDate(new Date());
			String content = "";
			content = mailEntity.getContent()
					+ String.format("u=%s&p=%s", URLEncoder.encode(
							activityInfo.getUserName(), "UTF-8"), URLEncoder
							.encode(new String(activityInfo.getUserPassword()),
									"UTF-8"), "UTF-8");
			content = String.format(Constant.NBSAYHELLO, content);
			if (!isMine) {
				message.setText(content);
			} else {
				BodyPart html = new MimeBodyPart();
				html.setContent(content, "text/html; charset=utf-8");
				Multipart mainPart = new MimeMultipart();
				mainPart.addBodyPart(html);
				message.setContent(mainPart);
			}
		} catch (MessagingException | UnsupportedEncodingException e) {
			logger.error("init message error", e);
		}
		return message;
	}

	public static boolean sendMail(UserActivityInfo activityInfo, Boolean isMine) {

		try {
			Message message = initMessage(activityInfo, isMine);
			if (message == null)
				return false;
			Transport.send(message);
			return true;

		} catch (MessagingException ex) {
			logger.error("send active email error", ex);
		}

		return false;
	}
}
