package day01;

import io.restassured.response.Response;
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
   Response response=given().accept("application/json").when().get(url);

    // 4.actuall result olustur

    //5.dogrulama yap(assertion)
                response.prettyPrint();

}


    }

