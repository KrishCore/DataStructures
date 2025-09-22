
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class DS2_Infix_Postfix_Calculator_Lab_Tests
{
    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }

    public TestingOutputStream testingOutputStream = new TestingOutputStream();

    @Before
    public void setup()
    {
        System.setOut(testingOutputStream);

    }

    public String unifyLineSeperators(String line)
    {
        return line.replaceAll("\\n|\\r\\n", System.getProperty("line.separator")).trim();
    }

    @Test(timeout = 250)
    public void test1() throws Exception{
        String input =  "33 + 18.5";

        String expected =   "Enter an equation in infix form (separating values and operators with spaces): \n" +
                "Postfix Form: 33 18.5 +\n" +
                "Result: 51.50";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);
        try
        {
            main.invoke(classObject,(Object)null);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

        expected = unifyLineSeperators(expected);

        TestingOutputStream o = (TestingOutputStream)System.out;
        Assert.assertEquals(unifyLineSeperators(expected), unifyLineSeperators(o.toString()));
    }

    @Test(timeout = 250)
    public void test2() throws Exception{
        String input =  "( 8.4 + 6 ) / ( 3 - 1.2 )";

        String expected =   "Enter an equation in infix form (separating values and operators with spaces): \n" +
                "Postfix Form: 8.4 6 + 3 1.2 - /\n" +
                "Result: 8.00";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);
        try
        {
            main.invoke(classObject,(Object)null);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

        expected = unifyLineSeperators(expected);

        TestingOutputStream o = (TestingOutputStream)System.out;
        Assert.assertEquals(unifyLineSeperators(expected), unifyLineSeperators(o.toString()));
    }

    @Test(timeout = 250)
    public void test3() throws Exception{
        String input =  "10 / ( 4 ^ 2.0 )";

        String expected =   "Enter an equation in infix form (separating values and operators with spaces): \n" +
                "Postfix Form: 10 4 2.0 ^ /\n" +
                "Result: 0.63";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);
        try
        {
            main.invoke(classObject,(Object)null);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

        expected = unifyLineSeperators(expected);

        TestingOutputStream o = (TestingOutputStream)System.out;
        Assert.assertEquals(unifyLineSeperators(expected), unifyLineSeperators(o.toString()));
    }

    @Test(timeout = 250)
    public void test4() throws Exception{
        String input =  "12.2 / ( 2 + 3.8 ) + ( 4 ^ 2 ) * ( 7 - 1.5 )";

        String expected =   "Enter an equation in infix form (separating values and operators with spaces): \n" +
                "Postfix Form: 12.2 2 3.8 + / 4 2 ^ 7 1.5 - * +\n" +
                "Result: 90.10";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);
        try
        {
            main.invoke(classObject,(Object)null);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

        expected = unifyLineSeperators(expected);

        TestingOutputStream o = (TestingOutputStream)System.out;
        Assert.assertEquals(unifyLineSeperators(expected), unifyLineSeperators(o.toString()));
    }

    @Test(timeout = 250)
    public void test5() throws Exception{
        String input =  "( 5 ^ 2.0 - 3.3 ) * ( 6 + 2.7 ) / ( 10 - ( 4 ^ 1.5 ) )";

        String expected =   "Enter an equation in infix form (separating values and operators with spaces): \n" +
                "Postfix Form: 5 2.0 ^ 3.3 - 6 2.7 + * 10 4 1.5 ^ - /\n" +
                "Result: 94.40";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);
        try
        {
            main.invoke(classObject,(Object)null);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

        expected = unifyLineSeperators(expected);

        TestingOutputStream o = (TestingOutputStream)System.out;
        Assert.assertEquals(unifyLineSeperators(expected), unifyLineSeperators(o.toString()));
    }

    @Test(timeout = 250)
    public void test6() throws Exception{
        String input =  "( 2.5 + 1.5 ) ^ 2 - 10 / ( 4 ^ 2.0 ) + 7 * 0.3";

        String expected =   "Enter an equation in infix form (separating values and operators with spaces): \n" +
                "Postfix Form: 2.5 1.5 + 2 ^ 10 4 2.0 ^ / - 7 0.3 * +\n" +
                "Result: 17.48";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);
        try
        {
            main.invoke(classObject,(Object)null);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }

        expected = unifyLineSeperators(expected);

        TestingOutputStream o = (TestingOutputStream)System.out;
        Assert.assertEquals(unifyLineSeperators(expected), unifyLineSeperators(o.toString()));
    }
}
