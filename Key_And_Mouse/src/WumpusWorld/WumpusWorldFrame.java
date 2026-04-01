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
    private ImageIcon arrow = getScaledImage("src\\WumpusWorld\\Images\\arrow.gif",100, 100);
    private ImageIcon black = getScaledImage("src\\WumpusWorld\\Images\\black.GIF", 100, 100);
    private ImageIcon breeze = getScaledImage("src\\WumpusWorld\\Images\\breeze.gif", 100, 100);
    private ImageIcon deadWumpus = getScaledImage("src\\WumpusWorld\\Images\\deadwumpus.GIF", 100, 100);
    private ImageIcon flooor = getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", 100, 100);
    private JPanel p_floor = new JPanel();
    private ImageIcon gold = getScaledImage("src\\WumpusWorld\\Images\\gold.gif", 100, 100);
    private ImageIcon ladder = getScaledImage("src\\WumpusWorld\\Images\\ladder.gif", 100, 100);
    private ImageIcon picture1 = getScaledImage("src\\WumpusWorld\\Images\\Picture1.gif", 100, 100);
    private ImageIcon pit = getScaledImage("src\\WumpusWorld\\Images\\pit.gif", 100, 100);
    private ImageIcon playerDown = getScaledImage("src\\WumpusWorld\\Images\\playerDown.png", 100, 100);
    private ImageIcon playerLeft = getScaledImage("src\\WumpusWorld\\Images\\playerLeft.png", 100, 100);
    private ImageIcon playerRight = getScaledImage("src\\WumpusWorld\\Images\\playerRight.png", 100, 100);
    private ImageIcon playerUp = getScaledImage("src\\WumpusWorld\\Images\\playerUp.png", 100, 100);
    private ImageIcon stench = getScaledImage("src\\WumpusWorld\\Images\\stench.gif", 100, 100);
    private ImageIcon wumpus = getScaledImage("src\\WumpusWorld\\Images\\wumpus.gif", 100, 100);

    public WumpusWorldFrame() throws IOException {
        super("Wumpus World");
        System.out.println(getWidth());
        setSize(520+12,520+35);
        //resizer - component listener
        {
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    p_floor.setBounds(0, 0, getWidth()-25, getHeight() - 50); // this needs to be changed
                    System.out.println("P_flooor" + p_floor.getWidth() + "  :  " + p_floor.getHeight());
                    flooor = getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", (p_floor.getWidth() - 20) / 5, (p_floor.getHeight() - 20) / 5);
                    System.out.println("flooor" + flooor.getIconWidth() + "  :  " + flooor.getIconHeight());
                    Graphics floorG = p_floor.getGraphics();// = this.getGraphics();
                    paint(floorG);
                }
            });
        }
        setLayout(null);
//        flooor = ImageIO.read(new File("src\\WumpusWorld\\Images\\Floor.gif"));
        getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", 100, 100);
        p_floor.setLayout(new GridLayout(5, 5, 6, 5));
        p_floor.setBounds(0, 0, getWidth()-12, getHeight()-35);
        p_floor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        p_floor.setBackground(Color.BLACK);
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                p_floor.add(new JLabel(flooor)); //change all of Buffered IMages into Image Icons
        add(p_floor);

        setVisible(true);
    }

    private ImageIcon getScaledImage (String path, int width, int height)
    {
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

    }

//    public void paint(Graphics g)
//    {
//        Graphics floorG = flooor.getGraphics();
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, getWidth(), getHeight());
//
//        g.drawImage(flooor.getImage(), 5, 5, null);
//    }

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
