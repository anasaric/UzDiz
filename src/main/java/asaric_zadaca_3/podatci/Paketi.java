package asaric_zadaca_3.podatci;

import java.util.List;

public class Paketi {
    private String oznaka;
    private String vrijemePrijema;
    private String posiljatelj;
    private String primatelj;
    private String vrstaPaketa;
    private float visina;
    private float sirina;
    private float duzina;
    private float tezina;
    private float cijena;
    private String uslugaDostave;
    private float iznosPouzeca;
    private boolean obraden;
    private String dostavljen;

    public Paketi(String oznaka, String vrijemePrijema, String posiljatelj, String primatelj, String vrstaPaketa,
                  float visina, float sirina, float duzina, float tezina, float cijena, String uslugaDostave, float iznosPouzeca,
                  boolean obraden, String dostavljen) {
        this.oznaka = oznaka;
        this.vrijemePrijema = vrijemePrijema;
        this.posiljatelj = posiljatelj;
        this.primatelj = primatelj;
        this.vrstaPaketa = vrstaPaketa;
        this.visina = visina;
        this.sirina = sirina;
        this.duzina = duzina;
        this.tezina = tezina;
        this.cijena = cijena;
        this.uslugaDostave = uslugaDostave;
        this.iznosPouzeca = iznosPouzeca;
        this.obraden = obraden;
        this.dostavljen = dostavljen;
    }


	public Paketi(Paketi paket) {
		this.oznaka = paket.oznaka;
        this.vrijemePrijema = paket.vrijemePrijema;
        this.posiljatelj = paket.posiljatelj;
        this.primatelj = paket.primatelj;
        this.vrstaPaketa = paket.vrstaPaketa;
        this.visina = paket.visina;
        this.sirina = paket.sirina;
        this.duzina = paket.duzina;
        this.tezina = paket.tezina;
        this.cijena = paket.cijena;
        this.uslugaDostave = paket.uslugaDostave;
        this.iznosPouzeca = paket.iznosPouzeca;
        this.obraden = paket.obraden;
        this.dostavljen = paket.dostavljen;
	}


	public static List<Paketi> getPaketi(List<Paketi> paketiList) {
        return paketiList;
    }
    
    public String getOznaka() {
        return oznaka;
    }

    public String getVrijemePrijema() {
        return vrijemePrijema;
    }

    public String getPosiljatelj() {
        return posiljatelj;
    }

    public String getPrimatelj() {
        return primatelj;
    }

    public String getVrstaPaketa() {
        return vrstaPaketa;
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

    public float getTezina() {
        return tezina;
    }
    
    public float getCijena() {
        return cijena;
    }

    public String getUslugaDostave() {
        return uslugaDostave;
    }

    public float getIznosPouzeca() {
        return iznosPouzeca;
    }

    public boolean isObraden() {
        return obraden;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public void setVrijemePrijema(String vrijemePrijema) {
        this.vrijemePrijema = vrijemePrijema;
    }

    public void setPosiljatelj(String posiljatelj) {
        this.posiljatelj = posiljatelj;
    }

    public void setPrimatelj(String primatelj) {
        this.primatelj = primatelj;
    }

    public void setVrstaPaketa(String vrstaPaketa) {
        this.vrstaPaketa = vrstaPaketa;
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

    public void setTezina(float tezina) {
        this.tezina = tezina;
    }
    
    public void setCijena(float cijena) {
        this.cijena = cijena;
    }

    public void setUslugaDostave(String uslugaDostave) {
        this.uslugaDostave = uslugaDostave;
    }

    public void setIznosPouzeca(float iznosPouzeca) {
        this.iznosPouzeca = iznosPouzeca;
    }

    public void setObraden(boolean obraden) {
        this.obraden = obraden;
    }
    
    public void setDostavljen(String dostavljen) {
		this.dostavljen = dostavljen;
	}

	public String getdostavljen() {
		return dostavljen;
	}
}
