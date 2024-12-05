package asaric_zadaca_3.strategyGps;

import java.time.LocalTime;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Osobe;
import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;
import asaric_zadaca_3.podatci.Ulice;
import asaric_zadaca_3.podatci.Vozila;

public class RedoslijedIsporukeStrategy implements IsporukaStrategy {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	public void izracunajGpsKoordinate(UkrcaniPaketi ukrcanPaket, Vozila vozilo) {
		String vrijeme = null;
		for (int i = 0; i < ukrcanPaket.getUkrcaniPaketi().size(); i++) {
			ZaprimljeniPaketi paket = ukrcanPaket.getUkrcaniPaketi().get(i);
			if(i+1 == ukrcanPaket.getUkrcaniPaketi().size()) {
				if(vrijeme != null) {
					GPSIzracuni.azurirajVoznjuVrijemeKraj(ukrcanPaket,vrijeme);
				}		
			}
			if (i == 0 && paket.getVrijemePotrebnoZaDostavu() == null) {
				float udaljenostDoPocetkaUlice = GPSIzracuni.UdaljenostPoduzeceDoPrveUlice(paket);
				paket.setBrojKilometara(udaljenostDoPocetkaUlice);
				vrijeme = GPSIzracuni.izracunajVrijemeDostave(udaljenostDoPocetkaUlice, vozilo);
				GPSIzracuni.azurirajVoznjuVrijeme(ukrcanPaket,vrijeme);
				paket.setVrijemePotrebnoZaDostavu(vrijeme);
				GPSIzracuni.azurirajSegmente(ukrcanPaket, paket, vrijeme, objekti.getTrenutnoRadnoVrijeme());
				GPSIzracuni.azurirajPodatkeVoznja(ukrcanPaket, paket);
			} else if (i != 0 && paket.getVrijemePotrebnoZaDostavu() == null) {
				ZaprimljeniPaketi prethodniPaket = ukrcanPaket.getUkrcaniPaketi().get(i - 1);
				float udaljenostiUlica = UdaljenostIzmeduUlica(prethodniPaket, paket);
				paket.setBrojKilometara(udaljenostiUlica);
				vrijeme = izracunajVrijemeDostaveSObziromNaProsli(udaljenostiUlica, vozilo, prethodniPaket);
				paket.setVrijemePotrebnoZaDostavu(vrijeme);
				GPSIzracuni.azurirajSegmente(ukrcanPaket, paket, vrijeme, prethodniPaket.getVrijemePotrebnoZaDostavu());
			}
		}
		ukrcanPaket.getUkrcaniPaketi().clear();
		ukrcanPaket.setDostava(true);
	}


	public static float izracunUdaljenostiDoKucnogBroja(Ulice ulica, int kucniBroj) {
		float lat_1 = ulica.getGpsLat1();
		float lon_1 = ulica.getGpsLon1();
		float lat_2 = ulica.getGpsLat2();
		float lon_2 = ulica.getGpsLon2();
		int maxKucniBroj = ulica.getNajveciKucniBroj();
		if (maxKucniBroj < kucniBroj)
			kucniBroj = maxKucniBroj;

		float duljinaUlice = GPSIzracuni.euklidskaUdaljenost(lat_1, lon_1, lat_2, lon_2);

		float duljinaDoKucnogBroja = duljinaUlice * (kucniBroj / maxKucniBroj);
		return duljinaDoKucnogBroja;
	}
	
	public static String izracunajVrijemeDostaveSObziromNaProsli(float udaljenostiUlica, Vozila vozilo,
			ZaprimljeniPaketi prethodniPaket) {
		LocalTime trenutnoVrijeme = LocalTime.parse(prethodniPaket.getVrijemePotrebnoZaDostavu());
		LocalTime zbrojVremena = null;
		int brzina = vozilo.getProcjecnaBrzina();

		float vrijeme = udaljenostiUlica / brzina;
		int sati = (int) vrijeme;
		int minute = (int) ((vrijeme - sati) * 60);
		int sekunde = (int) (((vrijeme - sati) * 60 - minute) * 60) * 100;

		if (sati != 0) {
			zbrojVremena = trenutnoVrijeme.plusHours(sati);
		}
		if (minute != 0) {
			zbrojVremena = trenutnoVrijeme.plusMinutes(minute);
		}
		if (sekunde != 0) {
			zbrojVremena = trenutnoVrijeme.plusSeconds(sekunde);
		}
		if (sati == 0 && minute == 0 && sekunde == 0) {
			zbrojVremena = trenutnoVrijeme;
		}
		return zbrojVremena.toString();
	}
	
	public static float UdaljenostIzmeduUlica(ZaprimljeniPaketi prethodniPaket, ZaprimljeniPaketi paket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		float lat_1 = 0;
		float lon_1 = 0;
		float lat_2 = 0;
		float lon_2 = 0;
		float izracunUUlici = 0;
		for (Osobe osoba : objekti.getOsobe()) {
			if (prethodniPaket.getPrimatelj().equalsIgnoreCase(osoba.getOsoba())) {
				for (Ulice ulica : objekti.getUlice()) {
					if (ulica.getId() == osoba.getUlica()) {
						lat_1 = noviLat(ulica, osoba.getKbr());
						lon_1 = noviLon(ulica, osoba.getKbr());
					}
				}
			}
			if (paket.getPrimatelj().equalsIgnoreCase(osoba.getOsoba())) {
				for (Ulice ulica : objekti.getUlice()) {
					if (ulica.getId() == osoba.getUlica()) {
						lat_2 = ulica.getGpsLat1();
						lon_2 = ulica.getGpsLon1();
						izracunUUlici = izracunUdaljenostiDoKucnogBroja(ulica, osoba.getKbr());

					}
				}
			}
		}
		if (lat_1 != 0 && lon_1 != 0 && lat_2 != 0 && lon_2 != 0) {
			float izracunDoUlice = GPSIzracuni.euklidskaUdaljenost(lat_1, lon_1, lat_2, lon_2);
			return izracunDoUlice + izracunUUlici;

		}
		return 0;
	}
	
	private static float noviLon(Ulice ulica, int kucniBroj) {
		int maxKucniBroj = ulica.getNajveciKucniBroj();
		if (maxKucniBroj < kucniBroj)
			kucniBroj = maxKucniBroj;
		return ulica.getGpsLon1() + (kucniBroj / maxKucniBroj) * (ulica.getGpsLon2() - ulica.getGpsLon1());
	}

	private static float noviLat(Ulice ulica, int kucniBroj) {
		int maxKucniBroj = ulica.getNajveciKucniBroj();
		if (maxKucniBroj < kucniBroj)
			kucniBroj = maxKucniBroj;
		return ulica.getGpsLat1() + (kucniBroj / maxKucniBroj) * (ulica.getGpsLat2() - ulica.getGpsLat1());
	}
}
