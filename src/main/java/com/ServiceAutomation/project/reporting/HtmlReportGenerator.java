package com.ServiceAutomation.project.reporting;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import com.ServiceAutomation.project.utils.ConfigUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class HtmlReportGenerator {
	

	public static void reportTemplate(String req, String res, String status, String testName, String url,
			String dbData) throws JsonMappingException, JsonProcessingException {
		String projectPath = Paths.get("").toAbsolutePath().toString();
		String filePath =projectPath+ConfigUtils.getProperty("RequestandResponceReportPath");;

		ObjectMapper objectMapper = new ObjectMapper();
		String formattedReq = objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(objectMapper.readTree(req));
		String formattedRes = objectMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(objectMapper.readTree(res));
		try {
		    File file = new File(filePath);
		    FileWriter fw = new FileWriter(file, true);
		    BufferedWriter bw = new BufferedWriter(fw);

		    bw.write("<html><head><title>Lima Service Automation Test Report</title>");
	        bw.write("<style>");
	        bw.write("body {font-family: Arial, sans-serif; margin: 20px;padding: 0;background-color: #f4f4f4;}");
	        bw.write(".container {max-width: 90%; margin: auto; background: white;padding: 20px;border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);}");
	        bw.write(".test-section {width:100%;margin-bottom: 20px;padding: 5px;border: 1px solid #ddd; border-radius: 5px; background: #fff;}");
	        bw.write(".title {font-size: 18px;font-weight: bold;margin-bottom: 5px;}");
	        bw.write(".status {color: green;font-weight: bold;}");
	        bw.write(".request-response {display: flex;justify-content: space-between;}");
	        bw.write(".box {font-family: 'Courier New', Courier, monospace;width: 48%;padding: 10px;border: 1px solid #ddd; border-radius: 5px;background: #fafafa;word-wrap: break-word;overflow-wrap: break-word;}");
	        bw.write(".green {background-color: #4CAF50; color: white; font-weight: bold; text-transform: uppercase;}");
	        bw.write("p{overflow-wrap: break-word; word-wrap: break-word; color: #e67e22; font-size: 14px;font-weight: bold;}");
	        bw.write(".col-20 {width: 20%; background-color: lightblue;padding: 10px;box-sizing: border-box;}");
	        bw.write(".col-80 {width: 80%;background-color: lightblue;padding: 10px;box-sizing: border-box;font-size: 14px;}");
	        bw.write(".row {display: flex; margin-bottom: 10px; width: 100%; word-wrap: break-word; overflow-wrap: break-word;}");
	        bw.write("table {width: 100%; border-collapse: collapse; margin-bottom: 20px;}");
	        bw.write("th, td {padding: 8px 12px; text-align: left; border: 1px solid #ddd;}");
	        bw.write("th {background-color: #f2f2f2;}");
	        bw.write("</style>");
	        bw.write("</head><body>");
		    bw.write("<div class='container'>");
		    
		    
		    bw.write("<div class='test-section'>");
		    
		    bw.write("<div class='row'>");
		    bw.write("<div class='col-20'>Test Case Name :</div>");
		    bw.write("<div class='col-80'>" + testName + "</div>");
		    bw.write("</div>");
		    
		    bw.write("<div class='row'>");
		    bw.write("<div class='col-20'>Status :</div>");
		    bw.write("<div class='col-80'><span class='status'>" + status + "</span></div>");
		    bw.write("</div>");
		    
		    bw.write("<div class='row'>");
		    bw.write("<div class='col-20'>Url :</div>");
		    bw.write("<div class='col-80'>" + url +"</div>");
		    bw.write("</div>");
		    
//		    bw.write("<div class='row'>");
//		    bw.write("<div class='col-20'>Test Data :</div>");
//		    String mpDataString = objectMapper.writeValueAsString(mpData);
//			if (mpData != null && !mpData.isEmpty()) {
//		    bw.write("<div class='col-80'>" + mpDataString.replace("\n", "<br>").replace(" ", "&nbsp;") + "</div>");
//		    bw.write("</div>");
//			}
		    
		    

//		    bw.write("<div class='request-response'>");
//		    bw.write("<div class='box'>");
//		    bw.write("<div><strong>Request</strong></div>");
//		    bw.write("<div><p>" + formattedReq.replace("\n", "<br>").replace(" ", "&nbsp;") + "</p></div>");
//		    bw.write("</div>");
//		    bw.write("<div class='box'>");
//		    bw.write("<div><strong>Response</strong></div>");
//		    bw.write("<div><p>" + formattedRes.replace("\n", "<br>").replace(" ", "&nbsp;") + "</p></div>");		    bw.write("</div>");
//		    bw.write("</div>");
//		    bw.write("</div>");
		    
		    bw.write("<div class='request-response'>");
		    bw.write("<div class='box'>");
		    bw.write("<div><strong>Request</strong></div>");
		    bw.write("<div><p>" + formattedReq.replace("\n", "<br>").replace(" ", "&nbsp;") + "</p></div>");
		    bw.write("</div>");
		    bw.write("<div class='box'>");
		    bw.write("<div><strong>Response</strong></div>");
		    bw.write("<div><p>" + formattedRes.replace("\n", "<br>").replace(" ", "&nbsp;") + "</p></div>");
		    bw.write("</div>");

		    // Add a dropdown to toggle the shoe section visibility
		    bw.write("<div class='box'>");
		    bw.write("<button onclick='toggleShoeSection()'>Toggle Shoe Section</button>");
		    bw.write("<div id='shoe-section' style='display:none;'>");
		    bw.write("<strong>Shoe Section</strong><br>");
		    bw.write("<p>Details about the shoe...</p>");
		    bw.write("</div>");
		    bw.write("</div>");

		    bw.write("</div>");

		    // Add JavaScript function to toggle the visibility
		    bw.write("<script>");
		    bw.write("function toggleShoeSection() {");
		    bw.write("  var section = document.getElementById('shoe-section');");
		    bw.write("  if (section.style.display === 'none') {");
		    bw.write("    section.style.display = 'block';");
		    bw.write("  } else {");
		    bw.write("    section.style.display = 'none';");
		    bw.write("  }");
		    bw.write("}");
		    bw.write("</script>");


		    bw.write("</div>");  // Closing .container div
		    bw.write("</body></html>");

		    bw.close();
		    fw.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	public static void generateReport() throws JsonMappingException, JsonProcessingException {
		List<List<String>> allReports=AddReportData.getReportData();
		for (List<String> reportData : allReports) {
            String currentReq = reportData.get(0);
            String currentRes = reportData.get(1);
            String currentStatus = reportData.get(2);
            String currentTestName = reportData.get(3);
            String currentUrl = reportData.get(4);
            String currentDbData = reportData.get(5);
            reportTemplate(currentReq, currentRes, currentStatus, currentTestName, currentUrl, currentDbData);
	}
	}
}