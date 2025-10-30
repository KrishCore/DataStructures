import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TopDownShooter extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private int playerX = 280, playerY = 400;
    private int playerSize = 40;
    private int playerSpeed = 6;
    private int score = 0;

    private boolean up, down, left, right;
    private boolean shootUp, shootDown, shootLeft, shootRight;

    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private Random rand = new Random();

    private boolean gameOver = false;

    // --- Inner classes for Bullet and Enemy ---
    class Bullet {
        int x, y, size = 8;
        int dx, dy, speed = 10;

        Bullet(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }

        void move() {
            x += dx * speed;
            y += dy * speed;
        }

        Rectangle getBounds() {
            return new Rectangle(x, y, size, size);
        }
    }

    class Enemy {
        int x, y, size = 40, speed = 2;

        Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        void moveToward(int px, int py) {
            if (x < px) x += speed;
            if (x > px) x -= speed;
            if (y < py) y += speed;
            if (y > py) y -= speed;
        }

        Rectangle getBounds() {
            return new Rectangle(x, y, size, size);
        }
    }

    // --- Game setup ---
    public TopDownShooter() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            updatePlayer();
            updateBullets();
            spawnEnemies();
            updateEnemies();
            checkCollisions();
        }
        repaint();
    }

    private void updatePlayer() {
        if (up && playerY > 0) playerY -= playerSpeed;
        if (down && playerY + playerSize < getHeight()) playerY += playerSpeed;
        if (left && playerX > 0) playerX -= playerSpeed;
        if (right && playerX + playerSize < getWidth()) playerX += playerSpeed;

        // Shooting logic
        if (shootUp) bullets.add(new Bullet(playerX + playerSize / 2, playerY, 0, -1));
        if (shootDown) bullets.add(new Bullet(playerX + playerSize / 2, playerY, 0, 1));
        if (shootLeft) bullets.add(new Bullet(playerX, playerY + playerSize / 2, -1, 0));
        if (shootRight) bullets.add(new Bullet(playerX + playerSize, playerY + playerSize / 2, 1, 0));
    }

    private void updateBullets() {
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.move();
            if (b.x < 0 || b.x > getWidth() || b.y < 0 || b.y > getHeight()) {
                it.remove();
            }
        }
    }

    private void spawnEnemies() {
        if (rand.nextInt(40) == 0) {
            int side = rand.nextInt(4);
            int x = 0, y = 0;
            switch (side) {
                case 0 -> { x = rand.nextInt(getWidth()); y = 0; }           // top
                case 1 -> { x = getWidth(); y = rand.nextInt(getHeight()); } // right
                case 2 -> { x = rand.nextInt(getWidth()); y = getHeight(); } // bottom
                case 3 -> { x = 0; y = rand.nextInt(getHeight()); }          // left
            }
            enemies.add(new Enemy(x, y));
        }
    }

    private void updateEnemies() {
        for (Enemy e : enemies) {
            e.moveToward(playerX, playerY);
        }
    }

    private void checkCollisions() {
        Iterator<Bullet> bulletIt = bullets.iterator();
        while (bulletIt.hasNext()) {
            Bullet b = bulletIt.next();
            Iterator<Enemy> enemyIt = enemies.iterator();
            while (enemyIt.hasNext()) {
                Enemy e = enemyIt.next();
                if (b.getBounds().intersects(e.getBounds())) {
                    bulletIt.remove();
                    enemyIt.remove();
                    score += 10;
                    break;
                }
            }
        }

        for (Enemy e : enemies) {
            if (e.getBounds().intersects(new Rectangle(playerX, playerY, playerSize, playerSize))) {
                gameOver = true;
                timer.stop();
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Player (triangle spaceship)
        g2.setColor(Color.CYAN);
        int[] px = {playerX, playerX + playerSize / 2, playerX + playerSize};
        int[] py = {playerY + playerSize, playerY, playerY + playerSize};
        g2.fillPolygon(px, py, 3);

        // Bullets
        g2.setColor(Color.YELLOW);
        for (Bullet b : bullets)
            g2.fillOval(b.x, b.y, b.size, b.size);

        // Enemies
        g2.setColor(Color.GREEN);
        for (Enemy e : enemies)
            g2.fillOval(e.x, e.y, e.size, e.size);

        // HUD
        g2.setColor(Color.WHITE);
        g2.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2);
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Final Score: " + score, getWidth() / 2 - 60, getHeight() / 2 + 40);
        }
    }

    // --- Key handling ---
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = true;
            case KeyEvent.VK_S -> down = true;
            case KeyEvent.VK_A -> left = true;
            case KeyEvent.VK_D -> right = true;
            case KeyEvent.VK_UP -> shootUp = true;
            case KeyEvent.VK_DOWN -> shootDown = true;
            case KeyEvent.VK_LEFT -> shootLeft = true;
            case KeyEvent.VK_RIGHT -> shootRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> up = false;
            case KeyEvent.VK_S -> down = false;
            case KeyEvent.VK_A -> left = false;
            case KeyEvent.VK_D -> right = false;
            case KeyEvent.VK_UP -> shootUp = false;
            case KeyEvent.VK_DOWN -> shootDown = false;
            case KeyEvent.VK_LEFT -> shootLeft = false;
            case KeyEvent.VK_RIGHT -> shootRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    // --- Main ---
    public static void main(String[] args) {
        JFrame frame = new JFrame("Top Down Shooter");
        TopDownShooter game = new TopDownShooter();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
