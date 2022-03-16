package com.tech.day09;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyBaseUrl {



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
    public  void test() {

        //url olustur
        spec03.pathParam("parametre1", "employees");

        //obje ureterek test datayi expected data yi alacaz
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Object> expectedDataMap = expectedObje.setUpTestData();
        System.out.println("expectedDataMap" + expectedDataMap);


        //request gonderme
        Response response = given().
                accept(ContentType.JSON).
                spec(spec03).
                when().
                get("/{parametre1}");

        // response.prettyPrint();


       //dohrulama JsonPah ile


        /**Status kodun 200 olduğunu,   (burdan 11.calsian sorusuna kadar bir map olusturacaz)
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
                "profile_image": "" }   */

        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besincicalisan"),json.getString("data[4].employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisansayisi"),json.getList("data.id").size());
        Assert.assertEquals(expectedDataMap.get("sondanikincicalisanmaasi "),json.getInt("data[-2].employee_salary"));
        Assert.assertTrue(json.getList("data.employee_age").containsAll((Collection<?>) expectedDataMap.get("arananyaslar")));
        Assert.assertEquals(((Map)expectedDataMap.get("onbrincicalisan")).get("id"),json.getInt("data[10].id"));

        Assert.assertEquals( ((Map)expectedDataMap.get("onbirincicalisan")).get("employee_name"),
                json.getString("data.employee_name"));
        Assert.assertEquals( ((Map)expectedDataMap.get("onbirincicalisan")).get("profile_image"),
                json.getString("data[10].profile_image"));




    }

}
