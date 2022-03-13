package com.tech.day07;

import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyBaseUrl {

   /*http://dummy.restapiexample.com/api/v1/employees
    url'inde bulunan
   status kodun 200,
    gelen body de,

    1)10'dan buyuk tum id'leri ekrana yazdirin
    ve 10'dan buyuk 14 id oldugunu
    2)30'dan kücük tüm yaslari ekrana yazdirin ve bu yaslarin icersinde en buyuk yasin 23 oldugunu
    3)Maasi 350000 den buyuk olan tüm employee name'leri ekrana yazdirin ve bunlerin icersinde"Charde Marshall"oldugunu
    test edin
     */


    @Test
    public void test() {

        //url olusturduk
        spec03.pathParam("parametre1", "employees");


        //istek gonderdik
        Response response = given().
                accept(ContentType.JSON).
                spec(spec03).
                when().
                get("/{parametre1}");  //{} yaparak sayisal degerini almaya calisiyoruz


        //Statuscode 200 oldugunu dogrulama
        Assert.assertEquals(200, response.getStatusCode());

        // gelen body de,
        //  1)10'dan buyuk tum id'leri ekrana yazdirin
        // ve 10'dan buyuk 14 id oldugunu


        JsonPath jsonPath = response.jsonPath();

        List<Integer> idList = jsonPath.getList("data.findAll{it.id>10}.id");//bu dil Groovy 'dir yani javanin bir alt dilidir

        //it id isaret ediyor,ingilizcedeki mantik,bu bir formul gibi

        //1)10'dan buyuk tum id'leri ekrana yazdirin
        System.out.println(idList);
        // ve 10'dan buyuk 14 id oldugunu
        Assert.assertEquals(14, idList.size());

        // 2)30'dan kücük tüm yaslari ekrana yazdirin
        List<Integer> yasListesi = jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasListesi);

        // ve bu yaslarin icersinde en buyuk yasin 23 oldugunu

        Collections.sort(yasListesi);
        Assert.assertEquals((Integer) 23, yasListesi.get(yasListesi.size() - 1));  //casting yapariz
        //Assert.assertEquals(23,(int)yasListesi.get(yasListesi.size()-1));     //casting yaptik

        // birinden birine cevirmemiz lazim,cunku biri primitive digeri nonprimitive
        //ikiside ayni sonucu verir,casting ile
        //get ile icinden bir deger getirilince hata veriyor,

        // 3)Maasi 350000 den buyuk olan tüm employee name'leri ekrana yazdirin

        List<String> isimListesi=jsonPath.getList ("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(isimListesi);

        // ve bunlerin icersinde"Charde Marshall"oldugunu test edin

          Assert.assertTrue(isimListesi.contains("Charde Marshall"));
    }

}