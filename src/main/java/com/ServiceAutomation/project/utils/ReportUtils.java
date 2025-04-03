package com.ServiceAutomation.project.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtils {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final String DEFAULT_REPORT_FOLDER = "LimaReport";
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance(DEFAULT_REPORT_FOLDER);
        }
        return extent;
    }
    
    public static ExtentReports getInstance(String customPath) {
        if (extent == null) {
            createInstance(customPath);
        }
        return extent;
    }
    
    private static void createInstance(String customPath) {
        // Create a timestamp for the report file name
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        
        // Use the custom folder path provided by the user
        File reportsFolder = new File(customPath);
        
        // Print a debug message to check if the Reports folder exists
        System.out.println("Reports folder exists: " + reportsFolder.exists());
        
        // Create the Reports folder if it doesn't exist
        if (!reportsFolder.exists()) {
            boolean created = reportsFolder.mkdirs();  // Using mkdirs() to create parent directories if needed
            System.out.println("Reports folder created: " + created);
        }
        
        // Set the report file path with the custom folder and timestamp
        String reportFilePath = customPath + "/LiMa-service-Automation"+".html";
        System.out.println("Report will be saved to: " + reportFilePath); // Debug message for the report path
        
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath);
        
        // Configure the reporter's settings
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("LiMa Service API Test Report");
        htmlReporter.config().setReportName("API Automation Test Results");
        htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        
        // Initialize ExtentReports instance and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        // Add system information
        extent.setSystemInfo("Environment", ConfigUtils.getProperty("environment"));
        extent.setSystemInfo("API Base URL", ConfigUtils.getProperty("api.base.url"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }
    
    public static ExtentTest createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName); // Using default folder
        test.set(extentTest);
        return extentTest;
    }
    
    public static ExtentTest createTest(String testName, String customReportPath) {
        ExtentTest extentTest = getInstance(customReportPath).createTest(testName);
        test.set(extentTest);
        return extentTest;
    }
    
    public static ExtentTest getTest() {
        return test.get();
    }
    
    public static void logRequest(String request) {
        getTest().info("Request Body: " + request);
    }
    
    public static void logResponse(String response) {
        getTest().info("Response Body: " + response);
    }
    
    public static void logError(String message, Throwable throwable) {
        getTest().fail(message);
        getTest().fail(throwable);
    }
    
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}