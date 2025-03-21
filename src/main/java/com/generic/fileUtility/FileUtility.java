package com.generic.fileUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility {
	
public String getDataFromPropertiesFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./config_app_data/hms.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);
		
		return  data;
	}

}
