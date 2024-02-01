package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.Set;

public class Stylish {
    public static String getResult(LinkedHashMap<String, Object> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> keys = hashMap.keySet();

        stringBuilder.append("{");
        stringBuilder.append("\n");

        for (String key: keys) {
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(hashMap.get(key));
            stringBuilder.append("\n");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
