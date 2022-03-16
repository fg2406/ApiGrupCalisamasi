package getHttpRequest;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingsDatesPojo;

import static io.restassured.RestAssured.given;

public class HerOkuPojo1 extends HerOkuAppTestBaseUrl {


 /**
 https://restful-booker.herokuapp.com/booking
 request body
 { "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }}
Status code is 200
 response body
 {
    "bookingid": 11,
       "booking": {
         "firstname": "Ali",
         "lastname": "Can",
         "totalprice": 500,
         "depositpaid": true,
         "bookingdates": {
            "checkin": "2022-03-01",
            "checkout": "2022-03-11"
                             }
                         }
                     }
  */
    @Test
    public void test(){


        spec02.pathParams("1","booking");

        BookingsDatesPojo bookingdates = new BookingsDatesPojo("2022-03-01","2022-03-11");

        // BookingPojo booking = new BookingPojo("Ali","Can",500,true,bookingdates);

        // BookingResponsePojo bookingResponse= new BookingResponsePojo(11, booking);

        //Response rs = given().contentType(ContentType.JSON).spec(spec02).auth().basic("admin","password123")
              //  .body(booking).when().post("/{1}");
        //resprs.prettyPeek();









    }
}