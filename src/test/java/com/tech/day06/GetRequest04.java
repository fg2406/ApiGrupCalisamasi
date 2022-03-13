package com.tech.day06;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest04 extends DummyBaseUrl {


    /*http://dummy.restapiexample.com/api/v1/employees
    url'inde bulunan
status kodun 200,
gelen body de,
5.calisanin isminin "Airi Satou" oldugunu,
6.calisanin maasinin "372000"oldugunu,
Toplam 24 tane calisan oldugunu,
"Rhona Davidson"in employee lerden biri oldugunu
"21","23","61" yaslarinda employeeler oldugunu test edin

      */

   @Test
    public  void  test(){

       spec03.pathParam("parametre1","employees");

       Response response=given().
               accept("application/json").
               spec(spec03).
               when().
               get("/{parametre1}");


             response.prettyPrint();

         //asagidakini yapmasakta olur
             Assert.assertEquals(200,response.getStatusCode());
             Assert.assertTrue(response.getStatusCode()==200);


       //Matchers class ile de olur biz ama JsonPath kullanarak yapariz

       // 5.calisanin isminin "Airi Satou" oldugunu,
       //               6.calisanin maasinin "372000"oldugunu,
       //               Toplam 24 tane calisan oldugunu,
       //               "Rhona Davidson"in employee lerden biri oldugunu
       //       "21","23","61" yaslarinda employeeler oldugunu test edin
        //Jsonpath ile
       JsonPath jsonPath=response.jsonPath();  //response den gelen jsonPath olarak bize getir

       System.out.println(jsonPath.getList("data.id").size()); //gorelim diye yazdik
       Assert.assertEquals(20,jsonPath.getList("data.id").size());
       Assert.assertEquals("Airi Satou",jsonPath.getString("data.employee_name[4]"));
       Assert.assertEquals(372000,jsonPath.getInt("data.employee_salary[5]"));
       Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));


       List<Integer> arananYaslar= Arrays.asList(21,23,61);
      // List<Integer> arananYaslar=new ArrayList<>();
      // arananYaslar.add(21);
       //arananYaslar.add(23);
       //arananYaslar.add(61);


       Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(arananYaslar));



   }


}
