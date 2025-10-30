import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SpaceShooterGame extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private int playerX = 280, playerY = 440;
    private int playerWidth = 40, playerHeight = 50;
    private int playerSpeed = 8;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean spacePressed = false;

    private ArrayList<Rectangle> bullets;
    private ArrayList<Rectangle> enemies;
    private Random rand = new Random();

    private int score = 0;
    private boolean gameOver = false;

    public SpaceShooterGame() {
        setPreferredSize(new Dimension(600, 500));
        setBackground(Color.BLACK);

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();

        timer = new Timer(16, this); // ~60 FPS
        timer.start();

        setFocusable(true);
        addKeyListener(this);
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
        if (leftPressed && playerX > 0)
            playerX -= playerSpeed;
        if (rightPressed && playerX + playerWidth < getWidth())
            playerX += playerSpeed;
    }

    private void updateBullets() {
        // Faster shooting: every frame while space is held
        if (spacePressed && rand.nextInt(4) == 0) {
            bullets.add(new Rectangle(playerX + playerWidth / 2 - 3, playerY - 10, 6, 15));
        }

        Iterator<Rectangle> it = bullets.iterator();
        while (it.hasNext()) {
            Rectangle bullet = it.next();
            bullet.y -= 12; // bullet speed
            if (bullet.y < 0)
                it.remove();
        }
    }

    private void spawnEnemies() {
        // Spawn less frequently
        if (rand.nextInt(40) == 0) {
            int x = rand.nextInt(getWidth() - 40);
            enemies.add(new Rectangle(x, 0, 50, 30)); // wider, flatter UFO shape
        }
    }

    private void updateEnemies() {
        Iterator<Rectangle> it = enemies.iterator();
        while (it.hasNext()) {
            Rectangle enemy = it.next();
            enemy.y += 2; // slower alien speed
            if (enemy.y > getHeight()) {
                gameOver = true;
                timer.stop();
            }
        }
    }

    private void checkCollisions() {
        Iterator<Rectangle> bulletIt = bullets.iterator();
        while (bulletIt.hasNext()) {
            Rectangle bullet = bulletIt.next();
            Iterator<Rectangle> enemyIt = enemies.iterator();
            while (enemyIt.hasNext()) {
                Rectangle enemy = enemyIt.next();
                if (bullet.intersects(enemy)) {
                    bulletIt.remove();
                    enemyIt.remove();
                    score += 10;
                    break;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw player spaceship (triangle)
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        int[] xPoints = { playerX, playerX + playerWidth / 2, playerX + playerWidth };
        int[] yPoints = { playerY + playerHeight, playerY, playerY + playerHeight };
        g2d.fillPolygon(xPoints, yPoints, 3);

        // Bullets
        g2d.setColor(Color.YELLOW);
        for (Rectangle b : bullets)
            g2d.fillRect(b.x, b.y, b.width, b.height);

        // Aliens (UFO-like ovals)
        g2d.setColor(Color.GREEN);
        for (Rectangle e : enemies) {
            g2d.fillOval(e.x, e.y, e.width, e.height);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(e.x + 10, e.y + 5, e.width - 20, e.height - 10);
            g2d.setColor(Color.GREEN);
        }

        // HUD
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Consolas", Font.PLAIN, 16));
        g2d.drawString("Score: " + score, 10, 20);

        if (gameOver) {
            g2d.setFont(new Font("Arial", Font.BOLD, 36));
            g2d.drawString("GAME OVER", getWidth() / 2 - 120, getHeight() / 2);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.drawString("Final Score: " + score, getWidth() / 2 - 60, getHeight() / 2 + 40);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = true;
            case KeyEvent.VK_RIGHT -> rightPressed = true;
            case KeyEvent.VK_SPACE -> spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> leftPressed = false;
            case KeyEvent.VK_RIGHT -> rightPressed = false;
            case KeyEvent.VK_SPACE -> spacePressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Space Shooter");
        SpaceShooterGame game = new SpaceShooterGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
