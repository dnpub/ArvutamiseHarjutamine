import java.io.Console;

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


        JagamiseVastus= (int)Math.round(Math.random()*(ülempiir/2)+1);
        int kordaja = ülempiir/JagamiseVastus;
        b =(int) Math.round(Math.random()*(kordaja-2)+2);  //

        a=JagamiseVastus*b;
    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = " + JagamiseVastus;
    }

    @Override
    String getVastus() {
        return String.valueOf(JagamiseVastus);

    }
}

