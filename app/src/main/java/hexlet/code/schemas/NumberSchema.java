package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Object> {
    private Boolean required = false;
    private Boolean positive = false;
    private boolean rangeFlag = false;
    private int minRange;
    private int maxRange;

    public Boolean isValid(Object number) {
        if (required && (number == null)) {
            return false;
        } else if (number == null) {
            return true;
        }

        int intNumber = (int) number;
        if (positive && (intNumber <= 0)) {
            return false;
        }
        if (rangeFlag && ((intNumber < minRange) || (intNumber > maxRange))) {
            return false;
        }

        return true;
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        minRange = minValue;
        maxRange = maxValue;
        rangeFlag = true;
        return this;
    }
}
