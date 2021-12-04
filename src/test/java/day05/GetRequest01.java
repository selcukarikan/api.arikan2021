package day05;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.RestFulHerokuTestBase;
import testData.HerOkuTestData;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class GetRequest01 extends RestFulHerokuTestBase {
    // java da olusturdgumuz bir nesneyi veya sinifi,
    // saklamak yada transfer etmek istedigimiz formata donusturme islemine serialization denir
    // Bunun tam tersi duruma ise De-serialization denir. Yani API da donen response u Map , List,
    //List of Map , Set gibi Java objelerine cevirme islemidir
      /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
{
 "firstname": "Eric",
 "lastname": "Smith",
 "totalprice": 555,
 "depositpaid": false,
 "bookingdates": {
 "checkin": "2016-09-09",
 "checkout": "2017-09-21"
 }
 } gibi olduğunu test edin
     */
    @Test
    public void test01(){
        spec01.pathParams("parametre1","booking","parametre2",1);

        //expectedData
        HerOkuTestData expectedObje=new HerOkuTestData();

        HashMap<String,Object> expectedDataMap= expectedObje.setUpTestData();
        System.out.println(expectedDataMap);

        //request gonderme

      Response response=  given().accept("application/json").spec(spec01).when().get("/{parametre1}/{parametre2}");
      response.prettyPrint();

      //deserialization olusturma
        HashMap<String,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);

        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingsDates")).
                get("checkin"),
                ((Map) actualDataMap.get("bookingdates")).get("checkin"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingDates")).
                get("checkout"),
                ((Map) actualDataMap.get("bookingdates")).get("checkout"));

        //2.yol JsonPath
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("firstname"),jsonPath.getString("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),jsonPath.getString("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),jsonPath.getInt("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),jsonPath.getBoolean("depositpaid"));

        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingDates")).get("checkin"),jsonPath.getString("bookingdates.checkin")
        );

        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingDates")).get("checkout"),jsonPath.getString("bookingdates.checkout")
        );


    }

}
