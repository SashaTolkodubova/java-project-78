package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    private Boolean required = false;
    private int minLength;
    private Boolean minLengthFlag = false;
    private String contains;
    private boolean containsFlag = false;

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        minLengthFlag = true;
        return this;
    }

    public StringSchema contains(String stringToFind) {
        contains = stringToFind;
        containsFlag = true;
        return this;
    }

    public Boolean isValid(String object) {
        if (required && ((object == null) || (object.equals("")))) {
            return false;
        }
        if ((containsFlag || minLengthFlag) && (object == null)) {
            return false;
        }
        if (minLengthFlag && (object.length() <= minLength)) {
            return false;
        }
        if (containsFlag && (!object.contains(contains))) {
            return false;
        }
        return true;
    }
}
