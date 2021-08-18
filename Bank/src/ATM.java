public class ATM {

    private Bank account;

    public ATM(String name) {
        Bank account = new Bank(100d, name);
        this.account = account;
    }
    public void balance(){
        account.balance();
    }
    public void deposit(double transferForBank){
        account.deposit(transferForBank);
    }
    public void withdraw(double transferForPerson){
        account.withdraw(transferForPerson);
    }
}







