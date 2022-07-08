/**
 * JSON Parser - CLI entry point
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

public class Main {
    public static void main(String[] args) {
        String json = args.length > 0 ? args[0] : "{\"key\":\"value\",\"num\":123,\"arr\":[1,2,3],\"nested\":{\"a\":1}}";
        try {
            JsonParser parser = new JsonParser(json);
            JsonValue result = parser.parse();
            System.out.println(prettyPrint(result, 0));
        } catch (Exception e) {
            System.err.println("Parse error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static String prettyPrint(JsonValue value, int indent) {
        String pad = "  ".repeat(indent);
        if (value instanceof JsonObject) {
            JsonObject obj = (JsonObject) value;
            StringBuilder sb = new StringBuilder("{\n");
            for (var entry : obj.getMembers().entrySet()) {
                sb.append(pad).append("  \"").append(entry.getKey()).append("\": ");
                sb.append(prettyPrint(entry.getValue(), indent + 1)).append(",\n");
            }
            if (!obj.getMembers().isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("\n").append(pad).append("}");
            return sb.toString();
        }
        if (value instanceof JsonArray) {
            JsonArray arr = (JsonArray) value;
            StringBuilder sb = new StringBuilder("[\n");
            for (JsonValue v : arr.getElements()) {
                sb.append(pad).append("  ").append(prettyPrint(v, indent + 1)).append(",\n");
            }
            if (!arr.getElements().isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("\n").append(pad).append("]");
            return sb.toString();
        }
        return value.toJsonString();
    }
}
