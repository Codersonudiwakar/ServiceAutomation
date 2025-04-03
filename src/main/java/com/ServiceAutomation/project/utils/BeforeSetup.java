package com.ServiceAutomation.project.utils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.ServiceAutomation.project.reporting.AddReportData;
import com.aventstack.extentreports.ExtentTest;

import groovyjarjarasm.asm.commons.Method;
import io.restassured.RestAssured;

public class BeforeSetup extends AfterSetup {
	private ExtentTest test;
	private AddReportData report;
	String url=ConfigUtils.getProperty("api.base.url");
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = url;
	}

	String projectPath = Paths.get("").toAbsolutePath().toString();
	String filePath = projectPath+ConfigUtils.getProperty("RequestandResponceReportPath");

	@BeforeSuite
	public void setupReport() throws IOException {
		ReportUtils.getInstance();
	}

	@BeforeMethod
	public void setup(Method method, Object[] testData) {
		String testName = method.getName();
		test = ReportUtils.createTest(testName);
		if (testData != null && testData.length > 0 && testData[0] instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, String> data = (Map) testData[0];
			test.info("Test Data: " + data.toString());
		}
	}

}
