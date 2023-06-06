package api.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.utils.Tokens;

import io.restassured.RestAssured;

public class BearerTokenAuth implements Tokens {

	@Test
	public void testBearerToken() throws URISyntaxException {

		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);

		wireMockServer.start();

		configureServer();

		configureBearerTokenStub();

		performBearerTokenAuthenticatedRequests();

		wireMockServer.stop();
	}

	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}

	/**
	 * This Method is to configure Bearer Token stub
	 */
	public void configureBearerTokenStub() {
		stubFor(get(urlEqualTo("/get/user/2"))
				.willReturn(aResponse()
						.withHeader("Authorization", "Bearer " +bearerToken)
						.withStatus(200).withBody("Bearer Token Authenticated")));
	}

	/**
	 * This Method is to verify Bearer Token Stub
	 * @throws URISyntaxException
	 */
	public void performBearerTokenAuthenticatedRequests() throws URISyntaxException {
		String bearerToken = "PMAK-644a652f36443b78b6aa0953-a901d88f06e52806aa16bbc4a56e147025";
		RestAssured.given().header("Authorization", "Bearer " + bearerToken).when()
				.get(new URI("http://localhost:8080/get/user/2")).then().assertThat().statusCode(200);

	}
}
