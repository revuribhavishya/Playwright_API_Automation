package com.qa.api.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class POST_CreateUser {
	Playwright pw;
	APIRequest req;
	APIRequestContext reqContext;
	static String email;
	
	
	@BeforeTest
	public void setup() {
		System.out.println("setup");
		 pw=Playwright.create();
		 req=pw.request();
		 reqContext=req.newContext();
	}
	
	@Test
	public void createUser() throws IOException {
		Map<String , Object> hmap = new HashMap<>();
		hmap.put("name" , "Bhavishya123");
		hmap.put("email" , getRandomEmail());
		hmap.put("gender" , "female");
		hmap.put("status" , "active");
		
		
	APIResponse postRes = reqContext.post( "https://gorest.co.in/public/v2/users", 
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
				// beader is must while passing authorization
				.setHeader("Authorization", "Bearer 6efda88ad65f9ba8405dfeb54a9834469371fefa846355f93f979ee85234e9f7")
				.setData(hmap)); // passing teh body here
		
		System.out.println("Status code " + postRes.status());
		Assert.assertEquals(postRes.status(), 201);
		Assert.assertEquals(postRes.statusText(), "Created");
		
		System.out.println(" ---- pprint api response with plain text ----" );
		System.out.println("Response Text : " + postRes.text());
		
		ObjectMapper objM = new ObjectMapper();
		
		JsonNode Response= objM.readTree(postRes.body());
		Response.toPrettyString();
		String userId = Response.get("id").asText();
		System.out.println("userId is : " + userId);
		
		System.out.println("--- Verify the created user from GET call ---");
		APIResponse getRes=reqContext.get("https://gorest.co.in/public/v2/users" ,
				RequestOptions.create()
				.setHeader("Authorization", "Bearer 6efda88ad65f9ba8405dfeb54a9834469371fefa846355f93f979ee85234e9f7"));
		System.out.println("status code is : " + getRes.status());
		Assert.assertEquals(getRes.status(), 200);
		
	}
	
	public static String getRandomEmail() {
	 email = "Bhavishya123" + System.currentTimeMillis() + "@gmail.com";
	return email;
}
	
	@AfterTest
	public void teardown() {
		pw.close();
	}


}
