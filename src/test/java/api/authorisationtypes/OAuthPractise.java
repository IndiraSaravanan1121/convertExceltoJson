package api.authorisationtypes;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class OAuthPractise {
	
	@Test
	public void testOAuth() {		
		
//		String code = "4/0AbUR2VO8H67Op1gVEJZXpG8mkFGr2G6GTbGobj4BHr3qtp9QeiqSu6UxN385OOuv6SVaNw";
//		
//		given()
//				.urlEncodingEnabled(false)
//			.queryParams("code", code)
//			.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//			.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
//			.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
//			.queryParams("grant_type", "authorization_code")
//		.when()
//			.post("https://www.googleapis.com/oauth2/v4/token");
				
		String accessToken = "ya29.a0AWY7CklaQAD5f7Z8Bj8zwhj8eaQvYwIleiAiROtlvFa6K5g318cubv5bgIakm9cC-uRJo5sZpG2sBUPh-ZKoQML8V2w_8tdeA0kl6XQW65PguxdQxNfaxIV0FpplySWYCWTaeLaXxsrYFFxHyDed1FhP9MGeaCgYKAZsSARISFQG1tDrpuSckSTLFhqod6z3U8m-xCQ0163";
		
		String Response = given()
			.log()
			.all()
			.queryParam("access_token", accessToken)
		.when()	
			.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(Response);
		
	}

}
