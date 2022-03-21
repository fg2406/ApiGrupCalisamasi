package com.tech.day14;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ReqresToken {

   //herhangi birseye ihtiyaca gerek yok,birkez kullanacagiz,token alacagiz,post isleminde token gekiyor
    //simdiye kadar token ile islem yapmadik burda,bazi api ler token ile calisr ve tokenler belli aralikla degisebilir

    @Test
    public  String TokenAl(){

       String url="https://reqres.in/api/login";

        HashMap<String,String> requestBody=new HashMap<String,String>();
        requestBody.put("email","eve.holt@reqres.in");  //gidip url den aldik body nasil olmali
        requestBody.put("password","cityslicka");
        //System.out.println("repuestBody"+requestBody);

      //request yolla
        Response response=given().   //spec yapmadik tek kez kullanacagimiz site oldugu icin extends yapmadik
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post(url);  //endpoint burasi


        //response.prettyPrint();

        JsonPath  json=response.jsonPath();
        String token= json.getString("token");
        System.out.println("token :"+token);
        return token;
      //ben bu classi cagirdigimda bana gelen token'i dondursun istiyorum ki kullanabileyim
      //token'i ben put,patch,post autoriziation bilgileri yerine ben bunu gonderirim
        //nasil dondurecek bu yuzden bir return olmali


    }

    //pojo dan sonra object mapper kullanacagiz ve pojo gerek kalmayacak



}
