package answers;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.*;
//import org.junit.*;
import org.testng.annotations.*;


public class RestAssuredGetTest {

	private static RequestSpecification requestSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://petstore.swagger.io/").
				build();
	}

	/*******************************************************
	 * Send a GET request to /us/90210
	 * and check that the response has HTTP status code 200
	 ******************************************************/

	@Test
	public void requestUsZipCode90210_checkResponseCode_expect200() {

		given().
		spec(requestSpec).
		when().
		get("/v2/pet/findByStatus?status=available").
		then().
		assertThat().
		statusCode(200);
		String bodyResponse = given().
				spec(requestSpec).
				when().
				get("/v2/pet/findByStatus?status=available").getBody().asString();
		System.out.println(bodyResponse);
	}


}