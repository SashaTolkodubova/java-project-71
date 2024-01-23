package hexlet.code;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        Parser parser = new Parser();
        HashMap file1Map = parser.getMap(filePath1);
        HashMap file2Map = parser.getMap(filePath2);
        return differ(file1Map, file2Map);
    }

    private static String differ(HashMap file1, HashMap file2) {
        var keySet1 = file1.keySet();
        var keySet2 = file2.keySet();
        Set<String> keySet = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();

        keySet.addAll(keySet1);
        keySet.addAll(keySet2);
        List keySetList = keySet.stream().sorted().toList();

        stringBuilder.append("{");
        stringBuilder.append("\n");

        for (Object key : keySetList) {
            stringBuilder.append(keyDiff(key, file1, file2));
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");


        return stringBuilder.toString();
    }

    private static String keyDiff(Object key, HashMap file1, HashMap file2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (file1.containsKey(key) && file2.containsKey(key)) {
            stringBuilder.append(valueDiff(key, file1.get(key), file2.get(key)));
        } else if (file1.containsKey(key) && !file2.containsKey(key)) {
            stringBuilder.append("- ");
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(file1.get(key));
        } else if (!file1.containsKey(key) && file2.containsKey(key)) {
            stringBuilder.append("+ ");
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(file2.get(key));
        }

        return stringBuilder.toString();
    }

    private static String valueDiff(Object key, Object value1, Object value2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (value1.equals(value2)) {
            stringBuilder.append("  ");
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(value1);
        } else {
            stringBuilder.append("- ");
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(value1);
            stringBuilder.append("\n");
            stringBuilder.append("+ ");
            stringBuilder.append(key);
            stringBuilder.append(": ");
            stringBuilder.append(value2);
        }

        return stringBuilder.toString();
    }

}
