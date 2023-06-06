package api.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.restassured.RestAssured;

public class NoAuth {
	
	@Test
	public void testNoAuth() throws URISyntaxException {
		
		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);

		wireMockServer.start();

		configureServer();

		configureNoAuthStubs();

		performNoAuthenticatedRequests();

		wireMockServer.stop();
	}

	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}

	/**
	 * This Method is to configure No Auth stub
	 */
	public void configureNoAuthStubs() {
		stubFor(get(urlEqualTo("/get/user/1"))
				.willReturn(aResponse().withStatus(200).withBody("No Auth Required")));
	}

	/**
	 * This Method is to verify No Auth stub
	 * @throws URISyntaxException
	 */
	public void performNoAuthenticatedRequests() throws URISyntaxException {
		
		RestAssured
		.given()
		.when()
			.get(new URI("http://localhost:8080/get/user/1"))
			.then()
			.assertThat()
			.statusCode(200);

	}

}
