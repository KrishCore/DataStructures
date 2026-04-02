package WumpusWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WumpusFrame extends JFrame
{

    public WumpusFrame() throws IOException {
        super("Wumpus World");
        System.out.println(getWidth());
        setSize(520+12,720+35);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new WumpusPanel();
        add(panel);
        //resizer - component listener   may not be needed (code at the end)
        {
//            addComponentListener(new ComponentAdapter() {
//                @Override
//                public void componentResized(ComponentEvent e) {
//                    super.componentResized(e);
//                    p_floor.setBounds(0, 0, getWidth() == 0 ? 0 : getWidth()-25, getHeight() == 0 ? 0 : getHeight() - 250); // this needs to be changed
//                    p_floor.setBackground(Color.BLACK);
//                    System.out.println("P_flooor " + p_floor.getWidth() + "  :  " + p_floor.getHeight());
//                    flooor = getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", (p_floor.getWidth() - 20) / 10, (p_floor.getHeight() - 20) / 10);
//                    System.out.println("flooor " + flooor.getWidth() + "  :  " + flooor.getHeight());
//                    Graphics floorG = p_floor.getGraphics();// = this.getGraphics();
//                    paint(floorG);
//                }
//            });
        }
//        flooor = ImageIO.read(new File("src\\WumpusWorld\\Images\\Floor.gif"));
//        getScaledImage("src\\WumpusWorld\\Images\\Floor.gif", 100, 100);
//        p_floor.setLayout(new GridLayout(5, 5, 6, 5));
//        p_floor.setBounds(0, 0, getWidth()-12, getHeight()-35);
//        p_floor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
//        p_floor.setBackground(Color.BLACK);
//        for (int i = 0; i < 5; i++)
//            for (int j = 0; j < 5; j++)
////                p_floor.add(flooor); //change all of Buffered IMages into Image Icons
//        add(p_floor);

        setVisible(true);
    }
}
