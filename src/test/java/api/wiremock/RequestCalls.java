package api.wiremock;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.utils.Tokens;

import io.restassured.RestAssured;

public class RequestCalls implements Tokens{

	private static final int PORT = 8080;
	private static final String HOST = "localhost";
	private static WireMockServer server = new WireMockServer(PORT);
	ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();

	@BeforeClass
	public static void setup() {	
		server.start();
	}

	@Test
	void configureGetStub() {
		mockResponse.withStatus(200);
		WireMock.configureFor(HOST, PORT);
		WireMock.stubFor(WireMock.get("/get/users/1").willReturn(mockResponse));

	}

	@Test
	void testGetWiremock() throws URISyntaxException {
		RestAssured.given()
		.log().all()
		.when().get(new URI("http://localhost:8080/get/users/1")).then().assertThat()
				.statusCode(200);
	}
	
	@Test
	void configurePostStub() {
		mockResponse.withStatus(201);
		WireMock.configureFor(HOST, PORT);
		WireMock.stubFor(WireMock.post("/users").willReturn(mockResponse));

	}

	@Test
	void testPostWiremock() throws URISyntaxException {
		RestAssured.given().when().post(new URI("http://localhost:8080/users")).then().assertThat()
				.statusCode(201);
	}
	
	@Test
	void configurePutStub() {
		mockResponse.withStatus(200);
		WireMock.configureFor(HOST, PORT);
		WireMock.stubFor(WireMock.put("/users").willReturn(mockResponse));

	}

	@Test
	void testPutWiremock() throws URISyntaxException {
		RestAssured.given().when().put(new URI("http://localhost:8080/users")).then().assertThat()
				.statusCode(200);
	}
	
	@Test
	void configureDeleteStub() {
		mockResponse.withStatus(204);
		WireMock.configureFor(HOST, PORT);
		WireMock.stubFor(WireMock.delete("/users/delete").willReturn(mockResponse));

	}

	@Test
	void testDeleteWiremock() throws URISyntaxException {
		RestAssured.given().when().delete(new URI("http://localhost:8080/users/delete")).then().assertThat()
				.statusCode(204);
	}
	
	@AfterClass
	public static void tearDown() {	
		server.stop();
	}

}
