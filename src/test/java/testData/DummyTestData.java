package testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

/**
 http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
 Status kodun 200 olduğunu,
 5. Çalışan isminin "Airi Satou" olduğunu ,
 çalışan sayısının 24 olduğunu,
 Sondan 2. çalışanın maaşının 106450 olduğunu
 40,21 ve 19 yaslarında çalışanlar olup olmadığını
 ve
 11. Çalışan bilgilerinin
 {
 “id”:”11”
 "employee_name": "Jena Gaines",
 "employee_salary": "90560",
 "employee_age": "30",
 "profile_image": "" }
 } gibi olduğunu test edin.
 */
//birde hepsini bir map atacaz,yani 3 map olacak,yaslar,11.calisanin bilgileri ve hepsini bir map atacaz

   public HashMap<String,Object> setUpTestData(){

      //yaslar icin map olustururlur

       List<Integer> yaslar=new ArrayList<Integer>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);



        //11.calisan icin map
       HashMap<String,Object>  onbirinci=new HashMap<>();
       onbirinci.put("id",11);
       onbirinci.put("employee_name","Jena Gaines");
       onbirinci.put("employee_salary",90560);
       onbirinci.put("employee_age",30);
       onbirinci.put("profile_image","");


      HashMap<String,Object> expectedData=new HashMap<String,Object>();
      expectedData.put("statusCode",200);
      expectedData.put("besincicalisan","Airi Satou");
      expectedData.put("calisansayisi",24);
      expectedData.put("sondanikincicalisanmaasi",106450);
      expectedData.put("arananyaslar",yaslar);
      expectedData.put("onbirincicalisan",onbirinci);

       return expectedData;
   }


    /**
     http://dummy.restapiexample.com./api/v1/employees
     Status kodun 200 oldugunu,
     en yüksek maasin 725000 oldugunu,
     En kucuk yasin 19 oldugunu,
     ikinci en yüksek maasin 675000 oldugunu test edin

     */


     public HashMap<String,Integer>  setUpData02(){


         HashMap<String,Integer> expectedData=new HashMap<String,Integer>();

         expectedData.put("statusCode",200);
         expectedData.put("enYuksekMaas",725000);
         expectedData.put("enKucukYas",19);
         expectedData.put("ikinciYuksekMaas",675000);

      return expectedData;


     }

  /** http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
    {
        "name":"Ahmet Aksoy",
            "salary":"1000",
            "age":"18",
            "profile_image": ""
    }
    gönderildiğinde(post yapildiginda), Status kodun 200 olduğunu ve dönen response body nin ,
    {
        "status": "success",
            "data": {
 “id”:…
    },
        "message": "Successfully! Record has been added."
    }
    olduğunu test edin*/


     public  HashMap<String,Object>  setUpRequestBody(){


         HashMap <String,Object>  requestBody=new HashMap<String,Object>();
         requestBody.put("name","flz");
         requestBody.put("salary","12000");
         requestBody.put("age",33);

   return requestBody;

     }



     //biz pnce olusturmak istedigimiz data yi yukarda olsuturduk post classta gonderdik
    //sonrada beklenen response expected data yi burda yazacagiz,postman da biz bunu uyguladik
     /**{
         "status": "success",
             "data": {
         "name": "filiz",
                 "salary": "1200",
                 "age": 33,
                 "id": 7340
     },
         "message": "Successfully! Record has been added."
     }
      buna benzer bir sonuc  donmesi gerekirdi request gonderdigimde
      */


   public HashMap<String,Object>  setUpExpectedData(){

       HashMap<String,Object>  data=new HashMap<String,Object>();
       data.put("name","filiz");
       data.put("salary","1200");
       data.put("age",33);

       HashMap<String,Object>  expectedData=new HashMap<String,Object>();
       expectedData.put("statusCode",200);
       expectedData.put("status","success");
       expectedData.put("data",data);
       expectedData.put("message","Successfully! Record has been added.");


       return expectedData;




   }






}
