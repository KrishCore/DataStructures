package WumpusWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class WumpusPanel extends JPanel //implements KeyListener
{
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private boolean toggleCheat = false;
    private Color bgColor = Color.DARK_GRAY;
    private boolean wumpusKilled = false;

    private BufferedImage arrow = getScaledImage("src\\WumpusWorld\\Images\\arrow.gif");
    private BufferedImage black = getScaledImage("src\\WumpusWorld\\Images\\black.GIF");
    private BufferedImage breeze = getScaledImage("src\\WumpusWorld\\Images\\breeze.gif");
    private BufferedImage deadWumpus = getScaledImage("src\\WumpusWorld\\Images\\deadwumpus.GIF");
    private BufferedImage flooor = getScaledImage("src\\WumpusWorld\\Images\\Floor.gif");
    private BufferedImage gold = getScaledImage("src\\WumpusWorld\\Images\\gold.gif");
    private BufferedImage ladder = getScaledImage("src\\WumpusWorld\\Images\\ladder.gif");
    private BufferedImage picture1 = getScaledImage("src\\WumpusWorld\\Images\\Picture1.gif");
    private BufferedImage pit = getScaledImage("src\\WumpusWorld\\Images\\pit.gif");
    private BufferedImage playerDown = getScaledImage("src\\WumpusWorld\\Images\\playerDown.png");
    private BufferedImage playerLeft = getScaledImage("src\\WumpusWorld\\Images\\playerLeft.png");
    private BufferedImage playerRight = getScaledImage("src\\WumpusWorld\\Images\\playerRight.png");
    private BufferedImage playerUp = getScaledImage("src\\WumpusWorld\\Images\\playerUp.png");
    private BufferedImage stench = getScaledImage("src\\WumpusWorld\\Images\\stench.gif");
    private BufferedImage wumpus = getScaledImage("src\\WumpusWorld\\Images\\wumpus.gif");

    BufferedImage buffer;

    public WumpusPanel()
    {
        setSize(580, 750);
        buffer = new BufferedImage(580, 750, BufferedImage.TYPE_INT_ARGB);
        map = new WumpusMap();
        reset();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e)
            {
                System.out.println("KeyTyped");
                int code = e.getKeyCode();
                char c = e.getKeyChar();
                System.out.println(c);

                //movement
                {
                    if (status == PLAYING && code == KeyEvent.VK_W) //up (w)
                    {
                        player.setRowPosition(player.getRowPosition() - 1);
                        player.setDirection(WumpusPlayer.NORTH);
                        map.getWumpusSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    }
                    if (status == PLAYING && code == KeyEvent.VK_A) //left (a)
                    {
                        player.setColPosition(player.getColPosition() - 1);
                        player.setDirection(WumpusPlayer.WEST);
                        map.getWumpusSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    }
                    if (status == PLAYING && code == KeyEvent.VK_S) //down (s)
                    {
                        player.setRowPosition(player.getRowPosition() + 1);
                        player.setDirection(WumpusPlayer.SOUTH);
                        map.getWumpusSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    }
                    if (status == PLAYING && code == KeyEvent.VK_D) //right (d)
                    {
                        player.setColPosition(player.getColPosition() + 1);
                        player.setDirection(WumpusPlayer.EAST);
                        map.getWumpusSquare(player.getRowPosition(), player.getColPosition()).setVisited(true);
                    }
                }

                //arrow
                {
                    if (status == PLAYING && code == KeyEvent.VK_I && player.hasArrow()) //up arrow
                    {
                        System.out.println("shoot arrow up");
                        for (int i = 0; i < player.getRowPosition(); i++) {
                            WumpusSquare ws = map.getWumpusSquare(player.getRowPosition() - i, player.getColPosition());
                            if (ws.isWumpus()) {
                                ws.setWumpus(false);
                                ws.setDeadWumpus(true);
                                wumpusKilled = true;
                                System.out.println("You hear a scream " + ws.isDeadWumpus());
                            }
                        }
                        player.setDirection(WumpusPlayer.NORTH);
                        player.setArrow(false);
                    }
                    if (status == PLAYING && code == KeyEvent.VK_J && player.hasArrow()) //left arrow
                    {
                        System.out.println("shoot arrow left");
                        for (int i = 0; i < player.getColPosition(); i++) {
                            WumpusSquare ws = map.getWumpusSquare(player.getRowPosition(), player.getColPosition()-i);
                            if (ws.isWumpus()) {
                                ws.setWumpus(false);
                                ws.setDeadWumpus(true);
                                wumpusKilled = true;
                                System.out.println("You hear a scream " + ws.isDeadWumpus());
                            }
                        }
                        player.setDirection(WumpusPlayer.WEST);
                        player.setArrow(false);
                    }
                    if (status == PLAYING && code == KeyEvent.VK_K && player.hasArrow()) //down arrow
                    {
                        System.out.println("shoot arrow down");
                        for (int i = 0; i < WumpusMap.NUM_ROWS-player.getRowPosition(); i++) {
                            WumpusSquare ws = map.getWumpusSquare(player.getRowPosition() + i, player.getColPosition());
                            if (ws.isWumpus()) {
                                ws.setWumpus(false);
                                ws.setDeadWumpus(true);
                                wumpusKilled = true;
                                System.out.println("You hear a scream " + ws.isDeadWumpus());
                            }
                        }
                        player.setDirection(WumpusPlayer.SOUTH);
                        player.setArrow(false);
                    }
                    if (status == PLAYING && code == KeyEvent.VK_L && player.hasArrow()) //right arrow
                    {
                        System.out.println("shoot arrow right");
                        for (int i = 0; i < WumpusMap.NUM_COLUMNS-player.getColPosition(); i++) {
                            WumpusSquare ws = map.getWumpusSquare(player.getRowPosition(), player.getColPosition() + i);
                            if (ws.isWumpus()) {
                                ws.setWumpus(false);
                                ws.setDeadWumpus(true);
                                wumpusKilled = true;
                                System.out.println("You hear a scream " + ws.isDeadWumpus());
                            }
                        }
                        player.setDirection(WumpusPlayer.EAST);
                        player.setArrow(false);
                    }
                }

                WumpusSquare sq = map.getWumpusSquare(player.getRowPosition(), player.getColPosition());
                //toggle cheat
                {
                    if (c == '*') {
                        toggleCheat = !toggleCheat;
                        System.out.println("You turned cheat on! " + toggleCheat);
                        repaint();
                    }
                }
                //check death
                {
                    if (sq.isPit()) {
                        status = DEAD;
                        System.out.println("You fell down a pit to your death");
                    } else if (sq.isWumpus()) {
                        status = DEAD;
                        System.out.println("You are eaten by the Wumpus");
                    }
                }
                //other actions
                {
                    if (sq.isLadder())
                        System.out.println("You bump into a ladder");
                    if (sq.isBreeze())
                        System.out.println("You feel a breeze");
                    if (sq.isStench() || sq.isDeadWumpus())
                        System.out.println("You smell a stench");
                    if (sq.isGold()) {
                        System.out.println("You see a glimmer");
                        if (code == KeyEvent.VK_P)
                            player.setGold(true);
                    }
                }
                //check win
                {
                    if (player.hasGold() && sq.isLadder() && code == KeyEvent.VK_C) {
                        System.out.println("You won!!");
                        status = WON;
                    }
                }
                //new game
                {
                    if (status != PLAYING && code == KeyEvent.VK_N) {
                        map = new WumpusMap();
                        status = PLAYING;
                        reset();
                        System.out.println("New game\n----------------------");
                    }
                }

                repaint();
                System.out.println(map.toString());
                //handles all the player inputs
            }
        });
    }

    private void reset()
    {
        status = PLAYING;
        map = new WumpusMap();
        player = new WumpusPlayer();
        player.setRowPosition(map.getLadderR());
        player.setColPosition(map.getLadderC());
        player.setArrow(true);
        toggleCheat = false;
        wumpusKilled = false;
        for (int i = 0; i < WumpusMap.NUM_ROWS; i++)
            for (int j = 0; j < WumpusMap.NUM_COLUMNS; j++)
                map.getWumpusSquare(i, j).setVisited(false);
        WumpusSquare sq = map.getWumpusSquare(player.getRowPosition(), player.getColPosition());
        sq.setVisited(true);
        if (sq.isBreeze())
            System.out.println("You feel a breeze");
        if (sq.isStench() || sq.isDeadWumpus())
            System.out.println("You smell a stench");
        if (sq.isLadder())
            System.out.println("You bump into a ladder");
    }

    public void paint(Graphics g)
    {
        Graphics2D gd = buffer.createGraphics();

        gd.setColor(bgColor);
        gd.fillRect(0, 0, getWidth(), getHeight());//-50);
        gd.setColor(Color.BLUE);
        gd.setStroke(new BasicStroke(15));
        gd.drawRect(0, 0, getWidth(), getHeight());

        //grid
        {
            for (int i = 0; i < WumpusMap.NUM_ROWS; i++) {
                for (int j = 0; j < WumpusMap.NUM_COLUMNS; j++) {
                    int x = j*50+40;
                    int y = i*50+20;
                    WumpusSquare wsq = map.getWumpusSquare(i, j);

                    //floor
                    {
                        gd.drawImage(flooor, x, y, 50, 50, null);
                        //fog of war
                        {
                            if (wsq.isVisited())
                            {
                                if (wsq.isPit()) gd.drawImage(pit, x, y, 50, 50, null);
                                if (!player.hasGold() && wsq.isGold()) gd.drawImage(gold, x, y, 50, 50, null);
                                if (wsq.isLadder()) gd.drawImage(ladder, x, y, 50, 50, null);
                                if (wsq.isWumpus()) gd.drawImage(wumpus, x, y, 50, 50, null);
                                if (wsq.isDeadWumpus()) gd.drawImage(deadWumpus, x, y, 50, 50, null);

                                if (wsq.isStench() && !wsq.isPit()) gd.drawImage(stench, x, y, 50, 50, null);
                                if (wsq.isBreeze() && !wsq.isPit()) gd.drawImage(breeze, x, y, 50, 50, null);
                            }
                            else gd.drawImage(black, x, y, 50, 50, null);
                        }

                        //cheat
                        {
                            if (toggleCheat && !wsq.isVisited())
                            {
                                gd.drawImage(flooor, x, y, 50, 50, null);
                                if (wsq.isPit()) gd.drawImage(pit, x, y, 50, 50, null);
                                if (!player.hasGold() && wsq.isGold()) gd.drawImage(gold, x, y, 50, 50, null);
                                if (wsq.isLadder()) gd.drawImage(ladder, x, y, 50, 50, null);
                                if (wsq.isWumpus()) gd.drawImage(wumpus, x, y, 50, 50, null);
                                if (wsq.isDeadWumpus()) gd.drawImage(deadWumpus, x, y, 50, 50, null);

                                if (wsq.isStench() && !wsq.isPit()) gd.drawImage(stench, x, y, 50, 50, null);
                                if (wsq.isBreeze() && !wsq.isPit()) gd.drawImage(breeze, x, y, 50, 50, null);
                            }
                        }
                    }
                }
            }
            //player
            {
                int px = player.getColPosition() * 50 + 40;
                int py = player.getRowPosition() * 50 + 20;
                BufferedImage image = playerUp;
                if (player.getDirection() == WumpusPlayer.NORTH) image = playerUp;
                if (player.getDirection() == WumpusPlayer.WEST) image = playerLeft;
                if (player.getDirection() == WumpusPlayer.SOUTH) image = playerDown;
                if (player.getDirection() == WumpusPlayer.EAST) image = playerRight;
                gd.drawImage(image, px, py, 50, 50, null);
            }
        }

        //inventory and messages
        {
            //inventory
            {
                gd.setColor(Color.BLACK);
                gd.fillRect(40, 560, 190, 140);
                gd.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25));
                gd.setColor(Color.RED);
                gd.drawString("Inventory:", 50, 590);
                if (player.hasArrow())
                    gd.drawImage(arrow, 50, 610, 65, 65, null);
                if (player.hasGold())
                    gd.drawImage(gold, 125, 615, 65, 65, null);
            }

            //messages
            {
                gd.setColor(Color.BLACK);
                gd.fillRect(230, 560, 310, 140);
                gd.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25));
                gd.setColor(Color.RED);
                gd.drawString("Messages:", 230, 590);

                ArrayList<String> messages = getStrings(map.getWumpusSquare(player.getRowPosition(), player.getColPosition()));
                System.out.println(messages);

                gd.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
                gd.setColor(Color.WHITE);
                int space = 0;
                for (String message : messages) {
                    gd.drawString(message, 230, 620 + space);
                    space += 20;
                }

                String[] mes = messages.toString().substring(1, messages.toString().length()-1).split(", "); //why - may not be needed
                System.out.println(Arrays.toString(mes));                                                          //why - may not be needed

            }
            gd.setColor(bgColor);
            gd.setStroke(new BasicStroke(.100f));
            gd.drawLine(215, 560, 215, 700);
        }

        g.drawImage(buffer, 0, 0, null);
        gd.dispose();

    }

    private ArrayList<String> getStrings(WumpusSquare sq) {
        ArrayList<String> messages = new ArrayList<>();
        //messages
        {
            if (status == PLAYING && sq.isLadder())
                messages.add("You bump into a ladder");
            if (status == PLAYING && wumpusKilled) {
                messages.add("You hear a scream.");
                wumpusKilled = false;
            }
            if (status == PLAYING && sq.isBreeze())
                messages.add("You feel a breeze.");
            if (status == PLAYING && sq.isStench() || sq.isDeadWumpus())
                messages.add("You smell a stench.");
            if (status == PLAYING && sq.isGold() && !player.hasGold())
                messages.add("You see a glimmer.");

            if (status == WON)
                messages.add("You Win. (N for new game)");
            if (sq.isPit())
                messages.add("You fell down a pit to your death.");
            if (sq.isWumpus())
                messages.add("You are eaten by the Wumpus.");
            if (status == DEAD)
                messages.add("Game Over. (N for new game)");
        }
        return messages;
    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//
//    }
//
//
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }

    public void addNotify()
    {
        super.addNotify();
        requestFocus();
    }

    public int getStatus() {
        return status;
    }

    private BufferedImage getScaledImage (String path)
    {
        try {
            BufferedImage og = ImageIO.read(new File(path));
            Image scaled = og.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            BufferedImage bi = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D gd = bi.createGraphics();
            gd.drawImage(scaled, 0, 0, null);
            gd.dispose();
            return bi;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
