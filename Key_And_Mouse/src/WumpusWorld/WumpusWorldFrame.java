package WumpusWorld;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WumpusWorldFrame extends JFrame implements KeyListener
{
    private ImageIcon arrow = new ImageIcon("src\\WumpusWorld\\Images\\arrow.gif");
    private ImageIcon flooor = new ImageIcon("src\\WumpusWorld\\Images\\Floor.gif");
    public WumpusWorldFrame()
    {
        super("Wumpus World");
        setSize(500,500);
        setLayout(null);
        JPanel p_floor = new JPanel();
        p_floor.setBounds(0, 0, getWidth(), getHeight());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) { // doesn't wrok
                JLabel l = new JLabel(flooor);
                l.setBounds(i*20, j*20,100,100);
                p_floor.add(l);
            }
        }
        add(p_floor);

        setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
