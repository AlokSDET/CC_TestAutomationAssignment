package com.common.services;

import static com.rest.common.Constants.BASE_URL;
import static com.rest.common.Constants.HTTP_OK;
import static com.rest.common.Constants.REQUESTSPECIFICATION;
import static com.rest.common.Constants.RESPONSESPECIFICATION;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONException;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class BadgesServices {

	public static Response getAllBadges() throws JSONException {
		// keeping site query parameter as this is mandatory.
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").when().get("badges/");
	}

	public static Response getAllBadgesWithQueryParam(Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get("badges/");

	}

	public static Response getAllBadgesReciepients(Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get("badges/recipients");
	}

	// Use of Response specification and extract method.
	public static Response getAllBadgesForTags(Map<String, Object> queryParam) throws JSONException {
		return (Response) given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get("badges/tags").then().spec(RESPONSESPECIFICATION).extract();
	}

	//Use of path and query parameter, use of validation of status code in request call it self.
	public static Response getAllBadgesByIds(String id, Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).pathParam("id", id).queryParam("site", "stackoverflow").queryParams(queryParam).expect().statusCode(HTTP_OK).when()
				.get("badges/{id}");
	}

}
