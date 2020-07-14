package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Location;

public class TestDataDriver {
public AddPlace getPayload(String Name, String Address, String Language){
	Location location=new Location();
	location.setLat(-38.383494);
	location.setLng(33.427362);
	List<String> list=new ArrayList<String>();
	list.add("shoe park");
	list.add("shop");
	AddPlace p=new AddPlace();
	p.setAccuracy(50);
	p.setAddress(Address);
	p.setLanguage(Language);
	p.setPhone_number("(+91) 983 093 3937");
	p.setName(Name);
	p.setWebsite("http://rahuldubey.com");
	p.setTypes(list);
	p.setLocation(location);
	return p;
}

public String deletePayload(String placeId){
	return "{\r\n\"place_id\": \""+placeId+"\"\r\n\r\n}";
}
}
