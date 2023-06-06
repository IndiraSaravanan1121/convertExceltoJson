package com.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	static public FileInputStream fi;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;

	public ExcelUtility(String path) {
		this.path = path;
	}

	/**
	 * This method is to get row count of Excel sheet
	 * 
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	public int getRowCount(String sheetName) {
		int rowcount = 0;
		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			System.out.println();
			sheet = workbook.getSheet(sheetName);
			 rowcount = sheet.getLastRowNum();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowcount;
	}

	/**
	 * This method is to get column count of Excel sheet
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @return
	 * @throws IOException
	 */
	public int getCellCount(String sheetName, int rowNum) {
		int cellCount = 0;
		try {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cellCount = row.getLastCellNum();
		fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellCount;
	}

	/**
	 * This Method is to get cell data based on sheet name, row no and column no
	 * 
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 * @return
	 * @throws IOException
	 */
	public String getCellData(String sheetName, int rowNo, int colNo) {
		String data = "";
		try {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNo);
		cell = row.getCell(colNo);

		DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
			data = "";
		fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
