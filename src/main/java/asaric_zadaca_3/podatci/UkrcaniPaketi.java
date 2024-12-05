package asaric_zadaca_3.podatci;

import java.util.ArrayList;
import java.util.List;

public class UkrcaniPaketi {
	private String oznakaVozila;
	private int brojVoznje;
	private String status;
	private int podrucje;
	private float sumaObujma;
	private float sumaTezina;
	private float sumaCijena;
	private boolean dostava;
	private List<ZaprimljeniPaketi> ukrcaniPaketi = new ArrayList<>(); 

	public UkrcaniPaketi(String oznakaVozila, int brojVoznje, String status, int podrucje, float sumaObujma,
			float sumaTezina, float sumaCijena, boolean dostava, ZaprimljeniPaketi ukrcaniPaketi) {
		this.oznakaVozila = oznakaVozila;
		this.brojVoznje = brojVoznje;
		this.podrucje = podrucje;
		this.sumaObujma = sumaObujma;
		this.sumaTezina = sumaTezina;
		this.sumaCijena = sumaCijena;
		this.ukrcaniPaketi.add(ukrcaniPaketi);
		this.dostava = dostava;
	}

	public String getOznakaVozila() {
		return oznakaVozila;
	}

	public void setOznakaVozila(String oznakaVozila) {
		this.oznakaVozila = oznakaVozila;
	}

	public int getBrojVoznje() {
		return brojVoznje;
	}

	public void setBrojVoznje(int brojVoznje) {
		this.brojVoznje = brojVoznje;
	}
	
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public int getPodrucje() {
		return podrucje;
	}

	public void setPodrucje(int podrucje) {
		this.podrucje = podrucje;
	}

	public float getSumaObujma() {
		return sumaObujma;
	}

	public void setSumaObujma(float sumaObujma) {
		this.sumaObujma = sumaObujma;
	}

	public float getSumaTezina() {
		return sumaTezina;
	}

	public void setSumaTezina(float sumaTezina) {
		this.sumaTezina = sumaTezina;
	}

	public float getSumaCijena() {
		return sumaCijena;
	}

	public void setSumaCijena(float sumaCijena) {
		this.sumaCijena = sumaCijena;
	}
	
	public boolean isDostava() {
		return dostava;
	}

	public void setDostava(boolean dostava) {
		this.dostava = dostava;
	}

	public List<ZaprimljeniPaketi> getUkrcaniPaketi() {
		return ukrcaniPaketi;
	}

	public void setUkrcaniPaketi(List<ZaprimljeniPaketi> ukrcaniPaketi) {
		this.ukrcaniPaketi = ukrcaniPaketi;
	}
	
	public void dodajUkrcaniPaket(ZaprimljeniPaketi ukrcaniPaketi) {
		this.ukrcaniPaketi.add(ukrcaniPaketi);
	}
	
	public void makniUkrcaniPaket(ZaprimljeniPaketi ukrcaniPaketi) {
		this.ukrcaniPaketi.remove(ukrcaniPaketi);
	}

}
