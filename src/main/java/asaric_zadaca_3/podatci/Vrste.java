package asaric_zadaca_3.podatci;

public class Vrste {
    private String oznaka;
    private String opis;
    private float visina;
    private float sirina;
    private float duzina;
    private float maksimalnaTezina;
    private float cijena;
    private float cijenaHitno;
    private float cijenaP;
    private float cijenaT;

    public Vrste(String oznaka, String opis, float visina, float sirina, float duzina, float maksimalnaTezina,
                 float cijena, float cijenaHitno, float cijenaP, float cijenaT) {
        this.oznaka = oznaka;
        this.opis = opis;
        this.visina = visina;
        this.sirina = sirina;
        this.duzina = duzina;
        this.maksimalnaTezina = maksimalnaTezina;
        this.cijena = cijena;
        this.cijenaHitno = cijenaHitno;
        this.cijenaP = cijenaP;
        this.cijenaT = cijenaT;
    }

    public String getOznaka() {
        return oznaka;
    }

    public String getOpis() {
        return opis;
    }

    public float getVisina() {
        return visina;
    }

    public float getSirina() {
        return sirina;
    }

    public float getDuzina() {
        return duzina;
    }

    public float getMaksimalnaTezina() {
        return maksimalnaTezina;
    }

    public float getCijena() {
        return cijena;
    }

    public float getCijenaHitno() {
        return cijenaHitno;
    }

    public float getCijenaP() {
        return cijenaP;
    }

    public float getCijenaT() {
        return cijenaT;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setVisina(float visina) {
        this.visina = visina;
    }

    public void setSirina(float sirina) {
        this.sirina = sirina;
    }

    public void setDuzina(float duzina) {
        this.duzina = duzina;
    }

    public void setMaksimalnaTezina(float maksimalnaTezina) {
        this.maksimalnaTezina = maksimalnaTezina;
    }

    public void setCijena(float cijena) {
        this.cijena = cijena;
    }

    public void setCijenaHitno(float cijenaHitno) {
        this.cijenaHitno = cijenaHitno;
    }

    public void setCijenaP(float cijenaP) {
        this.cijenaP = cijenaP;
    }

    public void setCijenaT(float cijenaT) {
        this.cijenaT = cijenaT;
    }
}
