package asaric_zadaca_3.strategyGps;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Vozila;


public class OdabirGpsIzracuna {

	public OdabirGpsIzracuna(UkrcaniPaketi paketi, Vozila vozilo) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		GpsContext izracun = new GpsContext();

		if (objekti.getIsporuka() == 1) {
			izracun.postaviOperacijuNaGps(new RedoslijedIsporukeStrategy());
			izracun.izracunajGpsKoordinate(paketi, vozilo);
		}
		if (objekti.getIsporuka() == 2) {
			izracun.postaviOperacijuNaGps(new NajblizaDostavaStrategy());
			izracun.izracunajGpsKoordinate(paketi, vozilo);
		}
	}
	
}
