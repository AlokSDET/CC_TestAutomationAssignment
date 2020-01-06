package com.tests.badgesTests;

import static com.rest.common.Constants.HTTP_OK;
import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.common.basetest.BadgeApiBaseTests;
import com.common.services.BadgesServices;

import io.restassured.response.Response;

public class BadgeIdApiTests extends BadgeApiBaseTests {

	public static Logger log = Logger.getLogger(BadgeIdApiTests.class.getName());

	@DataProvider(name = "rest assured test data")
	public Object[][] testData() {
		return new Object[][] { { "1" }, { "1;2" }, { "1;2;3;4;5;6;7;8;9;10;11;12;13;14;15" } };
	}

	@Test(description = "Verify that badges are displaying only for given Id")
	public void getBadgesById() throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();

		List<String> idsList = new LinkedList<String>(Arrays.asList("1", "1;2", "1;2;3;4;5;6;7;8;9;10;11;12;13;14;15"));

		idsList.forEach(ids -> testBadgesById(ids, queryParam));
	}

	@Test(description = "Verify that badges are displaying only for given Id", dataProvider = "rest assured test data")
	public void getBadgesByIdUsingDataProvider(String id) throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		testBadgesById(id, queryParam);
	}

	@Test(description = "Verify that badges are displaying only for given Id once even though same ids "
			+ "are passed multiple times")
	public void getBadgesBySameId() throws Exception {
		Map<String, Object> queryParam = new HashMap<String, Object>();
		String ids = "1;1";

		Response allBadgesResponseForIds = BadgesServices.getAllBadgesByIds(ids, queryParam);
		assertEquals(allBadgesResponseForIds.getStatusCode(), HTTP_OK);
		List<Map<String, Object>> allBagdes = allBadgesResponseForIds.getBody().jsonPath().getList("items");
		// use of logging from java util
		log.log(Level.INFO, String.valueOf(allBagdes.size()));

		assertEquals(allBagdes.size(), 1, "No of badges are incorrect ");
	}
}
