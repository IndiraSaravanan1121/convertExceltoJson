package api.SerializationAndDeserilaization;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.List;

public class EmployeeDetails {

	EmployeePojoClass em;

	@Test
	public void testEmployeeAddress() {

		EmployeePojoClass rs = given().log().all().when().get("http://localhost:3000/EmployeeDetail")
				.as(EmployeePojoClass.class);

		int empCount = rs.getEmployee().size();
		
		for (int i = 0; i < empCount; i++) {
			if (rs.getEmployee().get(i).getName().equalsIgnoreCase("Indira")) {
				int addressCount = rs.getEmployee().get(i).getAddress().size();
				for (int j = 0; i < addressCount; j++) {
					if (rs.getEmployee().get(i).getAddress().get(j).getCity().equalsIgnoreCase("chennai")) {
						System.out.println(rs.getEmployee().get(i).getAddress().get(j).getStreet());
						break;
					}
				}
			}
		}
	}
}
