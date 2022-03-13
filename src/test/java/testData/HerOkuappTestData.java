package testData;

import java.util.HashMap;

public class HerOkuappTestData {

/*
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

}
