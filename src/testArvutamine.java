/*
Tegemata asjad:
1) Küsimise meetoditele: tsüklid, kui sisestatud väärtus ei vasta reeglitele, siis teavitada ja küsida uuesti
2) Programmist väljumise meetod, mis töötaks nii harjutuskorra lähteandmete etapis kui ka siis, kui ülesannete lahendamine juba käib
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public String kysiNimi() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta oma nimi: ");
        String nimi = scan.next();
        scan.close();
        return nimi;
    }

    //kasutajalt limiidi tüübi küsimine

    public boolean kasAjaPeale (){
        Scanner scan = new Scanner(System.in);
        System.out.println("Kas soovid piirata harjutamise aja (1) või ülesannete arvu (2)? Sisesta 1 või 2: ");
        int limiidiTyyp = scan.nextInt();
        scan.close();
        if (limiidiTyyp == 1){
            return true;
        } else if (limiidiTyyp == 2) {
            return false;
        } else {
            System.out.println("Sisestatud väärtus ei olnud 1 või 2. Eeldame, et ei soovita lahendada aja peale.");
            return false;
        }
    }

    //kasutajalt limiidi väärtuse küsimine

    public int kysiLimiidiVaartus (boolean kasAjaPeale) {
        Scanner scan = new Scanner(System.in);
        if (kasAjaPeale) {
            System.out.println("Sisesta harjutamise aeg minutites (täisarv): ");
        } else {
            System.out.println("Sisesta harjutuskorra ülesannete arv (täisarv): ");
        }
        int limiit = scan.nextInt();
        scan.close();
        return limiit;
    }

    //tehetevaliku küsimine
    public ArrayList<String> kysiTeheteValik() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta tehted, mida soovid harjutada (lubatud märgid: +-*/): ");
        String tehted = scan.next();
        scan.close();

        //tükelda sisestatud tähemärkide jada üksikuteks märkideks
        ArrayList<String> tehteMargid = new ArrayList<String>();
        for (int i = 0; i < tehted.length()-1; i++) {
            tehteMargid.add(String.valueOf(tehted.charAt(i)));
        }

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

        return tehteMargid;
    }


    //ajalimiidiga harjutuskorra loomine

    //ülesannete limiidiga harjutuskorra loomine

    //ülesande tekitamine

    //ülesande esitamine kasutajale ja kasutaja vastuse registreerimine
    // esimese ülesande esitamisega koos tuleb ka stopper käima panna
    //ajalimiidiga harjutuskorra puhul tuleks näidata ka allesjäänud aega, teatud regulaarsusega
    // ülesannete limiidi korral viimase ülesande lahendamisega koos tuleb ka stopper seisma panna või siis ajalimiidi korral tule


}
