package api.test;

import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.utils.DataProviders;

import api.endpoints.UserEndPoints;
import api.payload.TestData;
import io.restassured.response.Response;

public class Tests {

	TestData data;	
	public Logger logger;
	UserEndPoints userEndPoints;
	
	@BeforeClass
	public void setupData() {
		logger =  LogManager.getLogger(this.getClass());
		// data.setId();
	}

	@Test(priority = 1)
	public void testGetRequest() {
		Response response = userEndPoints.readUser();
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(dataProvider="Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String name, String job) {
		TestData testData = new TestData();
		testData.setName(name);
		testData.setJob(job);

		Response response = userEndPoints.createNewUser(testData);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);
		logger.info("Post Request Completed");
	}

	@Test(priority=3)
	public void testPutRequest() {

		Response response = userEndPoints.updateUser();
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
		logger.info("Put Request Completed");
	}

	@Test(priority = 4)
	public void testDeleteRequest() {
		
		Response response = userEndPoints.deleteUser();
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), HttpStatus.SC_NO_CONTENT);
		logger.info("Delete Request Completed");
	}
}
