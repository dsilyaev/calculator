package com.dsilyaev.calculator;

import java.io.Serializable;

public class CalculatorModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private double memory           = 0.0;
    private double store            = 0.0;
    private double functionValue    = 0.0;
    private Operation operation     = Operation.NONE;
    private Operation evalOperation = Operation.NONE;
    private double evalOperand      = 0.0;
    /*
    memory:		memorized value,
    store:		current value stored in the calculator,
    functionValue:	value returned by the last call to the function,
    operation:		current operation,
    evalOperation:	last operation (used by 'Enter' button),
    evalOperand:	last operand (used by 'Enter' button)
    */
    
    private void compute(Operation operation, double val) {
        this.evalOperand = val;
        this.evalOperation = operation;
        switch (operation) {
            case ADDITION:
                store += val;
                break;
            case SUBTRACTION:
                store -= val;
                break;
            case MULTIPLICATION:
                store *= val;
                break;
            case DIVISION:
                store /= val;
                break;
            case NONE:
            default:
            	store = val;
            	break;
        }
    }
    
    public static boolean isZero(double num) {
        String str = String.valueOf(num);
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                continue;
            }
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }
    
    public void reset() {
        store = 0.0;
        functionValue = 0.0;
        operation = Operation.NONE;
        evalOperation = Operation.NONE;
        evalOperand = 0.0;
    }
    
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
    public void setValue(double val) {
        this.store = val;
    }
    
    public Operation getOperation() {
        return operation;
    }
    
    public Operation getEvalOperation() {
        return evalOperation;
    }
    
    public double getValue() {
        return store;
    }
    
    public double getFunctionValue() {
        return functionValue;
    }
    
    public double getMemory() {
        return memory;
    }
    
    public void compute(double val) {
        compute(operation, val);
    }
    
    public void compute() {
        compute(evalOperation, evalOperand);
    }
    
    public void sqrt(double val) {
        functionValue = Math.sqrt(val);
    }
    
    public void changeSign(double val) {
        functionValue = -val;
    }
    
    public void reciprocal(double val) {
        functionValue = 1.0 / val;
    }
    
    public void percentage(double val) {
        functionValue = store * (val / 100);
    }
    
    public void memorySave(double val) {
        memory = val;
        functionValue = val;
    }
    
    public void memoryRecall() {
        functionValue = memory;
    }
    
    public void memoryClear(double val) {
        memory = 0.0;
        functionValue = val;
    }
    
    public void memoryAdd(double val) {
        memory += val;
        functionValue = val;
    }
    
    public void memorySubtract(double val) {
        memory -= val;
        functionValue = val;
    }
}