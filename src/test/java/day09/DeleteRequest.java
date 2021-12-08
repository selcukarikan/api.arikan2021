package day09;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import testBase.DummyTestBase;
import testData.DummyTestData;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteRequest extends DummyTestBase {
     /*
    http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
 Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
 {
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
 }
     */

    @Test
    public void test(){
        spec01.pathParams("parametre1","delete","parametre2",2);

        DummyTestData testData=new DummyTestData();
        JSONObject expectedData= testData.setUpDeleteExpected();

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin","password123").
                when().
                delete("/{parametre1}/{parametre2");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200).
                body("status",equalTo(expectedData.getString("status")),

                        "message",equalTo(expectedData.getString("message")));

    }
}
