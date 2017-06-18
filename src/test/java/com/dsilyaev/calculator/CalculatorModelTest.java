package com.dsilyaev.calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorModelTest {
    private static final double DELTA = 1e-8;
    private final CalculatorModel calculator = new CalculatorModel();
    
    @Before
    public void setUp() {
        calculator.reset();
    }

    @Test
    public void testIsZero() {
        assertTrue(CalculatorModel.isZero(0));
        assertTrue(CalculatorModel.isZero(0.0));
        assertTrue(CalculatorModel.isZero(-0.0));
    }

    @Test
    public void testCompute() {
        calculator.setValue(10.0);
        calculator.setOperation(Operation.NONE);
        calculator.compute(2.0);
        assertEquals(2.0, calculator.getValue(), DELTA);
        calculator.setValue(10.0);
        calculator.setOperation(Operation.ADDITION);
        calculator.compute(2.0);
        assertEquals(12.0, calculator.getValue(), DELTA);
        calculator.setValue(10.0);
        calculator.setOperation(Operation.SUBTRACTION);
        calculator.compute(2.0);
        assertEquals(8.0, calculator.getValue(), DELTA);
        calculator.setValue(10.0);
        calculator.setOperation(Operation.MULTIPLICATION);
        calculator.compute(2.0);
        assertEquals(20.0, calculator.getValue(), DELTA);
        calculator.setValue(10.0);
        calculator.setOperation(Operation.DIVISION);
        calculator.compute(2.0);
        assertEquals(5.0, calculator.getValue(), DELTA);
    }
    
    @Test
    public void testSqrt() {
        calculator.sqrt(-1.0);
        assertTrue(Double.isNaN(calculator.getFunctionValue()));
        calculator.sqrt(25.0);
        assertEquals(5.0, calculator.getFunctionValue(), DELTA);
        
    }
    
    @Test
    public void testChangeSign() {
        calculator.changeSign(1.0);
        assertEquals(-1.0, calculator.getFunctionValue(), DELTA);
        calculator.changeSign(-1.0);
        assertEquals(1.0, calculator.getFunctionValue(), DELTA);
    }

    @Test
    public void testReciprocal() {
        calculator.reciprocal(0);
        assertTrue(Double.isInfinite(calculator.getFunctionValue()));
        calculator.reciprocal(5);
        assertEquals(0.2, calculator.getFunctionValue(), DELTA);
    }

    @Test
    public void testPercentage() {
        calculator.setValue(10.0);
        calculator.percentage(50.0);
        assertEquals(5.0, calculator.getFunctionValue(), DELTA);
    }
}
