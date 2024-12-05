package asaric_zadaca_3.podatci;

public class Osobe {
	private String osoba;
	private int grad;
	private int ulica;
	private int kbr;

	public Osobe(String osoba, int grad, int ulica, int kbr) {
		this.osoba = osoba;
		this.grad = grad;
		this.ulica = ulica;
		this.kbr = kbr;
	}

	public String getOsoba() {
		return osoba;
	}

	public void setOznaka(String osoba) {
		this.osoba = osoba;
	}

	public int getGrad() {
		return grad;
	}
	
	public void setGrad(int grad) {
		this.grad = grad;
	}
	
	public int getUlica() {
		return ulica;
	}
	
	public void setUlica(int ulica) {
		this.ulica = ulica;
	}
	
	public int getKbr() {
		return kbr;
	}
	
	public void setKbr(int kbr) {
		this.kbr = kbr;
	}
}
