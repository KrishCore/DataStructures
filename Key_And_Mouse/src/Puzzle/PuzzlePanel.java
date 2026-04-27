package Puzzle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PuzzlePanel extends JPanel implements MouseListener
{
    private JButton[][] butons = new JButton[4][4];
    private BufferedImage black = ImageIO.read(new File("src\\Puzzle\\black.png"));
    private BufferedImage[][] images = new BufferedImage[4][4];
    private BufferedImage[][] numbers = new BufferedImage[4][4];
    private String path;
    private JLabel moveCount = new JLabel("Move Count: 0");
    private JButton newGame =  new JButton("New Game");
    private JToggleButton toggle = new JToggleButton("Images");
    private JLabel winMessage = new JLabel("You solved the puzzle in __ moves!");
    private String[] messagesOfEncouragement = {"Keep going! You got this!", "Almost there!", "Keep on keeping on!",
            "Take a deep breath and remember how far you've come!", "You're capable of incredible things",
            "Trust yourself, you're on the right path", "This is tough, but you're tougher!", "One tile at a time!",
            "You're doing better than you realize"};
    private ArrayList<String> mesagesOfEncouragement = new ArrayList<>();
    private JLabel highScore = new JLabel("High Score: \nNone");
    private File file = new File("src\\Puzzle\\highSchore");
    private int fScore;

    private PuzzleBoard board;

    private int moves;
    private boolean gameWon;
    private boolean imageMode;
    private boolean imageSelected = false;
    private boolean defaultImageSelected = true;

    public PuzzlePanel() throws IOException {
        setSize(550,550);
        setLayout(null);//ew BorderLayout());
        setBackground(Color.WHITE);
        board = new PuzzleBoard();
        loadNumber();
//        loadImage();
        setFocusable(true);
        addMouseListener(this);
        requestFocusInWindow();
        winMessage.setText(messagesOfEncouragement[(int) (Math.random()*messagesOfEncouragement.length)]);

        highScore.setBounds(525, 100, 200, 30);
        highScore.setFont(new Font("Monospace", Font.BOLD, 15));
        highScore.setForeground(Color.BLACK);
        add(highScore);

        if (!file.exists())
        {
            file.createNewFile();
            fScore = Integer.MAX_VALUE;
        }
        else if (file.length() > 0)
            try (Scanner sc = new Scanner(file)) {
                if (sc.hasNextInt()) {
                    fScore = sc.nextInt();
                    highScore.setText("High Score: " + fScore);
                    System.out.println("High Score: " + fScore);
                }
            }
        else fScore = Integer.MAX_VALUE;

        winMessage.setBounds(0, 570, 500, 30);
        winMessage.setHorizontalAlignment(JLabel.CENTER);
        winMessage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        winMessage.setForeground(Color.BLACK);
        add(winMessage);
        mesagesOfEncouragement.addAll(Arrays.asList(messagesOfEncouragement));

        //grid
        {
            JPanel grid = new JPanel(null);//ew GridLayout(4, 4));
            grid.setBounds(0, 30, 500, 500);
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++) {
                    butons[r][c] = new JButton();
                    butons[r][c].setBounds(c*125, r*125, 125, 125);
                    butons[r][c].setPreferredSize(new Dimension(100, 100));
                    butons[r][c].setBorder(BorderFactory.createLineBorder(new Color(0, 60, 255), 6));
                    butons[r][c].setFont(new Font("Arial", Font.BOLD, 20));
                    butons[r][c].setFocusPainted(false);
                    butons[r][c].setSelected(false);
                    butons[r][c].setFocusable(false);
                    //mouse listener - mousePresed
                    {
                        int row = r;
                        int col = c;
                        butons[r][c].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                try {
                                    tileClicked(row, col);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    }
                    grid.add(butons[r][c]);
                }
            add(grid);//, BorderLayout.CENTER);
        }

        //extra - does not work
        {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) { //not needed, extra
                    keyPresed(e);
                }
            });
        }

        addMouseListener(this);
        moveCount.setBounds(0, 0, 500, 30);
        moveCount.setFont(new Font("Monospace", Font.BOLD, 20));
        moveCount.setHorizontalAlignment(JLabel.CENTER);
        moveCount.setForeground(Color.BLACK);
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
            requestFocusInWindow();
            updateBoard();
        });

        //new game
        {
//        newGame.addActionListener(e -> {
//            if (gameWon) //set as "true" for testing purposes otherwise gameWon
//            {
//                if (imageMode)
//                {
//                    int result = JOptionPane.showConfirmDialog(this, "Do you want to use the same image?", "", JOptionPane.YES_NO_OPTION);
//                    System.out.println(result);
//                    imageSelected = false;
//                    if (result == 1)
//                    {
//                        int choose = JOptionPane.showConfirmDialog(this, "Do you want to choose another image of yours?", "", JOptionPane.YES_NO_OPTION);
//                        System.out.println(choose);
//                        if (choose == 0)
//                        {
//                            JFileChooser jfc = new JFileChooser();
//                            int j = jfc.showOpenDialog(this);
//
//                            if (j == JFileChooser.APPROVE_OPTION)
//                            {
//                                File f = jfc.getSelectedFile();
//                                path = f.getAbsolutePath();
//                                System.out.println(path);
//                                imageSelected = true;
//                            }
//                        }
//                        else defaultImageSelected = false;
//                    }
//                }
//                try {
//                    loadImage();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                board.shuffle();
//                moves = 0;
//                gameWon = false;
//                System.out.println("New Game----------------\nMove Count: 0");
//                moveCount.setText("Move Count: 0");
//                updateBoard();
//                updateEncouragement();
//            }
//        });
            newGame.addActionListener(e -> {
                // FIX: If you want to allow a new game at any time (not just when won),
                // change 'if (gameWon)' to simply let them restart.
                if (gameWon)
                {
                    if (imageMode)
                    {
                        int result = JOptionPane.showConfirmDialog(this, "Do you want to use the same image?", "", JOptionPane.YES_NO_OPTION);
                        System.out.println(result);
                        imageSelected = false;

                        // If they DO NOT want to use the same image
                        if (result == JOptionPane.NO_OPTION)
                        {
                            int choose = JOptionPane.showConfirmDialog(this, "Do you want to choose another image of yours?", "", JOptionPane.YES_NO_OPTION);
                            System.out.println(choose);

                            // If they WANT to choose their own new image
                            if (choose == JOptionPane.YES_OPTION)
                            {
                                JFileChooser jfc = new JFileChooser();
                                int j = jfc.showOpenDialog(this);

                                if (j == JFileChooser.APPROVE_OPTION)
                                {
                                    File f = jfc.getSelectedFile();
                                    path = f.getAbsolutePath();
                                    System.out.println(path);
                                    imageSelected = true;
                                }
                            }
                            // If they DO NOT want to choose their own image, use default game images
                            else
                            {
                                defaultImageSelected = true;
                            }
                        }
                    }
                    try {
                        loadImage();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    board.shuffle();
                    moves = 0;
                    gameWon = false;
                    System.out.println("New Game----------------\nMove Count: 0");
                    moveCount.setText("Move Count: 0");
                    updateBoard();
                    updateEncouragement();
                }
            });
        }

        //toggle image
        {
//            toggle.addActionListener(e -> {
//                if (!imageSelected)
//                {
//                    int response = JOptionPane.showConfirmDialog(this, "Do you want to use your own image?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//                    System.out.println(response);
//                    if (response == JOptionPane.YES_OPTION)
//                    {
//                        JFileChooser jfc = new JFileChooser();
//                        int j = jfc.showOpenDialog(this);
//
//                        if (j == JFileChooser.APPROVE_OPTION)
//                        {
//                            File f = jfc.getSelectedFile();
//                            path = f.getAbsolutePath();
//                            System.out.println(path);
//                            imageSelected = true;
//                        }
//                    }
//                }
//                imageMode = !imageMode;
//                if (imageMode)
//                    toggle.setText("Numbers");
//                else toggle.setText("Images");
//
//                try {
//                    loadImage();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//
//                }
//                updateBoard();
//            });
             // New game listener
        toggle.addActionListener(e -> {
            // FIX: Use toggle.isSelected() so the prompt only happens when turning images ON.
            // This stops the dialog from popping up when turning image mode OFF.
            if (toggle.isSelected() && !imageSelected && defaultImageSelected)
            {
                int response = JOptionPane.showConfirmDialog(this, "Do you want to use your own image?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                System.out.println(response);

                if (response == JOptionPane.YES_OPTION)
                {
                    JFileChooser jfc = new JFileChooser();
                    int j = jfc.showOpenDialog(this);

                    if (j == JFileChooser.APPROVE_OPTION)
                    {
                        File f = jfc.getSelectedFile();
                        path = f.getAbsolutePath();
                        System.out.println(path);
                        imageSelected = true;
                    }
                }
                // If they say no, fallback securely to default program images
                else
                {
                    defaultImageSelected = true;
                }
            }

            // Toggle the visual state
            imageMode = toggle.isSelected();

            if (imageMode)
                toggle.setText("Numbers");
            else
                toggle.setText("Images");

            try {
                loadImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            updateBoard();
        });
        }
        setVisible(true);
    }

    private void loadImage() throws IOException // the image grid contains the black square
    {
        if (imageSelected || !defaultImageSelected)
        {
            System.out.println(path);
            PuzzleSquare puzzleSquare = new PuzzleSquare(path);

            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++)
                    if (r == 3 && c == 3)
                        images[r][c] = black;
                    else images[r][c] = puzzleSquare.getPortion(r, c);
        }
        else
        {
            PuzzleSquare puzzleSquare;// = rand < .5 ? new PuzzleSquare("src\\Puzzle\\spongebob-fish.png") : new PuzzleSquare("src\\Puzzle\\spongebob-pfp.png");
            if (Math.random() >= .5)
                puzzleSquare = new PuzzleSquare("src\\Puzzle\\spongebob-fish.png");
            else
                try {
                    puzzleSquare = new PuzzleSquare("src\\Puzzle\\spongebob-pfp.png");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            for (int r = 0; r < 4; r++)
                for (int c = 0; c < 4; c++)
                    if (r == 3 && c == 3)
                        images[r][c] = black;
                    else images[r][c] = puzzleSquare.getPortion(r, c);
        }
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
            updateEncouragement();

            if (board.isSolved())
            {
                gameWon = true;
                winMessage.setText("You solved the puzzle in " + moves + " moves!");
                updateHighScore(moves);
            }
            else updateEncouragement();
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

                }
                else
                    if (!imageMode)
                    {
                        int row = (val-1) / 4;
                        int col = (val-1) % 4;

                        Image scaled = numbers[row][col].getScaledInstance(125, 125, Image.SCALE_SMOOTH);
                        butons[r][c].setIcon(new ImageIcon(scaled));
                    }
                    else
                    {
                        butons[r][c].setText("");
                        int row = (val - 1) / 4;
                        int col = (val - 1) % 4;

                        Image scaled = images[row][col].getScaledInstance(125, 125, Image.SCALE_SMOOTH);
                        butons[r][c].setIcon(new ImageIcon(scaled));
                    }
            }
        }
    }

    private void updateEncouragement()
    {
        String mesage = winMessage.getText();
        System.out.println(mesage);
        mesagesOfEncouragement.remove(mesage);
        String s = mesagesOfEncouragement.get((int) (Math.random()*mesagesOfEncouragement.size()));
        System.out.println(s);
        winMessage.setText(s);
        mesagesOfEncouragement.add(mesage);
        System.out.println(mesagesOfEncouragement);
    }

    private void updateHighScore(int newScore)
    {
        if (newScore < fScore) {
            fScore = newScore;
            highScore.setText("High Score: " + newScore);
            System.out.println(newScore);
            repaint();

            try {
                PrintWriter pw = new PrintWriter(new FileWriter(file, false));
                pw.print(newScore);
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("New High Score Saved: " + newScore);
        }
    }

    public boolean getGameWon()
    {
        return gameWon;
    }

    public void keyPresed(KeyEvent e) // used AI for the summary, same as in PuzzleFrame class
    {
        int code = e.getKeyCode();
        System.out.println(e.getKeyCode());
        if (code == KeyEvent.VK_R) {
            //code to show the solved version
            System.out.println("s: " + e.getKeyChar());
            JOptionPane.showMessageDialog(this, "Welcome to 15 Puzzle." +
                    "\nThe 15 Puzzle Game is a classic sliding puzzle that challenges players to arrange tiles in the correct order." +
                    "\nThe game consists of a 4×4 grid with 15 numbered tiles and one empty space." +
                    "\nPlayers move tiles into the empty space to reorder them into ascending order or to complete a full image." +
                    "\nThis program recreates the 15 puzzle in a digital format." +
                    "\nIt allows users to interact with the puzzle by clicking tiles, track their number of moves, switch between number and image modes, and start a new game once the puzzle is solved." +
                    "\nThe goal is to solve the puzzle in the fewest moves possible.", "Intro", JOptionPane.PLAIN_MESSAGE);
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
                    "\nThe game starts in number mode, where each tile displays a number. The toggle button allows the player to switch between numbers and images." +
                    "\nWhen the button labeled “Images” is clicked, the grid changes to display pieces of an image and the button text changes to “Numbers.” Clicking the button again switches the grid back to numbers and updates the button text to “Images.”\n" +
                    "\nThe new game button only becomes active after the puzzle has been solved." +
                    "\nWhen pressed, it resets the move counter to zero, shuffles the tiles into a new random arrangement, and starts a new game.", "Rules", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
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
