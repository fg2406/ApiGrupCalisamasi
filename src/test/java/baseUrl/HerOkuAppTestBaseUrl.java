package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppTestBaseUrl {


    protected RequestSpecification spec02;

    @Before

    public  void setUp(){
        //RequestSpecBuilder bir interface biz bundan obje olusturamayiz
        spec02=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();




    }


}
