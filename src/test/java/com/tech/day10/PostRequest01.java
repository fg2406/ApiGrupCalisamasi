package com.tech.day10;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyBaseUrl {


  /**  http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
    {
        "name":"Ahmet Aksoy",
            "salary":"1000",
            "age":"18",
            "profile_image": ""
    }
    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
    {
        "status": "success",
            "data": {
 “id”:…
    },
        "message": "Successfully! Record has been added."
    }
    olduğunu test edin  */


  @Test
    public  void  test(){
       //url olusturdum
      spec03.pathParam("parametre1","create");


      //expected data aldim
      DummyTestData obje=new DummyTestData();

      //post request yaparken biz body gondermek zorundayiz,test data da olusturdugumuzt resquest body burda cagiracagz

      HashMap<String,Object> requestBodyMap=obje.setUpRequestBody();  //bu istek olarak olusturdugum datalar
      HashMap<String,Object>  expectedDataMap=obje.setUpExpectedData();  //bu bekledigim datalar


      //sistemde degisliklik yapmak icin o sistemin bizim  kimligi tanimasi lazim,bunu ici
      //bunun icin Authorization devereye girer,ordan uygun olan sitekin veridigi password bilgi neyse
      //onu kullanarak sisteme girer islem yapariz,


      Response response=given().
            accept(ContentType.JSON).
            auth().basic("admin","password123").  //islem yapmak icin verilen password,isim girildi,basic girdik ama baska seylerde var sirket veriyor
            spec(spec03).body(requestBodyMap).
            when().
            post("/{parametre}");

       response.prettyPrint();


          //karsilastirma yapacaz,actual expected ile
          //De Serialization yöntemi ile

          HashMap<String,Object> actualDataMap=response.as(HashMap.class);
          Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
          Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
          Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

          //JsonPath
          JsonPath json=response.jsonPath();
          Assert.assertEquals(expectedDataMap.get("status"),json.getString("status"));
          Assert.assertEquals(expectedDataMap.get("message"),json.getString("message"));









  }






}
