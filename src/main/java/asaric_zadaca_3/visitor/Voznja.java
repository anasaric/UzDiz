package asaric_zadaca_3.visitor;

import java.time.LocalTime;

public class Voznja {
	private String registracija;
	private int brojVoznje;
	private LocalTime vrijemePocetka;
	private LocalTime vrijemePovratka;
	private long trajanje;
	private float ukupnoKilometara;
	private int brojHitnihPaketa;
	private int brojObicnihPaketa;
	private int brojIsporucenihPaketa;
	private float pocetniPostotakZauzetogProstora;
	private float pocetniPostotakZauzeteTezine;


	public Voznja(String registracija, int brojVoznje, LocalTime vrijemePocetka, LocalTime vrijemePovratka, long trajanje,
			float ukupnoKilometara, int brojHitnihPaketa, int brojObicnihPaketa, int brojIsporucenihPaketa,
			float pocetniPostotakZauzetogProstora, float pocetniPostotakZauzeteTezine) {
		this.registracija = registracija;
		this.brojVoznje = brojVoznje;
		this.vrijemePocetka = vrijemePocetka;
		this.vrijemePovratka = vrijemePovratka;
		this.trajanje = trajanje;
		this.ukupnoKilometara = ukupnoKilometara;
		this.brojHitnihPaketa = brojHitnihPaketa;
		this.brojObicnihPaketa = brojObicnihPaketa;
		this.brojIsporucenihPaketa = brojIsporucenihPaketa;
		this.pocetniPostotakZauzetogProstora = pocetniPostotakZauzetogProstora;
		this.pocetniPostotakZauzeteTezine = pocetniPostotakZauzeteTezine;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}
	
	public int getBrojVoznje() {
		return brojVoznje;
	}

	public void setBrojVoznje(int brojDostave) {
		this.brojVoznje = brojDostave;
	}
	
	public LocalTime getVrijemePocetka() {
		return vrijemePocetka;
	}

	public void setVrijemePocetka(LocalTime vrijemePocetka) {
		this.vrijemePocetka = vrijemePocetka;
	}

	public LocalTime getVrijemePovratka() {
		return vrijemePovratka;
	}

	public void setVrijemePovratka(LocalTime vrijemePovratka) {
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

	public int getBrojHitnihPaketa() {
		return brojHitnihPaketa;
	}

	public void setBrojHitnihPaketa(int brojHitnihPaketa) {
		this.brojHitnihPaketa = brojHitnihPaketa;
	}

	public int getBrojObicnihPaketa() {
		return brojObicnihPaketa;
	}

	public void setBrojObicnihPaketa(int brojObicnihPaketa) {
		this.brojObicnihPaketa = brojObicnihPaketa;
	}

	public int getBrojIsporucenihPaketa() {
		return brojIsporucenihPaketa;
	}

	public void setBrojIsporucenihPaketa(int brojIsporucenihPaketa) {
		this.brojIsporucenihPaketa = brojIsporucenihPaketa;
	}

	public float getPocetniPostotakZauzetogProstora() {
		return pocetniPostotakZauzetogProstora;
	}

	public void setPocetniPostotakZauzetogProstora(float pocetniPostotakZauzetogProstora) {
		this.pocetniPostotakZauzetogProstora = pocetniPostotakZauzetogProstora;
	}

	public float getPocetniPostotakZauzeteTezine() {
		return pocetniPostotakZauzeteTezine;
	}

	public void setPocetniPostotakZauzeteTezine(float pocetniPostotakZauzeteTezine) {
		this.pocetniPostotakZauzeteTezine = pocetniPostotakZauzeteTezine;
	}
	
	public void accept(VoznjeVisitor visitor) {
		visitor.azurirajVoznju(this);
    }

}
