package com.tech.day06;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 extends HerOkuAppTestBaseUrl {


 /***
    https://restful-booker.herokuapp.com/booking/5 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    "firstname": "Mary",
    "lastname": "Wilson",
    "totalprice": 875,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-08-25",
        "checkout": "2021-11-04"
     */

     @Test
    public  void  test(){

      //1)URL Olustur
       spec02.pathParams("parametre1","booking","parametre2",5);

       //2)Request olustur

         //bazen deneme yanilma yapmak gerekebilir,mesela when silince calisabiliyor
         //importlara dikkat,responce io.restassured burdan olacak

         Response response=given().
                 accept("application/json").spec(spec02).when().get("/{parametre1}/{parametre2}");

                 response.prettyPrint();


         //dogrulama

         //headersda statuscodu dgrulama 1.yontem
         response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

         //2.yontem
         Assert.assertEquals(200,response.getStatusCode());

         //matchers ile normal assert ile yapiliyordu ama body icinden alamiyorduk

        // Assert.assertEquals(200);,response.getStatusCode()); bu sekilde body icine giremeyiz bunun icin Jsonpath ile yapariz dogrulama yani assertion
          //!!!!!!!!JsonPath ile sadece body dogrulama olur!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


         /**status kodunun 200
         ve content type'inin "application/json"

         "firstname": "Mary",
    "lastname": "Wilson",
    "totalprice": 875,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2021-08-25",
        "checkout": "2021-11-04" */

         //JUnit te once expected sonra actual yapilir

         //body icini dogrulama
         JsonPath jsonPath=response.jsonPath();

         Assert.assertEquals("Mary",jsonPath.getString("firstname"));
         Assert.assertEquals("Wilson",jsonPath.getString("lastname"));
         Assert.assertEquals(875,jsonPath.getInt("totalprice"));
         Assert.assertEquals(false,jsonPath.getBoolean("depositpaid"));
         Assert.assertEquals("2021-08-25",jsonPath.getString("bookingdates.checkin"));
         Assert.assertEquals("2021-11-04",jsonPath.getString("bookingdates.checkout"));


         //JsonPath bize sadece body dondurur,ama burda matchers ilede bu dogrulama yapilir
     }





}
