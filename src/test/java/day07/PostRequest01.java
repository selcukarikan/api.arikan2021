package day07;

import io.restassured.response.Response;
import org.junit.Test;
import testBase.DummyTestBase;
import testData.DummyTestData;

import java.awt.geom.RectangularShape;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
 "name":"Selcuk",
 "salary":"65000",
 "age":"36",
  }
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
{
 "status": "success",
 "data": {
 “id”:…
 },
 "message": "Successfully! Record has been added."
 }
olduğunu test edin
     */
    @Test
    public void test(){
        spec01.pathParam("parametre1","create");
        DummyTestData obje=new DummyTestData();

        //post request yaparken biz body gondermek zorundayiz, testdata classinda olusturdugumuz
        //request bodyi burada cagiriyoruz

        HashMap<String ,String> requestBodyMap= obje.setUpRequestBody();
      HashMap<String,Object> expectedDataMap= obje.setUpExpectedData();

        Response response=given().
                accept("application/json").
                spec(spec01).
                body(requestBodyMap).
                when().
                post("/{parametre1}");

        response.prettyPrint();

    }
}
