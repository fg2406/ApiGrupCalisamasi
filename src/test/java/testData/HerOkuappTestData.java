package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuappTestData {

/**
    https://restful-booker.herokuapp.com/booking/1 url'ine
    GET request'i yolladigimda gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
            "firstname": "Eric",
            "lastname": "Jackson",
            "totalprice": 555,
            "depositpaid": false,
            "bookingdates": {
        "checkin": "2020-12-20",
                "checkout": "2021-05-03"   gibi oldugunu test edin
        */



    public HashMap<String,Object>  setUpTestData(){


        //datalarda ic ice body varsa bizde map leeri ayri olustururz ve bir araya getiririz

        HashMap<String,Object> bookingdates=new HashMap<String,Object>();
        bookingdates.put("checkin","2020-depositpaid12-20");
        bookingdates.put("checkout","2021-05-03");

        HashMap<String,Object> expectedData=new HashMap<String,Object>();
        expectedData.put("firstname","Eric");
        expectedData.put("lastname",555);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingdates);

        return expectedData;


    }


   /** https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
    { "firstname": "Selim",
            "lastname": "Ak",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
        "checkin": "2020-09-09",
                "checkout": "2020-09-21"
    }
    }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
"booking": {
        "firstname": " Selim ",
                "lastname": " Ak ",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2020-09-01",
                    "checkout": " 2020-09-21”
        },
    }
    olduğunu test edin  */


      public JSONObject  setUpTestAndRequestData(){

          JSONObject bookingdates=new JSONObject();
          bookingdates.put("checkin","2021-01-05");
          bookingdates.put("checkout","2021-01.10");

          JSONObject expectedRequest=new JSONObject();
          expectedRequest.put("firstname","azKaldi");
          expectedRequest.put("lastname","bitiyor");
          expectedRequest.put("totalprice",123);
          expectedRequest.put("depositpaid",true);
          expectedRequest.put("bookingdates",bookingdates);

           return  expectedRequest;


      }




}







