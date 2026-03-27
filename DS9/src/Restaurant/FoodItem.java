package Restaurant;

import javax.swing.*;

public class FoodItem
{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private ImageIcon image;

    public FoodItem (String name, double price, int quantity)
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
        return name + " - $" + price + ": " + quantity + " _ " + quantity*price;
    }
}