
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class DS6_PriorityQueue_Tests {
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
        String className = "DS6_PriorityQueue";
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
    @Test(timeout = 250)
    public void test1() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_PriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");

            Object check =  priorityQueue.getConstructor().newInstance();

            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertNull(poll.invoke(check));
            Assert.assertNull(element.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    int[][] adds = {{18, 5, 16, 5, 2, 16, 11, 21, 1, 26, 5, 10, 28},
            {6, 18, 8, 19, 1, 14, 18, 2, 13, 2, 7, 24},
            {21, 6, 8, 30, 20, 21, 21, 14, 29, 20},
            {11, 17, 13, 20, 15, 20},
            {16, 4, 5, 2, 14, 13, 1, 25},
            {23, 29, 4, 9, 8, 21, 17},
            {16, 16, 22, 13, 2, 23, 27, 4, 25, 27, 12, 4},
            {30, 14, 25, 27, 24, 7, 3},
            {6, 14, 4, 5, 26, 11, 21, 25, 14, 29},
            {21, 4, 23, 14, 22, 9, 11, 21, 7, 25, 1, 11},
            {28, 5, 14, 7, 3, 23, 21, 15, 16, 18, 6},
            {30, 16, 21, 12, 10, 5, 18, 26, 9},
            {6, 26, 30, 19, 26, 29, 16},
            {15, 24, 3, 14, 8, 18, 2, 29, 28, 27, 6},
            {14, 2, 23, 2, 12, 8, 11, 6, 8, 3, 3, 11},
            {16, 17, 27, 21, 24, 28, 5, 17},
            {24, 14, 5, 1, 30, 4, 9, 22, 23, 7, 11, 15},
            {10, 24, 24, 13, 26, 27},
            {1, 22, 2, 19, 8, 15, 17, 10, 11, 13, 2},
            {7, 15, 2, 9, 28, 9, 22, 12, 18, 27, 29, 6, 3}};
    String[] toStrings = {"[1, 2, 10, 5, 5, 11, 16, 21, 18, 26, 5, 16, 28]",
            "[1, 2, 8, 6, 2, 14, 18, 19, 13, 18, 7, 24]",
            "[6, 14, 8, 20, 20, 21, 21, 30, 29, 21]",
            "[11, 15, 13, 20, 17, 20]",
            "[1, 4, 2, 16, 14, 13, 5, 25]",
            "[4, 8, 17, 29, 9, 23, 21]",
            "[2, 4, 4, 13, 12, 22, 27, 16, 25, 27, 16, 23]",
            "[3, 24, 7, 30, 27, 25, 14]",
            "[4, 5, 6, 14, 26, 11, 21, 25, 14, 29]",
            "[1, 4, 9, 14, 7, 11, 11, 21, 21, 25, 22, 23]",
            "[3, 5, 14, 15, 6, 23, 21, 28, 16, 18, 7]",
            "[5, 9, 10, 12, 16, 21, 18, 30, 26]",
            "[6, 19, 16, 26, 26, 30, 29]",
            "[2, 6, 3, 24, 8, 18, 15, 29, 28, 27, 14]",
            "[2, 2, 8, 6, 3, 11, 11, 14, 8, 12, 3, 23]",
            "[5, 17, 16, 17, 24, 28, 27, 21]",
            "[1, 5, 4, 22, 7, 14, 9, 24, 23, 30, 11, 15]",
            "[10, 13, 24, 24, 26, 27]",
            "[1, 2, 2, 10, 8, 15, 17, 22, 11, 19, 13]",
            "[2, 9, 3, 12, 27, 6, 22, 15, 18, 28, 29, 9, 7]"};
    int[][] removes = {{1, 2, 5, 5, 5, 10, 11, 16, 16, 18},
            {1, 2, 2},
            {6, 8, 14, 20},
            {11},
            {},
            {4, 8},
            {2, 4},
            {3, 7, 14},
            {4, 5, 6, 11},
            {1, 4, 7, 9},
            {3, 5, 6, 7, 14, 15, 16},
            {5, 9, 10, 12, 16, 18},
            {6},
            {2, 3, 6, 8, 14, 15, 18, 24, 27},
            {2, 2, 3, 3, 6, 8, 8, 11},
            {5, 16, 17, 17, 21},
            {1, 4, 5, 7, 9, 11, 14, 15, 22, 23, 24},
            {10, 13, 24, 24},
            {1, 2, 2, 8, 10},
            {2, 3, 6}};
    String[] removeToStrings = {"[21, 26, 28]",
            "[6, 7, 8, 13, 18, 14, 18, 19, 24]",
            "[20, 21, 21, 29, 30, 21]",
            "[13, 15, 20, 20, 17]",
            "[1, 4, 2, 16, 14, 13, 5, 25]",
            "[9, 21, 17, 29, 23]",
            "[4, 12, 22, 13, 16, 23, 27, 16, 25, 27]",
            "[24, 27, 25, 30]",
            "[14, 14, 21, 25, 26, 29]",
            "[11, 14, 11, 21, 22, 25, 21, 23]",
            "[18, 23, 21, 28]",
            "[21, 26, 30]",
            "[16, 19, 29, 26, 26, 30]",
            "[28, 29]",
            "[11, 12, 23, 14]",
            "[24, 27, 28]",
            "[30]",
            "[26, 27]",
            "[11, 13, 15, 22, 17, 19]",
            "[7, 9, 9, 12, 27, 29, 22, 15, 18, 28]"};

    @Test(timeout = 250)
    public void test2() throws Exception {
        try
        {
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_PriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");

            Object check =  priorityQueue.getConstructor().newInstance();

            offer.invoke(check,"tina");
            offer.invoke(check,"alex");
            offer.invoke(check,"jack");

            Assert.assertFalse((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(3,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals("alex",get.invoke(check,0));
            Assert.assertEquals("tina",get.invoke(check,1));
            Assert.assertEquals("jack",get.invoke(check,2));
            Assert.assertEquals("alex",element.invoke(check));
            Assert.assertEquals("alex",poll.invoke(check));
            Assert.assertEquals(2,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals("jack",element.invoke(check));
            Assert.assertEquals("jack",poll.invoke(check));
            Assert.assertEquals(1,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals("tina",element.invoke(check));
            Assert.assertFalse((Boolean)isEmpty.invoke(check));
            clear.invoke(check);
            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertNull(poll.invoke(check));

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
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_PriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");


            for(int x=0; x<adds.length; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();

                for(int v:adds[x])
                    offer.invoke(check,v);

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(adds[x].length,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(toStrings[x],toString.invoke(check));
            }
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
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_PriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();

                for(int v:adds[x])
                    offer.invoke(check,v);

                for(int v:removes[x]) {
                    Assert.assertEquals(v, ((Integer) element.invoke(check)).intValue());
                    Assert.assertEquals(v, ((Integer) poll.invoke(check)).intValue());
                }

                Assert.assertEquals(removeToStrings[x],toString.invoke(check));
            }
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
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_PriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();

                for(int v:adds[x])
                    offer.invoke(check,v);

                clear.invoke(check);

                Assert.assertTrue((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
                Assert.assertNull(element.invoke(check));
                Assert.assertNull(poll.invoke(check));
            }
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
            Class<?> priorityQueue = Class.forName(generateClassName("DS6_PriorityQueue"));
            Method isEmpty = priorityQueue.getMethod("isEmpty");
            Method offer = priorityQueue.getMethod("offer",Comparable.class);
            Method poll = priorityQueue.getMethod("poll");
            Method size = priorityQueue.getMethod("size");
            Method clear = priorityQueue.getMethod("clear");
            Method get = priorityQueue.getMethod("get", int.class);
            Method element = priorityQueue.getMethod("element");
            Method toString = priorityQueue.getMethod("toString");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  priorityQueue.getConstructor().newInstance();


                for(int v:adds[x]) {
                    offer.invoke(check, v);

                }

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(adds[x].length,((Integer)size.invoke(check)).intValue());

            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

}
