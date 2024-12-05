package asaric_zadaca_3.strategyGps;

import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Vozila;

public class GpsContext {
	private IsporukaStrategy isporuka;

	public void postaviOperacijuNaGps(IsporukaStrategy isporuka) {
		this.isporuka = isporuka;
	}

	public void izracunajGpsKoordinate(UkrcaniPaketi ukrcanPaket, Vozila vozilo) {
		if (isporuka != null) {
			isporuka.izracunajGpsKoordinate(ukrcanPaket, vozilo);
		} else {
			System.out.println("Strategija isporuke nije postavljena.");
		}
	}
}
