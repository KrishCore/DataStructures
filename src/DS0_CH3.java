public class DS0_CH3
{
    public static int[] fewest (int cents)
    {
        int[] values = {1, 5, 10, 25, 100, 500, 1000, 2000, 5000, 10000};
        int[] result = new int[values.length];

        for (int i = result.length-1; i >= 0; i--) {
            result[i] = cents/values[i];
            cents %= values[i];
        }
        return result;

    }
}
