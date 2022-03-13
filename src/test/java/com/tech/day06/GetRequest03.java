package com.tech.day06;

import baseUrl.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest03 extends DummyBaseUrl {

    /*http://dummy.restapiexample.com/api/v1/employees
    url'inde bulunan
    1)Butun calisanlarin isimlerini consola yazdiralim
    2)3.calisan kisinin ismini konsola yazdirin
    3)ilk 5 calisanin adini consola yazdirin
    4) En son calisanin adini konsola yazdrialim

     */

    @Test
    public  void  test(){

     //url olusturma
      spec03.pathParam("parametre1","employees")  ;

      //request gonderme
        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

     //1)Butun calisanlarin isimlerini consola yazdiralim

        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name")); //list yerine String ilede olur
        //for icine butun bilgileri atip,sonra 3.,ilk 5 te bulunabilir,for ile daha dinamik olur

        //data icinde index ile gezilebilir

        //    2)3.calisan kisinin ismini konsola yazdirin

        System.out.println(jsonPath.getString("data[2].employee_name"));  //[2] nameden sonrada yazilir

        //    3)ilk 5 calisanin adini consola yazdirin
        System.out.println(jsonPath.getString("data[0,1,2,3,4].employee_name"));

        //    4) En son calisanin adini konsola yazdrialim

        System.out.println(jsonPath.getString("data.employee_name[-1]"));


        //dogrulama bu soruda istenmemis ama biz gormek adina yapalim

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("Tiger Nixon",jsonPath.getString("data.employee_name[1]"));

    }




}
