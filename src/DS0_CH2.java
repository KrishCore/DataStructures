public class DS0_CH2
{
    public static int[] commonNeighbors (int[] list)
    {
        if (list.length == 1) {
            return new int[]{0};
        }

        int[] changed = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            changed[i] = list[i];
        }
        for (int i = 0; i < list.length; i++) {
            if (i == 0 && list[i] != list[i+1])
                changed[i] = 0;
            if ((i != 0 || i != list.length )|| list[i] != list[i-1] || list[i] != list[i+1])
                changed[i] = 0;
            if (i == list.length-1 && list[i] != list[i-1])
                changed[i] = 0;
        }
        return changed;
    }
}
