package IceCream;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class IceCreamLab extends JFrame
{
    private Font xBig = new Font(Font.SERIF, Font.BOLD, 70);
    private Font big = new Font(Font.DIALOG, Font.BOLD, 40);
    private Font medium = new Font(Font.SANS_SERIF, Font.BOLD, 30);
    private Font small = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    private JLabel title = new JLabel("Ice Cream Shoppe");
    private JLabel containerType  = new JLabel("Container Type:");
    private JRadioButton container1 = new JRadioButton("Bowl ($0.50)");
    private JRadioButton container2 = new JRadioButton("Waffle Bowl ($2.00)");
    private JRadioButton container3 = new JRadioButton("Waffle Cone ($2.00)");
    private JRadioButton container4 = new JRadioButton("Chocolate Waffle Cone ($3.50)");
    private ButtonGroup containersG = new ButtonGroup();

    private JLabel lbl_flavor = new JLabel("Flavor:");
    private JComboBox<String> flavor = new JComboBox<>(new String[] {"Cookies and Cream", "Chocolate", "Vanilla",
            "Butter Pecan", "Strawberry", "Chocolate Chip Cookie Dough", "Coffee", "Cinnamon"});
    private JLabel lbl_numScoops = new JLabel("Number of Scoops:");
    private JComboBox<String> numScoops = new JComboBox<>(new String[] {"1 ($3.00)", "2 ($5.00)", "3 ($7.00)"});

    private JLabel lbl_toppings = new JLabel("Toppings:");
    private JCheckBox topping1 = new JCheckBox("Chocolate Syrup ($0.75)");
    private JCheckBox topping2 = new JCheckBox("Carmel Syrup ($0.75)");
    private JCheckBox topping3 = new JCheckBox("M&M's ($1.00)");
    private JCheckBox topping4 = new JCheckBox("Oreos ($1.00)");
    private JCheckBox topping5 = new JCheckBox("Peanut Butter Cup ($1.25)");
    private JCheckBox topping6 = new JCheckBox("Chocolate Chip ($1.00)");
    private JCheckBox topping7 = new JCheckBox("Sprinkles ($0.75)");

    private JButton addSave = new JButton("Add");
    private JButton delete = new JButton("Delete");

    private ArrayList<IceCream> orderTable = new ArrayList<>();
    private final String[] headings = new String[] {"Container Type", "Flavor", "Number of Scoops", "Toppings"};
    private JTable table;
    private JScrollPane scr_table;

    private JLabel subTotal = new JLabel("Subtotal: $0.00");
    private double st = 0;
    private JLabel tax = new JLabel("Tax: 8.25%");
    private JLabel total = new JLabel("Total: $0.00");
    private double t = 0;
    private ArrayList<Double> prices = new ArrayList<>();

    private JButton pay = new JButton("Pay");

    public IceCreamLab()
    {
        super("Ice Cream Shop");
        setSize(1300,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //title
        {
            title.setBounds(0, 0, getWidth()-40, 100);
            title.setFont(xBig);
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
            topping1.setBounds(600, 155, 220, 20);
            topping1.setFont(small);
            add(topping1);
            topping2.setBounds(600, 180, 220, 20);
            topping2.setFont(small);
            add(topping2);
            topping3.setBounds(600, 205, 220, 20);
            topping3.setFont(small);
            add(topping3);
            topping4.setBounds(600, 230, 220, 20);
            topping4.setFont(small);
            add(topping4);
            topping5.setBounds(600, 255, 220, 20);
            topping5.setFont(small);
            add(topping5);
            topping6.setBounds(600, 280, 220, 20);
            topping6.setFont(small);
            add(topping6);
            topping7.setBounds(600, 305, 220, 20);
            topping7.setFont(small);
            add(topping7);
        }

        //number of scoops
        {
            lbl_numScoops.setBounds(300, 230, 300, 60);
            lbl_numScoops.setFont(medium);
            add(lbl_numScoops);
            numScoops.setBounds(300, 300, 270, 20);
            System.out.println(numScoops.getFont());
            numScoops.setFont(small);
            add(numScoops);
        }

        //add button
        {
            addSave.setBounds(825, 120, 420, 95);
            addSave.setFont(big);
            addSave.addActionListener(e -> {
                addSave.setText("Add");
                delete.setEnabled(false);
                if (table.getSelectedRow() != -1) //save
                {
                    pay.setEnabled(false);
                    ArrayList<String> toppings = new ArrayList<>();
                    String selectedRadio = "Bowl ($0.50)";
                    for (Enumeration<AbstractButton> b = containersG.getElements(); b.hasMoreElements(); ) {
                        AbstractButton bu = b.nextElement();
                        if (bu.isSelected()) selectedRadio = bu.getText();
                    }
                    //save toppings
                    {
                        if (topping1.isSelected())
                            toppings.add(topping1.getText());
                        if (topping2.isSelected())
                            toppings.add(topping2.getText());
                        if (topping3.isSelected())
                            toppings.add(topping3.getText());
                        if (topping4.isSelected())
                            toppings.add(topping4.getText());
                        if (topping5.isSelected())
                            toppings.add(topping5.getText());
                        if (topping6.isSelected())
                            toppings.add(topping6.getText());
                        if (topping7.isSelected())
                            toppings.add(topping7.getText());
                    }
                    double oldPrice = orderTable.get(table.getSelectedRow()).getPrice();
                    orderTable.get(table.getSelectedRow()).setContainer(selectedRadio);
                    orderTable.get(table.getSelectedRow()).setFlavor(flavor.getSelectedItem().toString());
                    orderTable.get(table.getSelectedRow()).setScoops(Integer.parseInt(numScoops.getSelectedItem().toString().substring(0, 1)));
                    orderTable.get(table.getSelectedRow()).setToppings(toppings);

                    prices.set(table.getSelectedRow(), orderTable.get(table.getSelectedRow()).getPrice());
                    st = st - oldPrice + prices.get(table.getSelectedRow());
                }
                else { //add
                    ArrayList<String> toppings = new ArrayList<>();
                    String selectedRadio = "Bowl ($0.50)";
                    for (Enumeration<AbstractButton> b = containersG.getElements(); b.hasMoreElements(); ) {
                        AbstractButton bu = b.nextElement();
                        if (bu.isSelected()) selectedRadio = bu.getText();
                    }
                    //add toppings
                    {
                        if (topping1.isSelected())
                            toppings.add(topping1.getText());
                        if (topping2.isSelected())
                            toppings.add(topping2.getText());
                        if (topping3.isSelected())
                            toppings.add(topping3.getText());
                        if (topping4.isSelected())
                            toppings.add(topping4.getText());
                        if (topping5.isSelected())
                            toppings.add(topping5.getText());
                        if (topping6.isSelected())
                            toppings.add(topping6.getText());
                        if (topping7.isSelected())
                            toppings.add(topping7.getText());
                    }
                    orderTable.add(new IceCream(selectedRadio, flavor.getSelectedItem().toString(), Integer.parseInt(numScoops.getSelectedItem().toString().substring(0, 1)), toppings));
                    prices.add(orderTable.getLast().getPrice());
                    st += prices.getLast();
                }
                subTotal.setText(((st + "").substring((st + "").indexOf(".")).length() == 2) ? "Subtotal: $" + st + "0" : "Subtotal: $" + st);
                t = st * 1.0825;
                String tot = String.format("$%.2f", t);
                if (tot.substring(tot.indexOf(".")).length() == 2)
                    System.out.println(true);
                System.out.println(tot.substring(tot.indexOf(".")) +"    " + tot.length());

                total.setText(String.format("Total: $%.2f", t));
                if (total.getText().equals("Total: $0.000") || total.getText().equals("Total: $0.0") || total.getText().equals("Total: $0.") || total.getText().equals("Total: $0"))
                    total.setText("Total $0.00");
                System.out.println(total.getText());
                if (!orderTable.isEmpty())
                    pay.setEnabled(true);
                String[][] data = new String[orderTable.size()][4];
                for (int i = 0; i < orderTable.size(); i++)
                {
                    data[i][0] = orderTable.get(i).getContainer();
                    data[i][1] = orderTable.get(i).getFlavor();
                    if (orderTable.get(i).getScoops() == 1)
                        data[i][2] = orderTable.get(i).getScoops() + " ($3.00)";
                    else if (orderTable.get(i).getScoops() == 2)
                        data[i][2] = orderTable.get(i).getScoops() + " ($5.00)";
                    else if (orderTable.get(i).getScoops() == 3)
                        data[i][2] = orderTable.get(i).getScoops() + " ($7.00)";
                    data[i][3] = orderTable.get(i).getToppings().toString();
                }

                scr_table.remove(table);
                table = new JTable(data, headings) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                selectionChange();
                scr_table.setViewportView(table);
                scr_table.revalidate();

                //reset selections
                {
                    container1.setSelected(true);
                    flavor.setSelectedIndex(2);
                    numScoops.setSelectedIndex(0);
                    topping1.setSelected(false);
                    topping2.setSelected(false);
                    topping3.setSelected(false);
                    topping4.setSelected(false);
                    topping5.setSelected(false);
                    topping6.setSelected(false);
                    topping7.setSelected(false);
                }
            });
            add(addSave);
        }

        //delete button
        {
            delete.setBounds(825, 230, 420, 95);
            delete.setFont(big);
            add(delete);
            delete.setEnabled(false);
            delete.addActionListener(e -> {
                delete.setEnabled(false);
                addSave.setText("Add");
                orderTable.remove(table.getSelectedRow());

                String[][] data = new String[orderTable.size()][4];
                for (int i = 0; i < orderTable.size(); i++) {
                    data[i][0] = orderTable.get(i).getContainer();
                    data[i][1] = orderTable.get(i).getFlavor();
                    if (orderTable.get(i).getScoops() == 1)
                        data[i][2] = orderTable.get(i).getScoops() + " ($3.00)";
                    else if (orderTable.get(i).getScoops() == 2)
                        data[i][2] = orderTable.get(i).getScoops() + " ($5.00)";
                    else if (orderTable.get(i).getScoops() == 3)
                        data[i][2] = orderTable.get(i).getScoops() + " ($7.00)";
                    data[i][3] = orderTable.get(i).getToppings().toString();
                }

                table = new JTable(data, headings) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                selectionChange();

                scr_table.setViewportView(table);
                scr_table.revalidate();

                pay.setEnabled(false);
                if (!orderTable.isEmpty())
                    pay.setEnabled(true);
            });
        }

        //table and update table - empty
        {
            //table
            {

            }

            //update table
            {

            }
        }

        //scroll bar table
        {
            table = new JTable(new String[0][3], headings) { //may not be needed
                @Override
                public boolean isCellEditable(int row, int column)
                {
                    return false;
                }
            };
            scr_table = new JScrollPane(table);
            scr_table.setBounds(40, 350, 1205, 300);
            scr_table.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            selectionChange();
            add(scr_table);
        }

        //cost
        {
            //subtotal
            {
                subTotal.setBounds(0, 660, getWidth()/2-80, 60);
                subTotal.setHorizontalAlignment(JLabel.CENTER);
                System.out.println(subTotal.getX() + " " + subTotal.getWidth());
                subTotal.setFont(medium);
                delete.addActionListener(e -> {
                    System.out.println("rows:" + table.getSelectedRow());
                    st-= orderTable.get(table.getSelectedRow()).getPrice();
                    subTotal.setText(((st+"").substring((st+"").indexOf(".")).length() == 2) ? "Subtotal: $" + st + "0" : "Subtotal: $" + st);
                    t = st * 1.0825;
                    if ((t+"").substring((t+"").indexOf(".")).length() == 2)
                        System.out.println("length 2");
                    else if ((t+"").substring((t+"").indexOf(".")).length() == 1)
                        System.out.println("length 1");
                    else if ((t+"").substring((t+"").indexOf(".")).length() == 3)
                        System.out.println("3");
                    total.setText(String.format("Total: $%.2f", t));
                    if (total.getText().equals("Total: $0.000") || total.getText().equals("Total: $0.0") || total.getText().equals("Total: $0.") || total.getText().equals("Total: $0"))
                        total.setText("Total $0.00");
                    System.out.println(total.getText());
                });
                add(subTotal);
            }

            //tax
            {
                tax.setBounds(0, 660, getWidth()-100,60);
                tax.setFont(medium);
                tax.setHorizontalAlignment(JLabel.CENTER);
                add(tax);
            }

            //total
            {
                total.setBounds(getWidth()/2-20, 660, getWidth()/2-80, 60);
                total.setFont(medium);
                total.setHorizontalAlignment(JLabel.CENTER);
                add(total);
            }
        }

        //pay
        {
            pay.setBounds(1100, 660, 100,50);
            pay.setEnabled(false);
            pay.addActionListener(e -> {
                int selection = -1;
                while (selection == -1)
                {
                    selection = JOptionPane.showOptionDialog(this, "How would you like to pay?", "Payment Method",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Cash", "Card"}, "Cash");
                    if (selection == 0)
                        JOptionPane.showMessageDialog(this, "Cash payment accepted.");
                    else if (selection == 1)
                        JOptionPane.showMessageDialog(this, "Card payment accepted");
                }
            });
            add(pay);
        }
        setVisible(true);
    }

    public void selectionChange()
    {
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()){
                if (table.getSelectedRow() != -1) {
                    pay.setEnabled(false);
                    delete.setEnabled(true);
                    addSave.setText("Save");
                    IceCream ic = orderTable.get(table.getSelectedRow());
                    if (ic.getContainer().equals("Bowl ($0.50)"))
                        containersG.setSelected(container1.getModel(), true);
                    else if (ic.getContainer().equals("Waffle Bowl ($2.00)"))
                        containersG.setSelected(container2.getModel(), true);
                    else if (ic.getContainer().equals("Waffle Cone ($2.00)"))
                        containersG.setSelected(container3.getModel(), true);
                    else if (ic.getContainer().equals("Chocolate Waffle Cone ($3.50)"))
                        containersG.setSelected(container4.getModel(), true);

                    flavor.setSelectedItem(ic.getFlavor());
                    numScoops.setSelectedIndex(ic.getScoops() - 1);

                    ArrayList<String> toppings = ic.getToppings();
                    topping1.setSelected(toppings.contains(topping1.getText()));
                    topping2.setSelected(toppings.contains(topping2.getText()));
                    topping3.setSelected(toppings.contains(topping3.getText()));
                    topping4.setSelected(toppings.contains(topping4.getText()));
                    topping5.setSelected(toppings.contains(topping5.getText()));
                    topping6.setSelected(toppings.contains(topping6.getText()));
                    topping7.setSelected(toppings.contains(topping7.getText()));
                }
            }
        });
    }
}