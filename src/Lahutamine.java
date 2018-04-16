public class Lahutamine extends Ülesanne {
    private String tehe = "-";
    private int a;
    private int b;
    private int LahutamiseVastus;

    public Lahutamine(int ülempiir) {
        super(ülempiir);

        a = (int) Math.round(Math.random() * ülempiir + 0);
        boolean sobib = false;
        while (sobib) {
            b = (int) Math.round(Math.random() * ülempiir + 0);
            if (b < a) {
                sobib = true;
            }
        }
        LahutamiseVastus = a - b;
    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = ?";
    }

    @Override
    String getVastus() {
        return String.valueOf(LahutamiseVastus);
    }
}
