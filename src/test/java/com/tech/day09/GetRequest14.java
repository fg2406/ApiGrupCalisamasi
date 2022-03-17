package com.tech.day09;

import baseUrl.DummyBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyBaseUrl {

    /**
      http://dummy.restapiexample.com./api/v1/employees
     Status kodun 200 oldugunu,
     en yüksek maasin 725000 oldugunu,
     En kucuk yasin 19 oldugunu,
     ikinci en yüksek maasin 675000 oldugunu test edin



     */

    @Test
    public void test(){

      //url olustur
      spec03.pathParam("parametre1","employees");



       //expected data cagirdik buraya
        DummyTestData expectedObje=new DummyTestData();
        HashMap<String,Integer> expectedDataMap=expectedObje.setUpData02();
        System.out.println("expectedDataMap"+expectedDataMap);

      //request gönderdik
        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");
        response.prettyPrint();

     //Dogrulama yapacagiz,3 yontemle yapacagiz,De-Serialization,Matchers Class,JsonPath ile
       /** Status kodun 200 oldugunu,
                en yüksek maasin 725000 oldugunu,
                En kucuk yasin 19 oldugunu,
                ikinci en yüksek maasin 675000 oldugunu test edin   */


       //1-De-Serializiation yontemi ile

      HashMap<String,Object> actualDataMap=response.as(HashMap.class);
      System.out.println("actualDataMap"+actualDataMap);


      //statusCodunun 200 oldugunu
        Assert.assertEquals(expectedDataMap.get("statusCode"),(Integer)response.getStatusCode());

      //en yüksek maasin 725000 oldugunu/hepsini bir for icine aliriz cunku biz han
         List<Integer> maasListesi=new ArrayList<Integer>();
        int datasize= ((List)actualDataMap.get("data")).size();

        for(int i=0; i<datasize; i++) {

            maasListesi.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));

        }
        //listeledik buyukten kucuge dogru
        Collections.sort(maasListesi);

        //sondan birinci yani en yuksek maasi aldi
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(maasListesi.size()-1));

        //en yuksek 2.maas
        Assert.assertEquals(expectedDataMap.get("ikinciYuksekMaas"),maasListesi.get(maasListesi.size()-2));

        //en buyuk kucuk 19 oldugunu
        List<Integer> yasListesi=new ArrayList<Integer>();

        for (int i=0;i<datasize; i++){

             yasListesi.add( (Integer)((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age") );

        }
        Collections.sort(yasListesi);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesi.get(0));

        //2-Jsonpath yontemi ile
        JsonPath json=response.jsonPath();

        //en yüksek maasin 725000 oldugunu

        List<Integer> maasListesiJson=json.getList("data.employee_salary");

        Collections.sort(maasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesiJson.get(maasListesi.size()-1));

        //en yuksek 2.maas

         Assert.assertEquals(expectedDataMap.get("ikinciYuksekMaas"),maasListesiJson.get(maasListesiJson.size()-2));

        //en kucuk yasin 19 oldugunu

        List<Integer>  yasListesiJson=json.getList("data.employee_age");

        Collections.sort(yasListesiJson);

        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesiJson.get(0));









    }
}