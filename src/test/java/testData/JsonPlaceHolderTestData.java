package testData;

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
}}
