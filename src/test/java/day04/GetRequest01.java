package day04;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testBase.DummyTestBase;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest01 extends DummyTestBase {

    @Test
    public void test01(){
      spec01.pathParam("parametre1","employees");

        //    http://dummy.restapiexample.com/api/v1/employees
        //url ine bir istek gönderildiğinde
        //Dönen response un
        // Status kodunun 200,
        // 1)10’dan büyük tüm id’leri ekrana yazdırın ve
        //10’dan büyük 14 id olduğunu,
        Response response=given().
                accept("application/json").
                when().
                spec(spec01).
                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(200,response.getStatusCode());
       // response.then().assertThat().statusCode(200); yukaridaki gibi yada bu sekilde yazilabilir

       //groovy language ile yazdirabiliriz asagidaki sekilde. javanin alt dili gibi birsey , lambda tarzi

       List<Integer> idList= jsonPath.
               getList( "data.findAll{it.id>10}.id");
        System.out.println(idList);
        Assert.assertEquals(14,idList.size());

        //2)30’dan küçük tüm yaşları ekrana yazdırın ve
        //  bu yaşların içerisinde en büyük yaşın 23 olduğunu

        List<Integer> ageList=jsonPath.
        getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(ageList);
        Collections.sort(ageList);
        Assert.assertEquals((Integer)23,ageList.get(ageList.size()-1));
       // Assert.assertEquals(23,(int) ageList.get(ageList.size()-1)); bu sekilde de olur yukaridaki sekilde  de olur .
        // basina integer oder int eklemek type casting islemidir

       // 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
       //  bunların içerisinde “Charde Marshall” olduğunu test edin
        List<String> salaryName=jsonPath.
                getList("data.findAll{it.employee_salary>350000}.employee_name");
        //employee_salary e gore liste yaptik ama daha sonra employee_name ye gore listeyi istedik
        System.out.println(salaryName);
        Assert.assertTrue(salaryName.contains("Charde Marshall"));





    }

}
