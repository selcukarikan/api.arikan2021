package testData;

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
}
