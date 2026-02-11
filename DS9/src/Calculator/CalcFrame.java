package Calculator;

import javax.swing.*;

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
    private JButton pm = new JButton("√+");
    private JButton clear = new JButton("C");


    public CalcFrame()
    {
        super("Calculator");
        setSize(400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        lbl_operand2.setBounds(20,20,210,20);
        add(lbl_operand2);

        lbl_operation.setBounds(210,40,20,20);
        add(lbl_operation);

        txt_operand1.setBounds(20,60,210,20);
        add(txt_operand1);
        txt_operand1.setEditable(false);

        //numbers
        {
            one.setBounds(20, 90+55, 45, 45);
            add(one);
            one.addActionListener(e -> oneClicked());

            two.setBounds(75, 90+55, 45, 45);
            add(two);
            two.addActionListener(e -> twoClicked());

            three.setBounds(130, 90+55, 45, 45);
            add(three);
            three.addActionListener(e -> threeClicked());

            four.setBounds(20, 145+55, 45, 45);
            add(four);
            four.addActionListener(e -> fourClicked());

            fivr.setBounds(75, 145+55, 45, 45);
            add(fivr);
            fivr.addActionListener(e -> fiveClicked());

            six.setBounds(130, 145+55, 45, 45);
            add(six);
            six.addActionListener(e -> sixClicked());

            seven.setBounds(20, 200+55, 45, 45);
            add(seven);
            seven.addActionListener(e -> sevenClicked());

            eight.setBounds(75, 200+55, 45, 45);
            add(eight);
            eight.addActionListener(e -> eightClicked());

            nine.setBounds(130, 200+55, 45, 45);
            add(nine);
            nine.addActionListener(e -> nineClicked());

            zero.setBounds(185, 90+55, 45, 155);
            add(zero);
            zero.addActionListener(e -> zeroClicked());
        }

        //operations
        {
            plus.setBounds(285, 90, 45, 45);
            add(plus);
            plus.addActionListener(e -> plusClicked());

            minus.setBounds(285, 145, 45, 45);
            add(minus);
            minus.addActionListener(e -> minusClicked());

            divide.setBounds(285, 200, 45, 45);
            add(divide);
            divide.addActionListener(e -> divideClicked());

            multiply.setBounds(285, 255, 45, 45);
            add(multiply);
            multiply.addActionListener(e -> multiplyClicked());

            equal.setBounds(20, 310, 45+55*3, 45);
            add(equal);
            equal.addActionListener(e -> equalClicked());
        }

        //other (decimals, clear, sqrt)
        {
            decimal.setBounds(285, 90, 45, 45);
            add(decimal);
            decimal.addActionListener(e -> decimalClicked());

            pm.setBounds(285, 90, 45, 45);
            add(pm);
            pm.addActionListener(e -> pmClicked());

            clear.setBounds(285, 90, 45, 45);
            add(clear);
            clear.addActionListener(e -> clearClicked());
        }

        setVisible(true);

    }


    public void oneClicked ()
    {
        txt_operand1.setText(txt_operand1.getText() + "1");
    }

    public void twoClicked () {txt_operand1.setText(txt_operand1.getText() + "2");}

    public void threeClicked () {txt_operand1.setText(txt_operand1.getText() + "3");}

    public void fourClicked () {txt_operand1.setText(txt_operand1.getText() + "4");}

    public void fiveClicked () {txt_operand1.setText(txt_operand1.getText() + "5");}

    public void sixClicked () {txt_operand1.setText(txt_operand1.getText() + "6");}

    public void sevenClicked () {txt_operand1.setText(txt_operand1.getText() + "7");}

    public void eightClicked () {txt_operand1.setText(txt_operand1.getText() + "8");}

    public void nineClicked () {txt_operand1.setText(txt_operand1.getText() + "9");}

    public void zeroClicked () {txt_operand1.setText(txt_operand1.getText() + "0");}

    private void plusClicked()
    {
        lbl_operand2.setText(txt_operand1.getText());
        lbl_operation.setText("+");
        txt_operand1.setText("");
    }

    private void minusClicked()
    {
        lbl_operand2.setText(txt_operand1.getText());
        lbl_operation.setText("-");
        txt_operand1.setText("");
    }

    private void multiplyClicked()
    {
        lbl_operand2.setText(txt_operand1.getText());
        lbl_operation.setText("*");
        txt_operand1.setText("");
    }

    private void divideClicked()
    {
        lbl_operand2.setText(txt_operand1.getText());
        lbl_operation.setText("/");
        txt_operand1.setText("");
    }

    private void equalClicked()
    {
        if (!lbl_operand2.getText().equals("") && !txt_operand1.getText().equals(""))
        {
            double v1 = Double.parseDouble(txt_operand1.getText());
            double v2 = Double.parseDouble(lbl_operand2.getText());

            if (lbl_operation.getText().equals("+"))
                txt_operand1.setText(""+(v2+v1));
            else if (lbl_operation.getText().equals("-"))
                txt_operand1.setText(""+(v2-v1));
            else if (lbl_operation.getText().equals("*"))
                txt_operand1.setText(""+(v2*v1));
            else if (lbl_operation.getText().equals("/"))
                txt_operand1.setText(""+(v2/v1));
            lbl_operation.setText("");
            lbl_operand2.setText("");
        }
    }

    private void decimalClicked() {txt_operand1.setText(txt_operand1.getText() + ".");}

    private void pmClicked() {txt_operand1.setText(txt_operand1.getText() + ".");} // change

    private void clearClicked() {txt_operand1.setText(txt_operand1.getText() + ".");} // change

}
