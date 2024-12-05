package asaric_zadaca_3.observer;

import java.util.List;
import asaric_zadaca_3.ObjektiSingleton;

public class ObavijestiPaket implements ObavijestiSubject {
	private List<ObavijestObserver> bezObavijesti;
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	public ObavijestiPaket() {
		this.bezObavijesti = objekti.getBezObavijest();
	}

	@Override
	public void dodajOsobu(ObavijestObserver osoba) {
		bezObavijesti.add(osoba);
	}

	@Override
	public void obrisiOsobu(String ime, String oznaka) {
		if (vratiTrazenuOsobu(ime, oznaka) == null) {
			System.out.println("Osoba " + ime + " nikad nije onemogucilo/la obavijesti za paket " + oznaka);
		} else {
			bezObavijesti.remove(vratiTrazenuOsobu(ime, oznaka));
		}
	}
	@Override
	public void obavijestiOZaprimljenomPaketu(ObavijestObserver osoba) {
		osoba.zaprimljenPaket();
	}

	public List<ObavijestObserver> getSviBezObavijesti() {
		return bezObavijesti;
	}

	public boolean sadrziOsobu(String ime, String oznaka) {
		for (ObavijestObserver osoba : bezObavijesti) {
			if (osoba instanceof Osoba && ((Osoba) osoba).getIme().equals(ime)
					&& ((Osoba) osoba).getOznaka().equals(oznaka)) {
				return true;
			} 
		}
		return false;
	}

	public ObavijestObserver vratiTrazenuOsobu(String ime, String oznaka) {
		for (ObavijestObserver osoba : bezObavijesti) {
			if (osoba instanceof Osoba && ((Osoba) osoba).getIme().equals(ime)
					&& ((Osoba) osoba).getOznaka().equals(oznaka)) {
				return osoba;
			}
		}
		return null;
	}

	@Override
	public void obavijestiOUkrcanomPaketu(ObavijestObserver osoba) {
		osoba.ukrcanPaket();
		
	}

	@Override
	public void obavijestiODostavljenomPaketu(ObavijestObserver osoba) {
		osoba.dostavljenPaket();	
	}

}
