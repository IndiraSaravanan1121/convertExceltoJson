package api.petstore.test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.ReadJsonData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PetStoreTests {

	ReadJsonData readJson = new ReadJsonData();
	int id;
	public Logger logger;


	@BeforeClass
	public void setupData() {
		logger =  LogManager.getLogger(this.getClass());
		// data.setId();
	}
	
	/**
	 * This Method is to read data from properties file
	 * 
	 * @return
	 */
	public static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("config");
		return routes;
	}

	/**
	 * This Method is to Add pet to Store
	 */
	@Test(groups = { "scenario1", "scenario2", "scenario3" })
	public void addPetToStore() {
		
		logger = LogManager.getLogger(this.getClass());

		String post_url = getURL().getString("petstore_base_url");

		JSONObject data = readJson.readJsonFile(".//body.json");

		id = RestAssured.given().log().all().contentType(ContentType.JSON).body(data.toString()).when().post(post_url)
				.jsonPath().getInt("id");
		System.out.println(id);
	}

	/**
	 * This Method is to find pet by using Id
	 */
	@Test(groups = {"scenario1", "scenario2" })
	public void findPetById() {

		String get_url = getURL().getString("petstore_getPetById_url");
		RestAssured.given().log().all().pathParam("id", id).when().get(get_url).then().statusCode(200).log().all();
	}

	/**
	 * This Method is to find pet using status
	 */
	@Test(groups = {"scenario1", "scenario2" })
	public void findPetByStatus() {

		String post_url = getURL().getString("petstore_base_url");
		String status = "available";
		String endpoint = "/findByStatus";

		RestAssured.given().queryParam("status", status).when().get(post_url + endpoint).then().statusCode(200).log()
				.all();

	}

	/**
	 * This Method is to delete pet data using Id
	 */
	@Test(groups = {"scenario2", "scenario3" },dependsOnMethods = {"findPetByStatus"})
	public void deletePetById() {
		String get_url = getURL().getString("petstore_getPetById_url");
		RestAssured.given().log().all().pathParam("id", id).when().delete(get_url).then().statusCode(200).log().all();
	}

	/**
	 * This Method is to upload images
	 */
	@Test(groups = "scenario1")
	public void uploadImage() {

		String uploadImage_url = getURL().getString("petstore_uploadPetImage_url");
		int new_id = id;
		String uploadImage_url_endpoint = "/uploadImage";

		RestAssured.given()
				.header("Content_Type", "multipart/form-data")
				.formParam("additionalMetadata", "Testing")
				.multiPart("file", new File(".\\imagefolder\\portrait-pomeranian-dog.jpg")).when().post(uploadImage_url + new_id + uploadImage_url_endpoint).then()
				.statusCode(200);
	}

	/**
	 * This Method is to update pet details
	 */
	@Test(groups = "scenario3")
	public void updatePet() {
		String post_url = getURL().getString("petstore_base_url");

		JSONObject data = readJson.readJsonFile(".//updatepet.json");

		String name = RestAssured.given().log().all().contentType(ContentType.JSON).body(data.toString()).when().put(post_url)
				.jsonPath().getString("name");
		assertEquals(name, "Rocky");
		System.out.println(id);
	}

}
