package WumpusWorld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WumpusFrame extends JFrame
{

    public WumpusFrame()
    {
        super("Wumpus World");
        System.out.println(getWidth());
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        WumpusPanel panel = new WumpusPanel();
        panel.setPreferredSize(new Dimension(580, 750));
        add(panel);
        pack();
        //resizer - component listener   may not be needed (code at the end)
        {
            Timer gameLoop = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    System.out.println("action perfomed-------------------------");
                    int status = panel.getStatus();
                    Color current = getContentPane().getBackground();
                    if (status == 1 && current != Color.RED)
                        getContentPane().setBackground(Color.RED);
                    else if (status == 2 && current != Color.GREEN)
                        getContentPane().setBackground(Color.GREEN);
                    else if (status == 0 && current != Color.WHITE)
                        getContentPane().setBackground(Color.WHITE);
                }
            });
            gameLoop.start();
        }
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
