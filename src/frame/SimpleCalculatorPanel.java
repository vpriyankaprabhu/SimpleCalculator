package frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by priprabh on 11/2/2017.
 */
public class SimpleCalculatorPanel extends JApplet implements ActionListener {
    /**
     * Component Variables required to layout the Calculator
     */
    private JPanel buttonPanel;
    private JPanel inputOutputPanel;
    private JTextField info;
    private JButton button;
    private ArrayList<JButton> listOfButtons;
    private JRadioButton on;
    private JRadioButton off;

    /**
     * Variables used for simple calculator operations
     */
    private boolean editable = true;
    private double num, ans;
    private int calculation;
    private String temp = "";

    /**
     * Constructor to layout the buttons and other related components on the canvas for simple calculator and to perform actions on events like onClick etc.
     */
    public SimpleCalculatorPanel() {
        int KeySize = 60;
        /** The main contentPane for laying out all the components.*/
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));

        /** buttonPanel to layout the buttons to perform operations.*/
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setSize(5 * KeySize, 4 * KeySize);
        buttonPanel.setLayout(new GridLayout(5, 4, 6, 6));
        //for padding purpose
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        /** inputOutputPanel to layout the textField to perform show the operations performed for user.*/
        inputOutputPanel = new JPanel();
        inputOutputPanel.setBackground(Color.black);
        inputOutputPanel.setLayout(new GridLayout(1, 1));
        //for padding purpose
        inputOutputPanel.setBorder(new EmptyBorder(40, 20, 40, 20));

        /** info is the textField which will show user the operations performed and results at end of the computations,
         * its part of inputOutputPanel. Here certain properties related to info field is set added to main contentPane.
         */
        info = new JTextField("0");
        info.setSize(KeySize * 4, 30);
        info.setHorizontalAlignment(JTextField.RIGHT);
        info.setEditable(editable);
        info.setFont(new Font("Lucida Console", Font.BOLD, 20));
        info.setBackground(Color.white);
        info.setBorder(BorderFactory.createLineBorder(Color.black));
        info.setPreferredSize(new Dimension(320, 35));
        info.addActionListener(this);
        inputOutputPanel.add(info, "NORTH");
        contentPane.add(inputOutputPanel);

        /** ON/OFF are the two buttons used mainly to switch the calculator on and off respectively.
         * These are added as part of ButtonGroup and On is default selected when applet is loaded first time.
         * The radioButtons then added to buttonPanelalong with other buttons to perform calculations/operations.
         */
        on = new JRadioButton("ON");
        off = new JRadioButton("OFF");
        ButtonGroup group = new ButtonGroup();
        group.add(on);
        group.add(off);
        on.setSelected(true);
        buttonPanel.add(on);
        buttonPanel.add(off);

        /** listOfButtons is a variable which stores the buttons list that should be added to buttonPanel after ON/OFF is added.*/
        listOfButtons = new ArrayList<JButton>();

        /** In this step the button list mentioned below is added to buttonPanel and its relative properties are set. */
        String buttons[] = {"←", "CLR", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "=", "+", "√"};
        for (String i : buttons) {
            /** Initialise the JButton from list of buttons */
            button = new JButton(i);

            /** try to set the color for the buttons for highlighting */
            if (i.equals("←") || i.equals("CLR")) {
                button.setBackground(Color.PINK);
            } else if (i.equals("+") || i.equals("-") || i.equals("*") || i.equals("/") || i.equals("√") || i.equals("=")) {
                button.setBackground(Color.CYAN);
            } else {
                button.setBackground(Color.lightGray);
            }

            /** Properties related to buttons are set */
            button.setName(i);
            button.setSize(KeySize, KeySize);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setFont(new Font("Lucida Console", Font.BOLD, 15));
            button.addActionListener(this);

            /** Add the button to buttonPanel and also listOfButtons arraylist */
            listOfButtons.add(button);
            buttonPanel.add(button);
        }

        /** ON ActionListener is executed when some actions are been done on this button such as OnClick of the ON Button,
         * the below mentioned statements will be executed to enable/disable the components related to its action.
         */
        on.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                on.setEnabled(false);
                on.setSelected(true);
                off.setSelected(false);
                off.setEnabled(true);
                info.setEnabled(true);
                for (JButton tempButton : listOfButtons) {
                    tempButton.setEnabled(true);
                }
            }
        });

        /** OFF ActionListener is executed when some actions are been done on this button such as OnClick of the OFF Button,
         * the below mentioned statements will be executed to enable/disable the components related to its action.
         */
        off.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                off.setEnabled(false);
                off.setSelected(true);
                on.setSelected(false);
                on.setEnabled(true);
                info.setText(" ");
                info.setEnabled(false);
                for (JButton tempButton : listOfButtons) {
                    tempButton.setEnabled(false);
                }
            }
        });

        /** Last but not the least add the buttons to the main ContentPane as the info TextField is added to inputOutputPanel which in turn
         * is again added to main Content Pane.
         * So the main ContentPane now contains inputOutputPanel and buttonPanel to showcase the simpleCalculator layout.
         */
        contentPane.add(buttonPanel);
    }

    /**
     * Method to fetch the info textField value.
     */
    private Double getTextFieldValue() {
        if (info.getText().equals("")) {
            return Double.parseDouble("0");
        } else {
            return Double.parseDouble(info.getText());
        }

    }

    /**
     * Method to perform actions when the the actions are acted upon components by the listeners.
     */
    public void actionPerformed(ActionEvent ae) {
        String s = ae.getActionCommand();
        try {
            if (s == "CLR") {
                /** If CLR button is pressed by user, all the text entered by user must be cleared.*/
                info.setText(" ");
                temp = "";
            } else if (s == "←") {
                /** If backspace "←" button is pressed by user, the text entered by user must be cleared one by one based on mo of times user presses this button.*/
                int length = info.getText().length();
                int number = length - 1;

                if (length > 0) {
                    StringBuilder back = new StringBuilder(info.getText());
                    back.deleteCharAt(number);
                    info.setText(back.toString());
                }
                temp = "";
            } else if (s == "+") {
                /** If "+" button is pressed by user, the numbers should be added based on inputs provided by user.*/
                num = getTextFieldValue();
                info.setText(" ");
                calculation = 1;
                temp = "";
            } else if (s == "-") {
                /** If "-" button is pressed by user, the numbers should be subtracted based on inputs provided by user.*/
                num = getTextFieldValue();
                info.setText(" ");
                calculation = 2;
                temp = "";
            } else if (s == "*") {
                /** If "*" button is pressed by user, the numbers should be multiplied based on inputs provided by user.*/
                num = getTextFieldValue();
                info.setText(" ");
                calculation = 3;
                temp = "";
            } else if (s == "/") {
                /** If "/" button is pressed by user, the numbers should be divided based on inputs provided by user.*/
                num = getTextFieldValue();
                info.setText(" ");
                calculation = 4;
                temp = "";
            } else if (s == "√") {
                /** If "√" button is pressed by user, sqrt of the number should be performed.*/
                num = getTextFieldValue();
                ans = Math.sqrt(num);
                info.setText(Double.toString(ans));
                temp = "";
            } else if (s == "=") {
                /** If "=" button is pressed by user, requested operation by user should be performed and result should be discplayed to user in info TextField box.*/
                Double inputVal = getTextFieldValue();
                switch (calculation) {
                    case 1:
                        ans = num + inputVal;
                        info.setText(Double.toString(ans));
                        break;
                    case 2:
                        ans = num - inputVal;
                        info.setText(Double.toString(ans));
                        break;
                    case 3:
                        ans = num * inputVal;
                        info.setText(Double.toString(ans));
                        break;
                    case 4:
                        ans = num / inputVal;
                        info.setText(Double.toString(ans));
                        break;
                }
            } else {
                temp = temp + s;
                info.setText(temp);
            }
        } catch (NumberFormatException e) {
            try {
                throw new Exception("Invalid Input provided by user!!!. Enter a valid number to work with operators.....");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}