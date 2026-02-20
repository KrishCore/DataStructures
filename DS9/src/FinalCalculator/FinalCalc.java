package FinalCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FinalCalc extends JFrame
{

    private JComboBox<Integer> cmb_numTerms;
    private JTextField txt_term1 = new JTextField();
    private JTextField txt_term2 = new JTextField();
    private JTextField txt_term3 = new JTextField();
    private JTextField txt_term4 = new JTextField();
    private JTextField txt_term5 = new JTextField();
    private JTextField txt_ttw = new JTextField("85");
    private JTextField txt_fw = new JTextField("15");
    private JTextField txt_gw = new JTextField("90");

    private JButton btn_calculate = new JButton("Calculate");
    private JButton btn_clear = new JButton("Clear");

    private JLabel lbl_ttw = new JLabel("Total Term Weight:");
    private JLabel lbl_fw = new JLabel("Final Weight:");
    private JLabel lbl_numTerms = new JLabel("Number of Terms:");
    private JLabel lbl_gw = new JLabel("Grade Wanted:");
    private JLabel lbl_term1 = new JLabel("Term 1 Grade:");
    private JLabel lbl_term2 = new JLabel("Term 2 Grade:");
    private JLabel lbl_term3 = new JLabel("Term 3 Grade:");
    private JLabel lbl_term4 = new JLabel("Term 4 Grade:");
    private JLabel lbl_term5 = new JLabel("Term 5 Grade:");
    private JLabel lbl_cgnof = new JLabel("Calculated Grade Needed On Final");
    private JLabel lbl_grade = new JLabel("");

    public FinalCalc() {
        super("Final Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(605, 800);

        //Total Term Weight
        {
            lbl_ttw.setBounds(20, 20, 300, 35);
            lbl_ttw.setFont(new Font("Digital", Font.BOLD, 30));
            add(lbl_ttw);

            txt_ttw.setBounds(320, 20, 250, 35);
            txt_ttw.setFont(new Font("Digital", Font.BOLD, 30));

            txt_ttw.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_ttw.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_ttw);
        }

        //Final Weight
        {
            lbl_fw.setBounds(20,70,300,35);
            lbl_fw.setFont(new Font("Digital", Font.BOLD, 30));
            add(lbl_fw);

            txt_fw.setBounds(320,70,250,35);;
            txt_fw.setFont(new Font("Digital", Font.BOLD, 30));

            txt_fw.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_fw.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_fw);

            System.out.println(txt_fw.getText());
        }

        //Number of Terms
        {
            lbl_numTerms.setBounds(20, 120, 300, 35);
            lbl_numTerms.setFont(new Font("Digital", Font.BOLD, 30));
            add(lbl_numTerms);

            Integer[] numbers = {1, 2, 3, 4, 5};
            cmb_numTerms = new JComboBox<>(numbers);
            cmb_numTerms.setFont(new Font("Digital", Font.BOLD, 30));
            cmb_numTerms.addActionListener(e -> changeTerms());
            cmb_numTerms.setBounds(320, 120, 250, 35);

            txt_term2.setText("");
            txt_term2.setEnabled(false);
            lbl_term2.setEnabled(false);
            txt_term3.setText("");
            txt_term3.setEnabled(false);
            lbl_term3.setEnabled(false);
            txt_term4.setText("");
            txt_term4.setEnabled(false);
            lbl_term4.setEnabled(false);
            txt_term5.setText("");
            txt_term5.setEnabled(false);
            lbl_term5.setEnabled(false);
            add(cmb_numTerms);
        }

        //Grade Wanted
        {
            lbl_gw.setBounds(20, 170, 300, 35);
            lbl_gw.setFont(new Font("Digital", Font.BOLD, 30));
            add(lbl_gw);

            txt_gw.setBounds(320,170,250,35);;
            txt_gw.setFont(new Font("Digital", Font.BOLD, 30));

            txt_gw.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_gw.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_gw);
        }

        //Term 1
        {
            lbl_term1.setBounds(20, 240, 300, 35);
            lbl_term1.setFont(new Font("Digital", Font.BOLD, 30));
            add(lbl_term1);
            txt_term1.setBounds(320, 240, 250, 35);
            txt_term1.setFont(new Font("Digital", Font.BOLD, 30));

            txt_term1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_term1.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_term1);
        }

        //Term 2
        {
            lbl_term2.setBounds(20, 290, 300, 35);
            lbl_term2.setFont(new Font("Digital", Font.BOLD, 30));
            add(lbl_term2);
            txt_term2.setBounds(320, 290, 250, 35);
            txt_term2.setFont(new Font("Digital", Font.BOLD, 30));

            txt_term2.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_term2.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_term2);
        }

        //Term 3
        {
            lbl_term3.setBounds(20, 340, 300, 35);
            add(lbl_term3);
            lbl_term3.setFont(new Font("Digital", Font.BOLD, 30));
            txt_term3.setBounds(320, 340, 250, 35);
            txt_term3.setFont(new Font("Digital", Font.BOLD, 30));

            txt_term3.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_term3.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_term3);
        }

        //Term 4
        {
            lbl_term4.setBounds(20, 390, 300, 35);
            add(lbl_term4);
            lbl_term4.setFont(new Font("Digital", Font.BOLD, 30));
            txt_term4.setBounds(320, 390, 250, 35);
            txt_term4.setFont(new Font("Digital", Font.BOLD, 30));

            txt_term4.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_term4.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_term4);
        }

        //Term 5
        {
            lbl_term5.setBounds(20, 440, 300, 35);
            add(lbl_term5);
            lbl_term5.setFont(new Font("Digital", Font.BOLD, 30));
            txt_term5.setBounds(320, 440, 250, 35);
            txt_term5.setFont(new Font("Digital", Font.BOLD, 30));

            txt_term5.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()) || Integer.parseInt(txt_term5.getText() + e.getKeyChar()) > 100 || e.getKeyChar() == 0) {
                        e.consume();
                        System.out.println(e.getKeyChar());
                    }
                }
            });
            add(txt_term5);
        }

        //Grade Needed on Final Exam
        {
            lbl_cgnof.setBounds(0,650,super.getWidth()-10,30);
            lbl_cgnof.setFont(new Font("Digital", Font.BOLD, 20));
            lbl_cgnof.setHorizontalAlignment(SwingConstants.CENTER);
            add(lbl_cgnof);
        }

        //Calculate
        {
            btn_calculate.setBounds(10,550,570,40);
            btn_calculate.setFont(new Font("Digital", Font.BOLD, 30));
            add(btn_calculate);

            btn_calculate.addActionListener(e -> {
                if (Integer.parseInt(txt_ttw.getText()) + Integer.parseInt(txt_fw.getText()) != 100)
                {
                    JOptionPane.showConfirmDialog(null, "The provided term weight and final weight are inaccurate\nBe better", "Error Message", JOptionPane.DEFAULT_OPTION);
                }
                else {
                    if ((Integer) cmb_numTerms.getSelectedItem() == 1) {
                        int avg = (Integer.parseInt(txt_term1.getText()));
                        System.out.println(avg);
                        double fg = (Integer.parseInt(txt_gw.getText()) - Integer.parseInt(txt_ttw.getText()) / 100.0 * (avg)) / Integer.parseInt(txt_fw.getText()) / 100.0;
                        System.out.println(fg * 10000);
                        lbl_grade.setText(fg * 10000 + "");
                    } else if ((Integer) cmb_numTerms.getSelectedItem() == 2) {
                        int avg = (Integer.parseInt(txt_term1.getText()) + Integer.parseInt(txt_term2.getText())) / 2;
                        System.out.println(avg);
                        double fg = (Integer.parseInt(txt_gw.getText()) - Integer.parseInt(txt_ttw.getText()) / 100.0 * (avg)) / Integer.parseInt(txt_fw.getText()) / 100.0;
                        System.out.println(fg * 10000);
                        lbl_grade.setText(fg * 10000 + "");
                    } else if ((Integer) cmb_numTerms.getSelectedItem() == 3) {
                        int avg = (Integer.parseInt(txt_term1.getText()) + Integer.parseInt(txt_term2.getText()) + Integer.parseInt(txt_term3.getText())) / 3;
                        System.out.println(avg);
                        double fg = (Integer.parseInt(txt_gw.getText()) - Integer.parseInt(txt_ttw.getText()) / 100.0 * (avg)) / Integer.parseInt(txt_fw.getText()) / 100.0;
                        System.out.println(fg * 10000);
                        lbl_grade.setText(fg * 10000 + "");
                    } else if ((Integer) cmb_numTerms.getSelectedItem() == 4) {
                        int avg = (Integer.parseInt(txt_term1.getText()) + Integer.parseInt(txt_term2.getText()) + Integer.parseInt(txt_term3.getText()) + Integer.parseInt(txt_term4.getText())) / 4;
                        System.out.println(avg);
                        double fg = (Integer.parseInt(txt_gw.getText()) - Integer.parseInt(txt_ttw.getText()) / 100.0 * (avg)) / Integer.parseInt(txt_fw.getText()) / 100.0;
                        System.out.println(fg * 10000);
                        lbl_grade.setText(fg * 10000 + "");

                    } else if ((Integer) cmb_numTerms.getSelectedItem() == 5) {
                        int avg = (Integer.parseInt(txt_term1.getText()) + Integer.parseInt(txt_term2.getText()) + Integer.parseInt(txt_term3.getText()) + Integer.parseInt(txt_term4.getText()) + Integer.parseInt(txt_term5.getText())) / 5;
                        System.out.println(avg);
                        double fg = (Integer.parseInt(txt_gw.getText()) - Integer.parseInt(txt_ttw.getText()) / 100.0 * (avg)) / Integer.parseInt(txt_fw.getText()) / 100.0;
                        System.out.println(fg * 10000);
                        lbl_grade.setText(fg * 10000 + "");
                    }
                }
            });

            //Grade
            {
                lbl_grade.setBounds(10, 675, 570, 40);
                lbl_grade.setHorizontalAlignment(JLabel.CENTER);
                lbl_grade.setFont(new Font("Digital", Font.BOLD, 25));
                add(lbl_grade);
            }
        }
        //Clear
        {
            btn_clear.setBounds(10,600,570,40);
            btn_clear.setFont(new Font("Digital", Font.BOLD, 30));
            btn_clear.addActionListener(e -> {
                txt_term1.setText("");
                txt_term2.setText("");
                txt_term3.setText("");
                txt_term4.setText("");
                txt_term5.setText("");
            });
            add(btn_clear);
        }
        setVisible(true);
    }

    private void changeTerms()
    {
        if ((Integer) cmb_numTerms.getSelectedItem() == 1)
        {            System.out.println(txt_fw.getText());

            txt_term2.setText("");
            txt_term2.setEnabled(false);
            lbl_term2.setEnabled(false);
            txt_term3.setText("");
            txt_term3.setEnabled(false);
            lbl_term3.setEnabled(false);
            txt_term4.setText("");
            txt_term4.setEnabled(false);
            lbl_term4.setEnabled(false);
            txt_term5.setText("");
            txt_term5.setEnabled(false);
            lbl_term5.setEnabled(false);
        }
        else if ((Integer) cmb_numTerms.getSelectedItem() == 2)
        {
            txt_term2.setEnabled(true);
            lbl_term2.setEnabled(true);
            txt_term3.setText("");
            txt_term3.setEnabled(false);
            lbl_term3.setEnabled(false);
            txt_term4.setText("");
            txt_term4.setEnabled(false);
            lbl_term4.setEnabled(false);
            txt_term5.setText("");
            txt_term5.setEnabled(false);
            lbl_term5.setEnabled(false);
        }
        else if ((Integer) cmb_numTerms.getSelectedItem() == 3)
        {
            txt_term2.setEnabled(true);
            lbl_term2.setEnabled(true);
            txt_term3.setEnabled(true);
            lbl_term3.setEnabled(true);
            txt_term4.setText("");
            txt_term4.setEnabled(false);
            lbl_term4.setEnabled(false);
            txt_term5.setText("");
            txt_term5.setEnabled(false);
            lbl_term5.setEnabled(false);
        }
        else if ((Integer) cmb_numTerms.getSelectedItem() == 4)
        {
            txt_term2.setEnabled(true);
            lbl_term2.setEnabled(true);
            txt_term3.setEnabled(true);
            lbl_term3.setEnabled(true);
            txt_term4.setEnabled(true);
            lbl_term4.setEnabled(true);
            txt_term5.setText("");
            txt_term5.setEnabled(false);
            lbl_term5.setEnabled(false);
        }
        else if ((Integer) cmb_numTerms.getSelectedItem() == 5)
        {
            txt_term2.setEnabled(true);
            lbl_term2.setEnabled(true);
            txt_term3.setEnabled(true);
            lbl_term3.setEnabled(true);
            txt_term4.setEnabled(true);
            lbl_term4.setEnabled(true);
            txt_term5.setEnabled(true);
            lbl_term5.setEnabled(true);
        }

    }
}
