
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DS0_CH4_Tests {
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


    public int[][] copy(int[][] grid)
    {
        int[][] copy = new int[grid.length][grid[0].length];
        for(int r=0; r< grid.length; r++)
            for(int c=0; c< grid[0].length; c++)
                copy[r][c] = grid[r][c];
        return copy;
    }



    @Test(timeout = 250)
    public void test1() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {{0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = true;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test2() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = { {0,1,2},
                {1,0,1},
                {1,1,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = false;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test3() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,5,6},
                {5,0,4},
                {5,5,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = true;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test4() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,1,1,2},
                {1,0,1,1},
                {1,1,0,1},
                {2,1,2,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = false;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test5() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,1,2,2},
                {20,0,2,2},
                {1,1,0,16},
                {1,1,1,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = true;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test6() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,1,2,2,1},
                {20,0,2,2,2},
                {1,1,0,16,4},
                {1,1,1,0,7},
                {1,1,1,14,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = false;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test7() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,9,7,6,1},
                {1,0,8,5,4},
                {2,3,0,2,3},
                {4,5,6,0,1},
                {7,8,9,1,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = true;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test8() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,9,1,1,1},
                {1,0,1,1,1},
                {4,4,0,2,3},
                {4,5,6,0,29},
                {7,8,9,1,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = true;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test9() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,9,9,9,9},
                {9,0,9,9,9},
                {9,9,0,9,1},
                {9,9,5,0,6},
                {9,9,9,2,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = true;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }

    @Test(timeout = 250)
    public void test10() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH4"));
        Method method = classRef.getMethod("balanced", int[][].class);

        int[][] grid = {    {0,47,74,9,9,13,55,17},
                {5,0,51,53,9,12,7,12},
                {4,8,0,6,1,14,88,6},
                {1,47,5,0,7,8,7,1},
                {9,77,9,2,0,85,130,1},
                {9,7,4,4,91,0,47,1},
                {14,87,9,2,0,8,0,99},
                {6,3,14,2,0,5,14,0}};
        int[][] gridCopy = copy(grid);
        boolean actual;
        try
        {
            actual = (boolean) method.invoke(method, (Object) grid);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        boolean expected = false;

        Assert.assertEquals(Arrays.deepToString(gridCopy),Arrays.deepToString(grid));
        Assert.assertEquals(expected,actual);
    }
}
