package com.ServiceAutomation.project;

import java.nio.file.Paths;
import java.util.Collections;

import org.testng.TestNG;

import com.ServiceAutomation.project.utils.ConfigUtils;

public class ServiceAutomation {
	public static void main(String[] args) {

		TestNG testng = new TestNG();
		String projectPath = Paths.get("").toAbsolutePath().toString();
		String filename=ConfigUtils.getProperty("xmlPath");
		String testngXmlPath=projectPath+filename;
		testng.setTestSuites(Collections.singletonList(testngXmlPath));
		testng.run();

	}

}