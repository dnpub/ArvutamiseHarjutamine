import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//testklass
public class testArvutamine {



    //peameetod
    public static void main(String[] args) {

        List<String> võimalikudTehted = Arrays.asList("+", "-", "*", "/");

        int ülempiir = 100;

        int i = 0;
        List<Ülesanne> esitatudÜlesanded = new ArrayList<>();
        while (i < 100) {
            String tehe = getTehe(võimalikudTehted);
            switch (tehe) {
                case "+":
                    Ülesanne ül1 = new Liitmine(ülempiir);
                    esitatudÜlesanded.add(ül1);
                    i++;
                    break;
                case "-":
                    Ülesanne ül2 = new Lahutamine(ülempiir);
                    esitatudÜlesanded.add(ül2);
                    i++;
                    break;
                case "*":
                    Ülesanne ül3 = new Korrutamine(ülempiir);
                    esitatudÜlesanded.add(ül3);
                    i++;
                    break;
                case "/":
                    Ülesanne ül4 = new Jagamine(ülempiir);
                    esitatudÜlesanded.add(ül4);
                    i++;
                    break;
            }
            System.out.println(esitatudÜlesanded.get(esitatudÜlesanded.size() - 1));
        }


    }

    //meetodid

    //valitud tehete hulgast juhuslikult ühe tehte valimine
    static String getTehe(List<String> tehted) {
        int a = 0;
        a = (int) Math.round(Math.random() * 3 + 0);
        String valitudTehe = tehted.get(a);
        return valitudTehe;
    }

    //kasutajalt nime küsimine

    //kasutajalt limiidi tüübi küsimine

    //kasutajalt limiidi väärtuse küsimine

    //ajalimiidiga harjutuskorra loomine

    //ülesannete limiidiga harjutuskorra loomine

    //ülesande tekitamine

    //ülesande esitamine kasutajale ja kasutaja vastuse registreerimine
    // esimese ülesande esitamisega koos tuleb ka stopper käima panna
    //ajalimiidiga harjutuskorra puhul tuleks näidata ka allesjäänud aega, teatud regulaarsusega
    // ülesannete limiidi korral viimase ülesande lahendamisega koos tuleb ka stopper seisma panna või siis ajalimiidi korral tule


}
