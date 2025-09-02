
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DS0_CH7_Account_Tests
{
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



    // Constructor Tests
    @Test(timeout = 250)
    public void test1() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("123456764"),"Billy Smith",100.0);

        Method getCustomerName = classRef.getMethod("getCustomerName");
        Method getAccountNumber = classRef.getMethod("getAccountNumber");
        Method getBalance = classRef.getMethod("getBalance");


        try
        {
            Assert.assertEquals("Billy Smith",(String)getCustomerName.invoke(account));
            Assert.assertEquals(Long.parseLong("123456764"),(long)getAccountNumber.invoke(account));
            Assert.assertEquals(100.0,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("999999"),"Jane Doe",99975.99);

        Method getCustomerName = classRef.getMethod("getCustomerName");
        Method getAccountNumber = classRef.getMethod("getAccountNumber");
        Method getBalance = classRef.getMethod("getBalance");

        try
        {
            Assert.assertEquals("Jane Doe",(String)getCustomerName.invoke(account));
            Assert.assertEquals(Long.parseLong("999999"),(long)getAccountNumber.invoke(account));
            Assert.assertEquals(99975.99,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("432255"),"Todd Turtle",499563.25);

        Method getCustomerName = classRef.getMethod("getCustomerName");
        Method getAccountNumber = classRef.getMethod("getAccountNumber");
        Method getBalance = classRef.getMethod("getBalance");

        try
        {
            Assert.assertEquals("Todd Turtle",(String)getCustomerName.invoke(account));
            Assert.assertEquals(Long.parseLong("432255"),(long)getAccountNumber.invoke(account));
            Assert.assertEquals(499563.25,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // Deposit Tests
    @Test(timeout = 250)
    public void test4() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("123456764"),"Billy Smith",100.0);

        Method getBalance = classRef.getMethod("getBalance");
        Method deposit = classRef.getMethod("deposit",double.class);
        try
        {
            Assert.assertEquals(false,deposit.invoke(account,-987));
            Assert.assertEquals(100.0,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("999999"),"Jane Doe",99975.99);

        Method getBalance = classRef.getMethod("getBalance");
        Method deposit = classRef.getMethod("deposit",double.class);
        try
        {
            Assert.assertEquals(false,deposit.invoke(account,0));
            Assert.assertEquals(99975.99,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("432255"),"Todd Turtle",499563.25);

        Method getBalance = classRef.getMethod("getBalance");
        Method deposit = classRef.getMethod("deposit",double.class);
        try
        {
            Assert.assertEquals(true,deposit.invoke(account,987));
            Assert.assertEquals(500550.25,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // Deposit Tests
    @Test(timeout = 250)
    public void test7() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("123456764"),"Billy Smith",100.0);

        Method getBalance = classRef.getMethod("getBalance");
        Method withdraw = classRef.getMethod("withdraw",double.class);
        try
        {
            Assert.assertEquals(false,withdraw.invoke(account,-987));
            Assert.assertEquals(100.0,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("999999"),"Jane Doe",99975.99);

        Method getBalance = classRef.getMethod("getBalance");
        Method withdraw = classRef.getMethod("withdraw",double.class);
        try
        {
            Assert.assertEquals(false,withdraw.invoke(account,0));
            Assert.assertEquals(99975.99,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("432255"),"Todd Turtle",499563.25);

        Method getBalance = classRef.getMethod("getBalance");
        Method withdraw = classRef.getMethod("withdraw",double.class);
        try
        {
            Assert.assertEquals(true,withdraw.invoke(account,235400));
            Assert.assertEquals(264163.25,(double)getBalance.invoke(account),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception
    {
        Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Account"));
        Object account = classRef.getConstructor(long.class,String.class,double.class).newInstance
                (Long.parseLong("432255"),"Todd Turtle",499563.25);

        Method getBalance = classRef.getMethod("getBalance");
        Method withdraw = classRef.getMethod("withdraw",double.class);
        try
        {
            Assert.assertEquals(false,withdraw.invoke(account,800000));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
