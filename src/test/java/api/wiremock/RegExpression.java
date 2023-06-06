package api.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;


import io.restassured.RestAssured;

public class RegExpression {
	
	@Test
	public void testRegEx() throws URISyntaxException {
		
		WireMockConfiguration config = WireMockConfiguration.options().port(8080);
		WireMockServer wireMockServer = new WireMockServer(config);
		
		wireMockServer.start();
		
		configureServer();

		regExpressionStub();

		performRegExpression();

        wireMockServer.stop();
	}
	
	/**
	 * This Method is to configure server
	 */
	public void configureServer() {
		configureFor("localhost", 8080);
	}
	
	/**
	 * This Method is to stub URL using Reg Expression
	 */
	public void regExpressionStub() {
		stubFor(get(urlPathMatching("/get/user/[0-9]+"))
				.willReturn(aResponse()
						.withStatus(200).withBody("URL Regular Expression matched")));
	}
	
	/**
	 * This Method is to verify URL using Reg Expression
	 * @throws URISyntaxException
	 */
	public void performRegExpression() throws URISyntaxException {
		RestAssured.given().when()
		.get(new URI("http://localhost:8080/get/user/19")).then().assertThat().statusCode(200);
	}

}
