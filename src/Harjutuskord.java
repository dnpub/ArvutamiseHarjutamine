import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Harjutuskord {

    //isendiväljad
    private boolean kasAjaPeale; //true = ühe harjutuskorra aeg on piiratud, false = ühe harjutuskorra ülesannete hulk on piiratud

    private int limiit; // aja või ülesannete arvu limiit: ajalimiit peab olema sisestatud täisarv ja minutites;
    //ylesannete limiit peab olema täisarv ja väljendab ühe harjutuskorra jaoks kasutaja poolt soovitud ülesannete hulka
    private int lahendatudYlesandeid; //lahendamise lõpetamise hetkeks tekkinud ülesannete hulk, millele kasutaja on vastuse sisestanud
    private int lahendamiseAeg; //lahendamisele kulunud aeg sekundites
    private int oigeidVastuseid; //õigete vastuste koguhulk ühes harjutuskorras
    private int raskusAste;//täisarv, mille piires võivad olla ülesannete vastused
    private List<String> teheteValik; // tehted,millega ülesandeid genereeritakse
    //konstruktorid

    //harjutuskorra loomine

    public Harjutuskord(boolean kasAjaPeale, int limiit, List<String> teheteValik, int raskusAste) {
        this.kasAjaPeale = kasAjaPeale;
        this.limiit = limiit;//kasutajalt küsitakse lahendamise aeg minutites, kuid arvutused kulunud aja kohta toimuvad sekundites
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
        //return ajalimiit;
        return limiit;
    }

    public int getYlesanneteLimiit() {
        // return ylesanneteLimiit;
        return limiit;
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

    public Ülesanne genereeriÜlesanne(List<String> tehted, int raskusaste) {
        List<String> võimalikudTehted = tehted;
        Ülesanne ül = null;
        String tehe = getTehe(võimalikudTehted);
        switch (tehe) {
            case "+":
                ül = new Liitmine(raskusaste);
                break;
            case "-":
                ül = new Lahutamine(raskusaste);
                break;
            case "*":
                ül = new Korrutamine(raskusaste);
                break;
            case "/":
                ül = new Jagamine(raskusaste);
                break;
        }
        return ül;
    }

    //valitud tehete hulgast juhuslikult ühe tehte valimine
    public String getTehe(List<String> tehted) {
        int a = 0;
        a = (int) Math.round(Math.random() * (tehted.size() - 1) + 0); // tehted.size()-1
        String valitudTehe = tehted.get(a);
        return valitudTehe;
    }

    //trüki harjutuskorra tulemused ekraanile

    @Override
    public String toString() {
        if (kasAjaPeale) {
            return
                    " Ajalimiit(min) = " + limiit +
                            " ; tehete valik = '" + teheteValik + '\'' +
                            " ; lahendatud ülesandeid = " + lahendatudYlesandeid +
                            " ; lahendamise aeg sekundites = " + lahendamiseAeg +
                            " ; õigeid vastuseid = " + oigeidVastuseid +
                            " ; raskusaste = " + raskusAste;
        } else {
            return
                    " Ülesannete limiit = " + limiit +
                            " ; tehete valik = '" + teheteValik + '\'' +
                            " ; lahendatud ülesandeid = " + lahendatudYlesandeid +
                            " ; lahendamise aeg sekundites = " + lahendamiseAeg +
                            " ; õigeid vastuseid = " + oigeidVastuseid +
                            " ; raskusaste = " + raskusAste;
        }

    }

}
