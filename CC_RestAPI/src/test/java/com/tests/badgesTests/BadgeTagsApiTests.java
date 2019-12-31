package com.rest.tests;

import static com.rest.common.Constants.HTTP_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.test.basetest.BadgeApiBaseTests;
import com.test.services.BadgesServices;

import io.restassured.response.Response;

public class BadgeTagsApiTests extends BadgeApiBaseTests {

	public static Logger log = Logger.getLogger(BadgeTagsApiTests.class.getName());

	@Test(description = "Verify that only gold badges list is displaying when max = gold")
	public void getListOfAllGoldBadges() throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("sort", "rank");
		queryParam.put("max", "gold");
		Response allBadgesResponseForGoldTags = BadgesServices.getAllBadgesForTags(queryParam);
		assertEquals(allBadgesResponseForGoldTags.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponseForGoldTags.getBody().jsonPath().getList("items");
		// use of logging from java util
		log.log(Level.INFO, String.valueOf(allBagdes.size()));
		List<Object> rankList = new ArrayList<Object>();
		for (Map<String, Object> badge : allBagdes) {
			rankList.add(badge.get("rank"));
		}
		rankList.forEach(rank -> assertEquals(rank, "gold", "rank is not gold when only set max = gold"));
	}

	@Test(description = "Verify that only Silver badges list is displaying when max = silver and min = silver")
	public void getListOfAllSilverBadges() throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("sort", "rank");
		queryParam.put("max", "silver");
		queryParam.put("min", "silver");
		queryParam.put("pagesize", "10");
		Response allBadgesResponseForSilverTags = BadgesServices.getAllBadgesForTags(queryParam);
		assertEquals(allBadgesResponseForSilverTags.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponseForSilverTags.getBody().jsonPath().getList("items");
		// use of logging from java util
		log.log(Level.INFO, String.valueOf(allBagdes.size()));
		List<Object> rankList = new ArrayList<Object>();
		for (Map<String, Object> badge : allBagdes) {
			rankList.add(badge.get("rank"));
		}
		rankList.forEach(
				rank -> assertEquals(rank, "silver", "rank is not silver when only set max = silver and min = silver"));
	}

	@Test(description = "Verify that only bronze badges list is displaying when min = bronze")
	public void getListOfAllBronzeBadges() throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("sort", "rank");
		queryParam.put("min", "bronze");
		Response allBadgesResponseForBronzeTags = BadgesServices.getAllBadgesForTags(queryParam);
		assertEquals(allBadgesResponseForBronzeTags.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponseForBronzeTags.getBody().jsonPath().getList("items");
		// use of logging from java util
		log.log(Level.INFO, String.valueOf(allBagdes.size()));
		List<Object> rankList = new ArrayList<Object>();
		for (Map<String, Object> badge : allBagdes) {
			rankList.add(badge.get("rank"));
		}
		rankList.forEach(rank -> assertEquals(rank, "bronze", "rank is not gold when only set min = bronze"));
	}

	@Test(description = "Verify that all badges list(bronze, silver , gold) is displaying when min = silver")
	public void getListOfAllBadges() throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("sort", "rank");
		queryParam.put("min", "silver");
		Response allBadgesResponseForTags = BadgesServices.getAllBadgesForTags(queryParam);
		assertEquals(allBadgesResponseForTags.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponseForTags.getBody().jsonPath().getList("items");
		// use of logging from java util
		log.log(Level.INFO, String.valueOf(allBagdes.size()));
		List<Object> rankList = new ArrayList<Object>();
		for (Map<String, Object> badge : allBagdes) {
			rankList.add(badge.get("rank"));
		}

		assertTrue(rankList.contains("silver"), "Silver badges does not present in list");
		assertTrue(rankList.contains("gold"), "Silver badges does not present in list");
		assertTrue(rankList.contains("bronze"), "Silver badges does not present in list");

	}

}
