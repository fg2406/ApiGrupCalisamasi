package com.tech.day14;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.JsonUtil;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWihtObjectMapper02 extends HerOkuAppTestBaseUrl {


   /**
   GetRequestWithObjectMapper02:
   https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
  status kodun 200 ve response body’nin
   {
  "firstname": "Mark",
  "lastname": "Wilson",
  "totalprice": 284,
  "depositpaid": false,
  "bookingdates": {
  "checkin": "2016-08-10",
  "checkout": "2018-06-22"
   }
   }
   Olduğunu Object Mapper kullanarak test edi
   *
   *
   * */
    @Test
    public  void  test(){
     spec02.pathParams("parametre1","booking","parametre2",2);


     String jsonData="{\n" +
             "  \"firstname\": \"Mark\",\n" +
             "  \"lastname\": \"Wilson\",\n" +
             "  \"totalprice\": 284,\n" +
             "  \"depositpaid\": false,\n" +
             "  \"bookingdates\": {\n" +
             "  \"checkin\": \"2016-08-10\",\n" +
             "  \"checkout\": \"2018-06-22\"\n" +
             "  }\n" +
             " }";

        //expected data JsonUtil den donen aldik
        HashMap<String,Object> expectedData= JsonUtil.convertJsonToJava(jsonData,HashMap.class);//jsonData String olarak gonderdik
        System.out.println("expectedData"+expectedData);              //bize Java HashMap olarak verdi


       //istek yolla

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

     // response.prettyPrint();

     //actual data aldik
     HashMap<String,Object> actualData=JsonUtil.convertJsonToJava(response.asString(),HashMap.class);

     //karsilastirma
        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpad"),actualData.get("depositpad"));
        Assert.assertEquals((  (Map)expectedData.get("bookingdates")).get("checkin"),
                ((Map) actualData.get("bookingdates")).get("checkin"));

       Assert.assertEquals(((Map<?, ?>) expectedData.get("bookingdates")).get("checkout"),
               ((Map<?, ?>) actualData.get("bookingdates")).get("checkout"));
    }









}
