
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DS8_AStar_JetPack_Tests {
    char[][][] testGraphs1 =
            {
                            {{'S','P','O'},
                            {'P','O','P'},
                            {'O','O','E'}},

                    {{'O','S','O','O','P','P','O','O'},
                            {'O','O','O','P','O','P','O','O'},
                            {'O','O','O','O','O','P','O','O'},
                            {'O','O','E','P','P','O','O','O'}},

                    {{'O','O','O','O','O','O','O','O'},
                            {'E','O','P','P','O','P','O','S'},
                            {'O','O','O','O','O','P','O','O'},
                            {'O','O','P','O','O','O','O','O'}},

                    {       {'P','O','O','O','O','O','O','P','O','O','P'},
                            {'P','O','O','P','O','P','P','O','O','O','P'},
                            {'P','O','P','O','P','O','O','O','O','P','O'},
                            {'P','O','P','O','O','O','O','O','O','O','S'},
                            {'O','E','O','P','O','O','O','P','P','P','O'},},
            };

    int [] distances1 = {0,1,3,1};

    char[][][] testGraphs2 = {
            {{'P', 'P', 'E', 'O', 'P', 'P', 'P'}, {'P', 'P', 'S', 'P', 'P', 'P', 'P'}, {'P', 'P', 'O', 'P', 'P', 'P', 'P'}},
            {{'P', 'P'}, {'S', 'P'}, {'O', 'P'}, {'O', 'O'}, {'O', 'P'}, {'O', 'O'}, {'E', 'O'}, {'O', 'O'}},
            {{'O', 'O', 'S', 'E'}},
            {{'S', 'O', 'E', 'O', 'P', 'P'}},
            {{'S', 'E'}},
            {{'P', 'P', 'O', 'O'}, {'E', 'O', 'O', 'P'}, {'P', 'S', 'P', 'P'}},
            {{'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'P'}, {'O', 'E', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'S', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O'}},
            {{'S'}, {'O'}, {'O'}, {'P'}, {'O'}, {'E'}, {'O'}},
            {{'O', 'O', 'O', 'O', 'P', 'O'}, {'O', 'P', 'P', 'P', 'P', 'E'}, {'O', 'P', 'O', 'S', 'O', 'P'}},
            {{'O', 'S', 'O', 'O'}, {'O', 'O', 'P', 'E'}, {'O', 'O', 'O', 'O'}},
            {{'O', 'O', 'O'}, {'E', 'O', 'S'}},
            {{'O', 'O', 'O', 'O', 'P', 'S', 'E'}},
            {{'O'}, {'O'}, {'E'}, {'O'}, {'S'}, {'O'}},
            {{'S', 'O', 'O', 'O', 'O', 'E', 'O', 'O'}},
            {{'P', 'O', 'O', 'O', 'S'}, {'P', 'O', 'O', 'O', 'O'}, {'P', 'O', 'O', 'O', 'O'}, {'P', 'P', 'O', 'O', 'O'}, {'P', 'O', 'O', 'O', 'O'}, {'P', 'O', 'O', 'E', 'O'}, {'O', 'O', 'P', 'O', 'O'}},
            {{'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'E'}, {'O', 'S', 'O', 'O'}, {'O', 'O', 'O', 'O'}, {'O', 'P', 'O', 'O'}, {'O', 'O', 'O', 'O'}},
            {{'P', 'E', 'O', 'P', 'S', 'O', 'O'}},
            {{'P', 'P'}, {'P', 'P'}, {'P', 'P'}, {'P', 'E'}, {'O', 'P'}, {'P', 'P'}, {'P', 'P'}, {'P', 'S'}},
            {{'S', 'E', 'O', 'O', 'O'}},
            {{'O', 'O', 'P', 'O', 'S'}, {'P', 'O', 'O', 'O', 'O'}, {'O', 'P', 'O', 'O', 'O'}, {'P', 'P', 'O', 'O', 'O'}, {'O', 'E', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}},
            {{'S'}, {'P'}, {'P'}, {'E'}, {'P'}, {'O'}},
            {{'P', 'P', 'O'}, {'O', 'S', 'E'}},
            {{'O', 'S', 'P', 'E', 'O'}},
            {{'P', 'P', 'E', 'P', 'O'}, {'S', 'P', 'P', 'P', 'P'}, {'P', 'O', 'P', 'P', 'P'}},
            {{'O'}, {'O'}, {'O'}, {'O'}, {'O'}, {'S'}, {'O'}, {'E'}},
            {{'E'}, {'S'}, {'O'}},
            {{'P', 'P', 'P', 'P', 'O', 'S'}, {'O', 'P', 'P', 'P', 'P', 'P'}, {'P', 'P', 'P', 'P', 'P', 'P'}, {'P', 'P', 'P', 'P', 'P', 'P'}, {'O', 'P', 'P', 'P', 'P', 'P'}, {'P', 'P', 'P', 'P', 'P', 'P'}, {'E', 'P', 'O', 'P', 'P', 'O'}},
            {{'E', 'O', 'O', 'O', 'S'}, {'P', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'P', 'O'}},
            {{'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'S', 'O', 'O', 'O', 'O'}, {'O', 'O', 'E', 'O', 'O'}, {'O', 'O', 'O', 'O', 'O'}, {'O', 'O', 'P', 'O', 'O'}},
            {{'P', 'E', 'P', 'P', 'O', 'P', 'S'}},
    };
    int[] distances2 =
            {0, 2, 0, 1, 0, 0, 2, 3, 0, 0, 1, 0, 1, 4, 3, 1, 1, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 3, 1, 1};

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
        allowedImports.add("java.util.Collections");
        allowedImports.add("java.awt.Point");
        allowedImports.add("java.util.Arrays");
    }

    @Test(timeout = 250)
    public void checkImports() throws Exception{
        String className = "DS8_AStar";
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
            Class<?> aStar = Class.forName(generateClassName("DS8_AStar"));
            Method jetPack = aStar.getMethod("aStar_JetPack", char[][].class);
            for(int x=0; x<testGraphs1.length; x++)
            {
                Integer solution=(Integer) jetPack.invoke(aStar,(Object)testGraphs1[x]);

                Assert.assertEquals(distances1[x], solution.intValue());
            }

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
            Class<?> aStar = Class.forName(generateClassName("DS8_AStar"));
            Method jetPack = aStar.getMethod("aStar_JetPack", char[][].class);
            for(int x=0; x<testGraphs2.length; x++)
            {
                Integer solution=(Integer) jetPack.invoke(aStar,(Object)testGraphs2[x]);
                Assert.assertEquals(distances2[x], solution.intValue());
            }

        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
