package getHttpRequest;

import baseUrl.JsonPlaceHolderUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPojo01;

public class JsonPlaceHolderPojo extends JsonPlaceHolderUrl {
    //syntex hatasi oldu mu F2 basinca ustune goturur
    /**
     * {
     * "userId": 1,
     * "id": 3,
     * "title": "fugiat veniam minus",
     * "completed": false
     * }
     */


    @Test
    public void test01() {
        //url olustur
        spec01.pathParams("parametre1", "todos", "parametre2", 3);


        //expected datayi getirdik
        JsonPojo01 expectedData = new JsonPojo01(1, 3, "fugiat veniam minus", false);
        System.out.println("expectedData" + expectedData);


        //request gonderdik
        Response response = RestAssured.given().accept(ContentType.JSON).spec(spec01).when().get("/{parametre1/parametre2}");

        response.prettyPrint();

        //response den gelen datayi JsonPojo gibi getir
        JsonPojo01 actualData = response.as(JsonPojo01.class);
        System.out.println(actualData + "actualData");


        //karsilastir dohrulama
        Assert.assertEquals(expectedData.getUserId(), actualData.getUserId());
        Assert.assertEquals(expectedData.getId(), actualData.getId());
        Assert.assertEquals(expectedData.getTitle(), actualData.getTitle());
        Assert.assertEquals(expectedData.isCompleted(), actualData.isCompleted());


    }


}
