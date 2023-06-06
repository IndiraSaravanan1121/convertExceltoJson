package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	
	/**
	 * This Method is to read data from properties file
	 * @param elements
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String properties(String elements, String path) throws IOException {
		Properties property = new Properties();
		FileInputStream stream;
		stream = new FileInputStream(path);
		property.load(stream);
		String data = property.getProperty(elements);
		return data;
	}

}
