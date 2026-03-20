package TextEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TextEditorFrame extends JFrame
{
    private JMenuBar mb_mainMenu = new JMenuBar();
    private JMenu m_file = new JMenu("File");
    private JMenuItem mi_create = new JMenuItem("Create");
    private JMenuItem mi_open = new JMenuItem("Open");
    private JMenuItem mi_saveAs = new JMenuItem("Save As");
    private JMenuItem mi_save = new JMenuItem("Save");
    private JMenuItem mi_close = new JMenuItem("Close");
    private JMenuItem mi_exit = new JMenuItem("Exit");
    private JMenuItem mi_font = new JMenuItem("Font");
    private JMenuItem mi_replace = new JMenuItem("Replace");
    private JMenuItem mi_wordCount = new JMenuItem("Word Count");

    private JPanel jp_red = new JPanel();
    private JPanel jp_green = new JPanel();
    private JPanel jp_blue = new JPanel();

    private JMenu m_edit = new JMenu("Edit");

    private JTabbedPane tabs =  new JTabbedPane();
    private ArrayList<String> arrN = new ArrayList<>();
    private ArrayList<JTextArea> arrT = new ArrayList<>();

    public TextEditorFrame()
    {
        super("File Editor");
        setSize(800,500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(mb_mainMenu);
        mb_mainMenu.add(m_file);
        mb_mainMenu.add(m_edit);

        // test stuff
        {
            jp_red.setBackground(Color.RED);
            jp_green.setBackground(Color.GREEN);
            jp_blue.setBackground(Color.BLUE);

            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    tabs.setBounds(0,0,getWidth()-15,getHeight()-60);
                }
            });
            if (arrN.isEmpty())
            {
                mi_saveAs.setEnabled(false);
                mi_save.setEnabled(false);
                mi_font.setEnabled(false);
                mi_replace.setEnabled(false);
                mi_wordCount.setEnabled(false);
            }
            else
            {
                mi_saveAs.setEnabled(true);
                mi_save.setEnabled(true);
                mi_font.setEnabled(true);
                mi_replace.setEnabled(true);
                mi_wordCount.setEnabled(true);
            }
            tabs.setBounds(0,0,getWidth()-15,getHeight()-60);
            add(tabs);
        }
        //file - needs work
        {
            //create
            {
                m_file.add(mi_create);
                mi_create.addActionListener(e -> {
                    JTextArea text = new JTextArea();
                    JScrollPane jsp = new JScrollPane(text);
                    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    text.setLineWrap(true);
                    File file = new File("src\\TextEditor\\Saves\\Untitled.txt");
                    if (tabs.indexOfTab("Untitled") != -1 || file.exists())
                    {
                        boolean isFound = false;
                        int c = 1;
                        while (!isFound)
                        {
                            file = new File("src\\TextEditor\\Saves\\Untitled" + c + ".txt");
                            if (file.exists() || tabs.indexOfTab("Untitled" + c) != -1)
                                c++;
                            else isFound = true;
                        }
                        tabs.add("Untitled" + c, jsp);
                        arrN.add("Untitled" + c);
                        arrT.add(text);
                        tabs.setSelectedIndex(tabs.indexOfTab(arrN.getLast()));
                    }
                    else {
                        tabs.add("Untitled", jsp);
                        arrN.add("Untitled");
                        arrT.add(text);
                        tabs.setSelectedIndex(arrN.size()-1);
                    }
                    try {
                        file.createNewFile();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //set enables
                    {
                        mi_saveAs.setEnabled(true);
                        mi_save.setEnabled(true);
                        mi_font.setEnabled(true);
                        mi_replace.setEnabled(true);
                        mi_wordCount.setEnabled(true);
                    }
                });
            }

            //open
            {
                m_file.add(mi_open);
                mi_open.addActionListener(e -> {
                    //select file
                    {
                        JFileChooser openPicker = new JFileChooser("C:\\Users\\k1338728\\OneDrive - Katy Independent School District\\GitHub\\DataStructures\\DS9\\src\\TextEditor\\Saves");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        openPicker.setFileFilter(fnef);
                        int result = openPicker.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION && !arrN.contains(openPicker.getSelectedFile().getName())) {
                            File selectedFile = openPicker.getSelectedFile();
                            String fText = "";
                            try {
                                Scanner fs = new Scanner(selectedFile);
                                while (fs.hasNextLine())
                                    fText += fs.nextLine() + "\n";
                                fs.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }


                            System.out.println(fText);
                            JTextArea text = new JTextArea(fText);
                            JScrollPane jsp = new JScrollPane(text);
                            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                            text.setLineWrap(true);
                            tabs.add(selectedFile.getName(), jsp);
                            arrN.add(selectedFile.getName());
                            arrT.add(text);

                            //set enables
                            {
                                mi_saveAs.setEnabled(true);
                                mi_save.setEnabled(true);
                                mi_font.setEnabled(true);
                                mi_replace.setEnabled(true);
                                mi_wordCount.setEnabled(true);
                            }
                        }
                    }


                });
            }

            //save as
            {
                m_file.add(mi_saveAs);
                mi_saveAs.addActionListener(e -> {
                    //create file
                    {
                        JFileChooser savePicker = new JFileChooser("C:\\Users\\k1338728\\OneDrive - Katy Independent School District\\GitHub\\DataStructures\\DS9\\src\\TextEditor\\Saves");
                        savePicker.setDialogTitle("Save As");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        savePicker.setFileFilter(fnef);
                        int result = savePicker.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = savePicker.getSelectedFile();
                            try {
                                FileWriter fw = new FileWriter(selectedFile);
                                PrintWriter pw = new PrintWriter(fw);
                                String fileName = arrN.get(tabs.getSelectedIndex());
                                pw.println(arrT.get(tabs.getSelectedIndex()).getText());
//                                arrT.get(tabs.getSelectedIndex()).setText("");

                                fw.close();
                                pw.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        arrN.add(tabs.getTitleAt(tabs.getSelectedIndex()));
//                        arrT.add(arrT.get(tabs.));
                        }
                    }
                });
                mi_saveAs.setEnabled(false);
            }

            //save
            {
                m_file.add(mi_save);
                mi_save.addActionListener(e -> {
                    String name = tabs.getTitleAt(tabs.getSelectedIndex());
                    Scanner fs = null;
                    try {
                        fs = new Scanner(new File("C:\\Users\\k1338728\\OneDrive - Katy Independent School District\\GitHub\\DataStructures\\DS9\\src\\TextEditor\\Saves\\" + name));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    String fil = "";
                    while (fs.hasNextLine())
                        fil += fs.nextLine() + "\n";
                    fs.close();
                    System.out.println("checked file");
//                    if (!fil.equals(arrT.get(tabs.getSelectedIndex()).getText()))
//                    {
//                        System.out.println("it not equal - save");
//
//                        try {
//                            FileWriter fw = fw = new FileWriter(name);
//                            PrintWriter pw = new PrintWriter(fw);
//                            pw.println(arrT.get(tabs.getSelectedIndex()).getText());
//                            fw.close();
//                            pw.close();
//                        } catch (Exception ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                    fs.close();

                    System.out.println(fil);
                    if (fil.isEmpty() || fil.equals(name + "\n") || fil.equals(name))
                    {
                        System.out.println("empty");
                        JFileChooser savePicker = new JFileChooser("C:\\Users\\k1338728\\OneDrive - Katy Independent School District\\GitHub\\DataStructures\\DS9\\src\\TextEditor\\Saves");
                        savePicker.setDialogTitle("Save As");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        savePicker.setFileFilter(fnef);
                        int result = savePicker.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = savePicker.getSelectedFile();
                            try {
                                FileWriter fw = new FileWriter(selectedFile);
                                PrintWriter pw = new PrintWriter(fw);
                                String fileName = arrN.get(tabs.getSelectedIndex());
                                pw.println(arrT.get(tabs.getSelectedIndex()).getText());
//                                arrT.get(tabs.getSelectedIndex()).setText("");

                                fw.close();
                                pw.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            arrN.add(tabs.getTitleAt(tabs.getSelectedIndex()));
//                        arrT.add(arrT.get(tabs.));
                        }
                    }
                    else // needs work
                    {
                        System.out.println("not empty");
                        try {
                            FileWriter fw = new FileWriter(name);
                            PrintWriter pw = new PrintWriter(fw);
                            String fileName = arrN.get(tabs.getSelectedIndex());
                            pw.println(arrT.get(tabs.getSelectedIndex()).getText());
//                                arrT.get(tabs.getSelectedIndex()).setText("");

                            fw.close();
                            pw.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                });
                mi_save.setEnabled(false);
            }

            //close
            {
                m_file.add(mi_close);
                mi_close.addActionListener(e -> {
                    int tab = tabs.getSelectedIndex();
                    String name = tabs.getTitleAt(tab);
                    String fil = "";
                    Scanner fs = new Scanner(name);
                    while (fs.hasNextLine())
                        fil += fs.nextLine() + "\n";
                    if (!fil.equals(arrT.get(tabs.getSelectedIndex()).getText()))
                    {
                        System.out.println("it not equal - close");
                        int resutl = JOptionPane.showConfirmDialog(this, "Are you sure you want to close " + name + "?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (resutl == 0)
                            tabs.remove(tabs.getSelectedIndex());
                    }
                    fs.close();
                });
            }

            //exit
            {
                m_file.add(mi_exit);
                mi_exit.addActionListener(e -> {

                    for (int i = arrN.size()-1; i >= 0; i--) {
                        String name = tabs.getTitleAt(i);
                        String fil = "";
                        Scanner fs = new Scanner(name);
                        while (fs.hasNextLine())
                            fil += fs.nextLine() + "\n";
                        if (!fil.equals(arrT.get(tabs.getSelectedIndex()).getText()))
                        {
                            System.out.println("it not equal");
                            int resutl = JOptionPane.showConfirmDialog(this, "Are you sure you want to close " + name + "?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                            if (resutl == 0)
                                tabs.remove(i);
                        }
                    }
                    dispose();
                });
            }
        }

        //edit - needs work
        {
            //Font
            {
                m_edit.add(mi_font);
                mi_font.addActionListener(e -> {

                });
                mi_font.setEnabled(false);
            }

            //replace
            {
                m_edit.add(mi_replace);
                mi_replace.addActionListener(e -> {

                });
                mi_replace.setEnabled(false);
            }

            //word count
            {
                m_edit.add(mi_wordCount);
                mi_wordCount.addActionListener(e -> {

                });
                mi_wordCount.setEnabled(false);
            }
        }

        setVisible(true);
    }
}
