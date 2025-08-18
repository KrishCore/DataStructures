public class DS0_CH2
{
    public static void commonNeighbors (int[] list)
    {
        if (list.length == 1)
            list[0] = 0;

        else {
            if (list[0] == list[1]) {}
            else list[0] = 0;

            for (int i = 1; i < list.length; i++) {
                if (i == list.length-1)
                    if (list[i] == list[i-1]){}
                    else list[i] = 0;
                else if (list[i] == list[i-1] || list[i] == list[i+1]){}
                else list[i] = 0;

            }
        }
    }
}
