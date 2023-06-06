package api.test;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import api.payload.TestData;

public class SerializationAndDeserialization {
	
	//@Test
	void testSerialization() throws JsonProcessingException {
		
		TestData data = new TestData();
		
		data.setName("Indira");
		data.setJob("Engineer");
		
		ObjectMapper obj = new ObjectMapper();
		String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(data);
		
		System.out.println(jsonData);
	}

	@Test
	void testDeserialization() throws JsonMappingException, JsonProcessingException {
		
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Indira\",\r\n"
				+ "  \"job\" : \"Engineer\"\r\n"
				+ "}";
		
		ObjectMapper obj = new ObjectMapper();
		
	    TestData data = obj.readValue(jsonData, TestData.class);
	    
	    System.out.println(data.getName());
	    System.out.println(data.getJob());
	}
}
