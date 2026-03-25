package Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantFrame extends JFrame
{
    private JLabel welcome = new JLabel("Welcome to");
    private JLabel title = new JLabel("Orangebee's");
    private Font Xbig = new Font(Font.SERIF, Font.BOLD, 70);
    private Font medium = new Font(Font.SERIF, Font.BOLD, 30);
    private Font headers = new Font(Font.SERIF, Font.BOLD, 40);

    private JMenuBar mb = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenuItem mi_appetizers = new JMenuItem("Appetizers");
    private JMenuItem mi_entrees = new JMenuItem("Entrees");
    private JMenuItem mi_desserts = new JMenuItem("Desserts");

    private JMenu pay = new JMenu("Pay");

    private JMenu cart = new JMenu("View Cart");
    private JTable table = new JTable();
    private String[] headings = new String[] {"Item Name", "Quantity", "Cost", "Extended Cost"};

    private JPanel mainScreen = new JPanel();
    private JPanel p_appetizers = new JPanel();
    private ArrayList<FoodItem> a_appetizers = new ArrayList<>();
    private JPanel p_entrees = new JPanel();
    private ArrayList<FoodItem> a_entrees= new ArrayList<>();
    private JPanel p_desserts = new JPanel();
    private ArrayList<FoodItem> a_desserts = new ArrayList<>();
    private JPanel panels = new JPanel();
    public RestaurantFrame()
    {
        super("Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(800, 500);

        //panels
        {
            panels.setBounds(0,0,getWidth()-12, getHeight()-57);
            panels.setLayout(new CardLayout());
            add(panels);
            panels.add(new JPanel(), "Appetizers");
            panels.add(new JPanel(), "Entrees");
            panels.add(new JPanel(), "Desserts");
        }

        //welcome and title
        { //error in this area
            JPanel p_home = new JPanel(null);

            welcome.setBounds(0, 0, getWidth() - 40, 50);
            welcome.setHorizontalAlignment(JLabel.CENTER);
            welcome.setFont(medium);

            title.setBounds(0, 20, getWidth() - 40, 100);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(Xbig);

            p_home.add(welcome);
            p_home.add(title);

            panels.add(p_home, "Home");

        } //after clicking Appetizers, the screen goes blank

        mb.setBounds(0, 0 ,getWidth(), getHeight());
        mb.add(menu);
        mb.setVisible(true);
        setJMenuBar(mb);

        //appetizer
        {
            menu.add(mi_appetizers);
            //add appetizers
            {
                a_appetizers.add(new FoodItem("Foreign-Imported Nachos", 8.99));
                a_appetizers.add(new FoodItem("Mini Cheese Straws", 7.99));
                a_appetizers.add(new FoodItem("The 1968 Trio", 21.99));
                a_appetizers.add(new FoodItem("Toaststicks with Sweet and Sour", 9.99));
                a_appetizers.add(new FoodItem("Soggy Onion Rings", 6.99));
                a_appetizers.add(new FoodItem("Fried Pigeon Wings", 11.99));
            }

            // set up
            {
                p_appetizers.setLayout(new BoxLayout(p_appetizers, BoxLayout.Y_AXIS));

                Color c1 = Color.WHITE;
                Color c2 = new Color(230, 230, 230);

                for (int i = 0; i < a_appetizers.size(); i++) {
                    Color bg = (i % 2 == 0) ? c1 : c2;
                    p_appetizers.add(createFoodPanel(a_appetizers.get(i), bg));
                }
                JScrollPane scrollA = new JScrollPane(p_appetizers);

                JPanel wrapper = new JPanel(new BorderLayout());
                wrapper.add(scrollA, BorderLayout.CENTER);
                panels.add(wrapper, "Appetizers");
            }

            mi_appetizers.addActionListener(e -> {
                CardLayout cl = (CardLayout) panels.getLayout();
                cl.show(panels, "Appetizers");
                panels.revalidate();
                panels.repaint();
            });
        }


        menu.add(mi_entrees);
        mi_entrees.addActionListener(e -> {
            CardLayout cl = (CardLayout) panels.getLayout();
            cl.show(panels, "Entrees");
        });

        menu.add(mi_desserts);
        mi_desserts.addActionListener(e -> {
            CardLayout cl = (CardLayout) panels.getLayout();
            cl.show(panels, "Desserts");
        });

        mb.add(cart);
        cart.addActionListener(e -> {

        });

        mb.add(pay);
        pay.addActionListener(e -> {

        });
        setVisible(true);
    }

    private JPanel createFoodPanel(FoodItem item, Color bg) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(700, 100));
        panel.setBackground(bg);

        JLabel name = new JLabel(item.getName());
        name.setBounds(10, 10, 200, 20);

        JLabel price = new JLabel("$" + item.getPrice());
        price.setBounds(10, 40, 100, 20);

        JLabel num = new JLabel("0");
        num.setBounds(250, 20, 30, 20);

        JButton add = new JButton("+");
        add.setBounds(300, 20, 50, 30);

        JButton remove = new JButton("-");
        remove.setBounds(360, 20, 50, 30);
        remove.setEnabled(false);

        add.addActionListener(e -> {
            int n = Integer.parseInt(num.getText()) + 1;
            num.setText(n + "");
            remove.setEnabled(true);
        });

        remove.addActionListener(e -> {
            int n = Integer.parseInt(num.getText()) - 1;
            num.setText(n + "");
            if (n == 0)
                remove.setEnabled(false);
        });

        panel.add(name);
        panel.add(price);
        panel.add(num);
        panel.add(add);
        panel.add(remove);

        return panel;
    }
}
