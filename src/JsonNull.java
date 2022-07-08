/**
 * JSON Parser - JsonNull representation
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

public class JsonNull implements JsonValue {
    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {}

    @Override
    public String toJsonString() {
        return "null";
    }
}
