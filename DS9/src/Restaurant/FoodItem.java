package Restaurant;

import javax.swing.*;

public class FoodItem
{
    private String name;
    private String description;
    private double price;
    private int quantity;
    private ImageIcon image;

    public FoodItem (String name, double price, int quantity, String description, ImageIcon image)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
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

    public String getDescription()
    {
        return description;
    }

    public ImageIcon getImage()
    {
        return image;
    }

    public String toString()
    {
        return name + " - $" + price + ": " + quantity + " _ " + quantity*price;
    }
}