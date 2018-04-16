import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sessioon {
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private DateFormat kell = new SimpleDateFormat("HH:mm:ss");
    private Date date = new Date();
    private String sessiooniID;
    private List<Harjutuskord> harjutuskordList = new ArrayList<>();
    private List<String> harjustuskorraKellaaeg = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    private String kasutajaNimi = kysiNimi();


// millisel kujul kirjutada faili ?
    // sessiooni id (alustamise kellaaeg + kasutaja) + harjutuskorra jknr + harjutuskorra kellaaeg + harjutuskorrakokkuvõte
    // võib muuta vastavalt logi -loogikale


    public Sessioon() throws FileNotFoundException, UnsupportedEncodingException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sessiooni algus = " + dateFormat.format(date) + " ; Kasutaja nimi = " + kasutajaNimi + " "); //
        sessiooniID = stringBuilder.toString();
        boolean harjutama = true;

        // tekitame harjutuskorra
        while (harjutama) {
            Date kellaaeg = new Date();
            boolean k = kasAjaPeale();
            Harjutuskord h1 = new Harjutuskord(k, kysiLimiidiVaartus(k), kysiTeheteValik(), kysiRaskusaste());
           /* if (k == false) {
                h1 = new Harjutuskord(k, kysiTeheteValik(), kysiLimiidiVaartus(k), kysiRaskusaste());
            } else {
                h1 = new Harjutuskord(k, kysiLimiidiVaartus(k), kysiTeheteValik(), kysiRaskusaste());
            }*/

            if (kasAlustame()) {  // Kasutajalt küsitakse : kas alustame?
                Stopper stopper1 = new Stopper();//siin fikseeritakse harjutuste tegemise alguse aeg ehk pannakse stopper käima
                boolean tingimus = true; // aeg on ok või ülesandeid < kui limiidi väärtus
                int allesJaanudYlesanneteArv = h1.getYlesanneteLimiit();
                while (tingimus) {
                    Ülesanne ülesanne = h1.genereeriÜlesanne(h1.getTeheteValik(), h1.getRaskusAste());
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
            //h1.kirjutaHarjutuskordFaili();//proovin selle eemaldada, et fail oleks informatiivsem ja sisaldaks igal real ka sessiooni infot, et saaks teha isikustatud statistikat
            this.kirjutaSessioonFaili();
            System.out.println(h1.toString());
            System.out.println("---------------------------------------------------");
            System.out.println();
            System.out.println("Uus harjutuskord");
            harjutama = kasAlustame();
            //scan.close();
        }
        scan.close();
    }


    //kasutajalt nime küsimine

    public String kysiNimi() {
        // Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta oma nimi: ");

        String s = scan.nextLine();
        if (s.equals("x")) {
            System.exit(0);
        }
        String nimi = s;


        return nimi;
    }

    //kasutajalt limiidi tüübi küsimine

    public boolean kasAjaPeale() {
        // Scanner scan = new Scanner(System.in);
        System.out.println("Kas soovid piirata harjutamise aja (1) või ülesannete arvu (2)? Sisesta 1 või 2: ");
        Integer limiidiTyyp;
        boolean test = true;
        boolean tagasta = false;
        String s;
        while (test) {
            try {
                s = scan.nextLine();
                if (s.equals("x")) {
                    System.exit(0);
                }
                limiidiTyyp = Integer.parseInt(s);
                if (limiidiTyyp == 1) {
                    tagasta = true;
                    test = false;
                } else if (limiidiTyyp == 2) {
                    tagasta = false;
                    test = false;

                } else {
                    System.out.println("Sisestatud väärtus ei olnud 1 või 2. Palun sisesta uuesti.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Sisestatud väärtus ei olnud 1 või 2. Palun sisesta uuesti.");
            }
        }
        return tagasta;
    }
//kasutajalt limiidi väärtuse küsimine

    public int kysiLimiidiVaartus(boolean kasAjaPeale) {
        //Scanner scan = new Scanner(System.in);
        Integer limiit;
        String s;
        while (true) {
            if (kasAjaPeale) {
                System.out.println("Sisesta harjutamise aeg minutites (täisarv): ");
            } else {
                System.out.println("Sisesta harjutuskorra ülesannete arv (täisarv): ");
            }
            //int limiit = scan.nextInt();
            try {
                s = scan.nextLine();
                if (s.equals("x")) {
                    System.exit(0);
                }
                limiit = Integer.parseInt(s);
                break; // katkestab while loobi
                //System.out.println("Oli täisarv");
            } catch (NumberFormatException e) {
                System.out.println("Ei olnud täisarv!");
            }

        }
        return limiit;
    }


    //tehetevaliku küsimine
    public ArrayList<String> kysiTeheteValik() { // eliko: kirjutasin ümber: tehteMärkidesse lisatakse nüüd ainult
        // kirjed, mis vastavad tingimustele.
        //Scanner scan = new Scanner(System.in);
        StringBuilder sisestatudTehted = new StringBuilder();
        ArrayList<String> tehteMargid = new ArrayList<>();
        List<Character> lubatudTehted = Arrays.asList('+', '-', '*', '/');
        while (tehteMargid.isEmpty()) {
            System.out.println("Sisesta tehted, mida soovid harjutada (lubatud märgid: +-*/): ");
            String tehted = scan.nextLine();
            if (tehted.equals("x")) {
                System.exit(0);
            }

            //tükelda sisestatud tähemärkide jada üksikuteks märkideks

            for (int i = 0; i < tehted.length(); i++) { // kui sisestatud märk on  lubatud tehetes, aga pole veel lisatud tehteMärkidesse:
                if (lubatudTehted.contains(tehted.charAt(i)) && (!tehteMargid.contains(String.valueOf(tehted.charAt(i))))) {
                    tehteMargid.add(String.valueOf(tehted.charAt(i))); // lisa tehe tehteMärkidesse
                    if (!sisestatudTehted.toString().contains(String.valueOf(tehted.charAt(i)))) {
                        sisestatudTehted.append(tehted.charAt(i)); // Kuvamiseks, mis tehted lähevad arvesse
                    }
                }
            }
            if (tehteMargid.isEmpty()) {
                System.out.println("Te ei sisestanud ühtegi lubatud tehet!");
            }
        }

        System.out.println("Ülesanded genereeritakse tehetega :" + sisestatudTehted);
        return tehteMargid;
    }

    // raskusastme küsimine

    int kysiRaskusaste() {
        //  Scanner scan = new Scanner(System.in);
        Integer raskusaste;
        String s;
        while (true) {
            System.out.println("Sisesta raskusaste (täisarv >=10): ");
            try {
                s = scan.nextLine();
                if (s.equals("x")) {
                    System.exit(0);
                }

                if (Integer.parseInt(s) >= 10) {
                    raskusaste = Integer.parseInt(s);
                    //System.out.println("Oli täisarv");
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Ei olnud täisarv!");
            }
        }
        return raskusaste;
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
        int index;
        for (int i = 0; i < harjutuskordList.size(); i++) {
            index = i + 1;

            s += "\n" + getSessiooniID() + ";" + " Harjutuskord = " + index + " ; Harjutuskorra algus = " + harjustuskorraKellaaeg.get(i) + " ; " + harjutuskordList.get(i);
        }
        return s;
    }


    public boolean kasAlustame() { //
        // Scanner scan = new Scanner(System.in);
        boolean tagasta = false; // kasAlustame? vastus
        boolean test = true; // testime, kas kasutaja on õigesti vastanud, kui ei ole, küsime sisestust uuesti
        while (test) {
            String vastus;
            System.out.println("Kas alustame jah (1) või ei (2)? Sisesta 1 või 2");
            vastus = scan.nextLine();
            try {
                if (vastus.equals("x")) {
                    System.exit(0);
                }
                if (Integer.parseInt(vastus) == 1) {
                    tagasta = true; // saime vastuse
                    test = false; // lõpetame küsimise
                } else if (Integer.parseInt(vastus) == 2) {
                    tagasta = false; // saime vastuse
                    test = false;// lõpetame küsimise
                } else {
                    System.out.println("Sisesta uuesti!"); // saime int vastuse, aga see pole 1 ega 2, küsime uuesti
                }

            } catch (NumberFormatException e) { // saime vastuseks midagi, mis pole int;
                System.out.println("Pole arv!");
            }

        }
        return tagasta;
    }

    public int kysiVastus() {
        //Scanner scan = new Scanner(System.in);
        Integer vastus;
        while (true) { // küsime kuni saame sobival kujul vastuse;
            System.out.println("Sisesta vastus (täisarv): ");
            String s;
            try {
                s = scan.nextLine();
                if (s.equals("x")) {
                    System.exit(0);
                }
                vastus = Integer.parseInt(s); // kas vastus on int ?
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
        return annaHarjutused();
        //return "Sessioon: " + getSessiooniID() + " " + annaHarjutused();
    }

    //kirjuta sessiooni andmed faili koos kasutaja andmete ja ajaga
    public void kirjutaSessioonFaili () throws FileNotFoundException, UnsupportedEncodingException {

        //luuakse faili isend
        String failinimi = "harjutuskorrad.txt";
        java.io.File fail = new java.io.File(failinimi);

        //kontrollitakse, kas fail on failisüsteemis olemas ja kui ei ole, siis luuakse. TODO: Vaja testida, kas ikka jääb salvestamata, kui harjutuskordade faili ei ole. Pigem luuakse uus fail
        if (!fail.exists()) {
            System.out.println("Faili " + failinimi + " ei ole programmi failiga samas kaustas olemas ja seetõttu ei saa harjutuskorra tulemusi salvestada.");
            java.io.PrintWriter pw = new java.io.PrintWriter(fail, "UTF-8");
            pw.close();
        }

        //kirjutatakse andmed faili
        try
        {
            FileWriter fw = new FileWriter(failinimi,true); // true tähendab, et andmeid lisatakse faili juurde, mitte ei kirjutata olemasolevaid üle
            fw.write(this.toString() + "\n");//lisab harjutuskorra kohta andmed stringina faili
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());// kui tekib, viga siis näidatakse kasutajale veateadet
        }

    }
}
