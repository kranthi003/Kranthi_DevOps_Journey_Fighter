package answers;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
//import org.junit.*;
//import org.junit.runner.RunWith;
import org.testng.annotations.*;


public class RestAssuredGetDataProviderTest {

	private static RequestSpecification requestSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
			setBaseUri("https://petstore.swagger.io/").
			build();
	}
	
	/*******************************************************
	 * Create a DataProvider with three test data rows:
	 * country code - zip code - state
	 * us           - 90210    - California
	 * us           - 12345    - New York
	 * ca           - Y1A      - Yukon
	 * ksa			- 12213		- Riyadh
	 ******************************************************/
	
//	@DataProvider
	public static Object[][] zipCodeData() {
		return new Object[][] {
				{ "us", "90210", "California" },
				{ "us", "12345", "New York" },
				{ "ca", "Y1A", "Yukon" },
				{ "ksa", "12213", "Riyadh" }
		};
	}
	
	/*******************************************************
	 * Request zip code data for given country / zip
	 * combinations by sending a GET to /<countryCode>/<zipCode>.
	 * Use the test data collection created
	 * above. Check that the state returned by the API
	 * matches the expected value. The
	 ******************************************************/
	
	@Test
//	@UseDataProvider("zipCodeData")
	public void checkStateForCountryCodeAndZipCode(String countryCode, String zipCode, String expectedState) {
		
		given().
			spec(requestSpec).
		and().
			pathParam("countryCode", countryCode).
			pathParam("zipCode", zipCode).
		when().
			get("/{countryCode}/{zipCode}").
		then().
			assertThat().
			body("places[0].state",equalTo(expectedState));
	}
}