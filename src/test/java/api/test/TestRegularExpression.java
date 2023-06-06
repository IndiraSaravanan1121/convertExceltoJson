package api.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class TestRegularExpression {

	@Test
	void testRegEx() {
		given().get("http://localhost:3000/EmployeeDetail").then().assertThat().body("Employee[0].email",
				matchesRegex("[a-zA-Z0-9\\_\\-]+@[a-zA-Z]+.[a-z]{2,3}"));
	}
}
