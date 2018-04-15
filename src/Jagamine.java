import java.io.Console;
import java.util.Random;

public class Jagamine extends Ülesanne {
    private String tehe = "/";
    private int a;
    private int b;
    private int JagamiseVastus;

    public Jagamine(int ülempiir) {

        super(ülempiir);
        // a/b=c
        // c, max ülempiir/2
        // b, max ülempiir/c
/*

        JagamiseVastus= (int)Math.round(Math.random()*(ülempiir/2)+1);
        int kordaja = ülempiir/JagamiseVastus;
        b =(int) Math.round(Math.random()*(kordaja-2)+2);  //
        a=JagamiseVastus*b;*/

        int i =0;
        while (i < getÜlempiir()) {

            Random random = new Random();
            boolean k = true;
            while (k == true) {
                JagamiseVastus = (int) Math.round(random.nextGaussian() * 50 + 0.3 * ülempiir);
                b = (int) Math.round(Math.random() * (ülempiir*0.5) + 2);
                // b = (int) Math.round(random.nextGaussian() * 35 + 0.5 * ülempiir);

                a = b * JagamiseVastus;

                if (a < ülempiir && JagamiseVastus > 0 && a != b) {

                    i++;
                    k = false;
                }
            }
        }

        }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = ?";
    }

    @Override
    String getVastus() {
        return String.valueOf(JagamiseVastus);

    }
}

