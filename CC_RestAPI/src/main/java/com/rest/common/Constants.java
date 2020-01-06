package com.rest.common;

import com.rest.utilities.TestUtilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * @author Alok Shrivastava
 * This class holds all constants which can be used across the framework.
 */
public class Constants {

	public static final String EMPTY_STRING = "";

	// HTTP RESPONSE CODES CONSTANTS
	public static final int HTTP_INTERNAL_SERVER_ERROR = 500;

	public static final int HTTP_NOT_FOUND = 404;

	public static final int HTTP_BAD_REQUEST = 400;

	public static final int HTTP_METHOD_NOT_ALLOWED = 405;

	public static final int HTTP_NOT_ACCEPTABLE = 406;

	public static final int HTTP_OK = 200;

	public static final int HTTP_CREATED = 201;

	public static final int HTTP_NO_CONTENT = 204;	
	
	public static final String BASE_URL = TestUtilities.getBaseUrl();
	
	// Request Specification constants can be created here as per Header Type.it can be flexible and reusable.
	public static final RequestSpecification REQUESTSPECIFICATION = new RequestSpecBuilder()
			.addHeader("Accept", "application/json").addHeader("Content-Type", "application/json").setBaseUri(BASE_URL).build();

	public static final RequestSpecification REQUESTSPECIFICATIONFORXML = new RequestSpecBuilder()
			.addHeader("Accept", "text/xml").addHeader("Content-Type", "text/xml").setBaseUri(BASE_URL).build();
	
	public static final ResponseSpecification RESPONSESPECIFICATION = new ResponseSpecBuilder().expectStatusCode(HTTP_OK).expectContentType(ContentType.JSON).build();
}
