public class MagicLamp {

    private String wish;
    private int remainingGenes;
    private int recharges;
    private int rubbed; // a new genie every time
    private Genie geniesOfLamp;
    private int maxOfGenies;



    public MagicLamp (int MaxOfGenies){
      this.maxOfGenies = MaxOfGenies;
      this.remainingGenes = this.maxOfGenies;
      this.recharges = 0;
      this.rubbed = 0;




    }


    public Genie geniesOfLamp(int maxNumberOfWishes) {
            if (remainingGenes > 0) {

                if (remainingGenes % 2 == 0) {
                    remainingGenes--;
                    return new FriendlyGenie(maxNumberOfWishes);
                }
                if (remainingGenes % 2 != 0) {
                    remainingGenes--;
                    return new GrumpyGenie(maxNumberOfWishes);
                }

            }
            System.out.println(remainingGenes);
            return new RecyclableDemon(maxNumberOfWishes);
    }
    public void recharge(RecyclableDemon geniesOfLamp){

        if (!geniesOfLamp.isRecycled()){
            this.remainingGenes = this.maxOfGenies;
            geniesOfLamp.demonRecyclable();
            return;
        }
        System.out.println("You don't have more Genies");

    }

}

