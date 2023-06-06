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

public class OAuth {

	@Test
	public void oAuth() throws URISyntaxException {
		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);

		wireMockServer.start();

		configureServer();

		configureoAuthStub();

		performOAuthenticatedRequests();

		wireMockServer.stop();
	}

	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}

	/**
	 * This Method is to configure oAuth stub
	 */
	public void configureoAuthStub() {
		stubFor(get(urlEqualTo("/get/user/3"))
				.withHeader("oauth2", WireMock.equalTo("OAuth oauth_consumer_key=\"abc_01\", oauth_token=\"abc_01\", oauth_signature_method=\"HMAC-SHA1\", oauth_signature=\"indira\", oauth_timestamp=\"1234567890\", oauth_nonce=\"ounce_1\", oauth_version=\"1.0\""))
				.willReturn(aResponse()
						.withStatus(200).withBody("O AUth Authenticated")));
	}

	/**
	 * This Method is to verify oAuth stub
	 * @throws URISyntaxException
	 */
	public void performOAuthenticatedRequests() throws URISyntaxException {
		RestAssured.given().auth().oauth2("OAuth oauth_consumer_key=\"abc_01\", oauth_token=\"abc_01\", oauth_signature_method=\"HMAC-SHA1\", oauth_signature=\"indira\", oauth_timestamp=\"1234567890\", oauth_nonce=\"ounce_1\", oauth_version=\"1.0\"").when()
				.get(new URI("http://localhost:8080/get/user/3")).then().assertThat().statusCode(200);

	}
}
