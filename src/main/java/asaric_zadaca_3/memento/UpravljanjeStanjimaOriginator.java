package asaric_zadaca_3.memento;

import java.util.Date;
import java.util.List;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;
import asaric_zadaca_3.visitor.AzuriranjePodatakaVisitor;
import asaric_zadaca_3.visitor.Segmenti;
import asaric_zadaca_3.visitor.Vozilo;
import asaric_zadaca_3.visitor.Voznja;
import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.UkrcaniPaketi;

public class UpravljanjeStanjimaOriginator implements SpremanjeInterface {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	private String stanje;

	public void postaviStanje(String stanje) {
		this.stanje = stanje;
	}

	@Override
	public PodatciMemento spremiStanje() {
		System.out.println("Spremanje trenutnog stanja virtualnog sata, paketa i vozila pod nazivom : " + stanje);
		long virtualnoVrijemeSekunde = objekti.getTrenutnoVrijemeSekunde();
		String virtualnoRadnoVrijeme = objekti.getTrenutnoRadnoVrijeme();
		Date virtualniSat = objekti.getVirtualniSat();
		List<Paketi> paketi = objekti.getPaketi();
		List<Vozila> vozila = objekti.getVozila();
		PodatciMemento VPV = new PodatciMemento(virtualnoVrijemeSekunde, virtualnoRadnoVrijeme, virtualniSat, paketi,
				vozila);
		return VPV;

	}

	@Override
	public void vratiStanje(PodatciMemento podatci) {
		System.out.println("Povratak spremljenog stanja virtualnog sata, paketa i vozila pod nazivom: " + stanje);
		vratiOsnovnePodatke(podatci);
		vratiZaprimljenePakete(podatci);
		vratiUkrcanePakete(podatci);
		azurirajUkrcanePodatcke(podatci);
		vratiVozila(podatci);
		 vratiSegmente(podatci); 

	}

	private void azurirajUkrcanePodatcke(PodatciMemento podatci) {
		float sumaCijena = 0;
		float sumaObujma = 0;
		float sumaTezine = 0;
		for (UkrcaniPaketi ukrcaniPaketi : objekti.getUkrcaniPaketi()) {
			for (ZaprimljeniPaketi zaprimljeniPaketi : ukrcaniPaketi.getUkrcaniPaketi()) {
				sumaCijena = sumaCijena + zaprimljeniPaketi.getIznosDostave();
				sumaObujma = sumaObujma + zaprimljeniPaketi.getObujam();
				sumaTezine = sumaTezine + zaprimljeniPaketi.getTezina();
			}
			ukrcaniPaketi.setSumaCijena(sumaCijena);
			ukrcaniPaketi.setSumaTezina(sumaTezine);
			ukrcaniPaketi.setSumaObujma(sumaObujma);
		}
	}

	private void vratiOsnovnePodatke(PodatciMemento podatci) {
		objekti.setTrenutnoRadnoVrijeme(podatci.getvirtualniRadniSat());
		objekti.setTrenutnoVrijemeSekunde(podatci.getVirtualniSatSekunde());
		objekti.setVirtualniSat((Date) podatci.getvirtualniSat().clone());
		objekti.setPaketi(podatci.getPaketi());
		objekti.setVozila(podatci.getVozila());
	}

	private void vratiVozila(PodatciMemento podatci) {
		for (UkrcaniPaketi ukrcaniPaketi : objekti.getUkrcaniPaketi()) {
			for (Vozila vozilo : objekti.getVozila()) {
				if (ukrcaniPaketi.getOznakaVozila().equals(vozilo.getRegistracija())) {
					if (vozilo.getDostava().equals("u mirovanju")) {
						ukrcaniPaketi.setDostava(false);
					}
					if (vozilo.getDostava().equals("na dostavi")) {
						ukrcaniPaketi.setDostava(true);
					}
				}
			}
		}
	}

	private void vratiSegmente(PodatciMemento podatci) {
		Segmenti obrisiSegment = null;
		for (Paketi paketi : podatci.getPaketi()) {
			for (Segmenti segment : objekti.getSegmenti()) {
				if (paketi.getOznaka().equals(segment.getOznakaPaketa()) && paketi.getdostavljen() == null) {
					obrisiSegment = segment;
				}
			}
			if (obrisiSegment != null) {
				objekti.getSegmenti().remove(obrisiSegment);
				azurirajVoznju(obrisiSegment, paketi.getUslugaDostave());
				azurirajVozilo(obrisiSegment, paketi);
				obrisiSegment = null;
			}
		}
	}

