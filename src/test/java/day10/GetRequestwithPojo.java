package day10;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.Data;
import pojos.DummyPojo;
import testBase.DummyTestBase;

import static io.restassured.RestAssured.given;

public class GetRequestwithPojo extends DummyTestBase {
     /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                            Status code is 200
                                 {
                                  "status": "success",
                                  "data": {
                                      "id": 1,
                                      "employee_name": "Tiger Nixon",
                                      "employee_salary": 320800,
                                      "employee_age": 61,
                                      "profile_image": ""
                                  },
                                  "message": "Successfully! Record has been fetched."
                                 }
 */

    @Test
    public void test(){
        spec01.pathParams("parametre1","employee","parametre2","1");

        Data data=
                new Data(1,"Tiger Nixon",320800,61,"");

        DummyPojo expextedData=
                new DummyPojo("success",data,"Successfully! Record has been fetched.");

        Response response=given().contentType(ContentType.JSON).spec(spec01).when().get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        DummyPojo actualData=response.as(DummyPojo.class);

        System.out.println(actualData);

        Assert.
                assertEquals(200,response.getStatusCode());

        Assert.
                assertEquals(expextedData.getStatus(),
                        actualData.getStatus());

        Assert.
                assertEquals(expextedData.getData().getemployee_name(),
                        actualData.getData().getemployee_name());

        Assert.
                assertEquals(expextedData.getData().
                        getId(),actualData.getData().getId());

        Assert.
                assertEquals(expextedData.getData().getemployee_age(),
                        actualData.getData().getemployee_age());

        Assert.
                assertEquals(expextedData.getData().getprofile_image(),
                        actualData.getData().getprofile_image());

        //Serialization java yapisinda olan datalari json formatina donusturme islemidir.
        //Gson sinifindan bir obje uretilir

        Gson gson=new Gson();
        String jsonFormJava=gson.toJson(actualData);
        System.out.println(jsonFormJava);

    }
}
