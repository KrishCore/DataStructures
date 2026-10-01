import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MarioGame extends JPanel implements Runnable, KeyListener {

    // Screen Dimensions
    private static final int TILE_SIZE = 32;
    private static final int SCREEN_COLUMNS = 25;
    private static final int SCREEN_ROWS = 15;
    private static final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLUMNS; // 800px
    private static final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROWS;  // 480px

    // Game Thread
    private Thread gameThread;
    private boolean isRunning = false;
    private final int FPS = 60;

    // Controls
    private boolean leftPressed, rightPressed, jumpPressed;

    // Player State
    private float playerX = 60;
    private float playerY = 300;
    private final int playerWidth = 28;
    private final int playerHeight = 32;
    private float velocityX = 0;
    private float velocityY = 0;
    private boolean isGrounded = false;
    private int score = 0;
    private boolean levelCleared = false;

    // Physics Constants
    private final float GRAVITY = 0.5f;
    private final float JUMP_STRENGTH = -11.0f;
    private final float MOVE_SPEED = 3.5f;

    // Level Representation (1: Ground/Block, 2: Coin, 3: Goal Flag)
    private final int[][] levelMap = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,3,0},
        {0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,3,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public MarioGame() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(107, 140, 255)); // Classic Mario Sky Blue
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void startGameThread() {
        isRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (isRunning) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        if (levelCleared) return;

        // Horizontal Movement
        velocityX = 0;
        if (leftPressed) velocityX -= MOVE_SPEED;
        if (rightPressed) velocityX += MOVE_SPEED;

        // Jump
        if (jumpPressed && isGrounded) {
            velocityY = JUMP_STRENGTH;
            isGrounded = false;
        }

        // Apply Gravity
        velocityY += GRAVITY;

        // Move Horizontal & Resolve Collisions
        playerX += velocityX;
        checkHorizontalCollisions();

        // Move Vertical & Resolve Collisions
        playerY += velocityY;
        checkVerticalCollisions();

        // Check Items & Objectives
        checkInteractables();
    }

    private void checkHorizontalCollisions() {
        Rectangle playerBounds = getPlayerBounds();

        for (int r = 0; r < SCREEN_ROWS; r++) {
            for (int c = 0; c < SCREEN_COLUMNS; c++) {
                if (levelMap[r][c] == 1) { // Block
                    Rectangle blockBounds = new Rectangle(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (playerBounds.intersects(blockBounds)) {
                        if (velocityX > 0) {
                            playerX = blockBounds.x - playerWidth;
                        } else if (velocityX < 0) {
                            playerX = blockBounds.x + blockBounds.width;
                        }
                    }
                }
            }
        }
    }

    private void checkVerticalCollisions() {
        Rectangle playerBounds = getPlayerBounds();
        isGrounded = false;

        for (int r = 0; r < SCREEN_ROWS; r++) {
            for (int c = 0; c < SCREEN_COLUMNS; c++) {
                if (levelMap[r][c] == 1) { // Block
                    Rectangle blockBounds = new Rectangle(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (playerBounds.intersects(blockBounds)) {
                        if (velocityY > 0) { // Landing on top
                            playerY = blockBounds.y - playerHeight;
                            velocityY = 0;
                            isGrounded = true;
                        } else if (velocityY < 0) { // Hitting ceiling
                            playerY = blockBounds.y + blockBounds.height;
                            velocityY = 0;
                        }
                    }
                }
            }
        }
    }

    private void checkInteractables() {
        Rectangle playerBounds = getPlayerBounds();

        for (int r = 0; r < SCREEN_ROWS; r++) {
            for (int c = 0; c < SCREEN_COLUMNS; c++) {
                if (levelMap[r][c] == 2) { // Coin
                    Rectangle coinBounds = new Rectangle(c * TILE_SIZE + 8, r * TILE_SIZE + 8, 16, 16);
                    if (playerBounds.intersects(coinBounds)) {
                        levelMap[r][c] = 0; // Collect coin
                        score += 100;
                    }
                } else if (levelMap[r][c] == 3) { // Goal Flag
                    Rectangle flagBounds = new Rectangle(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (playerBounds.intersects(flagBounds)) {
                        levelCleared = true;
                    }
                }
            }
        }
    }

    private Rectangle getPlayerBounds() {
        return new Rectangle((int) playerX, (int) playerY, playerWidth, playerHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw Map
        for (int r = 0; r < SCREEN_ROWS; r++) {
            for (int c = 0; c < SCREEN_COLUMNS; c++) {
                int tile = levelMap[r][c];
                int x = c * TILE_SIZE;
                int y = r * TILE_SIZE;

                if (tile == 1) { // Ground / Brick
                    g2.setColor(new Color(184, 50, 0));
                    g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);
                } else if (tile == 2) { // Coin
                    g2.setColor(Color.GOLD);
                    g2.fillOval(x + 8, y + 8, 16, 16);
                } else if (tile == 3) { // Goal Pole
                    g2.setColor(Color.GREEN);
                    g2.fillRect(x + 12, y, 8, TILE_SIZE);
                }
            }
        }

        // Draw Mario (Player)
        g2.setColor(Color.RED);
        g2.fillRect((int) playerX, (int) playerY, playerWidth, playerHeight);

        // Draw Overlays / HUD
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.drawString("SCORE: " + score, 20, 30);

        if (levelCleared) {
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("STAGE CLEARED!", SCREEN_WIDTH / 2 - 150, SCREEN_HEIGHT / 2);
        }
    }

    // Key Listener Overrides
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) leftPressed = true;
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) rightPressed = true;
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP || code == KeyEvent.VK_W) jumpPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) rightPressed = false;
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_UP || code == KeyEvent.VK_W) jumpPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Main Method Entry Point
    public static void main(String[] args) {
        JFrame window = new JFrame("2D Java Mario");
        MarioGame gamePanel = new MarioGame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
