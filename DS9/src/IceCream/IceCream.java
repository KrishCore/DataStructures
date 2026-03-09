package IceCream;

import java.util.ArrayList;

public class IceCream
{
//    public static final int BOWL = 0;
//    public static final int WAFFLE_BOWL = 1;
//    public static final int CHOCOLATE_WAFFLE_BOWL = 2;
//    public static final int WAFFLE_CONE = 3;
//
//    public static final int COOKIES_AND_CREAM = 0;
//    public static final int CHOCOLATE = 1;
//    public static final int VANILLA = 2;
//    public static final int BUTTER_PECAN = 3;
//    public static final int STRAWBERRY = 4;
//    public static final int CHOCOLATE_CHIP_COOKIE_DOUGH = 5;
//    public static final int COFFEE = 6;
//    public static final int CINNAMON = 7;
//
//    public static final int CHOCOLATE_SYRUP = 0;
//    public static final int CARMEL_SYRUP = 1;
//    public static final int M_AND_M_S = 2;
//    public static final int OREOS = 3;
//    public static final int PEANUT_BUTTER = 4;
//    public static final int CHOCOLATE_CHIP = 5;
//    public static final int SPRINKLES = 6;

    private String container;
    private String flavor;
    private int scoops;
    private ArrayList<String> toppings;

    public IceCream(String container, String flavor, int scoops, ArrayList<String> toppings)
    {
        this.container = container;
        this.flavor = flavor;
        this.scoops = scoops;
        this.toppings = toppings;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container)
    {
        this.container = container;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getScoops() {
        return scoops;
    }

    public void setScoops(int scoops) {
        this.scoops = scoops;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public double getPrice()
    {
        double price = 0;
        //container price
        {
            if (container.equals("Bowl ($0.50)"))
                price += 0.50;
            else if (container.equals("Waffle Bowl ($2.00)"))
                price += 2.00;
            else if (container.equals("Waffle Cone ($2.00)"))
                price += 2.00;
            else if (container.equals("Chocolate Waffle Cone ($3.50)"))
                price += 3.50;
        }
        //scoops price
        {
            if (scoops == 1)
                price += 3.00;
            else if (scoops == 2)
                price += 5.00;
            else if (scoops == 3)
                price += 7.00;
        }
        //toppings price
        {
            if (toppings.contains("Chocolate Syrup ($0.75)"))
                price += 0.75;
            if (toppings.contains("Carmel Syrup ($0.75)"))
                price += 0.75;
            if (toppings.contains("M&M's ($1.00)"))
                price += 1.00;
            if (toppings.contains("Oreos ($1.00)"))
                price += 1.00;
            if (toppings.contains("Peanut Butter Cup ($1.25)"))
                price += 1.25;
            if (toppings.contains("Chocolate Chip ($1.00)"))
                price += 1.00;
            if (toppings.contains("Sprinkles ($0.75)"))
                price += 0.75;
        }
        return price;
    }
}
