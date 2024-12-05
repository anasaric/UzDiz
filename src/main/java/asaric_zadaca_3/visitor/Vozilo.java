package asaric_zadaca_3.visitor;


public class Vozilo {
	private String registracija;
	private String status;
	private float ukupnoKilometara;
	private int brojHitnihPaketa;
	private int brojObicnihPaketa;
	private int brojIsporucenihPaketa;
	private float trenutniPostotakZauzetogProstora;
	private float trenutniPostotakZauzeteTezine;
	private int brojVoznji;

	public Vozilo(String registracija, String status, float ukupnoKilometara, int brojHitnihPaketa,
			int brojObicnihPaketa, int brojIsporucenihPaketa, float trenutniPostotakZauzetogProstora,
			float trenutniPostotakZauzeteTezine, int brojVoznji) {
		this.registracija = registracija;
		this.status = status;
		this.ukupnoKilometara = ukupnoKilometara;
		this.brojHitnihPaketa = brojHitnihPaketa;
		this.brojObicnihPaketa = brojObicnihPaketa;
		this.brojIsporucenihPaketa = brojIsporucenihPaketa;
		this.trenutniPostotakZauzetogProstora = trenutniPostotakZauzetogProstora;
		this.trenutniPostotakZauzeteTezine = trenutniPostotakZauzeteTezine;
		this.brojVoznji = brojVoznji;
	}

	public String getRegistracija() {
		return registracija;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public float getTrenutniPostotakZauzetogProstora() {
		return trenutniPostotakZauzetogProstora;
	}

	public void setTrenutniPostotakZauzetogProstora(float trenutniPostotakZauzetogProstora) {
		this.trenutniPostotakZauzetogProstora = trenutniPostotakZauzetogProstora;
	}

	public float getTrenutniPostotakZauzeteTezine() {
		return trenutniPostotakZauzeteTezine;
	}

	public void setTrenutniPostotakZauzeteTezine(float trenutniPostotakZauzeteTezine) {
		this.trenutniPostotakZauzeteTezine = trenutniPostotakZauzeteTezine;
	}

	public int getBrojVoznji() {
		return brojVoznji;
	}

	public void setBrojVoznji(int brojVoznji) {
		this.brojVoznji = brojVoznji;
	}
	
	public void accept(VoznjeVisitor visitor) {
		visitor.azurirajVozilo(this);
    }

}
