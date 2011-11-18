package com.aqua.pingtai.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OpenPropertiesFile {
	
	private Properties config;

	private static final OpenPropertiesFile cfg = new OpenPropertiesFile();

	private OpenPropertiesFile() {
		config = new Properties();
		InputStream is = null;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream("/sysConfig.properties");
			config.load(is);
		} catch (Exception exception) {
			System.out.println("Can't read the properties file. ");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException exception) {
			}
		}
	}

	public static OpenPropertiesFile getInstance() {
		return cfg;
	}

	public Properties getMailPropertiesSet() {
		return config;
	}

	public String getValue(String key) {
		return config.getProperty(key).trim();
	}

	public void setValue(String key, String value) {
		config.setProperty(key.trim(), value.trim());
	}
}
