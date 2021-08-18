public class RecyclableDemon extends Genie{


        private String wish;
        private boolean demonRecyclable;

    public RecyclableDemon(int maxNumberOfWishes) {
        super(maxNumberOfWishes);
        this.demonRecyclable = false;
        System.out.println("You now have a Recyclable Damon and you can make " + maxNumberOfWishes + " wishes");
    }
    @Override
    public boolean makeWish(String wish) {
        if (demonRecyclable == false) {
            super.makeWish(wish);
            return true;
        } else {
            return false;
        }
    }

    public boolean isRecycled(){
            return demonRecyclable;
        }
    public void demonRecyclable(){
            this.demonRecyclable = true;
            }





}


