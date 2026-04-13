package Puzzle;

import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame
{
    public PuzzleFrame()
    {
        super("15 Puzzle");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);

        JPanel panel = new PuzzlePanel();
        add(panel, BorderLayout.CENTER);
//        pack();
        setVisible(true);
    }
}
