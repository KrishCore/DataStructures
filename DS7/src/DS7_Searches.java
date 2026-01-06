public class DS7_Searches
{
    public static int linearSearch(int[] list, int value)
    {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == value)
                return i;
        }
        return -1;
    }
    public static int binarySearch(int[] list, int value)
    {
        int start = 0;
        int end = list.length-1;

        while (start <= end) {
            int check = (start + end) / 2;
            if (list[check] == value)
                return check;
            else if (list[check] > value)
                end = check - 1;
            else
                start = check + 1;
        }
        return -1;
    }
}
