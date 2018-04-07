public class Korrutamine extends Ülesanne {
    private String tehe = "*";
    private int a;
    private int b;
    private int KorrutamimiseVastus;

    public Korrutamine(int ülempiir) {
        super(ülempiir);

        boolean test = true;
        while (test == true) {
            //int s = (int) Math.round(Math.random() * (ülempiir / 2) + 0);

            a = (int) Math.round(Math.random() * ülempiir + 2);//
            int kordaja = ülempiir / a;

            b = (int) Math.round(Math.random() * (kordaja - 2) + 2);
            KorrutamimiseVastus = a * b;
            if (b > ülempiir/3){
            test = false;}
        }

    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = " + KorrutamimiseVastus;
    }


    @Override
    String getVastus() {
        return String.valueOf(KorrutamimiseVastus);
    }
}
