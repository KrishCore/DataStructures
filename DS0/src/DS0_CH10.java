public class DS0_CH10
{
    public static long factorial (long num)
    {
        if (num == 1)
            return 1;
        else return num * factorial(num-1);
    }
}
