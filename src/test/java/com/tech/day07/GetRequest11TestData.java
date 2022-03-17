package com.tech.day07;

import baseUrl.JsonPlaceHolderUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest11TestData extends JsonPlaceHolderUrl {

   /**
   "https://jsonplaceholder.typicode.com/todos/2 url'ine istel gonderildiginde
   dönen response in Status codunun 200,
   dönen body de,
   "completed":degerinin false
"title": degerinin "quis ut nam facilis et officia qui"
"userId"sinin 1 ve header degerlerinden
"Via" degerinin "1.1 vegur! ve
"Server" degerinin "cloudflare"oldugunu test edin


  //simdiye kadar 3unu yapmistik bundan sonra diger isikinide yapacaz

  url olustur
  --expected data
  request gönder
  ---actual data  --> bir nevi yapmistik bunu jsonPtah ile al istik actual datayi
  assertion


   */


    @Test
    public void test() {
        //url olustur
        spec01.pathParams("parametre1", "todos", "parametre2", 2);

        //testDta pakagede bir class olusturduk,expectedData orada olusturduk,method yaptik ve obje ile cagiracagiz
        // map ile expectedData olustururuz sonra istek yollariz bize donecek actual ile karsilastiracaz
        // body header hepsini icine attik

        JsonPlaceHolderTestData expectedObje=new JsonPlaceHolderTestData();  //expected bize verilen datalardir
        HashMap<String,Object>  expectedData=expectedObje.setUpTestData(); //diger classtan getirdi,map dondurdu
        System.out.println(expectedData);


        //Request (istek)yolla
        Response response = given().
                accept(ContentType.JSON).
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //response ile yukarda zaten istek gondererek karisilinda actualData yi almis olduk



     /** dönen response in Status codunun 200,
              dönen body de,
      "completed":degerinin false
      "title": degerinin "quis ut nam facilis et officia qui"
      "userId"sinin 1 ve header degerlerinden
      "Via" degerinin "1.1 vegur! ve
      "Server" degerinin "cloudflare"oldugunu test edin  */

        //biz actual kismi response den alabilirz
        //simdi iki sekilde dogrulama yaopacaz

        //1)Matchers class ile ve 2)JsonPath class ile

        response.
                then().
                assertThat().
                statusCode((int) expectedData.    //ama burda int olarak bundan dolayi casting yapilir,integer da yazilir
                        get("statusCode")).
                headers("via", Matchers.equalTo(expectedData.get("via")),  //burada direk deger yok ama yinede tam dinamik degil
                        "Server", equalTo(expectedData.get("Server"))).       //userId falan belki degismez ama body icindeki isimlendirmeler degisecegi
                body("userId", equalTo(expectedData.get("userId")),        //icin tam dinamik degil,biz diger classta bunu daha dinamik yapacaz
                "title", equalTo(expectedData.get("title")),
                "completed", equalTo(expectedData.get("completed")));

        //2.yöntem JsonPath ile
        JsonPath jsonPath = response.jsonPath();   //response den gelen actualdatalari jsonPath ile aliriz

        Assert.assertEquals(expectedData.get("statusCode"), response.statusCode());
        Assert.assertEquals(expectedData.get("via"), response.header("via"));
        Assert.assertEquals(expectedData.get("Server"), response.getHeader("Server")); //headeri matchers ve response ile yaptik
        //bundan sonrasi body kismi jsonPath ile headers dogrulama yapamiyorduk,jsonPath ile body icinde gezebiliriz

        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId")); //OBJECT,INT OLDUGU ICIN CASTING ISTEMEDI GEREK KALMADI
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed")); //false yada true verecek boolean yazilir


        //3.yöntem deserialization ilede olur,iki kutüphane ekledik bunun icin pom a,serialization ve de-seri.icin
        //JsonPath'i Java ya cevirme Serialization denir,body icini aliyoz burda



        HashMap<String,Object> actualData=response.as(HashMap.class);  //as(gibi) demek,response den gelen data'yi map gibi al actualDataya ata demek

         System.out.println(actualData);
         Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));
         Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
         Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
        //bu yöntem daha kisa ,responseden map gibi al demek,actualDta olarak ata demek,sonrada actual data ile expected data ile kiyasla


    }
}