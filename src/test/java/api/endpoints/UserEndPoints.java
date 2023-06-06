package api.endpoints;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileReader;
import java.util.ResourceBundle;

import org.json.JSONObject;
import org.json.JSONTokener;

import api.payload.TestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	
	static JSONObject data;

	/**
	 * This Method is to read data from properties file
	 * @return
	 */
	public static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("config");
		return routes;
	}

	/**
	 * This Method will take Post URL and send the post request and return the response
	 * @param payload
	 * @return
	 */
	public static Response createNewUser(TestData payload) {

		String post_url = getURL().getString("post_url");
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(post_url);
		return response;
	}

	/**
	 * This Method will take Get URL and send the get request and return the response
	 * @return
	 */
	public static Response readUser() {
		String get_url = getURL().getString("get_url");

		Response response = when().get(get_url);
		return response;
	}

	/**
	 * This Method will take Put URL and send the put request and return the response
	 * @return
	 */
	public static Response updateUser() {
		String put_url = getURL().getString("put_url");
		try {
			File f = new File(".\\body.json");
			FileReader fr = new FileReader(f);
			JSONTokener jt = new JSONTokener(fr);
			 data = new JSONObject(jt);
		} catch (Exception e) {
			System.out.println("File not Found");
		}
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(data).when()
				.put(put_url);
		return response;

	}

	/**
	 * This Method will take Delete URL and send the delete request and return the response
	 * @return
	 */
	public static Response deleteUser() {
		String delete_url = getURL().getString("delete_url");

		Response response = when().delete(delete_url);

		return response;
	}

}
