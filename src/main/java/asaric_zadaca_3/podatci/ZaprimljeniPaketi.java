package asaric_zadaca_3.podatci;

public class ZaprimljeniPaketi {
	private String oznaka;
	private String vrijemePrijema;
	private String posiljatelj;
	private String primatelj;
	private String vrstaPaketa;
	private float obujam;
	private float tezina;
	private String uslugaDostave;
	private float iznosDostave;
	private boolean naDostavi;
	private String vrijemePotrebnoZaDostavu;
	private float brojKilometara;

	public ZaprimljeniPaketi(String oznaka, String vrijemePrijema, String posiljatelj, String primatelj,
			String vrstaPaketa, float obujam, float tezina, String uslugaDostave, float iznosDostave, boolean naDostavi,
			String vrijemePotrebnoZaDostavu, float brojKilometara) {
		this.oznaka = oznaka;
		this.vrijemePrijema = vrijemePrijema;
		this.posiljatelj = posiljatelj;
		this.primatelj = primatelj;
		this.vrstaPaketa = vrstaPaketa;
		this.obujam = obujam;
		this.tezina = tezina;
		this.uslugaDostave = uslugaDostave;
		this.iznosDostave = iznosDostave;
		this.naDostavi = naDostavi;
		this.vrijemePotrebnoZaDostavu = vrijemePotrebnoZaDostavu;
		this.brojKilometara = brojKilometara;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getVrijemePrijema() {
		return vrijemePrijema;
	}

	public void setVrijemePrijema(String vrijemePrijema) {
		this.vrijemePrijema = vrijemePrijema;
	}

	public String getPosiljatelj() {
		return posiljatelj;
	}

	public void setPosiljatelj(String posiljatelj) {
		this.posiljatelj = posiljatelj;
	}

	public String getPrimatelj() {
		return primatelj;
	}

	public void setPrimatelj(String primatelj) {
		this.primatelj = primatelj;
	}

	public String getVrstaPaketa() {
		return vrstaPaketa;
	}

	public void setVrstaPaketa(String vrstaPaketa) {
		this.vrstaPaketa = vrstaPaketa;
	}

	public String getUslugaDostave() {
		return uslugaDostave;
	}

	public void setUslugaDostave(String uslugaDostave) {
		this.uslugaDostave = uslugaDostave;
	}

	public float getIznosDostave() {
		return iznosDostave;
	}

	public void setIznosDostave(float iznosDostave) {
		this.iznosDostave = iznosDostave;
	}

	public float getObujam() {
		return obujam;
	}

	public void getTezina(float tezina) {
		this.tezina = tezina;
	}

	public float getTezina() {
		return tezina;
	}

	public void getObujam(float obujam) {
		this.obujam = obujam;
	}

	public void setNaDostavi(boolean naDostavi) {
		this.naDostavi = naDostavi;
	}

	public boolean getNaDostavi() {
		return naDostavi;
	}

	public String getVrijemePotrebnoZaDostavu() {
		return vrijemePotrebnoZaDostavu;
	}

	public void setVrijemePotrebnoZaDostavu(String vrijemePotrebnoZaDostavu) {
		this.vrijemePotrebnoZaDostavu = vrijemePotrebnoZaDostavu;
	}

	public float getBrojKilometara() {
		return brojKilometara;
	}

	public void setBrojKilometara(float brojKilometara) {
		this.brojKilometara = brojKilometara;
	}
}
