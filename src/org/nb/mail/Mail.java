package org.nb.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.nb.resource.user.UserActivityInfo;
import org.nb.resource.user.UsersResource;

public class Mail {
	public static Logger logger = Logger.getLogger(UsersResource.class);

	public static boolean sendMail(UserActivityInfo activityInfo) {
		return sendMail(activityInfo, true);
	}

	public static boolean sendMail(UserActivityInfo activityInfo, Boolean isMine) {
		NBAuthenticator authenticator = null;
		MailEntity mailEntity = MailEntity.getEmail();
		if (mailEntity.isValidate()) {
			authenticator = new NBAuthenticator(mailEntity.getUserName(),
					mailEntity.getPassword());
		}
		Properties pro = mailEntity.getProperties();
		Session session = Session.getDefaultInstance(pro, authenticator);
		try {
			Message message = new MimeMessage(session);
			try {
				Address addressFrom = new InternetAddress(
						mailEntity.getFromAddress());
				Address addressTo = new InternetAddress(
						mailEntity.getToAddress());
				message.setFrom(addressFrom);
				message.setRecipient(Message.RecipientType.TO, addressTo);
				message.setSubject(mailEntity.getSubject());
				message.setSentDate(new Date());
				if (!isMine) {
					message.setText(mailEntity.getContent());
				} else {
					BodyPart html = new MimeBodyPart();
					html.setContent(
							String.format(mailEntity.getContent(),
									activityInfo.getUserName(),
									activityInfo.getUserPassword()),
							"text/html; charset=utf-8");
					Multipart mainPart = new MimeMultipart();
					mainPart.addBodyPart(html);
					message.setContent(mainPart);
				}
				Transport.send(message);
				return true;
			} catch (AddressException e) {
				logger.error("send active email error", e);
			}

		} catch (MessagingException ex) {
			logger.error("send active email error", ex);
		}

		return false;
	}
}
