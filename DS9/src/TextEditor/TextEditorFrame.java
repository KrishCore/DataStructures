package TextEditor;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
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

    private JMenu m_edit = new JMenu("Edit");
    private JMenuItem mi_font = new JMenuItem("Font");
    private JMenuItem mi_replace = new JMenuItem("Replace");
    private JMenuItem mi_wordCount = new JMenuItem("Word Count");

    private JTabbedPane tabs =  new JTabbedPane();
    private ArrayList<String> arrN = new ArrayList<>();
    private ArrayList<JTextArea> arrT = new ArrayList<>();
    private ArrayList<String> arrOT = new ArrayList<>();
    private ArrayList<File> arrF = new ArrayList<>();
    private ArrayList<Boolean> arrS = new ArrayList<>();

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
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    tabs.setBounds(0,0,getWidth()-15,getHeight()-60);
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
            });
        }

        //file
        {
            //create
            {
                m_file.add(mi_create);
                mi_create.addActionListener(e -> {
                    JTextArea text = new JTextArea();
                    text.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                        public void insertUpdate(javax.swing.event.DocumentEvent e) { markUnsaved(text); }
                        public void removeUpdate(javax.swing.event.DocumentEvent e) { markUnsaved(text); }
                        public void changedUpdate(javax.swing.event.DocumentEvent e) { markUnsaved(text); }
                    });
                    JScrollPane jsp = new JScrollPane(text);
                    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    text.setLineWrap(true);
                    text.setWrapStyleWord(true);
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
                        arrOT.add("");
                        arrF.add(null);
                        arrS.add(false);
                        tabs.setSelectedIndex(tabs.indexOfTab(arrN.getLast()));
                    }
                    else {
                        tabs.add("Untitled", jsp);
                        arrN.add("Untitled");
                        arrT.add(text);
                        arrOT.add("");
                        arrF.add(null);
                        arrS.add(false);
                        tabs.setSelectedIndex(arrN.size()-1);
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
                        System.out.println("Open command clicked");
                        JFileChooser openPicker = new JFileChooser("src\\TextEditor\\Saves");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        openPicker.setFileFilter(fnef);
                        int result = openPicker.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION && !arrN.contains(openPicker.getSelectedFile().getName())) {
                            File selectedFile = openPicker.getSelectedFile();
                            String fText = "";
                            Font font = new Font(Font.DIALOG, Font.PLAIN, 12);
                            try {
                                Scanner fs = new Scanner(selectedFile);
                                String[] parts = fs.nextLine().split(",");
                                font = new Font(parts[0], Font.PLAIN, Integer.parseInt(parts[1]));
                                while (fs.hasNextLine())
                                    fText += fs.nextLine() + "\n";
                                fs.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            fText = fText.trim().isEmpty() ? fText.trim() : fText.trim() + "\n";

                            System.out.println(fText);
                            JTextArea text = new JTextArea(fText);
                            text.setFont(font);
                            text.getDocument().addDocumentListener(new DocumentListener() {
                                public void insertUpdate(javax.swing.event.DocumentEvent e) { markUnsaved(text); }
                                public void removeUpdate(javax.swing.event.DocumentEvent e) { markUnsaved(text); }
                                public void changedUpdate(javax.swing.event.DocumentEvent e) { markUnsaved(text); }
                            });
                            JScrollPane jsp = new JScrollPane(text);
                            jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                            text.setLineWrap(true);
                            text.setWrapStyleWord(true);
                            tabs.add(selectedFile.getName(), jsp);
                            arrN.add(selectedFile.getName());
                            arrT.add(text);
                            arrOT.add(fText);
                            arrF.add(selectedFile);
                            tabs.setSelectedIndex(arrN.size()-1);
                            if (text.getText().equals(fText))
                                arrS.add(true);
                            else arrS.add(false); // need to add a constant listener to see when text != fText so saved can be updated

                            //set enables
                            {
                                mi_saveAs.setEnabled(true);
                                mi_save.setEnabled(true);
                                mi_font.setEnabled(true);
                                mi_replace.setEnabled(true);
                                mi_wordCount.setEnabled(true);
                            }
                        }
                        else if (result == JFileChooser.APPROVE_OPTION && arrN.contains(openPicker.getSelectedFile().getName()))
                            tabs.setSelectedIndex(arrN.indexOf(openPicker.getSelectedFile().getName()));
                    }


                });
            }

            //save as
            {
                m_file.add(mi_saveAs);
                mi_saveAs.addActionListener(e -> {
                    //create file
                    {
                        JFileChooser savePicker = new JFileChooser("src\\TextEditor\\Saves");
                        savePicker.setDialogTitle("Save As");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        savePicker.setFileFilter(fnef);
                        int result = savePicker.showSaveDialog(this);
                        int i = tabs.getSelectedIndex();
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = savePicker.getSelectedFile();
                            if (!selectedFile.getName().endsWith(".txt"))
                                selectedFile = new File(selectedFile.getAbsolutePath()+".txt");
                            if (!selectedFile.exists()) {
                                try {
                                    selectedFile.createNewFile();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            try {
                                FileWriter fw = new FileWriter(selectedFile);
                                PrintWriter pw = new PrintWriter(fw);
                                Font f = arrT.get(i).getFont();
                                pw.println(f.getName() + "," + f.getSize());
                                pw.print(arrT.get(tabs.getSelectedIndex()).getText());
                                fw.close();
                                pw.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            arrS.set(i, true);
                            File oldFile = arrF.get(i);
                            if (oldFile != null && oldFile.exists())
                                oldFile.delete();
                            arrF.set(i, selectedFile);
                            arrN.set(i, selectedFile.getName());
                            arrOT.set(i, arrT.get(i).getText());
                            tabs.setTitleAt(i, selectedFile.getName());
                        }
                    }
                });
                mi_saveAs.setEnabled(false);
            }

            //save
            {
                m_file.add(mi_save);
                mi_save.addActionListener(e -> {
                    String name = arrN.get(tabs.getSelectedIndex());
                    System.out.println(Objects.equals(arrN.get(tabs.getSelectedIndex()), name) + "  name = arn.get");

                    int i = tabs.getSelectedIndex();
                    if (arrF.get(i) == null) {
                        // first time or unsaved (Save As)
                        JFileChooser savePicker = new JFileChooser("src\\TextEditor\\Saves");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        savePicker.setFileFilter(fnef);
                        int result = savePicker.showSaveDialog(this);

                        System.out.println(result + "  result");
                        if (result == JFileChooser.APPROVE_OPTION) {
                            System.out.println(savePicker.getSelectedFile() + " savePicker.getSelectedFile()");
                            File file = savePicker.getSelectedFile();
                            if (!file.getName().endsWith(".txt"))
                                file = new File(file.getAbsolutePath() + ".txt");
                            try {
                                PrintWriter pw = new PrintWriter(new FileWriter(file));
                                Font f = arrT.get(i).getFont();
                                pw.println(f.getName() + "," + f.getSize());
                                pw.print(arrT.get(i).getText());
                                pw.close();
                                arrOT.set(i, arrT.get(i).getText());
                                arrS.set(i, true);
                                arrF.set(i, file);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            arrN.set(i, arrF.get(i).getName());
                            tabs.setTitleAt(i, arrF.get(i).getName());
                        }
                    }
                    else {
                        //normal save
                        try {
                            System.out.println("normal Save");
                            File file = arrF.get(i);
                            PrintWriter pw = new PrintWriter(new FileWriter(file));
                            Font font = arrT.get(i).getFont();
                            pw.println(font.getName() + "," + font.getSize());
                            pw.print(arrT.get(i).getText());
                            pw.close();
                            arrOT.set(i, arrT.get(i).getText());
                            arrS.set(i, true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    arrS.set(i, true);
                    tabs.setTitleAt(i, arrN.get(i));
                    arrOT.set(i, arrT.get(i).getText());
                });
                mi_save.setEnabled(false);
            }

            //close
            {
                m_file.add(mi_close);
                mi_close.addActionListener(e -> {
                    int tab = tabs.getSelectedIndex();
                    if (!arrS.get(tab))
                    {
                        System.out.println("it not equal - close");
                        int resutl = JOptionPane.showConfirmDialog(this, "Unsaved data will be lost. Are you sure you want to close this file?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (resutl == 1)
                            return;
                    }
                    tabs.remove(tab);
                    arrN.remove(tab);
                    arrT.remove(tab);
                    arrF.remove(tab);
                    arrS.remove(tab);
                    if (arrN.isEmpty())
                    {
                        mi_saveAs.setEnabled(false);
                        mi_save.setEnabled(false);
                        mi_font.setEnabled(false);
                        mi_replace.setEnabled(false);
                        mi_wordCount.setEnabled(false);
                        mi_close.setEnabled(!arrN.isEmpty());
                    }
                });
            }

            //exit
            {
                m_file.add(mi_exit);
                mi_exit.addActionListener(e -> {
                    boolean hasUnsaved = false;
                    for (boolean b : arrS)
                        if (!b)
                            hasUnsaved = true;
                    if (hasUnsaved)
                    {
                        int resutl = JOptionPane.showConfirmDialog(this, "There are files that have not been saved, unsaved data will be lost. Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (resutl != 0)
                            return;
                    }
                    dispose();
                    System.exit(0);
                });
            }
        }

        //edit
        {
            //Font
            {
                m_edit.add(mi_font);
                mi_font.addActionListener(e -> {
                    int i = tabs.getSelectedIndex();
                    JTextArea text = arrT.get(i);

                    JFrame fraem = new JFrame("Font");
                    fraem.setSize(300,300);
                    fraem.setLayout(null);

                    String[] fonts = {Font.SERIF, "Sans Serif", Font.MONOSPACED, Font.DIALOG, "Dialog Input"};
                    JComboBox<String> fontBox = new JComboBox<>(fonts);
                    System.out.println("text.getFont().getName():   " + text.getFont().getName());
                    fontBox.setSelectedItem(text.getFont().getName());;
                    fontBox.setBounds(20,20,200,25);
                    fraem.add(fontBox);

                    JTextField sizeF = new JTextField();
                    sizeF.setText(text.getFont().getSize()+"");
                    sizeF.setBounds(20,60,200,25);
                    sizeF.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            if (!Character.isDigit(e.getKeyChar()))
                                e.consume();
                        }
                    });
                    fraem.add(sizeF);

                    JButton save = new JButton("Save");
                    save.setBounds(20,110,80,25);
                    save.addActionListener(es -> {
                        try {
                            String fontName = (String) fontBox.getSelectedItem();
                            int size = sizeF.getText().isEmpty()? 12 : Integer.parseInt(sizeF.getText());
                            text.setFont(new Font(fontName, Font.PLAIN, size));
                            markUnsaved(text);
                            fraem.dispose();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                    fraem.add(save);

                    JButton cancel = new JButton("Cancel");
                    cancel.setBounds(140,110,80,25);
                    cancel.addActionListener(ec -> fraem.dispose());
                    fraem.add(cancel);

                    fraem.setVisible(true);
                });
                mi_font.setEnabled(false);
            }

            //replace
            {
                m_edit.add(mi_replace);
                mi_replace.addActionListener(e -> {
                    int i = tabs.getSelectedIndex();
                    String find = JOptionPane.showInputDialog(this, "Find");
                    if (find == null)
                        return;

                    String replace = JOptionPane.showInputDialog(this, "Replace");
                    if (replace == null)
                        return;
                    String text = arrT.get(i).getText();
                    text = text.replace(find, replace);
                    arrT.get(i).setText(text);
                    markUnsaved(arrT.get(i));
                });
                mi_replace.setEnabled(false);
            }

            //word count
            {
                m_edit.add(mi_wordCount);
                mi_wordCount.addActionListener(e -> {
                    int i = tabs.getSelectedIndex();
                    String text = arrT.get(i).getText().trim();

                    if (text.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Word Count: 0");
                        return;
                    }
                    String[] words = text.split("\\s+");
                    JOptionPane.showMessageDialog(this, "Word Count " + words.length);
                });
                mi_wordCount.setEnabled(false);
            }
        }
        setVisible(true);
    }

    private void markUnsaved(JTextArea text) {
        int i = arrT.indexOf(text);
        if (i != -1) {
            String cur = text.getText();
            String og = arrOT.get(i);
            String name = arrN.get(i);

            if (cur.equals(og)) {
                arrS.set(i, true);
                if (!tabs.getTitleAt(i).equals(name))
                    tabs.setTitleAt(i, name);
            } else {
                arrS.set(i, false);
                String newTitle = name + "*";
                if (!tabs.getTitleAt(i).equals(newTitle))
                    tabs.setTitleAt(i, newTitle);
            }
        }
    }
}
