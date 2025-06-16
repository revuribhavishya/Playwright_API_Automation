package com.qa.api.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.data.User;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

// DELTE API -> 204 NO Content
public class DELETE_API_POJO {

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
	public void updateUser() throws IOException {
		//DELETE
		// update the sttus to 'inactive' using DELETE call
		
		System.out.println("--DELETE resource --- ");
	
		int userId = 7940917;
	APIResponse deleteRes = reqContext.delete( "https://gorest.co.in/public/v2/users/" + userId, 
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
				// beader is must while passing authorization
				.setHeader("Authorization", "Bearer 1bdb17586a9b527e66cf607a8e881d50d56185ba96a220252a77745f48f4f684"));
			
		
		System.out.println("Status code " + deleteRes.status());
		Assert.assertEquals(deleteRes.status(), 204);
		Assert.assertEquals(deleteRes.statusText(), "No Content");
		
		
		System.out.println("--GET resource after deleting --- ");
		APIResponse getRes = reqContext.get( "https://gorest.co.in/public/v2/users/" + userId, 
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
				// beader is must while passing authorization
				.setHeader("Authorization", "Bearer 1bdb17586a9b527e66cf607a8e881d50d56185ba96a220252a77745f48f4f684"));
			
		
		System.out.println("Status code " + getRes.status());
		Assert.assertEquals(getRes.status(), 404);
		Assert.assertEquals(getRes.statusText(), "Not Found");
	}
	
	@AfterTest
	public void teardown() {
		pw.close();
	}
}
