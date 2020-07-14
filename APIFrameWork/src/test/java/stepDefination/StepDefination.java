package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataDriver;
import resources.Utils;

import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;
public class StepDefination extends Utils{
	Response response;
	//RequestSpecification reqspec;
	RequestSpecification req;
	ResponseSpecification resspec;
	TestDataDriver data=new TestDataDriver(); 
	static String place_id;


	
	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String Name, String Address, String Language) throws IOException {
	
			 req=given().spec(requestSpec()).body(data.getPayload(Name,Address,Language));

		}




	
	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String resource, String method) {
		    // Write code here that turns the phrase above into concrete actions
			APIResources apiResource=APIResources.valueOf(resource);
			if(method.equalsIgnoreCase("post")) 
			response=req.when().post(apiResource.getResource());
			else if(method.equalsIgnoreCase("GET")){
				response=req.when().get(apiResource.getResource());	
			}
			
		}
		
		@Then("the api call got success with Status Code {int}")
		public void the_api_call_got_success_with_status_code(Integer int1) {
		    // Write code here that turns the phrase above into concrete actions
			System.out.println(response.getStatusCode());
			 assertEquals(response.getStatusCode(), 200	); 
		    
		}
		
		@Then("{string} in response body is {string}")
		public void in_response_body_is(String key, String value) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(getKeyValue(response,key), value);
		  
		}

			@Then("verify place_ID created maps to {string} using {string}")
			public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
			    // Write code here that turns the phrase above into concrete actions
			    
			    place_id=getKeyValue(response,"place_id");
				req=given().spec(requestSpec()).queryParam("place_id", place_id);
				user_calls_with_request(resource,"GET");
				String Actualname=getKeyValue(response,"name");
				assertEquals(name, Actualname);
				
			}

			
				@Given("Delete Place Payload")
				public void delete_place_payload() throws IOException {
				    // Write code here that turns the phrase above into concrete actions
				    req=given().spec(requestSpec()).body(data.deletePayload(place_id));
					
				}







}
