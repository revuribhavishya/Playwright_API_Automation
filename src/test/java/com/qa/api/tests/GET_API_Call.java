package com.qa.api.tests;

import java.io.IOException;
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

public class GET_API_Call {
	Playwright pw;
	APIRequest req;
	APIRequestContext reqContext;
	
	@BeforeTest
	public void setup() {
		System.out.println("setup");
		 pw=Playwright.create();
		 req=pw.request();
		 reqContext=req.newContext();
	}
	@Test
	public void getSpecificUserApiTest() {
		APIResponse apiResponse =reqContext.get("https://gorest.co.in/public/v2/users",
				RequestOptions.create()
				.setQueryParam("id", 7935906)
				.setQueryParam("status", "inactive")
				);
		System.out.println(" ---- print api Status code ----" );
		int statusCode=apiResponse.status();
		System.out.println("response status code: " + statusCode );
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(apiResponse.ok(), true);
		
		System.out.println(" ---- print api Status text ----" );
		String statusResText = apiResponse.statusText();
		System.out.println("response status text: " + statusResText );
		
		System.out.println(" ---- pprint api response with plain text ----" );
		System.out.println(apiResponse.text());
	}
	
	@Test
	public void getUsersApiTest() throws IOException {

		APIResponse apiResponse =reqContext.get("https://gorest.co.in/public/v2/users");
		
		System.out.println(" ---- print api Status code ----" );
		int statusCode=apiResponse.status();
		System.out.println("response status code: " + statusCode );
		Assert.assertEquals(statusCode, 200);
		
		System.out.println(" ---- print api Status text ----" );
		String statusResText = apiResponse.statusText();
		System.out.println("response status text: " + statusResText );
		
		System.out.println(apiResponse.body()); // the response will be [B@21d03963 .. which is byte array 
		//to get the actual json response , add Jackson-databind in pom.xml
		
		ObjectMapper objMapper = new ObjectMapper(); 
		JsonNode jsonResponse=	objMapper.readTree(apiResponse.body());
		String jsonPrettyResponse = jsonResponse.toPrettyString();
		System.out.println(jsonPrettyResponse);
		
		System.out.println(" ---- print api url ----" );
		System.out.println(" api url is : " + apiResponse.url());
		
		System.out.println(" ---- print api Response headers -----" );
		
		Map<String, String> headerMap=apiResponse.headers();
		System.out.println("headers" + headerMap);
		Assert.assertEquals(headerMap.get("content-type"), "application/json; charset=utf-8");
		Assert.assertEquals(headerMap.get("x-download-options"), "noopen");
	
	}
	
	@AfterTest
	public void teardown() {
		pw.close();
	}

}
