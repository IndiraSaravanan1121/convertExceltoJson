package api.chaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.utils.Tokens;

public class GetUser implements Tokens {
	
	@Test
	void testGetUser(ITestContext context) {
		
		int id = (int) context.getSuite().getAttribute("user_id");
		String get_url = (String) context.getAttribute("get_url");
				
		given()
		.header("Authorization", "Bearer " + bearerToken)
		.pathParam("id", id)
		.when()
			.get(get_url)
		.then()
			.statusCode(200)
			.log().all();
	}
}
