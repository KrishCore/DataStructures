
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DS5_BinarySearchTree_After_Adds_Tests
{
    int[][] adds = {{7, 10, 16, 8, 15, 4, 10, 9},
            {16, 1, 18, 14, 9, 18, 3, 1},
            {6, 3, 20, 10, 5, 13, 3, 3, 1},
            {13, 10, 9, 14, 3, 16},
            {14, 9, 14, 1, 5, 6, 3, 11, 15},
            {18, 8, 13, 8, 17, 12, 15, 4},
            {14, 9, 16, 2, 16, 1, 6, 14, 12},
            {14, 10, 15, 6, 5, 1, 20, 7, 9, 3},
            {12, 6, 10, 1, 6, 12},
            {13, 6, 12, 8, 8, 1, 16}};
    boolean[][] addResults = {{true, true, true, true, true, true, false, true},
            {true, true, true, true, true, false, true, false},
            {true, true, true, true, true, true, false, false, true},
            {true, true, true, true, true, true},
            {true, true, false, true, true, true, true, true, true},
            {true, true, true, false, true, true, true, true},
            {true, true, true, true, false, true, true, false, true},
            {true, true, true, true, true, true, true, true, true, true},
            {true, true, true, true, false, false},
            {true, true, true, true, false, true, true}};

    String[] preOrdersPostAdds = {"[7, 4, 10, 8, 9, 16, 15]",
            "[16, 1, 14, 9, 3, 18]",
            "[6, 3, 1, 5, 20, 10, 13]",
            "[13, 10, 9, 3, 14, 16]",
            "[14, 9, 1, 5, 3, 6, 11, 15]",
            "[18, 8, 4, 13, 12, 17, 15]",
            "[14, 9, 2, 1, 6, 12, 16]",
            "[14, 10, 6, 5, 1, 3, 7, 9, 15, 20]",
            "[12, 6, 1, 10]",
            "[13, 6, 1, 12, 8, 16]"};
    String[] inOrdersPostAdds = {"[4, 7, 8, 9, 10, 15, 16]",
            "[1, 3, 9, 14, 16, 18]",
            "[1, 3, 5, 6, 10, 13, 20]",
            "[3, 9, 10, 13, 14, 16]",
            "[1, 3, 5, 6, 9, 11, 14, 15]",
            "[4, 8, 12, 13, 15, 17, 18]",
            "[1, 2, 6, 9, 12, 14, 16]",
            "[1, 3, 5, 6, 7, 9, 10, 14, 15, 20]",
            "[1, 6, 10, 12]",
            "[1, 6, 8, 12, 13, 16]"};
    String[] postOrdersAdds = {"[4, 9, 8, 15, 16, 10, 7]",
            "[3, 9, 14, 1, 18, 16]",
            "[1, 5, 3, 13, 10, 20, 6]",
            "[3, 9, 10, 16, 14, 13]",
            "[3, 6, 5, 1, 11, 9, 15, 14]",
            "[4, 12, 15, 17, 13, 8, 18]",
            "[1, 6, 2, 12, 9, 16, 14]",
            "[3, 1, 5, 9, 7, 6, 10, 20, 15, 14]",
            "[1, 10, 6, 12]",
            "[1, 8, 12, 6, 16, 13]"};

    int[] sizeAdds = {7, 6, 7, 6, 8, 7, 7, 10, 4, 6};
    int[] heightAdds = {4, 5, 4, 4, 5, 5, 4, 6, 3, 4};
    int[] maxDepthAdds = {3, 4, 3, 3, 4, 4, 3, 5, 2, 3};
    int[] minAdds = {4, 1, 1, 3, 1, 4, 1, 1, 1, 1};
    int[] maxAdds = {16, 18, 20, 16, 15, 18, 16, 20, 12, 16};
    int[][] nodeDepthAdds = {{-1, -1, -1, 1, -1, -1, 0, 2, 3, 1, -1, -1, -1, -1, 3, 2, -1, -1, -1, -1},
            {1, -1, 4, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, 2, -1, 0, -1, 1, -1, -1},
            {2, -1, 1, -1, 2, 0, -1, -1, -1, 2, -1, -1, 3, -1, -1, -1, -1, -1, -1, 1},
            {-1, -1, 3, -1, -1, -1, -1, -1, 2, 1, -1, -1, 0, 1, -1, 2, -1, -1, -1, -1},
            {2, -1, 4, -1, 3, 4, -1, -1, 1, -1, 2, -1, -1, 0, 1, -1, -1, -1, -1, -1},
            {-1, -1, -1, 2, -1, -1, -1, 1, -1, -1, -1, 3, 2, -1, 4, -1, 3, 0, -1, -1},
            {3, 2, -1, -1, -1, 3, -1, -1, 1, -1, -1, 2, -1, 0, -1, 1, -1, -1, -1, -1},
            {4, -1, 5, -1, 3, 2, 3, -1, 4, 1, -1, -1, -1, 0, 1, -1, -1, -1, -1, 2},
            {2, -1, -1, -1, -1, 1, -1, -1, -1, 2, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1},
            {2, -1, -1, -1, -1, 1, -1, 3, -1, -1, -1, 2, 0, -1, -1, 1, -1, -1, -1, -1}};
    boolean[][] containsAdds = {{false, false, false, true, false, false, true, true, true, true, false, false, false, false, true, true, false, false, false, false},
            {true, false, true, false, false, false, false, false, true, false, false, false, false, true, false, true, false, true, false, false},
            {true, false, true, false, true, true, false, false, false, true, false, false, true, false, false, false, false, false, false, true},
            {false, false, true, false, false, false, false, false, true, true, false, false, true, true, false, true, false, false, false, false},
            {true, false, true, false, true, true, false, false, true, false, true, false, false, true, true, false, false, false, false, false},
            {false, false, false, true, false, false, false, true, false, false, false, true, true, false, true, false, true, true, false, false},
            {true, true, false, false, false, true, false, false, true, false, false, true, false, true, false, true, false, false, false, false},
            {true, false, true, false, true, true, true, false, true, true, false, false, false, true, true, false, false, false, false, true},
            {true, false, false, false, false, true, false, false, false, true, false, true, false, false, false, false, false, false, false, false},
            {true, false, false, false, false, true, false, true, false, false, false, true, true, false, false, true, false, false, false, false}};

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

    public void buildTree(Object o, int treeNumber) throws Exception
    {
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method insert = bst.getMethod("insert",Comparable.class);


                for(int p=0; p< adds[treeNumber].length; p++)
                    if(addResults[treeNumber][p])
                        Assert.assertTrue("Failed insert("+adds[treeNumber][p]+") check (expected true), while added these values: "+ Arrays.toString(adds[treeNumber]),(Boolean)insert.invoke(o,adds[treeNumber][p]));
                    else
                        Assert.assertFalse("Failed insert("+adds[treeNumber][p]+") check (expected false), while added these values: "+ Arrays.toString(adds[treeNumber]),(Boolean)insert.invoke(o,adds[treeNumber][p]));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test1() throws Exception {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method maxDepth = bst.getMethod("maxDepth");
            Method height = bst.getMethod("height");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");

            Object check =  bst.getConstructor().newInstance();

            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals(0,((Integer)height.invoke(check)).intValue());
            Assert.assertEquals(-1,((Integer)maxDepth.invoke(check)).intValue());
            Assert.assertEquals("[]",preOrder.invoke(check));
            Assert.assertEquals("[]",inOrder.invoke(check));
            Assert.assertEquals("[]",postOrder.invoke(check));

            Assert.assertNull(getRoot.invoke(check));

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method maxDepth = bst.getMethod("maxDepth");
            Method height = bst.getMethod("height");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");

            Object check =  bst.getConstructor(Comparable.class).newInstance("apple");

            Assert.assertFalse((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(1,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals(1,((Integer)height.invoke(check)).intValue());
            Assert.assertEquals(0,((Integer)maxDepth.invoke(check)).intValue());
            Assert.assertEquals("[apple]",preOrder.invoke(check));
            Assert.assertEquals("[apple]",inOrder.invoke(check));
            Assert.assertEquals("[apple]",postOrder.invoke(check));

            Assert.assertNotNull(getRoot.invoke(check));

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method isEmpty = bst.getMethod("isEmpty");
            Method maxDepth = bst.getMethod("maxDepth");
            Method height = bst.getMethod("height");
            Method preOrder = bst.getMethod("preOrder");
            Method inOrder = bst.getMethod("inOrder");
            Method postOrder = bst.getMethod("postOrder");
            Method getRoot = bst.getMethod("getRoot");
            Method size = bst.getMethod("size");
            Method clear = bst.getMethod("clear");

            Object check =  bst.getConstructor(Comparable.class).newInstance("apple");

            clear.invoke(check);
            Assert.assertTrue((Boolean)isEmpty.invoke(check));
            Assert.assertEquals(0,((Integer)size.invoke(check)).intValue());
            Assert.assertEquals(0,((Integer)height.invoke(check)).intValue());
            Assert.assertEquals(-1,((Integer)maxDepth.invoke(check)).intValue());
            Assert.assertEquals("[]",preOrder.invoke(check));
            Assert.assertEquals("[]",inOrder.invoke(check));
            Assert.assertEquals("[]",postOrder.invoke(check));

            Assert.assertNull(getRoot.invoke(check));


        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    // contains
    @Test(timeout = 250)
    public void test2() throws Exception {
        checkImports();
        try
        {
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method contains = bst.getMethod("contains",Comparable.class);
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                for (int v = 1; v <= 20; v++) {
                    if (containsAdds[t][v-1])
                        Assert.assertTrue("Failed contains("+v+") check after adding these values: " + Arrays.toString(adds[t]), (Boolean) contains.invoke(check, v));
                    else
                        Assert.assertFalse("Failed contains("+v+") check after adding these values: " + Arrays.toString(adds[t]), (Boolean) contains.invoke(check, v));
                }
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method size = bst.getMethod("size");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed size() after adding these values: " + Arrays.toString(adds[t]), sizeAdds[t] ,((Integer) size.invoke(check)).intValue());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method minValue = bst.getMethod("minValue");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed minValue() after adding these values: " + Arrays.toString(adds[t]), minAdds[t] ,((Integer) minValue.invoke(check)).intValue());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method maxValue = bst.getMethod("maxValue");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed maxValue() after adding these values: " + Arrays.toString(adds[t]), maxAdds[t] ,((Integer) maxValue.invoke(check)).intValue());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method maxDepth = bst.getMethod("maxDepth");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed maxDepth() after adding these values: " + Arrays.toString(adds[t]), maxDepthAdds[t] ,((Integer) maxDepth.invoke(check)).intValue());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method height = bst.getMethod("height");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed height() after adding these values: " + Arrays.toString(adds[t]), heightAdds[t] ,((Integer) height.invoke(check)).intValue());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method preOrder = bst.getMethod("preOrder");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed preOrder() after adding these values: " + Arrays.toString(adds[t]), preOrdersPostAdds[t] ,preOrder.invoke(check).toString());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method inOrder = bst.getMethod("inOrder");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed inOrder() after adding these values: " + Arrays.toString(adds[t]), inOrdersPostAdds[t] ,inOrder.invoke(check).toString());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method postOrder = bst.getMethod("postOrder");
            Method getRoot = bst.getMethod("getRoot");

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                Assert.assertEquals("Failed postOrder() after adding these values: " + Arrays.toString(adds[t]), postOrdersAdds[t] ,postOrder.invoke(check).toString());
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

                linkageCheck(preList,postList,getRoot.invoke(check));
            }

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
            Class<?> bst = Class.forName(generateClassName("DS5_BinarySearchTree"));
            Method getRoot = bst.getMethod("getRoot");
            Method nodeDepth = bst.getMethod("nodeDepth",Comparable.class);

            for(int t=0; t<adds.length; t++)
            {
                Object check =  bst.getConstructor().newInstance();
                buildTree(check, t);

                for (int v = 1; v <= 20; v++) {
                        Assert.assertEquals("Failed nodeDepth("+v+") check after adding these values: " + Arrays.toString(adds[t]), nodeDepthAdds[t][v-1],((Integer) nodeDepth.invoke(check, v)).intValue());
                }
                String[] postList = postOrdersAdds[t].substring(1,postOrdersAdds[t].length()-1).split(", ");
                String[] preList = preOrdersPostAdds[t].substring(1,preOrdersPostAdds[t].length()-1).split(", ");

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
}
