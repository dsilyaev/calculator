package com.dsilyaev.calculator;

import javax.swing.SwingUtilities;

public class Calculator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculatorFrame frame = new CalculatorFrame();
                frame.setVisible(true);
            }
        });
    }
}
