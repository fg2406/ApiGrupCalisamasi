package com.tech.day12;

import baseUrl.JsonPlaceHolderUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.TodosPojo;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class PostRequestWithPojo01 extends JsonPlaceHolderUrl {

    /**
     PostRequestWithPojo01:
      https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
      Request body {
       "userId": 21,
       "id": 201,
       "title": "Tidy your room",
       "completed": false
       }
      Status kodun 201, response body ‘nin ise
      {
       "userId": 21,
       "id": 201,
      "title": "Tidy your room",
      "completed": false
      } olduğunu test edin
     */


    @Test
    public  void  test(){
      spec01.pathParam("parametre1","todos");


      //expected aldik,gitti toString ile yapti
      TodosPojo requestExpected=new TodosPojo(21,201,"Tidy your room",false);
      System.out.println("requestExpected"+requestExpected);

    //repuest gonderme

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).auth().basic("admin","password123").
                body(requestExpected).when().post("/{parametre1}");
        response.prettyPrint();

    //DE Serialization
    TodosPojo actualData=response.as(TodosPojo.class); //map yerine pojoda bu sekilde olacak

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(requestExpected.getUserId(),actualData.getUserId());
        Assert.assertEquals(requestExpected.getId(),actualData.getId());
        Assert.assertEquals(requestExpected.getTitle(),actualData.getTitle());
        Assert.assertEquals(requestExpected.isCompleted(),actualData.isCompleted());



    }



}
