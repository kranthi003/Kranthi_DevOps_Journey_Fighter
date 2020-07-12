package answers;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import dataentities.Car;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
//import org.junit.*;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class RestAssuredPostTest {

	private static RequestSpecification requestSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://petstore.swagger.io/").
				setContentType(ContentType.JSON).
				build();
	}

	/*******************************************************
	 * Create a new Car object that represents a 2012 Ford Focus
	 * POST this object to /car/postcar
	 * Verify that the response HTTP status code is equal to 200
	 ******************************************************/

	@Test
	public void postCarObject_checkResponseHttpStatusCode_expect200() {

		//		Car myCar = new Car("Ford", "Focus", 2012);
		//		given().
		//		spec(requestSpec).
		//	and().
		//		body(myCar).
		//	when().
		//		post("/v2/store/order").
		//	then().
		//		assertThat().
		//		statusCode(200);
		JSONObject requestParams = new JSONObject();
    	requestParams.put("id", 0); 
    	requestParams.put("petId", 0);
    	 
    	requestParams.put("quantity", 0);
    	requestParams.put("shipDate", "2020-07-11T10:33:07.425Z");
    	requestParams.put("status",  "placed");
    	requestParams.put("complete",  true);
    	
		String bodyResponse = given().
				spec(requestSpec).
				and().body(requestParams.toJSONString()).
				when().
				post("/v2/store/order").getBody().asString();
		System.out.println(bodyResponse);
		
		given().
		spec(requestSpec).
		when().
		post("/v2/store/order").
		then().
		assertThat().
		statusCode(400);
		
	}



}