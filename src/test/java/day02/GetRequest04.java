package day02;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest04 {
      /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
    */
    @Test
    public void test04(){
        String url="https://jsonplaceholder.typicode.com/todos/123";
        Response response=given().accept("application/url").when().get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("application/json").
                body("userId", Matchers.equalTo(7)).
                body("title",Matchers.equalTo("esse et quis iste est earum aut impedit")).
                body("completed",Matchers.equalTo("<false>"));

    }
}
