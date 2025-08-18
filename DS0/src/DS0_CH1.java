import java.util.ArrayList;
public class DS0_CH1 {
    public static int uniqueCount(int[] list) {
        ArrayList <Integer> numbers = new ArrayList<>();
        for (int i = 0; i < list.length; i++)
            if (!numbers.contains(list[i]))
                numbers.add(list[i]);
        return numbers.size();
    }
}