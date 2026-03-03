package ContactList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

public class ContactFrame extends JFrame {
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
    private JButton btn_new = new JButton("New");

    private ArrayList<Person> contacts = new ArrayList<>();

    public ContactFrame() throws IOException {
        super("Contact List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 600);

        try {
            File file = new File("Rolodex.txt");
            if (!file.exists())
                file.createNewFile();
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine()) {
                String[] p = fs.nextLine().split(";");

                contacts.add(new Person(p[0], p[1], (p[2].isEmpty() || p[2].equals("📇") ? -1 : Long.parseLong(p[2])), p[3].equals("📇") ? "" : p[3]));
                {
                    System.out.println(Arrays.toString(contacts.toArray()));
                    System.out.println("#: " + contacts.getFirst().getNumber());
                    System.out.println("address: " + contacts.getFirst().getAddress());
                }
            }

            System.out.println(contacts);
            Collections.sort(contacts);
            System.out.println(contacts);
            list_contacts.setListData(contacts.toArray(new Person[0]));
            savePerson();
            //contacts
            {
                scr_contacts = new JScrollPane(list_contacts);
                scr_contacts.setBounds(10, 10, 270, 500);
                scr_contacts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                scr_contacts.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                add(scr_contacts);

                //selection change
                list_contacts.addListSelectionListener(e -> {
                    System.out.println("gsi; " + list_contacts.getSelectedIndex());
                    if (list_contacts.getSelectedIndex() == -1) {
                        btn_clearSelection.setVisible(false);
                        btn_delete.setVisible(false);
                        btn_clearSelection.setVisible(true);
                        txt_firstName.setText("");
                        txt_lastName.setText("");
                        txt_number.setText("");
                        txt_address.setText("");
                    } else {
                        //setVisibles
                        {
                            btn_saveChanges.setVisible(true);
                            btn_save.setVisible(false);
                            btn_new.setVisible(false);
                            btn_delete.setVisible(true);
                            btn_clearSelection.setEnabled(true);
                            btn_clearSelection.setVisible(true);
                            btn_delete.setVisible(true);
                        }
                        txt_firstName.setText(list_contacts.getSelectedValue().getFirstName());
                        System.out.println("------" + list_contacts.getSelectedValue().getFirstName());
                        txt_lastName.setText(list_contacts.getSelectedValue().getLastName());
                        System.out.println(list_contacts.getSelectedValue().getLastName());
                        if (list_contacts.getSelectedValue().getNumber() != -1)
                            txt_number.setText(list_contacts.getSelectedValue().getNumber() + "");
                        else txt_number.setText("");
                        System.out.println(list_contacts.getSelectedValue().getNumber());
                        if (list_contacts.getSelectedValue().getAddress() != null && !list_contacts.getSelectedValue().getAddress().isEmpty())
                            txt_address.setText(list_contacts.getSelectedValue().getAddress());
                        else txt_address.setText("");
                        System.out.println(list_contacts.getSelectedValue().getAddress());
                    }
                });
            }

            //clear selection
            {
                btn_clearSelection.setBounds(65, 520, 150, 30);
                btn_clearSelection.setEnabled(false);
                btn_clearSelection.addActionListener(e -> {
                    System.out.println(list_contacts.getSelectedIndex() + " clear");
                    list_contacts.clearSelection();
                    //setVisibles
                    {
                        btn_clearSelection.setVisible(true);
                        btn_clearSelection.setEnabled(false);
                        btn_saveChanges.setVisible(false);
                        btn_delete.setVisible(false);
                        btn_save.setVisible(true);
                        btn_new.setVisible(true);
                    }
                });
                add(btn_clearSelection);
            }

            //first name
            {
                lbl_firstName.setBounds(300, 40, 170, 40);
                lbl_firstName.setFont(new Font("Digital", Font.BOLD, 20));
                add(lbl_firstName);
                txt_firstName.setBounds(500, 40, 470, 40);
                txt_firstName.setFont(new Font("Digital", Font.ITALIC, 20));
                txt_firstName.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyChar() != '(' && e.getKeyChar() != ')' && e.getKeyChar() != '-' && e.getKeyChar() != ' ' && !Character.isLetter(e.getKeyChar()))
                            e.consume();
                    }
                });
                add(txt_firstName);
            }

            //last name
            {
                lbl_lastName.setBounds(300, 90, 170, 40);
                lbl_lastName.setFont(new Font("Digital", Font.BOLD, 20));
                add(lbl_lastName);
                txt_lastName.setBounds(500, 90, 470, 40);
                txt_lastName.setFont(new Font("Digital", Font.ITALIC, 20));
                txt_lastName.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (e.getKeyChar() != '(' && e.getKeyChar() != ')' && e.getKeyChar() != '-' && e.getKeyChar() != ' ' && !Character.isLetter(e.getKeyChar()))
                            e.consume();
                    }
                });
                add(txt_lastName);
            }

            //phone Number
            {
                lbl_number.setBounds(300, 140, 170, 40);
                lbl_number.setFont(new Font("Digital", Font.BOLD, 20));
                add(lbl_number);
                txt_number.setBounds(500, 140, 470, 40);
                txt_number.setFont(new Font("Digital", Font.ITALIC, 20));
                txt_number.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if (!Character.isDigit(e.getKeyChar()) || txt_number.getText().length() >= 13)
                            e.consume();
                    }
                });
                add(txt_number);
            }

            //address
            {
                lbl_address.setBounds(300, 190, 170, 40);
                lbl_address.setFont(new Font("Digital", Font.BOLD, 20));
                add(lbl_address);
                txt_address.setBounds(500, 190, 470, 40);
                txt_address.setFont(new Font("Digital", Font.ITALIC, 20));
                txt_address.addKeyListener(new KeyAdapter() {
                });
                add(txt_address);
            }

            //save and new
            {
                //save
                {
                    btn_save.setBounds(500, 275, 195, 50);
                    btn_save.setFont(new Font("Digital", Font.BOLD, 20));
                    btn_save.addActionListener(e -> {
                        if (txt_firstName.getText().isEmpty() || txt_lastName.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "You must enter both a first name and a last name");
                            return;
                        } else if (list_contacts.getSelectedIndex() == -1) {
                            System.out.println("save 1");
                            Person p;
                            if (txt_number.getText().isEmpty() && txt_address.getText().isEmpty()) // both empty
                                p = new Person(txt_firstName.getText(), txt_lastName.getText(), -1, "");
                            else if (txt_number.getText().isEmpty()) //number empty
                                p = new Person(txt_firstName.getText(), txt_lastName.getText(), -1, txt_address.getText());
                            else if (txt_address.getText().isEmpty()) //address empty
                                p = new Person(txt_firstName.getText(), txt_lastName.getText(), Long.parseLong(txt_number.getText()), "");
                            else p = new Person(txt_firstName.getText(), txt_lastName.getText(),
                                        Long.parseLong(txt_number.getText()), txt_address.getText());
                            contacts.add(p);
                            Collections.sort(contacts);
                            System.out.println(contacts);
                            list_contacts.setListData(contacts.toArray(new Person[0]));
                            savePerson();
                            System.out.println(p.getFirstName() + ";" + p.getLastName() + ";" + p.getNumber() + ";" + p.getAddress());
                        }
                        txt_firstName.setText("");
                        txt_lastName.setText("");
                        txt_number.setText("");
                        txt_address.setText("");
                    });
                    add(btn_save);
                }
                //new
                {
                    btn_new.setBounds(705, 275, 195, 50);
                    btn_new.setFont(new Font("Digital", Font.BOLD, 20));
                    btn_new.addActionListener(e -> {
                        txt_firstName.setText("");
                        txt_lastName.setText("");
                        txt_number.setText("");
                        txt_address.setText("");
                        //setVisibles
                        {
                            btn_saveChanges.setVisible(false);
                            btn_delete.setVisible(false);
                            btn_save.setVisible(true);
                            btn_new.setVisible(true);
                            btn_clearSelection.setEnabled(false);
                        }
                    });
                    add(btn_new);
                }
            }
            //save changes and delete
            {
                // save Changes
                {
                    btn_saveChanges.setBounds(500, 340, 400, 50);
                    btn_saveChanges.setFont(new Font("Digital", Font.BOLD, 20));
                    btn_saveChanges.setVisible(false);
                    btn_saveChanges.addActionListener(e -> {
                        if (txt_firstName.getText().isEmpty() || txt_lastName.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "You must enter both a first name and a last name");
                            return;
                        } else {
                            Person p = list_contacts.getSelectedValue();
                            contacts.remove(list_contacts.getSelectedIndex());
                            p.setFirstName(txt_firstName.getText());
                            System.out.println("first: "+ txt_firstName.getText());
                            p.setLastName(txt_lastName.getText());
                            System.out.println("last: "+ txt_lastName.getText());
                            if (!txt_number.getText().isEmpty())
                                p.setNumber(Long.parseLong(txt_number.getText()));
                            else p.setNumber(-1);
                            System.out.println("number: "+ txt_number.getText());
                            p.setAddress(txt_address.getText());
                            System.out.println("address: "+ txt_address.getText());
                            contacts.add(p);
                            Collections.sort(contacts);
                            list_contacts.setListData(contacts.toArray(new Person[0]));
                            savePerson();
                            System.out.println(p.getAddress());
                            list_contacts.clearSelection();
                            btn_clearSelection.setVisible(true);
                            //find the person in the txt File and change it
                            //reorder
                            //setVisibles
                            {
                                btn_new.setVisible(true);
                                btn_saveChanges.setVisible(false);
                                btn_delete.setVisible(false);
                                btn_save.setVisible(true);
                                btn_new.setVisible(true);
                                btn_clearSelection.setVisible(true);
                                btn_clearSelection.setEnabled(false);
                            }
                            txt_firstName.setText("");
                            txt_lastName.setText("");
                            txt_number.setText("");
                            txt_address.setText("");
                        }
                    });
                    add(btn_saveChanges);
                }

                //delete
                {
                    btn_delete.setBounds(500, 405, 400, 50);
                    btn_delete.setFont(new Font("Digital", Font.BOLD, 20));
                    btn_delete.addActionListener(e -> {
                        contacts.remove(list_contacts.getSelectedIndex());
                        btn_new.setVisible(true);
                        txt_number.setText("");
                        txt_address.setText("");
                        //setVisibles
                        {
                            btn_saveChanges.setVisible(false);
                            btn_delete.setVisible(false);
                            btn_clearSelection.setVisible(true);
                            btn_clearSelection.setEnabled(false);
                            btn_save.setVisible(true);
                            btn_new.setVisible(true);
                        }
                        //find this contact in the txt File and remove it
                        list_contacts.setListData(contacts.toArray(new Person[0]));
                        savePerson();
                    });
                    //setVisibles
                    {
                        btn_clearSelection.setVisible(true);
                        btn_saveChanges.setVisible(false);
                        btn_delete.setVisible(false);
                    }
                    add(btn_delete);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        setVisible(true);
    }

    private void savePerson() {
        try {
            System.out.println("saving");
            FileWriter fw = new FileWriter("Rolodex.txt", false);
            PrintWriter pw = new PrintWriter(fw);
            int i = 0;
            for (Person contact : contacts) {
                pw.println(contact.getFirstName() + ";" + contact.getLastName() + ";" +
                        (contact.getNumber() == -1 ? "📇" : contact.getNumber()) + ";" +
                        (contact.getAddress().isEmpty() ? "📇" : contact.getAddress()));
                System.out.print(" i" + i++);
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
