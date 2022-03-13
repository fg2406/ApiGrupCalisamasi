package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderUrl {


    protected RequestSpecification spec01;

    @Before

    public void setUp() {
        //RequestSpecBuilder bir interface biz bundan obje olusturamayiz
        spec01 = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();


    }
}