package ContactList;

public class Person
{
    String first, last, address;
    int number;
    public Person(String first, String last)
    {
        this.first = first;
        this.last = last;
    }

    public String getFirstName()
    {
        return first;
    }

    public String getLastName()
    {
        return last;
    }

    public void setFirstName(String first)
    {
        this.first = first;
    }

    public void setLastName(String last)
    {
        this.last = last;
    }

    @Override
    public String toString()
    {
        return last + ", " + first;
    }
}
