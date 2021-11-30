package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GetRequest01 {

            @Test
            public void test01(){

    // https://restful-booker.herokuapp.com/booking/3 adresine bir request
    // gonderildiginde donecek cevap(response) icin
    // HTTP status kodunun 200
    // Content Type nin Json
    // status line nin HTTP7/1.1 200 OK oldugunu test ediniz.

    //1.api test yaparken ilk olarak endpoint(url) belirlenmeli
    String url="https://restful-booker.herokuapp.com/booking/3";

    //2.beklenen sonuc (expected result) olusturulur


    // bu case de benden body istenmedigi icin simdilik beklenen sonuc olusturmuyoruz
    //3. request gonder
   Response response=given().
           accept("application/json").
           when().
           get(url);

            response.prettyPrint();

    // 4.actuall result olustur

    //5.dogrulama yap(assertion)

                System.out.println("status Code: "+response.getStatusCode());
                System.out.println("content Type : "+response.getContentType());
                System.out.println("status Line : "+response.getStatusLine());
                System.out.println("response.getHeaders() = " + response.getHeaders());

              //  Assert.assertEquals(200,response.getStatusCode());

                //expected kismi bize taslak olarak verilen degerdir, actual kismi ise response dan donen degerdir

                //  Assert.assertEquals("application/json; charset=utf-8", response.getContentType());

                //   Assert.assertEquals("HTTP/1.1 200 OK", response.getStatusLine());

                response.then().
                        assertThat().
                        statusCode(200).
                        contentType(ContentType.JSON).
                        statusLine("HTTP/1.1 200 OK");

}


    }

