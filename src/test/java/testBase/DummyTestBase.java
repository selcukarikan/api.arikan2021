package testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

public class DummyTestBase {

    protected RequestSpecification spec01;

    @Before
    public void setUp(){
        spec01=new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
}
