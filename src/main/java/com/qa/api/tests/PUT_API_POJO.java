package com.qa.api.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.data.User;
import com.api.data.Users_Lombok_Lib;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class PUT_API_POJO {
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
		
		// update the sttus to 'inactive' using PUT call
		User usersObj = new User();
		usersObj.setName("qwerty");
		usersObj.setEmail("a" + System.currentTimeMillis() + "@hmail.com"); // must be unique
		usersObj.setGender("female");
		usersObj.setStatus("inactive");
		int userId = 7940919;

	APIResponse putRes = reqContext.put( "https://gorest.co.in/public/v2/users/" + userId, 
				RequestOptions.create()
				.setHeader("Content-Type", "application/json")
				// beader is must while passing authorization
				.setHeader("Authorization", "Bearer 1bdb17586a9b527e66cf607a8e881d50d56185ba96a220252a77745f48f4f684")
				.setData(usersObj)); // passing the body here.. NO NEED to SERIALIZE UNLIKE RESTASSURED
		
		System.out.println("Status code " + putRes.status());
		Assert.assertEquals(putRes.status(), 200);
		Assert.assertEquals(putRes.statusText(), "OK");
	}
	
	@AfterTest
	public void teardown() {
		pw.close();
	}
}
