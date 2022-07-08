/**
 * JSON Parser - JsonNumber representation
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

public class JsonNumber implements JsonValue {
    private final String value;

    public JsonNumber(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toJsonString() {
        return value;
    }
}
