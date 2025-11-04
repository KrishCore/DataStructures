
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class DS4_LinkedList_Tests
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
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "DS4_LinkedList";
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
    public void arrayCheck() throws Exception{
        checkImports();
        String className = "DS4_LinkedList";
        String fileName = "src/"+generateClassName(className).replaceAll("\\.","/")+".java";
        boolean allowedOnly = true;
        ArrayList<String> invalidImport = new ArrayList<>();
        try
        {
            File file = new File(fileName);
            Scanner fromFile = new Scanner(file);
            String allLines="";
            while(fromFile.hasNextLine())
            {
                allLines += fromFile.nextLine().trim();

            }
            Assert.assertFalse("You code may not include any arrays",allLines.matches(".*[^\\\"]\\[(\\s|\\n)*\\]($|[^\\\"]+).*"));
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
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();

            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertNull(getFirstNode.invoke(check));
            Assert.assertNull( getLastNode.invoke(check));
            Assert.assertEquals(0,size.invoke(check));
            Assert.assertEquals("[]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor(Object.class).newInstance(1);

            Assert.assertFalse((boolean)isEmpty.invoke(check));
            Assert.assertNotNull(getFirstNode.invoke(check));
            Assert.assertNotNull(getLastNode.invoke(check));
            Assert.assertNull(getNext.invoke(getLastNode.invoke(check)));
            Assert.assertNull(getNext.invoke(getFirstNode.invoke(check)));
            Assert.assertTrue(getFirstNode.invoke(check)==getLastNode.invoke(check));
            Assert.assertEquals(1,getFirst.invoke(check));
            Assert.assertEquals(1,getLast.invoke(check));
            Assert.assertEquals(1,size.invoke(check));
            Assert.assertEquals("[1]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(1);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,5);

            Assert.assertFalse((boolean)isEmpty.invoke(check));
            Assert.assertNotNull(getFirstNode.invoke(check));
            Assert.assertNotNull(getLastNode.invoke(check));
            Assert.assertNull(getNext.invoke(getLastNode.invoke(check)));
            Assert.assertNull(getNext.invoke(getFirstNode.invoke(check)));
            Assert.assertTrue(getFirstNode.invoke(check)==getLastNode.invoke(check));
            Assert.assertEquals(5,getFirst.invoke(check));
            Assert.assertEquals(5,getLast.invoke(check));
            Assert.assertEquals(1,size.invoke(check));
            Assert.assertEquals("[5]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(5);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addLast.invoke(check,17);

            Assert.assertFalse((boolean)isEmpty.invoke(check));
            Assert.assertNotNull(getFirstNode.invoke(check));
            Assert.assertNotNull(getLastNode.invoke(check));
            Assert.assertNull(getNext.invoke(getLastNode.invoke(check)));
            Assert.assertNull(getNext.invoke(getFirstNode.invoke(check)));
            Assert.assertTrue(getFirstNode.invoke(check)==getLastNode.invoke(check));
            Assert.assertEquals(17,getFirst.invoke(check));
            Assert.assertEquals(17,getLast.invoke(check));
            Assert.assertEquals(1,size.invoke(check));
            Assert.assertEquals("[17]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(17);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;

                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addLast.invoke(check, thingsToAdd.get(x));
                    correct.add(thingsToAdd.get(x));
                }
                String message = "Called addLast multiples using these values:"+thingsToAdd;

                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,34);

            Assert.assertEquals(34,get.invoke(check,0));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(34);
            linkageCheck(correct,getFirstNode.invoke(check));

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;

                for(int x=0; x<correct.size(); x++)
                    Assert.assertEquals(message,correct.get(x),get.invoke(check,x));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,34);
            addLast.invoke(check,14);
            clear.invoke(check);

            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertNull(getFirstNode.invoke(check));
            Assert.assertNull( getLastNode.invoke(check));
            Assert.assertEquals(0,size.invoke(check));
            Assert.assertEquals("[]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,8);

            Assert.assertEquals(8,remove.invoke(check,0));
            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertNull(getFirstNode.invoke(check));
            Assert.assertNull( getLastNode.invoke(check));
            Assert.assertEquals(0,size.invoke(check));
            Assert.assertEquals("[]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test11() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;

                Assert.assertEquals(message,correct.remove(0),removeFirst.invoke(check));
                message += ", and then calling removeFirst()";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test12() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,8);

            Assert.assertEquals(8,removeLast.invoke(check));
            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertNull(getFirstNode.invoke(check));
            Assert.assertNull( getLastNode.invoke(check));
            Assert.assertEquals(0,size.invoke(check));
            Assert.assertEquals("[]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test13() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;

                Assert.assertEquals(message,correct.remove(correct.size()-1),removeLast.invoke(check));
                message += ", and then calling removeLast()";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test14() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;
                int index = (int)(Math.random()*correct.size());
                Assert.assertEquals(message,correct.remove(index),remove.invoke(check,index));
                message += ", and then calling remove("+index+")";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }


    @Test(timeout = 250)
    public void test15() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;
                int index = correct.size()-1;
                Assert.assertEquals(message,correct.remove(index),remove.invoke(check,index));
                message += ", and then calling remove("+index+")";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test15_1() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;
                int index = 0;
                Assert.assertEquals(message,correct.remove(index),remove.invoke(check,index));
                message += ", and then calling remove("+index+")";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test16() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,7);

            Assert.assertEquals(7,set.invoke(check,0,19));
            Assert.assertFalse((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(19,getFirst.invoke(check));
            Assert.assertEquals(19,getLast.invoke(check));
            Assert.assertEquals(1,size.invoke(check));
            Assert.assertEquals("[19]",toString.invoke(check));
            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(19);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test17() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;
                int index = (int)(Math.random()*correct.size());
                int value = (int)(Math.random()*100+1);
                Assert.assertEquals(message,correct.set(index,value),set.invoke(check,index,value));
                message += ", and then calling set("+index+","+value+")";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test18() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            add.invoke(check,0,55);

            Assert.assertFalse((boolean)isEmpty.invoke(check));
            Assert.assertNotNull(getFirstNode.invoke(check));
            Assert.assertNotNull(getLastNode.invoke(check));
            Assert.assertNull(getNext.invoke(getLastNode.invoke(check)));
            Assert.assertNull(getNext.invoke(getFirstNode.invoke(check)));
            Assert.assertTrue(getFirstNode.invoke(check)==getLastNode.invoke(check));
            Assert.assertEquals(55,getFirst.invoke(check));
            Assert.assertEquals(55,getLast.invoke(check));
            Assert.assertEquals(1,size.invoke(check));
            Assert.assertEquals("[55]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(55);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test19() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,63);
            add.invoke(check,0,55);

            Assert.assertFalse((boolean)isEmpty.invoke(check));
            Assert.assertNotNull(getFirstNode.invoke(check));
            Assert.assertNotNull(getLastNode.invoke(check));
            Assert.assertNull(getNext.invoke(getLastNode.invoke(check)));
            Assert.assertNotNull(getNext.invoke(getFirstNode.invoke(check)));
            Assert.assertEquals(55,getFirst.invoke(check));
            Assert.assertEquals(63,getLast.invoke(check));
            Assert.assertEquals(2,size.invoke(check));
            Assert.assertEquals("[55, 63]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(55);
            correct.add(63);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test20() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            Object check =  ll.getConstructor().newInstance();
            addFirst.invoke(check,63);
            add.invoke(check,1,55);

            Assert.assertFalse((boolean)isEmpty.invoke(check));
            Assert.assertNotNull(getFirstNode.invoke(check));
            Assert.assertNotNull(getLastNode.invoke(check));
            Assert.assertNull(getNext.invoke(getLastNode.invoke(check)));
            Assert.assertNotNull(getNext.invoke(getFirstNode.invoke(check)));
            Assert.assertEquals(63,getFirst.invoke(check));
            Assert.assertEquals(55,getLast.invoke(check));
            Assert.assertEquals(2,size.invoke(check));
            Assert.assertEquals("[63, 55]",toString.invoke(check));

            ArrayList<Integer> correct = new ArrayList<>();
            correct.add(63);
            correct.add(55);
            linkageCheck(correct,getFirstNode.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test21() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;
                int index = (int)(Math.random()*correct.size());
                int value = (int)(Math.random()*100);
                correct.add(index,value);
                add.invoke(check,index,value);
                message += ", and then calling add("+index+","+value+")";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test22() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;

                int index = 0;
                int value = (int)(Math.random()*100);
                correct.add(index,value);
                add.invoke(check,index,value);
                message += ", and then calling add("+index+","+value+")";

                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test23() throws Exception {
        checkImports();
        try
        {
            Class<?> ll = Class.forName(generateClassName("DS4_LinkedList"));
            Method getFirstNode = ll.getMethod("getFirstNode");
            Method getLastNode = ll.getMethod("getLastNode");
            Method getFirst = ll.getMethod("getFirst");
            Method getLast = ll.getMethod("getLast");
            Method removeFirst = ll.getMethod("removeFirst");
            Method removeLast = ll.getMethod("removeLast");
            Method addFirst = ll.getMethod("addFirst",Object.class);
            Method addLast = ll.getMethod("addLast",Object.class);
            Method clear = ll.getMethod("clear");
            Method size = ll.getMethod("size");
            Method get = ll.getMethod("get",int.class);
            Method add = ll.getMethod("add",int.class,Object.class);
            Method remove = ll.getMethod("remove",int.class);
            Method set = ll.getMethod("set",int.class,Object.class);
            Method isEmpty = ll.getMethod("isEmpty");
            Method toString = ll.getMethod("toString");

            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int w=0; w<10;w++)
            {
                Object check =  ll.getConstructor().newInstance();

                int numAdds= (int) (Math.random() * 4) + 3;
                ArrayList<Integer> thingsToAdd = new ArrayList<>();
                for (int x = 0; x < numAdds;x++)
                {
                    int v = (int) (Math.random() * 35) + 1;
                    thingsToAdd.add(v);
                }
                ArrayList<Integer> correct = new ArrayList<>();
                for(int x = 0; x<thingsToAdd.size();x++) {
                    addFirst.invoke(check, thingsToAdd.get(x));
                    correct.add(0,thingsToAdd.get(x));
                }
                String message = "Called addFirst multiples using these values:"+thingsToAdd;
                int index = correct.size()-1;
                int value = (int)(Math.random()*100);
                correct.add(index,value);
                add.invoke(check,index,value);
                message += ", and then calling add("+index+","+value+")";
                assertFalse(message,(Boolean)isEmpty.invoke(check));
                assertNotNull(message, getFirstNode.invoke(check));
                assertNotNull(message, getLastNode.invoke(check));
                assertNull(message, getNext.invoke(getLastNode.invoke(check)));
                assertEquals(message,correct.get(0),getFirst.invoke(check));
                assertEquals(message,correct.get(correct.size()-1),getLast.invoke(check));
                assertEquals(message,correct.size(),size.invoke(check));
                assertEquals(message,correct.toString(),toString.invoke(check));

                linkageCheck(correct,getFirstNode.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    public void linkageCheck(ArrayList<Integer> correct, Object cur) throws Exception
    {
        if(correct.size()!=0)
        {
            Class<?> lln = Class.forName(generateClassName("DS4_LinkedList_Node"));
            Method getData = lln.getMethod("getData");
            Method getNext = lln.getMethod("getNext");

            for(int x=0; x<correct.size(); x++)
            {
                Assert.assertEquals(correct.get(x),getData.invoke(cur));
                cur = (Object)getNext.invoke(cur);
            }
            Assert.assertNull(cur);
        }
    }
}
