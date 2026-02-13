package FinalCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FinalCalc extends JFrame
{

    private JComboBox<Integer> cmb_numTerms;
    private JTextField txt_term1 = new JTextField();
    private JTextField txt_term2 = new JTextField();
    private JTextField finalWeight = new JTextField();
    private JTextField txt_term2 = new JTextField();

    private JLabel lbl_ttw = new JLabel("Total Term Weight:");
    private JLabel lbl_fw = new JLabel("Final Weight:");
    private JLabel lbl_numTerms = new JLabel("# of Terms");
    private JLabel lbl_gw = new JLabel("Grade Wanted:");
    private JLabel lbl_term1 = new JLabel("Term 1:");
    private JLabel lbl_term2 = new JLabel("Term 2:");
    private JLabel lbl_term3 = new JLabel("Term 3:");
    private JLabel lbl_term4 = new JLabel("Term 4:");
    private JLabel lbl_term5 = new JLabel("Term 5:");

    public FinalCalc()
    {
        super("Final Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(300,400);

        lbl_numTerms.setBounds(20,130,100,30);
        add(lbl_numTerms);

        Integer[] numbers = {1,2};
        cmb_numTerms = new JComboBox<>(numbers);
        cmb_numTerms.setBounds(120,130,100,30);
        cmb_numTerms.addActionListener (e -> changeTerms());
        add(cmb_numTerms);

        lbl_term1.setBounds(20,170,100,30);
        add(lbl_term1);
        txt_term1.setBounds(120,170,100,30);
        add(txt_term1);

        lbl_term2.setBounds(20,210,100,30);
        add(lbl_term2);
        txt_term2.setBounds(120,210,100,30);
        add(txt_term2);

        setVisible(true);
    }

    private void changeTerms()
    {
        if ((Integer) cmb_numTerms.getSelectedItem() == 1)
        {
            txt_term2.setText("");
            txt_term2.setEnabled(false);
            lbl_term2.setEnabled(false);
        }
        else {
            txt_term1.setEnabled(true);
            lbl_term1.setEnabled(true);
        }

    }
}
