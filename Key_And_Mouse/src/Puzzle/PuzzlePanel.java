package Puzzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class PuzzlePanel extends JPanel
{
    private JButton[][] butons = new JButton[4][4];
    private BufferedImage[][] images = new BufferedImage[4][4];
    private JLabel moveCount = new JLabel("Move Count: 0");
    private JButton newGame =  new JButton("New Game");
    private JToggleButton toggle = new JToggleButton("Images");

    private PuzzleBoard board;

    private int moves;
    private boolean gameWon;
    private boolean imageMode;

    public PuzzlePanel()
    {
//        setSize(500,500);
        setLayout(new BorderLayout());
        board = new PuzzleBoard();
        loadImage();

        JPanel grid = new JPanel(new GridLayout(4, 4));
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++) {
                butons[r][c] = new JButton();
                butons[r][c].setPreferredSize(new Dimension(100, 100));
                butons[r][c].setBorder(BorderFactory.createLineBorder(Color.GRAY,5));
                int row = r;
                int col = c;
                butons[r][c].setFont(new Font("Arial", Font.BOLD, 20));
                butons[r][c].setFocusPainted(false);
                butons[r][c].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        tileClicked(row, col);
                    }
                });
                addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) { //not needed, extra
                        super.keyTyped(e);
                        int code = e.getKeyCode();
                        if (code == KeyEvent.VK_S)
                        {
                            //code to show the solved version
                        }
                    }
                });
                grid.add(butons[r][c]);
            }
        add(grid, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(moveCount);
        bottom.add(newGame);
        bottom.add(toggle);
        add(bottom, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> updateBoard());

        newGame.addActionListener(e -> {
            if (gameWon) //set as "true" for testing purposes
            {
                board.shuffle();
                moves = 0;
                gameWon = false;
                moveCount.setText("Move Count: 0");
                updateBoard();
            }
        });

        toggle.addActionListener(e -> {
            imageMode = !imageMode;

            if (imageMode)
                toggle.setText("Numbers");
            else toggle.setText("Images");

            updateBoard();
        });

        setVisible(true);
        }

    private void loadImage()
    {
        try {
            BufferedImage img = ImageIO.read(new File("src\\Puzzle\\spongebob.png"));

            int size = Math.min(img.getWidth(), img.getHeight());
            int tile = size / 4;

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    images[r][c] = img.getSubimage(c * tile, r * tile, tile, tile);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                JOptionPane.showMessageDialog(this, "Solved in " + moves + " moves!");
            }
        }
    }

    private void updateBoard()
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
                    if (!imageMode) {
                        butons[r][c].setText(val + "");
                        butons[r][c].setIcon(null);
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
}
