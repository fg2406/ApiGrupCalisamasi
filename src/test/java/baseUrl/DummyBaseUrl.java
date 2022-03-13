package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.Request;

public class DummyBaseUrl {


    // http://dummy.restapiexample.com/api/v1/employees

    protected RequestSpecification spec03;

    @Before
    public void setUp(){

        spec03=new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();      //builder insa et demek
    }


}
