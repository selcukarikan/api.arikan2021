package utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
    private static ObjectMapper mapper;

    static{ //static method hepsinden once calisir
        mapper=new ObjectMapper();
    }

    public static <T> T convertJsonToJava(String json,Class<T> cls){
        T javaResult= null;  //T herhangi bir data type olabilir anlaminda
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.err.println("json datası javaya dönüştürülemedi"); // err hata mesajlari icin kullanilir
        }
        return javaResult;
    }


}
