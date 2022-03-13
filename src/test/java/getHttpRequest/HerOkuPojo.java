package getHttpRequest;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingsDatesPojo;
import pojos.HerOkuPojo01;
import testData.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;

public class HerOkuPojo extends HerOkuAppTestBaseUrl {

/**
       https://restful-booker.herokuapp.com/booking
       { "firstname": "Ali",
                  "lastname": "Can",
                  "totalprice": 500,
                  "depositpaid": true,
                  "bookingdates": {
                      "checkin": "2022-03-01",
                      "checkout": "2022-03-11"
                   }
    }
    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,

        */


     @Test
    public  void  test01(){

        spec02.pathParam("parametre","booking");

         BookingsDatesPojo bookingdates=new BookingsDatesPojo("2022-03-01","2022-03-11");

         HerOkuPojo01 expectedData=new HerOkuPojo01("Ali","Can",500,true,bookingdates);
         System.out.println(expectedData);


         //request olustur post ile
         Response response=given().
                 accept(ContentType.JSON).
                 spec(spec02).
                 body(expectedData).
                 when().
                 post("/{parametre}");

         response.prettyPrint();


         //actual data olustur responseden alarak HerokuPojo ya cevir diyecez

         JsonPath jsonPath = response.jsonPath();


         Assert.assertEquals(expectedData.getFirstname(),jsonPath.getString("booking.firstname"));
         Assert.assertEquals(expectedData.getLastname(),jsonPath.getString("booking.lastname"));
         Assert.assertEquals(expectedData.getTotalprice(),jsonPath.getInt("booking.totalprice"));
         Assert.assertEquals(expectedData.isDepositpaid(),jsonPath.getBoolean("booking.depositpaid"));

         Assert.assertEquals(expectedData.getBookingdates().getCheckin(),jsonPath.getString("booking.bookingdates.checkin"));
         Assert.assertEquals(expectedData.getBookingdates().getCheckout(),jsonPath.getString("booking.bookingdates.checkout"));








     }





}
