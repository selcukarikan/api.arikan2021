package day06;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import testBase.DummyTestBase;
import testData.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestMatcherClass01 extends DummyTestBase {
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

        response.then().assertThat().
                statusCode((Integer) expectedDataMap.get("statusCode")).
                body("data[4].employee_name",equalTo(expectedDataMap.get("besinciCalisan")),
                        "data.id",hasSize ((Integer) expectedDataMap.get("calisanSayisi")),
                        "data[-2].employee_salary",equalTo(expectedDataMap.get("sondanIkinciCalisaninMaasi")),
                        "data.employee_age", hasItems (((List)expectedDataMap.get("arananYaslar")).get(0),
                                ((List)  expectedDataMap.get("arananYaslar")).get(1),
                                ((List) expectedDataMap.get("arananYaslar")).get(2)),
                                "data[10].employee_name", equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_name")),
                                "data[10].employee_salary",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_salary")),
                                "data[10].employee_age",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_age")),
                                "data[10].profile_image",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("profile_image")));


    }}
