package com.tech.day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import baseUrl.HerOkuAppTestBaseUrl;

import testData.HerOkuappTestData;


public class GetRequest12 extends HerOkuAppTestBaseUrl {

   /*
    https://restful-booker.herokuapp.com/booking/1 url'ine
    GET request'i yolladigimda gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
     "firstname": "Eric",
    "lastname": "Jackson",
    "totalprice": 555,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2020-12-20",
        "checkout": "2021-05-03"   gibi oldugunu test edin
        */


    @Test
    public  void test() {


      spec02.pathParams("parametre1","booking","parametre2","1");

       //expected data,testData da olusturduk cagirdik
       HerOkuappTestData expectedObje=new HerOkuappTestData();
       HashMap<String,Object>expectedDataMap=expectedObje.setUpTestData();
       System.out.println(expectedDataMap);

        //resquest gonderirim

        Response response= RestAssured.given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();




        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(   ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map) actualDataMap.get("bookingdates")).get("checkin"));

        Assert.assertEquals(   ((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map) actualDataMap.get("bookingdates")).get("checkout")  );


        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                ((Map)actualDataMap.get("bookingdates")).get("checkin")
        );


        // 2. yol JsonPath

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("firstname"),jsonPath.getString("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),jsonPath.getString("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),jsonPath.getInt("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(  ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map) expectedDataMap.get("bookingdates")).get("checkout"),
                jsonPath.getString("bookingdates.checkout"));








    }








}
