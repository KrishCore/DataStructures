import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarioGame extends JPanel implements Runnable, KeyListener {

    // Display / Tile Config
    private static final int TILE_SIZE = 32;
    private static final int VIEWPORT_COLS = 25;
    private static final int VIEWPORT_ROWS = 15;
    private static final int SCREEN_WIDTH = TILE_SIZE * VIEWPORT_COLS; // 800px
    private static final int SCREEN_HEIGHT = TILE_SIZE * VIEWPORT_ROWS; // 480px

    // Loop Settings
    private Thread gameThread;
    private boolean isRunning = false;
    private final int FPS = 60;

    // Camera Tracking
    private float cameraX = 0;

    // Controls
    private boolean leftPressed, rightPressed, jumpPressed;

    // Player Stats & Physics
    private float playerX = 100;
    private float playerY = 300;
    private final int playerWidth = 26;
    private final int playerHeight = 32;
    private float velocityX = 0;
    private float velocityY = 0;
    private boolean isGrounded = false;
    private boolean isDead = false;
    private int score = 0;
    private boolean levelCleared = false;

    // Physics Constants (Faster, Snappier Feel)
    private final float GRAVITY = 0.65f;
    private final float JUMP_STRENGTH = -13.5f;
    private final float MOVE_SPEED = 5.2f;

    // Level Layout (40 columns wide)
    // 1: Ground/Brick, 2: Coin, 3: Goal Flag, 4: Question Block, 5: Pipe
    private final int[][] levelMap = {
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,4,2,4,2,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,3,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0},
        {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private final int levelWidthCols = levelMap[0].length;
    private final int levelWidthPixels = levelWidthCols * TILE_SIZE;

    // Enemies
    private final List<Goomba> goombas = new ArrayList<>();

    public MarioGame() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(107, 140, 255)); // Sky Blue
        this.setFocusable(true);
        this.addKeyListener(this);

        // Spawn Goombas
        goombas.add(new Goomba(450, 352));
        goombas.add(new Goomba(750, 352));
        goombas.add(new Goomba(900, 192));
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
        if (levelCleared || isDead) return;

        // Player Inputs
        velocityX = 0;
        if (leftPressed) velocityX -= MOVE_SPEED;
        if (rightPressed) velocityX += MOVE_SPEED;

        if (jumpPressed && isGrounded) {
            velocityY = JUMP_STRENGTH;
            isGrounded = false;
        }

        // Apply Gravity
        velocityY += GRAVITY;

        // X Movement & Collisions
        playerX += velocityX;
        if (playerX < 0) playerX = 0;
        checkHorizontalCollisions();

        // Y Movement & Collisions
        playerY += velocityY;
        checkVerticalCollisions();

        // Update Camera Position
        cameraX = playerX - (SCREEN_WIDTH / 3.0f);
        if (cameraX < 0) cameraX = 0;
        if (cameraX > levelWidthPixels - SCREEN_WIDTH) cameraX = levelWidthPixels - SCREEN_WIDTH;

        // Interactables (Coins & Goal)
        checkInteractables();

        // Update Goombas
        updateGoombas();

        // Pitfall Death Check
        if (playerY > SCREEN_HEIGHT + 100) {
            isDead = true;
        }
    }

    private void checkHorizontalCollisions() {
        Rectangle pBounds = getPlayerBounds();
        for (int r = 0; r < VIEWPORT_ROWS; r++) {
            for (int c = 0; c < levelWidthCols; c++) {
                if (isSolid(levelMap[r][c])) {
                    Rectangle block = new Rectangle(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (pBounds.intersects(block)) {
                        if (velocityX > 0) playerX = block.x - playerWidth;
                        else if (velocityX < 0) playerX = block.x + block.width;
                    }
                }
            }
        }
    }

    private void checkVerticalCollisions() {
        Rectangle pBounds = getPlayerBounds();
        isGrounded = false;

        for (int r = 0; r < VIEWPORT_ROWS; r++) {
            for (int c = 0; c < levelWidthCols; c++) {
                if (isSolid(levelMap[r][c])) {
                    Rectangle block = new Rectangle(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (pBounds.intersects(block)) {
                        if (velocityY > 0) {
                            playerY = block.y - playerHeight;
                            velocityY = 0;
                            isGrounded = true;
                        } else if (velocityY < 0) {
                            playerY = block.y + block.height;
                            velocityY = 0;
                        }
                    }
                }
            }
        }
    }

    private boolean isSolid(int tile) {
        return tile == 1 || tile == 4 || tile == 5;
    }

    private void checkInteractables() {
        Rectangle pBounds = getPlayerBounds();
        for (int r = 0; r < VIEWPORT_ROWS; r++) {
            for (int c = 0; c < levelWidthCols; c++) {
                if (levelMap[r][c] == 2) { // Coin
                    Rectangle coin = new Rectangle(c * TILE_SIZE + 8, r * TILE_SIZE + 8, 16, 16);
                    if (pBounds.intersects(coin)) {
                        levelMap[r][c] = 0;
                        score += 100;
                    }
                } else if (levelMap[r][c] == 3) { // Goal
                    Rectangle flag = new Rectangle(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if (pBounds.intersects(flag)) {
                        levelCleared = true;
                    }
                }
            }
        }
    }

    private void updateGoombas() {
        Rectangle pBounds = getPlayerBounds();
        Iterator<Goomba> it = goombas.iterator();

        while (it.hasNext()) {
            Goomba goomba = it.next();
            goomba.update();

            Rectangle gBounds = goomba.getBounds();
            if (pBounds.intersects(gBounds)) {
                // Check if Mario stomped on top
                if (velocityY > 0 && playerY + playerHeight - velocityY <= goomba.y + 10) {
                    it.remove();
                    velocityY = JUMP_STRENGTH * 0.6f; // Bounce
                    score += 200;
                } else {
                    isDead = true; // Mario touched from side/below
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

        // Enable Anti-Aliasing for smoother text
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Translate Camera
        g2.translate(-cameraX, 0);

        // Background Decor (Clouds)
        drawBackgroundDetails(g2);

        // Draw Map
        for (int r = 0; r < VIEWPORT_ROWS; r++) {
            for (int c = 0; c < levelWidthCols; c++) {
                int tile = levelMap[r][c];
                int x = c * TILE_SIZE;
                int y = r * TILE_SIZE;

                // Culling: Only draw tiles visible in camera frame
                if (x + TILE_SIZE < cameraX || x > cameraX + SCREEN_WIDTH) continue;

                if (tile == 1) { // Ground / Brick pattern
                    g2.setColor(new Color(184, 50, 0));
                    g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    g2.setColor(Color.DARK_GRAY);
                    g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);
                    g2.drawLine(x, y + 16, x + TILE_SIZE, y + 16);
                } else if (tile == 2) { // Gold Coin
                    g2.setColor(Color.GOLD);
                    g2.fillOval(x + 8, y + 8, 16, 16);
                    g2.setColor(Color.ORANGE);
                    g2.drawOval(x + 8, y + 8, 16, 16);
                } else if (tile == 3) { // Goal Flag
                    g2.setColor(Color.GREEN);
                    g2.fillRect(x + 14, y, 4, TILE_SIZE);
                    g2.setColor(Color.RED);
                    g2.fillPolygon(new int[]{x + 18, x + 32, x + 18}, new int[]{y, y + 8, y + 16}, 3);
                } else if (tile == 4) { // Question Block
                    g2.setColor(new Color(230, 140, 0));
                    g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);
                    g2.setFont(new Font("Arial", Font.BOLD, 18));
                    g2.drawString("?", x + 10, y + 24);
                } else if (tile == 5) { // Pipe
                    g2.setColor(new Color(0, 180, 0));
                    g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    g2.setColor(Color.BLACK);
                    g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Draw Goombas
        for (Goomba goomba : goombas) {
            goomba.draw(g2);
        }

        // Draw Mario
        if (!isDead) {
            g2.setColor(Color.RED); // Shirt/Hat
            g2.fillRect((int) playerX, (int) playerY, playerWidth, playerHeight);
            g2.setColor(Color.BLUE); // Overalls
            g2.fillRect((int) playerX + 4, (int) playerY + 16, playerWidth - 8, playerHeight - 16);
        }

        // Reset Translation for Screen Overlay (HUD)
        g2.translate(cameraX, 0);

        // HUD
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 18));
        g2.drawString("SCORE: " + score, 20, 30);

        if (levelCleared) {
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("STAGE CLEARED!", SCREEN_WIDTH / 2 - 150, SCREEN_HEIGHT / 2);
        } else if (isDead) {
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("GAME OVER", SCREEN_WIDTH / 2 - 110, SCREEN_HEIGHT / 2);
        }
    }

    private void drawBackgroundDetails(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval(200, 60, 60, 30);
        g2.fillOval(220, 50, 50, 30);
        g2.fillOval(600, 80, 70, 35);
        g2.fillOval(1000, 50, 60, 30);
    }

    // Key Listener Inputs
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

    // Inner Goomba Enemy Class
    private static class Goomba {
        float x, y;
        float speed = 1.2f;
        int width = 28;
        int height = 28;
        int walkDistance = 0;
        int maxWalkDistance = 100;

        public Goomba(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public void update() {
            x += speed;
            walkDistance += Math.abs(speed);
            if (walkDistance >= maxWalkDistance) {
                speed = -speed; // Reverse direction
                walkDistance = 0;
            }
        }

        public Rectangle getBounds() {
            return new Rectangle((int) x, (int) y, width, height);
        }

        public void draw(Graphics2D g2) {
            g2.setColor(new Color(139, 69, 19)); // Brown Body
            g2.fillRect((int) x, (int) y, width, height);
            g2.setColor(Color.WHITE); // Eyes
            g2.fillRect((int) x + 4, (int) y + 6, 6, 8);
            g2.fillRect((int) x + 18, (int) y + 6, 6, 8);
            g2.setColor(Color.BLACK);
            g2.fillRect((int) x + 6, (int) y + 8, 2, 4);
            g2.fillRect((int) x + 20, (int) y + 8, 2, 4);
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("2D Java Mario - Scrolling Edition");
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
