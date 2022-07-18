package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	public Properties prop;
	//this method is used to load the properties from config.properties file
	public Properties init_prop() {
	try {
	prop=new Properties();
	FileInputStream ip=new FileInputStream("src/test/resources/config/config.properties");
	prop.load(ip);
	System.out.println("file   "   +   ip);
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	return prop;
	}
	}

	
