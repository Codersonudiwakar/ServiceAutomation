package com.ServiceAutomation.project.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.ServiceAutomation.project.reporting.AddReportData;
import com.ServiceAutomation.project.reporting.HtmlReportGenerator;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.io.Files;

public class AfterSetup {
	private ExtentTest test;
	private AddReportData report;
	String url=ConfigUtils.getProperty("api.base.url");
	
	String projectPath = Paths.get("").toAbsolutePath().toString();
	String filePath = projectPath+ConfigUtils.getProperty("RequestandResponceReportPath");
	@AfterMethod
	public void tearDown(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + testName);
			if (result.getThrowable() != null) {
				test.fail(result.getThrowable());
			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed: " + testName);
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Skipped: " + testName);
		}
	}

	@AfterSuite
	public void finalizeReport() throws JsonMappingException, JsonProcessingException {
		ReportUtils.flush();
		HtmlReportGenerator.generateReport();
//		try {
//			File file = new File(filePath);
//			if (file.exists()) {
//			//	Files.write(Paths.get(filePath), file);
////				Files.write(Paths.get(filePath), "".getBytes());
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
	}
	

}
