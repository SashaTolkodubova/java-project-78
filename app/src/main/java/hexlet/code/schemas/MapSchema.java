package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private Boolean required = false;
    private int sizeOf;
    private Boolean sizeOfFlag = false;

    public Boolean isValid(Map<String, String> object) {
        if (required && (object == null)) {
            return false;
        }
        if (!sizeOfFlag && (object == null)) {
            return true;
        }
        if (sizeOfFlag && (object.size() != sizeOf)) {
            return false;
        }
        return true;
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        sizeOf = size;
        sizeOfFlag = true;
        return this;
    }


}
