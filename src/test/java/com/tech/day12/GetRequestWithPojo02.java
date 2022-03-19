package com.tech.day12;

import baseUrl.HerOkuAppTestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import pojos.BookingsDatesPojo;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo02 extends HerOkuAppTestBaseUrl {

 /**
 PostRequestWithPojo02:
https://restful-booker.herokuapp.com/booking
url’ine aşağıdaki request body gönderildiğinde,
{
 "firstname": "Selim",
 "lastname": "Ak",
 "totalprice": 15000,
 "depositpaid": true,
 "bookingdates": {
 "checkin": "2020-09-09",
 "checkout": "2020-09-21"
 }
}
Status kodun 200 ve dönen response ‘un
{
 "bookingid": 11,
 "booking": {
 "firstname": "Selim",
 "lastname": "Ak",
 "totalprice": 15000,
 "depositpaid": true,
 "bookingdates": {
 "checkin": "2020-09-09",
 "checkout": "2020-09-21"
  */


  @Test
    public void  test(){

    spec02.pathParam("parametre1","booking");

    //test datasi olurtur,expected datayi cagir
     //request icin iki tanesini cagirdik

      BookingsDatesPojo bookingsDates=new BookingsDatesPojo("2020-09-09","2020-09-21");
      BookingPojo bookingPojo=
              new BookingPojo("Selim","Ak",15000,true,bookingsDates);

      System.out.println("bookingPojo"+bookingPojo);

    //request gonder
      Response response=given().
              contentType(ContentType.JSON).
              spec(spec02).
              auth().basic("admin","password123").
              body(bookingPojo).
              when().post("/{parametre1}");

      response.prettyPrint();

     //actualdata ile dogrulama

      BookingResponsePojo actualData=response.as(BookingResponsePojo.class);

      Assert.assertEquals(200,response.getStatusCode());
      //expectedata yi booknig ile yukarda aldik
      Assert.assertEquals(bookingPojo.getFirstname(),actualData.getBooking().getFirstname());
      Assert.assertEquals(bookingPojo.getLastname(),actualData.getBooking().getLastname());
      Assert.assertEquals(bookingPojo.getTotalprice(),actualData.getBooking().getTotalprice());
      Assert.assertEquals(bookingPojo.isDepositpaid(),actualData.getBooking().isDepositpaid());

      Assert.assertEquals(bookingPojo.getBookingdates().getCheckin(),
              actualData.getBooking().getBookingdates().getCheckin());

      Assert.assertEquals(bookingPojo.getBookingdates().getCheckout(),
              actualData.getBooking().getBookingdates().getCheckout());




  }




}
