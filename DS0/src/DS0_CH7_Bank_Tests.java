
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DS0_CH7_Bank_Tests
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



    @Test(timeout = 250)
    public void test1() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Bank of Scrooge McDuck");

            Method getName = classRef.getMethod("getName");
            Method getAccounts = classRef.getMethod("getAccounts");


            Assert.assertEquals("Bank of Scrooge McDuck",getName.invoke(bank));

            ArrayList accounts = (ArrayList) getAccounts.invoke(bank);
            Assert.assertNotNull(accounts);
            Assert.assertEquals(0,accounts.size());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Dimes and Quarters");


            Method getName = classRef.getMethod("getName");
            Method getAccounts = classRef.getMethod("getAccounts");

            Assert.assertEquals("Dimes and Quarters",getName.invoke(bank));
            ArrayList accounts =  (ArrayList) getAccounts.invoke(bank);
            Assert.assertNotNull(accounts);
            Assert.assertEquals(0,accounts.size());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Bank of Scrooge McDuck");


            Method getName = classRef.getMethod("getName");
            Method setName = classRef.getMethod("setName",String.class);

            setName.invoke(bank,"Turtles");
            Assert.assertEquals("Turtles",getName.invoke(bank));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Bank of Scrooge McDuck");


            Method getName = classRef.getMethod("getName");
            Method setName = classRef.getMethod("setName",String.class);

            setName.invoke(bank,"Money Town");
            Assert.assertEquals("Money Town",getName.invoke(bank));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Class<?> accountRef = Class.forName(generateClassName("DS0_CH7_Account"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Piggy's Bank");


            Method openAccount = classRef.getMethod("openAccount",long.class,String.class,double.class);
            Method getAccounts = classRef.getMethod("getAccounts");
            Method getAccount = classRef.getMethod("getAccount", long.class);

            Method getAccountNumber = accountRef.getMethod("getAccountNumber");
            Method getCustomerName = accountRef.getMethod("getCustomerName");
            Method getBalance = accountRef.getMethod("getBalance");

            Assert.assertEquals(true,openAccount.invoke(bank,12345678L,"Billy Smith",3500.98));
            Assert.assertEquals(true,openAccount.invoke(bank,14287569L,"Jane Long",99999.53));
            Assert.assertEquals(true,openAccount.invoke(bank,44458765L,"Lisa Turtle",87596.66));

            ArrayList accounts = (ArrayList)getAccounts.invoke(bank);
            Assert.assertNotNull(accounts);
            Assert.assertEquals(3,accounts.size());

            Object account1 = getAccount.invoke(bank,12345678);
            Assert.assertNotNull(account1);
            Assert.assertEquals(12345678L,(long)getAccountNumber.invoke(account1));
            Assert.assertEquals("Billy Smith",getCustomerName.invoke(account1));
            Assert.assertEquals(3500.98,(double)getBalance.invoke(account1),.001);

            Object account2 = getAccount.invoke(bank,14287569L);
            Assert.assertNotNull(account2);
            Assert.assertEquals(14287569L,(long)getAccountNumber.invoke(account2));
            Assert.assertEquals("Jane Long",getCustomerName.invoke(account2));
            Assert.assertEquals(99999.53,(double)getBalance.invoke(account2),.001);

            Object account3 = getAccount.invoke(bank,44458765L);
            Assert.assertNotNull(account3);
            Assert.assertEquals(44458765L,(long)getAccountNumber.invoke(account3));
            Assert.assertEquals("Lisa Turtle",getCustomerName.invoke(account3));
            Assert.assertEquals(87596.66,(double)getBalance.invoke(account3),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Piggy's Bank");


            Method openAccount = classRef.getMethod("openAccount",long.class,String.class,double.class);

            Assert.assertEquals(false,openAccount.invoke(bank,12345678L,"Billy Smith",-20));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Piggy's Bank");


            Method openAccount = classRef.getMethod("openAccount",long.class,String.class,double.class);

            Assert.assertEquals(false,openAccount.invoke(bank,12345678L,"Billy Smith",0));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Piggy's Bank");


            Method openAccount = classRef.getMethod("openAccount",long.class,String.class,double.class);

            Assert.assertEquals(true,openAccount.invoke(bank,12345678L,"Billy Smith",134));
            Assert.assertEquals(false,openAccount.invoke(bank,12345678L,"James Smith",4444));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }



    @Test(timeout = 250)
    public void test9() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Piggy's Bank");

            Method openAccount = classRef.getMethod("openAccount", long.class, String.class, double.class);
            Method getAccount = classRef.getMethod("getAccount", long.class);

            openAccount.invoke(bank, 12345678L, "Billy Smith", 589);

            Assert.assertNull(getAccount.invoke(bank, 14277569L));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception {
        try
        {


            Class<?> classRef = Class.forName(generateClassName("DS0_CH7_Bank"));
            Class<?> accountRef = Class.forName(generateClassName("DS0_CH7_Account"));

            Object bank = classRef.getConstructor(String.class).newInstance
                    ("Piggy's Bank");

            Method openAccount = classRef.getMethod("openAccount", long.class, String.class, double.class);
            Method getAccount = classRef.getMethod("getAccount", long.class);
            Method getAccounts = classRef.getMethod("getAccounts");
            Method closeAccount = classRef.getMethod("closeAccount", long.class);

            openAccount.invoke(bank, 12345678L, "Billy Joe", 111.11);
            openAccount.invoke(bank, 12299999L, "Alex Smith", 222.22);
            openAccount.invoke(bank, 33333333L, "Jane Timith", 333.33);

            Assert.assertEquals(222.22,(double)closeAccount.invoke(bank,12299999L),.001);

            ArrayList accounts = (ArrayList)getAccounts.invoke(bank);
            Assert.assertNotNull(accounts);
            Assert.assertEquals(2,accounts.size());

            Method getAccountNumber = accountRef.getMethod("getAccountNumber");
            Method getCustomerName = accountRef.getMethod("getCustomerName");
            Method getBalance = accountRef.getMethod("getBalance");

            Object account1 = getAccount.invoke(bank,12345678L);
            Assert.assertNotNull(account1);
            Assert.assertEquals(12345678L,(long)getAccountNumber.invoke(account1));
            Assert.assertEquals("Billy Joe",getCustomerName.invoke(account1));
            Assert.assertEquals(111.11,(double)getBalance.invoke(account1),.001);

            Object account2 = getAccount.invoke(bank,12299999L);
            Assert.assertNull(account2);

            Object account3 = getAccount.invoke(bank,33333333L);
            Assert.assertNotNull(account3);
            Assert.assertEquals(33333333L,(long)getAccountNumber.invoke(account3));
            Assert.assertEquals("Jane Timith",getCustomerName.invoke(account3));
            Assert.assertEquals(333.33,(double)getBalance.invoke(account3),.001);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

}
