
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

public class DS3_UniqueWords_Tests {

    public TestingOutputStream testingOutputStream = new TestingOutputStream();

    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }

    public String unifyLineSeperators(String line)
    {
        return line.replaceAll("\\n|\\r\\n", System.getProperty("line.separator")).trim();
    }

    @Before
    public void setup()
    {
        System.setOut(testingOutputStream);
    }

    @Test(timeout = 250)
    public void test1() throws Exception {
        String input =  "A.txt";

        String expected =   "Enter the file name: \n" +
                "the\n" +
                "sun\n" +
                "rises\n" +
                "in\n" +
                "east\n" +
                "and\n" +
                "sets\n" +
                "west\n" +
                "every\n" +
                "morning\n" +
                "birds\n" +
                "sing\n" +
                "world\n" +
                "wakes\n" +
                "up\n" +
                "nature\n" +
                "is\n" +
                "beautiful";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "A.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("The sun rises in the east and sets in the \n" +
                    "west. Every morning, birds sing and the world wakes \n" +
                    "up. Nature is beautiful.\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test2() throws Exception {
        String input =  "B.txt";

        String expected =   "Enter the file name: \n" +
                "artificial\n" +
                "intelligence\n" +
                "is\n" +
                "changing\n" +
                "the\n" +
                "world\n" +
                "from\n" +
                "healthcare\n" +
                "to\n" +
                "finance\n" +
                "ai\n" +
                "making\n" +
                "tasks\n" +
                "easier\n" +
                "and\n" +
                "faster\n" +
                "it\n" +
                "truly\n" +
                "revolutionary";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "B.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Artificial intelligence is changing the world. From healthcare to finance, \n" +
                    "AI is making tasks easier and faster. It is truly revolutionary.\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception {
        String input =  "C.txt";

        String expected =   "Enter the file name: \n" +
                "programming\n" +
                "in\n" +
                "python\n" +
                "can\n" +
                "be\n" +
                "fun\n" +
                "and\n" +
                "challenging\n" +
                "you\n" +
                "automate\n" +
                "tasks\n" +
                "analyze\n" +
                "data\n" +
                "even\n" +
                "build\n" +
                "games\n" +
                "learning\n" +
                "opens\n" +
                "many\n" +
                "doors\n";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "C.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Programming in Python can be fun and challenging. \n" +
                    "You can automate tasks, analyze data, and even build games. \n" +
                    "Learning Python opens many doors.\n\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception {
        String input =  "D.txt";

        String expected =   "Enter the file name: \n" +
                "history\n" +
                "teaches\n" +
                "us\n" +
                "valuable\n" +
                "lessons\n" +
                "by\n" +
                "studying\n" +
                "the\n" +
                "past\n" +
                "we\n" +
                "understand\n" +
                "present\n" +
                "and\n" +
                "can\n" +
                "shape\n" +
                "future\n" +
                "knowledge\n" +
                "is\n" +
                "power\n";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "D.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("History teaches us valuable lessons. \n" +
                    "By studying the past, we understand the present \n" +
                    "and can shape the future. Knowledge is power.");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception {
        String input =  "E.txt";

        String expected =   "Enter the file name: \n" +
                "programming\n" +
                "in\n" +
                "python\n" +
                "can\n" +
                "be\n" +
                "fun\n" +
                "and\n" +
                "challenging\n" +
                "you\n" +
                "automate\n" +
                "tasks\n" +
                "analyze\n" +
                "data\n" +
                "even\n" +
                "build\n" +
                "games\n" +
                "learning\n" +
                "opens\n" +
                "many\n" +
                "doors\n";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "E.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Programming in Python can be fun and challenging. You can automate tasks, analyze data, and even build games. Learning Python opens many doors.");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception {
        String input =  "F.txt";

        String expected =   "Enter the file name: \n" +
                "traveling\n" +
                "allows\n" +
                "people\n" +
                "to\n" +
                "experience\n" +
                "new\n" +
                "cultures\n" +
                "meet\n" +
                "and\n" +
                "explore\n" +
                "different\n" +
                "places\n" +
                "every\n" +
                "journey\n" +
                "is\n" +
                "a\n" +
                "story\n" +
                "waiting\n" +
                "be\n" +
                "told";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "F.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Traveling allows people \n" +
                    "to experience new cultures, meet new \n" +
                    "people, and explore different places. \n" +
                    "Every journey is a story waiting to be told.");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception {
        String input =  "G.txt";

        String expected =   "Enter the file name: \n" +
                "life\n" +
                "is\n" +
                "what\n" +
                "happens\n" +
                "when\n" +
                "youre\n" +
                "busy\n" +
                "making\n" +
                "other\n" +
                "plans\n" +
                "said\n" +
                "john\n" +
                "indeed\n" +
                "moments\n" +
                "pass\n" +
                "quickly\n" +
                "laughter\n" +
                "tears\n" +
                "joy\n" +
                "and\n" +
                "sorrow\n" +
                "all\n" +
                "shape\n" +
                "our\n" +
                "journey";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "G.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("\"Life is what happens when you’re busy making \n" +
                    "other plans,\" said John. Indeed, moments pass quickly: \n" +
                    "laughter, tears, joy and sorrow all shape our journey.\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception {
        String input =  "H.txt";

        String expected =   "Enter the file name: \n" +
                "on\n" +
                "a\n" +
                "rainy\n" +
                "day\n" +
                "the\n" +
                "streets\n" +
                "were\n" +
                "empty\n" +
                "puddles\n" +
                "formed\n" +
                "everywhere\n" +
                "children\n" +
                "masked\n" +
                "in\n" +
                "raincoats\n" +
                "splashed\n" +
                "happily\n" +
                "shouting\n" +
                "look\n" +
                "at\n" +
                "me";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "H.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("On a rainy day, the streets were empty;\n" +
                    " puddles formed everywhere. Children masked in \n" +
                    "raincoats splashed happily, shouting: " +
                    "\n\"Look at me!\".\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception {
        String input =  "I.txt";

        String expected =   "Enter the file name: \n" +
                "cooking\n" +
                "is\n" +
                "both\n" +
                "an\n" +
                "art\n" +
                "and\n" +
                "a\n" +
                "science\n" +
                "ingredients\n" +
                "fresh\n" +
                "herbs\n" +
                "spices\n" +
                "vegetables\n" +
                "combine\n" +
                "to\n" +
                "create\n" +
                "flavors\n" +
                "timing\n" +
                "matters\n" +
                "techniques\n" +
                "matter\n" +
                "even\n" +
                "more\n";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "I.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Cooking is both an art and a science. \n" +
                    "Ingredients (fresh herbs, spices, vegetables) \n" +
                    "combine to create flavors; timing matters, \n" +
                    "techniques matter even more.\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception {
        String input =  "J.txt";

        String expected =   "Enter the file name: \n" +
                "success\n" +
                "isnt\n" +
                "final\n" +
                "failure\n" +
                "fatal\n" +
                "its\n" +
                "the\n" +
                "courage\n" +
                "to\n" +
                "continue\n" +
                "that\n" +
                "counts\n" +
                "winston\n" +
                "churchill\n" +
                "motivation\n" +
                "perseverance\n" +
                "and\n" +
                "effort\n" +
                "lead\n" +
                "achievements\n";

        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        Class<?> classRef = Class.forName(generateClassName("DS3_UniqueWords"));
        Object classObject = classRef.getConstructor().newInstance();

        Method main = classRef.getMethod("main",String[].class);

        String fileName = "J.txt";
        File file = new File(fileName);

        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("\"Success isn’t final, failure isn’t \n" +
                    "fatal: it’s the courage to continue that counts.\n" +
                    "\" – Winston Churchill. Motivation, perseverance, \n" +
                    "and effort lead to achievements!\n");
            printWriter.close();

            main.invoke(classObject,(Object)null);

            expected = unifyLineSeperators(expected);

            TestingOutputStream o = (TestingOutputStream)System.out;
            Assert.assertEquals(expected, unifyLineSeperators(o.toString()));
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
        catch(Exception e)
        {
            StackTraceElement[] ste = new StackTraceElement[]{};
            e.setStackTrace(ste);
            throw e;
        }
        finally {
            file.delete();
        }
    }
}
