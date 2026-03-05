package IceCream;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IceCreamLab extends JFrame
{
    private Font big = new Font(Font.SANS_SERIF, Font.BOLD, 50);
    private Font medium = new Font(Font.SANS_SERIF, Font.BOLD, 30);
    private Font small = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    private JLabel title = new JLabel("Ice Cream Shoppe");
    private JLabel containerType  = new JLabel("Container Type:");
    private JRadioButton container1 = new JRadioButton("Bowl (.50)");
    private JRadioButton container2 = new JRadioButton("Waffle Bowl (2.00)");
    private JRadioButton container3 = new JRadioButton("Waffle Cone (2.00)");
    private JRadioButton container4 = new JRadioButton("Chocolate Waffle Cone (3.50)");
    private ButtonGroup containersG = new ButtonGroup();

    private JLabel lbl_flavor = new JLabel("Flavor:");
    private JComboBox<String> flavor = new JComboBox<>(new String[] {"Cookies and Cream", "Chocolate", "Vanilla",
            "Butter Pecan", "Strawberry", "Chocolate Chip Cookie Dough", "Coffee", "Cinnamon"});
    private JLabel lbl_numScoops = new JLabel("Number of Scoops:");
    private JComboBox<String> numScoops = new JComboBox<>(new String[] {"1 (3.00)", "2 (5.00)", "3 (7.00)"});

    private JLabel lbl_toppings = new JLabel("Toppings:");
    private JCheckBox topping1 = new JCheckBox("Chocolate Syrup (.75)");
    private JCheckBox topping2 = new JCheckBox("Carmel Syrup (.75");
    private JCheckBox topping3 = new JCheckBox("M&M's (1.00)");
    private JCheckBox topping4 = new JCheckBox("Oreos (1.00)");
    private JCheckBox topping5 = new JCheckBox("Peanut Butter Cup (1.25)");
    private JCheckBox topping6 = new JCheckBox("Chocolate Chip (1.00)");
    private JCheckBox topping7 = new JCheckBox("Sprinkles (.75)");

    private JButton add = new JButton("Add");
    private JButton delete = new JButton("Delete");

    private ArrayList<String> orderTable = new ArrayList<>();
    private ArrayList<String> headings;
    private JTable table;
    private JScrollPane scr_table;

    private double bill = 0;

    public IceCreamLab()
    {
        super("Ice Cream Shop");
        setSize(1500,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //title
        {
            title.setBounds(-113, 0, getWidth(), 50);
            title.setFont(big);
            title.setHorizontalAlignment(JLabel.CENTER);
            add(title);
        }

        //container
        {
            containerType.setBounds(40, 100, 230, 60);
            containerType.setFont(medium);
            add(containerType);
            container1.setBounds(40, 160, 220, 20);//0
            container1.setFont(small);
            add(container1);
            container2.setBounds(40, 206, 220, 20);//+46
            container2.setFont(small);
            add(container2);
            container3.setBounds(40, 253, 220, 20);//+47
            container3.setFont(small);
            add(container3);
            container4.setFont(small);
            container4.setBounds(40, 300, 230, 20);//+47
            add(container4);
            containersG.add(container1);
            containersG.add(container2);
            containersG.add(container3);
            containersG.add(container4);
            container1.setSelected(true);
            //unnecessary actionListeners
            {
                container1.addActionListener(e -> {
                    System.out.println(e.getActionCommand());
                });
                container2.addActionListener(e -> {
                    System.out.println(e.getActionCommand());
                });
                container3.addActionListener(e -> {
                    System.out.println(e.getActionCommand());
                });
                container4.addActionListener(e -> {
                    System.out.println(e.getActionCommand());
                });
            }
        }

        //flavor
        {
            lbl_flavor.setBounds(300, 100, 300, 60);
            lbl_flavor.setFont(medium);
            add(lbl_flavor);
            flavor.setBounds(300, 170, 270, 20);
            flavor.setFont(small);
            flavor.setSelectedIndex(2);
            add(flavor);
        }

        //toppings
        {
            lbl_toppings.setBounds(600, 100, 300, 60);
            lbl_toppings.setFont(medium);
            add(lbl_toppings);
            topping1.setBounds(600, 155, 200, 20);
            topping1.setFont(small);
            add(topping1);
            topping2.setBounds(600, 180, 200, 20);
            topping2.setFont(small);
            add(topping2);
            topping3.setBounds(600, 205, 200, 20);
            topping3.setFont(small);
            add(topping3);
            topping4.setBounds(600, 230, 200, 20);
            topping4.setFont(small);
            add(topping4);
            topping5.setBounds(600, 255, 200, 20);
            topping5.setFont(small);
            add(topping5);
            topping6.setBounds(600, 280, 200, 20);
            topping6.setFont(small);
            add(topping6);
            topping7.setBounds(600, 305, 200, 20);
            topping7.setFont(small);
            add(topping7);
        }

        //number of scoops
        {
            lbl_numScoops.setBounds(300, 230, 300, 60);
            lbl_numScoops.setFont(medium);
            add(lbl_numScoops);
            numScoops.setBounds(300, 300, 270, 20);
            add(numScoops);
        }

        //add button
        {

        }

        //delete button
        {

        }

        //table and update table
        {
            //table
            {

            }

            //update table
            {

            }
        }

        //headings table
        {
            headings = new ArrayList<>();
            headings.add("Container Type");
            headings.add("Number of Scoops");
            headings.add("Flavor");
            headings.add("Toppings");
            table = new JTable(new String[0][3], headings.toArray());
            scr_table = new JScrollPane(table);
            scr_table.setBounds(850, 100, 600, 625);
            scr_table.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scr_table);
        }

        //cost
        {
            
        }

        setVisible(true);
    }
}
