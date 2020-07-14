package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqspec;
	ResponseSpecification resspec;
	JsonPath js;
	
public RequestSpecification requestSpec() throws IOException{
	
	if(reqspec==null){
	PrintStream file=new PrintStream(new FileOutputStream("logfile.txt"));
	reqspec=new RequestSpecBuilder().setBaseUri(readPropertyFile("baseURI")).addQueryParam("key", "qaclick123")
			.setContentType(ContentType.JSON)
			.addFilter(RequestLoggingFilter.logRequestTo(file))
			.addFilter(ResponseLoggingFilter.logResponseTo(file))
			.build();
	
	return reqspec;
	}
	return reqspec;
}

public ResponseSpecification responseSpec(){
	resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
	return resspec;
}

public String readPropertyFile(String key) throws IOException{
	Properties pro=new Properties();
	FileInputStream fis=new FileInputStream("C:\\Users\\Anurag\\workspace\\APIFrameWork\\src\\test\\java\\resources\\global.properties");
	pro.load(fis);
	return pro.getProperty(key);
}

public String getKeyValue(Response response,String Key){
	 js=new JsonPath(response.asString());
	 return js.get(Key).toString();
}
}
