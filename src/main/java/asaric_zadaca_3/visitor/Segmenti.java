package asaric_zadaca_3.visitor;


public class Segmenti {
	private int brojVoznje;
	private String registracija;
	private String vrijemePocetka;
	private String vrijemePovratka;
	private long trajanje;
	private float ukupnoKilometara;
	private String oznakaPaketa;
	private boolean dostavljen;

	public Segmenti(String registracija, int brojVoznje, String vrijemePocetka, String vrijemePovratka,
			long trajanje, float ukupnoKilometara, String oznakaPaketa, boolean dostavljen) {
		this.brojVoznje = brojVoznje;
		this.registracija = registracija;
		this.vrijemePocetka = vrijemePocetka;
		this.vrijemePovratka = vrijemePovratka;
		this.trajanje = trajanje;
		this.ukupnoKilometara = ukupnoKilometara;
		this.oznakaPaketa = oznakaPaketa;
		this.dostavljen = dostavljen;
	}

	public int getBrojVoznje() {
		return brojVoznje;
	}

	public void setBrojVoznje(int brojVoznje) {
		this.brojVoznje = brojVoznje;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getVrijemePocetka() {
		return vrijemePocetka;
	}

	public void setVrijemePocetka(String vrijemePocetka) {
		this.vrijemePocetka = vrijemePocetka;
	}

	public String getVrijemePovratka() {
		return vrijemePovratka;
	}

	public void setVrijemePovratka(String vrijemePovratka) {
		this.vrijemePovratka = vrijemePovratka;
	}

	public long getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(long trajanje) {
		this.trajanje = trajanje;
	}

	public float getUkupnoKilometara() {
		return ukupnoKilometara;
	}

	public void setUkupnoKilometara(float ukupnoKilometara) {
		this.ukupnoKilometara = ukupnoKilometara;
	}

	public String getOznakaPaketa() {
		return oznakaPaketa;
	}

	public void setOznakaPaketa(String oznakaPaketa) {
		this.oznakaPaketa = oznakaPaketa;
	}
	
	public boolean isDostavljen() {
		return dostavljen;
	}

	public void setDostavljen(boolean dostavljen) {
		this.dostavljen = dostavljen;
	}
	
	public void accept(VoznjeVisitor visitor) {
		visitor.azurirajSegment(this);
    }
}
