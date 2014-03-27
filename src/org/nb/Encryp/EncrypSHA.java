package org.nb.Encryp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypSHA implements Encrypt {

	@Override
	public byte[] encrypt(String password) {
		byte[] resultBytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] srcBytes = password.getBytes();
			md.update(srcBytes);
			resultBytes = md.digest();
		} catch (NoSuchAlgorithmException e) {
		}

		return resultBytes;
	}
}
