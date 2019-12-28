package com.assignment.util;

import com.assignment.base.BaseTest;

public class Constants {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static long MAX_TIMEOUT = 30;

	public static final String userName = BaseTest.properties.getProperty("userName");

	public static final String encodedPassword = BaseTest.properties.getProperty("password");

	public static final String url = BaseTest.properties.getProperty("url");

	public static final String browser = BaseTest.properties.getProperty("browser");

}
