package FileEditor;

import javax.swing.*;

public class TextEditorFrame extends JFrame
{
    private JMenuBar mb_mainMenu = new JMenuBar();
    private JMenu m_file = new JMenu("File");
    private JMenuItem mi_exit = new JMenuItem("Exit");

    private JMenu m_screenSize = new JMenu("Screen Size");
    private JMenuItem mi_200 = new JMenuItem("200x200");
    private JMenuItem mi_300 = new JMenuItem("300x300");
    private JMenuItem mi_400 = new JMenuItem("400x400");
    private JMenu m_colors = new JMenu("Background Color");
    private JMenuItem mi_red = new JMenuItem("Red");
    private JMenuItem mi_green = new JMenuItem("Green");
    private JMenuItem mi_blue = new JMenuItem("Blue");

    private JMenu m_screen = new JMenu("Screen");

    public TextEditorFrame()
    {
        super("File Editor");
        setSize(200,200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(mb_mainMenu);
        mb_mainMenu.add(m_file);
        mb_mainMenu.add(m_screen);

        m_file.add(mi_exit);
        mi_exit.addActionListener(e -> {
            dispose();
        });

        m_screen.add(m_screenSize);
        m_screen.add(m_colors);

        m_screenSize.add(mi_200);
        m_screenSize.add(mi_300);
        m_screenSize.add(mi_400);

        m_colors.add(mi_red);
        m_colors.add(mi_green);
        m_colors.add(mi_blue);

        mi_200.addActionListener(e -> {
            if (e.getActionCommand().equals("200x200"))
                setSize(200,200);
            else if (e.getActionCommand().equals("300x300"))
                setSize(300,300);
            else if (e.getActionCommand().equals("400x400"))
                setSize(400,400);
        });
        mi_300.addActionListener(e -> {
            if (e.getActionCommand().equals("200x200"))
                setSize(200,200);
            else if (e.getActionCommand().equals("300x300"))
                setSize(300,300);
            else if (e.getActionCommand().equals("400x400"))
                setSize(400,400);
        });
        mi_400.addActionListener(e -> {
            if (e.getActionCommand().equals("200x200"))
                setSize(200,200);
            else if (e.getActionCommand().equals("300x300"))
                setSize(300,300);
            else if (e.getActionCommand().equals("400x400"))
                setSize(400,400);
        });
        setVisible(true);
    }
}
