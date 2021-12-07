package testData;

import com.google.gson.JsonObject;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;

import java.util.HashMap;

public class HerOkuTestData {

      /*
{
 "firstname": "Eric",
 "lastname": "Smith",
 "totalprice": 555,
 "depositpaid": false,
 "bookingdates": {
 "checkin": "2016-09-09",
 "checkout": "2017-09-21"

     */
    public HashMap<String, Object> setUpTestData(){
        HashMap<String,Object> bookingDates=new HashMap<String,Object>();
        bookingDates.put("checkin","2016-07-06");
        bookingDates.put("checkout","2018-03-16");


        HashMap<String ,Object> expectedData=new HashMap<String,Object>();
        expectedData.put("firstname","jim");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",726);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingDates);

        return expectedData;



    }
    //     "firstname": " Selcuk  ",
    //         "lastname": " Arikan ",
    //         "totalprice":  123456,
    //         "depositpaid": true,
    //         "bookingdates": {
    //             "checkin": "2020-09-01",
    //              "checkout": " 2020-09-21”

    public JSONObject setUpTestAndRequest(){

        JSONObject bookingDates=new JSONObject();
        bookingDates.put("checkin","2020-09-01");
        bookingDates.put("checkout","2020-09-21");


        JSONObject expectedRequest= new JSONObject();
        expectedRequest.put("firstname","Selcuk");
        expectedRequest.put("lastname","Arikan");
        expectedRequest.put("totalprice",123456);
        expectedRequest.put("depositpaid",true);
        expectedRequest.put("bookingdates",bookingDates);
        return expectedRequest;



    }
}
