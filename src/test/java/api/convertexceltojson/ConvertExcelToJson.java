package api.convertexceltojson;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ConvertExcelToJson {

	@Test
	public void convertExceltoJsonData() {
		try {
			FileInputStream file = new FileInputStream(new File(".//puppy_data.xlsx"));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				JSONObject jsonObject = new JSONObject();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String columnName = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
					int cellType = cell.getCellType();

					switch (cellType) {
					case Cell.CELL_TYPE_STRING:
						String stringValue = cell.getStringCellValue();
						jsonObject.put(columnName, stringValue);
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							Date dateValue = (Date) cell.getDateCellValue();
							jsonObject.put(columnName, dateValue);
						} else {
							double numericValue = cell.getNumericCellValue();
							jsonObject.put(columnName, numericValue);
						}
						break;
					}
				}
				RestAssured.given().contentType(ContentType.JSON).body(jsonObject.toString()).log().all()
						.post("https://petstore.swagger.io/v2/pet").then().statusCode(200).log().all();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
