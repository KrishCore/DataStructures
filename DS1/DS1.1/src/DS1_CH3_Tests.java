import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DS1_CH3_Tests {
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
    public void test1() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("columnSums",String.class);

        String fileName = "grid1.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3X4\n" +
                    "3 4 7\n" +
                    "87 -4 11\n" +
                    "1 1 1\n" +
                    "2 1 1");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {93, 2, 20};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("rowSums",String.class);

        String fileName = "grid1.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3X4\n" +
                    "3 4 7\n" +
                    "87 -4 11\n" +
                    "1 1 1\n" +
                    "2 1 1");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {14, 94, 3, 4};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("columnSums",String.class);

        String fileName = "grid2.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1X1\n" +
                    "3");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {3};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("rowSums",String.class);

        String fileName = "grid2.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1X1\n" +
                    "143");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {143};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("columnSums",String.class);

        String fileName = "nums.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("5X2\n" +
                    "3 5 6 -3 7\n"+
                    "4 4 7 8 88");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {7, 9, 13, 5, 95};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("rowSums",String.class);

        String fileName = "nums.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("5X2\n" +
                    "3 5 6 -3 7\n"+
                    "4 4 7 8 88");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {18, 111};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("columnSums",String.class);

        String fileName = "nums2.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3X6\n" +
                    "6 7 5\n" +
                    "44 -88 77\n" +
                    "9 7 9 \n" +
                    "3 5 1\n" +
                    "1 2 33\n" +
                    "33 2 1 ");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {96, -65, 126};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("rowSums",String.class);

        String fileName = "nums2.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3X6\n" +
                    "6 7 5\n" +
                    "44 -88 77\n" +
                    "9 7 9 \n" +
                    "3 5 1\n" +
                    "1 2 33\n" +
                    "33 2 1 ");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {18, 33, 25, 9, 36, 36};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("columnSums",String.class);

        String fileName = "square.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3X3\n" +
                    "6 7 5\n" +
                    "44 -88 77\n" +
                    "9 7 9");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {59, -74, 91};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH3"));
        Method method = classRef.getMethod("rowSums",String.class);

        String fileName = "square.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3X3\n" +
                    "6 7 5\n" +
                    "44 -88 77\n" +
                    "9 7 9");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {18, 33, 25};

            Assert.assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }
}
