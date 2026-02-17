package Calculator;

import javax.swing.*;
import java.awt.*;

public class CalcFrame extends JFrame
{
    JTextField txt_operand1 = new JTextField();
    private JLabel lbl_operation = new JLabel();
    private JLabel lbl_operand2 = new JLabel();
    private JButton one = new JButton("1");
    private JButton two = new JButton("2");
    private JButton three = new JButton("3");
    private JButton four = new JButton("4");
    private JButton fivr = new JButton("5");
    private JButton six = new JButton("6");
    private JButton seven = new JButton("7");
    private JButton eight = new JButton("8");
    private JButton nine = new JButton("9");
    private JButton zero = new JButton("0");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multiply = new JButton("*");
    private JButton divide = new JButton("/");
    private JButton equal = new JButton("=");
    private JButton decimal = new JButton(".");
    private JButton pm = new JButton("-/+");
    private JButton clear = new JButton("C");
    private JButton backspace = new JButton("⌫");


    public CalcFrame() {
            super("Calculator");
            //set up
        {
            setSize(325, 420);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setLayout(null);

            lbl_operand2.setBounds(20, 15, 255, 20);
            lbl_operand2.setFont(new Font("Dialog", Font.PLAIN, 16));
            lbl_operand2.setHorizontalAlignment(JLabel.RIGHT);
            add(lbl_operand2);

            lbl_operation.setBounds(270, 30, 20, 20);
            lbl_operation.setFont(new Font("Dialog", Font.PLAIN, 12));
            add(lbl_operation);

            txt_operand1.setBounds(20, 50, 265, 30);
            txt_operand1.setHorizontalAlignment(JLabel.RIGHT);
            txt_operand1.setFont(new Font("Dialog", Font.PLAIN, 16));
            add(txt_operand1);
            txt_operand1.setEditable(false);
        }

        //numbers
        {
            one.setBounds(20, 145, 45, 45);
            add(one);
            one.addActionListener(e -> oneClicked());

            two.setBounds(75, 145, 45, 45);
            add(two);
            two.addActionListener(e -> twoClicked());

            three.setBounds(130, 145, 45, 45);
            add(three);
            three.addActionListener(e -> threeClicked());

            four.setBounds(20, 200, 45, 45);
            add(four);
            four.addActionListener(e -> fourClicked());

            fivr.setBounds(75, 200, 45, 45);
            add(fivr);
            fivr.addActionListener(e -> fiveClicked());

            six.setBounds(130, 200, 45, 45);
            add(six);
            six.addActionListener(e -> sixClicked());

            seven.setBounds(20, 255, 45, 45);
            add(seven);
            seven.addActionListener(e -> sevenClicked());

            eight.setBounds(75, 255, 45, 45);
            add(eight);
            eight.addActionListener(e -> eightClicked());

            nine.setBounds(130, 255, 45, 45);
            add(nine);
            nine.addActionListener(e -> nineClicked());

            zero.setBounds(185, 145, 45, 155);
            add(zero);
            zero.addActionListener(e -> zeroClicked());
        }

        //operations
        {
            plus.setBounds(240, 90, 45, 45);
            add(plus);
            plus.addActionListener(e -> plusClicked());

            minus.setBounds(240, 145, 45, 45);
            add(minus);
            minus.addActionListener(e -> minusClicked());

            divide.setBounds(240, 200, 45, 45);
            add(divide);
            divide.addActionListener(e -> divideClicked());

            multiply.setBounds(240, 255, 45, 45);
            add(multiply);
            multiply.addActionListener(e -> multiplyClicked());

            equal.setBounds(20, 310, 45+55*4, 45);
            add(equal);
            equal.addActionListener(e -> equalClicked());
        }

        //other (decimals, clear, sign, backspace)
        {
            decimal.setBounds(75, 90, 45, 45);
            add(decimal);
            decimal.addActionListener(e -> decimalClicked());

            pm.setBounds(130, 90, 45, 45);
            pm.setFont(new Font("Dialog", Font.PLAIN, 9));
            add(pm);
            pm.addActionListener(e -> pmClicked());

            clear.setBounds(185, 90, 45, 45);
            add(clear);
            clear.addActionListener(e -> clearClicked());

            backspace.setBounds(20, 90, 45, 45);
            backspace.setFont(new Font("Dialog", Font.PLAIN, 8));
            add(backspace);
            backspace.addActionListener(e -> backspaceClicked());
        }

        setVisible(true);
    }

