package ContactList;

public class Person
{
    String first, last, address;
    int number;

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

    public String getNumber()
    {
        return last;
    }

    public String getAddress()
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
}
