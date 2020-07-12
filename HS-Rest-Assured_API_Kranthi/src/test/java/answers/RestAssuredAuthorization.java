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

public class RestAssuredAuthorization {

	private static RequestSpecification requestSpec;
	private static String myAuthenticationToken;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://petstore.swagger.io").
				setContentType(ContentType.JSON).
				build();
		JSONObject requestParams = new JSONObject();
		requestParams.put("client_id",  "123");
		myAuthenticationToken =

				given().
				auth().
				preemptive().
				basic("test", "abc123").
				and().body(requestParams.toJSONString()).
				when().
				get("/oauth/authorize").
				then().
				extract().
				path("");
		System.out.println("myAuthenticationToken: "+myAuthenticationToken);
	}

	@Test
	public void usePreviouslyStoredAuthToken() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("petId",  "123");

		String bodyResponse = given().
				auth().
				oauth2(myAuthenticationToken).
				spec(requestSpec).
				and().body(requestParams.toJSONString()).
				when().
				post("/pet/1").getBody().asString();
		System.out.println(bodyResponse);

	}

}