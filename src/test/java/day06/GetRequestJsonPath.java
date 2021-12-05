package day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.security.jarsigner.JarSigner;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyTestBase;
import testData.DummyTestData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestJsonPath extends DummyTestBase {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
 5. Çalışan isminin "Airi Satou" olduğunu ,
 çalışan sayısının 24 olduğunu,


11. Çalışan bilgilerinin
{
“id”:”11”
   "employee_name": "Jena Gaines",
       "employee_salary": "90560",
       "employee_age": "30",
       "profile_image": "" }
} gibi olduğunu test edin.
*/
    @Test
    public void test02(){
        spec01.pathParam("parametre1","employees");

        DummyTestData expectedObje=new DummyTestData();
        HashMap<String,Object> expectedDataMap=expectedObje.setUpTestData();
        System.out.println(expectedDataMap);

        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}");
        response.prettyPrint();

        //json path yontemi , ilk basta json objesi olusturmaliyiz

        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"), jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),jsonPath.getList("data.id").size());
        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisaninMaasi"), jsonPath.getInt("data[-2].employee_salary"));
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll((Collection<?>) expectedDataMap.get("arananYaslar")));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("id"),jsonPath.getInt("data[10].id"));


        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_name"),jsonPath.getString("data[10].employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));


        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("profile_image"),
                jsonPath.getString("data[10].profile_image"));


    }}

