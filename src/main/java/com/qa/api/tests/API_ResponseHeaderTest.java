package com.qa.api.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.HttpHeader;

public class API_ResponseHeaderTest {
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
	public void getHeaders() {
		
		APIResponse apiResponse =reqContext.get("https://gorest.co.in/public/v2/users");	
		System.out.println(" ---- print api Status code ----" );
		int statusCode = apiResponse.status();
		System.out.println("response status code: " + statusCode );
		Assert.assertEquals(statusCode, 200);
		
		// headers -- Map
		System.out.println("----  Headers -----------");
		Map<String, String> map= apiResponse.headers();
		map.forEach((k,v)-> System.out.println(k + ":" + v));
		System.out.println("total response headers" + map.size());
		Assert.assertEquals(map.get("server"), "cloudflare");
		
		// headersArrays - List
		System.out.println("---- Headers Arrays-----");
		List<HttpHeader> headersList= apiResponse.headersArray();
		for(HttpHeader h : headersList) {
			System.out.println(h.name+  ":" + h.value);
		}
		
	}
	
	@AfterTest
	public void teardown() {
		pw.close();
	}


}
