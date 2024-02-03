package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.List;

public class Plain {
    public static String getResult(LinkedHashMap<String, Object> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> keys = hashMap.keySet().stream().toList();

        for (int i = 0; i < keys.size() - 1; i++) {
            String key1 = keys.get(i);
            String key2 = keys.get(i + 1);
            if (key1.substring(2).equals(key2.substring(2))) {
                stringBuilder.append(wasUpdated(key1, key2, hashMap.get(key1), hashMap.get(key2)));
                i++;
            } else if (key1.charAt(0) == '-') {
                stringBuilder.append(wasRemoved(key1));
            } else if (key1.charAt(0) == '+') {
                stringBuilder.append(wasAdded(key1, hashMap.get(key1)));
            }
        }
        int lastKeyIndex = keys.size() - 1;
        String lastKey = keys.get(lastKeyIndex);
        String keyBeforeLAst = keys.get(lastKeyIndex - 1);
        if ((lastKey.charAt(0) != ' ')
                && !(lastKey.substring(2).equals(keyBeforeLAst.substring(2)))) {
            stringBuilder.append(checkLastValues(lastKey, hashMap.get(lastKey)));
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private static String checkLastValues(String key, Object value) {
        if (key.charAt(0) == '-') {
            return wasRemoved(key);
        } else {
            return wasAdded(key, value);
        }
    }

    private static String checkComplexValue(Object value) {
        String complexValue = "[complex value]";
        if (value instanceof String) {
            return value == null ? "null" : "'" + value.toString() + "'";
        } else if ((value instanceof Integer)
                || (value instanceof Character)
                || (value instanceof Float)
                || (value instanceof Long)
                || (value instanceof Double)
                || (value instanceof Boolean)
                || (value instanceof Byte)
                || (value instanceof Short)
                || (value == null)
        ) {
            return value == null ? "null" : value.toString();
        }
        return complexValue;
    }

    private static String wasUpdated(String key1, String key2, Object object1, Object object2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Property " + "'" + key1.substring(2) + "' ");
        stringBuilder.append("was updated. From ");
        stringBuilder.append(checkComplexValue(object1));
        stringBuilder.append(" to ");
        stringBuilder.append(checkComplexValue(object2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private static String wasRemoved(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Property " + "'" + key.substring(2) + "' ");
        stringBuilder.append("was removed");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private static String wasAdded(String key, Object value) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Property " + "'" + key.substring(2) + "' ");
        stringBuilder.append("was added with value: ");
        stringBuilder.append(checkComplexValue(value));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
