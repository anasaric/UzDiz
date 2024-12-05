package asaric_zadaca_3.observer;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Paketi;

public class Osoba implements ObavijestObserver{
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	private String ime;
	private String oznaka;

    public Osoba(String ime, String oznaka) {
        this.ime = ime;
        this.oznaka = oznaka;
    }

	@Override
	public void zaprimljenPaket() {
		if(provjeriOsobu(ime, oznaka) == "posiljatelj") {
			System.out.println("Obavijest za posiljatelja " + ime + ": Paket " + oznaka + " je zaprimljen.");
		} else {
			System.out.println("Obavijest za primatelja " + ime + ": Paket " + oznaka + " je zaprimljen.");
		}
		  
	}

	public String getIme() {
		return ime;
	}

	public String getOznaka() {
		return oznaka;
	}
	
	private String provjeriOsobu(String ime, String oznaka) {
		for (Paketi paket : objekti.getPaketi()) {
			if (oznaka.equalsIgnoreCase(paket.getOznaka()) && ime.equalsIgnoreCase(paket.getPosiljatelj())) {
				return "posiljatelj";
			}
			if (oznaka.equalsIgnoreCase(paket.getOznaka()) && ime.equalsIgnoreCase(paket.getPrimatelj())) {
				return "primatelj";
			}
		}
		return null;
	}

	@Override
	public void ukrcanPaket() {
		if(provjeriOsobu(ime, oznaka) == "posiljatelj") {
			System.out.println("Obavijest za posiljatelja " + ime + ": Paket " + oznaka + " je ukrcan u vozilo.");
		} else {
			System.out.println("Obavijest za primatelja " + ime + ": Paket " + oznaka + " je ukrcan u vozilo.");
		}
		
	}

	@Override
	public void dostavljenPaket() {
		if(provjeriOsobu(ime, oznaka) == "posiljatelj") {
			System.out.println("Obavijest za posiljatelja " + ime + ": Paket " + oznaka + " je dostavljen na adresu.");
		} else {
			System.out.println("Obavijest za primatelja " + ime + ": Paket " + oznaka + " je dostavljen na adresu.");
		}
	}
}
