package day09;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.TodosPojo;
import testBase.JsonPlaceHolderTestBase;

import static io.restassured.RestAssured.*;

public class PostRequestWithPojo extends JsonPlaceHolderTestBase {
     /*
    https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
 Request body{
 "userId": 21,
 "id": 201,
 "title": "arikan your room",
 "completed": false
}
 Status kodun 201, response body ‘nin ise
{
 "userId": 21,
 "id": 201,
 "title": "arikan your room",
 "completed": false
 }
     */

    @Test
    public void test(){
        spec01.pathParam("parametre1","todos");

        TodosPojo requestExpected=new TodosPojo(21,201,"arikan your room",false);
        System.out.println(requestExpected);

        Response response=given().
                spec(spec01).
                contentType(ContentType.JSON).
                auth().
                basic("admin","password").
                body(requestExpected).
                when().
                post("/{parametre1}");
        response.prettyPrint();

        //De-serialization
        TodosPojo actualData=response.as(TodosPojo.class);

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(requestExpected.getUserId(),actualData.getUserId());
        Assert.assertEquals(requestExpected.getId(),actualData.getId());
        Assert.assertEquals(requestExpected.getTitle(),actualData.getTitle());
        Assert.assertEquals(requestExpected.isCompleted(),actualData.isCompleted());




    }

}
