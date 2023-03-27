import components.DigitJButton;
import components.OperatorJButton;
import listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ApplicationForm extends JFrame {


    private JTextField inputField;

    public ApplicationForm(String title) {
        super(title);

        setBounds(1000, 400, 300, 400);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        ActionListener buttonListener = new ButtonListener(inputField);
        setJMenuBar(createMenu(buttonListener));
        add(createCenterPanel(buttonListener), BorderLayout.CENTER);
        setVisible(true);

    }
    private JMenuBar createMenu(ActionListener buttonListener) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(buttonListener);
        JMenu menuEdit = new JMenu("Edit");
        JMenu menuAbout = new JMenu("About");

        menuBar.add(menuFile);
        menuFile.add(clear);

            JMenuItem exit = new JMenuItem("Exit");
//            exit.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.exit(0);
 //               }
 //           });
        exit.addActionListener(e -> System.exit(0));
        menuFile.add(exit);

        menuBar.add(menuEdit);
        menuEdit.add(new JMenuItem("Copy"));
        menuEdit.add(new JMenuItem("Paste"));
        menuBar.add(menuAbout);


        return menuBar;
    }

    private JPanel createTopPanel() {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        inputField = new JTextField();
        inputField.setEditable(false);
        top.add(inputField);
        inputField.setFont(new Font("Arial", Font.PLAIN, 25));
        inputField.setMargin(new Insets(2, 0, 5,0));
        inputField.setBackground(new Color(248, 232, 130));
        return top;
    }
    private JPanel createCenterPanel(ActionListener buttonListener) {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(createDigitsPanel(buttonListener), BorderLayout.CENTER);
        centerPanel.add(createOperatorsPanel(buttonListener), BorderLayout.WEST);

        return centerPanel;
    }

    private JPanel createOperatorsPanel(ActionListener buttonListener) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        JButton minus = new OperatorJButton("-");
        minus.addActionListener(buttonListener);
        panel.add(minus);
        JButton plus = new OperatorJButton("+");
        plus.addActionListener(buttonListener);
        panel.add(plus);
        JButton multiply = new OperatorJButton("x");
        multiply.addActionListener(buttonListener);
        panel.add(multiply);
        JButton divide = new OperatorJButton("/");
        divide.addActionListener(buttonListener);
        panel.add(divide);
        return panel;
    }

    private JPanel createDigitsPanel(ActionListener buttonListener) {
        JPanel digitsPanel = new JPanel();
        digitsPanel.setLayout(new GridLayout(4,3));
        for (int i = 0; i <10 ; i++) {
            String buttonTitle = (i==9) ? "0" : String.valueOf(i+1);
            JButton btn = new DigitJButton(buttonTitle);
            btn.addActionListener(buttonListener);
            digitsPanel.add(btn);
        }
        JButton calc = new OperatorJButton("=");
        calc.addActionListener(buttonListener);
//btn.addActionListener(buttonListener);
        digitsPanel.add(calc);
        //calc.setEnabled(false);

        JButton clear = new OperatorJButton("C");
        clear.addActionListener(buttonListener);
        //btn.addActionListener(buttonListener);
        digitsPanel.add(clear);


        return digitsPanel;

    }

}
