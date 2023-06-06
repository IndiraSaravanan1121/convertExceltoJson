package api.convertexceltojson;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ConvertExcelToJson {

	@Test
	public void convertExceltoJsonData() {
		try {
			FileInputStream file = new FileInputStream(new File(".//testdata//userdata.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				JSONObject jsonObject = new JSONObject();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String columnName = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
					jsonObject.put(columnName, cell.getStringCellValue());
				}

				RestAssured.given().contentType(ContentType.JSON).body(jsonObject.toString())
						.post("https://reqres.in/api/users").then().statusCode(201).log().all();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
