package com.rest.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtilities {

	public static final String PropertyFilePath = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\config\\application.Properties";

	public static Properties properties;

	/**
	 * This method will return the value of provided key from application.properties
	 * file.
	 * 
	 * @param propertyKey : property key for which value needs to be return
	 * @return: Returns the value of given property key.
	 */
	public static String getPropertyValue(String propertyKey) {
		String propertyValue = null;
		try {
			// Getting value of given property key from property file.
			propertyValue = loadPropertyFile().getProperty(propertyKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}

	public static Properties loadPropertyFile() throws IOException {
		try {
			properties = new Properties();
			File file = new File(PropertyFilePath);
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
