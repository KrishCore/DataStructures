package Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantFrame extends JFrame
{
    private JLabel welcome = new JLabel("Welcome to");
    private JLabel title = new JLabel("Orangebee's");
    private Font tf = new Font(Font.SERIF, Font.BOLD, 70);
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
    private ArrayList<FoodItem> a_dessers = new ArrayList<>();
    public RestaurantFrame()
    {
        super("Restaurant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(800, 500);

        welcome.setBounds(0, 0, 40, 100); // wrok from here
        title.setBounds(0, 0, getWidth()-40, 100);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(tf);
        add(title);

        mb.setBounds(0, 0 ,getWidth(), getHeight());
        mb.add(menu);
        mb.setVisible(true);
        setJMenuBar(mb);
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


        mi_appetizers.addActionListener(e -> {
            p_appetizers.setLayout(null);
            p_appetizers.setBackground(Color.WHITE);
            p_appetizers.setBounds(0, 0, getWidth(), getHeight());
            p_appetizers.setVisible(false);
            add(p_appetizers);
            p_appetizers.setVisible(true);
        });
        menu.add(mi_entrees);
        mi_entrees.addActionListener(e -> {

        });
        menu.add(mi_desserts);
        mi_desserts.addActionListener(e -> {

        });

        mb.add(cart);
        cart.addActionListener(e -> {

        });

        mb.add(pay);
        pay.addActionListener(e -> {

        });
        setVisible(true);
    }
}
