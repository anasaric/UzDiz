package asaric_zadaca_3.strategy;

import java.time.LocalTime;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;

public class UredZaPakete {

	public UredZaPakete() {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		System.out.println("Trenutno virtualno vrijeme : " + objekti.getTrenutnoRadnoVrijeme());
		PaketiContext voditelj = new PaketiContext();

		if (provjeriPuniSat()) {
			voditelj.postaviOperacijuNadPaketima(new UkrcavanjePaketa());
			voditelj.izvrsiOperacijuNadPaketom();
		}

		voditelj.postaviOperacijuNadPaketima(new ZaprimanjePaketa());
		voditelj.izvrsiOperacijuNadPaketom();
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getDostava().equalsIgnoreCase("na dostavi")) {
				voditelj.postaviOperacijuNadPaketima(new DostavaPaketa());
				voditelj.izvrsiOperacijuNadPaketom();
			}
		}
	}

	private boolean provjeriPuniSat() {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		LocalTime trenutnoVrijeme = LocalTime.parse(objekti.getTrenutnoRadnoVrijeme());
		LocalTime prosloVrijeme = LocalTime.parse(objekti.getProsloRadnoVrijeme());
		if (trenutnoVrijeme.getHour() != (prosloVrijeme.getHour())) {
			objekti.setProsloRadnoVrijeme(objekti.getTrenutnoRadnoVrijeme());
			return true;
		}
		objekti.setProsloRadnoVrijeme(objekti.getTrenutnoRadnoVrijeme());
		return false;
	}
}
