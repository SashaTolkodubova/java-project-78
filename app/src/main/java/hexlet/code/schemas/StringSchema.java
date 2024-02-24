package hexlet.code.schemas;

public final class StringSchema implements Schema{
    private Boolean required = false;
    private  int minLength;
    private Boolean minLenthFlag = false;
    private String contains;
    private boolean containsFlag = false;

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        minLenthFlag = true;
        return this;
    }

    public StringSchema contains(String stringToFind) {
        contains = stringToFind;
        containsFlag = true;
        return this;
    }

    @Override
    public Boolean isValid(String string) {
        if (required && ((string == null) || (string.equals("")))) {
            return false;
        }
        if ((containsFlag || minLenthFlag) && (string == null)) {
            return false;
        }
        if (minLenthFlag && (string.length() <= minLength)) {
            return false;
        }
        if (containsFlag && (!string.contains(contains))) {
            return false;
        }
        return true;
    }
}
