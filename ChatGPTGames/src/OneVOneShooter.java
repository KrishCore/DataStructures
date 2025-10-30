import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class OneVOneShooter extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Player player1, player2;
    private ArrayList<Bullet> bullets;
    private boolean gameOver = false;
    private String winner = "";

    public OneVOneShooter() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        bullets = new ArrayList<>();

        player1 = new Player(100, 250, Color.CYAN, "Player 1");
        player2 = new Player(650, 250, Color.RED, "Player 2");

        timer = new Timer(16, this); // ~60 FPS
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            player1.update();
            player2.update();

            updateBullets();
            checkCollisions();

            if (player1.health <= 0) {
                gameOver = true;
                winner = player2.name;
            }
            if (player2.health <= 0) {
                gameOver = true;
                winner = player1.name;
            }
        }
        repaint();
    }

    // ----- Game Logic -----
    private void updateBullets() {
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            b.move();

            // Remove if off-screen
            if (b.x < 0 || b.x > getWidth() || b.y < 0 || b.y > getHeight()) {
                it.remove();
            }
        }
    }

    private void checkCollisions() {
        Iterator<Bullet> it = bullets.iterator();
        while (it.hasNext()) {
            Bullet b = it.next();
            if (b.shooter == player1 && b.getBounds().intersects(player2.getBounds())) {
                player2.health -= 10;
                it.remove();
            } else if (b.shooter == player2 && b.getBounds().intersects(player1.getBounds())) {
                player1.health -= 10;
                it.remove();
            }
        }
    }

    // ----- Rendering -----
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Players
        player1.draw(g2);
        player2.draw(g2);

        // Bullets
        for (Bullet b : bullets) b.draw(g2);

        // HUD
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Consolas", Font.BOLD, 18));
        g2.drawString(player1.name + " HP: " + player1.health, 20, 20);
        g2.drawString(player2.name + " HP: " + player2.health, getWidth() - 180, 20);

        if (gameOver) {
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("🏆 " + winner + " WINS!", getWidth() / 2 - 150, getHeight() / 2);
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.drawString("Press R to Restart", getWidth() / 2 - 90, getHeight() / 2 + 40);
        }
    }

    // ----- Player Class -----
    class Player {
        int x, y, w = 40, h = 40, speed = 5, health = 100;
        Color color;
        String name;
        boolean up, down, left, right;
        long lastShotTime = 0;

        Player(int x, int y, Color color, String name) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.name = name;
        }

        void update() {
            if (up && y > 0) y -= speed;
            if (down && y + h < getHeight()) y += speed;
            if (left && x > 0) x -= speed;
            if (right && x + w < getWidth()) x += speed;
        }

        void draw(Graphics2D g2) {
            g2.setColor(color);
            g2.fillRect(x, y, w, h);
        }

        Rectangle getBounds() {
            return new Rectangle(x, y, w, h);
        }

        void shoot(int dx, int dy) {
            long now = System.currentTimeMillis();
            if (now - lastShotTime > 250) { // 4 shots per second
                bullets.add(new Bullet(x + w / 2, y + h / 2, dx, dy, this));
                lastShotTime = now;
            }
        }
    }

    // ----- Bullet Class -----
    class Bullet {
        int x, y, size = 8, speed = 8;
        int dx, dy;
        Player shooter;

        Bullet(int x, int y, int dx, int dy, Player shooter) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.shooter = shooter;
        }

        void move() {
            x += dx * speed;
            y += dy * speed;
        }

        void draw(Graphics2D g2) {
            g2.setColor(Color.YELLOW);
            g2.fillOval(x, y, size, size);
        }

        Rectangle getBounds() {
            return new Rectangle(x, y, size, size);
        }
    }

    // ----- Keyboard Controls -----
    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        // Player 1 controls (WASD + SPACE)
        if (k == KeyEvent.VK_W) player1.up = true;
        if (k == KeyEvent.VK_S) player1.down = true;
        if (k == KeyEvent.VK_A) player1.left = true;
        if (k == KeyEvent.VK_D) player1.right = true;
        if (k == KeyEvent.VK_SPACE) player1.shoot(1, 0); // shoots right

        // Player 2 controls (Arrow keys + ENTER)
        if (k == KeyEvent.VK_UP) player2.up = true;
        if (k == KeyEvent.VK_DOWN) player2.down = true;
        if (k == KeyEvent.VK_LEFT) player2.left = true;
        if (k == KeyEvent.VK_RIGHT) player2.right = true;
        if (k == KeyEvent.VK_ENTER) player2.shoot(-1, 0); // shoots left

        // Restart
        if (gameOver && k == KeyEvent.VK_R) resetGame();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();

        if (k == KeyEvent.VK_W) player1.up = false;
        if (k == KeyEvent.VK_S) player1.down = false;
        if (k == KeyEvent.VK_A) player1.left = false;
        if (k == KeyEvent.VK_D) player1.right = false;

        if (k == KeyEvent.VK_UP) player2.up = false;
        if (k == KeyEvent.VK_DOWN) player2.down = false;
        if (k == KeyEvent.VK_LEFT) player2.left = false;
        if (k == KeyEvent.VK_RIGHT) player2.right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void resetGame() {
        player1 = new Player(100, 250, Color.CYAN, "Player 1");
        player2 = new Player(650, 250, Color.RED, "Player 2");
        bullets.clear();
        winner = "";
        gameOver = false;
        timer.start();
    }

    // ----- Main -----
    public static void main(String[] args) {
        JFrame frame = new JFrame("1v1 Shooter");
        OneVOneShooter game = new OneVOneShooter();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
