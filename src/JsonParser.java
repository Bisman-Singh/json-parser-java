/**
 * JSON Parser - Recursive descent parser
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */

public class JsonParser {
    private final String input;
    private int pos;

    public JsonParser(String input) {
        this.input = input;
        this.pos = 0;
    }

    private void skipWhitespace() {
        while (pos < input.length() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }

    private char peek() {
        skipWhitespace();
        if (pos >= input.length()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        return input.charAt(pos);
    }

    private char consume() {
        skipWhitespace();
        if (pos >= input.length()) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        return input.charAt(pos++);
    }

    private boolean hasMore() {
        skipWhitespace();
        return pos < input.length();
    }

    public JsonValue parse() {
        skipWhitespace();
        if (pos >= input.length()) {
            throw new IllegalArgumentException("Empty input");
        }
        JsonValue result = parseValue();
        skipWhitespace();
        if (pos < input.length()) {
            throw new IllegalArgumentException("Unexpected character at position " + pos);
        }
        return result;
    }

    private JsonValue parseValue() {
        char c = peek();
        switch (c) {
            case '{': return parseObject();
            case '[': return parseArray();
            case '"': return parseString();
            case 't': case 'f': return parseBoolean();
            case 'n': return parseNull();
            case '-': case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                return parseNumber();
            default:
                throw new IllegalArgumentException("Unexpected character: " + c + " at position " + pos);
        }
    }

    private JsonObject parseObject() {
        consume(); // {
        JsonObject obj = new JsonObject();
        if (peek() == '}') {
            consume();
            return obj;
        }
        while (true) {
            String key = parseStringValue();
            if (peek() != ':') {
                throw new IllegalArgumentException("Expected ':' at position " + pos);
            }
            consume(); // :
            JsonValue value = parseValue();
            obj.put(key, value);
            char next = consume();
            if (next == '}') break;
            if (next != ',') {
                throw new IllegalArgumentException("Expected ',' or '}' at position " + pos);
            }
        }
        return obj;
    }

    private JsonArray parseArray() {
        consume(); // [
        JsonArray arr = new JsonArray();
        if (peek() == ']') {
            consume();
            return arr;
        }
        while (true) {
            arr.add(parseValue());
            char next = consume();
            if (next == ']') break;
            if (next != ',') {
                throw new IllegalArgumentException("Expected ',' or ']' at position " + pos);
            }
        }
        return arr;
    }

    private JsonString parseString() {
        return new JsonString(parseStringValue());
    }

    private String parseStringValue() {
        if (consume() != '"') {
            throw new IllegalArgumentException("Expected '\"' at position " + (pos - 1));
        }
        StringBuilder sb = new StringBuilder();
        while (pos < input.length()) {
            char c = input.charAt(pos++);
            if (c == '"') {
                return sb.toString();
            }
            if (c == '\\') {
                if (pos >= input.length()) throw new IllegalArgumentException("Invalid escape");
                char esc = input.charAt(pos++);
                switch (esc) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    default: throw new IllegalArgumentException("Invalid escape: \\" + esc);
                }
            } else {
                sb.append(c);
            }
        }
        throw new IllegalArgumentException("Unterminated string");
    }

    private JsonNumber parseNumber() {
        int start = pos;
        if (peek() == '-') consume();
        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isDigit(c) || c == '.' || c == 'e' || c == 'E' || c == '+' || c == '-') {
                pos++;
            } else {
                break;
            }
        }
        return new JsonNumber(input.substring(start, pos));
    }

    private JsonBoolean parseBoolean() {
        if (input.startsWith("true", pos)) {
            pos += 4;
            return new JsonBoolean(true);
        }
        if (input.startsWith("false", pos)) {
            pos += 5;
            return new JsonBoolean(false);
        }
        throw new IllegalArgumentException("Expected true or false at position " + pos);
    }

    private JsonNull parseNull() {
        if (input.startsWith("null", pos)) {
            pos += 4;
            return JsonNull.INSTANCE;
        }
        throw new IllegalArgumentException("Expected null at position " + pos);
    }
}
