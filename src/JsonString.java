/**
 * JSON Parser - JsonString representation
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

public class JsonString implements JsonValue {
    private final String value;

    public JsonString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toJsonString() {
        return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }
}
