package Puzzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PuzzlePanel extends JPanel implements MouseListener
{
    private JButton[][] butons = new JButton[4][4];
    private BufferedImage image = ImageIO.read(new File("src\\Puzzle\\spongebob.png"));
    private BufferedImage[][] images = new BufferedImage[4][4];
    private BufferedImage[][] numbers = new BufferedImage[4][4];
    private JLabel moveCount = new JLabel("Move Count: 0");
    private JButton newGame =  new JButton("New Game");
    private JToggleButton toggle = new JToggleButton("Images");
    private JLabel winMessage = new JLabel("You solved the puzzle in __ moves!");

    private PuzzleBoard board;

    private int moves;
    private boolean gameWon;
    private boolean imageMode;

    public PuzzlePanel() throws IOException {
        setSize(500,500);
        setLayout(null);//ew BorderLayout());
        board = new PuzzleBoard();
        loadImage();
        loadNumber();
        winMessage.setBounds(0, 550, 500, 50);
        winMessage.setHorizontalAlignment(JLabel.CENTER);
        winMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        //grid
        {
            JPanel grid = new JPanel(new GridLayout(4, 4));
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++) {
                    butons[r][c] = new JButton();
                    butons[r][c].setPreferredSize(new Dimension(100, 100));
                    butons[r][c].setBorder(BorderFactory.createLineBorder(new Color(0, 60, 255), 6));
                    int row = r;
                    int col = c;
                    butons[r][c].setFont(new Font("Arial", Font.BOLD, 20));
                    butons[r][c].setFocusPainted(false);
                    butons[r][c].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tileClicked(row, col);
                        }
                    });
                    addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) { //not needed, extra
                            super.keyTyped(e);
                            int code = e.getKeyCode();
                            if (code == KeyEvent.VK_S) {
                                //code to show the solved version
                            }
                        }
                    });
                    grid.add(butons[r][c]);
                }
            grid.setBounds(0, 50, 450, 450);

            add(grid);//, BorderLayout.CENTER);
        }
        addMouseListener(this);
        moveCount.setBounds(0, 0, 500, 50);
        moveCount.setFont(new Font("Monospace", Font.BOLD, 20));
        moveCount.setHorizontalAlignment(JLabel.CENTER);
        add(moveCount);

        JPanel bottom = new JPanel();
        bottom.add(newGame);
        newGame.setFont(new Font("Monospace", Font.BOLD, 15));
        bottom.add(toggle);
        toggle.setFont(new Font("Monospace", Font.BOLD, 15));
        bottom.setBounds(-25, 500, 500, 50);
        bottom.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        add(bottom, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> updateBoard());

        //new game
        newGame.addActionListener(e -> {
            if (gameWon) //set as "true" for testing purposes
            {
                board.shuffle();
                moves = 0;
                gameWon = false;
                moveCount.setText("Move Count: 0");
                winMessage.setVisible(false);
                updateBoard();
            }
        });

        //image on/of
        {
            toggle.addActionListener(e -> {
                imageMode = !imageMode;
                if (imageMode)
                    toggle.setText("Numbers");
                else toggle.setText("Images");
                updateBoard();
            });
        }

        setVisible(true);
        }

    private void loadImage() throws IOException
    {
        PuzzleSquare puzzleSquare = new PuzzleSquare("src\\Puzzle\\spongebob.png");
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++)
                    images[r][c] = puzzleSquare.getImagePortion(r, c);
    }

    private void loadNumber() throws IOException
    {
        PuzzleSquare puzzleSquare = new PuzzleSquare("src\\Puzzle\\Numbes.png");
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++)
                    numbers[r][c] = puzzleSquare.getImagePortion(r, c);
    }

    private void tileClicked(int r, int c)
    {
        if (gameWon) return;

        if (board.move(r, c))
        {
            moves++;
            moveCount.setText("Move Count: " + moves);
            updateBoard();
            if (board.isSolved())
            {
                gameWon = true;
                winMessage.setVisible(true);
                winMessage.setText("You solved the puzzle in " + moves + " moves!");

                JOptionPane.showMessageDialog(this, "You solved the puzzle in " + moves + " moves!");
            }
        }
    }

    private void updateBoard() // may need to be changes a bit
    {
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                int val = board.getValue(r, c);
                if (val == 0)
                {
                    butons[r][c].setText("");
                    butons[r][c].setIcon(null);
                }
                else
                {
                    if (!imageMode)
                    {
                        int row = (val - 1) / 4;
                        int col = (val - 1) % 4;

                        int w = butons[r][c].getWidth();
                        int h = butons[r][c].getHeight();

                        if (w <= 0 || h <= 0) {
                            w = 100;
                            h = 100;
                        }

                        Image scaled = numbers[row][col].getScaledInstance(w, h, Image.SCALE_SMOOTH);
                        butons[r][c].setIcon(new ImageIcon(scaled));
                    }
                    else
                    {
                        butons[r][c].setText("");
                        int row = (val - 1) / 4;
                        int col = (val - 1) % 4;

                        int w = butons[r][c].getWidth();
                        int h = butons[r][c].getHeight();

                        if (w <= 0 || h <= 0) {
                            w = 100;
                            h = 100;
                        }

                        Image scaled = images[row][col].getScaledInstance(w, h, Image.SCALE_SMOOTH);
                        butons[r][c].setIcon(new ImageIcon(scaled));
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) { //implment this ----------------------------------------
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX + "____" + mouseY);

        if (e.getButton() == MouseEvent.BUTTON1)
        {
            //if statement for each possible square?

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
