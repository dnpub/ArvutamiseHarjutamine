import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
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


    public Sessioon() throws FileNotFoundException, UnsupportedEncodingException {

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
                h1 = new Harjutuskord(k, testArvutamine.kysiTeheteValik(), testArvutamine.kysiLimiidiVaartus(k), testArvutamine.kysiRaskusaste()); //peaks lisama selle, et 0 ei ole OK ülesannete arv, mida sisestada
            } else {
                h1 = new Harjutuskord(k, testArvutamine.kysiLimiidiVaartus(k), testArvutamine.kysiTeheteValik(), testArvutamine.kysiRaskusaste());
            }
            boolean vastus = kasAlustame();
            Stopper stopper1 = new Stopper();//siin fikseeritakse harjutuste tegemise alguse aeg ehk pannakse stopper käima
            if (vastus) {
                boolean tingimus = true; // aeg on ok või ülesandeid < kui limiidi väärtus : pole kirjutud
                int allesJaanudYlesanneteArv = h1.getYlesanneteLimiit();
                while (tingimus) {
                    Ülesanne ülesanne = h1.genereeriÜlesanne(h1.getTeheteValik(), h1.getRaskusAste());
                    //System.out.println(h1.getTeheteValik());
                    System.out.println(ülesanne.toString());

                    //kasutajalt vastuse küsimine, selle õigsuse hindamine ja sellele vastavalt statistika uuendamine.
                    if (kasVastusOige(kysiVastus(), Integer.parseInt(ülesanne.getVastus()))) {
                        System.out.println("Tubli! Õige vastus!");
                        h1.setOigeidVastuseid(h1.getOigeidVastuseid() + 1);//suurendamine õigete vastuste hulka harjutuskorras
                    } else {
                        System.out.println("Vale vastus! Õige vastus on: " + ülesanne.getVastus());
                    }

                    System.out.println("Lahendamisele seni kulunud aeg sekundites: " + stopper1.annaKulunudAegSekundites());

                    if (h1.kasAjaPeale()) {
                        System.out.println("Lahendamise lõpuni jäänud aeg sekundites: " + stopper1.annaAllesjaanudAegSekundites(h1.getAjalimiit()));
                        System.out.println("Kas etteantud aeg juba läbi: " + stopper1.kasAegLabi(h1.getAjalimiit()));
                    }

                    //System.out.println("Järgmine ülesanne tekitatakse? " + tingimus);

                    //vastusega ülesannete arvu suurendamine
                    h1.setLahendatudYlesandeid(h1.getLahendatudYlesandeid() + 1);

                    //lahendamisele seni kulunud aja muutmine
                    h1.setLahendamiseAeg((int) stopper1.annaKulunudAegSekundites());
                    System.out.println();

                    //ülesannete tekitamise jätkamise otsustamine
                    if (h1.kasAjaPeale() == false) {//ülesannete arvu limiidiga harjutuskorra järgmise ülesande tekitamise tingimuse arvutamine
                        allesJaanudYlesanneteArv--;//iga tsükli korraga vähendame allesjäänud ülesannete arvu
                        if (allesJaanudYlesanneteArv == 0) {
                            tingimus = false; //kui allesjäänud ülesandeid enam pole, siis neid juurde ei tekitata ja harjutuskord lõpeb
                        } else {
                            tingimus = true;
                        }
                    } else { //aja limiidiga ülesannete korral tuleb kontrollida allesjäänud aega ning otsustada jätkamine selle alusel

                        if (stopper1.kasAegLabi(h1.getAjalimiit())) {
                            stopper1.aegSeisma();
                            tingimus = false; //kui aeg on läbi, siis rohkem ülesandeid ei tekitata
                        } else {
                            tingimus = true;
                        }
                    }
                    //System.out.println("Järgmine ülesanne tekitatakse? " + tingimus);
                }
            }


            // salvestame harjutuskorra statistika
            harjustuskorraKellaaeg.add(kell.format(kellaaeg).toString());// hetkel lõpuaeg
            harjutuskordList.add(h1);


            //kirjutame harjutuskorra andmed logifaili
            h1.kirjutaHarjutuskordFaili();

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


    public boolean kasAlustame() { //
        Scanner scan = new Scanner(System.in);
        boolean tagasta = false; // kasAlustame? vastus
        boolean test = true; // testime, kas kasutaja on õigesti vastanud, kui ei ole, küsime sisestust uuesti
        while (test) {
            String vastus = null;
            System.out.println("Kas alustame jah (1) või ei (2)? Sisesta 1 või 2");
            vastus = scan.nextLine();
            try {
                if(vastus.equals("x")){System.exit(0);}
                if (Integer.parseInt(vastus) == 1) {
                    tagasta = true; // saime vastuse
                    test = false; // lõpetame küsimise
                } else if (Integer.parseInt(vastus) == 2) {
                    tagasta = false; // saime vastuse
                    test = false;// lõpetame küsimise
                } else {
                    System.out.println("Pole sobib arv"); // saime int vastuse, aga see pole 1 ega 2, küsime uuesti
                }

            } catch (NumberFormatException e) { // saime vastuseks midagi, mis pole int;
                System.out.println("Pole arv!");
            }

        }
        return tagasta;
    }

    public int kysiVastus() {
        Scanner scan = new Scanner(System.in);
        Integer vastus = null;
        while (true) { // küsime kuni saame sobival kujul vastuse;
            System.out.println("Sisesta vastus (täisarv): ");
            String s;
            try {
                s=scan.nextLine();
                if(s.equals("x")){System.exit(0);}
                vastus = Integer.parseInt(scan.nextLine()); // kas vastus on int ?
                //System.out.println("Oli täisarv");
                break;
            } catch (NumberFormatException e) { // vastus polnud int
                System.out.println("Ei olnud täisarv!");
            }
        }
        return vastus;
    }

    public boolean kasVastusOige(int kasutajaVastus, int oigeVastus) {
        if (kasutajaVastus == oigeVastus) {
            return true;
        } else return false;
    }

    @Override
    public String toString() {
        return "Sessioon: " + getSessiooniID() + " " + annaHarjutused();
    }
}
