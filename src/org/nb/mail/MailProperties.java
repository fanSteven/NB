package org.nb.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MailProperties {
	private static final Logger logger = Logger.getLogger(MailProperties.class);
	private static Properties properties = null;

	public static String getProperty(String name) {
		return properties.getProperty(name);
	}

	public static String getProperty(String name, String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	static {
		loadProperties();
	}

	private static void loadProperties() {
		InputStream is = null;
		Exception error = null;
		String path="../../";
		try {
			path = MailProperties.class.getClassLoader().getResource("")
					.toURI().getPath();
		} catch (URISyntaxException e1) {
			error = e1;
		}
		File propertiesFile = new File(path + "mail.properties");
		try {
			is = new FileInputStream(propertiesFile);
		} catch (FileNotFoundException e) {
			error = e;
		}
		if (is != null) {
			properties = new Properties();
			try {
				properties.load(is);
				is.close();
			} catch (Exception e) {
				error = e;
			}
		}

		if (is == null || error != null) {
			logger.warn("Failed to load mail.properties", error);
			properties = new Properties();
		}

		Enumeration<?> enumeration = properties.propertyNames();
		while (enumeration.hasMoreElements()) {
			String name = (String) enumeration.nextElement();
			String value = properties.getProperty(name);
			if (value != null) {
				System.setProperty(name, value);
			}

		}
	}
}
