package com.tech.day12;

import baseUrl.DummyBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.DummyTestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends DummyBaseUrl {

   /**Delete islemi
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test
    edin
    {
    "status": "success",
    "data": "2",
    "message": "Successfully! Record has been deleted"
    }
    */


     @Test
    public void  test(){

      spec03.pathParams("parametre1","delete","parametre2",2);

      //expected data und resquest gonderme
         DummyTestData testData=new DummyTestData();  //obje olusturarak aldik
         JSONObject  expectedData=testData.setUpDeleteExpectedData(); //methodu istedigimiz modelin icine attik

      //request olusturacagiz

         Response response= given().
                accept(ContentType.JSON).
                 spec(spec03).
                 auth().
                 basic("admin","password123").
                 when().delete("/{parametre1}/{parametre2}");

       response.prettyPrint();


       //actualdata yi responseden alip expected ile karsilastiracagiz,3 cesit dogrulama yapariz
      //Matchers ile yapiyoruz

      response.then().assertThat().statusCode(200).
              body("status", equalTo(expectedData.getString("status")),
                      "data",equalTo (expectedData.getString("data")),
                       "message",equalTo(expectedData.getString("message")));

      //JsonPath yontemi ile
         JsonPath json=response.jsonPath();

         Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());

         Assert.assertEquals(expectedData.getString("status"),json.getString("status"));
         Assert.assertEquals(expectedData.getString("data"),json.getString("data"));
         Assert.assertEquals(expectedData.getString("message"),json.getString("message"));






     }





}
