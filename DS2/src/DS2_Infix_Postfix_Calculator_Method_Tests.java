
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DS2_Infix_Postfix_Calculator_Method_Tests {

    public String generateClassName(String name) {
        if (getClass().toString().contains(".")) {
            return getClass().toString().substring(6, getClass().toString().lastIndexOf(".") + 1) + name;
        }
        return name;
    }

    @Test(timeout = 250)
    public void test1() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method infixToPostfix = classRefItP.getMethod("infixToPostfix",String.class);
            String actual= (String)infixToPostfix.invoke(infixToPostfix, "( 7.5 ) - 2 ^ 3 * 1.2");
            
            Assert.assertEquals("7.5 2 3 ^ 1.2 * -",actual);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method infixToPostfix = classRefItP.getMethod("infixToPostfix",String.class);
            String actual= (String)infixToPostfix.invoke(infixToPostfix, "( 10 - 2.5 ) / ( 3 + 1.5 ^ 2 )");

            Assert.assertEquals("10 2.5 - 3 1.5 2 ^ + /",actual);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method infixToPostfix = classRefItP.getMethod("infixToPostfix",String.class);
            String actual= (String)infixToPostfix.invoke(infixToPostfix, "4 ^ 1.5 + 6 * ( 2.2 - 1 )");

            Assert.assertEquals("4 1.5 ^ 6 2.2 1 - * +",actual);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method infixToPostfix = classRefItP.getMethod("infixToPostfix",String.class);
            String actual= (String)infixToPostfix.invoke(infixToPostfix, "( 8.4 + 6 ) / ( 3 - 1.2 ) ^ 2");

            Assert.assertEquals("8.4 6 + 3 1.2 - 2 ^ /",actual);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method infixToPostfix = classRefItP.getMethod("infixToPostfix",String.class);
            String actual= (String)infixToPostfix.invoke(infixToPostfix, "( 2.5 + 1.5 ) ^ 2 - 10 / ( 4 ^ 2.0 ) + 7 * 0.3");

            Assert.assertEquals("2.5 1.5 + 2 ^ 10 4 2.0 ^ / - 7 0.3 * +",actual);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method solvePostfix = classRefItP.getMethod("solvePostfix",String.class);
            double actual= (double) solvePostfix.invoke(solvePostfix, "2.5 1.5 + 2 ^ 10 4 2.0 ^ / - 7 0.3 * +");

            Assert.assertEquals(17.475,actual,.01);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method solvePostfix = classRefItP.getMethod("solvePostfix",String.class);
            double actual= (double) solvePostfix.invoke(solvePostfix, "3 5.5 + 2 3 ^ * 4.2 -");

            Assert.assertEquals(63.8,actual,.01);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method solvePostfix = classRefItP.getMethod("solvePostfix",String.class);
            double actual= (double) solvePostfix.invoke(solvePostfix, "9 2 1.5 + / 3.2 2 ^ +");

            Assert.assertEquals(12.8114285714,actual,.01);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method solvePostfix = classRefItP.getMethod("solvePostfix",String.class);
            double actual= (double) solvePostfix.invoke(solvePostfix, "12 2.5 2 ^ - 7 3.5 - / 6 0.5 ^ +");

            Assert.assertEquals(4.0923468856,actual,.01);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception{
        try
        {
            Class<?> classRefItP = Class.forName(generateClassName("DS2_Infix_Postfix_Calculator"));

            Method solvePostfix = classRefItP.getMethod("solvePostfix",String.class);
            double actual= (double) solvePostfix.invoke(solvePostfix, "5.5 4 1.2 - 2 ^ * 2 3 0.5 - ^ -");

            Assert.assertEquals(37.4631457505,actual,.01);
        }
        catch (InvocationTargetException e) {
            throw (Exception) e.getCause();
        }
    }

}
