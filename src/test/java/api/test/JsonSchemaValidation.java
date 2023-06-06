package api.test;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;


public class JsonSchemaValidation {
	
	@Test
	void jsonSchemaValidation() {
		
		given()
		
		.when()
			.get("http://localhost:3000/EmployeeDetail")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));		
	}

}
