public class DS0_CH7_Account
{
    private
    long accountNumber;
    String customerName;
    double balance;

    public DS0_CH7_Account(long accountNumber, String customerName, double balance)
    {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }
    public boolean deposit(double depositAmount) {
        if (depositAmount > 0) {
            balance += depositAmount;
            return true;
        }
        else return false;
    }
    public boolean withdraw(double amount)
    {
        if (amount > balance || amount <= 0)
            return false;
        else
        {
            balance -= amount;
            return true;
        }
    }
    public long getAccountNumber()
    {
        return accountNumber;
    }
    public double getBalance()
    {
        return balance;
    }
    public String getCustomerName()
    {
        return customerName;
    }
}
