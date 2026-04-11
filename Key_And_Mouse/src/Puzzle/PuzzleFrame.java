package Puzzle;

import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame
{
    public PuzzleFrame()
    {
        super("15 Puzzle");
        setLayout(new BorderLayout());
        add(new PuzzlePanel(), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
//        pack();
        setVisible(true);
    }
}
