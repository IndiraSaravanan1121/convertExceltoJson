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
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import io.restassured.RestAssured;

public class ApiKeyAuth {

	@Test
	public void testApiKeyAuth() throws URISyntaxException {

		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);

		wireMockServer.start();

		configureServer();

		configureApiStub();

		performApiKeyAuthenticatedRequests();

		wireMockServer.stop();
	}

	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}

	/**
	 * This Method is to do API Key stub
	 */
	public void configureApiStub() {
		stubFor(get(urlEqualTo("/get/user/1"))
				.withHeader("MyKeys",
						WireMock.equalTo("PMAK-644a652f36443b78b6aa0953-a901d88f06e52806aa16bbc4a56e147025"))
				.willReturn(aResponse().withStatus(200).withBody("API Key Authenticated")));
	}

	/**
	 * This Method is to do verify API Key stub
	 * @throws URISyntaxException
	 */
	public void performApiKeyAuthenticatedRequests() throws URISyntaxException {

		String apiKeys = "PMAK-644a652f36443b78b6aa0953-a901d88f06e52806aa16bbc4a56e147025";
		RestAssured.given().header("MyKeys", apiKeys).when().get(new URI("http://localhost:8080/get/user/1")).then()
				.assertThat().statusCode(200);

	}
}
