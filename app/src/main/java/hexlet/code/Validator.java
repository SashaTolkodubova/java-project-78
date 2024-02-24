package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {
    public StringSchema string() {
        StringSchema schema = new StringSchema();
        return schema;
    }

    public NumberSchema number() {
        NumberSchema schema = new NumberSchema();
        return schema;
    }

    public MapSchema map() {
        MapSchema schema = new MapSchema();
        return schema;
    }
}
