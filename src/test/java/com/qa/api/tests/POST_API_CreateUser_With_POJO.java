package com.qa.api.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.data.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class POST_API_CreateUser_With_POJO {
	

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

		User userObj = new User("bhavi11445561", getRandomEmail() , "female", "active"); // this is the JSON in body
		
	APIResponse postRes = reqContext.post( "https://gorest.co.in/public/v2/users", 
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
				// beader is must while passing authorization
				.setHeader("Authorization", "Bearer 6efda88ad65f9ba8405dfeb54a9834469371fefa846355f93f979ee85234e9f7")
				.setData(userObj)); // passing the body here.. NO NEED to SERIALIZE UNLIKE RESTASSURED
		
		System.out.println("Status code " + postRes.status());
		Assert.assertEquals(postRes.status(), 201);
		Assert.assertEquals(postRes.statusText(), "Created");
		
		System.out.println(" ---- pprint api response with plain text ----" );
		String responseInText = postRes.text();
		System.out.println("Response Text : " + responseInText);
		
		
		//DeSerialization - converting JOSN response to POJO
		ObjectMapper objMap = new ObjectMapper();
		User  actUser=objMap.readValue(responseInText, User.class); // use readValue() for POJO deserialization. This is the response
		System.out.println(actUser.getEmail());
		
		Assert.assertEquals(actUser.getName(), userObj.getName());
		Assert.assertEquals(actUser.getEmail(), userObj.getEmail());
		Assert.assertEquals(actUser.getStatus(), userObj.getStatus());
		Assert.assertEquals(actUser.getGender(), userObj.getGender());
		Assert.assertNotNull(actUser.getId());

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
