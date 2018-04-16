import java.util.Random;

public class Korrutamine extends Ülesanne {
    private String tehe = "*";
    private int a;
    private int b;
    private int KorrutamimiseVastus;

    public Korrutamine(int ülempiir) {
        super(ülempiir);

        Random random= new Random();
        boolean test = true;
        while (test) {
            //int s = (int) Math.round(Math.random() * (ülempiir / 2) + 0);
            /*
            a = (int) Math.round(Math.random() * ülempiir + 2);//
            int kordaja = ülempiir / a;

            b = (int) Math.round(Math.random() * (kordaja - 3) + 0);
            KorrutamimiseVastus = a * b;
            if (b > ülempiir/3){
            test = false;}*/

            if (ülempiir==10){
               // a = (int) Math.round(random.nextGaussian()*ülempiir*0.5 + 0.6*ülempiir);//
                //b= (int) Math.round(random.nextGaussian()*ülempiir*0.5+ 0.5*ülempiir);//
                a= (int) Math.round(Math.random()*(ülempiir*0.5)+1);
                b= (int) Math.round(Math.random()*(ülempiir)+1);
                }
            else{
                a = (int) Math.round(random.nextGaussian()*ülempiir*0.7 + 0.5*ülempiir);//
                b= (int) Math.round(random.nextGaussian()*ülempiir*0.7 + ülempiir*0.5);}//

            KorrutamimiseVastus = a * b;
            if(a*b<ülempiir){ // valitakse välja a ja b, et korrutis oleks < ülempiir
                if(ülempiir>10 && b>2 && a>1){
                    test= false;
                }
                else if(ülempiir==10 && a>=1 && b>=1){
                test = false;}

            }
        }

    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = ?";
    }


    @Override
    String getVastus() {
        return String.valueOf(KorrutamimiseVastus);
    }
}
