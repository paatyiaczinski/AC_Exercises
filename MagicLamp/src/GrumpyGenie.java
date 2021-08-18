public class GrumpyGenie extends Genie {

    private String wish;
    private boolean availableWish;
    //private int

    public GrumpyGenie(int maxNumberOfWishes) {
        super(maxNumberOfWishes);
        availableWish = true;
        System.out.println("You now have a Grumpy Genie and you can make " + maxNumberOfWishes + " wishes");
    }
    @Override
    public boolean makeWish(String wish) {
        if (availableWish) {
            super.makeWish(wish);
            return availableWish = false;
        } else {
            System.out.println("Trick question...You don't have more wishes!!");
            return false;
        }

    }

}