    public void oneClicked ()
    {
        if (txt_operand1.getText().contains("E"))
            txt_operand1.setText("1");
        else txt_operand1.setText(txt_operand1.getText() + "1");
    }

    public void twoClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("2");
        else txt_operand1.setText(txt_operand1.getText() + "2");
    }

    public void threeClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("3");
        else txt_operand1.setText(txt_operand1.getText() + "3");
    }

    public void fourClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("4");
        else txt_operand1.setText(txt_operand1.getText() + "4");
    }

    public void fiveClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("5");
        else txt_operand1.setText(txt_operand1.getText() + "5");
    }

    public void sixClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("6");
        else txt_operand1.setText(txt_operand1.getText() + "6");
    }

    public void sevenClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("7");
        else txt_operand1.setText(txt_operand1.getText() + "7");
    }

    public void eightClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("8");
        else txt_operand1.setText(txt_operand1.getText() + "8");
    }

    public void nineClicked ()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("9");
        else txt_operand1.setText(txt_operand1.getText() + "9");
    }

    public void zeroClicked ()
    {
        if (txt_operand1.getText().contains("E"))
            txt_operand1.setText("0");
        else txt_operand1.setText(txt_operand1.getText() + "0");
    }

    private void plusClicked()
    {
        if (lbl_operand2.getText().contains("Error") || lbl_operand2.getText().contains("Erro") || lbl_operand2.getText().contains("Err") || lbl_operand2.getText().contains("Er") || lbl_operand2.getText().contains("E"))
        {}
        else if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
        {}
        else if (txt_operand1.getText().equals("-") || txt_operand1.getText().equals(".") || txt_operand1.getText().equals("-."))
        {}
        else {
            if (!txt_operand1.getText().isEmpty()) {
                if (lbl_operation.getText().isEmpty()) {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("+");
                    txt_operand1.setText("");
                    System.out.println(1);
                }
            } else if (!lbl_operand2.getText().isEmpty()) {
                lbl_operation.setText("+");
                System.out.println(2);
            }
            if (!txt_operand1.getText().isEmpty() && !lbl_operation.getText().isEmpty() && !lbl_operand2.getText().isEmpty()) {
                equalClicked();
                if (txt_operand1.getText().contains("Error")) {
                } else {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("+");
                    txt_operand1.setText("");
                    System.out.println(3);
                }
            }
        }
    }

    private void minusClicked()
    {
        if (lbl_operand2.getText().contains("Error") || lbl_operand2.getText().contains("Erro") || lbl_operand2.getText().contains("Err") || lbl_operand2.getText().contains("Er") || lbl_operand2.getText().contains("E"))
        {}
        else if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
        {}
        else if (txt_operand1.getText().equals("-") || txt_operand1.getText().equals(".") || txt_operand1.getText().equals("-."))
        {}
        else {
            if (!txt_operand1.getText().isEmpty()) {
                if (lbl_operation.getText().isEmpty()) {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("-");
                    txt_operand1.setText("");
                    System.out.println(1);
                }
            } else if (!lbl_operand2.getText().isEmpty()) {
                lbl_operation.setText("-");
                System.out.println(2);
            }
            if (!txt_operand1.getText().isEmpty() && !lbl_operation.getText().isEmpty() && !lbl_operand2.getText().isEmpty()) {
                equalClicked();
                if (txt_operand1.getText().contains("Error")) {
                } else {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("-");
                    txt_operand1.setText("");
                    System.out.println(3);
                }
            }
        }
    }

    private void multiplyClicked()
    {
        if (lbl_operand2.getText().contains("Error") || lbl_operand2.getText().contains("Erro") || lbl_operand2.getText().contains("Err") || lbl_operand2.getText().contains("Er") || lbl_operand2.getText().contains("E"))
        {}
        else if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
        {}
        else if (txt_operand1.getText().equals("-") || txt_operand1.getText().equals(".") || txt_operand1.getText().equals("-."))
        {}
        else {
            if (!txt_operand1.getText().isEmpty()) {
                if (lbl_operation.getText().isEmpty()) {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("*");
                    txt_operand1.setText("");
                    System.out.println(1);
                }
            } else if (!lbl_operand2.getText().isEmpty()) {
                lbl_operation.setText("*");
                System.out.println(2);
            }
            if (!txt_operand1.getText().isEmpty() && !lbl_operation.getText().isEmpty() && !lbl_operand2.getText().isEmpty()) {
                equalClicked();
                if (txt_operand1.getText().contains("Error")) {
                } else {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("*");
                    txt_operand1.setText("");
                    System.out.println(3);
                }
            }
        }
    }

    private void divideClicked()
    {
        if (lbl_operand2.getText().contains("Error") || lbl_operand2.getText().contains("Erro") || lbl_operand2.getText().contains("Err") || lbl_operand2.getText().contains("Er") || lbl_operand2.getText().contains("E"))
        {}
        else if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
        {}
        else if (txt_operand1.getText().equals("-") || txt_operand1.getText().equals(".") || txt_operand1.getText().equals("-."))
        {}
        else {
            if (!txt_operand1.getText().isEmpty()) {
                if (lbl_operation.getText().isEmpty()) {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("/");
                    txt_operand1.setText("");
                    System.out.println(1);
                }
            } else if (!lbl_operand2.getText().isEmpty()) {
                lbl_operation.setText("/");
                System.out.println(2);
            }
            if (!txt_operand1.getText().isEmpty() && !lbl_operation.getText().isEmpty() && !lbl_operand2.getText().isEmpty()) {
                equalClicked();
                if (txt_operand1.getText().contains("Error")) {
                } else {
                    lbl_operand2.setText(txt_operand1.getText());
                    lbl_operation.setText("/");
                    txt_operand1.setText("");
                    System.out.println(3);
                }
            }
        }
    }

    private void equalClicked()
    {
        if (!lbl_operand2.getText().isEmpty() && !txt_operand1.getText().isEmpty())
        {
            if (lbl_operand2.getText().contains("Error") || lbl_operand2.getText().contains("Erro") || lbl_operand2.getText().contains("Err") || lbl_operand2.getText().contains("Er") || lbl_operand2.getText().contains("E"))
                txt_operand1.setText("Error");
            else if (txt_operand1.getText().equals("-") || txt_operand1.getText().equals(".") || txt_operand1.getText().equals("-."))
            {
                txt_operand1.setText("Error");
            }
            else {
                double v1 = Double.parseDouble(txt_operand1.getText());
                double v2 = Double.parseDouble(lbl_operand2.getText());

                if (lbl_operation.getText().equals("+"))
                    txt_operand1.setText(""+(v2+v1));
                else if (lbl_operation.getText().equals("-"))
                    txt_operand1.setText(""+(v2-v1));
                else if (lbl_operation.getText().equals("*"))
                    txt_operand1.setText(""+(v2*v1));
                else if (lbl_operation.getText().equals("/"))
                {
                    System.out.println("/");
                    if (v1 == 0) {
                        System.out.println("/0");
                        txt_operand1.setText("Error");
                    }
                    else txt_operand1.setText("" + (v2 / v1));

                }
            }
            lbl_operation.setText("");
            lbl_operand2.setText("");
        }
    }

    private void decimalClicked()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText(".");
        else if (!txt_operand1.getText().contains("."))
            txt_operand1.setText(txt_operand1.getText() + ".");
    }

    private void pmClicked()
    {
        if (txt_operand1.getText().contains("Error") || txt_operand1.getText().contains("Erro") || txt_operand1.getText().contains("Err") || txt_operand1.getText().contains("Er") || txt_operand1.getText().contains("E"))
            txt_operand1.setText("-");
        else if (txt_operand1.getText().contains("-"))
            txt_operand1.setText(txt_operand1.getText().substring(1));
        else txt_operand1.setText("-" + txt_operand1.getText());
    }

    private void clearClicked()
    {
        txt_operand1.setText("");
        lbl_operand2.setText("");
        lbl_operation.setText("");
    }

    private void backspaceClicked()
    {
        if (!txt_operand1.getText().isEmpty())
            txt_operand1.setText(txt_operand1.getText().substring(0,txt_operand1.getText().length()-1));
    }
}