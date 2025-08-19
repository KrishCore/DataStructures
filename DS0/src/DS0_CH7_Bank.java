import java.util.ArrayList;

public class DS0_CH7_Bank
{
    private
    String bankName;
    ArrayList<DS0_CH7_Account> accounts;

    public DS0_CH7_Bank(String name)
    {
        this.bankName = name;
        accounts = new ArrayList<DS0_CH7_Account>();
    }
    public boolean openAccount(long accountNumber, String customerName, double startingBalance)
    {
        boolean tf = false;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber)
                tf = true;
        }
        if (startingBalance <= 0 || tf)
            return false;
        else
        {
            accounts.add(new DS0_CH7_Account(accountNumber, customerName, startingBalance));
            return true;
        }
    }
    public double closeAccount(long accountNumber)
    {
        for (int i = accounts.size()-1; i >= 0; i--) {
            if (accounts.get(i).getAccountNumber() == accountNumber)
                return accounts.remove(i).getBalance();
        }
        return -1;
    }
    public void setName(String bankName)
    {
        this.bankName = bankName;
    }
    public String getName()
    {
        return bankName;
    }
    public DS0_CH7_Account getAccount(long accountNumber)
    {
        DS0_CH7_Account ga = null;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber)
                ga = accounts.get(i);
        }
        return ga;
    }
    public ArrayList<DS0_CH7_Account> getAccounts()
    {
        return accounts;
    }
}
