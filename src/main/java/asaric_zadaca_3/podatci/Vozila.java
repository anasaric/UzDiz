package asaric_zadaca_3.podatci;

public class Vozila {
	private String registracija;
	private String opis;
	private float kapacitetTezine;
	private float kapacitetProstora;
	private int redoslijed;
	private int prosjecnaBrzina;
	private String podrucjaURangu;
	private String status;
	private String dostava;

	public Vozila(String registracija, String opis, float kapacitetTezine, float kapacitetProstora, int redoslijed,
			int prosjecnaBrzina, String podrucjaURangu, String status, String dostava) {
		this.registracija = registracija;
		this.opis = opis;
		this.kapacitetTezine = kapacitetTezine;
		this.kapacitetProstora = kapacitetProstora;
		this.redoslijed = redoslijed;
		this.prosjecnaBrzina = prosjecnaBrzina;
		this.podrucjaURangu = podrucjaURangu;
		this.status = status;
		this.dostava = dostava;
	}

	public Vozila(Vozila vozilo) {
		this.registracija = vozilo.registracija;
		this.opis = vozilo.opis;
		this.kapacitetTezine = vozilo.kapacitetTezine;
		this.kapacitetProstora = vozilo.kapacitetProstora;
		this.redoslijed = vozilo.redoslijed;
		this.prosjecnaBrzina = vozilo.prosjecnaBrzina;
		this.podrucjaURangu = vozilo.podrucjaURangu;
		this.status = vozilo.status;
		this.dostava = vozilo.dostava;
	}

	public String getRegistracija() {
		return registracija;
	}

	public String getOpis() {
		return opis;
	}

	public float getKapacitetTezine() {
		return kapacitetTezine;
	}

	public float getKapacitetProstora() {
		return kapacitetProstora;
	}

	public int getRedoslijed() {
		return redoslijed;
	}
	
	public int getProcjecnaBrzina() {
		return prosjecnaBrzina;
	}
	
	public String getPodrucjaPoRangu() {
		return podrucjaURangu;
	}
	
	public String getStatus() {
		return status;
	}

	public String getDostava() {
		return dostava;
	}

	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public void setKapacitetTezine(float kapacitetTezine) {
		this.kapacitetTezine = kapacitetTezine;
	}

	public void setKapacitetProstora(float kapacitetProstora) {
		this.kapacitetProstora = kapacitetProstora;
	}

	public void setRedoslijed(int redoslijed) {
		this.redoslijed = redoslijed;
	}
	
	public void setProsjecnaBrzina(int prosjecnaBrzina) {
		this.prosjecnaBrzina = prosjecnaBrzina;
	}
	
	public void setPodrucjaURangu(String podrucjaURangu) {
		this.podrucjaURangu = podrucjaURangu;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setDostava(String dostava) {
		this.dostava = dostava;
	}
}
