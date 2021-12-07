package day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testBase.JsonPlaceHolderTestBase;
import testData.JsonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostRequest01 extends JsonPlaceHolderTestBase {
     /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     {
     "userId": 55,
     "title": "arikan your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "arikan your room",
     "completed": false,
     "id": …
    }
     */
    @Test
    public void test(){
        spec01.pathParam("parametre1","todos");
        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest=testObje.setUpPostata();
        System.out.println(expectedRequest);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").body(expectedRequest.toString()).when().post("/{parametre1}");
  response.prettyPrint();

  //matcher class
        response.then().
                assertThat().
                statusCode((Integer) expectedRequest.get("statusCode")).
                body("completed",equalTo(expectedRequest.
                        getBoolean("completed")),"title",equalTo(expectedRequest.
                        getString("title")),"userId",equalTo(expectedRequest.getInt("userId")));

        //jsonpath ile
        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequest.getInt("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));

        //De-Serialization

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),actualDataMap.get("completed"));



    }

}
