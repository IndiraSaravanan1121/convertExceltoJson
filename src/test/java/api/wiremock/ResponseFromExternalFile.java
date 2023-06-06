package api.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ResponseFromExternalFile {

	@Test
	public void testResponseBodyUsingFile() throws URISyntaxException {

		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);

		wireMockServer.start();

		configureServer();

		responseFileUsingExternalFileStub();

		performResponseBodyUsingExternalFile();

		wireMockServer.stop();
	}

	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}
	
	/**
	 * This Method is to stub Response Body using External File
	 */
	public void responseFileUsingExternalFileStub() {
		stubFor(get(urlPathMatching("/get/user/1"))
				.willReturn(aResponse()
						.withStatus(200).withBody("data.json")));
	}
	
	/**
	 * This Method is to Verify Response Body using External File
	 * @throws URISyntaxException
	 */
	public void performResponseBodyUsingExternalFile() throws URISyntaxException {
		Response response = (Response) RestAssured.given().when()
		.get(new URI("http://localhost:8080/get/user/1")).then().log().all();
		System.out.println(response);
	}
}
