package testData;

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
}