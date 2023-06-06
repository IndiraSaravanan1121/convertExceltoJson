package api.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.utils.Tokens;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class BasicAuth implements Tokens {

	@Test
	public void testBasicAuth() throws URISyntaxException {
		
		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);

		wireMockServer.start();

		configureServer();

		configureBasicAuthStubs();

		performBasicAuthenticatedRequests();

		wireMockServer.stop();
	}

	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}

	/**
	 * This Method is to configure basic auth stub
	 */
	public void configureBasicAuthStubs() {
		stubFor(get(urlEqualTo("/get/user/1")).withBasicAuth(username, password)
				.willReturn(aResponse().withStatus(200).withBody("Basic Auth Authenticated")));
	}

	/**
	 * This Method is to verify basic auth stub
	 * @throws URISyntaxException
	 */
	public void performBasicAuthenticatedRequests() throws URISyntaxException {
		String credentials = username + ":" + password;
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
		
		RestAssured
		.given()
			.header("Authorization", "Basic "+encodedCredentials)
		.when()
			.get(new URI("http://localhost:8080/get/user/1"))
			.then()
			.assertThat()
			.statusCode(200);
	}
}
