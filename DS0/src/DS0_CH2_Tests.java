
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DS0_CH2_Tests {
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
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }

    @Test(timeout = 250)
    public void test2() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {0,1,1,1,0};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,1,1,1,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }
    @Test(timeout = 250)
    public void test3() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {14,14,5,6,7};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {14,14,0,0,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }


    @Test(timeout = 250)
    public void test4() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1,2,3,4,4};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,0,0,4,4};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }

    @Test(timeout = 250)
    public void test5() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1,2,3,3,3,3,1,4};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,0,3,3,3,3,0,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }

    @Test(timeout = 250)
    public void test6() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1,2,2,1,2,2,1};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,2,2,0,2,2,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }

    @Test(timeout = 250)
    public void test7() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1,2,3,4,4,3,2,1};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,0,0,4,4,0,0,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }
    @Test(timeout = 250)
    public void test8() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {8,8,8,8,8};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {8,8,8,8,8};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }


    @Test(timeout = 250)
    public void test9() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1,2,5,6,5,8,7,4,4,4,4,5,5,8};
        try
        {
        method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,0,0,0,0,0,0,4,4,4,4,5,5,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }

    @Test(timeout = 250)
    public void test10() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH2"));
        Method method = classRef.getMethod("commonNeighbors", int[].class);

        int[] list = {1,2,2,3,3,3,4,4,4,4,5};
        try
        {
            method.invoke(method, list);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        int[] expected = {0,2,2,3,3,3,4,4,4,4,0};

        Assert.assertEquals(Arrays.toString(expected),Arrays.toString(list));
    }
}
