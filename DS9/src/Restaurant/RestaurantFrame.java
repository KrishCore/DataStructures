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
    private ArrayList<String> app_des = new ArrayList<>();
    private ArrayList<ImageIcon> app_image = new ArrayList<>();

    private JPanel p_entrees = new JPanel();
    private ArrayList<FoodItem> arrEntrees = new ArrayList<>();
    private ArrayList<String> ent_description = new ArrayList<>();
    private ArrayList<ImageIcon> ent_image = new ArrayList<>();

    private JPanel p_desserts = new JPanel();
    private ArrayList<FoodItem> arrDesserts = new ArrayList<>();
    private ArrayList<String> des_description = new ArrayList<>();
    private JPanel panels = new JPanel();
    private ArrayList<ImageIcon> des_image = new ArrayList<>();

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
                        app_des.add("<html>Freshly made white corn tortilla chips are topped with grilled chicken, queso blanco, a blend of melted Cheddar cheeses, house-made pico de gallo, fresh jalapeños, chopped cilantro, sour cream and guacamole.</html>");
                        app_des.add("<html>Crispy outside with melty Mozzarella inside, this favorite is served with marinara sauce.</html>");
                        app_des.add("<html>Get ready to build your Ultimate Trio! Choose 3 appetizers from a mouthwatering selection of 10 and pair them with 3 irresistible dipping sauces. With endless combinations, every bite is a new adventure!</html>");
                        app_des.add("<html>Five golden brown signature breadsticks brushed with buttery garlic and parsley. Served with creamy Alfredo sauce for dipping.</html>");
                        app_des.add("<html>Crispy, golden-fried rings with a savory flavor, served with Ranch and Honey BBQ sauce.</html>");
                        app_des.add("<html>Crispy breaded pieces of tender boneless chicken tossed in your choice of sauce. Served with Bleu cheese or house-made ranch buttermilk ranch dressings and celery.</html>");

                        app_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\Foriegn-Imported Nachos.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        app_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\Mini Cheese Straws.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        app_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\The 1968 Trio.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        app_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\Toatsticks with SAS.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        app_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\Soggy Onion Rings.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        app_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Appetizers\\Fried Pigeon Wings.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

                        arrAppetizers.add(new FoodItem("Foreign-Imported Nachos", 8.99, 0, app_des.get(0), app_image.get(0)));
                        arrAppetizers.add(new FoodItem("Mini Cheese Straws", 7.99, 0, app_des.get(1), app_image.get(1)));
                        arrAppetizers.add(new FoodItem("The 1968 Trio", 21.99, 0, app_des.get(2), app_image.get(2)));
                        arrAppetizers.add(new FoodItem("Toaststicks with Sweet and Sour", 9.99, 0, app_des.get(3), app_image.get(3)));
                        arrAppetizers.add(new FoodItem("Soggy Onion Rings", 6.99, 0, app_des.get(4), app_image.get(4)));
                        arrAppetizers.add(new FoodItem("Fried Pigeon Wings", 11.99, 0, app_des.get(5), app_image.get(5)));
                    }

                    // set up
                    {
                        p_appetizers.setLayout(new BoxLayout(p_appetizers, BoxLayout.Y_AXIS));

                        Color c1 = Color.WHITE;
                        Color c2 = new Color(230, 230, 230);

                        for (int i = 0; i < arrAppetizers.size(); i++) {
                            Color bg = (i % 2 == 0) ? c1 : c2;
                            // add an image
                            p_appetizers.add(createFoodPanel(arrAppetizers.get(i), bg, arrAppetizers.get(i).getDescription(), arrAppetizers.get(i).getImage()));
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
                        ent_description.add("<html>Grilled chipotle lime chicken on fresh greens and cilantro rice with house-made pico de gallo, black bean corn salsa and guacamole. Topped with chimichurri, tortilla strips and  a fresh lime wedge.");
                        ent_description.add("<html>Juicy chicken breast seasoned and grilled over an open flame. Served with your choice of two sides.</html>");
                        ent_description.add("<html>Cajun-seasoned chicken and blackened shrimp jazzed up in buttery garlic and parsley, served sizzling with sautéed mushrooms & onions and garlic mashed potatoes.</html>");
                        ent_description.add("<html>Juicy, perfectly grilled steak served with garlic mashed potatoes and steamed broccoli, because sometimes being a little late comes with a delicious reward!</html>");
                        ent_description.add("<html>Crisp romaine tossed in garlic Caesar dressing topped with grilled chicken, croutons and shaved Parmesan. Served with a golden brown signature breadstick brushed with buttery garlic and parsley.</html>");
                        ent_description.add("<html>Crispy, chicken breast filet, smothered in marinara sauce and topped with Parmesan cheese on a bed of fettuccine pasta in a Parmesan cream sauce. Served with a golden-brown signature breadstick with buttery garlic and parsley.</html>");
                        ent_description.add("<html>Sliced grilled chicken breast topped with hot honey glaze and parsley. Served with sautéed mushrooms & onions and waffle fries.</html>");
                        ent_description.add("<html>Savor the Bayou with Bourbon Street chicken, Spanish rice, garlicky green beans, sautéed onions and peppers with a side of creamy Cajun sauce.</html>");
                        ent_description.add("<html>A little sweet, a little spicy. Crispy, hand-breaded chicken breast smothered in Sweet Asian Chile sauce with our signature coleslaw on a Brioche bun. Served with classic fries.</html>");
                        ent_description.add("<html>A long-running favorite, grilled chicken top a bed of fresh Asian greens, crunchy noodles and almonds tossed in our Oriental vinaigrette. Served with a golden brown signature breadstick brushed with buttery garlic and parsley.</html>");
                        ent_description.add("<html>Tart, fresh and flavorful, fresh greens served with garden tomatoes, red onions and fresh strawberries. Topped with grilled chicken, sliced almonds and a drizzle of balsamic glaze and sweet lemon olive vinaigrette on the side. Served with a golden brown signature breadstick brushed with buttery garlic and parsley.<html>");
                        ent_description.add("<html>Juicy chipotle chicken with crisp lettuce, a blend of Cheddar cheeses and house-made pico de gallo wrapped in a tortilla with our Mexi-ranch dipping sauce. Served with classic fries.</html>");

                        //https://cloudconvert.com/ use ths link to convert the images
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Northeast Chicken Plate.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Fried Chicken Chest.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Wall Street Shrimp and Chicken.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Robotics Steak Dinner.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Frid Chicken Julius Salade.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Whitened Chicken Farfalle.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Cold Syrup Glazed Chicken Skillet.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Creek Chicken Pot.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\The Hot Honey Hen.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Fried Occidental Chicken Herba Salata.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\Raspberry-Pi Vinegar Chicken Gobbledygook.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        ent_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Entrees\\The Wannabe Tex-Mex Taquito.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

                        arrEntrees.add(new FoodItem("Northeast Chicken Plate", 26.99, 0, ent_description.get(0), ent_image.get(0)));
                        arrEntrees.add(new FoodItem("Fried Chicken Chest", 19.99, 0, ent_description.get(1), ent_image.get(1)));
                        arrEntrees.add(new FoodItem("Wall Street Shrimp and Chicken", 23.99, 0, ent_description.get(2), ent_image.get(2)));
                        arrEntrees.add(new FoodItem("Robotics Steak Dinner", 31.99, 0, ent_description.get(3), ent_image.get(3)));
                        arrEntrees.add(new FoodItem("Fried Chicken Julius Salade", 22.99, 0, ent_description.get(4), ent_image.get(4)));
                        arrEntrees.add(new FoodItem("Whitened Chicken Farfalle", 21.99, 0, ent_description.get(5), ent_image.get(5)));
                        arrEntrees.add(new FoodItem("Cold Syrup Glazed Chicken Skillet", 18.99, 0, ent_description.get(6), ent_image.get(6)));
                        arrEntrees.add(new FoodItem("Creek Chicken Pot", 27.99, 0, ent_description.get(7), ent_image.get(7)));
                        arrEntrees.add(new FoodItem("The Hot Honey Hen", 22.99, 0, ent_description.get(8), ent_image.get(8)));
                        arrEntrees.add(new FoodItem("Fried Occidental Chicken Salata", 28.99, 0, ent_description.get(9), ent_image.get(9)));
                        arrEntrees.add(new FoodItem("Orange-Pi Vinegar Chicken Gobbledygook", 25.99, 0, ent_description.get(10), ent_image.get(10)));
                        arrEntrees.add(new FoodItem("The Wannabe Tex-Mex Taquito", 23.99, 0, ent_description.get(11), ent_image.get(11)));
                    }

                    // set up
                    {
                        p_entrees.setLayout(new BoxLayout(p_entrees, BoxLayout.Y_AXIS));

                        Color c1 = Color.WHITE;
                        Color c2 = new Color(230, 230, 230);

                        for (int i = 0; i < arrEntrees.size(); i++) {
                            Color bg = (i % 2 == 0) ? c1 : c2;
                            p_entrees.add(createFoodPanel(arrEntrees.get(i), bg, arrEntrees.get(i).getDescription(), arrEntrees.get(i).getImage()));
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
                        des_description.add("<html>Cinnabon® Mini Swirls are an irresistible sweet treat. Fried to a crisp, golden brown and dusted with Cinnabon® cinnamon sugar, drizzled with caramel and paired with Cinnabon’s signature cream cheese frosting.</html>");
                        des_description.add("<html>Enjoy a tasty American classic made with vanilla ice cream and Reese's Pieces® cookie pieces.</html>");
                        des_description.add("<html>Warm, rich, fudge-filled chocolate cake is drizzled with hot fudge. Served with vanilla ice cream.</html>");
                        des_description.add("<html>The perfect size of a warm dark chocolate brownie with nuts served with vanilla ice cream and drizzled with hot fudge.</html>");

                        des_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Desserts\\Mega Cinnabon Eddies.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        des_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Desserts\\Reese's Peices Shake.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        des_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Desserts\\Quadruple Vanilla Anxieties.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
                        des_image.add(new ImageIcon(new ImageIcon("src\\Restaurant\\Iamges\\Desserts\\Cacao Nibbles.jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));

                        arrDesserts.add(new FoodItem("Mega Cinnabon® Eddies", 7.99, 0, des_description.get(0), des_image.get(0)));
                        arrDesserts.add(new FoodItem("Reese's Pieces Shake", 4.89, 0, des_description.get(1), des_image.get(1)));
                        arrDesserts.add(new FoodItem("Quadruple Chocolate Anxieties", 11.99, 0, des_description.get(2), des_image.get(2)));
                        arrDesserts.add(new FoodItem("Cacao Nibbles", 6.99, 0, des_description.get(3), des_image.get(3)));
                    }

                    // set up
                    {
                        p_desserts.setLayout(new BoxLayout(p_desserts, BoxLayout.Y_AXIS));

                        Color c1 = Color.WHITE;
                        Color c2 = new Color(230, 230, 230);

                        for (int i = 0; i < arrDesserts.size(); i++) {
                            Color bg = (i % 2 == 0) ? c1 : c2;
                            p_desserts.add(createFoodPanel(arrDesserts.get(i), bg, arrDesserts.get(i).getDescription(), arrDesserts.get(i).getImage()));
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

    private JPanel createFoodPanel(FoodItem item, Color bg, String description, ImageIcon image)
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(700, 150));
        panel.setBackground(bg);

        JLabel name = new JLabel(item.getName());
        name.setBounds(10, 10, 500, 40);
        name.setFont(food);

        JLabel price = new JLabel("$" + item.getPrice());
        price.setBounds(10, 45, 100, 20);
        price.setFont(pr);

        JLabel des = new JLabel(description); // make the panel longer and move the description to the bottom
        des.setBounds(400, 50, 200, 100);
        panel.add(des);

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