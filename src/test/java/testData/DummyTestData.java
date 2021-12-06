package testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
     /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
      5. Çalışan isminin "Airi Satou" olduğunu ,
      çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
11. Çalışan bilgilerinin
    {
 “id”:”11”
        "employee_name": "Jena Gaines",
            "employee_salary": "90560",
            "employee_age": "30",
            "profile_image": "" }
} gibi olduğunu test edin.
*/
public HashMap<String, Object> setUpTestData(){

    List<Integer> ageList=new ArrayList<Integer>();
    ageList.add(40);
    ageList.add(21);
    ageList.add(19);

    HashMap<String,Object> onbirinci=new HashMap<String,Object>();
    onbirinci.put("id",11);
    onbirinci.put("employee_name","Jena Gaines");
    onbirinci.put("employee_salary",90560);
    onbirinci.put("employee_age",30);
    onbirinci.put("profile_image","");

    HashMap<String ,Object> expectedData=new HashMap<String,Object>();
    expectedData.put("statusCode",200);
    expectedData.put("besinciCalisan","Airi Satou");
    expectedData.put("calisanSayisi",24);
    expectedData.put("sondanIkinciCalisaninMaasi",106450);
    expectedData.put("arananYaslar",ageList);
    expectedData.put("onbirinciCalisan",onbirinci);
    return expectedData;
}
   /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    public HashMap<String,Integer> setUpTestData02(){

    HashMap<String, Integer> expectedData=new HashMap<String,Integer>();
    expectedData.put("statusCode",200);
    expectedData.put("enKucukYas",19);
    expectedData.put("enYuksekMaas",320800);
    expectedData.put("enYuksekIkinciMaas",675000);
    return expectedData;
    }
    public HashMap<String, String> setUpRequestBody(){
        HashMap<String,String> requestBody=new HashMap<String,String>();
    requestBody.put("name","Selcuk");
    requestBody.put("salary","65000");
    requestBody.put("age","36");
    return requestBody;


    }
    public HashMap<String, Object> setUpExpectedData (){
        //  HashMap<String,String> data=new HashMap<String ,String>();
        // data.put("name","Selcuk");
        // data.put("salary","65000");
        // data.put("age","36");

        HashMap<String,Object> expectedData=new HashMap<String,Object>();
        expectedData.put("statusCode",200);
        expectedData.put("status","success");
       // expectedData.put("data", data);
        expectedData.put("message","Successfully! Record has been added.");
        return expectedData;



    }
}