package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;

public class Parser {
    public HashMap getMap(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        return mapper.readValue(file, HashMap.class);
    }
}
