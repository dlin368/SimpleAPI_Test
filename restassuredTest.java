
import io.restassured.RestAssured;
import io.restassured.response.*;
import io.restassured.http.*;
import static org.testng.Assert.assertTrue;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import java.lang.String;
import java.util.List;
import org.testng.annotations.Test;

@Test
public class restassuredTest {
	public static void main(String[] args) {
		Response dataBody = getJsonResponse("https://jsonplaceholder.typicode.com/users"); 
		dataBody.then().assertThat().statusCode(200);
		List<String> jsonList = getRootElementInArray(dataBody);
		assertTrue(jsonList.size() == 10, "Pass");
		assertTrue(dataBody.jsonPath().getString("name").contains("Mrs. Dennis Schulist"),"contains");
		dataBody.then().body(matchesJsonSchema("I think defined schema should be passed here but would throw exceptions for this command")); 

	}
	
	public static Response getJsonResponse(String url) {
      return  
    		  RestAssured
      .given()
	.contentType(ContentType.JSON)
      .when()
	.get(url);
	}
	
	public static List<String> getRootElementInArray(Response body){
		return
				body.jsonPath().getList("$");
	}
}