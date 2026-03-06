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
    private String scoops;
    private ArrayList<String> toppings;


    public IceCream(String container, String flavor, String scoops, ArrayList<String> toppings)
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

    public String getScoops() {
        return scoops;
    }

    public void setScoops(String scoops) {
        this.scoops = scoops;
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<String> toppings) {
        this.toppings = toppings;
    }
}
