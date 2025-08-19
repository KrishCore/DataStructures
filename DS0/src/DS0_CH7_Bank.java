import java.util.ArrayList;

public class DS0_CH7_Bank
{
    private
    String bankName;
//    ArrayList<Account> accounts;

    public DS0_CH7_Bank(String name)
    {
        this.bankName = name;
//        accounts = new ArrayList<Account>();
    }
//    public boolean openAccount(long accountNumber, String customerName double startingBalance)
//    {
//
//    }
//    public double closeAccount(long accountNumber)
//    {
//
//    }
    public void setName(String bankName)
    {
        this.bankName = bankName;
    }
    public String getName()
    {
        return bankName;
    }
//    public Account getAccount(long accountNumber)
//    {
//
//    }
//    public ArrayList<Account> getAccounts()
//    {
//        return accounts;
//    }
}
