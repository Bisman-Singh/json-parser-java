/**
 * JSON Parser - JsonObject representation
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonObject implements JsonValue {
    private final Map<String, JsonValue> members = new LinkedHashMap<>();

    public void put(String key, JsonValue value) {
        members.put(key, value);
    }

    public JsonValue get(String key) {
        return members.get(key);
    }

    public Map<String, JsonValue> getMembers() {
        return members;
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<String, JsonValue> entry : members.entrySet()) {
            if (!first) sb.append(",");
            sb.append("\"").append(escape(entry.getKey())).append("\":");
            sb.append(entry.getValue().toJsonString());
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return toJsonString();
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
