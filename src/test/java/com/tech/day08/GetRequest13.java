package com.tech.day08;

import baseUrl.DummyBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.*;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyBaseUrl {


 /**
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,   (burdan 11.calsian sorusuna kadar bir map olusturacaz)
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
  ve
11. Çalışan bilgilerinin   (burda bir map olacak)
    {
 “id”:”11”
        "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
} gibi olduğunu test edin.
*/


 @Test
    public  void test(){

     //url olustur
     spec03.pathParam("parametre1","employees");

     //obje ureterek test datayi expected data yi alacaz
       DummyTestData expectedObje=new DummyTestData();
       HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();
       System.out.println("expectedDataMap"+expectedDataMap);


      //request gonderme
           Response  response=given().
            accept(ContentType.JSON).
            spec(spec03).
            when().
            get("/{parametre1}");

    // response.prettyPrint();


     //gonderilen request'in responseden alarak actual data ile expexted karsilatirma yapilacak
     //De-Serialization islemi(Java dan JsonPath cevirme)

      HashMap<String,Object> actualDataMap=response.as(HashMap.class);
      System.out.println("actualData"+actualDataMap);

     //!!!Status kodun 200 olduğunu--statusCodu response den aliyorz!!!
       Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());


       //!!!!!5.Çalışan isminin "Airi Satou" olduğunu!!!,[]list datalar var icinde,{} map var icinde
     //actual data sonucunda once list sonra map sonra list icinde datalar var

     Assert.assertEquals(((Map) expectedDataMap.get("arananyaslar")).get("besincicalisan"),

             ((Map) ((List) actualDataMap.get("data")).get(4)).get("employee_name"));



     //listlerde index ile aliyorduk degeri,bu yuzden actual.get icine index yazdik
     // map lerde key-value degeri ile aliyorz degeri,bu yuzden get icine key gibi yazdik
    //gelen actualData da map icinde list gibi geldi bize
     //!!!!!!çalışan sayısının 24 olduğunu!!!!!
      Assert.assertEquals(((List) expectedDataMap.get("arananyaslar")).get(24),
              ((List) actualDataMap.get("data")).size());


      //Sondan 2. çalışanın maaşının 106450 olduğunu
      //once actual data'dan bize dönen list'in size 'ini almaliyiz
      int dataSize=((List) actualDataMap.get("data")).size();
      Assert.assertEquals(expectedDataMap.get("sondanikincicalisanmaasi"),
              ((Map) ((List<?>) actualDataMap.get("data")).get(dataSize - 2)).get("employee_salary"));


      //40,21 ve 19 yaslarında çalışanlar olup olmadığını
       List<Integer> actualYasListesi=new ArrayList<Integer>();
       for (int i=0;i<dataSize;i++){

       actualYasListesi.add(((Integer) ((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age")));


       }

      Assert.assertTrue(actualYasListesi.containsAll((List) expectedDataMap.get("arananyaslar")));


     /**

      11. Çalışan bilgilerinin   (burda bir map olacak)
    {
 “id”:”11”
        "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
} gibi olduğunu test edin.*/

      
      Assert.assertEquals(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_name"),

              ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_name"));

      Assert.assertEquals( ((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_salary"),
              ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

      Assert.assertEquals(  ((Map<?, ?>) expectedDataMap.get("onbirincicalisan")).get("employee_age"),

              ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

      Assert.assertEquals( ((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image"),

              ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));


 }




}




