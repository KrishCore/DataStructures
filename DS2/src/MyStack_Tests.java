
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyStack_Tests {
    public String generateClassName(String name) {
        if (getClass().toString().contains(".")) {
            return getClass().toString().substring(6, getClass().toString().lastIndexOf(".") + 1) + name;
        }
        return name;
    }

    public ArrayList<String> allowedImports = new ArrayList<>();

    @Before
    public void setup()
    {
        allowedImports.add("java.util.ArrayList");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "MyStack";
        String fileName = "src/"+generateClassName(className).replaceAll("\\.","/")+".java";
        boolean allowedOnly = true;
        ArrayList<String> invalidImport = new ArrayList<>();
        try
        {

            File file = new File(fileName);
            Scanner fromFile = new Scanner(file);
            while(fromFile.hasNextLine())
            {
                String line = fromFile.nextLine().trim();
                if(line.contains("import"))
                {
                    boolean good = false;
                    for(String allowed: allowedImports)
                    {
                        if(line.matches("\\s*import\\s+"+allowed+"\\s*;\s*(//\\.*)?"))
                            good=true;
                    }
                    if(!good)
                    {
                        allowedOnly=false;
                        invalidImport.add(line);
                    }
                }

            }
        }
        catch(Exception e)
        {
            Assert.assertTrue("Missing File: "+className+".java",false);
            allowedOnly = false;
        }

        Assert.assertTrue("Invalid imports: "+invalidImport,allowedOnly);
    }

    // is empty tests
    @Test(timeout = 250)
    public void test1() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            boolean expected = true;
            Assert.assertEquals(expected, stack.isEmpty());
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
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(5);
            boolean expected = false;
            Assert.assertEquals(expected, stack.isEmpty());
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

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<String> stack = (StackInterface<String>) classRef.getConstructor().newInstance();

            stack.push("bob");
            stack.pop();
            stack.push("bob");
            boolean expected = false;
            Assert.assertEquals(expected, stack.isEmpty());
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

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<String> stack = (StackInterface<String>) classRef.getConstructor().newInstance();

            stack.push("bob");
            stack.pop();
            boolean expected = true;
            Assert.assertEquals(expected, stack.isEmpty());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // size
    @Test(timeout = 250)
    public void test5() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            int expected = 0;
            Assert.assertEquals(expected, stack.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(5);

            int expected = 1;
            Assert.assertEquals(expected, stack.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(2);

            int expected = 4;
            Assert.assertEquals(expected, stack.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(1);
            stack.push(2);
            stack.pop();
            stack.push(3);
            stack.pop();
            stack.push(2);

            int expected = 2;
            Assert.assertEquals(expected, stack.size());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // peek tests
    @Test(timeout = 250)
    public void test9() throws Exception {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(3);
            stack.push(4);

            Assert.assertEquals(2,stack.size());
            Assert.assertEquals(4,stack.peek().intValue());
            Assert.assertEquals(4,stack.peek().intValue());
            Assert.assertEquals(2,stack.size());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // peek tests
    @Test(timeout = 250)
    public void test10() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<String> stack = (StackInterface<String>) classRef.getConstructor().newInstance();

            stack.push("Joe");
            stack.push("Tina");

            Assert.assertEquals(2,stack.size());
            stack.clear();
            Assert.assertEquals(0,stack.size());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // pop tests
    @Test(timeout = 250)
    public void test11() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<String> stack = (StackInterface<String>) classRef.getConstructor().newInstance();

            stack.push("Joe");
            stack.push("Tina");

            Assert.assertEquals(2,stack.size());
            Assert.assertEquals("Tina",stack.pop());
            Assert.assertEquals(1,stack.size());
            Assert.assertEquals("Joe",stack.pop());
            Assert.assertEquals(0,stack.size());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test12() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(6);
            stack.push(3);
            stack.push(4);

            Assert.assertEquals(3,stack.size());
            Assert.assertEquals(4,stack.pop().intValue());
            Assert.assertEquals(2,stack.size());
            stack.push(7);
            Assert.assertEquals(3,stack.size());
            Assert.assertEquals(7,stack.pop().intValue());
            Assert.assertEquals(2,stack.size());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    //toString
    @Test(timeout = 250)
    public void test13() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            stack.push(6);
            stack.push(3);
            stack.pop();
            stack.peek();
            stack.push(88);
            stack.push(7);

            Assert.assertEquals("[6, 88, 7]",stack.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test14() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyStack"));
            StackInterface<Integer> stack = (StackInterface<Integer>) classRef.getConstructor().newInstance();

            Assert.assertEquals("[]",stack.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
