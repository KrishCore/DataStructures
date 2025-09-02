
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DS8_CH8_Sale_Tests {

    DS8_CH8_Item[] originalItems = {    new DS8_CH8_Item("Candy Bar",1.99, DS8_CH8_Item.NORMAL),
            new DS8_CH8_Item("Shoe",42.99, DS8_CH8_Item.NORMAL),
            new DS8_CH8_Item("Dress",55.55, DS8_CH8_Item.NORMAL),
            new DS8_CH8_Item("Model Car",23.99, DS8_CH8_Item.NORMAL),
            new DS8_CH8_Item("Phone",599.87, DS8_CH8_Item.NORMAL),
            new DS8_CH8_Item("Hat",9.87, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Computer",1234.56, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Chair",15.99, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Alarm Clock",6.36, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Turtle",20.20, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Art Supplies",23.65, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Board Game",59.09, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Bat",15.21, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Soccer Ball",13.42, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Stapler",5.53, DS8_CH8_Item.CLEARANCE)};

    DS8_CH8_Item[] resultingItems = {    new DS8_CH8_Item("Candy Bar",1.393, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Shoe",30.093, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Dress",38.885, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Model Car",16.793, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Phone",419.909, DS8_CH8_Item.DISCOUNTED),
            new DS8_CH8_Item("Hat",4.935, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Computer",617.28, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Chair",7.995, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Alarm Clock",3.18, DS8_CH8_Item.CLEARANCE),
            new DS8_CH8_Item("Turtle",10.10, DS8_CH8_Item.CLEARANCE),
            null,
            null,
            null,
            null,
            null};

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


    public DS8_CH8_Item clone(DS8_CH8_Item i)
    {
        return new DS8_CH8_Item(i.getName(),i.getPrice(),i.getStatus());
    }


    @Test(timeout = 250)
    public void test1() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {0};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }


    }

    @Test(timeout = 250)
    public void test2() throws Exception
    {
        try
        {
            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {5};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test3() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {10};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test4() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {1,3,6,13,14};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test5() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test6() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test7() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {0,1,5,6,7,8,11,12};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test8() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {0,2,4,6,8,10,12,14};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test9() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {3,6,9,13};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }

    @Test(timeout = 250)
    public void test10() throws Exception
    {
        try
        {

            Class<?> classRef = Class.forName(generateClassName("DS8_CH8_Sale"));
            Method method = classRef.getMethod("sale", ArrayList.class);

            ArrayList<DS8_CH8_Item> actual = new ArrayList<>();
            ArrayList<DS8_CH8_Item> expected = new ArrayList<>();
            int[] itemIndexes = {7,8,9};

            for(int i: itemIndexes) {
                actual.add(clone(originalItems[i]));
                if(resultingItems[i]!=null)
                    expected.add(resultingItems[i]);
            }

            method.invoke(method, actual);

            Assert.assertEquals(expected.size(), actual.size());
            for(int i=0; i < expected.size(); i++) {
                Assert.assertEquals(expected.get(i).getName(), actual.get(i).getName());
                Assert.assertEquals(expected.get(i).getPrice(), actual.get(i).getPrice(),.00011);
            }
        }
        catch ( InvocationTargetException e )
        {
            throw (Exception) e.getCause();
        }
    }
}
