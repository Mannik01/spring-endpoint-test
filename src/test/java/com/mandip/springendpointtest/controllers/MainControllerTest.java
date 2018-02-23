package com.mandip.springendpointtest.controllers;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.mandip.springendpointtest.services.ContentService;


@RunWith(SpringRunner.class)
public class MainControllerTest{
	
	final Optional<String> emptyOptString = Optional.ofNullable(null);
	
	@Mock
	private ContentService contentService;
	
	@InjectMocks
	MainController mainController;
	
	@Test
	public void validateEndpointIsRunning() {
		String check = "<p>helloooo&nbsp;</p>\r\n";
		given().when().get("http://neimancloud-dev.adobecqms.net/content/neimanmarcus/mandipwork.layout.json")
		.then()
		.body("text.sling.resourceType", equalTo("foundation/components/text"))
		.statusCode(200);
	}
	
	@Ignore
	public void validateGetJSONDataWithExpectedResponse() throws JSONException {
		
		final JSONObject expectedTextJsonObject = new JSONObject().put("texVal", "helloooo");
		when(contentService.getEndpointContent(anyString())).thenReturn(expectedTextJsonObject);
		
		String actualString = mainController.getJSONData("text");
		
		assertEquals(expectedTextJsonObject.toString(), actualString);
	}
	

}
