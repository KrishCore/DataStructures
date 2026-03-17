package TextEditor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

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

    public TextEditorFrame()
    {
        super("File Editor");
        setSize(800,500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(mb_mainMenu);
        mb_mainMenu.add(m_file);
        mb_mainMenu.add(m_edit);

        //file - needs work
        {
            //create
            {
                m_file.add(mi_create);
                mi_create.addActionListener(e -> {

                });
            }

            //open
            {
                m_file.add(mi_open);
                mi_open.addActionListener(e -> {
                    //select file
                    {
                        JFileChooser openPicker = new JFileChooser("C:\\");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        openPicker.setFileFilter(fnef);
                        int result = openPicker.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = openPicker.getSelectedFile();
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
                        JFileChooser savePicker = new JFileChooser("C:\\");
                        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Text Filter", "txt", "text");
                        savePicker.setFileFilter(fnef);
                        int result = savePicker.showOpenDialog(this);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = savePicker.getSelectedFile();
                        }
                    }
                });
                mi_saveAs.setEnabled(false);
            }

            //save
            {
                m_file.add(mi_save);
                mi_save.addActionListener(e -> {

                });
                mi_save.setEnabled(false);
            }

            //exit
            {
                m_file.add(mi_exit);
                mi_exit.addActionListener(e -> {
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
