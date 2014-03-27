package org.nb.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class NBAuthenticator extends Authenticator {
	private String userName;
	private String password;

	public NBAuthenticator() {
		super();
	}

	public NBAuthenticator(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {

		return new PasswordAuthentication(userName, password);
	}

}
