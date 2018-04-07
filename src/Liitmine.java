import java.util.List;

public class Liitmine extends Ülesanne {
    private String tehe = "+";
    private int a;
    private int b;
    private int LiitmiseVastus;


    public Liitmine(int ülempiir) {
        super(ülempiir);
        a = (int) Math.round(Math.random() * ülempiir + 0);
        b = (int) Math.round(Math.random() * (ülempiir - a) + 0);
        LiitmiseVastus = a + b;
    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = " + LiitmiseVastus;
    }

    @Override
    String getVastus() {
        return String.valueOf(LiitmiseVastus);
    }
}
