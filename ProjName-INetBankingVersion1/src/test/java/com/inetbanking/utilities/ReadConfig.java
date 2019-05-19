package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig
{
	Properties properties;
	
	//creating an constructor for reading configuration file data
	public ReadConfig()
	{
		//path where the file is stored from which we will be reading variables 
		File fileSrc = new File("./Configuration/config.properties");
		
		try
		{
			FileInputStream fileRead = new FileInputStream(fileSrc);//reading the file
			properties = new Properties();//creating properties object for reading variables(i.e Datas)
			properties.load(fileRead);//complete file will be loaded at run time
		}
		catch(Exception e)
		{
			System.out.println("Exception is : "+e.getMessage());
		}
	}
	
	//Now to read values form properties file we will be creating method for each variables
	
	public String getChromePath()
	{
		String chromePath =properties.getProperty("chromePath");// case sensitive
		return chromePath;
	}
	
	public String getFirefoxPath()
	{
		return properties.getProperty("firefoxPath");
	}
	
	public String getIEPath()
	{
		return properties.getProperty("iePath");
	}
	
	public String getApplicationURL()
	{
		return	properties.getProperty("baseURL");
	}
	
	public String getUserID()
	{
		return properties.getProperty("userID");
	}
	
	public String getPassword()
	{
		return properties.getProperty("password");
	}
	
}
