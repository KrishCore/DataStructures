package WumpusWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WumpusPanel extends JPanel implements KeyListener
{
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;

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

    BufferedImage buffer;

    public WumpusPanel()
    {
        setSize(100, 700);
        addKeyListener(this);
        buffer = new BufferedImage(1000, 700, BufferedImage.TYPE_INT_ARGB);
        map = new WumpusMap();
        reset();
    }

    private void reset()
    {
        status = PLAYING;
        map = new WumpusMap();
        player = new WumpusPlayer();
        player.setRowPosition(map.getLadderR());
        player.setColPosition(map.getLadderC());
    }

    public void paint(Graphics g)
    {
        Graphics2D gd = buffer.createGraphics();

        gd.setColor(Color.BLACK);
        gd.fillRect(0, 0, getWidth(), getHeight());

        //grid
        {
            for (int i = 0; i < WumpusMap.NUM_ROWS; i++) {
                for (int j = 0; j < WumpusMap.NUM_ROWS; j++) {
                    int x = j * 50;
                    int y = i * 50;
                    WumpusSquare wsq = map.getWumpusSquare(j, i);

                    //floor
                    {
                        gd.drawImage(flooor, x, y, 50, 50, null);
                        //cheat
                        {
                            if (wsq.isPit()) gd.drawImage(pit, x, y, 50, 50, null);
                            if (wsq.isGold()) gd.drawImage(gold, x, y, 50, 50, null);
                            if (wsq.isLadder()) gd.drawImage(ladder, x, y, 50, 50, null);
                            if (wsq.isWumpus()) gd.drawImage(wumpus, x, y, 50, 50, null);
                            if (wsq.isDeadWumpus()) gd.drawImage(deadWumpus, x, y, 50, 50, null);
                        }
                    }
                }
            }
        }

        //player
        {
            int px = player.getColPosition()*50;
            int py = player.getRowPosition()*50;

            gd.drawImage(playerUp, px, py,50, 50, null);
            g.drawImage(buffer, 0, 0, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (status != PLAYING)
            return;

        char c = e.getKeyChar();

        //set the player directoin (the picture) ex: playerRight
        //find out how to do it
        if (c == 'W') {
            player.setRowPosition(player.getRowPosition() + 1);
            player.setDirection(WumpusPlayer.NORTH);

        }
        if (c == 'A') {
            player.setColPosition(player.getColPosition() - 1);
            player.setDirection(WumpusPlayer.EAST);
        }
        if (c == 'S') {
            player.setRowPosition(player.getRowPosition() - 1);
            player.setDirection(WumpusPlayer.SOUTH);
        }
        if (c == 'D') {
            player.setColPosition(player.getColPosition() + 1);
            player.setDirection(WumpusPlayer.EAST);
        }

        // check death
        WumpusSquare sq = map.getWumpusSquare(player.getColPosition(), player.getRowPosition());

        if (sq.isPit()) {
            status = DEAD;
            System.out.println("You fell into a pit");
        }
        else if (sq.isWumpus()) {
            status = DEAD;
            System.out.println("You got eaten");
        }

        repaint();
        //handles all the player inputs
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
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
}
