import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class DS8_Dijkstras_Weighted_Tests
{
    String[][] testGraphs = {
            {"BA10", "AD4", "CB5", "EC5", "CF5", "BC1"},
            {"EC1", "BD14", "FB13", "CE9", "BF2", "DB9"},
            {"AH1", "EH4", "CD7", "CG1", "FD7", "CA9", "EB14", "AC13", "AB11", "DF14", "AG10", "FC6", "AF10", "DA11", "FB4", "GB3"},
            {"AB5", "BC12", "EA6", "AE4", "EB9", "AD13", "CE14", "DB7", "ED13", "CB7", "CA5"},
            {"CB11", "DC13", "CA3", "AE7", "DB2", "AD11", "BA3", "BC13"},
            {"GF5", "EA5", "FB3", "BH8", "DG8", "CD9", "EC6", "AB10", "CF1", "EH1"},
            {"DB10", "AD4", "DA5", "CD5", "BA1", "CA13"},
            {"AD7", "ED11", "AC6", "BC13", "EC2", "CF2"},
            {"FG14", "GD12", "EB3", "BE11", "CF12", "AG2", "DF6", "AE14", "GF1", "DC6", "AD5"},
            {"DB10", "DC3", "BC13", "AB9", "AC7", "CB6", "CD6", "BA14"},
            {"DB12", "AB13", "AD9", "BA1", "BD14", "DA5", "CA5", "DC2", "BC8", "CD5"},
            {"DB5", "CE2", "EA1", "CB1", "CA7", "CF6", "BD7", "BA14", "DA3", "BE14"},
            {"BC10", "CB3", "DB11", "DC2", "CD3", "CA7"},
            {"AE3", "DE7", "DA4", "GC9", "AF11", "AG12"},
            {"AE10", "GB2", "DC4", "EG13", "CB13", "FG10", "EC12", "GE2", "CE8", "FB7", "ED2", "AD6", "DF3", "GF9", "DA10", "AC9"},
            {"ED4", "FD7", "BA7", "CF7", "CA1", "BC14", "BF3", "AC9", "DF1", "FA9"},
            {"CE4", "AD10", "AE7", "DB6", "CD2", "EC14", "DE3", "EA8", "CB10"},
            {"AE13", "GF1", "BD7", "EG7", "AB3", "GD14", "GC4", "EC11", "AF6", "EA11", "AD2", "CG2", "HB12"},
            {"BD14", "AG13", "GB1", "CE9", "FA2", "AB2", "FC3", "FD14", "GF12", "EC11", "DF11", "AC1", "GD14"},
            {"DA1", "CA7", "BC10", "DC3", "AC14", "DB7", "CB4", "AB4", "CD7", "BA1"},
            {"AC6", "HA8", "EF9", "CF11", "DH1", "HB12", "BD2", "DC5", "AG13", "AH4", "HC3", "GH4", "DA8", "GE2", "HG5", "GD8", "EB10"},
            {"FC1", "EG13", "EA12", "AG9", "DG10", "AD8", "AF5", "EC14"},
            {"AC10", "AD3", "BC13", "BA13", "AB3", "DB3", "CA4", "CD9", "CB9", "DC1"},
            {"AB4", "CB12", "AC8", "CD3", "BA13", "BD14", "DB8", "DC1"},
            {"CB10", "AE5", "EA12", "BC7", "ED9", "DB13", "BD5", "BE6", "DA6"},
            {"GE5", "CD8", "BC13", "BA7", "AB13", "FE14", "EB2", "GB5", "DA11", "ED2", "GF9", "DF14", "FD14"},
            {"AC12", "AD3", "CD6", "DC5", "BC5", "BD8", "AB7", "CB13"},
            {"DF2", "FD7", "CF1", "DB2", "CA5", "EB4", "BA8"},
            {"EC2", "BG11", "EB6", "CA7", "DB4", "CD2", "BE6", "BC4", "ED2", "AB11"},
            {"EA11", "BD5", "AC3", "EB1", "DA13", "BE7", "BC13", "CA2", "AD7", "ED7", "EC14", "CE8", "CD11"}
    };
    String[] testVertices =
            {"ABCDEFG", "ABCDEF", "ABCDEFGH", "ABCDE", "ABCDE", "ABCDEFGH", "ABCD", "ABCDEF", "ABCDEFG", "ABCD", "ABCD", "ABCDEF", "ABCD", "ABCDEFG", "ABCDEFG", "ABCDEF", "ABCDE", "ABCDEFGH", "ABCDEFG", "ABCD", "ABCDEFGH", "ABCDEFG", "ABCD", "ABCD", "ABCDE", "ABCDEFG", "ABCD", "ABCDEF", "ABCDEFG", "ABCDE"};
    String[][] testSolutions = {
            {"AB-1", "AC-1", "AD4", "AE-1", "AF-1", "AG-1", "BA10", "BC1", "BD14", "BE-1", "BF6", "BG-1", "CA15", "CB5", "CD19", "CE-1", "CF5", "CG-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "DG-1", "EA20", "EB10", "EC5", "ED24", "EF10", "EG-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "GA-1", "GB-1", "GC-1", "GD-1", "GE-1", "GF-1"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "AF-1", "BA-1", "BC-1", "BD14", "BE-1", "BF2", "CA-1", "CB-1", "CD-1", "CE9", "CF-1", "DA-1", "DB9", "DC-1", "DE-1", "DF11", "EA-1", "EB-1", "EC1", "ED-1", "EF-1", "FA-1", "FB13", "FC-1", "FD27", "FE-1"},
            {"AB11", "AC13", "AD17", "AE-1", "AF10", "AG10", "AH1", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "BG-1", "BH-1", "CA9", "CB4", "CD7", "CE-1", "CF19", "CG1", "CH10", "DA11", "DB18", "DC20", "DE-1", "DF14", "DG21", "DH12", "EA-1", "EB14", "EC-1", "ED-1", "EF-1", "EG-1", "EH4", "FA15", "FB4", "FC6", "FD7", "FE-1", "FG7", "FH16", "GA-1", "GB3", "GC-1", "GD-1", "GE-1", "GF-1", "GH-1", "HA-1", "HB-1", "HC-1", "HD-1", "HE-1", "HF-1", "HG-1"},
            {"AB5", "AC17", "AD13", "AE4", "BA17", "BC12", "BD30", "BE21", "CA5", "CB7", "CD18", "CE9", "DA24", "DB7", "DC19", "DE28", "EA6", "EB9", "EC21", "ED13"},
            {"AB13", "AC24", "AD11", "AE7", "BA3", "BC13", "BD14", "BE10", "CA3", "CB11", "CD14", "CE10", "DA5", "DB2", "DC13", "DE12", "EA-1", "EB-1", "EC-1", "ED-1"},
            {"AB10", "AC-1", "AD-1", "AE-1", "AF-1", "AG-1", "AH18", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "BG-1", "BH8", "CA-1", "CB4", "CD9", "CE-1", "CF1", "CG17", "CH12", "DA-1", "DB16", "DC-1", "DE-1", "DF13", "DG8", "DH24", "EA5", "EB10", "EC6", "ED15", "EF7", "EG23", "EH1", "FA-1", "FB3", "FC-1", "FD-1", "FE-1", "FG-1", "FH11", "GA-1", "GB8", "GC-1", "GD-1", "GE-1", "GF5", "GH16", "HA-1", "HB-1", "HC-1", "HD-1", "HE-1", "HF-1", "HG-1"},
            {"AB14", "AC-1", "AD4", "BA1", "BC-1", "BD5", "CA10", "CB15", "CD5", "DA5", "DB10", "DC-1"},
            {"AB-1", "AC6", "AD7", "AE-1", "AF8", "BA-1", "BC13", "BD-1", "BE-1", "BF15", "CA-1", "CB-1", "CD-1", "CE-1", "CF2", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "EA-1", "EB-1", "EC2", "ED11", "EF4", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1"},
            {"AB17", "AC11", "AD5", "AE14", "AF3", "AG2", "BA-1", "BC-1", "BD-1", "BE11", "BF-1", "BG-1", "CA-1", "CB-1", "CD38", "CE-1", "CF12", "CG26", "DA-1", "DB-1", "DC6", "DE-1", "DF6", "DG20", "EA-1", "EB3", "EC-1", "ED-1", "EF-1", "EG-1", "FA-1", "FB-1", "FC32", "FD26", "FE-1", "FG14", "GA-1", "GB-1", "GC18", "GD12", "GE-1", "GF1"},
            {"AB9", "AC7", "AD13", "BA14", "BC13", "BD19", "CA20", "CB6", "CD6", "DA23", "DB9", "DC3"},
            {"AB13", "AC11", "AD9", "BA1", "BC8", "BD10", "CA5", "CB17", "CD5", "DA5", "DB12", "DC2"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "AF-1", "BA10", "BC-1", "BD7", "BE14", "BF-1", "CA3", "CB1", "CD8", "CE2", "CF6", "DA3", "DB5", "DC-1", "DE19", "DF-1", "EA1", "EB-1", "EC-1", "ED-1", "EF-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1"},
            {"AB-1", "AC-1", "AD-1", "BA17", "BC10", "BD13", "CA7", "CB3", "CD3", "DA9", "DB5", "DC2"},
            {"AB-1", "AC21", "AD-1", "AE3", "AF11", "AG12", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "BG-1", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "CG-1", "DA4", "DB-1", "DC25", "DE7", "DF15", "DG16", "EA-1", "EB-1", "EC-1", "ED-1", "EF-1", "EG-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "GA-1", "GB-1", "GC9", "GD-1", "GE-1", "GF-1"},
            {"AB16", "AC9", "AD6", "AE10", "AF9", "AG19", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "BG-1", "CA20", "CB13", "CD10", "CE8", "CF13", "CG21", "DA10", "DB10", "DC4", "DE12", "DF3", "DG13", "EA12", "EB12", "EC6", "ED2", "EF5", "EG13", "FA24", "FB7", "FC18", "FD14", "FE12", "FG10", "GA14", "GB2", "GC8", "GD4", "GE2", "GF7"},
            {"AB-1", "AC9", "AD23", "AE-1", "AF16", "BA7", "BC14", "BD10", "BE-1", "BF3", "CA1", "CB-1", "CD14", "CE-1", "CF7", "DA10", "DB-1", "DC19", "DE-1", "DF1", "EA14", "EB-1", "EC23", "ED4", "EF5", "FA9", "FB-1", "FC18", "FD7", "FE-1"},
            {"AB16", "AC21", "AD10", "AE7", "BA-1", "BC-1", "BD-1", "BE-1", "CA12", "CB8", "CD2", "CE4", "DA11", "DB6", "DC17", "DE3", "EA8", "EB22", "EC14", "ED16"},
            {"AB3", "AC24", "AD2", "AE13", "AF6", "AG20", "AH-1", "BA-1", "BC-1", "BD7", "BE-1", "BF-1", "BG-1", "BH-1", "CA-1", "CB-1", "CD16", "CE-1", "CF3", "CG2", "CH-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "DG-1", "DH-1", "EA11", "EB14", "EC11", "ED13", "EF8", "EG7", "EH-1", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "FH-1", "GA-1", "GB-1", "GC4", "GD14", "GE-1", "GF1", "GH-1", "HA-1", "HB12", "HC-1", "HD19", "HE-1", "HF-1", "HG-1"},
            {"AB2", "AC1", "AD16", "AE10", "AF25", "AG13", "BA27", "BC28", "BD14", "BE37", "BF25", "BG40", "CA-1", "CB-1", "CD-1", "CE9", "CF-1", "CG-1", "DA13", "DB15", "DC14", "DE23", "DF11", "DG26", "EA-1", "EB-1", "EC11", "ED-1", "EF-1", "EG-1", "FA2", "FB4", "FC3", "FD14", "FE12", "FG15", "GA14", "GB1", "GC15", "GD14", "GE24", "GF12"},
            {"AB4", "AC14", "AD21", "BA1", "BC10", "BD17", "CA5", "CB4", "CD7", "DA1", "DB5", "DC3"},
            {"AB16", "AC6", "AD17", "AE11", "AF17", "AG9", "AH4", "BA10", "BC6", "BD2", "BE10", "BF17", "BG8", "BH3", "CA-1", "CB-1", "CD-1", "CE-1", "CF11", "CG-1", "CH-1", "DA8", "DB13", "DC4", "DE8", "DF15", "DG6", "DH1", "EA20", "EB10", "EC16", "ED12", "EF9", "EG18", "EH13", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "FH-1", "GA12", "GB12", "GC7", "GD8", "GE2", "GF11", "GH4", "HA8", "HB12", "HC3", "HD13", "HE7", "HF14", "HG5"},
            {"AB-1", "AC6", "AD8", "AE-1", "AF5", "AG9", "BA-1", "BC-1", "BD-1", "BE-1", "BF-1", "BG-1", "CA-1", "CB-1", "CD-1", "CE-1", "CF-1", "CG-1", "DA-1", "DB-1", "DC-1", "DE-1", "DF-1", "DG10", "EA12", "EB-1", "EC14", "ED20", "EF17", "EG13", "FA-1", "FB-1", "FC1", "FD-1", "FE-1", "FG-1", "GA-1", "GB-1", "GC-1", "GD-1", "GE-1", "GF-1"},
            {"AB3", "AC4", "AD3", "BA13", "BC13", "BD16", "CA4", "CB7", "CD7", "DA5", "DB3", "DC1"},
            {"AB4", "AC8", "AD11", "BA13", "BC15", "BD14", "CA24", "CB11", "CD3", "DA21", "DB8", "DC1"},
            {"AB27", "AC34", "AD14", "AE5", "BA11", "BC7", "BD5", "BE6", "CA21", "CB10", "CD15", "CE16", "DA6", "DB13", "DC20", "DE11", "EA12", "EB22", "EC29", "ED9"},
            {"AB13", "AC26", "AD34", "AE62", "AF48", "AG-1", "BA7", "BC13", "BD21", "BE49", "BF35", "BG-1", "CA19", "CB32", "CD8", "CE36", "CF22", "CG-1", "DA11", "DB24", "DC37", "DE28", "DF14", "DG-1", "EA9", "EB2", "EC15", "ED2", "EF16", "EG-1", "FA23", "FB16", "FC29", "FD14", "FE14", "FG-1", "GA12", "GB5", "GC18", "GD7", "GE5", "GF9"},
            {"AB7", "AC8", "AD3", "BA-1", "BC5", "BD8", "CA-1", "CB13", "CD6", "DA-1", "DB18", "DC5"},
            {"AB-1", "AC-1", "AD-1", "AE-1", "AF-1", "BA8", "BC-1", "BD-1", "BE-1", "BF-1", "CA5", "CB10", "CD8", "CE-1", "CF1", "DA10", "DB2", "DC-1", "DE-1", "DF2", "EA12", "EB4", "EC-1", "ED-1", "EF-1", "FA17", "FB9", "FC-1", "FD7", "FE-1"},
            {"AB11", "AC15", "AD17", "AE17", "AF-1", "AG22", "BA11", "BC4", "BD6", "BE6", "BF-1", "BG11", "CA7", "CB6", "CD2", "CE12", "CF-1", "CG17", "DA15", "DB4", "DC8", "DE10", "DF-1", "DG15", "EA9", "EB6", "EC2", "ED2", "EF-1", "EG17", "FA-1", "FB-1", "FC-1", "FD-1", "FE-1", "FG-1", "GA-1", "GB-1", "GC-1", "GD-1", "GE-1", "GF-1"},
            {"AB12", "AC3", "AD7", "AE11", "BA15", "BC13", "BD5", "BE7", "CA2", "CB9", "CD9", "CE8", "DA13", "DB25", "DC16", "DE24", "EA11", "EB1", "EC14", "ED6"}
    };

    String[][] staticGraphs = {
            {"ab3", "ae5", "bc8", "bd5", "be3", "dc2", "ed4"},
            {"ab1", "ac2", "ad1", "bc1", "bd2", "db2"},
            {"AD3", "BC6", "CE7", "DA1", "EB5", "EC4", "FE10"}
    };
    String[] staticVertices =
            {"abcde", "abcd", "ABCDEF"};
    String[][] staticSolutions = {
            {"ab3", "ac10", "ad8", "ae5", "ba-1", "bc7", "bd5", "be3", "ca-1", "cb-1", "cd-1", "ce-1", "da-1", "db-1", "dc2", "de-1", "ea-1", "eb-1", "ec6", "ed4"},
            {"ab1", "ac2", "ad1", "ba-1", "bc1", "bd2", "ca-1", "cb-1", "cd-1", "da-1", "db2", "dc3"},
            {"AB-1", "AC-1", "AD3", "AE-1", "AF-1", "BA-1", "BC6", "BD-1", "BE13", "BF-1", "CA-1", "CB12", "CD-1", "CE7", "CF-1", "DA1", "DB-1", "DC-1", "DE-1", "DF-1", "EA-1", "EB5", "EC4", "ED-1", "EF-1", "FA-1", "FB15", "FC14", "FD-1", "FE10"}
    };

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
        String className = "DS8_Dijkstras";
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
            Class<?> dijkstras = Class.forName(generateClassName("DS8_Dijkstras"));
            Method weighted = dijkstras.getMethod("dijkstras_Weighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<staticGraphs.length; g++)
                for(int t=0; t<staticSolutions[g].length; t++) {

                        assertEquals("dijkstras_Weighted(" + Arrays.toString(staticGraphs[g]) + ", " + staticVertices[g] + ", " +staticSolutions[g][t].charAt(0)+", "+staticSolutions[g][t].charAt(1)+") failed to produce the correct result.",Integer.parseInt(staticSolutions[g][t].substring(2)),
                                weighted.invoke(dijkstras,staticGraphs[g],staticVertices[g],staticSolutions[g][t].charAt(0),staticSolutions[g][t].charAt(1)));
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
            Class<?> dijkstras = Class.forName(generateClassName("DS8_Dijkstras"));
            Method weighted = dijkstras.getMethod("dijkstras_Weighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<testGraphs.length; g++)
                for(int t=0; t<testSolutions[g].length; t++) {
                    assertEquals("dijkstras_Weighted(" + Arrays.toString(testGraphs[g]) + ", " + testVertices[g] + ", " +testSolutions[g][t].charAt(0)+", "+testSolutions[g][t].charAt(1)+") failed to produce the correct result.",Integer.parseInt(testSolutions[g][t].substring(2)),
                            weighted.invoke(dijkstras,testGraphs[g],testVertices[g],testSolutions[g][t].charAt(0),testSolutions[g][t].charAt(1)));
                }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
