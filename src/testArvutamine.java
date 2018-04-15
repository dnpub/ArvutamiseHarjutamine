/*
Tegemata asjad:

1) Harjutuskorra tulemuste faili kirjutamine
2) Liiga lihtsate tehete ja väikeste tegurite vältimine
3) dokumentatsioonifail.txt
4) koodi grupeerimine - sh õiged meetodid õigetesse klassidesse tõsta
5) sessiooni jooksul mitme harjututskorra lubamine
Testida:
 *Küsimise meetoditele: tsüklid, kui sisestatud väärtus ei vasta reeglitele, siis teavitada ja küsida uuesti
 *System.exit(0) testida, tehtud:  Programmist väljumise meetod, mis töötaks nii harjutuskorra lähteandmete etapis kui ka siis, kui ülesannete lahendamine juba käib.
Mingi Listener klaviatuuri kombinatsioonide jaoks?
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.*;

//testklass
public class testArvutamine {


    //peameetod
    public static void main(String[] args) throws Exception {
        System.out.println("Programm Arvutamine");
        System.out.println("Dokumentatsiooni lugemiseks vajuta (1):");
        System.out.println();
        System.out.println("---------------------------------------------------");
        Scanner scan = new Scanner(System.in);

        try{

        if(Integer.parseInt(scan.nextLine()) == 1){
            // loe sisse dokumentatsioonifail;
           File file = new File("harjutuskorrad.txt");
          BufferedReader reader = new BufferedReader(new FileReader(file));
           String line;
           while((line =reader.readLine())!=null){
               System.out.println(line);
           }
            System.out.println("\n---------------------------------------------------");

        }}
        catch (Exception e){}
   // scan.close(); ei saa panna siia close - Sessiooni jms alammeetodite scannerid lõpetavad töö
        System.out.println("Väljumiseks vajuta 'x'.");
        System.out.println("---------------------------------------------------\n");
       Sessioon s1 = new Sessioon();
       System.out.println(s1.toString());

    }

    //meetodid

    //valitud tehete hulgast juhuslikult ühe tehte valimine
    static String getTehe(List<String> tehted) {
        int a = 0;
        a = (int) Math.round(Math.random() * (tehted.size()-1) + 0); // tehted.size()-1
        String valitudTehe = tehted.get(a);
        return valitudTehe;
    }


    //kasutajalt nime küsimine

    static public String kysiNimi() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta oma nimi: ");

        String s=scan.nextLine();
        if(s.equals("x")){System.exit(0);}
        String nimi = s;
        //scan.close(); ei saa hetkel panna close - muidu ülejäänud kasutatud scannerid eitööta

        return nimi;
    }

    //kasutajalt limiidi tüübi küsimine

    static public boolean kasAjaPeale() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Kas soovid piirata harjutamise aja (1) või ülesannete arvu (2)? Sisesta 1 või 2: ");
        Integer limiidiTyyp= null;
        boolean test = true;
        boolean tagasta = false;
        String s;
        while (test) {
            try{
                s=scan.nextLine();
                if(s.equals("x")){System.exit(0);}
            limiidiTyyp = Integer.parseInt(s);
            //scan.close();
            if (limiidiTyyp == 1) {
                //return true;

                tagasta = true;
                test=false;
            } else if (limiidiTyyp == 2) {
                tagasta = false;
                test=false;
                //return false;
            } else {
                // System.out.println("Sisestatud väärtus ei olnud 1 või 2. Eeldame, et ei soovita lahendada aja peale.");
                //return false;
                System.out.println("Sisestatud väärtus ei olnud 1 või 2. Palun sisesta uuesti.");
            }}
            catch (NumberFormatException e){
                System.out.println("Sisestatud väärtus ei olnud 1 või 2. Palun sisesta uuesti.");
            }
        }
        return tagasta;
    }

    //kasutajalt limiidi väärtuse küsimine

    static public int kysiLimiidiVaartus(boolean kasAjaPeale) {
        Scanner scan = new Scanner(System.in);
        Integer limiit = null;
        String s;
        while (true) {
            if (kasAjaPeale) {
                System.out.println("Sisesta harjutamise aeg minutites (täisarv): ");
            } else {
                System.out.println("Sisesta harjutuskorra ülesannete arv (täisarv): ");
            }
            //int limiit = scan.nextInt();
            try {
                s=scan.nextLine();
                if(s.equals("x")){System.exit(0);}
                limiit = Integer.parseInt(s);
                break; // katkestab while loobi
                //System.out.println("Oli täisarv");
            } catch (NumberFormatException e) {
                System.out.println("Ei olnud täisarv!");
            }
            //scan.close();
        }
        return limiit;
    }

    //tehetevaliku küsimine
    static public ArrayList<String> kysiTeheteValik() { // eliko: kirjutasin ümber: tehteMärkidesse lisatakse nüüd ainult
        // kirjed, mis vastavad tingimustele.
        Scanner scan = new Scanner(System.in);
        StringBuilder sisestatudTehted = new StringBuilder();
        ArrayList<String> tehteMargid = new ArrayList<String>();
        List<Character> lubatudTehted = Arrays.asList('+', '-', '*', '/');
        while (tehteMargid.isEmpty()) {
            System.out.println("Sisesta tehted, mida soovid harjutada (lubatud märgid: +-*/): ");
            String tehted=scan.nextLine();
            if(tehted.equals("x")){System.exit(0);}
            //scan.close();
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
/*  Oli probleeme seoses sellega, et teheteMärkidest eemaldati vahepeal kirjeid. Tegin ümber vt ülalpool.
        //eemalda märgid, mis ei kuulu tehtemärkide hulka.
        for (String taheMark: tehteMargid)
              {
                  if (taheMark.equals("+")){
                      continue; //kui on õige märk, siis minnakse tsükli järgmist käituskorda täitma ehk järgmist märki kontrollima
                    } else if (taheMark.equals("-")){
                      continue; //kui on õige märk, siis minnakse tsükli järgmist käituskorda täitma ehk järgmist märki kontrollima
                             } else if (taheMark.equals("/")){
                                continue; //kui on õige märk, siis minnakse tsükli järgmist käituskorda täitma ehk järgmist märki kontrollima
                                } else if (taheMark.equals("*")){
                                            continue; //kui on õige märk, siis minnakse tsükli järgmist käituskorda täitma ehk järgmist märki kontrollima
                                        } else {
                                                System.out.println(taheMark + " ei ole tehtemärk ja seda ei arvestata.");
                                                tehteMargid.remove(taheMark);
                                                }

                }
*/
        return tehteMargid;
    }

    // raskusastme küsimine

    static int kysiRaskusaste(){
        Scanner scan = new Scanner(System.in);
        Integer raskusaste = null;
        String s;
        while (true){
            System.out.println("Sisesta raskusaste (täisarv >=10): ");
        try {
            s=scan.nextLine();
            if(s.equals("x")){System.exit(0);}
            raskusaste = Integer.parseInt(s);
            if(raskusaste>=10){
            //System.out.println("Oli täisarv");
            break;}

        } catch (NumberFormatException e) {
            System.out.println("Ei olnud täisarv!");
        } }
        return raskusaste;
    }

    //ajalimiidiga harjutuskorra loomine

    //ülesannete limiidiga harjutuskorra loomine

    //ülesande tekitamine

    //ülesande esitamine kasutajale ja kasutaja vastuse registreerimine
    // esimese ülesande esitamisega koos tuleb ka stopper käima panna
    //ajalimiidiga harjutuskorra puhul tuleks näidata ka allesjäänud aega, teatud regulaarsusega
    // ülesannete limiidi korral viimase ülesande lahendamisega koos tuleb ka stopper seisma panna või siis ajalimiidi korral tule


}
