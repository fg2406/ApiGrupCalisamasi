package com.tech.day13;

import baseUrl.DummyBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends DummyBaseUrl {

   /** GetRequestWithPojo01:
    http://dummy.restapiexample.com/api/v1/employee/1 url ‘ine bir get request
    gönderildiğinde , dönen response ‘un,
    Status kodunun 200 ve response body’nin
    {
        "status": "success",
            "data": {
        "id": 1,
                "employee_name": "Tiger Nixon",
                "employee_salary": 320800,
                "employee_age": 61,
                "profile_image": ""
    },
        "message": "Successfully! Record has been fetched."
    }
    Olduğunu test edin*/


     @Test
    public void test(){

     spec03.pathParams("parametre1","employee","parametre2",1);

     Data data=new Data(1,"Tiger Nixon",320800,62,"");
     DummyPojo expectedData=new DummyPojo("success",data,"Successfully! Record has been fetched.");

     System.out.println("expectedData"+expectedData);


     //request gonder
         Response response=given().
                 contentType(ContentType.JSON).
                 spec(spec03).
                 when().
                 get("/{parametre1}/{parametre2}");

   //  response.prettyPrint();

    DummyPojo actualData=response.as(DummyPojo.class);
    System.out.println("actualData"+actualData);

    Assert.assertEquals(200,response.getStatusCode());
    Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());//yukardaki data icindeki status bu
    Assert.assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
    Assert.assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
    Assert.assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
    Assert.assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
    Assert.assertEquals(expectedData.getMessage(),actualData.getMessage());//data icinden ciktik,getData yok burda


      // De-Serialization --> json formattan java ya cevirir (HasMap ile yaptik)
    //Serialization --> java yapisinda olan datalari json formatina
    //Gson sinifindan bir obje uretilerek yapilir

      Gson gson=new Gson();
      String jsonFormJava=gson.toJson(actualData);
      System.out.println("jsonFormJava"+jsonFormJava);





     }





}
