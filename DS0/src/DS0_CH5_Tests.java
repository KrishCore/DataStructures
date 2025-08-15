
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DS0_CH5_Tests {
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
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1,2,3,4,5};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,1,5);
            String expected = "[]";

            Assert.assertEquals(expected,actual.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1,2,3,4,5};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,3,3);
            String expected = "[1, 2, 4, 5]";

            Assert.assertEquals(expected,actual.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

    }

    @Test(timeout = 250)
    public void test3() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1,2,3,4,5};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,2,4);
            String expected = "[1, 5]";

            Assert.assertEquals(expected,actual.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

    }

    @Test(timeout = 250)
    public void test4() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1,22,3,14,15,17,55,6,8,13};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,11,15);
            String expected = "[1, 22, 3, 17, 55, 6, 8]";

            Assert.assertEquals(expected,actual.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

    }

    @Test(timeout = 250)
    public void test5() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {17,22,3,14,15,17,55,6,8,13};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,2,5);
            String expected = "[17, 22, 14, 15, 17, 55, 6, 8, 13]";

            Assert.assertEquals(expected,actual.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

    }

    @Test(timeout = 250)
    public void test6() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1,2,3,4,5};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,5,15);
            String expected = "[1, 2, 3, 4]";

            Assert.assertEquals(expected,actual.toString());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1,2,3,4,5};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,1,1);
            String expected = "[2, 3, 4, 5]";

            Assert.assertEquals(expected,actual.toString());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {1123,745,26,365,845,621,555,463,77};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,100,500);
            String expected = "[1123, 745, 26, 845, 621, 555, 77]";

            Assert.assertEquals(expected,actual.toString());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {55,2,3,4,62};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,50,60);
            String expected = "[2, 3, 4, 62]";

            Assert.assertEquals(expected,actual.toString());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH5"));
            Method method = classRef.getMethod("removeAllInRange", ArrayList.class,int.class,int.class);

            ArrayList<Integer> actual = new ArrayList<>();
            int[] toAdd = {74,22,78};
            for(int v: toAdd)
                actual.add(v);

            method.invoke(method, actual,20,30);
            String expected = "[74, 78]";

            Assert.assertEquals(expected,actual.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
