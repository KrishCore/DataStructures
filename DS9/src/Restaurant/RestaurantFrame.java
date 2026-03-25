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

    private ArrayList<FoodItem> order = new ArrayList<>();
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
            panels.add(new JPanel(), "Home");


            JPanel p_home = new JPanel(null);

            welcome.setBounds(0, 0, getWidth() - 40, 50);
            welcome.setHorizontalAlignment(JLabel.CENTER);
            welcome.setFont(medium);

            title.setBounds(0, 20, getWidth() - 40, 100);
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setFont(Xbig);

            p_home.add(welcome, BorderLayout.CENTER);
            p_home.add(title, BorderLayout.CENTER);
            panels.add(p_home, "Home");

            CardLayout cl = (CardLayout) panels.getLayout();
            cl.show(panels, "Home");
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

        //Entrees
        {
            menu.add(mi_entrees);
            //add entrees
            {
                a_entrees.add(new FoodItem("Northwest Chicken Plate", 20.99));
                a_entrees.add(new FoodItem("Fried Chicken Chest", 19.99));
                a_entrees.add(new FoodItem("Wall Street Shrimp and Chicken", 21.99));
                a_entrees.add(new FoodItem("The Steak Dinner Mr. Modi has been looking forward to", 31.99));
                a_entrees.add(new FoodItem("Fried Chicken Julius Salad", 25.99));
                a_entrees.add(new FoodItem("", 11.99));
                a_entrees.add(new FoodItem("", 11.99));
                a_entrees.add(new FoodItem("", 11.99));
                a_entrees.add(new FoodItem("", 11.99));
                a_entrees.add(new FoodItem("", 11.99));
                a_entrees.add(new FoodItem("", 11.99));
                a_entrees.add(new FoodItem("", 11.99));
            }

            // set up
            {
                p_entrees.setLayout(new BoxLayout(p_entrees, BoxLayout.Y_AXIS));

                Color c1 = Color.WHITE;
                Color c2 = new Color(230, 230, 230);

                for (int i = 0; i < a_entrees.size(); i++) {
                    Color bg = (i % 2 == 0) ? c1 : c2;
                    p_entrees.add(createFoodPanel(a_entrees.get(i), bg));
                }
                JScrollPane scrollA = new JScrollPane(p_entrees);

                JPanel wrapper = new JPanel(new BorderLayout());
                wrapper.add(scrollA, BorderLayout.CENTER);
                panels.add(wrapper, "Entrees");
            }

            mi_entrees.addActionListener(e -> {
                CardLayout cl = (CardLayout) panels.getLayout();
                cl.show(panels, "Entrees");
                panels.revalidate();
                panels.repaint();
            });
        }

        //Deserts
        {
            menu.add(mi_desserts);
            //add deserts
            {
                a_desserts.add(new FoodItem("Mega Cinnabon® Eddies", 7.99));
                a_desserts.add(new FoodItem("Reese's Pieces Shake", 4.89));
                a_desserts.add(new FoodItem("Quadruple Vanilla Anxieties", 11.99));
                a_desserts.add(new FoodItem("Whitie Nibble", 6.99));
            }

            // set up
            {
                p_desserts.setLayout(new BoxLayout(p_desserts, BoxLayout.Y_AXIS));

                Color c1 = Color.WHITE;
                Color c2 = new Color(230, 230, 230);

                for (int i = 0; i < a_desserts.size(); i++) {
                    Color bg = (i % 2 == 0) ? c1 : c2;
                    p_desserts.add(createFoodPanel(a_desserts.get(i), bg));
                }
                JScrollPane scrollA = new JScrollPane(p_desserts);

                JPanel wrapper = new JPanel(new BorderLayout());
                wrapper.add(scrollA, BorderLayout.CENTER);
                panels.add(wrapper, "Desserts");
            }

            mi_desserts.addActionListener(e -> {
                CardLayout cl = (CardLayout) panels.getLayout();
                cl.show(panels, "Desserts");
                panels.revalidate();
                panels.repaint();
            });
        }




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

        JTextField num = new JTextField("0");
        num.setBounds(300, 20, 50, 50);
        num.setDisabledTextColor(Color.BLACK);
        num.setEnabled(false);
        num.setHorizontalAlignment(JTextField.CENTER);

        JButton add = new JButton("+");
        add.setBounds(360, 20, 50, 50);

        JButton remove = new JButton("-");
        remove.setBounds(240, 20, 50, 50);
        remove.setEnabled(false);

        add.addActionListener(e -> {
            int n = Integer.parseInt(num.getText()) + 1;
            num.setText(n + "");
            remove.setEnabled(true);
            order.add(item);
            System.out.println(order);
        });

        remove.addActionListener(e -> {
            int n = Integer.parseInt(num.getText()) - 1;
            num.setText(n + "");
            if (n == 0)
                remove.setEnabled(false);
            order.remove(item);
            System.out.println(order);
        });

        panel.add(name);
        panel.add(price);
        panel.add(num);
        panel.add(add);
        panel.add(remove);

        return panel;
    }
}
