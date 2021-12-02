package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import testBase.RestFulHerokuTestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest01 extends RestFulHerokuTestBase {
    /*
    https://restful-booker.herokuapp.com/booking/5 url’ine bir request yolladigimda
    HTTP Status Code’unun 200
    ve response content type’inin “application/JSON” oldugunu
    ve response body’sinin asagidaki gibi oldugunu test edin
    {"firstname": Sally,
            "lastname": "Smith",
            "totalprice": 789,
            "depositpaid": false,
            "bookingdates": {
               "checkin": "2017-12-11",
                "checkout":"2020-02-20"
*/
    @Test
    public void test01(){
        spec01.pathParams("parametre1","booking","parametre2","5");

        Response response=given().accept("application/json").spec(spec01).when().get("/{parametre1}/{parametre2}");

        response.prettyPrint();

       //matcher yontemi

      /*  response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname",equalTo("Mark")).
                body("lastname",equalTo("Jackson")).
                body("totalprice",equalTo(322)).
                body("depositpaid",equalTo(true)).
                body("bookingdates.checkin",equalTo("2020-02-19")).
                body("bookingdates.checkout",equalTo("2021-03-26"));
*/
        //JsonPath yontemi ile assert
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals("Mary",jsonPath.getString("firstname"));
        Assert.assertEquals("Jones",jsonPath.getString("lastname"));
        Assert.assertEquals(179,jsonPath.getInt("totalprice"));
        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals("2019-09-07",jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2021-05-04",jsonPath.getString("bookingdates.checkout"));

    }


}
