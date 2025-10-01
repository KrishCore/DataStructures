
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class DS3_Bank_Tests {
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
        String input =  "A.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 123456789\n" +
                "\tAccount 123456789 opened with balance 0.0\n" +
                "DEPOSIT 123456789 250\n" +
                "\tDeposited 250.0 into 123456789, new balance 250.0\n" +
                "OPEN 987654321\n" +
                "\tAccount 987654321 opened with balance 0.0\n" +
                "DEPOSIT 987654321 400\n" +
                "\tDeposited 400.0 into 987654321, new balance 400.0\n" +
                "WITHDRAW 123456789 100\n" +
                "\tWithdrew 100.0 from 123456789, new balance 150.0\n" +
                "CLOSE 987654321\n" +
                "\tAccount 987654321 closed";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "A.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 123456789\n" +
                    "DEPOSIT 123456789 250\n" +
                    "OPEN 987654321\n" +
                    "DEPOSIT 987654321 400\n" +
                    "WITHDRAW 123456789 100\n" +
                    "CLOSE 987654321");
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
        String input =  "B.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 100000001\n" +
                "\tAccount 100000001 opened with balance 0.0\n" +
                "OPEN 100000001\n" +
                "\tAccount 100000001 already exists\n" +
                "DEPOSIT 100000001 500\n" +
                "\tDeposited 500.0 into 100000001, new balance 500.0\n" +
                "WITHDRAW 100000001 200\n" +
                "\tWithdrew 200.0 from 100000001, new balance 300.0\n" +
                "WITHDRAW 100000001 400\n" +
                "\tInsufficient funds for withdrawal from 100000001\n" +
                "CLOSE 100000001\n" +
                "\tAccount 100000001 closed\n" +
                "DEPOSIT 100000001 100\n" +
                "\tAccount not found for deposit into 100000001\n" +
                "WITHDRAW 100000001 50\n" +
                "\tAccount not found for withdrawal from 100000001\n" +
                "CLOSE 100000001\n" +
                "\tAccount not found for closing 100000001\n" +
                "OPEN 200000002\n" +
                "\tAccount 200000002 opened with balance 0.0\n" +
                "WITHDRAW 200000002 50\n" +
                "\tInsufficient funds for withdrawal from 200000002\n" +
                "DEPOSIT 200000002 150\n" +
                "\tDeposited 150.0 into 200000002, new balance 150.0\n" +
                "WITHDRAW 200000002 100\n" +
                "\tWithdrew 100.0 from 200000002, new balance 50.0\n" +
                "OPEN 200000002\n" +
                "\tAccount 200000002 already exists\n" +
                "CLOSE 300000003\n" +
                "\tAccount not found for closing 300000003";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "B.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 100000001\n" +
                    "OPEN 100000001\n" +
                    "DEPOSIT 100000001 500\n" +
                    "WITHDRAW 100000001 200\n" +
                    "WITHDRAW 100000001 400\n" +
                    "CLOSE 100000001\n" +
                    "DEPOSIT 100000001 100\n" +
                    "WITHDRAW 100000001 50\n" +
                    "CLOSE 100000001\n" +
                    "OPEN 200000002\n" +
                    "WITHDRAW 200000002 50\n" +
                    "DEPOSIT 200000002 150\n" +
                    "WITHDRAW 200000002 100\n" +
                    "OPEN 200000002\n" +
                    "CLOSE 300000003");
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
        String input =  "C.txt";

        String expected =   "Enter the file name: \n" +
                "CLOSE 999999999\n" +
                "\tAccount not found for closing 999999999";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "C.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("CLOSE 999999999");
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
        String input =  "bank.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 111111111\n" +
                "\tAccount 111111111 opened with balance 0.0\n" +
                "OPEN 222222222\n" +
                "\tAccount 222222222 opened with balance 0.0\n" +
                "OPEN 333333333\n" +
                "\tAccount 333333333 opened with balance 0.0\n" +
                "DEPOSIT 111111111 100\n" +
                "\tDeposited 100.0 into 111111111, new balance 100.0\n" +
                "DEPOSIT 222222222 200\n" +
                "\tDeposited 200.0 into 222222222, new balance 200.0\n" +
                "DEPOSIT 333333333 300\n" +
                "\tDeposited 300.0 into 333333333, new balance 300.0\n" +
                "WITHDRAW 111111111 50\n" +
                "\tWithdrew 50.0 from 111111111, new balance 50.0\n" +
                "WITHDRAW 222222222 250\n" +
                "\tInsufficient funds for withdrawal from 222222222\n" +
                "WITHDRAW 333333333 100\n" +
                "\tWithdrew 100.0 from 333333333, new balance 200.0\n" +
                "OPEN 111111111\n" +
                "\tAccount 111111111 already exists\n" +
                "CLOSE 222222222\n" +
                "\tAccount 222222222 closed\n" +
                "WITHDRAW 222222222 50\n" +
                "\tAccount not found for withdrawal from 222222222\n" +
                "DEPOSIT 444444444 400\n" +
                "\tAccount not found for deposit into 444444444\n" +
                "OPEN 444444444\n" +
                "\tAccount 444444444 opened with balance 0.0\n" +
                "DEPOSIT 444444444 400\n" +
                "\tDeposited 400.0 into 444444444, new balance 400.0\n" +
                "WITHDRAW 444444444 100\n" +
                "\tWithdrew 100.0 from 444444444, new balance 300.0\n" +
                "CLOSE 111111111\n" +
                "\tAccount 111111111 closed\n" +
                "CLOSE 333333333\n" +
                "\tAccount 333333333 closed\n" +
                "WITHDRAW 333333333 50\n" +
                "\tAccount not found for withdrawal from 333333333\n" +
                "CLOSE 555555555\n" +
                "\tAccount not found for closing 555555555";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "bank.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 111111111\n" +
                    "OPEN 222222222\n" +
                    "OPEN 333333333\n" +
                    "DEPOSIT 111111111 100\n" +
                    "DEPOSIT 222222222 200\n" +
                    "DEPOSIT 333333333 300\n" +
                    "WITHDRAW 111111111 50\n" +
                    "WITHDRAW 222222222 250\n" +
                    "WITHDRAW 333333333 100\n" +
                    "OPEN 111111111\n" +
                    "CLOSE 222222222\n" +
                    "WITHDRAW 222222222 50\n" +
                    "DEPOSIT 444444444 400\n" +
                    "OPEN 444444444\n" +
                    "DEPOSIT 444444444 400\n" +
                    "WITHDRAW 444444444 100\n" +
                    "CLOSE 111111111\n" +
                    "CLOSE 333333333\n" +
                    "WITHDRAW 333333333 50\n" +
                    "CLOSE 555555555");
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
        String input =  "Accounts.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 101010101\n" +
                "\tAccount 101010101 opened with balance 0.0\n" +
                "OPEN 202020202\n" +
                "\tAccount 202020202 opened with balance 0.0\n" +
                "DEPOSIT 101010101 500\n" +
                "\tDeposited 500.0 into 101010101, new balance 500.0\n" +
                "DEPOSIT 202020202 200\n" +
                "\tDeposited 200.0 into 202020202, new balance 200.0\n" +
                "WITHDRAW 101010101 100\n" +
                "\tWithdrew 100.0 from 101010101, new balance 400.0\n" +
                "WITHDRAW 202020202 300\n" +
                "\tInsufficient funds for withdrawal from 202020202\n" +
                "OPEN 101010101\n" +
                "\tAccount 101010101 already exists\n" +
                "CLOSE 202020202\n" +
                "\tAccount 202020202 closed\n" +
                "WITHDRAW 202020202 50\n" +
                "\tAccount not found for withdrawal from 202020202\n" +
                "DEPOSIT 303030303 100\n" +
                "\tAccount not found for deposit into 303030303\n" +
                "OPEN 303030303\n" +
                "\tAccount 303030303 opened with balance 0.0\n" +
                "DEPOSIT 303030303 150\n" +
                "\tDeposited 150.0 into 303030303, new balance 150.0\n" +
                "WITHDRAW 303030303 50\n" +
                "\tWithdrew 50.0 from 303030303, new balance 100.0\n" +
                "CLOSE 101010101\n" +
                "\tAccount 101010101 closed\n" +
                "WITHDRAW 101010101 50\n" +
                "\tAccount not found for withdrawal from 101010101";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "Accounts.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 101010101\n" +
                    "OPEN 202020202\n" +
                    "DEPOSIT 101010101 500\n" +
                    "DEPOSIT 202020202 200\n" +
                    "WITHDRAW 101010101 100\n" +
                    "WITHDRAW 202020202 300\n" +
                    "OPEN 101010101\n" +
                    "CLOSE 202020202\n" +
                    "WITHDRAW 202020202 50\n" +
                    "DEPOSIT 303030303 100\n" +
                    "OPEN 303030303\n" +
                    "DEPOSIT 303030303 150\n" +
                    "WITHDRAW 303030303 50\n" +
                    "CLOSE 101010101\n" +
                    "WITHDRAW 101010101 50");
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
        String input =  "file.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 888888888\n" +
                "\tAccount 888888888 opened with balance 0.0\n" +
                "WITHDRAW 888888888 50\n" +
                "\tInsufficient funds for withdrawal from 888888888\n" +
                "DEPOSIT 888888888 100\n" +
                "\tDeposited 100.0 into 888888888, new balance 100.0\n" +
                "WITHDRAW 888888888 50\n" +
                "\tWithdrew 50.0 from 888888888, new balance 50.0\n" +
                "OPEN 999999999\n" +
                "\tAccount 999999999 opened with balance 0.0\n" +
                "DEPOSIT 999999999 200\n" +
                "\tDeposited 200.0 into 999999999, new balance 200.0\n" +
                "WITHDRAW 999999999 100\n" +
                "\tWithdrew 100.0 from 999999999, new balance 100.0\n" +
                "CLOSE 888888888\n" +
                "\tAccount 888888888 closed\n" +
                "DEPOSIT 888888888 20\n" +
                "\tAccount not found for deposit into 888888888\n" +
                "WITHDRAW 999999999 150\n" +
                "\tInsufficient funds for withdrawal from 999999999\n" +
                "CLOSE 999999999\n" +
                "\tAccount 999999999 closed\n" +
                "CLOSE 888888888\n" +
                "\tAccount not found for closing 888888888";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "file.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 888888888\n" +
                    "WITHDRAW 888888888 50\n" +
                    "DEPOSIT 888888888 100\n" +
                    "WITHDRAW 888888888 50\n" +
                    "OPEN 999999999\n" +
                    "DEPOSIT 999999999 200\n" +
                    "WITHDRAW 999999999 100\n" +
                    "CLOSE 888888888\n" +
                    "DEPOSIT 888888888 20\n" +
                    "WITHDRAW 999999999 150\n" +
                    "CLOSE 999999999\n" +
                    "CLOSE 888888888");
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
        String input =  "test 7.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 666666666\n" +
                "\tAccount 666666666 opened with balance 0.0\n" +
                "OPEN 777777777\n" +
                "\tAccount 777777777 opened with balance 0.0\n" +
                "DEPOSIT 666666666 500\n" +
                "\tDeposited 500.0 into 666666666, new balance 500.0\n" +
                "DEPOSIT 777777777 100\n" +
                "\tDeposited 100.0 into 777777777, new balance 100.0\n" +
                "WITHDRAW 666666666 600\n" +
                "\tInsufficient funds for withdrawal from 666666666\n" +
                "WITHDRAW 777777777 50\n" +
                "\tWithdrew 50.0 from 777777777, new balance 50.0\n" +
                "CLOSE 666666666\n" +
                "\tAccount 666666666 closed\n" +
                "WITHDRAW 666666666 50\n" +
                "\tAccount not found for withdrawal from 666666666\n" +
                "CLOSE 777777777\n" +
                "\tAccount 777777777 closed\n" +
                "WITHDRAW 777777777 10\n" +
                "\tAccount not found for withdrawal from 777777777";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "test 7.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 666666666\n" +
                    "OPEN 777777777\n" +
                    "DEPOSIT 666666666 500\n" +
                    "DEPOSIT 777777777 100\n" +
                    "WITHDRAW 666666666 600\n" +
                    "WITHDRAW 777777777 50\n" +
                    "CLOSE 666666666\n" +
                    "WITHDRAW 666666666 50\n" +
                    "CLOSE 777777777\n" +
                    "WITHDRAW 777777777 10");
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
        String input =  "two words.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 555555555\n" +
                "\tAccount 555555555 opened with balance 0.0\n" +
                "DEPOSIT 555555555 300\n" +
                "\tDeposited 300.0 into 555555555, new balance 300.0\n" +
                "OPEN 555555555\n" +
                "\tAccount 555555555 already exists\n" +
                "WITHDRAW 555555555 100\n" +
                "\tWithdrew 100.0 from 555555555, new balance 200.0\n" +
                "CLOSE 555555555\n" +
                "\tAccount 555555555 closed\n" +
                "DEPOSIT 555555555 50\n" +
                "\tAccount not found for deposit into 555555555\n" +
                "CLOSE 555555555\n" +
                "\tAccount not found for closing 555555555";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "two words.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 555555555\n" +
                    "DEPOSIT 555555555 300\n" +
                    "OPEN 555555555\n" +
                    "WITHDRAW 555555555 100\n" +
                    "CLOSE 555555555\n" +
                    "DEPOSIT 555555555 50\n" +
                    "CLOSE 555555555");
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
        String input =  "9.txt";

        String expected =   "Enter the file name: \n" +
                "OPEN 333333333\n" +
                "\tAccount 333333333 opened with balance 0.0\n" +
                "OPEN 444444444\n" +
                "\tAccount 444444444 opened with balance 0.0\n" +
                "DEPOSIT 333333333 150\n" +
                "\tDeposited 150.0 into 333333333, new balance 150.0\n" +
                "WITHDRAW 444444444 50\n" +
                "\tInsufficient funds for withdrawal from 444444444\n" +
                "DEPOSIT 444444444 200\n" +
                "\tDeposited 200.0 into 444444444, new balance 200.0\n" +
                "WITHDRAW 333333333 100\n" +
                "\tWithdrew 100.0 from 333333333, new balance 50.0\n" +
                "CLOSE 333333333\n" +
                "\tAccount 333333333 closed\n" +
                "CLOSE 444444444\n" +
                "\tAccount 444444444 closed";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "9.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("OPEN 333333333\n" +
                    "OPEN 444444444\n" +
                    "DEPOSIT 333333333 150\n" +
                    "WITHDRAW 444444444 50\n" +
                    "DEPOSIT 444444444 200\n" +
                    "WITHDRAW 333333333 100\n" +
                    "CLOSE 333333333\n" +
                    "CLOSE 444444444");
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
        String input =  "A.txt";

        String expected =   "Enter the file name: \n" +
                "DEPOSIT 222222222 200\n" +
                "\tAccount not found for deposit into 222222222\n" +
                "OPEN 222222222\n" +
                "\tAccount 222222222 opened with balance 0.0\n" +
                "DEPOSIT 222222222 200\n" +
                "\tDeposited 200.0 into 222222222, new balance 200.0\n" +
                "WITHDRAW 222222222 500\n" +
                "\tInsufficient funds for withdrawal from 222222222\n" +
                "WITHDRAW 222222222 100\n" +
                "\tWithdrew 100.0 from 222222222, new balance 100.0\n" +
                "CLOSE 222222222\n" +
                "\tAccount 222222222 closed";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_Bank"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "A.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("DEPOSIT 222222222 200\n" +
                    "OPEN 222222222\n" +
                    "DEPOSIT 222222222 200\n" +
                    "WITHDRAW 222222222 500\n" +
                    "WITHDRAW 222222222 100\n" +
                    "CLOSE 222222222");
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
