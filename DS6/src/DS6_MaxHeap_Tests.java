
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class DS6_MaxHeap_Tests
{
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
        String className = "DS6_MaxHeap";
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

    int[][] adds = {{21, 14, 8, 16, 17, 16, 22, 30, 16, 24, 20, 12},
            {6, 19, 26, 15, 19, 14, 28, 28, 6, 20, 1, 14, 13},
            {22, 25, 26, 6, 5, 30, 28},
            {26, 17, 3, 22, 12, 24, 21, 30, 20, 27, 4},
            {25, 25, 3, 7, 22, 22, 28, 24, 2, 24, 11, 10, 28},
            {14, 28, 10, 3, 18, 22, 5, 20, 30},
            {16, 22, 3, 16, 17, 21, 22, 4},
            {9, 9, 11, 6, 27, 18, 23, 18, 13, 3, 3, 22},
            {3, 26, 7, 10, 1, 28, 17, 15, 16},
            {21, 14, 3, 30, 16, 12, 21, 12, 27},
            {2, 10, 16, 30, 27, 28},
            {11, 28, 2, 16, 28, 29},
            {18, 9, 30, 12, 6, 27, 25},
            {27, 17, 18, 14, 8, 12, 14, 30, 13, 9, 17},
            {9, 29, 1, 16, 1, 19, 13, 25, 3, 16},
            {27, 3, 2, 7, 7, 20, 12, 4, 12, 15},
            {23, 14, 12, 16, 19, 4, 1, 1, 8, 3, 22},
            {15, 10, 23, 4, 25, 22},
            {2, 25, 3, 4, 22, 24, 12, 16},
            {2, 3, 27, 14, 4, 16, 30, 30, 28, 7, 25, 18, 27}};
    String[] addsToStrings = {"[30, 24, 21, 17, 22, 12, 16, 14, 16, 16, 20, 8]",
            "[28, 28, 26, 19, 20, 14, 19, 6, 6, 15, 1, 14, 13]",
            "[30, 22, 28, 6, 5, 25, 26]",
            "[30, 27, 24, 22, 26, 3, 21, 17, 20, 12, 4]",
            "[28, 25, 28, 24, 24, 25, 22, 7, 2, 22, 11, 3, 10]",
            "[30, 28, 22, 20, 14, 10, 5, 3, 18]",
            "[22, 17, 22, 16, 16, 3, 21, 4]",
            "[27, 18, 23, 13, 9, 22, 18, 6, 11, 3, 3, 9]",
            "[28, 16, 26, 15, 1, 7, 17, 3, 10]",
            "[30, 27, 21, 21, 16, 3, 12, 12, 14]",
            "[30, 27, 28, 2, 16, 10]",
            "[29, 28, 28, 11, 16, 2]",
            "[30, 12, 27, 9, 6, 18, 25]",
            "[30, 27, 18, 17, 17, 12, 14, 14, 13, 8, 9]",
            "[29, 25, 19, 16, 16, 1, 13, 9, 3, 1]",
            "[27, 15, 20, 7, 12, 2, 12, 3, 4, 7]",
            "[23, 22, 12, 14, 19, 4, 1, 1, 8, 3, 16]",
            "[25, 23, 22, 4, 10, 15]",
            "[25, 22, 24, 16, 4, 3, 12, 2]",
            "[30, 30, 27, 28, 25, 27, 16, 2, 14, 4, 7, 3, 18]"};
    int[][] removes = {{30, 24, 22, 21, 20, 17},
            {28, 28, 26, 20, 19},
            {30, 28, 26, 25},
            {30, 27, 26, 24, 22},
            {28, 28, 25, 25, 24},
            {30, 28, 22, 20, 18},
            {22, 22, 21, 17, 16, 16},
            {},
            {28},
            {30},
            {30, 28, 27},
            {29, 28, 28, 16, 11},
            {30, 27, 25, 18, 12},
            {30, 27, 18, 17, 17, 14, 14, 13, 12, 9},
            {},
            {27, 20, 15, 12, 12, 7, 7},
            {23, 22, 19, 16, 14, 12, 8, 4, 3},
            {25, 23},
            {25, 24, 22, 16, 12, 4, 3},
            {30, 30, 28, 27, 27, 25, 18, 16, 14}};
    String[] removeToStrings = {"[16, 16, 16, 14, 8, 12]",
            "[19, 15, 14, 6, 13, 6, 14, 1]",
            "[22, 6, 5]",
            "[21, 20, 4, 17, 12, 3]",
            "[24, 22, 22, 7, 2, 10, 11, 3]",
            "[14, 5, 10, 3]",
            "[4, 3]",
            "[27, 18, 23, 13, 9, 22, 18, 6, 11, 3, 3, 9]",
            "[26, 16, 17, 15, 1, 7, 10, 3]",
            "[27, 21, 21, 14, 16, 3, 12, 12]",
            "[16, 2, 10]",
            "[2]",
            "[9, 6]",
            "[8]",
            "[29, 25, 19, 16, 16, 1, 13, 9, 3, 1]",
            "[4, 3, 2]",
            "[1, 1]",
            "[22, 15, 10, 4]",
            "[2]",
            "[7, 3, 4, 2]"};

    @Test(timeout = 250)
    public void test1() throws Exception {
        try
        {
            Class<?> heap = Class.forName(generateClassName("DS6_MaxHeap"));
            Method isEmpty = heap.getMethod("isEmpty");
            Method insert = heap.getMethod("insert",Comparable.class);
            Method remove = heap.getMethod("remove");
            Method size = heap.getMethod("size");
            Method clear = heap.getMethod("clear");

            Object check =  heap.getConstructor().newInstance();

            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertNull(remove.invoke(check));
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
            Class<?> heap = Class.forName(generateClassName("DS6_MaxHeap"));
            Method isEmpty = heap.getMethod("isEmpty");
            Method insert = heap.getMethod("insert",Comparable.class);
            Method remove = heap.getMethod("remove");
            Method size = heap.getMethod("size");
            Method clear = heap.getMethod("clear");
            Method toString = heap.getMethod("toString");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  heap.getConstructor().newInstance();

                for(int v:adds[x])
                    insert.invoke(check,v);

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(adds[x].length,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(addsToStrings[x],toString.invoke(check));
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
            Class<?> heap = Class.forName(generateClassName("DS6_MaxHeap"));
            Method isEmpty = heap.getMethod("isEmpty");
            Method insert = heap.getMethod("insert",Comparable.class);
            Method remove = heap.getMethod("remove");
            Method size = heap.getMethod("size");
            Method clear = heap.getMethod("clear");
            Method toString = heap.getMethod("toString");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  heap.getConstructor().newInstance();

                for(int v:adds[x])
                    insert.invoke(check,v);

                for(int v:removes[x])
                    Assert.assertEquals(v,((Integer)remove.invoke(check)).intValue());

                Assert.assertEquals(removeToStrings[x],toString.invoke(check));
            }
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
            Class<?> heap = Class.forName(generateClassName("DS6_MaxHeap"));
            Method isEmpty = heap.getMethod("isEmpty");
            Method insert = heap.getMethod("insert",Comparable.class);
            Method remove = heap.getMethod("remove");
            Method size = heap.getMethod("size");
            Method clear = heap.getMethod("clear");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  heap.getConstructor().newInstance();

                for(int v:adds[x])
                    insert.invoke(check,v);

                clear.invoke(check);

                Assert.assertTrue((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
                Assert.assertNull(remove.invoke(check));
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
            Class<?> heap = Class.forName(generateClassName("DS6_MaxHeap"));
            Method isEmpty = heap.getMethod("isEmpty");
            Method insert = heap.getMethod("insert",Comparable.class);
            Method remove = heap.getMethod("remove");
            Method size = heap.getMethod("size");
            Method clear = heap.getMethod("clear");
            Method toString = heap.getMethod("toString");

            for(int x=0; x<adds.length; x++)
            {
                Object check =  heap.getConstructor().newInstance();

                int max = adds[x][0];
                for(int v:adds[x]) {
                    insert.invoke(check, v);
                    if(max < v)
                        max=v;
                }

                Assert.assertFalse((Boolean)isEmpty.invoke(check));
                Assert.assertEquals(adds[x].length,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(max,((Integer)remove.invoke(check)).intValue());
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

}
