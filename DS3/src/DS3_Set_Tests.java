
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DS3_Set_Tests {


    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }

    public ArrayList<String> allowedImports = new ArrayList<>();

    @Before
    public void setup()
    {
        allowedImports.add("java.util.ArrayList");
        allowedImports.add("java.util.Iterator");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "DS3_Set";
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

    // add
    @Test(timeout = 250)
    public void test1() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(set.add("Jane"));
            Assert.assertTrue(set.add("Ted"));
            Assert.assertTrue(set.add("Billy"));
            Assert.assertFalse(set.add("Jane"));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<Integer> set = (SetInterface<Integer>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(set.add(1));
            Assert.assertFalse(set.add(1));
            Assert.assertTrue(set.add(2));
            Assert.assertTrue(set.add(3));
            Assert.assertFalse(set.add(1));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // contains
    @Test(timeout = 250)
    public void test3() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertFalse(set.contains("Ted"));
            set.add("Ted");
            Assert.assertTrue(set.contains("Ted"));
            Assert.assertFalse(set.contains("Billy"));
            set.add("Jane");
            Assert.assertTrue(set.contains(new String("Jane")));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<Integer> set = (SetInterface<Integer>) classRef.getConstructor().newInstance();

        try
        {
            set.add(1);
            set.add(3);
            set.add(6);
            Assert.assertFalse(set.contains(2));
            Assert.assertFalse(set.contains(4));
            Assert.assertFalse(set.contains(5));


        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // size
    @Test(timeout = 250)
    public void test5() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertEquals(0,set.size());
            set.add("Ted");
            Assert.assertEquals(1,set.size());
            set.add("Jane");
            Assert.assertEquals(2,set.size());
            set.add("Jane");
            Assert.assertEquals(2,set.size());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<Integer> set = (SetInterface<Integer>) classRef.getConstructor().newInstance();

        try
        {
            set.add(1);
            Assert.assertEquals(1,set.size());
            set.add(3);
            Assert.assertEquals(2,set.size());
            set.add(6);
            Assert.assertEquals(3,set.size());
            set.add(19);
            Assert.assertEquals(4,set.size());
            set.add(3);
            Assert.assertEquals(4,set.size());

        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // isEmpty
    @Test(timeout = 250)
    public void test7() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(set.isEmpty());
            set.add("Ted");
            Assert.assertFalse(set.isEmpty());
            set.add("Jane");
            Assert.assertFalse(set.isEmpty());
            set.remove("Ted");
            Assert.assertFalse(set.isEmpty());
            set.remove("Jane");
            Assert.assertTrue(set.isEmpty());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<Integer> set = (SetInterface<Integer>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(set.isEmpty());
            set.add(1);
            Assert.assertFalse(set.isEmpty());
            set.add(3);
            Assert.assertFalse(set.isEmpty());
            set.clear();
            Assert.assertTrue(set.isEmpty());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // isEmpty
    @Test(timeout = 250)
    public void test9() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        try
        {
            Assert.assertTrue(set.isEmpty());
            set.add("Ted");
            set.add("Jane");
            set.add("Tina");
            set.clear();
            Assert.assertTrue(set.isEmpty());
            Assert.assertEquals(0,set.size());
            Assert.assertFalse(set.contains("Ted"));
            Assert.assertFalse(set.contains("Jane"));
            Assert.assertFalse(set.contains("Tina"));

        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // isEmpty
    @Test(timeout = 250)
    public void test10() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        try
        {
            set.add("Ted");
            set.add("Jane");
            Assert.assertFalse(set.remove("Billy"));
            Assert.assertTrue(set.remove("Jane"));
            Assert.assertTrue(set.remove("Ted"));
            Assert.assertFalse(set.remove("Jane"));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test11() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<Integer> set = (SetInterface<Integer>) classRef.getConstructor().newInstance();

        try
        {
            set.add(1);
            set.add(3);
            Assert.assertFalse(set.remove(0));
            Assert.assertFalse(set.remove(2));
            Assert.assertFalse(set.remove(4));
            Assert.assertFalse(set.remove(5));
            Assert.assertTrue(set.remove(1));
            Assert.assertTrue(set.remove(3));
            Assert.assertFalse(set.remove(1));
            Assert.assertFalse(set.remove(3));
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    // size
    @Test(timeout = 250)
    public void test12() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<String> set = (SetInterface<String>) classRef.getConstructor().newInstance();

        String[] arr_results = {"Ted", "Jane","Fred"};

        ArrayList<String> results = new ArrayList<>();
        for(String s:arr_results)
            results.add(s);

        try
        {
            set.add("Ted");
            set.add("Jane");
            set.add("Jane");
            set.add("Fred");
            set.add("Jack");
            set.remove("Jack");

            Iterator<String> it = set.iterator();

            while(it.hasNext())
            {
                String s=it.next();
                Assert.assertTrue(results.contains(s));
            }
            Assert.assertEquals(results.size(),set.size());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

    @Test(timeout = 250)
    public void test13() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS3_Set"));
        SetInterface<Integer> set = (SetInterface<Integer>) classRef.getConstructor().newInstance();

        int[] arr_results = {1,2,5,6,7,9};

        ArrayList<Integer> results = new ArrayList<>();
        for(Integer s:arr_results)
            results.add(s);

        try
        {
            set.add(10);
            set.add(1);
            set.add(2);
            set.add(3);
            set.add(5);
            set.add(6);
            set.add(7);
            set.add(9);
            set.remove(10);
            set.remove(3);

            Iterator<Integer> it = set.iterator();

            while(it.hasNext())
            {
                Integer s=it.next();

                Assert.assertTrue(results.contains(s));
            }
            Assert.assertEquals(results.size(),set.size());
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
    }

}

