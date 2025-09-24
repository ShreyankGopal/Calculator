package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    public void testSquareRootPositive() {
        assertEquals(5.0, calc.squareRoot(25.0), 0.0001);
    }

    @Test
    public void testSquareRootNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.squareRoot(-4.0);
        });
        assertEquals("Cannot take square root of negative number", exception.getMessage());
    }

    @Test
    public void testFactorialZero() {
        assertEquals(1, calc.factorial(0));
    }

    @Test
    public void testFactorialPositive() {
        assertEquals(120, calc.factorial(5));
    }

    @Test
    public void testFactorialNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.factorial(-3);
        });
        assertEquals("Cannot take factorial of negative number", exception.getMessage());
    }

    @Test
    public void testNaturalLogPositive() {
        assertEquals(0.0, calc.naturalLog(1.0), 0.0001);
    }

    @Test
    public void testNaturalLogNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calc.naturalLog(-5.0);
        });
        assertEquals("Logarithm defined only for positive numbers", exception.getMessage());
    }

    @Test
    public void testPower() {
        assertEquals(8.0, calc.power(2.0, 3.0), 0.0001);
    }
}
