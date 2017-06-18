package com.dsilyaev.calculator;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class CalculatorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    public static final String ADD_ACTION               = "addAction";
    public static final String CHANGE_SIGN_ACTION       = "changeSignAction";
    public static final String CLEAR_ACTION             = "clearAction";
    public static final String CLEAR_ENTRY_ACTION       = "clearEntryAction";
    public static final String DIVIDE_ACTION            = "divideAction";
    public static final String DOT_ACTION               = "dotAction";
    public static final String ERASE_ACTION             = "eraseAction";
    public static final String EVALUATE_ACTION          = "evaluateAction";
    public static final String MEMORY_ADD_ACTION        = "memoryAddAction";
    public static final String MEMORY_CLEAR_ACTION      = "memoryClearAction";
    public static final String MEMORY_RECALL_ACTION     = "memoryRecallAction";
    public static final String MEMORY_SAVE_ACTION       = "memorySaveAction";
    public static final String MEMORY_SUBTRACT_ACTION   = "memorySubtractAction";
    public static final String MULTIPLY_ACTION          = "multiplyAction";
    public static final String[] NUMBER_ACTIONS         = {
        "number0Action",
        "number1Action", "number2Action", "number3Action",
        "number4Action", "number5Action", "number6Action",
        "number7Action", "number8Action", "number9Action",
    };
    public static final String NEGATIVE_ROOT_MESSAGE    = "Invalid input";
    public static final String PERCENTAGE_ACTION        = "percentageAction";
    public static final String RECIPROCAL_ACTION        = "reciprocalAction";
    public static final String SQUARE_ROOT_ACTION       = "squareRootAction";
    public static final String SUBTRACT_ACTION          = "subtractAction";
    private static final int MAX_INPUT_LENGTH       = 15;
    private static final double MAX_REPRESENTABLE   = 999999999999999.;
    private static final double MIN_REPRESENTABLE   = 0.00000000000001;
    private static final String ZERO_DIVISION_MESSAGE_1 = "Result is undefined";
    private static final String ZERO_DIVISION_MESSAGE_2 = "Cannot divide by zero";
    private final Map<String, AbstractAction> actionMap = new HashMap<>();
    private final CalculatorPanel calculatorPane;
    private final CalculatorModel calculator;
    private InputState inputState = InputState.NO_INPUT;
    private String userInput = "";
    private boolean errorState = false;
    
    public CalculatorFrame() {
        setTitle("Calculator");
        setSize(218, 285);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        initializeActionMap();
        registerKeys();
        
        calculator = new CalculatorModel();
        calculatorPane = new CalculatorPanel(actionMap);
        calculatorPane.setDisplayText("0");
        getContentPane().add(calculatorPane);
    }
    
    private void initializeActionMap() {
        actionMap.put(ADD_ACTION, new ComputeAction(Operation.ADDITION));
        actionMap.put(CHANGE_SIGN_ACTION, new ChangeSignAction());
        actionMap.put(CLEAR_ACTION, new ClearAction());
        actionMap.put(CLEAR_ENTRY_ACTION, new ClearEntryAction());
        actionMap.put(DIVIDE_ACTION, new ComputeAction(Operation.DIVISION));
        actionMap.put(DOT_ACTION, new DotAction());
        actionMap.put(ERASE_ACTION, new EraseAction());
        actionMap.put(EVALUATE_ACTION, new EvaluateAction());
        actionMap.put(MEMORY_ADD_ACTION, new MemoryAddAction());
        actionMap.put(MEMORY_CLEAR_ACTION, new MemoryClearAction());
        actionMap.put(MEMORY_RECALL_ACTION, new MemoryRecallAction());
        actionMap.put(MEMORY_SAVE_ACTION, new MemorySaveAction());
        actionMap.put(MEMORY_SUBTRACT_ACTION, new MemorySubtractAction());
        actionMap.put(MULTIPLY_ACTION, new ComputeAction(Operation.MULTIPLICATION));
        for (int i = 0; i < 10; i++) {
            actionMap.put(NUMBER_ACTIONS[i], new NumberAction(i));
        }
        actionMap.put(PERCENTAGE_ACTION, new PercentAction());
        actionMap.put(RECIPROCAL_ACTION, new ReciprocalAction());
        actionMap.put(SQUARE_ROOT_ACTION, new SquareRootAction());
        actionMap.put(SUBTRACT_ACTION, new ComputeAction(Operation.SUBTRACTION));
    }
    
    private void registerKeys() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap keyActionMap = getRootPane().getActionMap();
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape");
        keyActionMap.put("Escape", actionMap.get(CLEAR_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "Backspace");
        keyActionMap.put("Backspace", actionMap.get(ERASE_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "Delete");
        keyActionMap.put("Delete", actionMap.get(CLEAR_ENTRY_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), "Period");
        keyActionMap.put("Period", actionMap.get(DOT_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DECIMAL, 0), "Decimal");
        keyActionMap.put("Decimal", actionMap.get(DOT_ACTION));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "0");
        keyActionMap.put("0", actionMap.get(NUMBER_ACTIONS[0]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "1");
        keyActionMap.put("1", actionMap.get(NUMBER_ACTIONS[1]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "2");
        keyActionMap.put("2", actionMap.get(NUMBER_ACTIONS[2]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "3");
        keyActionMap.put("3", actionMap.get(NUMBER_ACTIONS[3]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "4");
        keyActionMap.put("4", actionMap.get(NUMBER_ACTIONS[4]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "5");
        keyActionMap.put("5", actionMap.get(NUMBER_ACTIONS[5]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "6");
        keyActionMap.put("6", actionMap.get(NUMBER_ACTIONS[6]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "7");
        keyActionMap.put("7", actionMap.get(NUMBER_ACTIONS[7]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "8");
        keyActionMap.put("8", actionMap.get(NUMBER_ACTIONS[8]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "9");
        keyActionMap.put("9", actionMap.get(NUMBER_ACTIONS[9]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD0, 0), "Numpad0");
        keyActionMap.put("Numpad0", actionMap.get(NUMBER_ACTIONS[0]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), "Numpad1");
        keyActionMap.put("Numpad1", actionMap.get(NUMBER_ACTIONS[1]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), "Numpad2");
        keyActionMap.put("Numpad2", actionMap.get(NUMBER_ACTIONS[2]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), "Numpad3");
        keyActionMap.put("Numpad3", actionMap.get(NUMBER_ACTIONS[3]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), "Numpad4");
        keyActionMap.put("Numpad4", actionMap.get(NUMBER_ACTIONS[4]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD5, 0), "Numpad5");
        keyActionMap.put("Numpad5", actionMap.get(NUMBER_ACTIONS[5]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), "Numpad6");
        keyActionMap.put("Numpad6", actionMap.get(NUMBER_ACTIONS[6]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), "Numpad7");
        keyActionMap.put("Numpad7", actionMap.get(NUMBER_ACTIONS[7]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "Numpad8");
        keyActionMap.put("Numpad8", actionMap.get(NUMBER_ACTIONS[8]));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), "Numpad9");
        keyActionMap.put("Numpad9", actionMap.get(NUMBER_ACTIONS[9]));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "F9");
        keyActionMap.put("F9", actionMap.get(CHANGE_SIGN_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, InputEvent.SHIFT_DOWN_MASK), "Plus");
        keyActionMap.put("Plus", actionMap.get(ADD_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "Minus");
        keyActionMap.put("Minus", actionMap.get(SUBTRACT_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK), "Multiply");
        keyActionMap.put("Multiply", actionMap.get(MULTIPLY_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "Divide");
        keyActionMap.put("Divide", actionMap.get(DIVIDE_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "Equals");
        keyActionMap.put("Equals", actionMap.get(EVALUATE_ACTION));
        
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), "NumpadPlus");
        keyActionMap.put("NumpadPlus", actionMap.get(ADD_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), "NumpadMinus");
        keyActionMap.put("NumpadMinus", actionMap.get(SUBTRACT_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MULTIPLY, 0), "NumpadMultiply");
        keyActionMap.put("NumpadMultiply", actionMap.get(MULTIPLY_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DIVIDE, 0), "NumpadDivide");
        keyActionMap.put("NumpadDivide", actionMap.get(DIVIDE_ACTION));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        keyActionMap.put("Enter", actionMap.get(EVALUATE_ACTION));
    }
    
    private String formatOutput(double val) {
        if (CalculatorModel.isZero(val)) {
            return "0";
        }
        if (Double.isInfinite(val)) {
            errorState = true;
            calculatorPane.setErrorState(errorState);
            return (val > 0) ? "Infinity" : "-Infinity";
        }
        String str;
        if (Math.abs(val) > MAX_REPRESENTABLE || Math.abs(val) < MIN_REPRESENTABLE) {
            str = String.format("%.15g", val);
            // remove trailing zeros before the 'e' character
            int left;
            int right;
            right = str.indexOf('e');
            left = right - 1;
            while (str.charAt(left) == '0') {
                left--;
            }
            str = str.substring(0, left + 1) + str.substring(right, str.length());
        }
        else {
            str = String.format("%.15f", val);
            while (str.endsWith("0")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.endsWith(".")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }
    
    private double getInput() {
        switch (inputState) {
            case NO_INPUT:
                // used by function-type actions
                // should be handled separately by ComputeAction and EvaluateAction
                return calculator.getValue();
            case FUNCTION_INPUT:
                return calculator.getFunctionValue();
            case USER_INPUT:
                return Double.parseDouble(calculatorPane.getDisplayText());
            default:
                return 0;
        }
    }
    
    private void handleZeroDivision(boolean checkStore) {
        errorState = true;
        calculatorPane.setErrorState(errorState);
        if (checkStore && CalculatorModel.isZero(calculator.getValue())) {
            calculatorPane.setDisplayText(ZERO_DIVISION_MESSAGE_1);
        }
        else {
            calculatorPane.setDisplayText(ZERO_DIVISION_MESSAGE_2);
        }
    }
    
    
    /* Action handlers */
    
    
    private class ClearAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            calculator.reset();
            errorState = false;
            calculatorPane.setErrorState(errorState);
            inputState = InputState.USER_INPUT;
            userInput = "0";
            calculatorPane.setDisplayText("0");
        }
    }
    
    private class ClearEntryAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                calculator.reset();
            }
            errorState = false;
            calculatorPane.setErrorState(errorState);
            inputState = InputState.USER_INPUT;
            userInput = "0";
            calculatorPane.setDisplayText("0");
        }
    }
    
    private class EraseAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            if (inputState != InputState.USER_INPUT) {
                return;
            }
            boolean lastChar = userInput.length() == 1
                    || (userInput.length() == 2 && userInput.charAt(0) == '-')
                    || "0.".equals(userInput)
                    || "-0.".equals(userInput);
            if (lastChar) {
                userInput = "0";
                calculatorPane.setDisplayText(userInput);
            }
            else {
                userInput = userInput.substring(0, userInput.length() - 1);
                calculatorPane.setDisplayText(userInput);
            }
        }
    }
    
    private class NumberAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        int number;
        
        public NumberAction(int num) {
            if (num < 0 || num > 9) {
                throw new IllegalArgumentException();
            }
            this.number = num;
        }
        
        @Override
        public void actionPerformed(ActionEvent evt) {          
            if (errorState) {
                return;
            }
            // check if the input length stays within the limits
            int inputLength = userInput.length();
            if (userInput.startsWith("-")) {
                inputLength--;
            }
            if (userInput.contains(".")) {
                inputLength--;
            }
            if (inputLength == MAX_INPUT_LENGTH) {
                return;
            }
            inputState = InputState.USER_INPUT;
            if ("0".equals(userInput)) {
                userInput = String.valueOf(number);
            }
            else {
                userInput += String.valueOf(number);
            }
            calculatorPane.setDisplayText(userInput);
        }
    }
    
    private class DotAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            if (!userInput.contains(".")) {
                if (inputState != InputState.USER_INPUT) {
                    userInput = "0.";
                }
                else {
                    userInput += ".";
                }
                inputState = InputState.USER_INPUT;
                calculatorPane.setDisplayText(userInput);
            }
        }
    }
    
    private class ComputeAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        private final Operation operation;
        
        ComputeAction(Operation operation) {
            this.operation = operation;
        }
        
        @Override
        public void actionPerformed(ActionEvent evt) {           
            if (errorState) {
                return;
            }

            if (inputState != InputState.NO_INPUT) {
                double val = getInput();
                // store the current value
                if (calculator.getOperation() == Operation.NONE) {
                    calculator.compute(val);
                }
                // calculate the previously set operation with the current input
                // check for zero division
                else if (calculator.getOperation() == Operation.DIVISION
                        && CalculatorModel.isZero(val)) {
                    handleZeroDivision(true);
                    return;
                }
                else {
                    calculator.compute(val);
                    calculatorPane.setDisplayText(formatOutput(calculator.getValue()));
                }
            }
            calculator.setOperation(operation);
            inputState = InputState.NO_INPUT;
            userInput = "";
        }
    }
    
    private class EvaluateAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            if (calculator.getOperation() == Operation.NONE) {
                // enter a number and press 'Enter'
                // calculate a function and press 'Enter'
                if (calculator.getEvalOperation() == Operation.NONE) {
                    double val = getInput();
                    calculator.compute(val);
                }
                // do some calculations, hit 'Enter'
                // hit 'Enter' again
                else if (inputState == InputState.NO_INPUT) {
                    calculator.compute();
                }
                else {
                    // do some calculations, hit 'Enter'
                    // enter some value, hit 'Enter' again
                    double val = getInput();
                    calculator.setValue(val);
                    calculator.compute();
                }
            }
            else {
                // hit operation button, followed by 'Enter'
                if (inputState == InputState.NO_INPUT) {
                    calculator.compute(calculator.getValue());
                }
                // type in some value after specifying the operation and hit 'Enter'
                else {
                    if (calculator.getOperation() == Operation.DIVISION
                            && CalculatorModel.isZero(getInput())) {
                        handleZeroDivision(true);
                        return;
                    }
                    double val = getInput();
                    calculator.compute(val);
                }
            }
            inputState = InputState.NO_INPUT;
            userInput = "";
            calculator.setOperation(Operation.NONE);
            calculatorPane.setDisplayText(formatOutput(calculator.getValue()));
        }
    }
    
    private class ChangeSignAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            if (inputState == InputState.NO_INPUT) {
                calculator.changeSign(calculator.getValue());
                inputState = InputState.FUNCTION_INPUT;
                calculatorPane.setDisplayText(formatOutput(calculator.getFunctionValue()));
            }
            else if (inputState == InputState.USER_INPUT) {
                if (userInput.charAt(0) == '-') {
                    userInput = userInput.substring(1, userInput.length());
                }
                else if (!"0".equals(userInput)) {
                    userInput = "-" + userInput;
                }
                calculatorPane.setDisplayText(userInput);
            }
            else {
                calculator.changeSign(calculator.getFunctionValue());
                calculatorPane.setDisplayText(formatOutput(calculator.getFunctionValue()));
            }
        }
    }
    
    private class SquareRootAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {         
            if (errorState) {
                return;
            }
            double val = getInput();
            if (val < 0) {
                errorState = true;
                calculatorPane.setErrorState(errorState);
                calculatorPane.setDisplayText(NEGATIVE_ROOT_MESSAGE);
                return;
            }
            calculator.sqrt(val);
            inputState = InputState.FUNCTION_INPUT;
            userInput = "";
            calculatorPane.setDisplayText(formatOutput(calculator.getFunctionValue()));
        }
    }
    
    private class ReciprocalAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {          
            if (errorState) {
                return;
            }
            double val = getInput();
            if (CalculatorModel.isZero(val)) {
                handleZeroDivision(false);
                return;
            }
            calculator.reciprocal(val);
            inputState = InputState.FUNCTION_INPUT;
            userInput = "";
            calculatorPane.setDisplayText(formatOutput(calculator.getFunctionValue()));
        }
    }
    
    private class PercentAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            double val = getInput();
            calculator.percentage(val);
            inputState = InputState.FUNCTION_INPUT;
            userInput = "";
            calculatorPane.setDisplayText(formatOutput(calculator.getFunctionValue()));
        }
    }
    
    private class MemoryClearAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            double val = getInput();
            calculator.memoryClear(val);
            inputState = (inputState == InputState.NO_INPUT) ?
                    InputState.NO_INPUT : InputState.FUNCTION_INPUT;
            userInput = "";
            calculatorPane.enableMemoryLabel(false);
        }
    }
    
    private class MemoryRecallAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (errorState) {
                return;
            }
            calculator.memoryRecall();
            inputState = InputState.FUNCTION_INPUT;
            userInput = "";
            calculatorPane.setDisplayText(formatOutput(calculator.getFunctionValue()));
        }
    }
    
    private class MemorySaveAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {         
            if (errorState) {
                return;
            }
            double val = getInput();
            if (CalculatorModel.isZero(val)) {
                userInput = "0";
                inputState = InputState.USER_INPUT;
                calculatorPane.setDisplayText("0");
                return;
            }
            calculator.memorySave(val);
            calculatorPane.enableMemoryLabel(true);
            inputState = (inputState == InputState.NO_INPUT) ?
                    InputState.NO_INPUT : InputState.FUNCTION_INPUT;
            userInput = "";
        }
    }
    
    private class MemoryAddAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {         
            if (errorState) {
                return;
            }
            double val = getInput();
            if (CalculatorModel.isZero(val)) {
                userInput = "0";
                inputState = InputState.USER_INPUT;
                calculatorPane.setDisplayText("0");
                return;
            }
            calculator.memoryAdd(val);
            if (CalculatorModel.isZero(calculator.getMemory())) {
                calculatorPane.enableMemoryLabel(false);
            }
            else {
                calculatorPane.enableMemoryLabel(true);
            }
            inputState = (inputState == InputState.NO_INPUT) ?
                InputState.NO_INPUT : InputState.FUNCTION_INPUT;
            userInput = "";
        }
    }
    
    private class MemorySubtractAction extends AbstractAction {
    	private static final long serialVersionUID = 1L;
        @Override
        public void actionPerformed(ActionEvent evt) {          
            if (errorState) {
                return;
            }
            double val = getInput();
            if (CalculatorModel.isZero(val)) {
                userInput = "0";
                inputState = InputState.USER_INPUT;
                calculatorPane.setDisplayText("0");
                return;
            }
            calculator.memorySubtract(val);
            if (CalculatorModel.isZero(calculator.getMemory())) {
                calculatorPane.enableMemoryLabel(false);
            }
            else {
                calculatorPane.enableMemoryLabel(true);
            }
            inputState = (inputState == InputState.NO_INPUT) ?
                    InputState.NO_INPUT : InputState.FUNCTION_INPUT;
            userInput = "";
        }
    }
}