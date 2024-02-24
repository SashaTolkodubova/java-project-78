package hexlet.code.schemas;

import java.util.Map;

public class MapSchema implements BaseSchema<Map<String, String>> {
    private Boolean required = false;
    private int sizeOf;
    private Boolean sizeOfFlag = false;
    private Map<String, BaseSchema<String>> shapes;
    private Boolean shapesFlag = false;

    public Boolean isValid(Map<String, String> object) {
        if (shapesFlag) {
            return withShapes(object);
        } else {
            return withOutShapes(object);
        }
    }

    public MapSchema shape(Map<String, BaseSchema<String>> mapsWithSchemes) {
        shapes = mapsWithSchemes;
        shapesFlag = true;
        return this;
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

    private Boolean withOutShapes(Map<String, String> object) {
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

    private Boolean withShapes(Map<String, String> object) {
        Boolean flag = false;
        for (String key : object.keySet()) {
            if (shapes.get(key).isValid(object.get(key))) {
                flag = true;
            } else {
                return false;
            }
        }

        return flag;
    }


}
