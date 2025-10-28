import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdGame extends JFrame {
    public FlappyBirdGame() {
        setTitle("Flappy Bird");
        setSize(500, 700);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        FlappyPanel flappyPanel = new FlappyPanel();
        add(flappyPanel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlappyBirdGame::new);
    }
}

class FlappyPanel extends JPanel implements ActionListener, KeyListener, MouseListener {
    private final int WIDTH = 500;
    private final int HEIGHT = 700;
    private final int BIRD_SIZE = 30;
    private final int PIPE_WIDTH = 80;
    private final int PIPE_GAP = 200;
    private final int FLAP_STRENGTH = -13;
    private final int GRAVITY = 1;

    private int birdX = 150;
    private int birdY = HEIGHT / 2;
    private int velocity = 0;
    private boolean gameOver = false;
    private int score = 0;

    private Timer gameTimer;
    private Timer countdownTimer;
    private ArrayList<Pipe> pipes = new ArrayList<>();
    private Random rand = new Random();

    private int countdown = 3;
    private boolean countingDown = true;

    public FlappyPanel() {
        setBackground(Color.CYAN);
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        gameTimer = new Timer(20, this);
        startCountdown();
    }

    private void startCountdown() {
        birdY = HEIGHT / 2;
        velocity = 0;
        gameOver = false;
        score = 0;
        pipes.clear();
        countingDown = true;
        countdown = 3;

        for (int i = 0; i < 3; i++) {
            addPipe(WIDTH + i * 200);
        }

        countdownTimer = new Timer(1000, e -> {
            countdown--;
            if (countdown < 0) {
                countingDown = false;
                countdownTimer.stop();
                gameTimer.start();
            }
            repaint();
        });
        countdownTimer.start();
    }

    private void addPipe(int x) {
        int height = 100 + rand.nextInt(300);
        pipes.add(new Pipe(x, 0, PIPE_WIDTH, height));
        pipes.add(new Pipe(x, height + PIPE_GAP, PIPE_WIDTH, HEIGHT - height - PIPE_GAP));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw bird
        int birdCenterX = birdX + BIRD_SIZE / 2;
        int birdCenterY = birdY + BIRD_SIZE / 2;
        g.setColor(Color.YELLOW);
        g.fillOval(birdX, birdY, BIRD_SIZE, BIRD_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(birdX, birdY, BIRD_SIZE, BIRD_SIZE);

        // Beak
        int[] xPoints = {birdX + BIRD_SIZE, birdX + BIRD_SIZE + 10, birdX + BIRD_SIZE};
        int[] yPoints = {birdCenterY - 5, birdCenterY, birdCenterY + 5};
        g.setColor(Color.ORANGE);
        g.fillPolygon(xPoints, yPoints, 3);

        // Pipes
        g.setColor(new Color(34, 139, 34));
        for (Pipe p : pipes) {
            g.fillRect(p.x, p.y, p.width, p.height);
        }

        // Score
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(score), WIDTH / 2 - 10, 50);

        // Countdown text
        if (countingDown) {
            g.setFont(new Font("Arial", Font.BOLD, 72));
            g.setColor(Color.BLACK);
            String text = countdown > 0 ? String.valueOf(countdown) : "Start!";
            drawCenteredString(g, text, WIDTH, HEIGHT / 2);
        }

        if (gameOver) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            drawCenteredString(g, "Game Over", WIDTH, HEIGHT / 2 - 50);
            g.setFont(new Font("Arial", Font.PLAIN, 28));
            drawCenteredString(g, "Press SPACE or Click to Restart", WIDTH, HEIGHT / 2 + 10);
        }
    }

    private void drawCenteredString(Graphics g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !countingDown) {
            velocity += GRAVITY;
            birdY += velocity;

            for (int i = 0; i < pipes.size(); i++) {
                Pipe p = pipes.get(i);
                p.x -= 5;

                if (p.x + PIPE_WIDTH == birdX && p.y == 0) {
                    score++;
                }
            }

            if (!pipes.isEmpty() && pipes.get(0).x + PIPE_WIDTH < 0) {
                pipes.remove(0);
                pipes.remove(0);
                addPipe(WIDTH);
            }

            for (Pipe p : pipes) {
                if (new Rectangle(birdX, birdY, BIRD_SIZE, BIRD_SIZE).intersects(
                        new Rectangle(p.x, p.y, p.width, p.height))) {
                    gameOver = true;
                    gameTimer.stop();
                }
            }

            if (birdY > HEIGHT || birdY < 0) {
                gameOver = true;
                gameTimer.stop();
            }

            repaint();
        }
    }

    private void flap() {
        if (gameOver) {
            startCountdown();
        } else if (!countingDown) {
            velocity = FLAP_STRENGTH;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            flap();
        }
    }
    @Override public void keyReleased(KeyEvent e) {}

    @Override public void mouseClicked(MouseEvent e) {
        flap();
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

class Pipe {
    int x, y, width, height;

    public Pipe(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
