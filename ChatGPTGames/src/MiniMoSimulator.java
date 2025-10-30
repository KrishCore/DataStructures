import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniMoSimulator extends JPanel implements KeyListener {

    private int x = 200;
    private int y = 200;
    private int angle = 0; // degrees
    private final int SPEED = 5;

    public MiniMoSimulator() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw robot as a rectangle
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angle));
        g2d.setColor(Color.RED);
        g2d.fillRect(-20, -10, 40, 20);
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-x, -y);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> {
                x += SPEED * Math.cos(Math.toRadians(angle));
                y += SPEED * Math.sin(Math.toRadians(angle));
            }
            case KeyEvent.VK_DOWN -> {
                x -= SPEED * Math.cos(Math.toRadians(angle));
                y -= SPEED * Math.sin(Math.toRadians(angle));
            }
            case KeyEvent.VK_LEFT -> angle -= 5;
            case KeyEvent.VK_RIGHT -> angle += 5;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini MoSimulator");
        MiniMoSimulator simulator = new MiniMoSimulator();
        frame.add(simulator);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
