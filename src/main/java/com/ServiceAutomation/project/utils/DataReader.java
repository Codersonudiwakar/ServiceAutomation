package com.ServiceAutomation.project.utils;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.*;

public class DataReader {
	
	private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelWSheet;
    private static FormulaEvaluator Evaluator;
    private static XSSFCell Cell;
    private static XSSFRow Row;
    private static int rowCount;
    public static int columnCount;
    
  
    
  //  String static excelFile=ConfigUtils.getProperty("RequestandResponceReportPath")
    
      private static void loadDataFile(String FileName) {
        try {
        	String projectPath = Paths.get("").toAbsolutePath().toString();
        	String filePath = projectPath+ConfigUtils.getProperty("excelFilePath");           
        	FileInputStream ExcelFile = new FileInputStream(filePath);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            Evaluator = ExcelWBook.getCreationHelper().createFormulaEvaluator();
            ExcelWSheet = ExcelWBook.getSheetAt(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
      public static int getRowCount(String FileName) {
      	loadDataFile(FileName);
          rowCount = ExcelWSheet.getLastRowNum();
          return rowCount;
      }
      
      public static Map<String, String> getDataSet(int rowNum,String FileName) {
      	loadDataFile(FileName);
          DataFormatter formatter = new DataFormatter();
          Row = ExcelWSheet.getRow(rowNum);
          columnCount = Row.getLastCellNum();
          Map<String, String> mpData=new HashMap<String,String>();
          for (int i = 0; i < columnCount; i++) {
          	String value="";
          	Cell = ExcelWSheet.getRow(0).getCell(i);
              String key = formatter.formatCellValue(Cell, Evaluator);
          	try
          	{
  	            Cell = Row.getCell(i);
  	            value = Cell.getStringCellValue();
          	}
          	catch(Exception e)
          	{
          		value ="";
          	}
              mpData.put(key, value);
          }
          return mpData;
      }
      
      @DataProvider(name = "getData")
  	public static Object[][] getData(Method m) 
  	{    
  		String dataFileName = ConfigUtils.getProperty("excelFileName"); ;
  		getRowCount(dataFileName);
  		int intTestRowCount=0;
  		int intStartRow=0;
  		for (int i = 0; i < rowCount; i++) 
  		{ 
  			if(getDataSet(i+1,dataFileName).containsValue(m.getName()))
  			{
  				intTestRowCount++; 
  				if(intStartRow==0)
  					intStartRow=i+1;
  			}
  		}
  		StoreIteration.strIterationCount=StoreIteration.strIterationCount+m.getName()+":"+intTestRowCount+",";
  		Object[][] iterationData = new Object[intTestRowCount][1];
  		int intCounter=0;
  		for (int i = intStartRow; i < intStartRow+intTestRowCount; i++) {
  			Map<String,String> mpData=getDataSet(i,dataFileName);
  			iterationData[intCounter]=new Map[] {mpData};
  			intCounter++;
  		}
  		
  		return iterationData;
  	}
 
      
      public static Map<String, String> getDataSet(int rowNum,String FileName, String sheetName) {
      	loadDataFile(FileName);
          DataFormatter formatter = new DataFormatter();
          Row = ExcelWSheet.getRow(rowNum);
          columnCount = Row.getLastCellNum();
          Map<String, String> mpData=new HashMap<String,String>();
          for (int i = 0; i < columnCount; i++) {
          	String value="";
          	Cell = ExcelWSheet.getRow(0).getCell(i);
              String key = formatter.formatCellValue(Cell, Evaluator);
          	try
          	{
  	            Cell = Row.getCell(i);
  	            value = Cell.getStringCellValue();
          	}
          	catch(Exception e)
          	{
          		value ="";
          	}
              mpData.put(key, value);
          }
          return mpData;
      }
      
      public static int getRowCount(String FileName, String sheetName) {
      	loadDataFile(FileName);
      	ExcelWSheet = ExcelWBook.getSheet(sheetName);
          rowCount = ExcelWSheet.getLastRowNum();
          return rowCount;
      }
	        
}