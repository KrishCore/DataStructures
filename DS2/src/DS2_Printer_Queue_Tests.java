
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class DS2_Printer_Queue_Tests {

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

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (5 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 28s: Job #1 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 1\n" +
                "Total Pages: 5\n" +
                "Average Job Wait Time: 3.0s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
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
            printWriter.print("0,5");
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
        String input =  "Bobs Printer.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (2 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 13s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 2s: Job #2 Received (3 pages)\n" +
                "Time 13s: Job #2 Buffering Started\n" +
                "Time 16s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 31s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 5s: Job #3 Received (1 pages)\n" +
                "Time 31s: Job #3 Buffering Started\n" +
                "Time 34s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 39s: Job #3 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 3\n" +
                "Total Pages: 6\n" +
                "Average Job Wait Time: 15.3s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "Bobs Printer.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,2\n" +
                    "2,3\n" +
                    "5,1");
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
        String input =  "3.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (4 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 23s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 1s: Job #2 Received (2 pages)\n" +
                "Time 23s: Job #2 Buffering Started\n" +
                "Time 26s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 36s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 20s: Job #3 Received (3 pages)\n" +
                "Time 36s: Job #3 Buffering Started\n" +
                "Time 39s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 54s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 22s: Job #4 Received (1 pages)\n" +
                "Time 54s: Job #4 Buffering Started\n" +
                "Time 57s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 62s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 25s: Job #5 Received (2 pages)\n" +
                "Time 62s: Job #5 Buffering Started\n" +
                "Time 65s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 75s: Job #5 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 5\n" +
                "Total Pages: 12\n" +
                "Average Job Wait Time: 24.4s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "3.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,4\n" +
                    "1,2\n" +
                    "20,3\n" +
                    "22,1\n" +
                    "25,2");
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
        String input =  "Four.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (2 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 13s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 1s: Job #2 Received (1 pages)\n" +
                "Time 13s: Job #2 Buffering Started\n" +
                "Time 16s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 21s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 10s: Job #3 Received (3 pages)\n" +
                "Time 21s: Job #3 Buffering Started\n" +
                "Time 24s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 39s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 30s: Job #4 Received (2 pages)\n" +
                "Time 39s: Job #4 Buffering Started\n" +
                "Time 42s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 52s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 40s: Job #5 Received (1 pages)\n" +
                "Time 52s: Job #5 Buffering Started\n" +
                "Time 55s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 60s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 70s: Job #6 Received (4 pages)\n" +
                "Time 70s: Job #6 Buffering Started\n" +
                "Time 73s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 93s: Job #6 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 6\n" +
                "Total Pages: 13\n" +
                "Average Job Wait Time: 10.3s\n" +
                "Idle Time: 10s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "Four.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,2\n" +
                    "1,1\n" +
                    "10,3\n" +
                    "30,2\n" +
                    "40,1\n" +
                    "70,4");
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
        String input =  "The Queue.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (1 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 8s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 1s: Job #2 Received (2 pages)\n" +
                "Time 8s: Job #2 Buffering Started\n" +
                "Time 11s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 21s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 2s: Job #3 Received (2 pages)\n" +
                "Time 21s: Job #3 Buffering Started\n" +
                "Time 24s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 34s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 3s: Job #4 Received (3 pages)\n" +
                "Time 34s: Job #4 Buffering Started\n" +
                "Time 37s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 52s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 4s: Job #5 Received (1 pages)\n" +
                "Time 52s: Job #5 Buffering Started\n" +
                "Time 55s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 60s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 10s: Job #6 Received (5 pages)\n" +
                "Time 60s: Job #6 Buffering Started\n" +
                "Time 63s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 88s: Job #6 Finished Printing\n" +
                "\n" +
                "Time 15s: Job #7 Received (2 pages)\n" +
                "Time 88s: Job #7 Buffering Started\n" +
                "Time 91s: Job #7 Finished Buffering and Started Printing\n" +
                "Time 101s: Job #7 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 7\n" +
                "Total Pages: 16\n" +
                "Average Job Wait Time: 35.6s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "The Queue.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,1\n" +
                    "1,2\n" +
                    "2,2\n" +
                    "3,3\n" +
                    "4,1\n" +
                    "10,5\n" +
                    "15,2");
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
        String input =  "printing.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (3 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 18s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 5s: Job #2 Received (2 pages)\n" +
                "Time 18s: Job #2 Buffering Started\n" +
                "Time 21s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 31s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 6s: Job #3 Received (1 pages)\n" +
                "Time 31s: Job #3 Buffering Started\n" +
                "Time 34s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 39s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 50s: Job #4 Received (4 pages)\n" +
                "Time 50s: Job #4 Buffering Started\n" +
                "Time 53s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 73s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 55s: Job #5 Received (2 pages)\n" +
                "Time 73s: Job #5 Buffering Started\n" +
                "Time 76s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 86s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 56s: Job #6 Received (1 pages)\n" +
                "Time 86s: Job #6 Buffering Started\n" +
                "Time 89s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 94s: Job #6 Finished Printing\n" +
                "\n" +
                "Time 100s: Job #7 Received (3 pages)\n" +
                "Time 100s: Job #7 Buffering Started\n" +
                "Time 103s: Job #7 Finished Buffering and Started Printing\n" +
                "Time 118s: Job #7 Finished Printing\n" +
                "\n" +
                "Time 120s: Job #8 Received (2 pages)\n" +
                "Time 120s: Job #8 Buffering Started\n" +
                "Time 123s: Job #8 Finished Buffering and Started Printing\n" +
                "Time 133s: Job #8 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 8\n" +
                "Total Pages: 18\n" +
                "Average Job Wait Time: 13.8s\n" +
                "Idle Time: 19s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "printing.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,3\n" +
                    "5,2\n" +
                    "6,1\n" +
                    "50,4\n" +
                    "55,2\n" +
                    "56,1\n" +
                    "100,3\n" +
                    "120,2");
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
        String input =  "hp.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (1 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 8s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 1s: Job #2 Received (1 pages)\n" +
                "Time 8s: Job #2 Buffering Started\n" +
                "Time 11s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 16s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 2s: Job #3 Received (1 pages)\n" +
                "Time 16s: Job #3 Buffering Started\n" +
                "Time 19s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 24s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 3s: Job #4 Received (1 pages)\n" +
                "Time 24s: Job #4 Buffering Started\n" +
                "Time 27s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 32s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 4s: Job #5 Received (1 pages)\n" +
                "Time 32s: Job #5 Buffering Started\n" +
                "Time 35s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 40s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 5s: Job #6 Received (1 pages)\n" +
                "Time 40s: Job #6 Buffering Started\n" +
                "Time 43s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 48s: Job #6 Finished Printing\n" +
                "\n" +
                "Time 6s: Job #7 Received (1 pages)\n" +
                "Time 48s: Job #7 Buffering Started\n" +
                "Time 51s: Job #7 Finished Buffering and Started Printing\n" +
                "Time 56s: Job #7 Finished Printing\n" +
                "\n" +
                "Time 7s: Job #8 Received (1 pages)\n" +
                "Time 56s: Job #8 Buffering Started\n" +
                "Time 59s: Job #8 Finished Buffering and Started Printing\n" +
                "Time 64s: Job #8 Finished Printing\n" +
                "\n" +
                "Time 8s: Job #9 Received (1 pages)\n" +
                "Time 64s: Job #9 Buffering Started\n" +
                "Time 67s: Job #9 Finished Buffering and Started Printing\n" +
                "Time 72s: Job #9 Finished Printing\n" +
                "\n" +
                "Time 9s: Job #10 Received (1 pages)\n" +
                "Time 72s: Job #10 Buffering Started\n" +
                "Time 75s: Job #10 Finished Buffering and Started Printing\n" +
                "Time 80s: Job #10 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 10\n" +
                "Total Pages: 10\n" +
                "Average Job Wait Time: 34.5s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "hp.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,1\n" +
                    "1,1\n" +
                    "2,1\n" +
                    "3,1\n" +
                    "4,1\n" +
                    "5,1\n" +
                    "6,1\n" +
                    "7,1\n" +
                    "8,1\n" +
                    "9,1");
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
        String input =  "File8.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (2 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 13s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 2s: Job #2 Received (2 pages)\n" +
                "Time 13s: Job #2 Buffering Started\n" +
                "Time 16s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 26s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 4s: Job #3 Received (3 pages)\n" +
                "Time 26s: Job #3 Buffering Started\n" +
                "Time 29s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 44s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 6s: Job #4 Received (1 pages)\n" +
                "Time 44s: Job #4 Buffering Started\n" +
                "Time 47s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 52s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 10s: Job #5 Received (5 pages)\n" +
                "Time 52s: Job #5 Buffering Started\n" +
                "Time 55s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 80s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 12s: Job #6 Received (1 pages)\n" +
                "Time 80s: Job #6 Buffering Started\n" +
                "Time 83s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 88s: Job #6 Finished Printing\n" +
                "\n" +
                "Time 20s: Job #7 Received (4 pages)\n" +
                "Time 88s: Job #7 Buffering Started\n" +
                "Time 91s: Job #7 Finished Buffering and Started Printing\n" +
                "Time 111s: Job #7 Finished Printing\n" +
                "\n" +
                "Time 25s: Job #8 Received (3 pages)\n" +
                "Time 111s: Job #8 Buffering Started\n" +
                "Time 114s: Job #8 Finished Buffering and Started Printing\n" +
                "Time 129s: Job #8 Finished Printing\n" +
                "\n" +
                "Time 30s: Job #9 Received (2 pages)\n" +
                "Time 129s: Job #9 Buffering Started\n" +
                "Time 132s: Job #9 Finished Buffering and Started Printing\n" +
                "Time 142s: Job #9 Finished Printing\n" +
                "\n" +
                "Time 35s: Job #10 Received (1 pages)\n" +
                "Time 142s: Job #10 Buffering Started\n" +
                "Time 145s: Job #10 Finished Buffering and Started Printing\n" +
                "Time 150s: Job #10 Finished Printing\n" +
                "\n" +
                "Time 40s: Job #11 Received (6 pages)\n" +
                "Time 150s: Job #11 Buffering Started\n" +
                "Time 153s: Job #11 Finished Buffering and Started Printing\n" +
                "Time 183s: Job #11 Finished Printing\n" +
                "\n" +
                "Time 60s: Job #12 Received (2 pages)\n" +
                "Time 183s: Job #12 Buffering Started\n" +
                "Time 186s: Job #12 Finished Buffering and Started Printing\n" +
                "Time 196s: Job #12 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 12\n" +
                "Total Pages: 32\n" +
                "Average Job Wait Time: 67.5s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "File8.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,2\n" +
                    "2,2\n" +
                    "4,3\n" +
                    "6,1\n" +
                    "10,5\n" +
                    "12,1\n" +
                    "20,4\n" +
                    "25,3\n" +
                    "30,2\n" +
                    "35,1\n" +
                    "40,6\n" +
                    "60,2");
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
        String input =  "turtle.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (1 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 8s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 1s: Job #2 Received (2 pages)\n" +
                "Time 8s: Job #2 Buffering Started\n" +
                "Time 11s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 21s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 2s: Job #3 Received (1 pages)\n" +
                "Time 21s: Job #3 Buffering Started\n" +
                "Time 24s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 29s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 10s: Job #4 Received (3 pages)\n" +
                "Time 29s: Job #4 Buffering Started\n" +
                "Time 32s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 47s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 11s: Job #5 Received (2 pages)\n" +
                "Time 47s: Job #5 Buffering Started\n" +
                "Time 50s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 60s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 20s: Job #6 Received (1 pages)\n" +
                "Time 60s: Job #6 Buffering Started\n" +
                "Time 63s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 68s: Job #6 Finished Printing\n" +
                "\n" +
                "Time 30s: Job #7 Received (4 pages)\n" +
                "Time 68s: Job #7 Buffering Started\n" +
                "Time 71s: Job #7 Finished Buffering and Started Printing\n" +
                "Time 91s: Job #7 Finished Printing\n" +
                "\n" +
                "Time 31s: Job #8 Received (1 pages)\n" +
                "Time 91s: Job #8 Buffering Started\n" +
                "Time 94s: Job #8 Finished Buffering and Started Printing\n" +
                "Time 99s: Job #8 Finished Printing\n" +
                "\n" +
                "Time 32s: Job #9 Received (1 pages)\n" +
                "Time 99s: Job #9 Buffering Started\n" +
                "Time 102s: Job #9 Finished Buffering and Started Printing\n" +
                "Time 107s: Job #9 Finished Printing\n" +
                "\n" +
                "Time 50s: Job #10 Received (2 pages)\n" +
                "Time 107s: Job #10 Buffering Started\n" +
                "Time 110s: Job #10 Finished Buffering and Started Printing\n" +
                "Time 120s: Job #10 Finished Printing\n" +
                "\n" +
                "Time 51s: Job #11 Received (2 pages)\n" +
                "Time 120s: Job #11 Buffering Started\n" +
                "Time 123s: Job #11 Finished Buffering and Started Printing\n" +
                "Time 133s: Job #11 Finished Printing\n" +
                "\n" +
                "Time 70s: Job #12 Received (5 pages)\n" +
                "Time 133s: Job #12 Buffering Started\n" +
                "Time 136s: Job #12 Finished Buffering and Started Printing\n" +
                "Time 161s: Job #12 Finished Printing\n" +
                "\n" +
                "Time 90s: Job #13 Received (2 pages)\n" +
                "Time 161s: Job #13 Buffering Started\n" +
                "Time 164s: Job #13 Finished Buffering and Started Printing\n" +
                "Time 174s: Job #13 Finished Printing\n" +
                "\n" +
                "Time 100s: Job #14 Received (1 pages)\n" +
                "Time 174s: Job #14 Buffering Started\n" +
                "Time 177s: Job #14 Finished Buffering and Started Printing\n" +
                "Time 182s: Job #14 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 14\n" +
                "Total Pages: 28\n" +
                "Average Job Wait Time: 47.3s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "turtle.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,1\n" +
                    "1,2\n" +
                    "2,1\n" +
                    "10,3\n" +
                    "11,2\n" +
                    "20,1\n" +
                    "30,4\n" +
                    "31,1\n" +
                    "32,1\n" +
                    "50,2\n" +
                    "51,2\n" +
                    "70,5\n" +
                    "90,2\n" +
                    "100,1");
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
        String input =  "printer.txt";

        String expected =   "Enter job file name: \n" +
                "Time 0s: Job #1 Received (1 pages)\n" +
                "Time 0s: Job #1 Buffering Started\n" +
                "Time 3s: Job #1 Finished Buffering and Started Printing\n" +
                "Time 8s: Job #1 Finished Printing\n" +
                "\n" +
                "Time 1s: Job #2 Received (1 pages)\n" +
                "Time 8s: Job #2 Buffering Started\n" +
                "Time 11s: Job #2 Finished Buffering and Started Printing\n" +
                "Time 16s: Job #2 Finished Printing\n" +
                "\n" +
                "Time 2s: Job #3 Received (2 pages)\n" +
                "Time 16s: Job #3 Buffering Started\n" +
                "Time 19s: Job #3 Finished Buffering and Started Printing\n" +
                "Time 29s: Job #3 Finished Printing\n" +
                "\n" +
                "Time 3s: Job #4 Received (2 pages)\n" +
                "Time 29s: Job #4 Buffering Started\n" +
                "Time 32s: Job #4 Finished Buffering and Started Printing\n" +
                "Time 42s: Job #4 Finished Printing\n" +
                "\n" +
                "Time 4s: Job #5 Received (3 pages)\n" +
                "Time 42s: Job #5 Buffering Started\n" +
                "Time 45s: Job #5 Finished Buffering and Started Printing\n" +
                "Time 60s: Job #5 Finished Printing\n" +
                "\n" +
                "Time 5s: Job #6 Received (3 pages)\n" +
                "Time 60s: Job #6 Buffering Started\n" +
                "Time 63s: Job #6 Finished Buffering and Started Printing\n" +
                "Time 78s: Job #6 Finished Printing\n" +
                "\n" +
                "Time 10s: Job #7 Received (4 pages)\n" +
                "Time 78s: Job #7 Buffering Started\n" +
                "Time 81s: Job #7 Finished Buffering and Started Printing\n" +
                "Time 101s: Job #7 Finished Printing\n" +
                "\n" +
                "Time 15s: Job #8 Received (5 pages)\n" +
                "Time 101s: Job #8 Buffering Started\n" +
                "Time 104s: Job #8 Finished Buffering and Started Printing\n" +
                "Time 129s: Job #8 Finished Printing\n" +
                "\n" +
                "Time 20s: Job #9 Received (2 pages)\n" +
                "Time 129s: Job #9 Buffering Started\n" +
                "Time 132s: Job #9 Finished Buffering and Started Printing\n" +
                "Time 142s: Job #9 Finished Printing\n" +
                "\n" +
                "Time 25s: Job #10 Received (2 pages)\n" +
                "Time 142s: Job #10 Buffering Started\n" +
                "Time 145s: Job #10 Finished Buffering and Started Printing\n" +
                "Time 155s: Job #10 Finished Printing\n" +
                "\n" +
                "Time 30s: Job #11 Received (1 pages)\n" +
                "Time 155s: Job #11 Buffering Started\n" +
                "Time 158s: Job #11 Finished Buffering and Started Printing\n" +
                "Time 163s: Job #11 Finished Printing\n" +
                "\n" +
                "Time 40s: Job #12 Received (6 pages)\n" +
                "Time 163s: Job #12 Buffering Started\n" +
                "Time 166s: Job #12 Finished Buffering and Started Printing\n" +
                "Time 196s: Job #12 Finished Printing\n" +
                "\n" +
                "Time 50s: Job #13 Received (3 pages)\n" +
                "Time 196s: Job #13 Buffering Started\n" +
                "Time 199s: Job #13 Finished Buffering and Started Printing\n" +
                "Time 214s: Job #13 Finished Printing\n" +
                "\n" +
                "Time 60s: Job #14 Received (4 pages)\n" +
                "Time 214s: Job #14 Buffering Started\n" +
                "Time 217s: Job #14 Finished Buffering and Started Printing\n" +
                "Time 237s: Job #14 Finished Printing\n" +
                "\n" +
                "Time 70s: Job #15 Received (2 pages)\n" +
                "Time 237s: Job #15 Buffering Started\n" +
                "Time 240s: Job #15 Finished Buffering and Started Printing\n" +
                "Time 250s: Job #15 Finished Printing\n" +
                "\n" +
                "Printing Simulation Complete.\n" +
                "Total Print Jobs: 15\n" +
                "Total Pages: 41\n" +
                "Average Job Wait Time: 85.3s\n" +
                "Idle Time: 0s";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Printer_Queue"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "printer.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("0,1\n" +
                    "1,1\n" +
                    "2,2\n" +
                    "3,2\n" +
                    "4,3\n" +
                    "5,3\n" +
                    "10,4\n" +
                    "15,5\n" +
                    "20,2\n" +
                    "25,2\n" +
                    "30,1\n" +
                    "40,6\n" +
                    "50,3\n" +
                    "60,4\n" +
                    "70,2");
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
