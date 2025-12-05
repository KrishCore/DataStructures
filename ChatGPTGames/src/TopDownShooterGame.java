import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class TopDownShooterGame extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    // Player attributes
    private int playerX = 400;
    private int playerY = 300;
    private int playerSize = 30;
    private int playerSpeed = 5;

    // Bullet attributes
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private int bulletSpeed = 10;

    // Enemy attributes
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private int enemySpeed = 2;

    private Timer timer;
    private Random rand;

    private int score = 0;

    private int mouseX, mouseY;

    // Timer for continuous shooting
    private Timer shootingTimer;

    public TopDownShooterGame() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);

        rand = new Random();
        timer = new Timer(16, this); // ~60 FPS
        timer.start();

        // Shooting timer: this controls the firing rate (every 100ms)
        shootingTimer = new Timer(100, e -> {
            if (shooting) {
                shootBullet();
            }
        });
        shootingTimer.setRepeats(true); // Ensure the timer keeps firing bullets continuously

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        movePlayer();
        moveBullets();
        moveEnemies();
        checkCollisions();
        spawnEnemies();
        repaint();
    }

    private void movePlayer() {
        if (left) playerX -= playerSpeed;
        if (right) playerX += playerSpeed;
        if (up) playerY -= playerSpeed;
        if (down) playerY += playerSpeed;

        // Keep player within bounds
        playerX = Math.max(0, Math.min(770, playerX));
        playerY = Math.max(0, Math.min(570, playerY));
    }

    private void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.x += b.dx;
            b.y += b.dy;

            // Remove bullet if it goes off screen
            if (b.x < 0 || b.x > getWidth() || b.y < 0 || b.y > getHeight()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    private void moveEnemies() {
        for (Enemy e : enemies) {
            e.x += e.dx;
            e.y += e.dy;
        }
    }

    private void spawnEnemies() {
        if (rand.nextInt(100) < 2) {
            int enemyX = rand.nextInt(getWidth());
            int enemyY = -20; // Start just above the screen
            int dx = rand.nextInt(3) - 1; // Random horizontal movement (-1, 0, or 1)
            int dy = rand.nextInt(3) + 1; // Downward movement

            enemies.add(new Enemy(enemyX, enemyY, dx, dy));
        }
    }

    private void checkCollisions() {
        // Check if bullet hits an enemy
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy e = enemies.get(j);
                if (new Rectangle(b.x, b.y, 10, 10).intersects(new Rectangle(e.x, e.y, 30, 30))) {
                    enemies.remove(j);
                    bullets.remove(i);
                    score += 10;
                    break;
                }
            }
        }

        // Check if enemy hits player
        for (Enemy e : enemies) {
            if (new Rectangle(playerX, playerY, playerSize, playerSize).intersects(new Rectangle(e.x, e.y, 30, 30))) {
                JOptionPane.showMessageDialog(this, "Game Over! Score: " + score);
                System.exit(0);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw player
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, playerSize, playerSize);

        // Draw bullets
        g.setColor(Color.YELLOW);
        for (Bullet b : bullets) {
            g.fillRect(b.x, b.y, 10, 10);
        }

        // Draw enemies
        g.setColor(Color.RED);
        for (Enemy e : enemies) {
            g.fillRect(e.x, e.y, 30, 30);
        }

        // Draw score
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    // Player movement flags
    private boolean left = false, right = false, up = false, down = false;
    private boolean shooting = false; // Flag to track if the mouse is pressed (for continuous shooting)

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = true;
            case KeyEvent.VK_RIGHT -> right = true;
            case KeyEvent.VK_UP -> up = true;
            case KeyEvent.VK_DOWN -> down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_RIGHT -> right = false;
            case KeyEvent.VK_UP -> up = false;
            case KeyEvent.VK_DOWN -> down = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // Shoot bullet towards the mouse position
    private void shootBullet() {
        // Calculate the direction to the mouse position
        double angle = Math.atan2(mouseY - (playerY + playerSize / 2), mouseX - (playerX + playerSize / 2));
        int dx = (int) (bulletSpeed * Math.cos(angle));
        int dy = (int) (bulletSpeed * Math.sin(angle));

        bullets.add(new Bullet(playerX + playerSize / 2, playerY + playerSize / 2, dx, dy));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shooting = true;
        shootingTimer.start(); // Start shooting continuously
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shooting = false;
        shootingTimer.stop(); // Stop shooting when the mouse is released
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame window = new JFrame("Top-Down Shooter Game");
        TopDownShooterGame game = new TopDownShooterGame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // Bullet class
    class Bullet {
        int x, y, dx, dy;

        Bullet(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }

    // Enemy class
    class Enemy {
        int x, y, dx, dy;

        Enemy(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }
}
