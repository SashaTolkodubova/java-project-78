package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void mainTest() {
        var v = new Validator();
        var schema = v.string();

// Пока не вызван метод required(), null и пустая строка считаются валидным
        Assertions.assertTrue(schema.isValid("")); // true
        Assertions.assertTrue(schema.isValid(null)); // true

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertFalse(schema.isValid("")); // false
        Assertions.assertTrue(schema.isValid("what does the fox say")); // true
        Assertions.assertTrue(schema.isValid("hexlet")); // true

        Assertions.assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        Assertions.assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        Assertions.assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        Assertions.assertFalse(schema.isValid("what does the fox say")); // false
    }
}
