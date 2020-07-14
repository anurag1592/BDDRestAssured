package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	StepDefination sd=new StepDefination();
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{
		if(StepDefination.place_id==null){
		sd.add_place_payload("Rahul", "123 holy street", "Gujrati");
		sd.user_calls_with_request("addPlaceAPI", "Post");
		sd.verify_place_id_created_maps_to_using("Rahul", "getPlaceAPI");
		}
	}
	
	
}
