package com.tech.day09;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import testData.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class GetRequest13Matchers extends DummyBaseUrl {



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

      //dogrulamayi matchers ile aparken actualdata yi bir yere atmak yerine direk yazmak gerekiyor.
        //bir önceki classta de-serialization icine aldik yani responstan donen JsonPath seklindeki data'yi java ya cevirdik
        //Simdi matchers class tan dogrulama yapiyorz
       /** Status kodun 200 olduğunu,   (burdan 11.calsian sorusuna kadar bir map olusturacaz)
        5. Çalışan isminin "Airi Satou" olduğunu ,
                çalışan sayısının 24 olduğunu,
                Sondan 2. çalışanın maaşının 106450 olduğunu
        40,21 ve 19 yaslarında çalışanlar olup olmadığını
        ve
        11. Çalışan bilgilerinin   (burda bir map olacak)
        {
     “id”:11
            "employee_name": "Jena Gaines",
                "employee_salary": "90560",
                "employee_age": "30",
                "profile_image": "" }
    } gibi olduğunu test edin.
            */

       response.then().assertThat().statusCode((Integer)expectedDataMap.get("statusCode")).
               body("data[4].employee_name",equalTo(expectedDataMap.get("besincicalisan")),
                       "data.id",hasSize((Integer)expectedDataMap.get("calisansayisi")),
                       "data[-2].employee_salary",equalTo(expectedDataMap.get("sondanikincicalisanmaasi")),
                       "data.employee_age",hasItems( ((List) expectedDataMap.get("arananayaslar")).get(0),
                               ((List) expectedDataMap.get("arananyaslar")).get(1),
                               ((List<?>) expectedDataMap.get("arananyaslar")).get(2)),
                             "data[10].employee_name",equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("employee_name")),
                             "data[10].employee_salary" ,equalTo(((Map)expectedDataMap.get("onbirincicalisan")).get("employee_salary")),
                             "data[10].employee_age",equalTo(((Map)expectedDataMap.get("onbirincicalisan")).get("profile_age")),
                             "data[10].profile_image",equalTo(((Map) expectedDataMap.get("onbirincicalisan")).get("profile_image"))) ;


    }



}
