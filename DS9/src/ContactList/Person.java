package ContactList;

public class Person implements Comparable<Person>
{
    private String first, last, address;
    private int number = -1;


    public Person(String first, String last)
    {
        this.first = first;
        this.last = last;
    }

    public Person(String first, String last, int number)
    {
        this.first = first;
        this.last = last;
        this.number = number;
    }

    public Person(String first, String last, String address)
    {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    public Person(String first, String last, int number, String address)
    {
        this.first = first;
        this.last = last;
        this.number = number;
        this.address = address;
    }

    public String getFirstName()
    {
        return first;
    }

    public String getLastName()
    {
        return last;
    }

    public int getNumber()
    {
        return number;
    }

    public String getAddress()
    {
        return address;
    }

    public void setFirstName(String first)
    {
        this.first = first;
    }

    public void setLastName(String last)
    {
        this.last = last;
    }


    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return last + ", " + first;
    }

    @Override
    public int compareTo(Person o) {
        return toString().compareTo(o.toString());
    }
}
