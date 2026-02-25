package ContactList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;


public class ContactFrame extends JFrame
{
    private JList<Person> list_contacts = new JList<>();
    private JScrollPane scr_contacts;

    private JLabel lbl_firstName = new JLabel("First Name:");
    private JTextField txt_firstName = new JTextField();

    private JLabel lbl_lastName = new JLabel("Last Name:");
    private JTextField txt_lastName = new JTextField();

    private JLabel lbl_number = new JLabel("Phone: Number:");
    private JTextField txt_number = new JTextField();

    private JLabel lbl_address = new JLabel("Address:");
    private JTextField txt_address = new JTextField();

    private JButton btn_save = new JButton("Save");
    private JButton btn_saveChanges = new JButton("Save Changes");
    private JButton btn_delete = new JButton("Delete Contact");
    private JButton btn_clearSelection = new JButton("Clear");

    private ArrayList<Person> contacts = new ArrayList<>();

    public ContactFrame() throws IOException
    {
        super("Contact List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 600);

        File file = new File("Rolodex.txt");
        FileWriter fw = new FileWriter(file,true);
        PrintWriter pw = new PrintWriter(fw);
        if (!file.exists())
            file.createNewFile();

        //contacts
        {
            scr_contacts = new JScrollPane(list_contacts);
            scr_contacts.setBounds(10,10,270,200);
            scr_contacts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scr_contacts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            add(scr_contacts);

            //selection change
            list_contacts.addListSelectionListener(e -> {
                System.out.println(list_contacts.getSelectedIndex());
                if (list_contacts.getSelectedIndex() == -1) {
                    btn_clearSelection.setEnabled(false);
                    btn_delete.setEnabled(false);
                    txt_firstName.setText("");
                    txt_lastName.setText("");
                } else {
                    add(btn_saveChanges);
                    remove(btn_save);
                    add(btn_delete);
                    btn_clearSelection.setEnabled(true);
                    btn_delete.setEnabled(true);
                    txt_firstName.setText(list_contacts.getSelectedValue().getFirstName());
                    txt_lastName.setText(list_contacts.getSelectedValue().getLastName());
                    ;
                }
            });
        }

        //clear selection
        {
            btn_clearSelection.setBounds(20, 250, 150, 30);
            btn_clearSelection.addActionListener(e -> {
                list_contacts.clearSelection();
                System.out.println(list_contacts.getSelectedIndex() + " clear");
            });
            btn_clearSelection.setEnabled(false);
            add(btn_clearSelection);
        }
        //first name
        {
            lbl_firstName.setBounds(300, 40, 170, 35);
            System.out.println(lbl_firstName.getFont().getSize());
            lbl_firstName.setFont(new Font("Digital", Font.BOLD, 20));
            add(lbl_firstName);
            txt_firstName.setBounds(500, 45, 470, 35);
            txt_firstName.setFont(new Font("Digital", Font.BOLD, 20));
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
            lbl_lastName.setBounds(300, 95, 170, 40);
            lbl_lastName.setFont(new Font("Digital", Font.BOLD, 20));
            add(lbl_lastName);
            txt_lastName.setBounds(500, 100, 470, 35);
            txt_lastName.setFont(new Font("Digital", Font.BOLD, 20));
            txt_lastName.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isLetter(e.getKeyChar()))
                        e.consume();
                }
            });
            add(txt_lastName);
        }

        //phone Number
        {
            lbl_number.setBounds(300, 145, 170, 40);
            lbl_number.setFont(new Font("Digital", Font.BOLD, 20));
            add(lbl_number);
            txt_number.setBounds(500, 150, 470, 35);
            txt_number.setFont(new Font("Digital", Font.BOLD, 20));
            txt_number.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar()))
                        e.consume();
                }
            });
            add(txt_number);
        }

        //address
        {
            lbl_address.setBounds(300, 195, 170, 40);
            lbl_address.setFont(new Font("Digital", Font.BOLD, 20));
            add(lbl_address);
            txt_address.setBounds(500, 200, 470, 35);
            txt_address.setFont(new Font("Digital", Font.BOLD, 20));
            txt_address.addKeyListener(new KeyAdapter() {});
            add(txt_address);
        }

        //save
        {
            btn_save.setBounds(570, 280, 100, 40);
            btn_save.addActionListener(e -> {
                if (txt_firstName.getText().isEmpty() || txt_lastName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "You must enter both a first name and a last name");
                    return;
                } else if (list_contacts.getSelectedIndex() == -1) {
                    Person p = new Person(txt_firstName.getText(), txt_lastName.getText(), Integer.parseInt(txt_number.getText()), txt_address.getText());
                    contacts.add(p);
                    list_contacts.setListData(contacts.toArray(new Person[0]));
                } else {
                    Person p = list_contacts.getSelectedValue();
                    p.setFirstName(txt_firstName.getText());
                    p.setLastName(txt_lastName.getText());
                    p.s
                    list_contacts.setListData(contacts.toArray(new Person[0]));
                }
                txt_firstName.setText("");
                txt_lastName.setText("");
            });
            add(btn_save);
        }
        // save Changes
        {
//            btn_saveChanges.setBounds();
        }

        //delete
        {
            btn_delete.setBounds(270, 120, 80, 30);
            btn_delete.addActionListener(e -> {
                contacts.remove(list_contacts.getSelectedIndex());
                list_contacts.setListData(contacts.toArray(new Person[0]));
            });
            btn_delete.setEnabled(false);
//            add(btn_delete);
        }

        setVisible(true);
    }
}
