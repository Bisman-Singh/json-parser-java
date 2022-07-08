# JSON Parser

A recursive descent JSON parser written in Java 17. Parses JSON strings and returns a simple `JsonObject`/`JsonArray` representation.

**Author:** Bisman Singh <bismanmadaan1@gmail.com>

## Features

- Parses objects `{"key":"value"}`
- Parses arrays `[1,2,3]`
- Supports strings, numbers, booleans, null
- Handles nested objects and arrays
- Recursive descent parsing algorithm

## Usage

```bash
make
make run
```

Or with custom JSON:

```bash
java -cp out Main '{"name":"test"}'
java -cp out Main '{"key":"value","num":123,"arr":[1,2,3],"nested":{"a":1}}'
```

## Output

Prints the parsed structure in pretty-printed format.
