package day02;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest02 {
    /*
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in “Jim"
    ve totalprice’in 600
    ve checkin date'in 2015-06-12"oldugunu test edin
    */

    @Test
    public void test02(){
        String url="https://restful-booker.herokuapp.com/booking/5";
        Response response=given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Eric")).
                body("totalprice",Matchers.equalTo(627)).
                body("bookingdates.checkin",Matchers.equalTo("2020-08-22"));

    }}
