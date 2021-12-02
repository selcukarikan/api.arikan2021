package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyTestBase;

import static io.restassured.RestAssured.given;

public class GetRequest03 extends DummyTestBase {
      /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    @Test
    public void test03(){

spec01.pathParam("parametre1","employees");

Response response=given().
        accept("application/json").
        when().
        spec(spec01).
        get("/{parametre1}");
response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

 // response.then().assertThat().statusCode(200);
        System.out.println(jsonPath.getList("data.id").size());
        Assert.assertEquals(24,jsonPath.getList("data.id").size());
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("Airi Satou",jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(372000,jsonPath.getInt("data[5].employee_salary"));

    }

}
