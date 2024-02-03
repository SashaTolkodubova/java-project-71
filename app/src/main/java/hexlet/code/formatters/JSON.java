package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

//import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

public class JSON {
    public static String getResult(LinkedHashMap<String, Object> hashMap) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(new File("app/src/main/resources/result.json"), hashMap);
        return objectMapper.writeValueAsString(hashMap);
    }
}
