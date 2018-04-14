import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Harjutuskord {

    //isendiväljad
    private boolean kasAjaPeale; //true = ühe harjutuskorra aeg on piiratud, false = ühe harjutuskorra ülesannete hulk on piiratud
    private int ajalimiit; //ajalimiit peab olema sisestatud täisarv ja minutites
    private int ylesanneteLimiit; //ylesannete limiit peab olema täisarv ja väljendab ühe harjutuskorra jaoks kasutaja poolt soovitud ülesannete hulka
   // private String teheteValik; //tehete valimiseks peab sisestama soovitud tehete märgid (+-*/) üksteisest kõrvale
    private int lahendatudYlesandeid; //lahendamise lõpetamise hetkeks tekkinud ülesannete hulk, millele kasutaja on vastuse sisestanud
    private int lahendamiseAeg; //lahendamisele kulunud aeg sekundites
    private int oigeidVastuseid; //õigete vastuste koguhulk ühes harjutuskorras
    private int raskusAste;//täisarv, mille piires võivad olla ülesannete vastused
    private List<String> teheteValik;
    //konstruktorid

    //aja peale toimuva harjutuskorra loomine




    public Harjutuskord(boolean kasAjaPeale, int ajalimiit, List<String> teheteValik, int raskusAste) {
        this.kasAjaPeale = kasAjaPeale;
        this.ajalimiit = ajalimiit;//kasutajalt küsitakse lahendamise aeg minutites, kuid arvutused kulunud aja kohta toimuvad sekundites
        this.teheteValik = teheteValik;
        this.raskusAste = raskusAste;
    }

    //limiteeritud ülesannete hulgaga harjutuskorra loomine
    public Harjutuskord(boolean kasAjaPeale, List<String> teheteValik, int ylesanneteLimiit, int raskusAste) {
        this.kasAjaPeale = kasAjaPeale;
        this.ylesanneteLimiit = ylesanneteLimiit;
        this.teheteValik = teheteValik;
        this.raskusAste = raskusAste;
    }

    //meetodid

    public int getLahendatudYlesandeid() {
        return lahendatudYlesandeid;
    }

    public void setLahendatudYlesandeid(int lahendatudYlesandeid) {
        this.lahendatudYlesandeid = lahendatudYlesandeid;
    }

    public int getLahendamiseAeg() {
        return lahendamiseAeg;
    }

    public void setLahendamiseAeg(int lahendamiseAeg) {
        this.lahendamiseAeg = lahendamiseAeg;
    }

    public int getOigeidVastuseid() {
        return oigeidVastuseid;
    }

    public void setOigeidVastuseid(int oigeidVastuseid) {
        this.oigeidVastuseid = oigeidVastuseid;
    }

    public int getAjalimiit() {
        return ajalimiit;
    }

    public int getYlesanneteLimiit() {
        return ylesanneteLimiit;
    }

    public int getRaskusAste() {
        return raskusAste;
    }

    public boolean kasAjaPeale() {
        return kasAjaPeale;
    }

    public List<String> getTeheteValik() {
        return teheteValik;
    }

    public Ülesanne genereeriÜlesanne( List<String> tehted, int raskusaste){
        List<String> võimalikudTehted = tehted;
        Ülesanne ül = null;
        String tehe = testArvutamine.getTehe(võimalikudTehted);
        switch (tehe) {
            case "+":
                ül = new Liitmine(raskusaste);
                break;
            case "-":
                ül =new Lahutamine(raskusaste);
                break;
            case "*":
                ül=new Korrutamine(raskusaste);
                break;
            case "/":
                ül=new Jagamine(raskusaste);
                break;
        }
        return ül;
    }

    //trüki harjutuskorra tulemused ekraanile

    @Override
    public String toString() {
        if (kasAjaPeale) { return
                " Ajalimiit = " + ajalimiit +" min" +
                ", tehete valik = '" + teheteValik + '\'' +
                ", lahendatud ülesandeid = " + lahendatudYlesandeid +
                ", lahendamise aeg sekundites = " + lahendamiseAeg +
                ", õigeid vastuseid = " + oigeidVastuseid +
                ", raskusaste = " + raskusAste ;}
                else {
                    return
                    "Ülesannete limiit=" + ylesanneteLimiit +
                    ", tehete valik = '" + teheteValik + '\'' +
                    ", lahendatud ülesandeid = " + lahendatudYlesandeid +
                    ", lahendamise aeg sekundites = " + lahendamiseAeg +
                    ", õigeid vastuseid = " + oigeidVastuseid +
                            ", raskusaste = " + raskusAste ; }

        }

    //kirjuta harjutuskorra andmed faili koos kasutaja andmete ja ajaga
    public void kirjutaHarjutuskordFaili () throws FileNotFoundException, UnsupportedEncodingException {

        //luuakse faili isend
        String failinimi = "harjutuskorrad.txt";
        java.io.File fail = new java.io.File(failinimi);

        //kontrollitakse, kas fail on failisüsteemis olemas ja kui ei ole, siis luuakse
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
