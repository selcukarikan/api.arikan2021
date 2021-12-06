package day06;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyTestBase;
import testData.DummyTestData;

import java.nio.channels.IllegalBlockingModeException;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.concurrent.Callable;

import static io.restassured.RestAssured.given;

public class GetRequest extends DummyTestBase {

       /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    @Test
    public void test() {
        spec01.pathParam("parametre1", "employees");
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String, Integer> expectedDataMap = expectedObje.setUpTestData02();
        System.out.println(expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}");
        //  response.prettyPrint();

        //de-serialization yontemi

        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);
        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        //  En yüksek maaşın 725000 olduğunu,

        List<Integer> maasListesi = new ArrayList<Integer>();
        int datasize = ((List) actualDataMap.get("data")).size();

        for (int i = 0; i < datasize; i++) {
         maasListesi.add((Integer)((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));
            Collections.sort(maasListesi);
           Assert.assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesi.get(maasListesi.size()-1));

    List<Integer> yasListesi=new ArrayList<Integer>();
            for (int j = 0; i < datasize; i++) {
                yasListesi.add((Integer) ((Map)((List)actualDataMap.get("data")).get(j)).get("employee_age"));
            }
            Collections.sort(yasListesi);
            Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesi.get(0));

          //jsonPath yontemi ile
            JsonPath json=response.jsonPath();
            //en yuksek maasin 725000 oldugunu ;
            List<Integer> maasListesiJson=json.getList("data.employee_salary");
            Collections.sort(maasListesiJson);
            Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),
                    maasListesiJson.get(maasListesiJson.size()-1));

            Assert.assertEquals(expectedDataMap.get("EnYuksekikinciMaas"),
                    maasListesiJson.get(maasListesiJson.size()-2));

            List<Integer> yasListesiJson=json.getList("data.employee_age");
            Collections.sort(yasListesiJson);
            Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesiJson.get(0));
        }
    }
}
