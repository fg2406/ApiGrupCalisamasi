package com.tech.day10;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.HerOkuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerOkuAppTestBaseUrl {

    /**
https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
{ "firstname": "Selim",
 "lastname": "Ak",
 "totalprice": 11111,
 "depositpaid": true,
 "bookingdates": {
 "checkin": "2020-09-09",
 "checkout": "2020-09-21"
 }
}gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
"booking": {
 "firstname": " Selim ",
 "lastname": " Ak ",
 "totalprice": 11111,
 "depositpaid": true,
 "bookingdates": {
 "checkin": "2020-09-01",
 "checkout": " 2020-09-21”
 },
 }
olduğunu test edin    */



   @Test
    public void  test(){
       //url olustur
       spec02.pathParam("parametre1","booking");

       //resquestBody ve expected data ayni oldugu icin tek bir JSONObject kullanilmasi yeterlidir
       HerOkuappTestData herokuData=new HerOkuappTestData();
       JSONObject expectedRequestData=herokuData.setUpTestAndRequestData();  //hasMap yerine post islemlerinde JSONObject kullanilr
       System.out.println("expectedRequestData :"+expectedRequestData);


       //request gönder//!!Request gönderilirken body içerisinde toString metodu kullanılması gerekiyor.!!
       Response response=given().
               contentType(ContentType.JSON).  //accept yazinca hata verdi
               spec(spec02).
               auth().basic("admin","password123").
               body(expectedRequestData.toString()).
               when().
               post("/{parametre1}");

       //response.prettyPrint();

       // response den gelen actualdata alip karsilastirma yapacaz
       //3 yontem vardi,De-Seriaziliation,Matchers,Json claas ile


        //De-Seriaziliation yontemi ile

       HashMap<String,Object> actuelDataMap=response.as(HashMap.class);
       //JSONObje yamaadik a icine kabul etmedi
       System.out.println("actualDataMap :"+actuelDataMap);


       Assert.assertEquals(expectedRequestData.getString("firstname"),((Map)actuelDataMap.get("booking")).get("firstname"));
       Assert.assertEquals(expectedRequestData.getString("lastname"),((Map<?, ?>) actuelDataMap.get("booking")).get("lastname"));
       Assert.assertEquals(expectedRequestData.getInt("totalprice"),((Map<?, ?>) actuelDataMap.get("booking")).get("totalprice"));
       Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),((Map<?, ?>) actuelDataMap.get("booking")).get("depositpaid"));
       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
               ((Map) ((Map<?, ?>) actuelDataMap.get("booking")).get("bookingdates")).get("checkin"));

       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
               ((Map) ((Map<?, ?>) actuelDataMap.get("booking")).get("bookingdates")).get("checkout"));


       //JsonPath yontemi ile:Casting yapmaya gerek yok bu yontemde

       JsonPath json=response.jsonPath();

       Assert.assertEquals(expectedRequestData.getString("lastname"),json.getString("booking.lastname"));
       Assert.assertEquals(expectedRequestData.getString("firstname"),json.getString("booking.firstname"));
       Assert.assertEquals(expectedRequestData.getInt("totalprice"),json.getInt("booking.totalprice"));
       Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),json.getBoolean("booking.depositpaid"));
       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),json.getString("booking.bookingdate.checkin"));
       Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),json.getString("booking.bookingdate.checkout"));





   }






}
