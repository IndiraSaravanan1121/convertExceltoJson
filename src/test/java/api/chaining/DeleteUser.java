package api.chaining;

import static io.restassured.RestAssured.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.utils.Tokens;


public class DeleteUser implements Tokens {
	
	@Test
	void testDeleteUser(ITestContext context) {
		
		int id = (int) context.getSuite().getAttribute("user_id");
		String delete_url = (String) context.getAttribute("get_url");
		
		given() 
			.header("Authorization", "Bearer " + bearerToken)
			.pathParam("id", id)
		.when()
	    	.delete(delete_url)
	    .then()
	    	.statusCode(204);
	}
}
