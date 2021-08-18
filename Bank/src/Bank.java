public class Bank {

    private double money;
    private String name;
    public Bank(double money, String name){
        this.money = money;
        this.name = name;
    }
    public double deposit(double transferForBank){
        double currentMoney = this.money + transferForBank;
        System.out.println("You have deposited " + transferForBank + " euros. You now have " + currentMoney + " euros in your bank account.");
        return this.money = currentMoney;
    }
    public double withdraw(double transferForPerson){
        if(money>=transferForPerson){
            double currentMoney = this.money - transferForPerson;
            System.out.println("You have withdrawn " + transferForPerson + " euro. You now have " + currentMoney + " euros in your bank account.");
            return this.money = currentMoney;
        } else {
            System.out.println("You don't have enough money for this transaction. Please check your balance.");
            return this.money;
        }
    }
    public void balance(){
        System.out.println("You have " + money + "euros in your account.");
    }
}