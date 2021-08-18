public class Genie {
    private int maxNumberOfWishes;
    private String wish;

    public Genie(int maxNumberOfWishes){
        this.maxNumberOfWishes = maxNumberOfWishes;
    }
    public boolean makeWish(String wish){
        System.out.println("Your wish " + wish + " was granted!!");
        return true;
    }
    public int getMaxNumberOfWishes() {
        return maxNumberOfWishes;
    }


}
