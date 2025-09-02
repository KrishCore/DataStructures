
public class DS8_CH8_Item
{
    private String name;
    private double price;
    private int status;

    public static final int NORMAL     = 0;
    public static final int DISCOUNTED = 1;
    public static final int CLEARANCE  = 2;

    public DS8_CH8_Item(String name, double price, int status)
    {
        this.name = name;
        this.price = price;
        this.status = status;
    }

    public String getName()
    {   return name;    }

    public double getPrice()
    {   return price;   }

    public int getStatus()
    {   return status;    }

    public void setName(String name)
    {   this.name = name;   }

    public void setPrice(double price)
    {   this.price = price; }

    public void setStatus(int status)
    {   this.status = status;   }

    public boolean equals(Object o)
    {
        if(! (o instanceof DS8_CH8_Item))
            return false;
        else {
            DS8_CH8_Item i = (DS8_CH8_Item)o;
            return name.equals(i.name) && status==i.status &&
                    Math.abs(price-i.price) <.00001;
        }
    }

    public String toString()
    {
        return "("+name+"/"+price+"/"+status+")";
    }
}
