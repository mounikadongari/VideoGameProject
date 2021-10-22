package com.videogame.testcases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC_VideoGamesAPI {

	                //GET all ids
	//@Test(priority=1)
	public void test_getAllVideogames()
	{
		given()
		//we dont have any Pre-requists
		
		.when() //get request
		   .get("http://localhost:8080/app/videogames")
		
		   .then() //do Validation here
		   .statusCode(200);
	}
	
	                 //POST SINGLE iD=200
	@Test(priority=2)
	public void test_addNewVideoGame()
	{
		HashMap data = new HashMap();
		data.put("id","200");
		data.put("name","MOUNI");
		data.put("releaseDate","2021-10-22T10:34:20.390Z");
		data.put("reviewScore","90");
		data.put("category","Driving");
		data.put("rating","Mature");
		
		//creating res variable to store the response
		Response res =
		given() //--------Request-Payload
		   .contentType("application/json")
		   .body(data)
		.when()
		    .post("http://localhost:8080/app/videogames")
		.then()
		   .statusCode(200)
		   .log().body() // to print the response body in the console
		   .extract().response();
		   
	       String jsonString=res.asString();
	       Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);
	
	
	}
	 
	       //GET specific ID
	@Test(priority=3)
	public void test_getVideoGameOfSpecificID()
	{
		
		given() //its get request so no need prerequists
		 .when()
		    .get("http://localhost:8080/app/videogames/200")
		  .then()
		     .statusCode(200)//******we will response in XML-Format
		 //    .body("videoGame.id",equalTo("100"))
		 //    .body("videoGame.name",equalTo("MOUNIKA"))
		     .log().body();
		     
	}
	
    
	         //PUT update id of 200
	@Test(priority=4)
	public void test_UpdateVideoGame()
	{
		HashMap data = new HashMap();
		data.put("id","101");
		data.put("name","MounikaDongari");
		data.put("releaseDate","2021-10-22T10:34:20.390Z");
		data.put("reviewScore","90");
		data.put("category","Driving");
		data.put("rating","Mature");
		
	given() //RequestPayload
		
		  .contentType("application/json")
		  .body(data)
	.when()
	     .put("http://localhost:8080/app/videogames/200")
		  
    .then()
      .statusCode(200)
      .log().body();
	 //    .body("videoGame.id",equalTo("101"))
	 //    .body("videoGame.name",equalTo("MounikaDongari"))
 
		 
	}
	        //Delete request
	@Test(priority=5)
	public void test_DeleteVideoGame() throws InterruptedException
	{
		Response res=
		given()
		 .when()
		   .delete("http://localhost:8080/app/videogames/101")
		 .then()
		   .statusCode(200)
		   .log().body()
		   .extract().response();
		
		Thread.sleep(3000);
		  String jsonstring= res.asString();
		  Assert.assertEquals(jsonstring.contains( "Record Deleted Successfully"),true);
		
	}
}