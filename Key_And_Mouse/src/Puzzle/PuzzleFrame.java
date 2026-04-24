package Puzzle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PuzzleFrame extends JFrame
{
    public PuzzleFrame() throws IOException {
        super("15 Puzzle");
        setLayout(null);//ew BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setBackground(Color.WHITE);

        PuzzlePanel panel = new PuzzlePanel();
        add(panel);//, BorderLayout.CENTER);
        panel.setBounds(0, 0, 700, 700);
        //intro and rules - used AI for summary
        {
            //intro
            JOptionPane.showMessageDialog(this, "Welcome to 15 Puzzle." +
                    "\nThe 15 Puzzle Game is a classic sliding puzzle that challenges players to arrange tiles in the correct order." +
                    "\nThe game consists of a 4×4 grid with 15 numbered tiles and one empty space." +
                    "\nPlayers move tiles into the empty space to reorder them into ascending order or to complete a full image." +
                    "\nThis program recreates the 15 puzzle in a digital format." +
                    "\nIt allows users to interact with the puzzle by clicking tiles, track their number of moves, switch between number and image modes, and start a new game once the puzzle is solved." +
                    "\nThe goal is to solve the puzzle in the fewest moves possible.", "Intro", JOptionPane.PLAIN_MESSAGE);
            //rules
            JOptionPane.showMessageDialog(this, "The 15 Puzzle is played on a 4×4 grid containing 15 tiles and one empty space." +
                    "\nAt the start of the game, the tiles are arranged randomly." +
                    "\nThe player moves tiles by clicking on them, but a tile will only move if it is directly next to the empty space, either above, below, to the left, or to the right." +
                    "\nWhen a valid tile is clicked, it moves into the empty space and the move counter increases by one." +
                    "\nIf a tile is not next to the empty space, clicking it will have no effect." +
                    "\nThe objective of the game is to arrange the tiles in the correct order." +
                    "\nThe puzzle is solved when the numbers are in ascending order from 1 to 15, or when the image is fully completed in image mode." +
                    "\nOnce the puzzle is solved, a winning message is displayed that shows how many moves were taken to complete the puzzle." +
                    "\nAfter the puzzle has been solved, the player is no longer allowed to move any tiles." +
                    "\nThe only actions that can be performed are switching between number and image modes or starting a new game." +
                    "\nThe game starts in number mode, where each tile displays a number." +
                    "\nThe toggle button allows the player to switch between numbers and images." +
                    "\nWhen the button labeled “Images” is clicked, the grid changes to display pieces of an image and the button text changes to “Numbers.”" +
                    "\nClicking the button again switches the grid back to numbers and updates the button text to “Images.”" +
                    "\nThe new game button only becomes active after the puzzle has been solved." +
                    "\nWhen pressed, it resets the move counter to zero, shuffles the tiles into a new random arrangement, and starts a new game.", "Rules", JOptionPane.PLAIN_MESSAGE);
            //show again
            JOptionPane.showMessageDialog(this, "Remember, you can access this by pressing R on your keyboard" +
                    "\nR for Rules\nGood Luck");
        }
        //used AI to make background cahnge color
        //color changer
        {
            float[] h = {0f};
            Timer gameLoop = new Timer(50 /*20 PFS*/, e -> {
                if (panel.getGameWon())
                    panel.setBackground(Color.GREEN);
                else {
                    Color rainbow = Color.getHSBColor(h[0], 1f, 1f);
                    panel.setBackground(rainbow);
                    h[0] += 0.002f;
                    if (h[0] > 1f)
                        h[0] = 0f;
                }
                panel.repaint();
            });
            gameLoop.start();
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
