package com.qa.api.tests;

import org.testng.annotations.Test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

public class API_DisposeTest {
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
	public void disposetest() {
		APIResponse apiResponse =reqContext.get("https://gorest.co.in/public/v2/users");
		
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
		
		apiResponse.dispose(); // will dispnse only response body.. status code, url, status text will remain same
		System.out.println(" ---- pprint api response with plain text after dispose ----" );
		System.out.println(apiResponse.text());
		int statusCode1 = apiResponse.status();  
		System.out.println("response after dispose " + statusCode1);
		System.out.println("response after dispose " + apiResponse.url());
		System.out.println("response after dispose " + apiResponse.statusText());
	}
	
	@AfterTest
	public void teardown() {
		pw.close();
	}


}