	private void azurirajVozilo(Segmenti segment, Paketi paket) {
		Vozilo azurirajVozilo = null;
		UkrcaniPaketi ukrcanPaket = null;
		Vozila pronadiVozilo = null;
		int brojHitnih = 0;
		int brojObicnih = 0;
		for (Vozilo vozilo : objekti.getVozilo()) {
			if (segment.getRegistracija().equalsIgnoreCase(vozilo.getRegistracija())) {
				azurirajVozilo = vozilo;
			}
			for (UkrcaniPaketi ukrcani : objekti.getUkrcaniPaketi()) {
				if (ukrcani.getOznakaVozila().equalsIgnoreCase(vozilo.getRegistracija())) {
					ukrcanPaket = ukrcani;
				}
			}
			for (Vozila vozila : objekti.getVozila()) {
				if (vozila.getRegistracija().equalsIgnoreCase(vozilo.getRegistracija())) {
					pronadiVozilo = vozila;
				}
			}
		}
		if (paket.getVrstaPaketa().equalsIgnoreCase("H")) {
			brojHitnih = -1;
		} else {
			brojObicnih = -1;
		}

		if (azurirajVozilo != null && ukrcanPaket != null && pronadiVozilo != null) {
			Vozilo vozilo = new Vozilo(ukrcanPaket.getOznakaVozila(), pronadiVozilo.getStatus(),
					-segment.getUkupnoKilometara(), brojHitnih, brojObicnih, -1,
					(ukrcanPaket.getSumaObujma() / pronadiVozilo.getKapacitetProstora()) * 100,
					(ukrcanPaket.getSumaTezina() / pronadiVozilo.getKapacitetTezine()) * 100, 0);
			AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
			vozilo.accept(visitor);
		}

	}

	private void azurirajVoznju(Segmenti segment, String uslugaDostave) {
		int brojHitnih = 0;
		int brojObicnih = 0;
		Voznja azurirajVoznju = null;
		for (Voznja voznja : objekti.getVoznja()) {
			if (voznja.getRegistracija().equalsIgnoreCase(segment.getRegistracija())
					&& voznja.getBrojVoznje() == segment.getBrojVoznje()) {
				azurirajVoznju = voznja;
			}
		}
		if (uslugaDostave.equalsIgnoreCase("H")) {
			brojHitnih = -1;
		} else {
			brojObicnih = -1;
		}
		if (azurirajVoznju != null) {
			AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
			Voznja voznja = new Voznja(azurirajVoznju.getRegistracija(), azurirajVoznju.getBrojVoznje(), null, null, 0,
					-segment.getUkupnoKilometara(), brojHitnih, brojObicnih, -1, 0, 0);
			voznja.accept(visitor);
		}
	}

	private void vratiUkrcanePakete(PodatciMemento podatci) {
		ZaprimljeniPaketi zaprimljeniZaBrisanje = null;
		UkrcaniPaketi ukrcaniZaMijenjanje = null;
		for (Paketi paketi : podatci.getPaketi()) {
			for (UkrcaniPaketi ukrcaniPaketi : objekti.getUkrcaniPaketi()) {
				for (ZaprimljeniPaketi zaprimljeniPaketi : ukrcaniPaketi.getUkrcaniPaketi()) {
					if (paketi.isObraden() == false
							&& paketi.getOznaka().equalsIgnoreCase(zaprimljeniPaketi.getOznaka())) {
						zaprimljeniZaBrisanje = zaprimljeniPaketi;
						ukrcaniZaMijenjanje = ukrcaniPaketi;
					}
				}
			}
			ukrcaniZaMijenjanje.makniUkrcaniPaket(zaprimljeniZaBrisanje);
		}
	}

	private void vratiZaprimljenePakete(PodatciMemento podatci) {
		ZaprimljeniPaketi paket = null;
		for (Paketi paketi : podatci.getPaketi()) {
			for (ZaprimljeniPaketi zaprimljeniPaket : objekti.getZaprimljeniPaketi()) {
				if (paketi.isObraden() == false && paketi.getOznaka().equalsIgnoreCase(zaprimljeniPaket.getOznaka())) {
					paket = zaprimljeniPaket;
				}
			}
			if (paket != null) {
				objekti.getZaprimljeniPaketi().remove(paket);
			}
		}
	}

}
