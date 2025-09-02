
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DS0_CH12_Tests {
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
            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'x','i','x'},
                    {'x','x','i'},
                    {'i','x','i'}};
            method.invoke(method, (Object) grid, 2,2,'i','c');
            String expected = "[[x, i, x], [x, x, c], [i, x, c]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'x','i','x'},
                    {'x','x','i'},
                    {'i','x','i'}};
            method.invoke(method, (Object) grid, 2,0,'x','m');
            String expected = "[[x, i, m], [x, x, i], [i, x, i]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'x','i','x'},
                    {'x','x','i'},
                    {'i','x','i'}};
            method.invoke(method, (Object) grid, 0,0,'x','*');
            String expected = "[[*, i, x], [*, *, i], [i, *, i]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'x','i','x'},
                    {'x','x','i'},
                    {'i','x','i'}};
            method.invoke(method, (Object) grid, 0,0,'p','*');
            String expected = "[[x, i, x], [x, x, i], [i, x, i]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'x'}};
            method.invoke(method, (Object) grid, 0,0,'x','*');
            String expected = "[[*]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'x'}};
            method.invoke(method, (Object) grid, 0,0,'o','*');
            String expected = "[[x]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'a','i','a','a','a','x','a','a','a'},
                    {'a','a','a','x','a','a','a','i','a'}};
            method.invoke(method, (Object) grid, 2,0,'a','^');
            String expected = "[[^, i, ^, ^, ^, x, ^, ^, ^], [^, ^, ^, x, ^, ^, ^, i, ^]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'a','i','a','-','a','x','a','a','a'},
                    {'a','a','a','-','a','a','a','-','a'},
                    {'a','a','-','-','-','-','-','-','a'},
                    {'a','a','-','x','a','a','a','-','a'}};
            method.invoke(method, (Object) grid, 4,3,'-','X');
            String expected = "[[a, i, a, -, a, x, a, a, a], [a, a, a, -, a, a, a, -, a], [a, a, -, -, -, -, -, -, a], [a, a, -, x, a, a, a, -, a]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'a','i','-','-'},
                    {'a','a','-','-'},
                    {'a','-','a','a'},
                    {'a','a','-','a'},
                    {'a','a','-','a'},
                    {'a','a','-','a'},
                    {'a','a','-','a'},
                    {'a','a','a','a'}};
            method.invoke(method, (Object) grid, 3,2,'a','w');
            String expected = "[[w, i, -, -], [w, w, -, -], [w, -, w, w], [w, w, -, w], [w, w, -, w], [w, w, -, w], [w, w, -, w], [w, w, w, w]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
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

            Class<?> classRef = Class.forName(generateClassName("DS0_CH12"));
            Method method = classRef.getMethod("floodFill", char[][].class, int.class, int.class, char.class, char.class);

            char[][] grid = {   {'a','i','-','-'},
                    {'a','a','-','-'},
                    {'a','-','a','a'},
                    {'a','a','-','a'},
                    {'a','a','-','a'},
                    {'a','a','-','a'},
                    {'a','a','-','a'},
                    {'a','a','a','a'}};
            method.invoke(method, (Object) grid, 2,3,'-','&');
            String expected = "[[a, i, -, -], [a, a, -, -], [a, -, a, a], [a, a, &, a], [a, a, &, a], [a, a, &, a], [a, a, &, a], [a, a, a, a]]";

            Assert.assertEquals(expected, Arrays.deepToString(grid));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
