package IceCream;

import javax.swing.*;
import java.awt.*;

public class IceCreamLab extends JFrame
{
    private JLabel title = new JLabel("Ice Cream Shoppe");
    private JLabel containerType  = new JLabel("Container Type:");
    private JRadioButton container1 = new JRadioButton("Bowl (.50)");
    private JRadioButton container2 = new JRadioButton("Waffle Bowl (2.00)");
    private JRadioButton container3 = new JRadioButton("Waffle Cone (2.00)");
    private JRadioButton container4 = new JRadioButton("Chocolate Waffle Cone (3.50)");
    private ButtonGroup containersG = new ButtonGroup();

    private JComboBox<String> flavor = new JComboBox<>(new String[] {"Cookies and Cream", "Chocolate", "Vanilla (Default)",
            "Butter Pecan", "Strawberry", "Chocolate Chip Cookie Dough", "Coffee", "Cinnamon"});
    private JComboBox<String> numScoops = new JComboBox<>(new String[] {"1 (3.00)", "2 (5.00)", "3 (7.00)"});

    private JLabel lbl_toppings = new JLabel("Toppings:");
    private JCheckBox topping1 = new JCheckBox("Chocolate Syrup (.75)");
    private JCheckBox topping2 = new JCheckBox("Carmel Syrup (.75");
    private JCheckBox topping3 = new JCheckBox("M&M's (1.00)");
    private JCheckBox topping4 = new JCheckBox("Oreos (1.00)");
    private JCheckBox topping5 = new JCheckBox("Peanut Butter Cup (1.25)");
    private JCheckBox topping6 = new JCheckBox("Chocolate Chip (1.00)");
    private JCheckBox topping7 = new JCheckBox("Sprinkles (.75)");

    public IceCreamLab()
    {
        super("Ice Cream Shop");
        setSize(1500,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //title
        {
            title.setBounds(0, 0, getWidth(), 50);
            title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
            title.setHorizontalAlignment(JLabel.CENTER);
            add(title);
        }

        //container
        {
            containerType.setBounds(40, 100, 200, 20);
            add(containerType);
            container1.setBounds(40, 135, 200, 20);
            add(container1);
            container2.setBounds(40, 160, 200, 20);
            add(container2);
            container3.setBounds(40, 185, 200, 20);
            add(container3);
            container4.setBounds(40, 210, 200, 20);
            add(container4);
            containersG.add(container1);
            containersG.add(container2);
            containersG.add(container3);
            containersG.add(container4);
            container1.setSelected(true);
            container2.setActionCommand("C1");
            container2.setActionCommand("C2");
            container3.setActionCommand("C3");
            container4.setActionCommand("C4");
            container1.addActionListener(e -> {
                System.out.println(e.getActionCommand());
            });
            container2.addActionListener(e -> {
                System.out.println(e.getActionCommand());
            });
            container3.addActionListener(e -> {
                System.out.println(e.getActionCommand());
            });
            container3.addActionListener(e -> {
                System.out.println(e.getActionCommand());
            });
        }

        //toppings
        {
            lbl_toppings.setBounds(490, 100, 200, 20);
            add(lbl_toppings);
            topping1.setBounds(490, 135, 200, 20);
            add(topping1);
            topping2.setBounds(490, 160, 200, 20);
            add(topping2);
            topping3.setBounds(490, 185, 200, 20);
            add(topping3);
            topping4.setBounds(490, 210, 200, 20);
            add(topping4);
            topping5.setBounds(490, 235, 200, 20);
            add(topping5);
            topping6.setBounds(490, 260, 200, 20);
            add(topping6);
            topping7.setBounds(490, 285, 200, 20);
            add(topping7);
        }


        setVisible(true);
    }
}
