package FinalCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FinalCalc extends JFrame
{

    private JComboBox<Integer> cmb_numTerms;
    private JTextField txt_term1 = new JTextField();
    private JTextField txt_term2 = new JTextField();
    private JTextField txt_term3 = new JTextField();
    private JTextField txt_term4 = new JTextField();
    private JTextField txt_term5 = new JTextField();
    private JTextField finalWeight = new JTextField();

    private JLabel lbl_ttw = new JLabel("Total Term Weight:");
    private JLabel lbl_fw = new JLabel("Final Weight:");
    private JLabel lbl_numTerms = new JLabel("Number of Terms:");
    private JLabel lbl_gw = new JLabel("Grade Wanted:");
    private JLabel lbl_term1 = new JLabel("Term Grade 1:");
    private JLabel lbl_term2 = new JLabel("Term Grade 2:");
    private JLabel lbl_term3 = new JLabel("Term Grade 3:");
    private JLabel lbl_term4 = new JLabel("Term Grade 4:");
    private JLabel lbl_term5 = new JLabel("Term Grade 5:");

    public FinalCalc()
    {
        super("Final Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(600,800);

        lbl_ttw.setBounds(20,20,300,35);
        lbl_ttw.setFont(new Font("Digital", Font.BOLD, 25));
        add(lbl_ttw);

        lbl_fw.setBounds(20,60,300,35);
        lbl_fw.setFont(new Font("Digital", Font.BOLD, 25));
        add(lbl_fw);



        lbl_numTerms.setBounds(20,130,100,30);
        add(lbl_numTerms);

        Integer[] numbers = {1,2};
        cmb_numTerms = new JComboBox<>(numbers);
        cmb_numTerms.setBounds(120,130,100,30);
        cmb_numTerms.addActionListener (e -> changeTerms());
        add(cmb_numTerms);

        lbl_term1.setBounds(20,170,100,30);
        add(lbl_term1);
        txt_term1.setBounds(320,170,250,30);
        add(txt_term1);

        lbl_term2.setBounds(20,210,100,30);
        add(lbl_term2);
        txt_term2.setBounds(320,210,250,30);
        add(txt_term2);

        lbl_term3.setBounds(20,250,100,30);
        add(lbl_term3);
        txt_term3.setBounds(320,250,250,30);
        add(txt_term3);

        lbl_term4.setBounds(20,290,100,30);
        add(lbl_term4);
        txt_term4.setBounds(320,290,250,30);
        add(txt_term4);

        lbl_term5.setBounds(20,330,100,30);
        add(lbl_term5);
        txt_term5.setBounds(320,330,250,30);
        add(txt_term5);

        setVisible(true);
    }

    private void changeTerms()
    {
        if ((Integer) cmb_numTerms.getSelectedItem() == 1)
        {
            txt_term2.setText("");
            txt_term1.setEnabled(true);
            lbl_term1.setEnabled(true);
        }
        else if ((Integer) cmb_numTerms.getSelectedItem() == 2)
        {
            txt_term2.setEnabled(true);
            lbl_term2.setEnabled(true);
        }

    }
}
