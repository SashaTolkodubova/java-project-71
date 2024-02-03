package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class JSON {
    public static String getResult(LinkedHashMap<String, Object> hashMap) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(hashMap);
        BufferedWriter writer = new BufferedWriter(new FileWriter("app/src/main/resources/result.json"));
        writer.write(result);
        writer.close();

        return result;
    }
}
