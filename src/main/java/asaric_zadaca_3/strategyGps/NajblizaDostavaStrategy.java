package asaric_zadaca_3.strategyGps;

import java.time.LocalTime;
import java.util.List;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Osobe;
import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Ulice;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;

public class NajblizaDostavaStrategy implements IsporukaStrategy {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	float lat = 0;
	float lon = 0;
	int pocetnaVelicina = 0;
	String vrijeme = null;
	String staroVrijeme = objekti.getTrenutnoRadnoVrijeme();
	float udaljenost = 1000;

	@Override
	public void izracunajGpsKoordinate(UkrcaniPaketi ukrcanPaket, Vozila vozilo) {
		pocetnaVelicina = ukrcanPaket.getUkrcaniPaketi().size();
		for (int i = 0; i < ukrcanPaket.getUkrcaniPaketi().size(); i++) {
			ZaprimljeniPaketi najbliziPaket = pronadiNajmanjiBroj(ukrcanPaket.getUkrcaniPaketi(), ukrcanPaket, i,
					vozilo);
			izracunajVrijemeIKilometre(najbliziPaket, ukrcanPaket.getUkrcaniPaketi(), i, vozilo);
			GPSIzracuni.azurirajSegmente(ukrcanPaket, najbliziPaket , vrijeme, staroVrijeme);
			if(i==0) {
				GPSIzracuni.azurirajVoznjuVrijeme(ukrcanPaket,vrijeme);
			}
			
			if(ukrcanPaket.getUkrcaniPaketi().size() == 0) {
				GPSIzracuni.UdaljenostZadnjaUlicaDoPoduzece(lat, lon);
				GPSIzracuni.azurirajVoznjuVrijemeKraj(ukrcanPaket,vrijeme);
			}
			ukrcanPaket.getUkrcaniPaketi().remove(najbliziPaket);
		}
		ukrcanPaket.setDostava(true);
	}

	private void izracunajVrijemeIKilometre(ZaprimljeniPaketi najbliziPaket, List<ZaprimljeniPaketi> list, int indeks, Vozila vozilo) {
		ZaprimljeniPaketi paket = list.get(indeks);
		if (pocetnaVelicina == list.size()) {
			najbliziPaket.setBrojKilometara(udaljenost);
			izracunajLatILon(najbliziPaket);
			vrijeme = izracunajVrijemeDostave(staroVrijeme, udaljenost, vozilo);
			paket.setVrijemePotrebnoZaDostavu(vrijeme);
		} else {
			najbliziPaket.setBrojKilometara(udaljenost);
			izracunajLatILon(najbliziPaket);
			staroVrijeme = vrijeme;
			vrijeme = izracunajVrijemeDostave(staroVrijeme, udaljenost, vozilo);
			paket.setVrijemePotrebnoZaDostavu(vrijeme);
		}
	}

	private ZaprimljeniPaketi pronadiNajmanjiBroj(List<ZaprimljeniPaketi> ukrcaniPaketi, UkrcaniPaketi ukrcanoVozilo,
			int indeks, Vozila vozilo) {
		float najmanjiBroj = 1000;
		
		ZaprimljeniPaketi najbliziPaket = ukrcaniPaketi.get(indeks);
		for (int i = 0; i < ukrcaniPaketi.size(); i++) {
			ZaprimljeniPaketi paket = ukrcaniPaketi.get(i);

			if (pocetnaVelicina != 0 && pocetnaVelicina == ukrcaniPaketi.size()) {
				udaljenost = GPSIzracuni.UdaljenostPoduzeceDoPrveUlice(paket);
			} else {
				System.out.println("BLAAA");
				udaljenost = UdaljenostIzmeduUlica(lat, lon, paket);
			}

			if (udaljenost < najmanjiBroj) {
				najmanjiBroj = udaljenost;
				najbliziPaket = paket;
			}
		}
		return najbliziPaket;	
	}

	private String izracunajVrijemeDostave(String staroVrijeme, float udaljenost, Vozila vozilo) {
		LocalTime trenutnoVrijeme = LocalTime.parse(staroVrijeme);
		LocalTime zbrojVremena = null;
		int brzina = vozilo.getProcjecnaBrzina();

		float vrijeme = udaljenost / brzina;
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

	private void izracunajLatILon(ZaprimljeniPaketi najbliziPaket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		for (Osobe osoba : objekti.getOsobe()) {
			if (najbliziPaket.getPrimatelj().equalsIgnoreCase(osoba.getOsoba())) {
				for (Ulice ulica : objekti.getUlice()) {
					int maxKucniBroj = ulica.getNajveciKucniBroj();
					if (maxKucniBroj < osoba.getKbr()) {
						lon = ulica.getGpsLon1()
								+ (osoba.getKbr() / maxKucniBroj) * (ulica.getGpsLon2() - ulica.getGpsLon1());
						lat = ulica.getGpsLat1()
								+ (osoba.getKbr() / maxKucniBroj) * (ulica.getGpsLat2() - ulica.getGpsLat1());
					}
				}
			}
		}
	}

	private float UdaljenostIzmeduUlica(float lat_1, float lon_1, ZaprimljeniPaketi paket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		float lat_2 = 0;
		float lon_2 = 0;
		float izracunUUlici = 0;
		for (Osobe osoba : objekti.getOsobe()) {
			if (paket.getPrimatelj().equalsIgnoreCase(osoba.getOsoba())) {
				for (Ulice ulica : objekti.getUlice()) {
					if (ulica.getId() == osoba.getUlica()) {
						lat_2 = ulica.getGpsLat1();
						lon_2 = ulica.getGpsLon1();
						izracunUUlici = GPSIzracuni.izracunUdaljenostiDoKucnogBroja(ulica, osoba.getKbr());

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

	
}
