package api.test;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class HamcrestMatchers {

	@Test
	public void hamcrestMatchers() {

		given()
			.get("http://localhost:3000/EmployeeDetail")
		.then().assertThat()
				.body("Employee[0].Name", equalTo("Rajesh"))
				.and()
				.body("Employee[0].Address1.street", is("X street"));

		given()
			.get("http://localhost:3000/EmployeeDetail")
		.then()
			.assertThat()
			.body("Employee[0].Address1.street",
				equalToIgnoringCase("x street"));

		given()
			.get("http://localhost:3000/EmployeeDetail")
		.then()
			.assertThat()
			.body("Employee[1].Name", equalToIgnoringWhiteSpace("Lakshmi "))
			.and()
			.body("Employee[0].Address1.street", is("X street"));

		given()
			.get("http://localhost:3000/EmployeeDetail")
		.then()
			.assertThat()
			.body("Employee[0].Address1.street", startsWith("x"));

		given()
			.get("http://localhost:3000/EmployeeDetail")
		.then()
			.assertThat()
			.body("Employee[0].Address1.street", startsWith("X"));
	}

}
