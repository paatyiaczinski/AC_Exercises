public class Person {

    private String name;
    private double wallet;
    private ATM atm;

    public Person (String name){
        ATM atm = new ATM(name);
        this.name = name;
        this.atm =atm;

    }
    public String getName() {
        return name;
    }
    public void balance(){
        atm.balance();
    }
    public void deposit(double transferForBank) {
        if (wallet >= transferForBank){
            atm.deposit(transferForBank);
            wallet -= transferForBank;
        } else{
            System.out.println("You don't enough money in your wallet");
        }
    }
    public void withdraw(double transferForPerson){
        atm.withdraw(transferForPerson);
        wallet += transferForPerson;
    }
    public void checkWallet(){
        System.out.println("You have " + wallet + " in your wallet");
    }

}
