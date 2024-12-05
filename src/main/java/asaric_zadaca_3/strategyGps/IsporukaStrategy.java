package asaric_zadaca_3.strategyGps;

import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Vozila;

public interface IsporukaStrategy {
	void izracunajGpsKoordinate(UkrcaniPaketi ukrcanPaket, Vozila vozilo);
}
