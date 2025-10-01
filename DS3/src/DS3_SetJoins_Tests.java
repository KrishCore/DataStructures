
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class DS3_SetJoins_Tests {

    public TestingOutputStream testingOutputStream = new TestingOutputStream();
    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }

    public String unifyLineSeperators(String line)
    {
        return line.replaceAll("\\n|\\r\\n", System.getProperty("line.separator")).trim();
    }

    @Before
    public void setup()
    {
        System.setOut(testingOutputStream);
    }

    @Test(timeout = 250)
    public void test1() throws Exception {
        String input =  "File1.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [1, 2, 3, 4, 5]\n" +
                "Set B: [4, 5, 6, 7, 8]\n" +
                "\n" +
                "Union: [1, 2, 3, 4, 5, 6, 7, 8]\n" +
                "Intersection: [4, 5]\n" +
                "A - B (Elements in A not in B): [1, 2, 3]\n" +
                "B - A (Elements in B not in A): [6, 7, 8]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File1.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1 2 3 4 5\n" +
                    "4 5 6 7 8");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "File2.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [10, 20, 30, 40]\n" +
                "Set B: [30, 40, 50, 60]\n" +
                "\n" +
                "Union: [10, 20, 30, 40, 50, 60]\n" +
                "Intersection: [30, 40]\n" +
                "A - B (Elements in A not in B): [10, 20]\n" +
                "B - A (Elements in B not in A): [50, 60]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File2.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("10 20 30 40\n" +
                    "30 40 50 60");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "File3.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [1, 3, 5, 7, 9]\n" +
                "Set B: [2, 4, 6, 8, 10]\n" +
                "\n" +
                "Union: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]\n" +
                "Intersection: []\n" +
                "A - B (Elements in A not in B): [1, 3, 5, 7, 9]\n" +
                "B - A (Elements in B not in A): [2, 4, 6, 8, 10]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File3.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1 3 5 7 9\n" +
                    "2 4 6 8 10");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "4.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [5]\n" +
                "Set B: [5]\n" +
                "\n" +
                "Union: [5]\n" +
                "Intersection: [5]\n" +
                "A - B (Elements in A not in B): []\n" +
                "B - A (Elements in B not in A): []";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "4.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("5 5 5 5\n" +
                    "5 5 5");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "File5.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [100, 200, 300]\n" +
                "Set B: [150, 200, 250]\n" +
                "\n" +
                "Union: [100, 150, 200, 250, 300]\n" +
                "Intersection: [200]\n" +
                "A - B (Elements in A not in B): [100, 300]\n" +
                "B - A (Elements in B not in A): [150, 250]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File5.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("100 200 300\n" +
                    "150 200 250");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "File6.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [1, 2, 3, 4]\n" +
                "Set B: [1, 2, 3]\n" +
                "\n" +
                "Union: [1, 2, 3, 4]\n" +
                "Intersection: [1, 2, 3]\n" +
                "A - B (Elements in A not in B): [4]\n" +
                "B - A (Elements in B not in A): []";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File6.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1 2 3 4\n" +
                    "1 2 3");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "bob.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [7, 14, 21, 28]\n" +
                "Set B: [14, 28, 35, 42]\n" +
                "\n" +
                "Union: [7, 14, 21, 28, 35, 42]\n" +
                "Intersection: [14, 28]\n" +
                "A - B (Elements in A not in B): [7, 21]\n" +
                "B - A (Elements in B not in A): [35, 42]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "bob.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("7 14 21 28\n" +
                    "14 28 35 42");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "FileA.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]\n" +
                "Set B: [10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]\n" +
                "\n" +
                "Union: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]\n" +
                "Intersection: [10, 11, 12, 13, 14, 15]\n" +
                "A - B (Elements in A not in B): [1, 2, 3, 4, 5, 6, 7, 8, 9]\n" +
                "B - A (Elements in B not in A): [16, 17, 18, 19, 20]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "FileA.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n" +
                    "10 11 12 13 14 15 16 17 18 19 20");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "File9.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50]\n" +
                "Set B: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]\n" +
                "\n" +
                "Union: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 20, 25, 30, 35, 40, 45, 50]\n" +
                "Intersection: [5, 10, 15]\n" +
                "A - B (Elements in A not in B): [20, 25, 30, 35, 40, 45, 50]\n" +
                "B - A (Elements in B not in A): [1, 2, 3, 4, 6, 7, 8, 9, 11, 12, 13, 14]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File9.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("5 10 15 20 25 30 35 40 45 50\n" +
                    "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
        String input =  "File10.txt";

        String expected =   "Enter the file name: \n" +
                "Set A: [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000]\n" +
                "Set B: [500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400]\n" +
                "\n" +
                "Union: [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400]\n" +
                "Intersection: [500, 600, 700, 800, 900, 1000]\n" +
                "A - B (Elements in A not in B): [100, 200, 300, 400]\n" +
                "B - A (Elements in B not in A): [1100, 1200, 1300, 1400]";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_SetJoins"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File10.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("100 200 300 400 500 600 700 800 900 1000\n" +
                    "500 600 700 800 900 1000 1100 1200 1300 1400");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
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
