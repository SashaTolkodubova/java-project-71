package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public HashMap getMap(String filePath) throws Exception {
        ObjectMapper mapperJSON = new JsonMapper();
        ObjectMapper mapperYML = new YAMLMapper();
        File file = new File(filePath);
        final String sampleJSON = "(json$)";
        final String sampleYML = "(yml$)";
        Pattern patternJSON = Pattern.compile(sampleJSON);
        Matcher matcherJSON = patternJSON.matcher(filePath);

        Pattern patternYML = Pattern.compile(sampleYML);
        Matcher matcherYML = patternYML.matcher(filePath);

        if (matcherJSON.find()) {
            return mapperJSON.readValue(file, HashMap.class);
        } else if (matcherYML.find()) {
            return mapperYML.readValue(file, HashMap.class);
        } else {
            throw new Exception("Format not supported");
        }
    }
}
