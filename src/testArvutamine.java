/*
Tegemata asjad:

1) Harjutuskorra tulemuste faili kirjutamine
2) Liiga lihtsate tehete ja väikeste tegurite vältimine
3) koodi grupeerimine - sh õiged meetodid õigetesse klassidesse tõsta
4) sessiooni jooksul mitme harjututskorra lubamine
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

        System.out.println("Programm Arvutamise harjutamine");
        System.out.println("Dokumentatsiooni lugemiseks vajuta (1):");
        System.out.println();
        System.out.println("---------------------------------------------------");
        Scanner scan = new Scanner(System.in);

        try{

        if(Integer.parseInt(scan.nextLine()) == 1){
            // loe sisse dokumentatsioonifail;
           File file = new File("dokumentatsioon.txt");
          BufferedReader reader = new BufferedReader(new FileReader(file));
           String line;
           while((line =reader.readLine())!=null){
               System.out.println(line);
           }
            System.out.println("\n---------------------------------------------------");

        }}
        catch (Exception e){}
   // scan.close(); ei saa panna siia close - Sessiooni jms alammeetodite scannerid lõpetavad töö
        System.out.println("Programmi katkestamiseks sisesta 'x'.");
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







    //ajalimiidiga harjutuskorra loomine

    //ülesannete limiidiga harjutuskorra loomine

    //ülesande tekitamine

    //ülesande esitamine kasutajale ja kasutaja vastuse registreerimine
    // esimese ülesande esitamisega koos tuleb ka stopper käima panna
    //ajalimiidiga harjutuskorra puhul tuleks näidata ka allesjäänud aega, teatud regulaarsusega
    // ülesannete limiidi korral viimase ülesande lahendamisega koos tuleb ka stopper seisma panna või siis ajalimiidi korral tule


}
