package hexlet.code;

import hexlet.code.formatters.JSON;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.List;


public class Differ {

    public static String generate(String filePath1, String filePath2, String formatter) throws Exception {
        Parser parser = new Parser();
        HashMap file1Map = parser.getMap(filePath1);
        HashMap file2Map = parser.getMap(filePath2);
        return implementFormatter(file1Map, file2Map, formatter);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        Parser parser = new Parser();
        HashMap file1Map = parser.getMap(filePath1);
        HashMap file2Map = parser.getMap(filePath2);
        return implementFormatter(file1Map, file2Map, "stylish");
    }

    private static String implementFormatter(HashMap file1, HashMap file2, String formatter) throws IOException {
        LinkedHashMap<String, Object> reusltOfGenDiff = generateDifferences(file1, file2);
        if (formatter.equals("stylish")) {
            return Stylish.getResult(reusltOfGenDiff);
        } else if (formatter.equals("plain")) {
            return Plain.getResult(reusltOfGenDiff);
        } else if (formatter.equals("json")) {
            JSON.getResult(reusltOfGenDiff);
            return "Written to file";
        } else {
            return null;
        }
    }


    static LinkedHashMap<String, Object> generateDifferences(HashMap file1, HashMap file2) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        var keySet1 = file1.keySet();
        var keySet2 = file2.keySet();
        Set<String> keySet = new HashSet<>();
        keySet.addAll(keySet1);
        keySet.addAll(keySet2);
        List keySetList = keySet.stream().sorted().toList();

        for (Object key : keySetList) {
            if (file1.containsKey(key) && file2.containsKey(key)) {
                diffOfValues(key, file1, file2, result);
            } else if (file1.containsKey(key) && !file2.containsKey(key)) {
                String abscencedKey = "- " + key;
                result.put(abscencedKey, file1.get(key));

            } else if (!file1.containsKey(key) && file2.containsKey(key)) {
                String containedKey = "+ " + key;
                result.put(containedKey, file2.get(key));
            }
        }
        return result;
    }

    private static void diffOfValues(Object key, HashMap file1, HashMap file2, LinkedHashMap<String, Object> result) {
        if ((file1.get(key) == null) || (file2.get(key) == null)) {
            diffOfValuesWithNull(key, file1, file2, result);
        } else {
            diffOfValuesWithOutNull(key, file1, file2, result);
        }

    }

    private static void diffOfValuesWithOutNull(Object key, HashMap file1, HashMap file2, LinkedHashMap<String,
            Object> result) {
        if (file1.get(key).equals(file2.get(key))) {
            String newKey = "  " + key;
            result.put(newKey, file1.get(key));
        } else {
            String keyFile1 = "- " + key;
            String keyFile2 = "+ " + key;
            result.put(keyFile1, file1.get(key));
            result.put(keyFile2, file2.get(key));
        }
    }

    private static void diffOfValuesWithNull(Object key, HashMap file1, HashMap file2, LinkedHashMap<String,
            Object> result) {
        if (file1.get(key) == (file2.get(key))) {
            String newKey = "  " + key;
            result.put(newKey, file1.get(key));
        } else {
            String keyFile1 = "- " + key;
            String keyFile2 = "+ " + key;
            result.put(keyFile1, file1.get(key));
            result.put(keyFile2, file2.get(key));
        }
    }

}
