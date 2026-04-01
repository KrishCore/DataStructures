package WumpusWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WumpusWorldFrame extends JFrame implements KeyListener
{
    private BufferedImage arrow = getScaledImage("src\\WumpusWorld\\Images\\arrow.gif",100, 100);
    private BufferedImage black = getScaledImage("src\\WumpusWorld\\Images\\black.GIF", 100, 100);
    private BufferedImage breeze = getScaledImage("src\\WumpusWorld\\Images\\breeze.gif", 100, 100);
    private BufferedImage deadWumpus = getScaledImage("src\\WumpusWorld\\Images\\deadwumpus.GIF", 100, 100);
    private BufferedImage flooor = getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", 100, 100);
    private JPanel p_floor = new JPanel();
    private BufferedImage gold = getScaledImage("src\\WumpusWorld\\Images\\gold.gif", 100, 100);
    private BufferedImage ladder = getScaledImage("src\\WumpusWorld\\Images\\ladder.gif", 100, 100);
    private BufferedImage picture1 = getScaledImage("src\\WumpusWorld\\Images\\Picture1.gif", 100, 100);
    private BufferedImage pit = getScaledImage("src\\WumpusWorld\\Images\\pit.gif", 100, 100);
    private BufferedImage playerDown = getScaledImage("src\\WumpusWorld\\Images\\playerDown.png", 100, 100);
    private BufferedImage playerLeft = getScaledImage("src\\WumpusWorld\\Images\\playerLeft.png", 100, 100);
    private BufferedImage playerRight = getScaledImage("src\\WumpusWorld\\Images\\playerRight.png", 100, 100);
    private BufferedImage playerUp = getScaledImage("src\\WumpusWorld\\Images\\playerUp.png", 100, 100);
    private BufferedImage stench = getScaledImage("src\\WumpusWorld\\Images\\stench.gif", 100, 100);
    private BufferedImage wumpus = getScaledImage("src\\WumpusWorld\\Images\\wumpus.gif", 100, 100);

    public WumpusWorldFrame() throws IOException {
        super("Wumpus World");
        System.out.println(getWidth());
        setSize(520+12,520+35);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                p_floor.setBounds(0, 0, getWidth()-12, getHeight()-35);
                System.out.println("P_flooor" + p_floor.getWidth() + "  :  " + p_floor.getHeight());
                flooor = getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", (p_floor.getWidth()-0)/5, (p_floor.getHeight()-0)/5);
                System.out.println("flooor" + flooor.getWidth() + "  :  " + flooor.getHeight());
            }
        });
        setLayout(null);
        p_floor.setLayout(new GridLayout(5, 5, 5, 5));
        p_floor.setBounds(0, 0, getWidth()-12, getHeight()-35);
        p_floor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        p_floor.setBackground(Color.BLACK);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                p_floor.add(new JLabel(flooor)); //change all of Buffered IMages into Image Icons
        add(p_floor);

        setVisible(true);
    }

    private BufferedImage getScaledImage (String path, int width, int height)
    {
        try {
            BufferedImage og = ImageIO.read(new File(path));
            Image scaled = og.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bi.createGraphics();
            g.drawImage(scaled, 0, 0, null);
            g.dispose();
            return bi;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
