
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class DS1_CH1_Tests {

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Bob.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3,4,7,8,-9\n" +
                    "-14,6\n" +
                    "7");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {13, -8, 7};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Bob.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1,-8,-7,-4,8,-9\n" +
                    "2,13\n" +
                    "14\n" +
                    "1,8,-7,8,-9");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {-19, 15, 14, 1};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Testing.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {1};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Testing.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1\n" +
                    "1,2\n" +
                    "1,2,3\n" +
                    "1,2,3,4\n" +
                    "1,2,3,4,5\n" +
                    "1,2,3,4,5,6\n" +
                    "1,2,3,4,5,6,7\n" +
                    "1,2,3,4,5,6,7,-8");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {1, 3, 6, 10, 15, 21, 28, 20};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Phase.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("-8,13,-3\n" +
                    "7,77\n" +
                    "409\n" +
                    "9876,567\n" +
                    "76,-55");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {2, 84, 409, 10443, 21};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Phase.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3,-7,36\n" +
                    "67,78\n" +
                    "894,5648,-1452\n" +
                    "1,1,1,1,54,78\n" +
                    "695,-678,6765\n" +
                    "1453\n" +
                    "-5,6,-8");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {32, 145, 5090, 136, 6782, 1453, -7};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Numbers.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("4,4,-4,38,-9\n" +
                    "-1,12,-99");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {33, -88};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Numbers.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3,-3,8");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {8};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Perfect.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("3,3,-3\n" +
                    "-4,6,78\n" +
                    "7,7,7");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {3, 80, 21};

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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH1"));
        Method method = classRef.getMethod("sumLines",String.class);

        String fileName = "Perfect.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1,2,1,2\n" +
                    "-2,3,-4,3\n" +
                    "2,-3,4,3\n" +
                    "1,-8,-9,-1");
            printWriter.close();

            int[] actual = (int[])method.invoke(method, fileName);
            int[] expected = {6, 0, 6, -17};

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
