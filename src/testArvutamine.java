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
    // public static final Scanner scan = new Scanner(System.in);

    //peameetod
    public static void main(String[] args) throws Exception {

        System.out.println("Programm Arvutamise harjutamine");
        System.out.println();
        System.out.println("Dokumentatsiooni lugemiseks vajuta (1), vahelejätmiseks (2)");
        Scanner scan = new Scanner(System.in);

        try {

            if (Integer.parseInt(scan.next()) == 1) {
                // loe sisse dokumentatsioonifail;
                File file = new File("dokumentatsioon.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            }

        } catch (Exception e) {
        } finally {
            System.out.println("---------------------------------------------------");
            System.out.println("Programmi katkestamiseks sisesta 'x'.");
            System.out.println("---------------------------------------------------\n");
        }

        Sessioon s1 = new Sessioon();
        System.out.println(s1.toString());


        scan.close();

    }

}



