package api.convertexceltojson;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class ConvertJsontoExcel {


	@Test
	public void convertJsonToExcel() {
	   
	        // Define the JSON data
	        String jsonData = "{\"id\":25667,\"category\":{\"id\":25,\"name\":\"puppy\"},\"name\":\"banti\",\"photoUrls\":[\"https://www.google.com/url?sa=i&url=https%3A%2F%2Funsplash.com%2Fs%2Fphotos%2Fbaby-dog&psig=AOvVaw1PLyIfrJF9TqrzoAxe5dPn&ust=1685513923905000&source=images&cd=vfe&ved=0CBEQjRxqFwoTCKC4peeynP8CFQAAAAAdAAAAABAE\"],\"tags\":[{\"id\":25,\"name\":\"friendly\"}],\"status\":\"available on now\"}";

	        // Parse the JSON data
	        JSONObject jsonObject = new JSONObject(jsonData);

	        // Create a new Excel workbook
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Puppy Data");

	        // Create header row
	        Row headerRow = sheet.createRow(0);
	        headerRow.createCell(0).setCellValue("ID");
	        headerRow.createCell(1).setCellValue("Category ID");
	        headerRow.createCell(2).setCellValue("Category Name");
	        headerRow.createCell(3).setCellValue("Name");
	        headerRow.createCell(4).setCellValue("Photo URL");
	        headerRow.createCell(5).setCellValue("Tag ID");
	        headerRow.createCell(6).setCellValue("Tag Name");
	        headerRow.createCell(7).setCellValue("Status");

	        // Populate data rows
	        Row dataRow = sheet.createRow(1);
	        dataRow.createCell(0).setCellValue(jsonObject.getInt("id"));
	        dataRow.createCell(1).setCellValue(jsonObject.getJSONObject("category").getInt("id"));
	        dataRow.createCell(2).setCellValue(jsonObject.getJSONObject("category").getString("name"));
	        dataRow.createCell(3).setCellValue(jsonObject.getString("name"));
	        JSONArray photoUrls = jsonObject.getJSONArray("photoUrls");
	        dataRow.createCell(4).setCellValue(photoUrls.getString(0));
	        JSONArray tags = jsonObject.getJSONArray("tags");
	        dataRow.createCell(5).setCellValue(tags.getJSONObject(0).getInt("id"));
	        dataRow.createCell(6).setCellValue(tags.getJSONObject(0).getString("name"));
	        dataRow.createCell(7).setCellValue(jsonObject.getString("status"));

	        // Auto-size columns for better readability
	        for (int i = 0; i < 8; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Write the workbook to an Excel file
	        try {
	            FileOutputStream fileOutputStream = new FileOutputStream("puppy_data.xlsx");
	            workbook.write(fileOutputStream);
	            // workbook.close();
	            fileOutputStream.close();
	            System.out.println("Excel file generated successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
