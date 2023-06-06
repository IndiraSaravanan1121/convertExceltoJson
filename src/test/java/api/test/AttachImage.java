package api.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AttachImage {
	
	
	@Test
    public void uploadImageTest() {
        String imagePath = "KurtiImage.jpg";
        
        RestAssured.given()
            .contentType("multipart/form-data")
            .multiPart("image", "image.jpg", getClass().getClassLoader().getResourceAsStream(imagePath))
        .when()
            .post("https://api.example.com/get")
        .then()
            .statusCode(200)
            .extract().response();
    }

}
