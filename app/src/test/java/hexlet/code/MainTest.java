package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void mainTestStringSchema() {
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

    @Test
    public void mainTestNumberSchema() {
        var v = new Validator();
        var schema = v.number();

        Assertions.assertTrue(schema.isValid(5)); // true

// Пока не вызван метод required(), null считается валидным
        Assertions.assertTrue(schema.isValid(null)); // true
        Assertions.assertTrue(schema.positive().isValid(null)); // true

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertTrue(schema.isValid(10)); // true

// Потому что ранее мы вызвали метод positive()
        Assertions.assertFalse(schema.isValid(-10)); // false
//  Ноль — не положительное число
        Assertions.assertFalse(schema.isValid(0)); // false

        schema.range(5, 10);

        Assertions.assertTrue(schema.isValid(5)); // true
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid(4)); // false
        Assertions.assertFalse(schema.isValid(11)); // false
    }
}
