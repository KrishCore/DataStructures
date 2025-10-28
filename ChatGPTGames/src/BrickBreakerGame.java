import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BrickBreakerGame extends JFrame {

    public BrickBreakerGame() {
        setTitle("Brick Breaker");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BrickBreakerGame();
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener, MouseMotionListener {

    private Timer timer;
    private Timer countdownTimer;

    private final int WIDTH = 600;
    private final int HEIGHT = 500;

    private int paddleX = 250;
    private final int paddleY = 450;
    private final int paddleWidth = 100;
    private final int paddleHeight = 10;

    private int ballX;
    private int ballY;
    private final int ballSize = 15;
    private int ballXDir = -2;
    private int ballYDir = -3;

    private boolean[][] bricks;
    private final int brickRows = 4;
    private final int brickCols = 8;
    private final int brickWidth = 60;
    private final int brickHeight = 20;

    private boolean playing = false;
    private boolean won = false;

    private int countdown = 3;
    private boolean countingDown = true;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        addMouseMotionListener(this);
        initBricks();

        resetBallOnPaddle();

        timer = new Timer(10, this);

        countdownTimer = new Timer(1000, e -> {
            countdown--;
            if (countdown < 0) {
                countingDown = false;
                playing = true;
                countdownTimer.stop();
                timer.start();
            }
            repaint();
        });

        countdownTimer.start();
    }

    private void initBricks() {
        bricks = new boolean[brickRows][brickCols];
        for (int i = 0; i < brickRows; i++)
            for (int j = 0; j < brickCols; j++)
                bricks[i][j] = true;
    }

    private boolean allBricksDestroyed() {
        for (int i = 0; i < brickRows; i++)
            for (int j = 0; j < brickCols; j++)
                if (bricks[i][j]) return false;
        return true;
    }

    private void resetBallOnPaddle() {
        ballX = paddleX + paddleWidth / 2 - ballSize / 2;
        ballY = paddleY - ballSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw paddle
        g.setColor(Color.GREEN);
        g.fillRect(paddleX, paddleY, paddleWidth, paddleHeight);

        // Draw ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Draw bricks
        g.setColor(Color.RED);
        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickCols; j++) {
                if (bricks[i][j]) {
                    int brickX = j * (brickWidth + 10) + 35;
                    int brickY = i * (brickHeight + 10) + 50;
                    g.fillRect(brickX, brickY, brickWidth, brickHeight);
                }
            }
        }

        // Countdown text
        if (countingDown) {
            g.setFont(new Font("Arial", Font.BOLD, 72));
            g.setColor(Color.WHITE);
            String text = countdown > 0 ? String.valueOf(countdown) : "Start!";
            drawCenteredString(g, text, WIDTH, HEIGHT / 2);
        }

        // Game over screen
        if (!playing && !won && !countingDown) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over", WIDTH / 2 - 80, HEIGHT / 2);
        }

        // You Win screen
        if (won) {
            g.setColor(Color.CYAN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("You Win!", WIDTH / 2 - 90, HEIGHT / 2);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press SPACE to play again", WIDTH / 2 - 120, HEIGHT / 2 + 40);
        }
    }

    private void drawCenteredString(Graphics g, String text, int width, int y) {
        FontMetrics fm = g.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (playing) {
            // Ball movement
            ballX += ballXDir;
            ballY += ballYDir;

            // Wall collision
            if (ballX <= 0 || ballX >= WIDTH - ballSize)
                ballXDir = -ballXDir;
            if (ballY <= 0)
                ballYDir = -ballYDir;

            // Paddle collision
            Rectangle ball = new Rectangle(ballX, ballY, ballSize, ballSize);
            Rectangle paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
            if (ball.intersects(paddle)) {
                // Calculate where the ball hit the paddle to change x velocity accordingly
                int paddleCenter = paddleX + paddleWidth / 2;
                int ballCenter = ballX + ballSize / 2;
                int distanceFromCenter = ballCenter - paddleCenter;

                // Max deflection angle in terms of x velocity (adjust as needed)
                int maxDeflection = 5;

                // Set ball X direction based on where it hit the paddle
                ballXDir = (distanceFromCenter * maxDeflection) / (paddleWidth / 2);
                if (ballXDir == 0) ballXDir = 1; // prevent straight vertical bounce

                ballYDir = -ballYDir;
                ballY = paddleY - ballSize; // prevent sticking
            }

            // Brick collision
            outer:
            for (int i = 0; i < brickRows; i++) {
                for (int j = 0; j < brickCols; j++) {
                    if (bricks[i][j]) {
                        int brickX = j * (brickWidth + 10) + 35;
                        int brickY = i * (brickHeight + 10) + 50;
                        Rectangle brick = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        if (ball.intersects(brick)) {
                            bricks[i][j] = false;
                            ballYDir = -ballYDir;
                            break outer;
                        }
                    }
                }
            }

            // Check win condition
            if (allBricksDestroyed()) {
                playing = false;
                won = true;
                timer.stop();
            }

            // Missed paddle = game over
            if (ballY > HEIGHT) {
                playing = false;
                won = false;
                timer.stop();
            }

            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (!countingDown) {
            if ((code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) && paddleX > 0) {
                paddleX -= 20;
            } else if ((code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) && paddleX < WIDTH - paddleWidth) {
                paddleX += 20;
            } else if (code == KeyEvent.VK_SPACE && !playing) {
                // Restart game and countdown
                playing = false;
                won = false;
                paddleX = 250;
                initBricks();

                countdown = 3;
                countingDown = true;
                resetBallOnPaddle();
                countdownTimer.start();
                repaint();
            }
        }
        // If counting down, ignore key movement so paddle doesn't move
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!countingDown) {
            paddleX = e.getX() - paddleWidth / 2;

            if (paddleX < 0) paddleX = 0;
            if (paddleX > WIDTH - paddleWidth) paddleX = WIDTH - paddleWidth;
        }
        // If counting down, ignore mouse movement so paddle stays still
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

}
