package com.rest.tests;

import static com.rest.common.Constants.HTTP_BAD_REQUEST;
import static com.rest.common.Constants.HTTP_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.test.basetest.BadgeApiBaseTests;
import com.test.services.BadgesServices;

import io.restassured.response.Response;

public class BadgeApiTests extends BadgeApiBaseTests {

	public static Logger log = Logger.getLogger(BadgeApiTests.class.getName());

	@Test(description = "Verify that badges list is not empty", priority = 1)
	public void getListOfAllBadges() throws Exception {
		Response allBadgesResponse = BadgesServices.getAllBadges();
		assertEquals(allBadgesResponse.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponse.getBody().jsonPath().getList("items");
		// use of log4j
		log.log(Level.INFO, String.valueOf(allBagdes.size()));
		assertNotNull(allBagdes.size());
	}

	@Test(description = "Verify that badges list is displaying in Ascending order for Badge Name")
	public void VerifyBadgesDisplaysInAscOrderByName() throws Exception {
		testBadgesShouldDisplayInGivenOrderForGivenSort("name", "asc", "name", HTTP_OK);
	}

	@Test(description = "Verify that badges list is displaying in Descending  order for Badge Name")
	public void VerifyBadgesDisplaysInDescOrderByName() throws Exception {
		testBadgesShouldDisplayInGivenOrderForGivenSort("name", "desc", "name", HTTP_OK);
	}

	@Test(description = "Verify that badges list is displaying in ascending  order for Badge Rank")
	public void VerifyBadgesDisplaysInAscOrderByRank() throws Exception {
		testBadgesShouldDisplayInGivenOrderForGivenSort("rank", "asc", "rank", HTTP_OK);
	}

	@Test(description = "Verify that badges list is displaying in Descending  order for Badge Rank")
	public void VerifyBadgesDisplaysInDescOrderByRank() throws Exception {
		testBadgesShouldDisplayInGivenOrderForGivenSort("rank", "desc", "rank", HTTP_OK);
	}

	@Test(description = "Verify that badges list is displaying in ascending  order for Badge type")
	public void VerifyBadgesDisplaysInAscOrderByType() throws Exception {
		testBadgesShouldDisplayInGivenOrderForGivenSort("type", "asc", "badge_type", HTTP_OK);
	}

	@Test(description = "Verify that badges list is displaying in Descending  order for Badge type")
	public void VerifyBadgesDisplaysInDescOrderByType() throws Exception {
		testBadgesShouldDisplayInGivenOrderForGivenSort("type", "desc", "badge_type", HTTP_OK);
	}

	@Test(description = "Verify that badges are displaying as per given valid page size, Also verify"
			+ "that throwing 400 for invalid page size")
	public void VerifyBadgesForValidPageSizeParameter() throws Exception {
		// Valid page size, should display the given no of badges per page
		testBadgesForGivenPageSize(5, HTTP_OK);

		// Max page size by boundary value analysis, should display the given no of
		// badges per page
		testBadgesForGivenPageSize(100, HTTP_OK);
	}

	@Test(description = "verify that it is throwing 400 error for invalid page size")
	public void VerifyBadgesForInvalidPageSizeParameter() throws Exception {
		// Foe invalid page size, should throw bad request
		testBadgesForGivenPageSize(101, HTTP_BAD_REQUEST);
	}

}
