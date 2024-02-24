package hexlet.code.schemas;

public  interface BaseSchema<T> {
    Boolean isValid(T object);
}

