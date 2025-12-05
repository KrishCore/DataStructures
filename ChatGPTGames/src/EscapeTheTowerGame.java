import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class EscapeTheTowerGame extends JPanel implements ActionListener, KeyListener {

    // Game settings
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TILE_SIZE = 50;
    private static final int PLAYER_SIZE = 40;
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = -15;

    // Player settings
    private int playerX = 100;
    private int playerY = WINDOW_HEIGHT - TILE_SIZE - PLAYER_SIZE;
    private int playerVelocityY = 0;
    private int playerSpeed = 5;
    private boolean left = false, right = false, jumping = false;

    // Level settings
    private ArrayList<Rectangle> platforms;
    private ArrayList<Rectangle> enemies;
    private ArrayList<Rectangle> coins;
    private int score = 0;

    // Game state
    private boolean gameOver = false;
    private Timer timer;

    public EscapeTheTowerGame() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.CYAN);

        platforms = new ArrayList<>();
        enemies = new ArrayList<>();
        coins = new ArrayList<>();

        // Set up platforms
        createPlatforms();

        // Timer to control the game loop
        timer = new Timer(16, this);  // ~60 FPS
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    private void createPlatforms() {
        // Create some platforms
        platforms.add(new Rectangle(0, 500, WINDOW_WIDTH, TILE_SIZE));  // Ground platform
        platforms.add(new Rectangle(200, 400, 200, TILE_SIZE));  // Platform 1
        platforms.add(new Rectangle(500, 300, 200, TILE_SIZE));  // Platform 2
        platforms.add(new Rectangle(100, 200, 200, TILE_SIZE));  // Platform 3

        // Add enemies
        enemies.add(new Rectangle(350, 270, 30, 30)); // Enemy 1
        enemies.add(new Rectangle(600, 170, 30, 30)); // Enemy 2

        // Add coins
        coins.add(new Rectangle(220, 380, 20, 20)); // Coin 1
        coins.add(new Rectangle(520, 280, 20, 20)); // Coin 2
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            movePlayer();
            applyGravity();
            checkCollisions();
            checkGameOver();
        }
        repaint(); // Redraw the screen every frame
    }

    private void movePlayer() {
        if (left) {
            playerX -= playerSpeed;
        }
        if (right) {
            playerX += playerSpeed;
        }

        // Make sure the player stays within bounds
        playerX = Math.max(0, Math.min(WINDOW_WIDTH - PLAYER_SIZE, playerX));

        // Update player's Y position based on velocity
        playerY += playerVelocityY;
    }

    private void applyGravity() {
        if (playerY < WINDOW_HEIGHT - PLAYER_SIZE && !jumping) {
            playerVelocityY += GRAVITY;  // Apply gravity
        }
    }

    private void checkCollisions() {
        // Check collision with platforms
        for (Rectangle platform : platforms) {
            if (playerY + PLAYER_SIZE <= platform.y && playerY + PLAYER_SIZE + playerVelocityY >= platform.y
                    && playerX + PLAYER_SIZE > platform.x && playerX < platform.x + platform.width) {
                playerY = platform.y - PLAYER_SIZE;
                playerVelocityY = 0;
                jumping = false;
                break;
            }
        }

        // Check collision with enemies
        for (Rectangle enemy : enemies) {
            if (new Rectangle(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE).intersects(enemy)) {
                gameOver = true;
            }
        }

        // Check collision with coins
        ArrayList<Rectangle> collectedCoins = new ArrayList<>();
        for (Rectangle coin : coins) {
            if (new Rectangle(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE).intersects(coin)) {
                collectedCoins.add(coin);
                score += 10; // Increment score when collecting a coin
            }
        }
        coins.removeAll(collectedCoins); // Remove collected coins
    }

    private void checkGameOver() {
        if (playerY > WINDOW_HEIGHT) {
            gameOver = true;  // Player falls off screen
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw platforms
        g.setColor(Color.GREEN);
        for (Rectangle platform : platforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }

        // Draw enemies
        g.setColor(Color.RED);
        for (Rectangle enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
        }

        // Draw coins
        g.setColor(Color.YELLOW);
        for (Rectangle coin : coins) {
            g.fillOval(coin.x, coin.y, coin.width, coin.height);
        }

        // Draw player
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, PLAYER_SIZE, PLAYER_SIZE);

        // Display score
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);

        // Game Over text
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", WINDOW_WIDTH / 3, WINDOW_HEIGHT / 2);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && !jumping) {
            jumping = true;
            playerVelocityY = JUMP_STRENGTH;  // Jump strength
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame window = new JFrame("Escape the Tower");
        EscapeTheTowerGame game = new EscapeTheTowerGame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
