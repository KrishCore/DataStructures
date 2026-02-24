package ContactList;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class ContactFrame extends JFrame
{
    private JLabel lbl_contacts = new JLabel("Contacts");

    private JList<Person> list_contacts = new JList<>();
    private JScrollPane scr_contacts;

    private JLabel lbl_firstName = new JLabel("First Name:");
    private JTextField txt_firstName = new JTextField();


    private JLabel lbl_lastName = new JLabel("Last Name:");
    private JTextField txt_lastName = new JTextField();

    private JButton btn_save = new JButton("Save");
    private JButton btn_delete = new JButton("Delete");
    private JButton btn_clearSelection = new JButton("Clear");

    private ArrayList<Person> contacts = new ArrayList<>();

    public ContactFrame()
    {
        super("Contact List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 600);

        lbl_contacts.setBounds(20,20,100,20);
        add(lbl_contacts);

        //contacts
        {
            scr_contacts = new JScrollPane(list_contacts);
            scr_contacts.setBounds(20,40,100,200);
            scr_contacts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scr_contacts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            add(scr_contacts);

            list_contacts.addListSelectionListener(e -> {
                System.out.println(list_contacts.getSelectedIndex());
                if (list_contacts.getSelectedIndex() == -1) {
                    btn_clearSelection.setEnabled(false);
                    btn_delete.setEnabled(false);
                    txt_firstName.setText("");
                    txt_lastName.setText("");
                } else {
                    btn_clearSelection.setEnabled(true);
                    btn_delete.setEnabled(true);
                    txt_firstName.setText(list_contacts.getSelectedValue().getFirstName());
                    txt_lastName.setText(list_contacts.getSelectedValue().getLastName());
                    ;
                }
            });
        }

        //clear
        {
            btn_clearSelection.setBounds(20, 250, 150, 30);
            btn_clearSelection.addActionListener(e -> {
                list_contacts.clearSelection();
                System.out.println(list_contacts.getSelectedIndex());
            });
            btn_clearSelection.setEnabled(false);
            add(btn_clearSelection);
        }
        //first name
        {
            lbl_firstName.setBounds(170, 20, 100, 20);
            add(lbl_firstName);
            txt_firstName.setBounds(170, 40, 100, 20);
            txt_firstName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isLetter(e.getKeyChar()))
                        e.consume();
                }
            });
            add(txt_firstName);
        }

        //last name
        {
            lbl_lastName.setBounds(170, 60, 100, 20);
            add(lbl_lastName);
            txt_lastName.setBounds(170, 80, 100, 20);
            txt_lastName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isLetter(e.getKeyChar()))
                        e.consume();
                }
            });
            add(txt_lastName);
        }

        //save
        {
            btn_save.setBounds(170, 120, 80, 30);
            btn_save.addActionListener(e -> {
                if (txt_firstName.getText().isEmpty() || txt_lastName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter both a first name and a last name");
                    return;
                } else if (list_contacts.getSelectedIndex() == -1) {
                    Person p = new Person(txt_firstName.getText(), txt_lastName.getText());
                    contacts.add(p);
                    list_contacts.setListData(contacts.toArray(new Person[0]));
                } else {
                    Person p = list_contacts.getSelectedValue();
                    p.setFirstName(txt_firstName.getText());
                    p.setLastName(txt_lastName.getText());
                    list_contacts.setListData(contacts.toArray(new Person[0]));
                }
                txt_firstName.setText("");
                txt_lastName.setText("");
            });
            add(btn_save);
        }

        //delete
        {
            btn_delete.setBounds(270, 120, 80, 30);
            btn_delete.addActionListener(e -> {

            });
            btn_delete.setEnabled(false);
            add(btn_delete);
        }

        setVisible(true);
    }
}
