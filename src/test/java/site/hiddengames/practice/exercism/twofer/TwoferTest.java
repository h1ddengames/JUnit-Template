package site.hiddengames.practice.exercism.twofer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice.exercism.twofer.Twofer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoferTest {

    private Twofer twofer;

    @BeforeEach
    public void setup() {
        twofer = new Twofer();
    }

    @Test
    public void nullNameGiven() {
        String input = null;
        String expected = "One for you, one for me.";

        assertEquals(expected, twofer.twofer(input));
    }

    @Test
    public void blankNameGiven() {
        String input = "";
        String expected = "One for you, one for me.";

        assertEquals(expected, twofer.twofer(input));
    }

    @Test
    public void aNameGiven() {
        String input = "Alice";
        String expected = "One for Alice, one for me.";

        assertEquals(expected, twofer.twofer(input));
    }

    @Test
    public void anotherNameGiven() {
        String input = "Bob";
        String expected = "One for Bob, one for me.";

        assertEquals(expected, twofer.twofer(input));
    }
}

