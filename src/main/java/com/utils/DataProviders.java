package com.utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	ExcelUtility xl;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "Data")
	public String[][] getAllData() {
		try {
		String path = System.getProperty("user.dir")+"/testdata/userdata.xlsx";
		xl = new ExcelUtility(path);
		} catch (Exception e) {
			
		}
		int rowNum = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);
		
		String apidata[][] = new String[rowNum][colCount];
		
		for(int i=1; i<=rowNum; i++) {
			for(int j=0; j<colCount; j++) {
				apidata[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
}
