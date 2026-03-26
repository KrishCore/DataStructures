package Restaurant;

public class FoodItem
{
    private String name;
    private double price;
    private int quantity;

    public FoodItem (String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String toString()
    {
        return name + " - " + price;
    }
}
