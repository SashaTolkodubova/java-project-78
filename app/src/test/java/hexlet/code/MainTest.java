package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void mainTest() {
        Main main = new Main();
        String result = main.returnString();
        String exp = "123";
        Assertions.assertEquals(exp, result);
    }
}
