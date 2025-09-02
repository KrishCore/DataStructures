import java.util.ArrayList;

public class DS8_CH8_Sale
{
    public static void sale (ArrayList<DS8_CH8_Item> inventory)
    {
        for (int i = inventory.size()-1; i >= 0; i--) {
            if (inventory.get(i).getStatus() == 0)
            {
                inventory.get(i).setStatus(1);
                inventory.get(i).setPrice(inventory.get(i).getPrice()*.7);
            }
            else if (inventory.get(i).getStatus() == 1)
            {
                inventory.get(i).setStatus(2);
                inventory.get(i).setPrice(inventory.get(i).getPrice()*.5);
            }
            else
                inventory.remove(i);
        }
    }
}
