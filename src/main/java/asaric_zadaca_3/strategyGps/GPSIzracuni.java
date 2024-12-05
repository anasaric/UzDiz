package asaric_zadaca_3.strategyGps;

import java.time.Duration;
import java.time.LocalTime;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Osobe;
import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Ulice;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;
import asaric_zadaca_3.visitor.AzuriranjePodatakaVisitor;
import asaric_zadaca_3.visitor.Segmenti;
import asaric_zadaca_3.visitor.Vozilo;
import asaric_zadaca_3.visitor.Voznja;

public class GPSIzracuni {
	public static float izracunUdaljenostiDoKucnogBroja(Ulice ulica, int kucniBroj) {
		float lat_1 = ulica.getGpsLat1();
		float lon_1 = ulica.getGpsLon1();
		float lat_2 = ulica.getGpsLat2();
		float lon_2 = ulica.getGpsLon2();
		int maxKucniBroj = ulica.getNajveciKucniBroj();
		if (maxKucniBroj < kucniBroj)
			kucniBroj = maxKucniBroj;

		float duljinaUlice = euklidskaUdaljenost(lat_1, lon_1, lat_2, lon_2);
		float duljinaDoKucnogBroja = duljinaUlice * (kucniBroj / maxKucniBroj);

		return duljinaDoKucnogBroja;
	}

