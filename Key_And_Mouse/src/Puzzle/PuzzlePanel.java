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
    private BufferedImage black = ImageIO.read(new File("src\\Puzzle\\black.png"));
    private BufferedImage[][] images = new BufferedImage[4][4];
    private BufferedImage[][] numbers = new BufferedImage[4][4];
    private JLabel moveCount = new JLabel("Move Count: 0");
    private JButton newGame =  new JButton("New Game");
    private JToggleButton toggle = new JToggleButton("Images");
    private JLabel winMessage = new JLabel("You solved the puzzle in __ moves!");
    private String[] messagesOfEncouragement = {"Keep going! You got this!", "Almost there!",
            "Take a deep breath and remember how far you've come!",
            "Trust yourself, you're on the right path", "This is tough, but you're tougher!", "One tile at a time!"};

    private PuzzleBoard board;

    private int moves;
    private boolean gameWon;
    private boolean imageMode;

    public PuzzlePanel() throws IOException {
        setSize(550,550);
        setLayout(null);//ew BorderLayout());
        setBackground(Color.GREEN);
        board = new PuzzleBoard();
        loadImage();
        loadNumber();
        winMessage.setText(messagesOfEncouragement[(int) (Math.random()*messagesOfEncouragement.length)]);
        winMessage.setBounds(0, 570, 500, 30);
        winMessage.setHorizontalAlignment(JLabel.CENTER);
        winMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        add(winMessage);

        //grid
        {
            JPanel grid = new JPanel(new GridLayout(4, 4));
            grid.setBounds(0, 30, 500, 500);
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++) {
                    butons[r][c] = new JButton();
                    butons[r][c].setPreferredSize(new Dimension(100, 100));
                    butons[r][c].setBorder(BorderFactory.createLineBorder(new Color(0, 60, 255), 6));
                    butons[r][c].setFont(new Font("Arial", Font.BOLD, 20));
                    butons[r][c].setFocusPainted(false);
                    butons[r][c].setSelected(false);
                    //mouse listeners - mousePresed
                    {
                        int row = r;
                        int col = c;
                        butons[r][c].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                tileClicked(row, col);
                            }
                        });
                    }
                    grid.add(butons[r][c]);
                }
            add(grid);//, BorderLayout.CENTER);
        }
        //extra
        {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) { //not needed, extra
//                    super.keyTyped(e);
                    int code = e.getKeyCode();
                    System.out.println(e.getKeyCode());
                    if (code == KeyEvent.VK_S) {
                        //code to show the solved version
                        System.out.println("s: " + code);
                    }
                }
            });
        }
        addMouseListener(this);
        moveCount.setBounds(0, 0, 500, 30);
        moveCount.setFont(new Font("Monospace", Font.BOLD, 20));
        moveCount.setHorizontalAlignment(JLabel.CENTER);
        add(moveCount);

        JPanel bottom = new JPanel();
        bottom.setBounds(0, 530, 500, 40);
        bottom.add(newGame);
        newGame.setFont(new Font("Monospace", Font.BOLD, 15));
        bottom.add(toggle);
        toggle.setFont(new Font("Monospace", Font.BOLD, 15));
        bottom.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        add(bottom);//, BorderLayout.SOUTH);

        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
            updateBoard();
        });
        //new game
        newGame.addActionListener(e -> {
            if (gameWon) //set as "true" for testing purposes otherwise gameWon
            {
                board.shuffle();
                moves = 0;
                gameWon = false;
                moveCount.setText("Move Count: 0");
                winMessage.setText(messagesOfEncouragement[(int) (Math.random()*messagesOfEncouragement.length)]);
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
                if (r == 3 && c == 3)
                    images[r][c] = black;
                else images[r][c] = puzzleSquare.getPortion(r, c);

    }

    private void loadNumber() throws IOException
    {
        PuzzleSquare puzzleSquare = new PuzzleSquare("src\\Puzzle\\Numbes.png");
        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++)
                numbers[r][c] = puzzleSquare.getPortion(r, c);

    }

    private void tileClicked(int r, int c)
    {
        if (gameWon) return;

        if (board.move(r, c))
        {
            moves++;
            moveCount.setText("Move Count: " + moves);
            updateBoard();
            winMessage.setText(messagesOfEncouragement[(int) (Math.random()*messagesOfEncouragement.length)]);
            if (board.isSolved())
            {
                gameWon = true;
                winMessage.setText("You solved the puzzle in " + moves + " moves!");

//                JOptionPane.showMessageDialog(this, "You solved the puzzle in " + moves + " moves!");
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
                    Image scaled = imageMode ? images[3][3].getScaledInstance(125, 125, BufferedImage.SCALE_SMOOTH) : numbers[3][3].getScaledInstance(125, 125, Image.SCALE_SMOOTH);
                    ImageIcon ii = new ImageIcon(scaled);
                    butons[r][c].setIcon(ii);
//                    butons[r][c].setIcon(null);
                }
                else
                {
                    if (!imageMode)
                    {
                        int row = (val-1) / 4;
                        int col = (val-1) % 4;

                        int w = butons[r][c].getWidth();
                        int h = butons[r][c].getHeight();
//                        System.out.println(w + ":" + h);
                        if (w <= 0 || h <= 0) {
                            Dimension d = butons[r][c].getPreferredSize();
                            w = d.width;
                            h = d.height;
                        }
//                        System.out.println(w + ":" + h);

                        Image scaled = numbers[row][col].getScaledInstance(125, 125, Image.SCALE_SMOOTH);
                        butons[r][c].setIcon(new ImageIcon(scaled));
                    }
                    else
                    {
                        butons[r][c].setText("");
                        int row = (val-1) / 4;
                        int col = (val-1) % 4;

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
