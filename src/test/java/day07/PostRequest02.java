package day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testBase.RestFulHerokuTestBase;
import testData.HerOkuTestData;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends RestFulHerokuTestBase {
      /*
    https://restful-booker.herokuapp.com/booking
    { "firstname": "Selcuk",
               "lastname": "Arikan",
               "totalprice": 123456,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }
 gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selcuk  ",
         "lastname": " Arikan ",
         "totalprice":  123456,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-09",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */

    @Test
    public void test(){
        //url icin
        spec01.pathParam("parametre1","booking");

        //requestBody ve expected Data ayni oldugu icin tek bir JSONObject kullanilmasi yeterlidir
        HerOkuTestData testData=new HerOkuTestData();
        JSONObject expectedRequestData=testData.setUpTestAndRequest();
        System.out.println(expectedRequestData);


        //request gonder

        Response response=given().
        contentType(ContentType.JSON).
                //accept("application/json"). //normalde bu satiri yazmam gerekliydi ama ContenType li yazinca calisti
                spec(spec01).
                body(expectedRequestData.toString()).
                auth().
                basic("admin","password123").
                when().
                post("/{parametre1}");

        response.prettyPrint();


    }
}