	public static float euklidskaUdaljenost(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) * 111;

	}

	public static float UdaljenostPoduzeceDoPrveUlice(ZaprimljeniPaketi paket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		String gps = objekti.getGps();
		String[] koordinateGps = gps.replace(" ", "").split(",");
		float lat_1 = Float.parseFloat(koordinateGps[0]);
		float lon_1 = Float.parseFloat(koordinateGps[1]);
		float lat_2 = 0;
		float lon_2 = 0;
		for (Osobe osoba : objekti.getOsobe()) {
			if (paket.getPrimatelj().equalsIgnoreCase(osoba.getOsoba())) {
				for (Ulice ulica : objekti.getUlice()) {
					if (ulica.getId() == osoba.getUlica()) {
						lat_2 = ulica.getGpsLat1();
						lon_2 = ulica.getGpsLon1();
						float izracunDoUlice = GPSIzracuni.euklidskaUdaljenost(lat_1, lon_1, lat_2, lon_2);
						float izracunUUlici = GPSIzracuni.izracunUdaljenostiDoKucnogBroja(ulica, osoba.getKbr());
						return izracunDoUlice + izracunUUlici;
					}
				}
			}
		}

		return 0;
	}

	public static String izracunajVrijemeDostave(float udaljenostDoPocetkaUlice, Vozila vozilo) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		LocalTime trenutnoVrijeme = LocalTime.parse(objekti.getTrenutnoRadnoVrijeme());
		LocalTime zbrojVremena = null;
		int brzina = vozilo.getProcjecnaBrzina();

		float vrijeme = udaljenostDoPocetkaUlice / brzina;
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

	public static void azurirajSegmente(UkrcaniPaketi ukrcanPaket, ZaprimljeniPaketi paket, String vrijeme,
			String staroVrijeme) {

		String[] staroVrijemeSplit = staroVrijeme.split(":");
		String[] vrijemeSplit = vrijeme.split(":");
		if (staroVrijemeSplit.length == 2) {
			staroVrijeme = staroVrijeme + ":00";
		}
		if (vrijemeSplit.length == 2) {
			vrijeme = vrijeme + ":00";
		}

		LocalTime pocetak = LocalTime.parse(staroVrijeme);
		LocalTime kraj = LocalTime.parse(vrijeme);
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();

		if (pocetak != null) {
			Duration razlika = Duration.between(pocetak, kraj);
			Segmenti segment = new Segmenti(ukrcanPaket.getOznakaVozila(), ukrcanPaket.getBrojVoznje(), staroVrijeme,
					vrijeme, razlika.toMinutes(), paket.getBrojKilometara(), paket.getOznaka(), false);
			segment.accept(visitor);
		}
		azurirajPodatkeOVozilu(ukrcanPaket, paket);
		azurirajPodatkeVoznja(ukrcanPaket, paket);
	}

	private static void azurirajPodatkeOVozilu(UkrcaniPaketi ukrcanPaket, ZaprimljeniPaketi paket) {
		int brHitnih = 0;
		int brObicnih = 0;
		if (paket.getUslugaDostave().equalsIgnoreCase("H")) {
			brHitnih++;
		} else {
			brObicnih++;
		}
		Vozilo vozilo = new Vozilo(ukrcanPaket.getOznakaVozila(), null, paket.getBrojKilometara(),
				brHitnih, brObicnih, 0, 0, 0,
				0);
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
		vozilo.accept(visitor);
	}


	public static float UdaljenostZadnjaUlicaDoPoduzece(float lat, float lon) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		String gps = objekti.getGps();
		String[] koordinateGps = gps.replace(" ", "").split(",");
		float lat_2 = Float.parseFloat(koordinateGps[0]);
		float lon_2 = Float.parseFloat(koordinateGps[1]);
		float izracunDoUlice = GPSIzracuni.euklidskaUdaljenost(lat, lon, lat_2, lon_2);
		return izracunDoUlice;
	}

	public static float UdaljenostZadnjaUlicaDoPoduzece(ZaprimljeniPaketi paket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		String gps = objekti.getGps();
		String[] koordinateGps = gps.replace(" ", "").split(",");
		float lat_1 = 0;
		float lon_1 = 0;
		float lat_2 = Float.parseFloat(koordinateGps[0]);
		float lon_2 = Float.parseFloat(koordinateGps[1]);
		for (Osobe osoba : objekti.getOsobe()) {
			if (paket.getPrimatelj().equalsIgnoreCase(osoba.getOsoba())) {
				for (Ulice ulica : objekti.getUlice()) {
					if (ulica.getId() == osoba.getUlica()) {
						lat_1 = ulica.getGpsLat1();
						lon_1 = ulica.getGpsLon1();
						float izracunDoUlice = euklidskaUdaljenost(lat_1, lon_1, lat_2, lon_2);
						float izracunUUlici = izracunUdaljenostiDoKucnogBroja(ulica, osoba.getKbr());
						return izracunDoUlice + izracunUUlici;
					}
				}
			}
		}

		return 0;
	}

	public static void azurirajPodatkeVoznja(UkrcaniPaketi ukrcanPaket, ZaprimljeniPaketi paket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		int brHitnihPaketa = 0;
		int brNormalnihPaketa = 0;
		Vozilo trazenoVozilo = null;
		for (Vozilo vozilo : objekti.getVozilo()) {
			if (ukrcanPaket.getOznakaVozila().equalsIgnoreCase(vozilo.getRegistracija())) {
				trazenoVozilo = vozilo;
			}
		}
		if (paket.getUslugaDostave().equalsIgnoreCase("H")) {
			brHitnihPaketa++;
		} else {
			brNormalnihPaketa++;
		}
		if (trazenoVozilo != null) {
			AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
			Voznja voznja = new Voznja(ukrcanPaket.getOznakaVozila(), trazenoVozilo.getBrojVoznji(), null, null, 0,
					paket.getBrojKilometara(), brHitnihPaketa, brNormalnihPaketa, 0, 0, 0);
			voznja.accept(visitor);
		}

	}

	public static void azurirajVoznjuVrijeme(UkrcaniPaketi ukrcanPaket, String vrijeme) {
		Vozilo trazenoVozilo = dohvatiVozilo(ukrcanPaket);
		if (trazenoVozilo != null) {
			AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
			Voznja voznja = new Voznja(ukrcanPaket.getOznakaVozila(), trazenoVozilo.getBrojVoznji(), LocalTime.parse(vrijeme), null, 0, 0, 0, 0,
					0, 0, 0);
			voznja.accept(visitor);
		}
	}

	public static void azurirajVoznjuVrijemeKraj(UkrcaniPaketi ukrcanPaket, String vrijeme) {
		Vozilo trazenoVozilo = dohvatiVozilo(ukrcanPaket);
		if (trazenoVozilo != null) {
			AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
			Voznja voznja = new Voznja(ukrcanPaket.getOznakaVozila(), trazenoVozilo.getBrojVoznji(), null, LocalTime.parse(vrijeme), 0, 0, 0, 0,
					0, 0, 0);
			voznja.accept(visitor);
		}
	}
	private static Vozilo dohvatiVozilo(UkrcaniPaketi ukrcanPaket) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		for (Vozilo vozilo : objekti.getVozilo()) {
			if (ukrcanPaket.getOznakaVozila().equalsIgnoreCase(vozilo.getRegistracija())) {
				return vozilo;
			}
		}
		return null;
	}
}
