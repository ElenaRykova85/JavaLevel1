package listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private JTextField inputField;
    public static double numbers;
    public static boolean memory = false;
    public static boolean digital = false;
    public static String operator = "";


    public ButtonListener(JTextField inputField) {

        this.inputField = inputField;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Clear") {
            inputField.setText("");
            numbers = 0;
            return;
        }
        JButton btn = (JButton) e.getSource();
        String s = btn.getText();
        String current = inputField.getText();
        if (operator.equals("")&&s.matches("[^0-9]")) {
            operator = s;
        } else if(s.matches("[^0-9]")){
            if (s!="=" &&s!="C") {
                s = operator;
                operator = btn.getText();
            }
        }
        switch (s) {
            case ("C"):
                numbers = 0;
                operator="";
                memory=false;
                digital=false;
                inputField.setText("");
                break;
            case ("+"):
                digital = false;
                if (memory) {
                    numbers = numbers + Double.valueOf(current);
                    inputField.setText(String.valueOf(numbers));
                } else {
                    if (current.equals("")) {
                        numbers = 0;
                    } else {
                        numbers = Double.valueOf(current);
                    }
                    memory = true;
                    inputField.setText("");
                }
                break;

            case ("-"):
                digital = false;
                if (memory) {
                    numbers = numbers - Double.valueOf(current);
                    inputField.setText(String.valueOf(numbers));
                } else {
                    if (current.equals("")) {
                        numbers = 0;
                    } else {
                        numbers = Double.valueOf(current);
                    }
                    memory = true;
                    inputField.setText("");
                }
                break;
            case ("/"):
                digital = false;
                if (memory) {
                    numbers = numbers / Double.valueOf(current);
                    inputField.setText(String.valueOf(numbers));
                } else {
                    if (current.equals("")) {
                        numbers = 0;
                    } else {
                        numbers = Double.valueOf(current);
                    }
                    memory = true;
                    inputField.setText("");
                }
                break;
            case ("x"):
                digital = false;
                if (memory) {
                    numbers = numbers * Double.valueOf(current);
                    inputField.setText(String.valueOf(numbers));
                } else {
                    if (current.equals("")) {
                        numbers = 0;
                    } else {
                        numbers = Double.valueOf(current);
                    }
                    memory = true;
                    inputField.setText("");
                }
                break;
            case ("="):
                digital = false;
                memory = false;
                switch (operator) {
                    case ("+"):
                        numbers = numbers + Double.valueOf(current);
                        break;
                    case ("-"):
                        numbers = numbers - Double.valueOf(current);
                        break;
                    case ("/"):
                        numbers = numbers / Double.valueOf(current);
                        break;
                    case ("x"):
                        numbers = numbers * Double.valueOf(current);
                        break;
                }
                operator = "";
                memory = false;
                inputField.setText(String.valueOf(numbers));
                break;
            default:
                if (!digital) {
                    inputField.setText("");
                    current="";
                }
                digital = true;
                inputField.setText(current + btn.getText());
                break;
        }

        //  numbers = numbers + Double.valueOf(btn.getText());
        // inputField.setText(String.valueOf(numbers));
        // inputField.setText(current + btn.getText());
    }
}
