package day08;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testBase.JsonPlaceHolderTestBase;
import testData.JsonPlaceHolderTestData;

import static io.restassured.RestAssured.given;

public class PutRequest02 extends JsonPlaceHolderTestBase {
      /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
 {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false
 }
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "userId": 21,
 "title": "Wash the dishes",
 "completed": false,
 "id": 198
 }
     */
    @Test
    public void test(){
        spec01.pathParams("parametre1","todos","parametre2",198);

        JsonPlaceHolderTestData testObje=new JsonPlaceHolderTestData();
        JSONObject expectedRequest= testObje.setUpPostata();
        System.out.println(expectedRequest);

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                body(expectedRequest.toString()).when().put("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //jsonpath ile

        JsonPath json=response.jsonPath();
        Assert.assertEquals(expectedRequest.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),json.getBoolean("completed"));

    }
}
