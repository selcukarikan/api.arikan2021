package day02;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
//  https://restful-booker.herokuapp.com/booking/7 url'ine
//accept type'i "application/json" olan GET request'i yolladigimda
//gelen response'un
//status kodunun 200
//ve content type'inin "application/json"


//ve firstname'in "Susan"
//ve lastname'in "Jackson"
//ve checkin date'in 2016-03-24"
//ve checkout date'in 2018-03-20 oldugunu test edin
    @Test
    public void test(){

        String url="https://restful-booker.herokuapp.com/booking/7";
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
                body("lastname",Matchers.equalTo("Ericsson")).
                body("totalprice",Matchers.equalTo(102)).
                body("depositpaid",Matchers.equalTo(true)).
                body("bookingdates.checkin",Matchers.equalTo("2018-10-22")).
                body("bookingdates.checkout",Matchers.equalTo("2020-05-14"));





    }

}
