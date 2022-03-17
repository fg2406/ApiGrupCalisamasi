package com.tech.day11;

import baseUrl.JsonPlaceHolderUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderUrl {

    /**
     PostRequest03:
     https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
     }
     Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu
     test edin
     {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …*/

    @Test
    public void test(){

        //url olustur
        spec01.pathParam("parametre1","todos");

        //expected data cagir

        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=testObje.setUpPostData();

        System.out.println("expectedRequest"+expectedRequest);

        //istek yolluyoruz

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin","password123").
                body(expectedRequest.toString()).
                when().
                post("/{parametre1}");

        response.prettyPrint();

        //dogrulama 3 cesit oluyor,matchers,JsonPath,De-Serizaitire
       //1-Matchers class ile

        response.then().assertThat().statusCode( (Integer)expectedRequest.get("statusCode")).
                body("completed", equalTo(expectedRequest.getString("completed")),
                        "title",equalTo(expectedRequest.getString("title")),
                        "userId",equalTo(expectedRequest.getInt("userId")));

        //2-JsonPath ile (cast yok bunda)
        JsonPath json=response.jsonPath();

        Assert.assertEquals(expectedRequest.getInt("statusCode"),response.getStatusCode()); //response alindi headers
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));  //body kismi JsonPath ile
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));


         //3-De_Serialization ile//HashMap ile response as ile olusturulur

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),actualDataMap.get("completed"));


    }




}
