package com.tech.day08;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.HerOkuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

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


      spec02.pathParams("parametre1","booking","parametre2",1);







    }






}
