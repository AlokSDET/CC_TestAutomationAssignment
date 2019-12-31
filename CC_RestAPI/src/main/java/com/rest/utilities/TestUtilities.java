package com.rest.utilities;

import static com.rest.utilities.FileUtilities.getPropertyValue;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestUtilities {

	public static String accessToken;

	public static String code;

	/**
	 * i have registered a APP and got client id, client secret . Below method would
	 * generate access token which can be used to access the stack exchange API.
	 * Code is generated in generateCode method.Hence called generateCode method
	 * before generating access token.
	 */
	public static String generateAccessToken() {
		generateCode();
		Response res = RestAssured.given().formParam("client_id", "16937")
				.formParam("client_secret", "qzVBQ*CGHk4CnBGPqN8Irw((")
				.formParam("redirect_uri", "https://stackexchange.com/oauth/login_success").formParam("code", code)
				.contentType("application/x-www-form-urlencoded; charset=UTF-8").expect().statusCode(200).when()
				.post("https://stackoverflow.com/oauth/access_token");

		// need to write code to extract the accessToken from redirect URL and
		// assign the value to accessToken variable which can be used to access the
		// stack exchange API.

		accessToken = res.getBody().jsonPath().get("access_token");
		System.out.println(accessToken);
		System.out.println(res.getBody().asString());
		return accessToken;
	}

	/**
	 * This method generates code based on client id and redirect_uri by hitting get
	 * API.
	 * 
	 */
	public static void generateCode() {
		Response res = RestAssured.given().queryParam("client_id", "16937")
				.queryParam("redirect_uri", "https://stackexchange.com/oauth/login_success").redirects().follow(false)
				.redirects().allowCircular(true).expect().statusCode(302).when().get("https://stackoverflow.com/oauth");

		// need to write code to extract the redirected URL
		String redirectURI = res.getHeader("redirectURI");

		Response resp2 = RestAssured.given().expect().statusCode(200).when().get(redirectURI);

		// Now write the code to extract the code which will be used to generate access
		// token
		code = resp2.getHeader("code");
		System.out.println(code);
	}

	public static String getBaseUrl() {
		return getPropertyValue("BASE_URL");
	}

	public static long getTimeInLong(String dateInStr) throws Exception {

		Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateInStr);

		System.out.println(date.getTime());

		return date.getTime();

	}

	public static String TruncateLongString(String str, int maxLength) {
		if (str.equals("") || str == null)
			return str;
		return str.substring(0, Math.min(str.length(), maxLength));
	}

}
