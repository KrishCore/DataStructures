
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DS0_CH6_Tests {
    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }



    @Before
    public void setup()
    {

    }

    @Test(timeout = 250)
    public void test1() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {1,2,3,4,5};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {1,2,3,4,5};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[0, 0, 0, 0, 0]";

        Assert.assertEquals("[1, 2, 3, 4, 5]",list1.toString());
        Assert.assertEquals("[1, 2, 3, 4, 5]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test2() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {5,4,3,2,1};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {1,2,3,4,5};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[4, 2, 0, 2, 4]";

        Assert.assertEquals("[5, 4, 3, 2, 1]",list1.toString());
        Assert.assertEquals("[1, 2, 3, 4, 5]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test3() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {17};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {55};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[38]";

        Assert.assertEquals("[17]",list1.toString());
        Assert.assertEquals("[55]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test4() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {55};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {17};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[38]";

        Assert.assertEquals("[55]",list1.toString());
        Assert.assertEquals("[17]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test5() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {1, 2, 3, 4, 3};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {5, 5, -5, 5, 0};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[4, 3, 8, 1, 3]";

        Assert.assertEquals("[1, 2, 3, 4, 3]",list1.toString());
        Assert.assertEquals("[5, 5, -5, 5, 0]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test6() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {3, 3, 4, 5, 75, 14, 67, 18};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {-3, 53, 13, 5, 12, 77, 77, -98};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[6, 50, 9, 0, 63, 63, 10, 116]";

        Assert.assertEquals("[3, 3, 4, 5, 75, 14, 67, 18]",list1.toString());
        Assert.assertEquals("[-3, 53, 13, 5, 12, 77, 77, -98]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test7() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {-4, -7, 0};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {-4, 8, 99};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[0, 15, 99]";

        Assert.assertEquals("[-4, -7, 0]",list1.toString());
        Assert.assertEquals("[-4, 8, 99]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test8() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {0, 0};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {0, 0};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[0, 0]";

        Assert.assertEquals("[0, 0]",list1.toString());
        Assert.assertEquals("[0, 0]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test9() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {88, 5, 17, 4, 99, 56, 12, 33, 2, 3, -65};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {0, -75, 57, 44, 1798, 62, 0, 0, -62, 3, 142};

        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[88, 80, 40, 40, 1699, 6, 12, 33, 64, 0, 207]";

        Assert.assertEquals("[88, 5, 17, 4, 99, 56, 12, 33, 2, 3, -65]",list1.toString());
        Assert.assertEquals("[0, -75, 57, 44, 1798, 62, 0, 0, -62, 3, 142]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }

    @Test(timeout = 250)
    public void test10() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH6"));
        Method method = classRef.getMethod("absoluteDifference", ArrayList.class,ArrayList.class);

        int[] list1Values = {1, 2, 5, 48, 7, 4, 52, 6, 8, 1, 64, 4851, 710, 52, 1, 82};
        ArrayList<Integer> list1 = new ArrayList<>();

        for(int v: list1Values)
            list1.add(v);

        int[] list2Values = {87, 485, 14, 48, 7, -44, -123, 87, 8, 41, 4, 8122, 20, 12, 11, 3};
        ArrayList<Integer> list2 = new ArrayList<>();

        for(int v: list2Values)
            list2.add(v);

        ArrayList<Integer> actual;
        try
        {
            actual = (ArrayList<Integer>) method.invoke(method, list1,list2);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        String expected = "[86, 483, 9, 0, 0, 48, 175, 81, 0, 40, 60, 3271, 690, 40, 10, 79]";

        Assert.assertEquals("[1, 2, 5, 48, 7, 4, 52, 6, 8, 1, 64, 4851, 710, 52, 1, 82]",list1.toString());
        Assert.assertEquals("[87, 485, 14, 48, 7, -44, -123, 87, 8, 41, 4, 8122, 20, 12, 11, 3]",list2.toString());
        Assert.assertEquals(expected,actual.toString());
    }


}
