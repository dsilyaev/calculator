package com.dsilyaev.calculator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CalculatorPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private boolean errorState;
    private final JTextField displayTxt;
    private final JLabel memoryLbl;
    private final NumpadButton[] numButtons;
    private final NumpadButton dotBtn;
    private final MemButton memClearBtn;
    private final MemButton memRecallBtn;
    private final MemButton memSaveBtn;
    private final MemButton memAddBtn;
    private final MemButton memSubtractBtn;
    private final FunctionButton eraseBtn;
    private final FunctionButton clearEntryBtn;
    private final FunctionButton clearBtn;
    private final FunctionButton signBtn;
    private final FunctionButton sqrtBtn;
    private final FunctionButton divideBtn;
    private final FunctionButton multiplyBtn;
    private final FunctionButton subtractBtn;
    private final FunctionButton addBtn;
    private final FunctionButton percentBtn;
    private final FunctionButton reciprocalBtn;
    private final FunctionButton evalBtn;
    private static final String DISPLAY_FONT_NAME  = "Consolas";
    private static final Font regularDisplayFont   = new Font(DISPLAY_FONT_NAME, Font.PLAIN, 22);
    private static final Font smallDisplayFont1    = new Font(DISPLAY_FONT_NAME, Font.PLAIN, 18);
    private static final Font smallDisplayFont2    = new Font(DISPLAY_FONT_NAME, Font.PLAIN, 14);
    private static final Font smallDisplayFont3    = new Font(DISPLAY_FONT_NAME, Font.PLAIN, 12);
    private static final Font errorDisplayFont     = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private static final Font buttonFont1          = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    private static final Font buttonFont2          = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
    private static final Font buttonFont3          = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    private static final Color backgroundColor     = new Color(231, 236, 245);
    private static final Color displayColor        = new Color(255, 255, 255);
    private static final Color numpadButtonColor   = new Color(250, 250, 250);
    private static final Color functionButtonColor = new Color(240, 240, 255);
    private static final Color memoryButtonColor   = new Color(230, 230, 255);
    private static final Color displayBorderColor  = new Color(160, 160, 160);
    private static final Color buttonBorderColor   = new Color(255, 205, 0);
    private static final Color mouseOverColor      = new Color(255, 255, 200);
    private static final Color mousePressedColor   = new Color(250, 235, 185);
    private static final Color buttonFontColor     = new Color(0, 0, 80);
    private static final int DF_GRID_WIDTH  = 1;
    private static final int DF_GRID_HEIGHT = 1;
    private static final double DF_WEIGHTX  = 0.2;
    private static final double DF_WEIGHTY  = 0;
    private static final int DF_ANCHOR      = GridBagConstraints.CENTER;
    private static final int DF_FILL        = GridBagConstraints.BOTH;
    private static final Insets DF_INSETS   = new Insets(3, 3, 3, 3);
    private static final int DF_IPADX       = 0;
    private static final int DF_IPADY       = 0;
    
    public CalculatorPanel(Map<String, AbstractAction> actionMap) {
        memoryLbl = new JLabel(" ");
        memoryLbl.setFont(smallDisplayFont2);
        memoryLbl.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        displayTxt = new JTextField();
        displayTxt.setEditable(false);
        displayTxt.setHorizontalAlignment(JTextField.RIGHT);
        displayTxt.setFocusable(false);
        displayTxt.setBorder(new LineBorder(Color.lightGray));
        displayTxt.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        displayTxt.setFont(regularDisplayFont);
        displayTxt.setBackground(displayColor);
        
        numButtons = new NumpadButton[10];
        for (int i = 0; i < 10; i++) {
            numButtons[i] = new NumpadButton(String.valueOf(i));
            numButtons[i].addActionListener(actionMap.get(CalculatorFrame.NUMBER_ACTIONS[i]));
        }
        dotBtn = new NumpadButton(".");
        dotBtn.addActionListener(actionMap.get(CalculatorFrame.DOT_ACTION));
        
        memClearBtn = new MemButton("MC");
        memClearBtn.addActionListener(actionMap.get(CalculatorFrame.MEMORY_CLEAR_ACTION));
        memRecallBtn = new MemButton("MR");
        memRecallBtn.addActionListener(actionMap.get(CalculatorFrame.MEMORY_RECALL_ACTION));
        memSaveBtn = new MemButton("MS");
        memSaveBtn.addActionListener(actionMap.get(CalculatorFrame.MEMORY_SAVE_ACTION));
        memAddBtn = new MemButton("M+");
        memAddBtn.addActionListener(actionMap.get(CalculatorFrame.MEMORY_ADD_ACTION));
        memSubtractBtn = new MemButton("M-");
        memSubtractBtn.addActionListener(actionMap.get(CalculatorFrame.MEMORY_SUBTRACT_ACTION));
        
        addBtn = new FunctionButton("+");
        addBtn.addActionListener(actionMap.get(CalculatorFrame.ADD_ACTION));
        subtractBtn = new FunctionButton("-");
        subtractBtn.addActionListener(actionMap.get(CalculatorFrame.SUBTRACT_ACTION));
        multiplyBtn = new FunctionButton("*");
        multiplyBtn.addActionListener(actionMap.get(CalculatorFrame.MULTIPLY_ACTION));
        divideBtn = new FunctionButton("/");
        divideBtn.addActionListener(actionMap.get(CalculatorFrame.DIVIDE_ACTION));
        
        evalBtn = new FunctionButton("=");
        evalBtn.addActionListener(actionMap.get(CalculatorFrame.EVALUATE_ACTION));
        signBtn = new FunctionButton("\u00B1");
        signBtn.setFont(buttonFont2);
        signBtn.addActionListener(actionMap.get(CalculatorFrame.CHANGE_SIGN_ACTION));
        sqrtBtn = new FunctionButton("\u221A");
        sqrtBtn.setFont(buttonFont2);
        sqrtBtn.addActionListener(actionMap.get(CalculatorFrame.SQUARE_ROOT_ACTION));
        percentBtn = new FunctionButton("%");
        percentBtn.setFont(buttonFont2);
        percentBtn.addActionListener(actionMap.get(CalculatorFrame.PERCENTAGE_ACTION));
        reciprocalBtn = new FunctionButton("<html>1/<i>x</i></html>");
        reciprocalBtn.setFont(buttonFont2);
        reciprocalBtn.addActionListener(actionMap.get(CalculatorFrame.RECIPROCAL_ACTION));
        eraseBtn = new FunctionButton("\u2190");
        eraseBtn.addActionListener(actionMap.get(CalculatorFrame.ERASE_ACTION));
        clearEntryBtn = new FunctionButton("CE");
        clearEntryBtn.setFont(buttonFont1);
        clearEntryBtn.addActionListener(actionMap.get(CalculatorFrame.CLEAR_ENTRY_ACTION));
        clearBtn = new FunctionButton("C");
        clearBtn.setFont(buttonFont1);
        clearBtn.addActionListener(actionMap.get(CalculatorFrame.CLEAR_ACTION));
        
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        JPanel displayPane = new JPanel(new BorderLayout());
        displayPane.setBackground(displayColor);
        // needed to prevent the label from invalidating the layout
        displayPane.setPreferredSize(new Dimension(198, 0));
        displayPane.setBorder(BorderFactory.createLineBorder(displayBorderColor));
        displayPane.add(memoryLbl, BorderLayout.WEST);
        displayPane.add(displayTxt, BorderLayout.CENTER);
        
        setBackground(backgroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints c;
        c = new GridBagConstraints(0, 0, 5, 1, 1, 1, GridBagConstraints.PAGE_START,
                GridBagConstraints.BOTH, DF_INSETS, 0, 0);
        add(displayPane, c);
        
        // row 1
        c = new GridBagConstraints(0, 1, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(memClearBtn, c);
        c = new GridBagConstraints(1, 1, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(memRecallBtn, c);
        c = new GridBagConstraints(2, 1, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(memSaveBtn, c);
        c = new GridBagConstraints(3, 1, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(memAddBtn, c);
        c = new GridBagConstraints(4, 1, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(memSubtractBtn, c);
        
        // row 2
        c = new GridBagConstraints(0, 2, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(eraseBtn, c);
        c = new GridBagConstraints(1, 2, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(clearEntryBtn, c);
        c = new GridBagConstraints(2, 2, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(clearBtn, c);
        c = new GridBagConstraints(3, 2, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(signBtn, c);
        c = new GridBagConstraints(4, 2, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(sqrtBtn, c);
        
        // row 3
        c = new GridBagConstraints(0, 3, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[7], c);
        c = new GridBagConstraints(1, 3, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[8], c);
        c = new GridBagConstraints(2, 3, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[9], c);
        c = new GridBagConstraints(3, 3, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(divideBtn, c);
        c = new GridBagConstraints(4, 3, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(percentBtn, c);
        
        // row 4
        c = new GridBagConstraints(0, 4, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[4], c);
        c = new GridBagConstraints(1, 4, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[5], c);
        c = new GridBagConstraints(2, 4, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[6], c);
        c = new GridBagConstraints(3, 4, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(multiplyBtn, c);
        c = new GridBagConstraints(4, 4, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(reciprocalBtn, c);
        
        // row 5
        c = new GridBagConstraints(0, 5, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[1], c);
        c = new GridBagConstraints(1, 5, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[2], c);
        c = new GridBagConstraints(2, 5, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[3], c);
        c = new GridBagConstraints(3, 5, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(subtractBtn, c);
        c = new GridBagConstraints(4, 5, DF_GRID_WIDTH, 2, DF_WEIGHTX, DF_WEIGHTY,
                DF_ANCHOR, GridBagConstraints.BOTH, DF_INSETS, DF_IPADX, DF_IPADY);                
        add(evalBtn, c);
        
        // row 6
        c = new GridBagConstraints(0, 6, 2, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(numButtons[0], c);
        c = new GridBagConstraints(2, 6, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(dotBtn, c);
        c = new GridBagConstraints(3, 6, DF_GRID_WIDTH, DF_GRID_HEIGHT,
                DF_WEIGHTX, DF_WEIGHTY, DF_ANCHOR, DF_FILL, DF_INSETS, DF_IPADX, DF_IPADY);
        add(addBtn, c);
        
        UIManager.put("Button.select", mousePressedColor);
    }
    
    private class CalcButton extends JButton {
    	private static final long serialVersionUID = 1L;
        CalcButton(String title) {
            super(title);
            setMargin(new Insets(0, 0, 0, 0));
            setMinimumSize(new Dimension(30, 25));
            setFocusPainted(false);
            setForeground(buttonFontColor);
            addMouseListener(new CalcButtonMouseAdapter());
        }
    }
    
    private class CalcButtonMouseAdapter extends MouseAdapter {
        private Color originalColor;
        private Border originalBorder;
        
        @Override
        public void mouseEntered(MouseEvent evt) {
            CalcButton source = (CalcButton) evt.getSource();
            originalColor = source.getBackground();
            originalBorder = source.getBorder();
            source.setBackground(mouseOverColor);
            source.setBorder(BorderFactory.createLineBorder(buttonBorderColor));
        }
        
        @Override
        public void mouseExited(MouseEvent evt) {
            CalcButton source = (CalcButton) evt.getSource();
            source.setBackground(originalColor);
            source.setBorder(originalBorder);
        }
    }
    
    private class NumpadButton extends CalcButton {
    	private static final long serialVersionUID = 1L;
        NumpadButton(String title) {
            super(title);
            setFont(buttonFont3);
            setBackground(numpadButtonColor);
        }
    }
    
    private class FunctionButton extends CalcButton {
    	private static final long serialVersionUID = 1L;
        FunctionButton(String title) {
            super(title);
            setFont(buttonFont3);
            setBackground(functionButtonColor);
        }
    }
    
    private class MemButton extends CalcButton {
    	private static final long serialVersionUID = 1L;
        MemButton(String title) {
            super(title);
            setFont(buttonFont1);
            setBackground(memoryButtonColor);
        }
    }
    
    public String getDisplayText() {
        return displayTxt.getText();
    }
    
    public void setDisplayText(String text) {
        if (errorState) {
            displayTxt.setFont(errorDisplayFont);
        }
        else if (text.length() > 20) {
            displayTxt.setFont(smallDisplayFont3);
        }
        else if (text.length() > 16) {
            displayTxt.setFont(smallDisplayFont2);
        }
        else if (text.length() > 12) {
            displayTxt.setFont(smallDisplayFont1);
        }
        else {
            displayTxt.setFont(regularDisplayFont);
        }
        displayTxt.setText(text);
    }
    
    public void setErrorState(boolean state) {
        errorState = state;
    }
    
    public void enableMemoryLabel(boolean enable) {
        if (enable) {
            memoryLbl.setText("M");
        }
        else {
            memoryLbl.setText(" ");
        }
    }
}
