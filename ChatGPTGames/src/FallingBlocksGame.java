import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FallingBlocksGame extends JPanel implements ActionListener, KeyListener {
    // Timer for game loop
    private Timer timer;
    private int playerX = 250, playerY = 450, playerWidth = 50, playerHeight = 50;
    private int playerSpeed = 8;
    private boolean leftPressed, rightPressed;
    private boolean gameOver = false;

    private ArrayList<Rectangle> blocks;
    private Random rand;
    private int score = 0;

    public FallingBlocksGame() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.WHITE);

        rand = new Random();
        blocks = new ArrayList<>();
        timer = new Timer(20, this); // ~50 FPS
        timer.start();

        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            updatePlayer();
            spawnBlocks();
            updateBlocks();
            checkCollisions();
            score++;
        }
        repaint();
    }

    private void updatePlayer() {
        if (leftPressed && playerX > 0)
            playerX -= playerSpeed;
        if (rightPressed && playerX + playerWidth < getWidth())
            playerX += playerSpeed;
    }

    private void spawnBlocks() {
        // Randomly add new blocks
        if (rand.nextInt(15) == 0) {
            int blockX = rand.nextInt(getWidth() - 50);
            blocks.add(new Rectangle(blockX, 0, 50, 50));
        }
    }

    private void updateBlocks() {
        Iterator<Rectangle> it = blocks.iterator();
        while (it.hasNext()) {
            Rectangle block = it.next();
            block.y += 6; // falling speed
            if (block.y > getHeight()) {
                it.remove();
            }
        }
    }

    private void checkCollisions() {
        Rectangle playerRect = new Rectangle(playerX, playerY, playerWidth, playerHeight);
        for (Rectangle block : blocks) {
            if (playerRect.intersects(block)) {
                gameOver = true;
                timer.stop();
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Player
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);

        // Blocks
        g.setColor(Color.RED);
        for (Rectangle block : blocks) {
            g.fillRect(block.x, block.y, block.width, block.height);
        }

        // Score
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", getWidth() / 2 - 100, getHeight() / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Final Score: " + score, getWidth() / 2 - 60, getHeight() / 2 + 40);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Falling Blocks Game");
        FallingBlocksGame game = new FallingBlocksGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
