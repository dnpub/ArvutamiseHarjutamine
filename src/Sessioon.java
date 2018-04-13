import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sessioon {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private DateFormat kell = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();
    private String sessiooniID;
    private String kasutajaNimi = testArvutamine.kysiNimi();
    private List<Harjutuskord> harjutuskordList = new ArrayList<>();
    private List<String> harjustuskorraKellaaeg = new ArrayList<>();

// millisel kujul kirjutada faili ?
    // sessiooni id (alustamise kellaaeg + kasutaja) + harjutuskorra jknr + harjutuskorra kellaaeg + harjutuskorrakokkuvõte
    // võib muuta vastavalt logi -loogikale


    public Sessioon() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dateFormat.format(date).toString() + "; " + kasutajaNimi); //
        sessiooniID = stringBuilder.toString();
        boolean harjutama = true;

        // tekitame harjutuskorra
        while (harjutama) {
            Date kellaaeg = new Date();
            boolean k = testArvutamine.kasAjaPeale();
            Harjutuskord h1 = null;


            // võib-olla oleks lihtsam teha üks konstruktor harjutuskorrale ( if kasAjapeale -> limiidiväärtuse sisu), siis ei peaks lahknemist siin tegema?
            if (k == false) {
                h1 = new Harjutuskord(k, testArvutamine.kysiTeheteValik(), testArvutamine.kysiLimiidiVaartus(k), testArvutamine.kysiRaskusaste());
            } else {
                h1 = new Harjutuskord(k, testArvutamine.kysiLimiidiVaartus(k), testArvutamine.kysiTeheteValik(), testArvutamine.kysiRaskusaste());
            }

            boolean vastus = kasAlustame();
            if (vastus) {
                boolean tingimus = true; // aeg on ok või ülesandeid < kui limiidi väärtus : pole kirjutud
                while (tingimus) {
                    Ülesanne ülesanne = h1.genereeriÜlesanne(h1.getTeheteValik(), h1.getRaskusAste());
                    //System.out.println(h1.getTeheteValik());
                    System.out.println(ülesanne.toString());
                    System.out.println(ülesanne.getVastus());
                    tingimus = false;
                }
            }


            // salvestame harjutuskorra statistika
            harjustuskorraKellaaeg.add(kell.format(kellaaeg).toString());// hetkel lõpuaeg
            harjutuskordList.add(h1);
            harjutama = false;
        }
    }

    public List<Harjutuskord> getHarjutuskordList() {
        return harjutuskordList;
    }

    public String getSessiooniID() {
        return sessiooniID;
    }

    public List<String> getHarjustuskorraKellaaeg() {
        return harjustuskorraKellaaeg;
    }

    public String annaHarjutused() { // harjutuskordade väljaprindiks
        String s = "";
        int index = 0;
        for (int i = 0; i < harjutuskordList.size(); i++) {
            index = i + 1;

            s += "\n" + "h" + index + " " + harjustuskorraKellaaeg.get(i) + " " + harjutuskordList.get(i);
        }
        return s;
    }


    public boolean kasAlustame() { // praegu kui pole 1, 2 - aga on arv, siis returnitakse false
        Scanner scan = new Scanner(System.in);
        while (true) {
            Integer vastus = null;
            System.out.println("Kas alustame jah (1) või ei (2)? Sisesta 1 või 2");
            try {
                vastus = scan.nextInt();
                if (vastus == 1) {
                    return true;
                } else if (vastus == 2) {
                    return false;
                }
                return false;
            } catch (NumberFormatException e) { // ideaalis peaks enda exceptioni looma, et iga vastus mis pole 1 või 2 catchitaks
                System.out.println("Pole arv!");
            }

        }
    }


    @Override
    public String toString() {
        return "Sessioon: " + getSessiooniID() + " " + annaHarjutused();
    }
}
