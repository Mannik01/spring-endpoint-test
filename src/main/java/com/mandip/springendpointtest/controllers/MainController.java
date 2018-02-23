package com.mandip.springendpointtest.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mandip.springendpointtest.services.ContentService;


@EnableCircuitBreaker
@RestController
public class MainController {
	
	private ContentService contentService;
	
	
	@Autowired
	public MainController(ContentService contentService) {
		this.contentService = contentService;
	}
	
	@GetMapping(value="/{endpointType:.+}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String getJSONData(@PathVariable final String endpointType) throws JSONException {
		final JSONObject jsonComponent = contentService.getEndpointContent(endpointType);
		
//		try {
//			System.out.println(jsonComponent.get("text"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return jsonComponent.toString();
		
	}
	
}
