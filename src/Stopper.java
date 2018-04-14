public class Stopper {

    //isendiväljad
    private Long algusAeg = System.currentTimeMillis();
    private Long loppAeg = null;
    private boolean aegJookseb;

    //konstruktorid
    public Stopper () {
        //this.algusAeg = System.currentTimeMillis();
        this.aegJookseb = true;
    }

    //meetodid
    public long annaAlgusAeg(){
        return algusAeg;
    }


    //pane stopper seisma
    public void aegSeisma() {
        this.loppAeg = System.currentTimeMillis();
        this.aegJookseb = false;
    }


    //leia kulunud aeg millisekundites
    public long annaKulunudAeg() {
        long kulunudAeg;
        if (aegJookseb) {
            kulunudAeg = (System.currentTimeMillis() - algusAeg);
        } else {
            aegSeisma();
            kulunudAeg = (loppAeg - algusAeg);
        }
        return kulunudAeg;
    }


    //leia kulunud aeg sekundites
    public long annaKulunudAegSekundites() {
        return annaKulunudAeg()/1000;
    }

    //leia harjutuskorras allesjäänud aeg sekundites, ajalimiit tuleb sisse minutites
    public long annaAllesjaanudAegSekundites (int ajalimiit){
        long allesJaanudAeg;
        if (aegJookseb) {
            allesJaanudAeg = (ajalimiit * 60) - annaKulunudAegSekundites();
                    //alternatiivne arvutusvariant: ((algusAeg + (ajalimiit * 60 * 1000)) - System.currentTimeMillis());
        } else {
            allesJaanudAeg = 0;
        }
        return allesJaanudAeg;
    }

    //kas aja limiit on läbi või mitte
    public boolean kasAegLabi (int ajalimiit){
        if (annaAllesjaanudAegSekundites(ajalimiit) <= 0){
            return true;
        } else{
            return false;
        }
    }
}
