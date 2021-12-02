package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyTestBase;

import static io.restassured.RestAssured.given;

public class GetRequest02 extends DummyTestBase {

    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim
 */
    @Test
    public void test02(){

        spec01.pathParam("parametre1","employees");

        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("data.employee_name"));
        System.out.println(jsonPath.getString("data.employee_name"));
        System.out.println(jsonPath.getString("data[2].employee_name"));//2 indexini name den sonra da yazabiliriz
        System.out.println(jsonPath.getString("data.employee_name[0,1,2,3,4]"));
        System.out.println(jsonPath.getString("data[-1].employee_name")); //-1 ile en sondaki indexi yazmis olduk


        Assert.assertEquals("Ashton Cox",jsonPath.getString("data[2].employee_name"));
        //3. indexi isminin Ashton Cox oldugunu gosterme. Dogru oldugu icin test pass oldu ve ekrana birsey yazdirmadi
        Assert.assertEquals("Doris Wilder",jsonPath.getString("data[-1].employee_name"));
        //indexi en son olan kisinin Doris Wilder oldugunu gosterme
    }
}
