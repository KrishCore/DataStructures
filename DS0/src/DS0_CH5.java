import java.util.ArrayList;

public class DS0_CH5
{
    public static void removeAllInRange (ArrayList<Integer> numbers, int lower, int upper)
    {
        for (int i = numbers.size()-1; i >= 0; i--) {
            if (numbers.get(i) >= lower && numbers.get(i) <= upper)
                numbers.remove(i);
        }
    }
}
