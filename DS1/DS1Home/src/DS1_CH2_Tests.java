
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DS1_CH2_Tests {
    public String generateClassName(String name)
    {
        if(getClass().toString().contains("."))
        {
            return getClass().toString().substring(6,getClass().toString().lastIndexOf(".")+1)+name;
        }
        return name;
    }



    @Before
    public void setup()
    {

    }

    @Test(timeout = 250)
    public void test1() throws Exception {
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "names1.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Elliana\n" +
                    "Sean\n" +
                    "Amari\n" +
                    "Yusuf");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 2;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "names1.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Drew\n" +
                    "Huxley\n" +
                    "Kareem\n" +
                    "Judah\n" +
                    "Jamison");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 0;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "names2.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Alice\n" +
                    "Eloise\n" +
                    "Ian\n" +
                    "Owen\n" +
                    "Umar");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 5;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "names2.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Macie\n" +
                    "Beatrice\n" +
                    "Franklin\n" +
                    "Atticus\n" +
                    "Augustus\n" +
                    "Aniya");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 3;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "test.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Estella\n" +
                    "Bryce\n" +
                    "Nyomi\n" +
                    "Ishaan\n" +
                    "Octavia\n" +
                    "Royce\n" +
                    "Octavia\n" +
                    "Yahir\n" +
                    "Alice\n" +
                    "Finley\n" +
                    "Macy\n" +
                    "Raphael\n" +
                    "Harmoni\n" +
                    "Jayce\n" +
                    "Emmeline\n" +
                    "Nicholas");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 6;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "test.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Dariel\n" +
                    "Frankie\n" +
                    "Madilynn\n" +
                    "Huxley\n" +
                    "Zainab\n" +
                    "Wayne\n" +
                    "Kimberly\n" +
                    "Amari\n" +
                    "Sabrina\n" +
                    "Jesse\n" +
                    "Aleena\n" +
                    "Adam\n" +
                    "Gloria\n" +
                    "Gianni\n" +
                    "Alisson\n" +
                    "Archer");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 5;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "turtles.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Ashton\n" +
                    "Bexley\n" +
                    "Matias\n" +
                    "Mckinley\n" +
                    "Felipe\n" +
                    "Marlee\n" +
                    "Van\n" +
                    "Aubrie\n" +
                    "Deandre\n" +
                    "Olive\n" +
                    "Hector\n" +
                    "Miracle\n" +
                    "Hudson");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 3;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "turtles.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Alvaro\n" +
                    "Emerson\n" +
                    "Terrance");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 2;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "turtles.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Kylo\n" +
                    "Emmy\n" +
                    "Alonso\n" +
                    "Hayden\n" +
                    "Felix\n" +
                    "Lilliana\n" +
                    "River \n" +
                    "Remy\n" +
                    "Santiago\n" +
                    "Elsa\n" +
                    "Spencer\n" +
                    "Kaiya\n" +
                    "Ashton\n" +
                    "Bexley\n" +
                    "Matias\n" +
                    "Mckinley\n" +
                    "Felipe");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 4;

            Assert.assertEquals(expected, actual);
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
        Class<?> classRef = Class.forName(generateClassName("DS1_CH2"));
        Method method = classRef.getMethod("vowelNames",String.class);

        String fileName = "whatName.txt";
        File file = new File(fileName);
        try
        {
            if(file.exists())
                file.delete();
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print("Cara\n" +
                    "Benson\n" +
                    "Eve\n" +
                    "Harley\n" +
                    "Heidi\n" +
                    "Marcos");
            printWriter.close();

            int actual = (int)method.invoke(method, fileName);
            int expected = 1;

            Assert.assertEquals(expected, actual);
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
