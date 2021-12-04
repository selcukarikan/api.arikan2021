package day05;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyTestBase;
import testData.DummyTestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest02 extends DummyTestBase {
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

        //De-serialization islemi

        HashMap<String,Object> actualDataMap=response.as(HashMap.class);

        System.out.println(actualDataMap);

        Assert.assertEquals(expectedDataMap.get("statusCode"),
                response.getStatusCode());

        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map)((List) actualDataMap.get("data")).get(4)).get("employee_name"));

        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List) actualDataMap.get("data")).size());

        // Sondan 2. çalışanın maaşının 106450 olduğunu
        int datasize=((List) actualDataMap.get("data")).size();

        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisaninMaasi"),
                ((Map) ((List)  actualDataMap.get("data")).get(datasize-2)).get("employee_salary"));

         // 40,21 ve 19 yaslarında çalışanlar olup olmadığını

        List<Integer> actualAgeList=new ArrayList<Integer>();
        for (int i=0; i<datasize; i++) {
            actualAgeList.add(((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age")));
        }
        Assert.assertTrue(actualAgeList.containsAll((List)expectedDataMap.get("arananYaslar")));

        Assert.assertEquals(((Map) expectedDataMap.get("onbirinciCalisan")).get("employee_name"),
                ((Map) ((List) actualDataMap.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),

                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onbirinciCalisan")).get("profile_image"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));
        }

}