public class FriendlyGenie extends Genie {

    private int availableWish;


    public FriendlyGenie(int maxNumberOfWishes){
        super(maxNumberOfWishes);
        availableWish = 0;
        System.out.println("You now have a Friendly Genie and you can make " + maxNumberOfWishes + " wishes");

    }

    @Override
    public boolean makeWish(String wish){
        if (availableWish < super.getMaxNumberOfWishes()){
            super.makeWish(wish);
            availableWish++;
            return true;
        } else {
            System.out.println("You don't have more wishes!!");
            return false;
        }
    }

}
