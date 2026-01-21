
//import SolutionsAndTests.CS_DS.u8.ChallengeSet4.DS8_Path_Solution;
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
import static org.junit.Assert.assertNull;

public class DS8_BFS_Unweighted_Tests {
    String[][] testGraphs = {
            {"BD", "FA"},
            {"CE", "CF", "BE", "BA"},
            {"BC", "ED", "GC", "BE", "FG", "CE"},
            {"DA", "CB", "AB", "CA", "BD"},
            {"AC", "BA", "DB", "BE", "DE", "DA"},
            {"DB", "AE", "DC", "EB", "DE"},
            {"DE", "EA", "CG", "BE", "CE", "FH", "GH", "AF", "FG"},
            {"EG", "CF", "AB"},
            {"FB", "EC", "AC"},
            {"CA", "AD"},
            {"BC", "EB", "DH", "AB", "BF", "CD", "AE", "BD"},
            {"CB", "DC", "BA"},
            {"AD", "DF", "GE", "FB", "BE", "CA", "AB", "EF"},
            {"CA", "BA", "BC", "BD", "DA", "CD"},
            {"AC", "ED", "AD", "AB", "BD", "EA", "DC", "FD"},
            {"AC", "AD", "BA", "CD", "BC", "BD"},
            {"DG", "GE", "BD", "CF", "AB", "DF"},
            {"BE", "DB"},
            {"BC", "EA"},
            {"GF", "AD", "BG", "DF", "FC", "BF", "EG", "DC"},
            {"GH", "BD", "BC", "AD", "DG", "FH"},
            {"AC", "DB", "CD", "AB", "AD"},
            {"BC", "AB", "DB", "CA"},
            {"GE", "DG", "DB", "FE"},
            {"BF", "EF", "CA", "BA", "EC", "AD", "CF"},
            {"AD", "BC", "EA"},
            {"CE", "DH", "HE", "BG", "EG"},
            {"DA", "BD"},
            {"FA", "CB", "FC", "AB", "FD", "FE"},
            {"BD", "EF", "DC", "EC", "FB", "DA", "GF"},
    };
    String[] testVertices =
            {"ABCDEFG", "ABCDEFG", "ABCDEFG", "ABCD", "ABCDE", "ABCDE", "ABCDEFGH", "ABCDEFGH", "ABCDEFG", "ABCD", "ABCDEFGH", "ABCDEF", "ABCDEFG", "ABCD", "ABCDEF", "ABCD", "ABCDEFG", "ABCDE", "ABCDE", "ABCDEFG", "ABCDEFGH", "ABCD", "ABCD", "ABCDEFGH", "ABCDEF", "ABCDE", "ABCDEFGH", "ABCD", "ABCDEF", "ABCDEFG"};
    String[][] testSolutions = {
            {"ABnull", "ACnull", "ADnull", "AEnull", "AFAF", "AGnull", "BAnull", "BCnull", "BDBD", "BEnull", "BFnull", "BGnull", "CAnull", "CBnull", "CDnull", "CEnull", "CFnull", "CGnull", "DAnull", "DBDB", "DCnull", "DEnull", "DFnull", "DGnull", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGnull", "FAFA", "FBnull", "FCnull", "FDnull", "FEnull", "FGnull", "GAnull", "GBnull", "GCnull", "GDnull", "GEnull", "GFnull"},
            {"ABAB", "ACABEC", "ADnull", "AEABE", "AFABECF", "AGnull", "BABA", "BCBEC", "BDnull", "BEBE", "BFBECF", "BGnull", "CACEBA", "CBCEB", "CDnull", "CECE", "CFCF", "CGnull", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "DGnull", "EAEBA", "EBEB", "ECEC", "EDnull", "EFECF", "EGnull", "FAFCEBA", "FBFCEB", "FCFC", "FDnull", "FEFCE", "FGnull", "GAnull", "GBnull", "GCnull", "GDnull", "GEnull", "GFnull"},
            {"ABnull", "ACnull", "ADnull", "AEnull", "AFnull", "AGnull", "BAnull", "BCBC", "BDBED", "BEBE", "BFBCGF", "BGBCG", "CAnull", "CBCB", "CDCED", "CECE", "CFCGF", "CGCG", "DAnull", "DBDEB", "DCDEC", "DEDE", "DFDECGF", "DGDECG", "EAnull", "EBEB", "ECEC", "EDED", "EFECGF", "EGECG", "FAnull", "FBFGCB", "FCFGC", "FDFGCED", "FEFGCE", "FGFG", "GAnull", "GBGCB", "GCGC", "GDGCED", "GEGCE", "GFGF"},
            {"ABAB", "ACAC", "ADAD", "BABA", "BCBC", "BDBD", "CACA", "CBCB", "CDCBD", "DADA", "DBDB", "DCDAC"},
            {"ABAB", "ACAC", "ADAD", "AEABE", "BABA", "BCBAC", "BDBD", "BEBE", "CACA", "CBCAB", "CDCAD", "CECABE", "DADA", "DBDB", "DCDAC", "DEDE", "EAEBA", "EBEB", "ECEBAC", "EDED"},
            {"ABAEB", "ACAEDC", "ADAED", "AEAE", "BABEA", "BCBDC", "BDBD", "BEBE", "CACDEA", "CBCDB", "CDCD", "CECDE", "DADEA", "DBDB", "DCDC", "DEDE", "EAEA", "EBEB", "ECEDC", "EDED"},
            {"ABAEB", "ACAEC", "ADAED", "AEAE", "AFAF", "AGAFG", "AHAFH", "BABEA", "BCBEC", "BDBED", "BEBE", "BFBEAF", "BGBECG", "BHBEAFH", "CACEA", "CBCEB", "CDCED", "CECE", "CFCGF", "CGCG", "CHCGH", "DADEA", "DBDEB", "DCDEC", "DEDE", "DFDEAF", "DGDECG", "DHDEAFH", "EAEA", "EBEB", "ECEC", "EDED", "EFEAF", "EGECG", "EHEAFH", "FAFA", "FBFAEB", "FCFGC", "FDFAED", "FEFAE", "FGFG", "FHFH", "GAGFA", "GBGCEB", "GCGC", "GDGCED", "GEGCE", "GFGF", "GHGH", "HAHFA", "HBHFAEB", "HCHGC", "HDHFAED", "HEHFAE", "HFHF", "HGHG"},
            {"ABAB", "ACnull", "ADnull", "AEnull", "AFnull", "AGnull", "AHnull", "BABA", "BCnull", "BDnull", "BEnull", "BFnull", "BGnull", "BHnull", "CAnull", "CBnull", "CDnull", "CEnull", "CFCF", "CGnull", "CHnull", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "DGnull", "DHnull", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGEG", "EHnull", "FAnull", "FBnull", "FCFC", "FDnull", "FEnull", "FGnull", "FHnull", "GAnull", "GBnull", "GCnull", "GDnull", "GEGE", "GFnull", "GHnull", "HAnull", "HBnull", "HCnull", "HDnull", "HEnull", "HFnull", "HGnull"},
            {"ABnull", "ACAC", "ADnull", "AEACE", "AFnull", "AGnull", "BAnull", "BCnull", "BDnull", "BEnull", "BFBF", "BGnull", "CACA", "CBnull", "CDnull", "CECE", "CFnull", "CGnull", "DAnull", "DBnull", "DCnull", "DEnull", "DFnull", "DGnull", "EAECA", "EBnull", "ECEC", "EDnull", "EFnull", "EGnull", "FAnull", "FBFB", "FCnull", "FDnull", "FEnull", "FGnull", "GAnull", "GBnull", "GCnull", "GDnull", "GEnull", "GFnull"},
            {"ABnull", "ACAC", "ADAD", "BAnull", "BCnull", "BDnull", "CACA", "CBnull", "CDCAD", "DADA", "DBnull", "DCDAC"},
            {"ABAB", "ACABC", "ADABD", "AEAE", "AFABF", "AGnull", "AHABDH", "BABA", "BCBC", "BDBD", "BEBE", "BFBF", "BGnull", "BHBDH", "CACBA", "CBCB", "CDCD", "CECBE", "CFCBF", "CGnull", "CHCDH", "DADBA", "DBDB", "DCDC", "DEDBE", "DFDBF", "DGnull", "DHDH", "EAEA", "EBEB", "ECEBC", "EDEBD", "EFEBF", "EGnull", "EHEBDH", "FAFBA", "FBFB", "FCFBC", "FDFBD", "FEFBE", "FGnull", "FHFBDH", "GAnull", "GBnull", "GCnull", "GDnull", "GEnull", "GFnull", "GHnull", "HAHDBA", "HBHDB", "HCHDC", "HDHD", "HEHDBE", "HFHDBF", "HGnull"},
            {"ABAB", "ACABC", "ADABCD", "AEnull", "AFnull", "BABA", "BCBC", "BDBCD", "BEnull", "BFnull", "CACBA", "CBCB", "CDCD", "CEnull", "CFnull", "DADCBA", "DBDCB", "DCDC", "DEnull", "DFnull", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "FAnull", "FBnull", "FCnull", "FDnull", "FEnull"},
            {"ABAB", "ACAC", "ADAD", "AEABE", "AFADF", "AGABEG", "BABA", "BCBAC", "BDBFD", "BEBE", "BFBF", "BGBEG", "CACA", "CBCAB", "CDCAD", "CECABE", "CFCADF", "CGCABEG", "DADA", "DBDAB", "DCDAC", "DEDFE", "DFDF", "DGDFEG", "EAEBA", "EBEB", "ECEBAC", "EDEFD", "EFEF", "EGEG", "FAFDA", "FBFB", "FCFDAC", "FDFD", "FEFE", "FGFEG", "GAGEBA", "GBGEB", "GCGEBAC", "GDGEFD", "GEGE", "GFGEF"},
            {"ABAB", "ACAC", "ADAD", "BABA", "BCBC", "BDBD", "CACA", "CBCB", "CDCD", "DADA", "DBDB", "DCDC"},
            {"ABAB", "ACAC", "ADAD", "AEAE", "AFADF", "BABA", "BCBAC", "BDBD", "BEBAE", "BFBDF", "CACA", "CBCAB", "CDCD", "CECAE", "CFCDF", "DADA", "DBDB", "DCDC", "DEDE", "DFDF", "EAEA", "EBEDB", "ECEDC", "EDED", "EFEDF", "FAFDA", "FBFDB", "FCFDC", "FDFD", "FEFDE"},
            {"ABAB", "ACAC", "ADAD", "BABA", "BCBC", "BDBD", "CACA", "CBCB", "CDCD", "DADA", "DBDB", "DCDC"},
            {"ABAB", "ACABDFC", "ADABD", "AEABDGE", "AFABDF", "AGABDG", "BABA", "BCBDFC", "BDBD", "BEBDGE", "BFBDF", "BGBDG", "CACFDBA", "CBCFDB", "CDCFD", "CECFDGE", "CFCF", "CGCFDG", "DADBA", "DBDB", "DCDFC", "DEDGE", "DFDF", "DGDG", "EAEGDBA", "EBEGDB", "ECEGDFC", "EDEGD", "EFEGDF", "EGEG", "FAFDBA", "FBFDB", "FCFC", "FDFD", "FEFDGE", "FGFDG", "GAGDBA", "GBGDB", "GCGDFC", "GDGD", "GEGE", "GFGDF"},
            {"ABnull", "ACnull", "ADnull", "AEnull", "BAnull", "BCnull", "BDBD", "BEBE", "CAnull", "CBnull", "CDnull", "CEnull", "DAnull", "DBDB", "DCnull", "DEDBE", "EAnull", "EBEB", "ECnull", "EDEBD"},
            {"ABnull", "ACnull", "ADnull", "AEAE", "BAnull", "BCBC", "BDnull", "BEnull", "CAnull", "CBCB", "CDnull", "CEnull", "DAnull", "DBnull", "DCnull", "DEnull", "EAEA", "EBnull", "ECnull", "EDnull"},
            {"ABADFB", "ACADC", "ADAD", "AEADFGE", "AFADF", "AGADFG", "BABFDA", "BCBFC", "BDBFD", "BEBGE", "BFBF", "BGBG", "CACDA", "CBCFB", "CDCD", "CECFGE", "CFCF", "CGCFG", "DADA", "DBDFB", "DCDC", "DEDFGE", "DFDF", "DGDFG", "EAEGFDA", "EBEGB", "ECEGFC", "EDEGFD", "EFEGF", "EGEG", "FAFDA", "FBFB", "FCFC", "FDFD", "FEFGE", "FGFG", "GAGFDA", "GBGB", "GCGFC", "GDGFD", "GEGE", "GFGF"},
            {"ABADB", "ACADBC", "ADAD", "AEnull", "AFADGHF", "AGADG", "AHADGH", "BABDA", "BCBC", "BDBD", "BEnull", "BFBDGHF", "BGBDG", "BHBDGH", "CACBDA", "CBCB", "CDCBD", "CEnull", "CFCBDGHF", "CGCBDG", "CHCBDGH", "DADA", "DBDB", "DCDBC", "DEnull", "DFDGHF", "DGDG", "DHDGH", "EAnull", "EBnull", "ECnull", "EDnull", "EFnull", "EGnull", "EHnull", "FAFHGDA", "FBFHGDB", "FCFHGDBC", "FDFHGD", "FEnull", "FGFHG", "FHFH", "GAGDA", "GBGDB", "GCGDBC", "GDGD", "GEnull", "GFGHF", "GHGH", "HAHGDA", "HBHGDB", "HCHGDBC", "HDHGD", "HEnull", "HFHF", "HGHG"},
            {"ABAB", "ACAC", "ADAD", "BABA", "BCBDC", "BDBD", "CACA", "CBCAB", "CDCD", "DADA", "DBDB", "DCDC"},
            {"ABAB", "ACAC", "ADABD", "BABA", "BCBC", "BDBD", "CACA", "CBCB", "CDCBD", "DADBA", "DBDB", "DCDBC"},
            {"ABnull", "ACnull", "ADnull", "AEnull", "AFnull", "AGnull", "AHnull", "BAnull", "BCnull", "BDBD", "BEBDGE", "BFBDGEF", "BGBDG", "BHnull", "CAnull", "CBnull", "CDnull", "CEnull", "CFnull", "CGnull", "CHnull", "DAnull", "DBDB", "DCnull", "DEDGE", "DFDGEF", "DGDG", "DHnull", "EAnull", "EBEGDB", "ECnull", "EDEGD", "EFEF", "EGEG", "EHnull", "FAnull", "FBFEGDB", "FCnull", "FDFEGD", "FEFE", "FGFEG", "FHnull", "GAnull", "GBGDB", "GCnull", "GDGD", "GEGE", "GFGEF", "GHnull", "HAnull", "HBnull", "HCnull", "HDnull", "HEnull", "HFnull", "HGnull"},
            {"ABAB", "ACAC", "ADAD", "AEACE", "AFACF", "BABA", "BCBFC", "BDBAD", "BEBFE", "BFBF", "CACA", "CBCAB", "CDCAD", "CECE", "CFCF", "DADA", "DBDAB", "DCDAC", "DEDACE", "DFDACF", "EAECA", "EBEFB", "ECEC", "EDECAD", "EFEF", "FAFBA", "FBFB", "FCFC", "FDFBAD", "FEFE"},
            {"ABnull", "ACnull", "ADAD", "AEAE", "BAnull", "BCBC", "BDnull", "BEnull", "CAnull", "CBCB", "CDnull", "CEnull", "DADA", "DBnull", "DCnull", "DEDAE", "EAEA", "EBnull", "ECnull", "EDEAD"},
            {"ABnull", "ACnull", "ADnull", "AEnull", "AFnull", "AGnull", "AHnull", "BAnull", "BCBGEC", "BDBGEHD", "BEBGE", "BFnull", "BGBG", "BHBGEH", "CAnull", "CBCEGB", "CDCEHD", "CECE", "CFnull", "CGCEG", "CHCEH", "DAnull", "DBDHEGB", "DCDHEC", "DEDHE", "DFnull", "DGDHEG", "DHDH", "EAnull", "EBEGB", "ECEC", "EDEHD", "EFnull", "EGEG", "EHEH", "FAnull", "FBnull", "FCnull", "FDnull", "FEnull", "FGnull", "FHnull", "GAnull", "GBGB", "GCGEC", "GDGEHD", "GEGE", "GFnull", "GHGEH", "HAnull", "HBHEGB", "HCHEC", "HDHD", "HEHE", "HFnull", "HGHEG"},
            {"ABADB", "ACnull", "ADAD", "BABDA", "BCnull", "BDBD", "CAnull", "CBnull", "CDnull", "DADA", "DBDB", "DCnull"},
            {"ABAB", "ACAFC", "ADAFD", "AEAFE", "AFAF", "BABA", "BCBC", "BDBCFD", "BEBCFE", "BFBCF", "CACBA", "CBCB", "CDCFD", "CECFE", "CFCF", "DADFA", "DBDFAB", "DCDFC", "DEDFE", "DFDF", "EAEFA", "EBEFAB", "ECEFC", "EDEFD", "EFEF", "FAFA", "FBFAB", "FCFC", "FDFD", "FEFE"},
            {"ABADB", "ACADC", "ADAD", "AEADCE", "AFADBF", "AGADBFG", "BABDA", "BCBDC", "BDBD", "BEBFE", "BFBF", "BGBFG", "CACDA", "CBCDB", "CDCD", "CECE", "CFCEF", "CGCEFG", "DADA", "DBDB", "DCDC", "DEDCE", "DFDBF", "DGDBFG", "EAECDA", "EBEFB", "ECEC", "EDECD", "EFEF", "EGEFG", "FAFBDA", "FBFB", "FCFEC", "FDFBD", "FEFE", "FGFG", "GAGFBDA", "GBGFB", "GCGFEC", "GDGFBD", "GEGFE", "GFGF"},
    };

    String[][] staticGraphs = {
            {"AC", "AB", "BD", "BE", "EH", "EF", "FG", "FC", "GC"},
            {"AC", "AE", "BI", "BD", "BG", "BF", "BE", "CI", "DI", "DG", "HI"},
            {"AB", "AC", "BC", "DE", "DF", "EF"}
    };
    String[] staticVertices =
            {"ABCDEFGH", "ABCDEFGHI", "ABCDEF"};
    String[][] staticSolutions = {
            {"ABAB", "ACAC", "ADABD", "AEABE", "AFACF", "AGACG", "AHABEH", "BABA", "BCBAC", "BDBD", "BEBE", "BFBEF", "BGBACG", "BHBEH", "CACA", "CBCAB", "CDCABD", "CECFE", "CFCF", "CGCG", "CHCFEH", "DADBA", "DBDB", "DCDBAC", "DEDBE", "DFDBEF", "DGDBACG", "DHDBEH", "EAEBA", "EBEB", "ECEFC", "EDEBD", "EFEF", "EGEFG", "EHEH", "FAFCA", "FBFEB", "FCFC", "FDFEBD", "FEFE", "FGFG", "FHFEH", "GAGCA", "GBGFEB", "GCGC", "GDGFEBD", "GEGFE", "GFGF", "GHGFEH", "HAHEBA", "HBHEB", "HCHEFC", "HDHEBD", "HEHE", "HFHEF", "HGHEFG"},
            {"ABAEB", "ACAC", "ADACID", "AEAE", "AFAEBF", "AGAEBG", "AHACIH", "AIACI", "BABEA", "BCBIC", "BDBD", "BEBE", "BFBF", "BGBG", "BHBIH", "BIBI", "CACA", "CBCIB", "CDCID", "CECAE", "CFCIBF", "CGCIBG", "CHCIH", "CICI", "DADBEA", "DBDB", "DCDIC", "DEDBE", "DFDBF", "DGDG", "DHDIH", "DIDI", "EAEA", "EBEB", "ECEAC", "EDEBD", "EFEBF", "EGEBG", "EHEBIH", "EIEBI", "FAFBEA", "FBFB", "FCFBIC", "FDFBD", "FEFBE", "FGFBG", "FHFBIH", "FIFBI", "GAGBEA", "GBGB", "GCGBIC", "GDGD", "GEGBE", "GFGBF", "GHGBIH", "GIGBI", "HAHICA", "HBHIB", "HCHIC", "HDHID", "HEHIBE", "HFHIBF", "HGHIBG", "HIHI", "IAICA", "IBIB", "ICIC", "IDID", "IEIBE", "IFIBF", "IGIBG", "IHIH"},
            {"ABAB", "ACAC", "ADnull", "AEnull", "AFnull", "BABA", "BCBC", "BDnull", "BEnull", "BFnull", "CACA", "CBCB", "CDnull", "CEnull", "CFnull", "DAnull", "DBnull", "DCnull", "DEDE", "DFDF", "EAnull", "EBnull", "ECnull", "EDED", "EFEF", "FAnull", "FBnull", "FCnull", "FDFD", "FEFE"}
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
        String className = "DS8_BFS";
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
            Class<?> dfs = Class.forName(generateClassName("DS8_BFS"));
            Method unweighted = dfs.getMethod("breadthFirstSearch_Unweighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<staticGraphs.length; g++)
                for(int t=0; t<staticSolutions[g].length; t++) {
                    if(staticSolutions[g][t].charAt(2)=='n')
                        assertNull("breadthFirstSearch_Unweighted(" + Arrays.toString(staticGraphs[g]) + ", " + staticVertices[g] + ", " +staticSolutions[g][t].charAt(0)+", "+staticSolutions[g][t].charAt(1)+") failed to produce null.",
                                unweighted.invoke(dfs,staticGraphs[g],staticVertices[g],staticSolutions[g][t].charAt(0),staticSolutions[g][t].charAt(1)));
                    else
                        checkSolution(
                                "breadthFirstSearch_Unweighted(" + Arrays.toString(staticGraphs[g]) + ", " + staticVertices[g] + ", " +staticSolutions[g][t].charAt(0)+", "+staticSolutions[g][t].charAt(1)+") failed ",
                                staticGraphs[g],
                                staticSolutions[g][t],
                                (String) unweighted.invoke(dfs,staticGraphs[g],staticVertices[g],staticSolutions[g][t].charAt(0),staticSolutions[g][t].charAt(1)));
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
            Class<?> dfs = Class.forName(generateClassName("DS8_BFS"));
            Method unweighted = dfs.getMethod("breadthFirstSearch_Unweighted", String[].class,String.class,char.class,char.class);

            for(int g=0;g<testGraphs.length; g++)
                for(int t=0; t<testSolutions[g].length; t++) {
                    if(testSolutions[g][t].charAt(2)=='n')
                        assertNull("breadthFirstSearch_Unweighted(" + Arrays.toString(testGraphs[g]) + ", " + testVertices[g] + ", " +testSolutions[g][t].charAt(0)+", "+testSolutions[g][t].charAt(1)+") failed to produce null.",
                                unweighted.invoke(dfs,testGraphs[g],testVertices[g],testSolutions[g][t].charAt(0),testSolutions[g][t].charAt(1)));
                    else
                        checkSolution(
                                "breadthFirstSearch_Unweighted(" + Arrays.toString(testGraphs[g]) + ", " + testVertices[g] + ", " +testSolutions[g][t].charAt(0)+", "+testSolutions[g][t].charAt(1)+") failed ",
                                testGraphs[g],
                                testSolutions[g][t],
                                (String) unweighted.invoke(dfs,testGraphs[g],testVertices[g],testSolutions[g][t].charAt(0),testSolutions[g][t].charAt(1)));
                }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    public void checkSolution(String message,String[] edgesIn, String teacherSolution,String studentSolution)
    {
        ArrayList<String> edgesList = new ArrayList<>();
        for(String ed:edgesIn)
            edgesList.add(ed);

        Assert.assertNotNull(message + "to produce a path",studentSolution);

        Assert.assertEquals(message + "to produce a path of the correct length",teacherSolution.length()-2,studentSolution.length());

        Assert.assertEquals(message + "to produce a path that starts with "+teacherSolution.charAt(0),teacherSolution.charAt(0),studentSolution.charAt(0));
        Assert.assertEquals(message + "to produce a path that ends with "+teacherSolution.charAt(1),teacherSolution.charAt(1),studentSolution.charAt(studentSolution.length()-1));

        Assert.assertEquals(message + "to produce a path that ends with "+teacherSolution.charAt(1),teacherSolution.charAt(1),studentSolution.charAt(studentSolution.length()-1));

        for(int x =1; x<studentSolution.length(); x++)
            Assert.assertTrue(message + " due to containing an invalid edge.",
                    edgesList.contains(studentSolution.charAt(x-1)+""+studentSolution.charAt(x)) ||
                            edgesList.contains(studentSolution.charAt(x)+""+studentSolution.charAt(x-1)) );

    }


}
