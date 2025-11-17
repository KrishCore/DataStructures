
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DS5_BinarySearchTree_After_Removes_Tests
{

    public void buildTree(int[] adds,Object o) throws Exception
    {
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method insert = bst.getMethod("insert",Comparable.class);

            for (int add : adds)
                insert.invoke(o, add);
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

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
        String className = "DS5_BinarySearchTree";
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
    public void removeRootWithNoChildren() throws Exception
    {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            

            Object check =  bst.getConstructor(Comparable.class).newInstance(2);

            remove.invoke(check,2);

            Assert.assertNull(getRoot.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals(0,((Integer)height.invoke(check)).intValue());
            Assert.assertTrue((Boolean)isEmpty.invoke(check));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeRootWithOnlyRightChildren() throws Exception
    {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            Class<?> bstn = Class.forName(generateClassName("DS5_BinarySearchTree_Node"));
            Method getData = bstn.getMethod("getData");
            
            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                int numberOfAdds = (int)(Math.random()*10)+3;
                int[] adds = new int[numberOfAdds];
                int[] afterAdds = new int[numberOfAdds-1];
                int[] afterReverse = new int[numberOfAdds-1];
                adds[0]=(int)(Math.random()*10+1);
                for(int a=1;a<numberOfAdds;a++) {
                    adds[a] = adds[a - 1] + (int) (Math.random() * 3 + 1);
                    afterAdds[a-1] = adds[a];
                }

                int pos = afterAdds.length-1;
                for(int a:afterAdds)
                {
                    afterReverse[pos--]=a;
                }

                buildTree(adds,check);
                String message = "Failed after adding these values "+ Arrays.toString(adds);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,adds[0]));
                message+=" and then removing "+adds[0];

                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,adds[1],((Integer)getData.invoke(getRoot.invoke(check))).intValue());
                Assert.assertEquals(message,adds.length-1,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,adds.length-1,((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,adds.length-2,((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,adds[1],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,adds[adds.length-1],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,Arrays.toString(afterAdds), preOrder.invoke(check));
                Assert.assertEquals(message,Arrays.toString(afterAdds), inOrder.invoke(check));
                Assert.assertEquals(message,Arrays.toString(afterReverse), postOrder.invoke(check));
                Assert.assertFalse(message,(Boolean)contains.invoke(check,adds[0]));
                for(int d=0; d<afterAdds.length; d++)
                    Assert.assertEquals(message,d,((Integer)nodeDepth.invoke(check,afterAdds[d])).intValue());
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeRootWithOnlyLeftChildren() throws Exception
    {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            Class<?> bstn = Class.forName(generateClassName("DS5_BinarySearchTree_Node"));
            Method getData = bstn.getMethod("getData");

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                int numberOfAdds = (int)(Math.random()*10)+3;
                int[] adds = new int[numberOfAdds];
                int[] afterAdds = new int[numberOfAdds-1];
                int[] afterReverse = new int[numberOfAdds-1];
                adds[0]=(int)(Math.random()*100+50);
                for(int a=1;a<numberOfAdds;a++) {
                    adds[a] = adds[a - 1] - (int) (Math.random() * 3 + 1);
                    afterAdds[a-1] = adds[a];
                }

                int pos = afterAdds.length-1;
                for(int a:afterAdds)
                {
                    afterReverse[pos--]=a;
                }

                buildTree(adds,check);
                String message = "Failed after adding these values "+ Arrays.toString(adds);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,adds[0]));
                message+=" and then removing "+adds[0];
                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,adds[1],((Integer)getData.invoke(getRoot.invoke(check))).intValue());
                Assert.assertEquals(message,adds.length-1,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,adds.length-1,((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,adds.length-2,((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,adds[adds.length-1],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,adds[1],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,Arrays.toString(afterAdds), preOrder.invoke(check));
                Assert.assertEquals(message,Arrays.toString(afterReverse), inOrder.invoke(check));
                Assert.assertEquals(message,Arrays.toString(afterReverse), postOrder.invoke(check));
                Assert.assertFalse(message,(Boolean)contains.invoke(check,adds[0]));
                for(int d=0; d<afterAdds.length; d++)
                    Assert.assertEquals(message,d,((Integer)nodeDepth.invoke(check,afterAdds[d])).intValue());
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeAll() throws Exception
    {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            Class<?> bstn = Class.forName(generateClassName("DS5_BinarySearchTree_Node"));
            Method getData = bstn.getMethod("getData");

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                int numberOfAdds = (int)(Math.random()*10)+3;
                int[] adds = new int[numberOfAdds];
                int[] afterAdds = new int[numberOfAdds-1];
                int[] afterReverse = new int[numberOfAdds-1];
                adds[0]=(int)(Math.random()*100+50);
                for(int a=1;a<numberOfAdds;a++) {
                    adds[a] = adds[a - 1] - (int) (Math.random() * 3 + 1);
                    afterAdds[a-1] = adds[a];
                }

                int pos = afterAdds.length-1;
                for(int a:afterAdds)
                {
                    afterReverse[pos--]=a;
                }

                buildTree(adds,check);
                String message = "Failed after adding these values "+ Arrays.toString(adds);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,adds[0]));
                message+=" and then removing "+adds[0];
                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,adds[1],((Integer)getData.invoke(getRoot.invoke(check))).intValue());
                Assert.assertEquals(message,adds.length-1,((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,adds.length-1,((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,adds.length-2,((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,adds[adds.length-1],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,adds[1],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,Arrays.toString(afterAdds), preOrder.invoke(check));
                Assert.assertEquals(message,Arrays.toString(afterReverse), inOrder.invoke(check));
                Assert.assertEquals(message,Arrays.toString(afterReverse), postOrder.invoke(check));
                Assert.assertFalse(message,(Boolean)contains.invoke(check,adds[0]));
                for(int d=0; d<afterAdds.length; d++)
                    Assert.assertEquals(message,d,((Integer)nodeDepth.invoke(check,afterAdds[d])).intValue());
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeRootWithTwoChildren() throws Exception
    {
        checkImports();
        int[][] adds = {{10, 14, 15, 20, 8, 4},
                {10, 20, 6, 7, 18, 18, 18, 14},
                {10, 11, 8, 7, 6, 20},
                {10, 2, 1, 7, 12, 13},
                {10, 17, 19, 1, 12, 1, 16, 13},
                {10, 2, 2, 2, 17, 3},
                {10, 15, 9, 5, 2, 14, 5, 2, 6},
                {10, 19, 1, 10, 10, 14},
                {10, 20, 6, 13, 1, 1},
                {10, 1, 12, 7, 11, 15, 16, 7}};
        int[] removes = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String[] preOrdersPostRemoves = {"[14, 8, 4, 15, 20]",
                "[14, 6, 7, 20, 18]",
                "[11, 8, 7, 6, 20]",
                "[12, 2, 1, 7, 13]",
                "[12, 1, 17, 16, 13, 19]",
                "[17, 2, 3]",
                "[14, 9, 5, 2, 6, 15]",
                "[14, 1, 19]",
                "[13, 6, 1, 20]",
                "[11, 1, 7, 12, 15, 16]"};
        String[] inOrdersPostRemoves = {"[4, 8, 14, 15, 20]",
                "[6, 7, 14, 18, 20]",
                "[6, 7, 8, 11, 20]",
                "[1, 2, 7, 12, 13]",
                "[1, 12, 13, 16, 17, 19]",
                "[2, 3, 17]",
                "[2, 5, 6, 9, 14, 15]",
                "[1, 14, 19]",
                "[1, 6, 13, 20]",
                "[1, 7, 11, 12, 15, 16]"};
        String[] postOrdersRemoves = {"[4, 8, 20, 15, 14]",
                "[7, 6, 18, 20, 14]",
                "[6, 7, 8, 20, 11]",
                "[1, 7, 2, 13, 12]",
                "[1, 13, 16, 19, 17, 12]",
                "[3, 2, 17]",
                "[2, 6, 5, 9, 15, 14]",
                "[1, 19, 14]",
                "[1, 6, 20, 13]",
                "[7, 1, 16, 15, 12, 11]"};
        int[] sizeRemoves = {5, 5, 5, 5, 6, 3, 6, 3, 4, 6};
        int[] heightRemoves = {3, 3, 4, 3, 4, 3, 4, 2, 3, 4};
        int[] maxDepthRemoves = {2, 2, 3, 2, 3, 2, 3, 1, 2, 3};
        int[] minRemoves = {4, 6, 6, 1, 1, 2, 2, 1, 1, 1};
        int[] maxRemoves = {20, 20, 20, 13, 19, 17, 15, 19, 20, 16};
        int[][] nodeDepthRemoves = {{-1, -1, -1, 2, -1, -1, -1, 1, -1, -1, -1, -1, -1, 0, 1, -1, -1, -1, -1, 2},
                {-1, -1, -1, -1, -1, 1, 2, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 2, -1, 1},
                {-1, -1, -1, -1, -1, 3, 2, 1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, 1},
                {2, 1, -1, -1, -1, -1, 2, -1, -1, -1, -1, 0, 1, -1, -1, -1, -1, -1, -1, -1},
                {1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 3, -1, -1, 2, 1, -1, 2, -1},
                {-1, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1},
                {-1, 3, -1, -1, 2, 3, -1, -1, 1, -1, -1, -1, -1, 0, 1, -1, -1, -1, -1, -1},
                {1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, 1, -1},
                {2, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, 1},
                {1, -1, -1, -1, -1, -1, 2, -1, -1, -1, 0, 1, -1, -1, 2, 3, -1, -1, -1, -1}};
        boolean[][] containsRemoves = {{false, false, false, true, false, false, false, true, false, false, false, false, false, true, true, false, false, false, false, true},
                {false, false, false, false, false, true, true, false, false, false, false, false, false, true, false, false, false, true, false, true},
                {false, false, false, false, false, true, true, true, false, false, true, false, false, false, false, false, false, false, false, true},
                {true, true, false, false, false, false, true, false, false, false, false, true, true, false, false, false, false, false, false, false},
                {true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, true, true, false, true, false},
                {false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false},
                {false, true, false, false, true, true, false, false, true, false, false, false, false, true, true, false, false, false, false, false},
                {true, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, true, false},
                {true, false, false, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, true, false, false, false, true, true, false, false, true, true, false, false, false, false}};
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                buildTree(adds[x],check);

                String message = "Failed after adding these values "+ Arrays.toString(adds[x]);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,removes[x]));
                message+=" and then removing "+removes[x];

                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,sizeRemoves[x],((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,heightRemoves[x],((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,maxDepthRemoves[x],((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,minRemoves[x],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,maxRemoves[x],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,preOrdersPostRemoves[x], preOrder.invoke(check));
                Assert.assertEquals(message,inOrdersPostRemoves[x], inOrder.invoke(check));
                Assert.assertEquals(message,postOrdersRemoves[x], postOrder.invoke(check));
                for (int v = 1; v <= 20; v++) {
                    if (containsRemoves[x][v-1])
                        Assert.assertTrue(message, (Boolean) contains.invoke(check, v));
                    else
                        Assert.assertFalse(message,(Boolean) contains.invoke(check, v));
                }
                for (int v = 1; v <= 20; v++) {
                    Assert.assertEquals( message, nodeDepthRemoves[x][v-1],((Integer) nodeDepth.invoke(check, v)).intValue());
                }

                String[] postList = postOrdersRemoves[x].substring(1,postOrdersRemoves[x].length()-1).split(", ");
                String[] preList = preOrdersPostRemoves[x].substring(1,preOrdersPostRemoves[x].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeLeaves() throws Exception
    {
        checkImports();
        int[][] adds = {{5, 20, 17, 7, 8, 14, 14},
                {7, 6, 5, 10, 20, 1, 10},
                {12, 20, 18, 12, 9, 14, 12, 10, 15},
                {11, 18, 13, 3, 10, 9, 5},
                {18, 14, 18, 9, 1, 9, 20, 11},
                {2, 20, 5, 12, 16, 20},
                {20, 10, 11, 13, 9, 11, 8, 1},
                {9, 17, 5, 15, 18, 6, 18, 19, 20},
                {18, 12, 4, 13, 20, 20, 6, 1},
                {1, 17, 18, 19, 19, 15, 18, 13},
                {7, 7, 12, 3, 16, 4},
                {17, 13, 15, 16, 5, 14, 18},
                {20, 16, 20, 6, 10, 20},
                {4, 16, 8, 6, 1, 3, 17, 17, 18},
                {7, 10, 14, 9, 16, 9, 19, 20},
                {19, 19, 15, 10, 14, 17, 2, 17, 18},
                {3, 18, 19, 11, 3, 20, 4, 18},
                {16, 11, 9, 11, 10, 10, 12},
                {16, 2, 2, 7, 8, 12, 9, 15, 7},
                {10, 2, 2, 6, 16, 3}};
        int[] removes = {14, 20, 15, 13, 1, 16, 13, 6, 20, 19, 16, 16, 10, 18, 20, 14, 20, 10, 15, 16};
        String[] preOrdersPostRemoves = {"[5, 20, 17, 7, 8]",
                "[7, 6, 5, 1, 10]",
                "[12, 9, 10, 20, 18, 14]",
                "[11, 3, 10, 9, 5, 18]",
                "[18, 14, 9, 11, 20]",
                "[2, 20, 5, 12]",
                "[20, 10, 9, 8, 1, 11]",
                "[9, 5, 17, 15, 18, 19, 20]",
                "[18, 12, 4, 1, 6, 13]",
                "[1, 17, 15, 13, 18]",
                "[7, 3, 4, 12]",
                "[17, 13, 5, 15, 14, 18]",
                "[20, 16, 6]",
                "[4, 1, 3, 16, 8, 6, 17]",
                "[7, 10, 9, 14, 16, 19]",
                "[19, 15, 10, 2, 17, 18]",
                "[3, 18, 11, 4, 19]",
                "[16, 11, 9, 12]",
                "[16, 2, 7, 8, 12, 9]",
                "[10, 2, 6, 3]"};
        String[] inOrdersPostRemoves = {"[5, 7, 8, 17, 20]",
                "[1, 5, 6, 7, 10]",
                "[9, 10, 12, 14, 18, 20]",
                "[3, 5, 9, 10, 11, 18]",
                "[9, 11, 14, 18, 20]",
                "[2, 5, 12, 20]",
                "[1, 8, 9, 10, 11, 20]",
                "[5, 9, 15, 17, 18, 19, 20]",
                "[1, 4, 6, 12, 13, 18]",
                "[1, 13, 15, 17, 18]",
                "[3, 4, 7, 12]",
                "[5, 13, 14, 15, 17, 18]",
                "[6, 16, 20]",
                "[1, 3, 4, 6, 8, 16, 17]",
                "[7, 9, 10, 14, 16, 19]",
                "[2, 10, 15, 17, 18, 19]",
                "[3, 4, 11, 18, 19]",
                "[9, 11, 12, 16]",
                "[2, 7, 8, 9, 12, 16]",
                "[2, 3, 6, 10]"};
        String[] postOrdersRemoves = {"[8, 7, 17, 20, 5]",
                "[1, 5, 6, 10, 7]",
                "[10, 9, 14, 18, 20, 12]",
                "[5, 9, 10, 3, 18, 11]",
                "[11, 9, 14, 20, 18]",
                "[12, 5, 20, 2]",
                "[1, 8, 9, 11, 10, 20]",
                "[5, 15, 20, 19, 18, 17, 9]",
                "[1, 6, 4, 13, 12, 18]",
                "[13, 15, 18, 17, 1]",
                "[4, 3, 12, 7]",
                "[5, 14, 15, 13, 18, 17]",
                "[6, 16, 20]",
                "[3, 1, 6, 8, 17, 16, 4]",
                "[9, 19, 16, 14, 10, 7]",
                "[2, 10, 18, 17, 15, 19]",
                "[4, 11, 19, 18, 3]",
                "[9, 12, 11, 16]",
                "[9, 12, 8, 7, 2, 16]",
                "[3, 6, 2, 10]"};
        int[] sizeRemoves = {5, 5, 6, 6, 5, 4, 6, 7, 6, 5, 4, 6, 3, 7, 6, 6, 5, 4, 6, 4};
        int[] heightRemoves = {5, 4, 4, 5, 4, 4, 5, 5, 4, 4, 3, 4, 3, 4, 5, 4, 4, 3, 6, 4};
        int[] maxDepthRemoves = {4, 3, 3, 4, 3, 3, 4, 4, 3, 3, 2, 3, 2, 3, 4, 3, 3, 2, 5, 3};
        int[] minRemoves = {5, 1, 9, 3, 9, 2, 1, 5, 1, 1, 3, 5, 6, 1, 7, 2, 3, 9, 2, 2};
        int[] maxRemoves = {20, 10, 20, 18, 20, 20, 20, 20, 18, 18, 12, 18, 20, 17, 19, 19, 19, 16, 16, 10};
        int[][] nodeDepthRemoves = {{-1, -1, -1, -1, 0, -1, 3, 4, -1, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, 1},
                {3, -1, -1, -1, 2, 1, 0, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, 1, 2, -1, 0, -1, 3, -1, -1, -1, 2, -1, 1},
                {-1, -1, 1, -1, 4, -1, -1, -1, 3, 2, 0, -1, -1, -1, -1, -1, -1, 1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, 2, -1, 3, -1, -1, 1, -1, -1, -1, 0, -1, 1},
                {-1, 0, -1, -1, 2, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, -1, 1},
                {4, -1, -1, -1, -1, -1, -1, 3, 2, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, 0},
                {-1, -1, -1, -1, 1, -1, -1, -1, 0, -1, -1, -1, -1, -1, 2, -1, 1, 2, 3, 4},
                {3, -1, -1, 2, -1, 3, -1, -1, -1, -1, -1, 1, 2, -1, -1, -1, -1, 0, -1, -1},
                {0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, 2, -1, 1, 2, -1, -1},
                {-1, -1, 1, 2, -1, -1, 0, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, 1, 3, 2, -1, 0, 1, -1, -1},
                {-1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 0},
                {1, -1, 2, 0, -1, 3, -1, 2, -1, -1, -1, -1, -1, -1, -1, 1, 2, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, 0, -1, 2, 1, -1, -1, -1, 2, -1, 3, -1, -1, 4, -1},
                {-1, 3, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, 1, -1, 2, 3, 0, -1},
                {-1, -1, 0, 3, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, 1, 2, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, 2, -1, 1, 2, -1, -1, -1, 0, -1, -1, -1, -1},
                {-1, 1, -1, -1, -1, -1, 2, 3, 5, -1, -1, 4, -1, -1, -1, 0, -1, -1, -1, -1},
                {-1, 1, 3, -1, -1, 2, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
        boolean[][] containsRemoves = {{false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, true, false, false, true},
                {true, false, false, false, true, true, true, false, false, true, false, false, false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false, true, true, false, true, false, true, false, false, false, true, false, true},
                {false, false, true, false, true, false, false, false, true, true, true, false, false, false, false, false, false, true, false, false},
                {false, false, false, false, false, false, false, false, true, false, true, false, false, true, false, false, false, true, false, true},
                {false, true, false, false, true, false, false, false, false, false, false, true, false, false, false, false, false, false, false, true},
                {true, false, false, false, false, false, false, true, true, true, true, false, false, false, false, false, false, false, false, true},
                {false, false, false, false, true, false, false, false, true, false, false, false, false, false, true, false, true, true, true, true},
                {true, false, false, true, false, true, false, false, false, false, false, true, true, false, false, false, false, true, false, false},
                {true, false, false, false, false, false, false, false, false, false, false, false, true, false, true, false, true, true, false, false},
                {false, false, true, true, false, false, true, false, false, false, false, true, false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false, false, false, false, false, true, true, true, false, true, true, false, false},
                {false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, true, false, false, false, true},
                {true, false, true, true, false, true, false, true, false, false, false, false, false, false, false, true, true, false, false, false},
                {false, false, false, false, false, false, true, false, true, true, false, false, false, true, false, true, false, false, true, false},
                {false, true, false, false, false, false, false, false, false, true, false, false, false, false, true, false, true, true, true, false},
                {false, false, true, true, false, false, false, false, false, false, true, false, false, false, false, false, false, true, true, false},
                {false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, true, false, false, false, false},
                {false, true, false, false, false, false, true, true, true, false, false, true, false, false, false, true, false, false, false, false},
                {false, true, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false, false, false}};
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                buildTree(adds[x],check);

                String message = "Failed after adding these values "+ Arrays.toString(adds[x]);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,removes[x]));
                message+=" and then removing "+removes[x];

                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,sizeRemoves[x],((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,heightRemoves[x],((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,maxDepthRemoves[x],((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,minRemoves[x],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,maxRemoves[x],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,preOrdersPostRemoves[x], preOrder.invoke(check));
                Assert.assertEquals(message,inOrdersPostRemoves[x], inOrder.invoke(check));
                Assert.assertEquals(message,postOrdersRemoves[x], postOrder.invoke(check));
                for (int v = 1; v <= 20; v++) {
                    if (containsRemoves[x][v-1])
                        Assert.assertTrue(message, (Boolean) contains.invoke(check, v));
                    else
                        Assert.assertFalse(message,(Boolean) contains.invoke(check, v));
                }
                for (int v = 1; v <= 20; v++) {
                    Assert.assertEquals( message, nodeDepthRemoves[x][v-1],((Integer) nodeDepth.invoke(check, v)).intValue());
                }

                String[] postList = postOrdersRemoves[x].substring(1,postOrdersRemoves[x].length()-1).split(", ");
                String[] preList = preOrdersPostRemoves[x].substring(1,preOrdersPostRemoves[x].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeNodesOnlyLeftChildren() throws Exception
    {
        checkImports();
        int[][] adds = {{9, 5, 5, 7, 13, 19, 15, 15, 2, 9},
                {17, 19, 10, 11, 7, 11, 4, 13, 5, 17},
                {9, 6, 1, 10, 6, 3, 13, 2},
                {11, 20, 7, 3, 15, 18},
                {6, 20, 4, 16, 15, 18, 1, 9, 11},
                {16, 9, 4, 16, 9, 2, 13},
                {18, 10, 13, 1, 11, 13, 5},
                {17, 1, 20, 12, 20, 17, 5, 6, 12},
                {11, 9, 2, 4, 13, 2, 6, 10, 20, 19},
                {9, 4, 18, 13, 4, 9, 2, 13, 3},
                {5, 14, 8, 12, 1, 5, 12},
                {16, 12, 11, 18, 2, 17, 2, 8, 14, 9},
                {2, 17, 4, 14, 13, 19, 1, 20, 13},
                {1, 4, 17, 13, 10, 3, 9, 16, 7},
                {12, 9, 17, 18, 4, 17, 13, 4},
                {5, 9, 14, 15, 11, 4, 1, 10},
                {1, 15, 19, 11, 16, 9},
                {13, 5, 1, 6, 1, 3, 2, 18, 8},
                {5, 4, 13, 11, 10, 20, 6},
                {7, 8, 5, 8, 11, 6, 6, 10, 4, 1}};
        int[] removes = {19, 7, 6, 7, 20, 4, 13, 12, 20, 4, 14, 11, 14, 9, 9, 4, 11, 3, 11, 11};
        String[] preOrdersPostRemoves = {"[9, 5, 2, 7, 13, 15]",
                "[17, 10, 4, 5, 11, 13, 19]",
                "[9, 1, 3, 2, 10, 13]",
                "[11, 3, 20, 15, 18]",
                "[6, 4, 1, 16, 15, 9, 11, 18]",
                "[16, 9, 2, 13]",
                "[18, 10, 1, 5, 11]",
                "[17, 1, 5, 6, 20]",
                "[11, 9, 2, 4, 6, 10, 13, 19]",
                "[9, 2, 3, 18, 13]",
                "[5, 1, 8, 12]",
                "[16, 12, 2, 8, 9, 14, 18, 17]",
                "[2, 1, 17, 4, 13, 19, 20]",
                "[1, 4, 3, 17, 13, 10, 7, 16]",
                "[12, 4, 17, 13, 18]",
                "[5, 1, 9, 14, 11, 10, 15]",
                "[1, 15, 9, 19, 16]",
                "[13, 5, 1, 2, 6, 8, 18]",
                "[5, 4, 13, 10, 6, 20]",
                "[7, 5, 4, 1, 6, 8, 10]"};
        String[] inOrdersPostRemoves = {"[2, 5, 7, 9, 13, 15]",
                "[4, 5, 10, 11, 13, 17, 19]",
                "[1, 2, 3, 9, 10, 13]",
                "[3, 11, 15, 18, 20]",
                "[1, 4, 6, 9, 11, 15, 16, 18]",
                "[2, 9, 13, 16]",
                "[1, 5, 10, 11, 18]",
                "[1, 5, 6, 17, 20]",
                "[2, 4, 6, 9, 10, 11, 13, 19]",
                "[2, 3, 9, 13, 18]",
                "[1, 5, 8, 12]",
                "[2, 8, 9, 12, 14, 16, 17, 18]",
                "[1, 2, 4, 13, 17, 19, 20]",
                "[1, 3, 4, 7, 10, 13, 16, 17]",
                "[4, 12, 13, 17, 18]",
                "[1, 5, 9, 10, 11, 14, 15]",
                "[1, 9, 15, 16, 19]",
                "[1, 2, 5, 6, 8, 13, 18]",
                "[4, 5, 6, 10, 13, 20]",
                "[1, 4, 5, 6, 7, 8, 10]"};
        String[] postOrdersRemoves = {"[2, 7, 5, 15, 13, 9]",
                "[5, 4, 13, 11, 10, 19, 17]",
                "[2, 3, 1, 13, 10, 9]",
                "[3, 18, 15, 20, 11]",
                "[1, 4, 11, 9, 15, 18, 16, 6]",
                "[2, 13, 9, 16]",
                "[5, 1, 11, 10, 18]",
                "[6, 5, 1, 20, 17]",
                "[6, 4, 2, 10, 9, 19, 13, 11]",
                "[3, 2, 13, 18, 9]",
                "[1, 12, 8, 5]",
                "[9, 8, 2, 14, 12, 17, 18, 16]",
                "[1, 13, 4, 20, 19, 17, 2]",
                "[3, 7, 10, 16, 13, 17, 4, 1]",
                "[4, 13, 18, 17, 12]",
                "[1, 10, 11, 15, 14, 9, 5]",
                "[9, 16, 19, 15, 1]",
                "[2, 1, 8, 6, 5, 18, 13]",
                "[4, 6, 10, 20, 13, 5]",
                "[1, 4, 6, 5, 10, 8, 7]"};
        int[] sizeRemoves = {6, 7, 6, 5, 8, 4, 5, 5, 8, 5, 4, 8, 7, 8, 5, 7, 5, 7, 6, 7};
        int[] heightRemoves = {3, 4, 4, 4, 5, 3, 4, 4, 5, 3, 3, 5, 4, 6, 3, 5, 4, 4, 4, 4};
        int[] maxDepthRemoves = {2, 3, 3, 3, 4, 2, 3, 3, 4, 2, 2, 4, 3, 5, 2, 4, 3, 3, 3, 3};
        int[] minRemoves = {2, 4, 1, 3, 1, 2, 1, 1, 2, 2, 1, 2, 1, 1, 4, 1, 1, 1, 4, 1};
        int[] maxRemoves = {15, 19, 13, 20, 18, 16, 18, 20, 19, 18, 12, 18, 20, 17, 18, 15, 19, 18, 20, 10};
        int[][] nodeDepthRemoves = {{-1, 2, -1, -1, 1, -1, 2, -1, 0, -1, -1, -1, 1, -1, 2, -1, -1, -1, -1, -1},
                {-1, -1, -1, 2, 3, -1, -1, -1, -1, 1, 2, -1, 3, -1, -1, -1, 0, -1, 1, -1},
                {1, 3, 2, -1, -1, -1, -1, -1, 0, 1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 2, -1, -1, 3, -1, 1},
                {2, -1, -1, 1, -1, 0, -1, -1, 3, -1, 4, -1, -1, -1, 2, 1, -1, 2, -1, -1},
                {-1, 2, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, 2, -1, -1, 0, -1, -1, -1, -1},
                {2, -1, -1, -1, 3, -1, -1, -1, -1, 1, 2, -1, -1, -1, -1, -1, -1, 0, -1, -1},
                {1, -1, -1, -1, 2, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, 1},
                {-1, 2, -1, 3, -1, 4, -1, -1, 1, 2, 0, -1, 1, -1, -1, -1, -1, -1, 2, -1},
                {-1, 1, 2, -1, -1, -1, -1, -1, 0, -1, -1, -1, 2, -1, -1, -1, -1, 1, -1, -1},
                {1, -1, -1, -1, 0, -1, -1, 1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, 2, -1, -1, -1, -1, -1, 3, 4, -1, -1, 1, -1, 2, -1, 0, 2, 1, -1, -1},
                {1, 0, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, 1, -1, 2, 3},
                {0, -1, 2, 1, -1, -1, 5, -1, -1, 4, -1, -1, 3, -1, -1, 4, 2, -1, -1, -1},
                {-1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 0, 2, -1, -1, -1, 1, 2, -1, -1},
                {1, -1, -1, -1, 0, -1, -1, -1, 1, 4, 3, -1, -1, 2, 3, -1, -1, -1, -1, -1},
                {0, -1, -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, 1, 3, -1, -1, 2, -1},
                {2, 3, -1, -1, 1, 2, -1, 3, -1, -1, -1, -1, 0, -1, -1, -1, -1, 1, -1, -1},
                {-1, -1, -1, 1, 0, 3, -1, -1, -1, 2, -1, -1, 1, -1, -1, -1, -1, -1, -1, 2},
                {3, -1, -1, 2, 1, 2, 0, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
        boolean[][] containsRemoves = {{false, true, false, false, true, false, true, false, true, false, false, false, true, false, true, false, false, false, false, false},
                {false, false, false, true, true, false, false, false, false, true, true, false, true, false, false, false, true, false, true, false},
                {true, true, true, false, false, false, false, false, true, true, false, false, true, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false, false, false, true, false, false, false, true, false, false, true, false, true},
                {true, false, false, true, false, true, false, false, true, false, true, false, false, false, true, true, false, true, false, false},
                {false, true, false, false, false, false, false, false, true, false, false, false, true, false, false, true, false, false, false, false},
                {true, false, false, false, true, false, false, false, false, true, true, false, false, false, false, false, false, true, false, false},
                {true, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true, false, false, true},
                {false, true, false, true, false, true, false, false, true, true, true, false, true, false, false, false, false, false, true, false},
                {false, true, true, false, false, false, false, false, true, false, false, false, true, false, false, false, false, true, false, false},
                {true, false, false, false, true, false, false, true, false, false, false, true, false, false, false, false, false, false, false, false},
                {false, true, false, false, false, false, false, true, true, false, false, true, false, true, false, true, true, true, false, false},
                {true, true, false, true, false, false, false, false, false, false, false, false, true, false, false, false, true, false, true, true},
                {true, false, true, true, false, false, true, false, false, true, false, false, true, false, false, true, true, false, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, true, true, false, false, false, true, true, false, false},
                {true, false, false, false, true, false, false, false, true, true, true, false, false, true, true, false, false, false, false, false},
                {true, false, false, false, false, false, false, false, true, false, false, false, false, false, true, true, false, false, true, false},
                {true, true, false, false, true, true, false, true, false, false, false, false, true, false, false, false, false, true, false, false},
                {false, false, false, true, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, true},
                {true, false, false, true, true, true, true, true, false, true, false, false, false, false, false, false, false, false, false, false}};
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                buildTree(adds[x],check);

                String message = "Failed after adding these values "+ Arrays.toString(adds[x]);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,removes[x]));
                message+=" and then removing "+removes[x];

                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,sizeRemoves[x],((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,heightRemoves[x],((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,maxDepthRemoves[x],((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,minRemoves[x],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,maxRemoves[x],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,preOrdersPostRemoves[x], preOrder.invoke(check));
                Assert.assertEquals(message,inOrdersPostRemoves[x], inOrder.invoke(check));
                Assert.assertEquals(message,postOrdersRemoves[x], postOrder.invoke(check));
                for (int v = 1; v <= 20; v++) {
                    if (containsRemoves[x][v-1])
                        Assert.assertTrue(message, (Boolean) contains.invoke(check, v));
                    else
                        Assert.assertFalse(message,(Boolean) contains.invoke(check, v));
                }
                for (int v = 1; v <= 20; v++) {
                    Assert.assertEquals( message, nodeDepthRemoves[x][v-1],((Integer) nodeDepth.invoke(check, v)).intValue());
                }

                String[] postList = postOrdersRemoves[x].substring(1,postOrdersRemoves[x].length()-1).split(", ");
                String[] preList = preOrdersPostRemoves[x].substring(1,preOrdersPostRemoves[x].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeNodesOnlyRightChildren() throws Exception
    {
        checkImports();
        int[][] adds = {{17, 7, 12, 5, 3, 8, 5},
                {4, 5, 12, 7, 17, 14, 11, 16, 9},
                {6, 18, 13, 19, 10, 6, 17, 2, 15},
                {4, 6, 7, 13, 9, 4, 4, 7},
                {1, 9, 1, 9, 18, 8, 15, 8},
                {9, 17, 14, 12, 15, 11, 3, 5, 3},
                {11, 7, 4, 11, 12, 12},
                {17, 11, 2, 11, 6, 19, 10, 3},
                {9, 2, 18, 12, 1, 16},
                {9, 19, 9, 8, 7, 10},
                {12, 18, 15, 5, 18, 10, 4, 18, 20, 9},
                {8, 15, 4, 3, 1, 10},
                {4, 14, 19, 3, 16, 9, 16, 5},
                {4, 20, 2, 10, 1, 3, 20, 15, 10, 20},
                {7, 16, 2, 10, 16, 12},
                {3, 17, 17, 4, 2, 6, 14},
                {17, 17, 17, 7, 6, 1, 15, 13, 19, 20},
                {15, 15, 7, 12, 9, 10, 15},
                {12, 5, 16, 18, 4, 15},
                {18, 1, 20, 10, 18, 12, 5, 2}};
        int[] removes = {12, 11, 17, 13, 18, 12, 7, 11, 2, 8, 10, 3, 19, 20, 16, 17, 15, 12, 5, 5};
        String[] preOrdersPostRemoves = {"[17, 7, 5, 3, 8]",
                "[4, 5, 12, 7, 9, 17, 14, 16]",
                "[6, 2, 18, 13, 10, 15, 19]",
                "[4, 6, 7, 9]",
                "[1, 9, 8, 15]",
                "[9, 3, 5, 17, 14, 11, 15]",
                "[11, 4, 12]",
                "[17, 2, 6, 3, 10, 19]",
                "[9, 1, 18, 12, 16]",
                "[9, 7, 19, 10]",
                "[12, 5, 4, 9, 18, 15, 20]",
                "[8, 4, 1, 15, 10]",
                "[4, 3, 14, 9, 5, 16]",
                "[4, 2, 1, 3, 10, 15]",
                "[7, 2, 10, 12]",
                "[3, 2, 4, 6, 14]",
                "[17, 7, 6, 1, 13, 19, 20]",
                "[15, 7, 9, 10]",
                "[12, 4, 16, 15, 18]",
                "[18, 1, 10, 2, 12, 20]"};
        String[] inOrdersPostRemoves = {"[3, 5, 7, 8, 17]",
                "[4, 5, 7, 9, 12, 14, 16, 17]",
                "[2, 6, 10, 13, 15, 18, 19]",
                "[4, 6, 7, 9]",
                "[1, 8, 9, 15]",
                "[3, 5, 9, 11, 14, 15, 17]",
                "[4, 11, 12]",
                "[2, 3, 6, 10, 17, 19]",
                "[1, 9, 12, 16, 18]",
                "[7, 9, 10, 19]",
                "[4, 5, 9, 12, 15, 18, 20]",
                "[1, 4, 8, 10, 15]",
                "[3, 4, 5, 9, 14, 16]",
                "[1, 2, 3, 4, 10, 15]",
                "[2, 7, 10, 12]",
                "[2, 3, 4, 6, 14]",
                "[1, 6, 7, 13, 17, 19, 20]",
                "[7, 9, 10, 15]",
                "[4, 12, 15, 16, 18]",
                "[1, 2, 10, 12, 18, 20]"};
        String[] postOrdersRemoves = {"[3, 5, 8, 7, 17]",
                "[9, 7, 16, 14, 17, 12, 5, 4]",
                "[2, 10, 15, 13, 19, 18, 6]",
                "[9, 7, 6, 4]",
                "[8, 15, 9, 1]",
                "[5, 3, 11, 15, 14, 17, 9]",
                "[4, 12, 11]",
                "[3, 10, 6, 2, 19, 17]",
                "[1, 16, 12, 18, 9]",
                "[7, 10, 19, 9]",
                "[4, 9, 5, 15, 20, 18, 12]",
                "[1, 4, 10, 15, 8]",
                "[3, 5, 9, 16, 14, 4]",
                "[1, 3, 2, 15, 10, 4]",
                "[2, 12, 10, 7]",
                "[2, 14, 6, 4, 3]",
                "[1, 6, 13, 7, 20, 19, 17]",
                "[10, 9, 7, 15]",
                "[4, 15, 18, 16, 12]",
                "[2, 12, 10, 1, 20, 18]"};
        int[] sizeRemoves = {5, 8, 7, 4, 4, 7, 3, 6, 5, 4, 7, 5, 6, 6, 4, 5, 7, 4, 5, 6};
        int[] heightRemoves = {4, 6, 4, 4, 3, 4, 2, 4, 4, 3, 3, 3, 4, 3, 3, 4, 4, 4, 3, 4};
        int[] maxDepthRemoves = {3, 5, 3, 3, 2, 3, 1, 3, 3, 2, 2, 2, 3, 2, 2, 3, 3, 3, 2, 3};
        int[] minRemoves = {3, 4, 2, 4, 1, 3, 4, 2, 1, 7, 4, 1, 3, 1, 2, 2, 1, 7, 4, 1};
        int[] maxRemoves = {17, 17, 19, 9, 15, 17, 12, 19, 18, 19, 20, 15, 16, 15, 12, 14, 20, 15, 18, 20};
        int[][] nodeDepthRemoves = {{-1, -1, 3, -1, 2, -1, 1, 2, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1},
                {-1, -1, -1, 0, 1, -1, 3, -1, 4, -1, -1, 2, -1, 4, -1, 5, 3, -1, -1, -1},
                {-1, 1, -1, -1, -1, 0, -1, -1, -1, 3, -1, -1, 2, -1, 3, -1, -1, 1, 2, -1},
                {-1, -1, -1, 0, -1, 1, 2, -1, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {0, -1, -1, -1, -1, -1, -1, 2, 1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1},
                {-1, -1, 1, -1, 2, -1, -1, -1, 0, -1, 3, -1, -1, 2, 3, -1, 1, -1, -1, -1},
                {-1, -1, -1, 1, -1, -1, -1, -1, -1, -1, 0, 1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, 1, 3, -1, -1, 2, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1, 0, -1, 1, -1},
                {1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, 2, -1, -1, -1, 3, -1, 1, -1, -1},
                {-1, -1, -1, -1, -1, -1, 1, -1, 0, 2, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1},
                {-1, -1, -1, 2, 1, -1, -1, -1, 2, -1, -1, 0, -1, -1, 2, -1, -1, 1, -1, 2},
                {2, -1, -1, 1, -1, -1, -1, 0, -1, 2, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1},
                {-1, -1, 1, 0, 3, -1, -1, -1, 2, -1, -1, -1, -1, 1, -1, 2, -1, -1, -1, -1},
                {2, 1, 2, 0, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1},
                {-1, 1, -1, -1, -1, -1, 0, -1, -1, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, 1, 0, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1},
                {3, -1, -1, -1, -1, 2, 1, -1, -1, -1, -1, -1, 2, -1, -1, -1, 0, -1, 1, 2},
                {-1, -1, -1, -1, -1, -1, 1, -1, 2, 3, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1},
                {-1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, 2, 1, -1, 2, -1, -1},
                {1, 3, -1, -1, -1, -1, -1, -1, -1, 2, -1, 3, -1, -1, -1, -1, -1, 0, -1, 1}};
        boolean[][] containsRemoves = {{false, false, true, false, true, false, true, true, false, false, false, false, false, false, false, false, true, false, false, false},
                {false, false, false, true, true, false, true, false, true, false, false, true, false, true, false, true, true, false, false, false},
                {false, true, false, false, false, true, false, false, false, true, false, false, true, false, true, false, false, true, true, false},
                {false, false, false, true, false, true, true, false, true, false, false, false, false, false, false, false, false, false, false, false},
                {true, false, false, false, false, false, false, true, true, false, false, false, false, false, true, false, false, false, false, false},
                {false, false, true, false, true, false, false, false, true, false, true, false, false, true, true, false, true, false, false, false},
                {false, false, false, true, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false},
                {false, true, true, false, false, true, false, false, false, true, false, false, false, false, false, false, true, false, true, false},
                {true, false, false, false, false, false, false, false, true, false, false, true, false, false, false, true, false, true, false, false},
                {false, false, false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, true, false},
                {false, false, false, true, true, false, false, false, true, false, false, true, false, false, true, false, false, true, false, true},
                {true, false, false, true, false, false, false, true, false, true, false, false, false, false, true, false, false, false, false, false},
                {false, false, true, true, true, false, false, false, true, false, false, false, false, true, false, true, false, false, false, false},
                {true, true, true, true, false, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false},
                {false, true, false, false, false, false, true, false, false, true, false, true, false, false, false, false, false, false, false, false},
                {false, true, true, true, false, true, false, false, false, false, false, false, false, true, false, false, false, false, false, false},
                {true, false, false, false, false, true, true, false, false, false, false, false, true, false, false, false, true, false, true, true},
                {false, false, false, false, false, false, true, false, true, true, false, false, false, false, true, false, false, false, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, true, false, false, true, true, false, true, false, false},
                {true, true, false, false, false, false, false, false, false, true, false, true, false, false, false, false, false, true, false, true}};

        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                buildTree(adds[x],check);

                String message = "Failed after adding these values "+ Arrays.toString(adds[x]);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,removes[x]));
                message+=" and then removing "+removes[x];

                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,sizeRemoves[x],((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,heightRemoves[x],((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,maxDepthRemoves[x],((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,minRemoves[x],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,maxRemoves[x],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,preOrdersPostRemoves[x], preOrder.invoke(check));
                Assert.assertEquals(message,inOrdersPostRemoves[x], inOrder.invoke(check));
                Assert.assertEquals(message,postOrdersRemoves[x], postOrder.invoke(check));
                for (int v = 1; v <= 20; v++) {
                    if (containsRemoves[x][v-1])
                        Assert.assertTrue(message, (Boolean) contains.invoke(check, v));
                    else
                        Assert.assertFalse(message,(Boolean) contains.invoke(check, v));
                }
                for (int v = 1; v <= 20; v++) {
                    Assert.assertEquals( message, nodeDepthRemoves[x][v-1],((Integer) nodeDepth.invoke(check, v)).intValue());
                }

                String[] postList = postOrdersRemoves[x].substring(1,postOrdersRemoves[x].length()-1).split(", ");
                String[] preList = preOrdersPostRemoves[x].substring(1,preOrdersPostRemoves[x].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void removeNodesTwoChildren() throws Exception
    {
        checkImports();
        int[][] adds = {{10, 5, 4, 18, 3, 12, 1, 4, 4, 14},
                {10, 17, 11, 17, 15, 18, 7, 2},
                {10, 11, 16, 8, 11, 4},
                {10, 5, 18, 1, 5, 15, 10, 11},
                {10, 20, 4, 7, 12, 3, 4, 1, 9},
                {10, 5, 8, 5, 15, 4, 11, 16},
                {10, 19, 18, 19, 20, 14, 4},
                {10, 7, 1, 18, 13, 2, 4, 13, 17, 3},
                {10, 1, 4, 9, 14, 10, 1},
                {10, 15, 17, 3, 5, 15}};
        int[] removes = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String[] preOrdersPostRemoves = {"[12, 5, 4, 3, 1, 18, 14]",
                "[11, 7, 2, 17, 15, 18]",
                "[11, 8, 4, 16]",
                "[11, 5, 1, 18, 15]",
                "[12, 4, 3, 1, 7, 9, 20]",
                "[11, 5, 4, 8, 15, 16]",
                "[14, 4, 19, 18, 20]",
                "[13, 7, 1, 2, 4, 3, 18, 17]",
                "[14, 1, 4, 9]",
                "[15, 3, 5, 17]"};
        String[] inOrdersPostRemoves = {"[1, 3, 4, 5, 12, 14, 18]",
                "[2, 7, 11, 15, 17, 18]",
                "[4, 8, 11, 16]",
                "[1, 5, 11, 15, 18]",
                "[1, 3, 4, 7, 9, 12, 20]",
                "[4, 5, 8, 11, 15, 16]",
                "[4, 14, 18, 19, 20]",
                "[1, 2, 3, 4, 7, 13, 17, 18]",
                "[1, 4, 9, 14]",
                "[3, 5, 15, 17]"};
        String[] postOrdersRemoves = {"[1, 3, 4, 5, 14, 18, 12]",
                "[2, 7, 15, 18, 17, 11]",
                "[4, 8, 16, 11]",
                "[1, 5, 15, 18, 11]",
                "[1, 3, 9, 7, 4, 20, 12]",
                "[4, 8, 5, 16, 15, 11]",
                "[4, 18, 20, 19, 14]",
                "[3, 4, 2, 1, 7, 17, 18, 13]",
                "[9, 4, 1, 14]",
                "[5, 3, 17, 15]"};
        int[] sizeRemoves = {7, 6, 4, 5, 7, 6, 5, 8, 4, 4};
        int[] heightRemoves = {5, 3, 3, 3, 4, 3, 3, 6, 4, 3};
        int[] maxDepthRemoves = {4, 2, 2, 2, 3, 2, 2, 5, 3, 2};
        int[] minRemoves = {1, 2, 4, 1, 1, 4, 4, 1, 1, 3};
        int[] maxRemoves = {18, 18, 16, 18, 20, 16, 20, 18, 14, 17};
        int[][] nodeDepthRemoves = {{4, -1, 3, 2, 1, -1, -1, -1, -1, -1, -1, 0, -1, 2, -1, -1, -1, 1, -1, -1},
                {-1, 2, -1, -1, -1, -1, 1, -1, -1, -1, 0, -1, -1, -1, 2, -1, 1, 2, -1, -1},
                {-1, -1, -1, 2, -1, -1, -1, 1, -1, -1, 0, -1, -1, -1, -1, 1, -1, -1, -1, -1},
                {2, -1, -1, -1, 1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 2, -1, -1, 1, -1, -1},
                {3, -1, 2, 1, -1, -1, 2, -1, 3, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, 1},
                {-1, -1, -1, 2, 1, -1, -1, 2, -1, -1, 0, -1, -1, -1, 1, 2, -1, -1, -1, -1},
                {-1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 2, 1, 2},
                {2, 3, 5, 4, -1, -1, 1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 2, 1, -1, -1},
                {1, -1, -1, 2, -1, -1, -1, -1, 3, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1},
                {-1, -1, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, 1, -1, -1, -1}};
        boolean[][] containsRemoves = {{true, false, true, true, true, false, false, false, false, false, false, true, false, true, false, false, false, true, false, false},
                {false, true, false, false, false, false, true, false, false, false, true, false, false, false, true, false, true, true, false, false},
                {false, false, false, true, false, false, false, true, false, false, true, false, false, false, false, true, false, false, false, false},
                {true, false, false, false, true, false, false, false, false, false, true, false, false, false, true, false, false, true, false, false},
                {true, false, true, true, false, false, true, false, true, false, false, true, false, false, false, false, false, false, false, true},
                {false, false, false, true, true, false, false, true, false, false, true, false, false, false, true, true, false, false, false, false},
                {false, false, false, true, false, false, false, false, false, false, false, false, false, true, false, false, false, true, true, true},
                {true, true, true, true, false, false, true, false, false, false, false, false, true, false, false, false, true, true, false, false},
                {true, false, false, true, false, false, false, false, true, false, false, false, false, true, false, false, false, false, false, false},
                {false, false, true, false, true, false, false, false, false, false, false, false, false, false, true, false, true, false, false, false}};
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method remove = bst.getMethod("remove",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method minValue = bst.getMethod("minValue");
            Method maxValue = bst.getMethod("maxValue");
            Method maxDepth = bst.getMethod("maxDepth");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method contains = bst.getMethod("contains",Comparable.class);
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            for(int x=0; x<10; x++)
            {
                Object check =  bst.getConstructor().newInstance();

                buildTree(adds[x],check);

                String message = "Failed after adding these values "+ Arrays.toString(adds[x]);
                Assert.assertTrue(message,(Boolean)remove.invoke(check,removes[x]));
                message+=" and then removing "+removes[x];

                Assert.assertFalse(message,(Boolean)isEmpty.invoke(check));
                Assert.assertEquals(message,sizeRemoves[x],((Integer)size.invoke(check)).intValue());
                Assert.assertEquals(message,heightRemoves[x],((Integer)height.invoke(check)).intValue());
                Assert.assertEquals(message,maxDepthRemoves[x],((Integer)maxDepth.invoke(check)).intValue());
                Assert.assertEquals(message,minRemoves[x],((Integer)minValue.invoke(check)).intValue());
                Assert.assertEquals(message,maxRemoves[x],((Integer)maxValue.invoke(check)).intValue());
                Assert.assertEquals(message,preOrdersPostRemoves[x], preOrder.invoke(check));
                Assert.assertEquals(message,inOrdersPostRemoves[x], inOrder.invoke(check));
                Assert.assertEquals(message,postOrdersRemoves[x], postOrder.invoke(check));
                for (int v = 1; v <= 20; v++) {
                    if (containsRemoves[x][v-1])
                        Assert.assertTrue(message, (Boolean) contains.invoke(check, v));
                    else
                        Assert.assertFalse(message,(Boolean) contains.invoke(check, v));
                }
                for (int v = 1; v <= 20; v++) {
                    Assert.assertEquals( message, nodeDepthRemoves[x][v-1],((Integer) nodeDepth.invoke(check, v)).intValue());
                }

                String[] postList = postOrdersRemoves[x].substring(1,postOrdersRemoves[x].length()-1).split(", ");
                String[] preList = preOrdersPostRemoves[x].substring(1,preOrdersPostRemoves[x].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }



    public void linkageCheck(String[] preList, String[] postList , Object o) throws Exception
    {

        try {
            Class<?> bstn = Class.forName(generateClassName("DS5_BinarySearchTree_Node"));
            Method getData = bstn.getMethod("getData");
            Method getLeft = bstn.getMethod("getLeft");
            Method getRight = bstn.getMethod("getRight");

            if(o == null)
                return;

            int value =((Integer)getData.invoke(o)).intValue();
            int preLocation = -1;
            int postLocation = -1;
            for(int x=0; x<preList.length; x++)
            {
                if(preList[x].equals(getData.invoke(o).toString()))
                    preLocation=x;
                if(postList[x].equals(getData.invoke(o).toString()))
                    postLocation=x;
            }
            //System.out.println("pre list "+Arrays.toString(preList) +" looking for "+getData.invoke(o) + " found at "+preLocation);
            Integer left =null;
            if(preLocation < preList.length-1 && Integer.parseInt(preList[preLocation+1])<value)
            {
                left = Integer.parseInt(preList[preLocation+1]);
                Assert.assertNotNull(getLeft.invoke(o));
                Assert.assertEquals(left.intValue(),((Integer) getData.invoke(getLeft.invoke(o))).intValue());
                linkageCheck(preList, postList, getLeft.invoke(o));
            }
            else
            {
                Assert.assertNull(getLeft.invoke(o));
            }

            Integer right =null;
            if(postLocation >0 && Integer.parseInt(postList[postLocation-1])>value)
            {
                right = Integer.parseInt(postList[postLocation-1]);
                Assert.assertNotNull(getRight.invoke(o));
                Assert.assertEquals(right.intValue(),((Integer) getData.invoke(getRight.invoke(o))).intValue());
                linkageCheck(preList, postList, getRight.invoke(o));
            }
            else
            {
                Assert.assertNull(getRight.invoke(o));
            }

        }
        catch(Exception e)
        {
            throw e;
        }
    }

    //remove everything
    @Test(timeout = 250)
    public void addAllRemoveAll() throws Exception
    {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method height = bst.getMethod("height");
            Method insert = bst.getMethod("insert", Comparable.class);
            Method remove = bst.getMethod("remove", Comparable.class);
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");

            for(int x=0; x<10; x++) {



                Object check = bst.getConstructor().newInstance();

                int numberOfItems = (int) (Math.random() * 5) + 6;
                ArrayList<Integer> numbers = new ArrayList<>();

                for(int a=0; a<numberOfItems;)
                {
                    int num=(int)(Math.random()*20+1);
                    if(!numbers.contains(num))
                    {
                        numbers.add(num);
                        a++;
                    }
                }

                for(Integer num:numbers)
                    Assert.assertTrue((Boolean)insert.invoke(check,num));

                for(Integer num:numbers)
                    Assert.assertTrue((Boolean)remove.invoke(check,num));

                for(Integer num:numbers)
                    Assert.assertFalse((Boolean)remove.invoke(check,num));

                Assert.assertNull(getRoot.invoke(check));
                Assert.assertEquals(0, ((Integer) size.invoke(check)).intValue());
                Assert.assertEquals(0, ((Integer) height.invoke(check)).intValue());
                Assert.assertTrue((Boolean) isEmpty.invoke(check));
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
    //random removals
}
