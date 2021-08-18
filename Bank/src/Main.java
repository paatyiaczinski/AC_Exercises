public class Main {
    public static void main(String[] args) {
        Person person1 =  new Person( "Rita");
        person1.balance();
        person1.deposit(20);
        person1.withdraw(40);
        person1.withdraw(20);
        person1.deposit(10);
        person1.checkWallet();

    }
}
