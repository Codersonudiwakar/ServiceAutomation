package com.ServiceAutomation.project.tests;

import org.junit.BeforeClass;

import com.ServiceAutomation.project.reporting.AddReportData;
import com.ServiceAutomation.project.utils.BeforeSetup;
import com.ServiceAutomation.project.utils.ConfigUtils;
import com.ServiceAutomation.project.utils.DBUtils;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;

public class APITests extends BeforeSetup{

	DBUtils db = new DBUtils();

	private ExtentTest test;
	private AddReportData report;
	String url=ConfigUtils.getProperty("api.base.url");
	

	
}


