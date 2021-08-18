public class Main {
    // Magic Lamp
    // Genie
    public static void main(String[] args) {
        MagicLamp magicLamp = new MagicLamp(2);
        Genie genie1 = magicLamp.geniesOfLamp(2);
        genie1.makeWish("hdh");
        genie1.makeWish("hf");
        genie1.makeWish("hdhd");
        Genie genie2 = magicLamp.geniesOfLamp(2);
        genie2.makeWish("ha");
        genie2.makeWish("hd");
        Genie genie3 = magicLamp.geniesOfLamp(2);
        genie3.makeWish("haha");
        genie3.makeWish("hasha");
        magicLamp.recharge((RecyclableDemon) genie3);
        Genie genie4 = magicLamp.geniesOfLamp(3);
        genie4.makeWish("hd");








    }



}
