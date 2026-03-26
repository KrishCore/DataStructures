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
        {
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
        }

        //Menu Bar
        {
            mb.setBounds(0, 0, getWidth(), getHeight());
            //menu
            {
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
                        scrollA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollA.setWheelScrollingEnabled(true);

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

                //Entrees - needs work
                {
                    menu.add(mi_entrees);
                    //add entrees
                    {
                        a_entrees.add(new FoodItem("Northwest Chicken Plate", 26.99));
                        a_entrees.add(new FoodItem("Fried Chicken Chest", 19.99));
                        a_entrees.add(new FoodItem("Wall Street Shrimp and Chicken", 23.99));
                        a_entrees.add(new FoodItem("Mr. Modi's Robotic Steak Dinner", 31.99));
                        a_entrees.add(new FoodItem("Fried Chicken Julius Salad", 22.99));
                        a_entrees.add(new FoodItem("Whitened Chicken Farfalle", 21.99));
                        a_entrees.add(new FoodItem("Cold Syrup Glazed Chicken Skillet", 18.99));
                        a_entrees.add(new FoodItem("Creek/Catchment Chicken Pot", 27.99));
                        a_entrees.add(new FoodItem("Half Lotta Chicken Burger", 22.99));
                        a_entrees.add(new FoodItem("", 28.99));
                        a_entrees.add(new FoodItem("", 25.99));
                        a_entrees.add(new FoodItem("", 23.99));
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
                        scrollA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollA.setWheelScrollingEnabled(true);

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
                        scrollA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollA.setWheelScrollingEnabled(true);

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
            }

            //cart
            {
                mb.add(cart);
                String[][] items = new String[1][4];
                table = new JTable(items, headings);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setWheelScrollingEnabled(true);
                cart.addActionListener(e -> {

                });
            }

            //pay
            {
                mb.add(pay);
                pay.addActionListener(e -> {

                });
            }
        }
        setVisible(true);
    }

    private JPanel createFoodPanel(FoodItem item, Color bg) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(700, 100));
        panel.setBackground(bg);

        JLabel name = new JLabel(item.getName());
        name.setBounds(10, 10, 400, 20);

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

    private void updateCardTable()
    {
        ArrayList<String[]> rows = new ArrayList<>();

        for (FoodItem f : a_appetizers)
        {
            rows.add(new String[]{f.getName(), f.getPrice()+""});
        }
    }
}
