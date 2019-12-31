package com.rest.tests;

import static com.rest.utilities.TestUtilities.TruncateLongString;
import static com.rest.utilities.TestUtilities.getTimeInLong;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.test.basetest.BadgeApiBaseTests;
import com.test.services.BadgesServices;

import io.restassured.response.Response;

public class BadgesRecipientsApiTests extends BadgeApiBaseTests {

	public static Logger log = Logger.getLogger(BadgesRecipientsApiTests.class.getName());

	@Test(description = "Verify that badges recipients are having user id attached to them")
	public void VerifyBadgesRecipients() throws Exception {
		// creating query param for page 1 , get all badges recipients from page 1.
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("page", 1);
		Response allBadgesRecipientsResponse = BadgesServices.getAllBadgesReciepients(queryParam);
		List<Object> userIdList = allBadgesRecipientsResponse.getBody().jsonPath().getList("items.user.user_id");
		userIdList.forEach(userId -> System.out.println(userId));
		userIdList.forEach(userId -> assertNotNull(userId, "user id can not be null"));
	}

	@Test(description = "Verify that it is giving badges recipients for given date range")
	public void VerifyBadgesRecipientsForGivenDateRange() throws Exception {
		String startDate = "2019-12-01";
		String toDate = "2019-12-31";

		// creating query param for date range, get all badges recipients with in given
		// date range.
		Map<String, Object> queryParam = new HashMap<String, Object>();

		queryParam.put("fromdate", TruncateLongString(Long.valueOf(getTimeInLong(startDate)).toString(), 10));
		queryParam.put("todate", TruncateLongString(Long.valueOf(getTimeInLong(toDate)).toString(), 10));

		Response allBadgesRecipientsResponse = BadgesServices.getAllBadgesReciepients(queryParam);
		List<Object> userIdList = allBadgesRecipientsResponse.getBody().jsonPath().getList("items.user.user_id");
		userIdList.forEach(userId -> System.out.println(userId));
		userIdList.forEach(userId -> assertNotNull(userId, "user id can not be null"));
	}

	@Test(description = "Verify that it is giving badges recipients for given date range")
	public void VerifyBadgesRecipientsForGivenInvalidDateRange() throws Exception {
		String startDate = "2020-12-01";
		String toDate = "2019-12-01";

		// creating query param for date range, get all badges recipients with in given
		// date range.
		Map<String, Object> queryParam = new HashMap<String, Object>();

		queryParam.put("fromdate", TruncateLongString(Long.valueOf(getTimeInLong(startDate)).toString(), 10));
		queryParam.put("todate", TruncateLongString(Long.valueOf(getTimeInLong(toDate)).toString(), 10));

		Response allBadgesRecipientsResponse = BadgesServices.getAllBadgesReciepients(queryParam);
		List<Object> userIdList = allBadgesRecipientsResponse.getBody().jsonPath().getList("items.user.user_id");
		userIdList.forEach(userId -> System.out.println(userId));
		userIdList.forEach(
				userId -> assertEquals(userIdList.size(), 0, "User ID list is not empty for invalid date range"));
	}
}
