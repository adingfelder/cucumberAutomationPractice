package com.planit.training.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	XSSFWorkbook wb;
	XSSFSheet sheet1;
	
	public ReadExcel(String excelPath, String sheetname) {
		try {
			File src = new File(excelPath);
			if (!src.exists()) {
				System.out.println("ExcelFile: " + excelPath + " does not exist");
			} else {
				FileInputStream fis= new FileInputStream(src);
				
				wb = new XSSFWorkbook(fis);
				sheet1 = wb.getSheet(sheetname);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getStringData(int row, int col) {
		String data = sheet1.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	
	public String getNumericData(int row, int col) {
		String data = sheet1.getRow(row).getCell(col).getRawValue();
		return data;
	}

	public static void main(String[] args)  throws Exception {
		//ensure test data from example resources is saved in this location
		ReadExcel excelFile = new ReadExcel("C:/selenium/Test Data.xlsx", "Sheet1"); 
		if (excelFile.wb != null) {
			String x = excelFile.getStringData(1,0);
			String y = excelFile.getStringData(1,1);
			String z = excelFile.getStringData(1,2);
			// you can get numeric data via: excelFile.getNumericData(1,2);
			
			System.out.println("test data contains:");
			System.out.println("1-email:" + x);
			System.out.println("2-password:" + y);
			System.out.println("3-name:" + z);
		} else {
			System.out.println("error opening test data");
		}
		
	}

}
