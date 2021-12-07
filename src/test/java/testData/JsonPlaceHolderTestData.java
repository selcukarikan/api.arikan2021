package testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    //TestData
    public Map<String,Object> setUpTestData(){
    HashMap<String,Object> expectedData=new HashMap<String,Object>();
        expectedData.put("statusCode",200);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");
        expectedData.put("userId",1);
        expectedData.put("completed","false");
        expectedData.put("title","quis ut nam facilis et officia qui");
        return expectedData;
}
    /*{
        "userId": 55,
            "title": "arikan your room",
            "completed": false
    }
    */
    public JSONObject setUpPostata(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",55);
        expectedRequest.put("title","arikan your name");
        expectedRequest.put("completed",false);
        expectedRequest.put("statusCode",201);
        return expectedRequest;
    }
    public void setUpPutData(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed",false);
    }
}
