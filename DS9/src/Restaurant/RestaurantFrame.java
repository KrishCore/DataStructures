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
    private Font food = new Font(Font.SERIF, Font.BOLD, 25);
    private Font pr = new Font(Font.SERIF, Font.BOLD, 20);

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
    private ArrayList<FoodItem> arrAppetizers = new ArrayList<>();
    private ArrayList<String> a_des = new ArrayList<>();
    private ArrayList<ImageIcon> a_image = new ArrayList<>();

    private JPanel p_entrees = new JPanel();
    private ArrayList<FoodItem> arrEntrees = new ArrayList<>();
    private ArrayList<String> e_des = new ArrayList<>();
    private ArrayList<ImageIcon> e_image = new ArrayList<>();

    private JPanel p_desserts = new JPanel();
    private ArrayList<FoodItem> arrDesserts = new ArrayList<>();
    private ArrayList<String> d_des = new ArrayList<>();
    private JPanel panels = new JPanel();
    private ArrayList<ImageIcon> d_image = new ArrayList<>();

    private ArrayList<FoodItem> order = new ArrayList<>();
    public RestaurantFrame()
    {
        super("Orangebee's");
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
                        arrAppetizers.add(new FoodItem("Foreign-Imported Nachos", 8.99, 0));
                        arrAppetizers.add(new FoodItem("Mini Cheese Straws", 7.99, 0));
                        arrAppetizers.add(new FoodItem("The 1968 Trio", 21.99, 0));
                        arrAppetizers.add(new FoodItem("Toaststicks with Sweet and Sour", 9.99, 0));
                        arrAppetizers.add(new FoodItem("Soggy Onion Rings", 6.99, 0));
                        arrAppetizers.add(new FoodItem("Fried Pigeon Wings", 11.99, 0));

                        a_des.add("Freshly made white corn tortilla chips are topped with grilled chicken, queso blanco, a blend of melted Cheddar cheeses, house-made pico de gallo, fresh jalapeños, chopped cilantro, sour cream and guacamole.");
                        a_des.add("Crispy outside with melty Mozzarella inside, this favorite is served with marinara sauce.");
                        a_des.add("Get ready to build your Ultimate Trio! Choose 3 appetizers from a mouthwatering selection of 10 and pair them with 3 irresistible dipping sauces. With endless combinations, every bite is a new adventure!");
                        a_des.add("Five golden brown signature breadsticks brushed with buttery garlic and parsley. Served with creamy Alfredo sauce for dipping.");
                        a_des.add(" Crispy, golden-fried rings with a savory flavor, served with Ranch and Honey BBQ sauce. ");
                        a_des.add("Crispy breaded pieces of tender boneless chicken tossed in your choice of sauce. Served with Bleu cheese or house-made ranch buttermilk ranch dressings and celery.");

                        a_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\Foriegn-Imported Nachos.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        System.out.println(a_image.get(0));
                    }

                    // set up
                    {
                        p_appetizers.setLayout(new BoxLayout(p_appetizers, BoxLayout.Y_AXIS));

                        Color c1 = Color.WHITE;
                        Color c2 = new Color(230, 230, 230);

                        for (int i = 0; i < arrAppetizers.size(); i++) {
                            Color bg = (i % 2 == 0) ? c1 : c2;
                            // add an image
                            p_appetizers.add(createFoodPanel(arrAppetizers.get(i), bg, a_image.get(0)));
                        }
                        JScrollPane scrollA = new JScrollPane(p_appetizers);
                        scrollA.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                        scrollA.setWheelScrollingEnabled(true);
                        scrollA.getHorizontalScrollBar().setUnitIncrement(16);
                        scrollA.setAutoscrolls(true);

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
                        arrEntrees.add(new FoodItem("Northwest Chicken Plate", 26.99, 0));
                        arrEntrees.add(new FoodItem("Fried Chicken Chest", 19.99, 0));
                        arrEntrees.add(new FoodItem("Wall Street Shrimp and Chicken", 23.99, 0));
                        arrEntrees.add(new FoodItem("Mr. Modi's Robotic Steak Dinner", 31.99, 0));
                        arrEntrees.add(new FoodItem("Fried Chicken Julius Salade", 22.99, 0));
                        arrEntrees.add(new FoodItem("Whitened Chicken Farfalle", 21.99, 0));
                        arrEntrees.add(new FoodItem("Cold Syrup Glazed Chicken Skillet", 18.99, 0));
                        arrEntrees.add(new FoodItem("Creek/Catchment Chicken Pot", 27.99, 0));
                        arrEntrees.add(new FoodItem("Half Lotta Chicken Burger", 22.99, 0));
                        arrEntrees.add(new FoodItem("Fried Occidental Chicken Herba Salata", 28.99, 0));
                        arrEntrees.add(new FoodItem("Raspberry-Pi Vinegar Chicken Gobbledygook", 25.99, 0));
                        arrEntrees.add(new FoodItem("Chicken Farm Chicken Grinder", 23.99, 0));
                    }

                    // set up
                    {
                        p_entrees.setLayout(new BoxLayout(p_entrees, BoxLayout.Y_AXIS));

                        Color c1 = Color.WHITE;
                        Color c2 = new Color(230, 230, 230);

                        for (int i = 0; i < arrEntrees.size(); i++) {
                            Color bg = (i % 2 == 0) ? c1 : c2;
                            p_entrees.add(createFoodPanel(arrEntrees.get(i), bg, null));
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
                        arrDesserts.add(new FoodItem("Mega Cinnabon® Eddies", 7.99, 0));
                        arrDesserts.add(new FoodItem("Reese's Pieces Shake", 4.89, 0));
                        arrDesserts.add(new FoodItem("Quadruple Vanilla Anxieties", 11.99, 0));
                        arrDesserts.add(new FoodItem("Whitie Nibble", 6.99, 0));
                    }

                    // set up
                    {
                        p_desserts.setLayout(new BoxLayout(p_desserts, BoxLayout.Y_AXIS));

                        Color c1 = Color.WHITE;
                        Color c2 = new Color(230, 230, 230);

                        for (int i = 0; i < arrDesserts.size(); i++) {
                            Color bg = (i % 2 == 0) ? c1 : c2;
                            p_desserts.add(createFoodPanel(arrDesserts.get(i), bg, null));
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

    private JPanel createFoodPanel(FoodItem item, Color bg, ImageIcon image) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(700, 150));
        panel.setBackground(bg);

        JLabel name = new JLabel(item.getName());
        name.setBounds(10, 10, 400, 40);
        name.setFont(food);

        JLabel price = new JLabel("$" + item.getPrice());
        price.setBounds(10, 45, 100, 20);
        price.setFont(pr);

        JLabel i = new JLabel(image);
        i.setBounds(645, 25, 100, 100);
        i.setVisible(true);
        panel.add(i);

        JTextField num = new JTextField("0");
        num.setBounds(300, 50, 50, 50);
        num.setDisabledTextColor(Color.BLACK);
        num.setEnabled(false);
        num.setHorizontalAlignment(JTextField.CENTER);

        JButton add = new JButton("+");
        add.setBounds(360, 50, 50, 50);

        JButton remove = new JButton("-");
        remove.setBounds(240, 50, 50, 50);
        remove.setEnabled(false);

        add.addActionListener(e -> {
            int q = Integer.parseInt(num.getText()) + 1;
            item.setQuantity(q);
            num.setText(q + "");
            remove.setEnabled(true);
            if (!order.contains(item))
                order.add(item);
            System.out.println(order);
        });

        remove.addActionListener(e -> {
            int q = Integer.parseInt(num.getText()) - 1;
            item.setQuantity(q);
            num.setText(q + "");
            if (q == 0)
                remove.setEnabled(false);
            if (q == 0)
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

        for (FoodItem f : arrAppetizers) //add appetizers
        {
            rows.add(new String[]{f.getName(), f.getQuantity()+"", f.getPrice()+"", f.getQuantity()*f.getPrice()+""});
        }

        for (FoodItem f : arrEntrees) //add entrees
        {
            rows.add(new String[]{f.getName(), f.getQuantity()+"", f.getPrice()+"", f.getQuantity()*f.getPrice()+""});
        }

        for (FoodItem f : arrDesserts) //add desserts
        {
            rows.add(new String[]{f.getName(), f.getQuantity()+"", f.getPrice()+"", f.getQuantity()*f.getPrice()+""});
        }
    }
}