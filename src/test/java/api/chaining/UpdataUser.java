package api.chaining;

import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.utils.Tokens;

public class UpdataUser implements Tokens {
	
	@Test
	void testUpdateUser(ITestContext context) {
		
		int id = (int) context.getSuite().getAttribute("user_id");
		String put_url = (String) context.getAttribute("get_url");
																					
		Faker faker = new Faker();

		JSONObject data = new JSONObject();

		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		given()
				.header("Authorization", "Bearer " + bearerToken)
				.contentType("application/json")
				.pathParam("id", id)
				.body(data.toString())

	    .when()
	    	.put(put_url)
	    	.jsonPath().getInt("id");	
		
		context.setAttribute("id", id);
				
	}

}
