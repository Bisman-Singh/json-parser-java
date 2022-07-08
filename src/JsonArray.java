/**
 * JSON Parser - JsonArray representation
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

import java.util.ArrayList;
import java.util.List;

public class JsonArray implements JsonValue {
    private final List<JsonValue> elements = new ArrayList<>();

    public void add(JsonValue value) {
        elements.add(value);
    }

    public JsonValue get(int index) {
        return elements.get(index);
    }

    public List<JsonValue> getElements() {
        return elements;
    }

    @Override
    public String toJsonString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(elements.get(i).toJsonString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return toJsonString();
    }
}
