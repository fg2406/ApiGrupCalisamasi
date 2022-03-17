package testData;

import org.json.JSONObject;

import java.util.HashMap;

public class JsonPlaceHolderTestData {


    public HashMap<String, Object> setUpTestData() {


        HashMap<String, Object> expectedData = new HashMap<String, Object>();  //sürekli HasMap yapabilirsin
        //body icin key value seklinde ve hem String,
        // hem int hem boolean oldugu icin object aldik,hashmap karisik yerlestirir index gore degil
        //key-value ile

        expectedData.put("statusCode", 200);  //200 obje olarak
        expectedData.put("completed", false);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        System.out.println(expectedData);
        return expectedData;

    }


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
     "id": …* */

     public  JSONObject  setUpPostData(){
          //post olustururken map kullanilirken ayni zamanda JSONObect ile de yapilir

         JSONObject expectedRequest=new JSONObject();
         expectedRequest.put("userId",55);
         expectedRequest.put("title","Tidy your room");
         expectedRequest.put("completed",false);

       return expectedRequest;

     }



}