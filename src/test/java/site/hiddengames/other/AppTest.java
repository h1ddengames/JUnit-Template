package site.hiddengames.other;

import site.hiddengames.entrypoint.App;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Arithmetic Operations")
@Feature("Addition and Subtraction Features")
public class AppTest {
    private final App app = new App();

    @Test
    @Story("User tries to add two numbers.")
    @Description("Get the value of two numbers added together.")
    void addition() {
        assertEquals(2, app.add(1,1));
    }

    @Test
    @Story("User tries to subtract two numbers.")
    @Description("Get the value of a number subtracted by another number.")
    void subtraction() {
        assertEquals(2, app.subtract(4, 2));
    }

    @Test
    @Story("User tries to add two numbers then subtract from a larger number.")
    @Description("Get the value of a number subtracted by two numbers added together.")
    void integrationOfAdditionAndSubtraction() {
        assertEquals(5, app.subtract(10, app.add(4, 1)));
    }
}
