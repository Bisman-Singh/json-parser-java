/**
 * JSON Parser - JsonBoolean representation
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

public class JsonBoolean implements JsonValue {
    private final boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toJsonString() {
        return value ? "true" : "false";
    }
}
