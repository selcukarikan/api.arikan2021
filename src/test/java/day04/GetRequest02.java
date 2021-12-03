package day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import testBase.JsonPlaceHolderTestBase;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.keyStore;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 extends JsonPlaceHolderTestBase {
      /*
https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
 "completed": değerinin false
"title”: değerinin “quis ut nam facilis et officia qui”
"userId" sinin 1 ve
header değerlerinden
 "Via" değerinin “1.1 vegur” ve
 "Server" değerinin “cloudflare” olduğunu test edin…
 */
@Test
    public void test02(){

    spec01.pathParams("parametre1","todos","parametre2","2");

    Response response=given().
            accept("application/json").
            when().
            spec(spec01).
            get("/{parametre1/parametre2}");
    System.out.println("++++++++++++++++++++++++++++++++++++++++");

    //dinamik olmasi ici hashmap olusturduk
    HashMap<String,Object> expectedData=new HashMap<String,Object>();
    expectedData.put("statusCode",200);
    expectedData.put("Via","1.1 vegur");
    expectedData.put("Server","cloudflare");
    expectedData.put("userId",1);
    expectedData.put("completed","false");
    expectedData.put("title","quis ut nam facilis et officia qui");
    System.out.println(expectedData);


    //1. yontem Matchers class ile assertion islemi
response.then().
        assertThat().
        statusCode((Integer) expectedData.get("statusCode")).
        headers("via", equalTo(expectedData.get("Via")),"Server",equalTo(expectedData.get("Server")),
       "completed",equalTo(expectedData.get("completed")),"userId",equalTo(expectedData.get("userId"))) ;

//2. yontem Jsonpath yontemi ile assertion islemi

    JsonPath jsonPath=response.jsonPath();
    Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
    Assert.assertEquals(expectedData.get("Via"),response.getHeader("via"));
    Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
    Assert.assertEquals(expectedData.get("completed"),jsonPath.getString("completed"));
    Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));

//bazi assert lerde response bazisinda jsonpath kullanmamizin sebebi bazi
// degerler postman da json ifadesinin altinda yer aldigi icin


    //3. yontem deserialization
    // object mapper
    //pojo class ile birlikte map

}
}
