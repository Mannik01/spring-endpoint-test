package com.mandip.springendpointtest.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class ContentService {
	
	
	private final String baseURL = "http://neimancloud-dev.adobecqms.net/content/neimanmarcus/";
	
	private final RestTemplate restTemplt = new RestTemplate();
	
	@HystrixCommand(fallbackMethod = "getFallbackContent")
	public JSONObject getEndpointContent(String endpointType) throws JSONException {
		final String destination = baseURL.concat(endpointType);
		
		ResponseEntity<String> response = restTemplt.getForEntity(destination, String.class);
		
		return new JSONObject(response.getBody());
		
	}
	
	
	public JSONObject getFallbackContent(String endpointType) throws JSONException {
		
		JSONObject defaultValue = new JSONObject();
		
		defaultValue.put("text", "No content received");
		
		return defaultValue;
	}
	
	

}
