package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void mainTestMapSchema() {
        var v = new Validator();

        var schema = v.map();

        Assertions.assertTrue(schema.isValid(null)); // true

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertTrue(schema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        Assertions.assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        Assertions.assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        Assertions.assertTrue(schema.isValid(data)); // true
    }

    @Test
    public void mainTestMapSchemaShapes() {
        var v = new Validator();

        var schema = v.map();

// shape позволяет описывать валидацию для значений каждого ключа объекта Map
// Создаем набор схем для проверки каждого ключа проверяемого объекта
// Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "firstName" и "lastName"
// Имя должно быть строкой, обязательно для заполнения
        schemas.put("firstName", v.string().required());
// Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("lastName", v.string().required().minLength(2));

// Настраиваем схему `MapSchema`
// Передаем созданный набор схем в метод shape()
        schema.shape(schemas);

// Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        Assertions.assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        Assertions.assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        Assertions.assertFalse(schema.isValid(human3)); // false
    }
}
