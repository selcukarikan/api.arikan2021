package day09;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testBase.JsonPlaceHolderTestBase;
import testData.JsonPlaceHolderTestData;

import java.awt.geom.RectangularShape;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {
     /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {
      "title": "API ist gleich spaß"
     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API ist gleich spaß"
 "completed": true,
 "id": 198
}
     */
    @Test
    public void test(){

        //url olusturma

        spec01.pathParams("parametre1","todos","parametre2",198);

        //expected ve request data olustur

        JsonPlaceHolderTestData testData=new JsonPlaceHolderTestData();
        JSONObject requestData=testData.setUpPatchRequestData();
        JSONObject expectedData=testData.setUpPatchExpectedData();

        //request gonder

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(requestData.toString()).
                when().
                patch("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //De-serialization

        HashMap<String,Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getInt("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedData.getString("title"),actualData.get("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"),actualData.get("completed"));


       }}

