package com.tech.day14;

import baseUrl.JsonPlaceHolderUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends JsonPlaceHolderUrl {


     /**GetRequestWithObjectMapper01:
   https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
  Dönen response ‘un status kodunun 200 ve body kısmının
  {
   "userId": 10,
   "id": 198,
   "title": "quis eius est sint explicabo",
   "completed": true
  }
   Olduğunu Object Mapper kullanarak test edin*/

    @Test
    public  void test(){

    spec01.pathParams("parametre1","todos","parametre2",198);


    //olmasini istedigim data yi gelip yapistirdik,testData packe yerine burdan gonderdik
        String jsonData="{\n" +
             " \"userId\": 10,\n" +
             "\"id\": 198,\n" +
             "\"title\": \"quis eius est sint explicabo\",\n" +
             " \"completed\": true\n" +
             "  }\n" +
             "";
        //expectedData
        HashMap<String,Object>  expectedData= JsonUtil.convertJsonToJava(jsonData,HashMap.class);//map te olurdu
        System.out.println("expectedData"+expectedData);


        //request gonder,response den jelen Json fotmattir

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
        //response.prettyPrint(); Jso);n formatinda

        //response den gelne Json fomatta biz java ya cevirerek kullanacaz
        HashMap<String,Object> actualData=JsonUtil.
                convertJsonToJava(response.asString(),HashMap.class);//asString ile cevir dedik

        System.out.println("actualData"+actualData);


        //simdi dogrulama

        Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));


    }




}
