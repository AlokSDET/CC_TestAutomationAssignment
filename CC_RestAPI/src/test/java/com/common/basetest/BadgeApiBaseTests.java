package com.test.basetest;

import static com.rest.common.Constants.HTTP_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.common.collect.Ordering;
import com.test.services.BadgesServices;

import io.restassured.response.Response;

public class BadgeApiBaseTests {

	public static Logger log = Logger.getLogger(BadgeApiBaseTests.class.getName());

	protected void testBadgesForGivenPageSize(int pageSize, int expectedStatusCode) {
		// creating query param for giving page size
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("pagesize", pageSize);
		Response badgesResponse = BadgesServices.getAllBadgesWithQueryParam(queryParam);
		assertEquals(badgesResponse.getStatusCode(), expectedStatusCode);
		if (expectedStatusCode == HTTP_OK) {
			JSONObject badgesResponseJsonObj = new JSONObject(badgesResponse.getBody().asString());
			JSONArray badgesResponseJsonArray = badgesResponseJsonObj.getJSONArray("items");
			assertEquals(badgesResponseJsonArray.length(), pageSize);
		}
	}

	protected void testBadgesShouldDisplayInGivenOrderForGivenSort(String sort, String order, String sortNode,
			int expectedResponseCode) {
		// creating query param for making badges list in given order for given sort
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("sort", sort);
		queryParam.put("order", order);

		Response badgesResponseSortedWithGivenSort = BadgesServices.getAllBadgesWithQueryParam(queryParam);
		assertEquals(badgesResponseSortedWithGivenSort.getStatusCode(), expectedResponseCode);

		List<Map<String, Object>> allBagdes = badgesResponseSortedWithGivenSort.getBody().jsonPath().getList("items");

		List<String> badge_type_list = new ArrayList<String>();

		for (Map<String, Object> badgeMap : allBagdes) {
			badge_type_list.add(badgeMap.get(sortNode).toString());
		}

		badge_type_list.stream().forEach(i -> System.out.println(i));
		if (order.contentEquals("asc")) {
			assertTrue(Ordering.natural().isOrdered(badge_type_list),
					"Badges " + sort + "  is sorted in ascending order");
		} else if (order.contentEquals("desc")) {
			/* Sorting in decreasing order */
			Collections.sort(badge_type_list);
			assertTrue(Ordering.natural().isOrdered(badge_type_list),
					"Badges " + sort + "  is sorted in descending order");
		}
	}

	protected void testBadgesById(String ids, Map<String, Object> queryParam) {

		String[] idsArray = ids.split(";");

		Response allBadgesResponseForIds = BadgesServices.getAllBadgesByIds(ids, queryParam);
		assertEquals(allBadgesResponseForIds.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponseForIds.getBody().jsonPath().getList("items");
		// use of logging from java util
		log.log(Level.INFO, String.valueOf(allBagdes.size()));

		assertEquals(allBagdes.size(), idsArray.length, "No of badges are incorrect ");
		List<Integer> allBadgeIdList = new ArrayList<Integer>();
		allBagdes.forEach(badge -> allBadgeIdList.add(Integer.parseInt(badge.get("badge_id").toString())));
		for (int i = 0; i < idsArray.length; i++) {
			assertTrue(allBadgeIdList.contains(Integer.parseInt(idsArray[i])), "Badge id is not matching");
		}
	}
}
