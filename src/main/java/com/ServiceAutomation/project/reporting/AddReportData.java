package com.ServiceAutomation.project.reporting;

import java.util.*;

public class AddReportData {
	static List<List<String>> allReports = new ArrayList<>();

    public static void addHtmlReportData(String req, String res, String status, String testName, String url, String dbData) {
      
    	List<String> reportData = new ArrayList<>();
        reportData.add(req);        // Request
        reportData.add(res);        // Response
        reportData.add(status);     // Status
        reportData.add(testName);   // Test Name
        reportData.add(url);        // URL
        reportData.add(dbData);     // DB Data
        allReports.add(reportData);
    }
    
    public static List<List<String>> getReportData() {
    	return allReports;
    }
    
    
    

}

