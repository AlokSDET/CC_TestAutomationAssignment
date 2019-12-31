package com.test.services;

import static com.rest.common.Constants.BASE_URL;
import static com.rest.common.Constants.REQUESTSPECIFICATION;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONException;

import io.restassured.response.Response;

public class BadgesServices {

	public static Response getAllBadges() throws JSONException {
		// keeping site query parameter as this is mandatory.
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").when().get(BASE_URL + "badges/");
	}

	public static Response getAllBadgesWithQueryParam(Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get(BASE_URL + "badges/");

	}

	public static Response getAllBadgesReciepients(Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get(BASE_URL + "badges/recipients");
	}

	public static Response getAllBadgesForTags(Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get(BASE_URL + "badges/tags");
	}

	public static Response getAllBadgesByIds(String ids, Map<String, Object> queryParam) throws JSONException {
		return given().spec(REQUESTSPECIFICATION).queryParam("site", "stackoverflow").queryParams(queryParam).when()
				.get(BASE_URL + "badges/" + ids);
	}

}
