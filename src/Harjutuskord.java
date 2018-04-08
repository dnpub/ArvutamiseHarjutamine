public class Harjutuskord {

    //isendiväljad
    private boolean kasAjaPeale; //true = ühe harjutuskorra aeg on piiratud, false = ühe harjutuskorra ülesannete hulk on piiratud
    private int ajalimiit; //ajalimiit peab olema sisestatud täisarv ja minutites
    private int ylesanneteLimiit; //ylesannete limiit peab olema täisarv ja väljendab ühe harjutuskorra jaoks kasutaja poolt soovitud ülesannete hulka
    private String teheteValik; //tehete valimiseks peab sisestama soovitud tehete märgid (+-*/) üksteisest kõrvale
    private int lahendatudYlesandeid; //lahendamise lõpetamise hetkeks tekkinud ülesannete hulk, millele kasutaja on vastuse sisestanud
    private int lahendamiseAeg; //lahendamisele kulunud aeg sekundites
    private int oigeidVastuseid; //õigete vastuste koguhulk ühes harjutuskorras

    //konstruktorid

    //aja peale toimuva harjutuskorra loomine
    public Harjutuskord(boolean kasAjaPeale, int ajalimiit, String teheteValik) {
        this.kasAjaPeale = kasAjaPeale;
        this.ajalimiit = ajalimiit;
        this.teheteValik = teheteValik;
    }

    //limiteeritud ülesannete hulgaga harjutuskorra loomine
    public Harjutuskord(boolean kasAjaPeale, String teheteValik, int ylesanneteLimiit) {
        this.kasAjaPeale = kasAjaPeale;
        this.ylesanneteLimiit = ylesanneteLimiit;
        this.teheteValik = teheteValik;
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

    //trüki harjutuskorra tulemused ekraanile

    @Override
    public String toString() {
        if (kasAjaPeale) { return "Harjutuskord{" +
                ", ajalimiit=" + ajalimiit +
                ", teheteValik='" + teheteValik + '\'' +
                ", lahendatudYlesandeid=" + lahendatudYlesandeid +
                ", lahendamiseAeg=" + lahendamiseAeg +
                ", oigeidVastuseid=" + oigeidVastuseid +
                '}';}
                else {
                    return "Harjutuskord{" +
                    ", ylesanneteLimiit=" + ylesanneteLimiit +
                    ", teheteValik='" + teheteValik + '\'' +
                    ", lahendatudYlesandeid=" + lahendatudYlesandeid +
                    ", lahendamiseAeg=" + lahendamiseAeg +
                    ", oigeidVastuseid=" + oigeidVastuseid +
                    '}';

        }
    }
}
