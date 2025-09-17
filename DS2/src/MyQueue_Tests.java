
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyQueue_Tests {
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
        String className = "MyQueue";
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
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            boolean expected = true;
            Assert.assertEquals(expected, queue.isEmpty());
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
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(5);
            boolean expected = false;
            Assert.assertEquals(expected, queue.isEmpty());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<String> queue = (QueueInterface<String>) classRef.getConstructor().newInstance();

            queue.offer("bob");
            queue.poll();
            queue.offer("bob");
            boolean expected = false;
            Assert.assertEquals(expected, queue.isEmpty());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<String> queue = (QueueInterface<String>) classRef.getConstructor().newInstance();

            queue.offer("bob");
            queue.poll();
            boolean expected = true;
            Assert.assertEquals(expected, queue.isEmpty());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            int expected = 0;
            Assert.assertEquals(expected, queue.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(5);

            int expected = 1;
            Assert.assertEquals(expected, queue.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(1);
            queue.offer(2);
            queue.offer(3);
            queue.offer(2);

            int expected = 4;
            Assert.assertEquals(expected, queue.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(1);
            queue.offer(2);
            queue.poll();
            queue.offer(3);
            queue.poll();
            queue.offer(2);

            int expected = 2;
            Assert.assertEquals(expected, queue.size());
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

            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(3);
            queue.offer(4);

            Assert.assertEquals(2,queue.size());
            Assert.assertEquals(3,queue.element().intValue());
            Assert.assertEquals(3,queue.element().intValue());
            Assert.assertEquals(2,queue.size());

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
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<String> queue = (QueueInterface<String>) classRef.getConstructor().newInstance();

            queue.offer("Joe");
            queue.offer("Tina");

            Assert.assertEquals(2,queue.size());
            queue.clear();
            Assert.assertEquals(0,queue.size());

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // poll tests
    @Test(timeout = 250)
    public void test11() throws Exception {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<String> queue = (QueueInterface<String>) classRef.getConstructor().newInstance();

            queue.offer("Joe");
            queue.offer("Tina");

            Assert.assertEquals(2,queue.size());
            Assert.assertEquals("Joe",queue.poll());
            Assert.assertEquals(1,queue.size());
            Assert.assertEquals("Tina",queue.poll());
            Assert.assertEquals(0,queue.size());

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
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(6);
            queue.offer(3);
            queue.offer(4);

            Assert.assertEquals(3,queue.size());
            Assert.assertEquals(6,queue.poll().intValue());
            Assert.assertEquals(2,queue.size());
            queue.offer(7);
            Assert.assertEquals(3,queue.size());
            Assert.assertEquals(3,queue.poll().intValue());
            Assert.assertEquals(2,queue.size());
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
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            queue.offer(6);
            queue.offer(3);
            queue.poll();
            queue.element();
            queue.offer(88);
            queue.offer(7);

            Assert.assertEquals("[3, 88, 7]",queue.toString());
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
            Class<?> classRef = Class.forName(generateClassName("MyQueue"));
            QueueInterface<Integer> queue = (QueueInterface<Integer>) classRef.getConstructor().newInstance();

            Assert.assertEquals("[]",queue.toString());
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
