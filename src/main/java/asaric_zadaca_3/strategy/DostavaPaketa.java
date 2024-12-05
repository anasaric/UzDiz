package asaric_zadaca_3.strategy;

import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.state.UMirovanju;
import asaric_zadaca_3.state.VozilaState;
import asaric_zadaca_3.strategyGps.OdabirGpsIzracuna;
import asaric_zadaca_3.visitor.AzuriranjePodatakaVisitor;
import asaric_zadaca_3.visitor.Segmenti;
import asaric_zadaca_3.visitor.Vozilo;
import asaric_zadaca_3.visitor.Voznja;

import java.time.LocalTime;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.observer.ObavijestiPaket;
import asaric_zadaca_3.observer.Osoba;
import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.Vozila;

public class DostavaPaketa implements VoditeljPaketa {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	Vozila vozilo;

	@Override
	public void upravljanjePaketom() {
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getDostava().equalsIgnoreCase("na dostavi")) {
				this.vozilo = vozilo;
				DostaviPaketeVozila(vozilo);
			}
		}
	}

	private void DostaviPaketeVozila(Vozila vozilo) {
		for (UkrcaniPaketi ukrcanPaket : objekti.getUkrcaniPaketi()) {
			if (!ukrcanPaket.isDostava() && vozilo.getRegistracija().equalsIgnoreCase(ukrcanPaket.getOznakaVozila())) {
				OdabirGpsIzracuna izracun = new OdabirGpsIzracuna(ukrcanPaket, vozilo);
			}
			if (ukrcanPaket.isDostava() && vozilo.getRegistracija().equalsIgnoreCase(ukrcanPaket.getOznakaVozila())) {
				dostaviPakete(ukrcanPaket);
			}
		}
	}

	private void dostaviPakete(UkrcaniPaketi ukrcanPaket) {
		for (int i = 0; i < objekti.getSegmenti().size(); i++) {
			Segmenti segment = objekti.getSegmenti().get(i);
			if (!segment.isDostavljen() && ukrcanPaket.getOznakaVozila().equalsIgnoreCase(segment.getRegistracija())) {
				if (LocalTime.parse(segment.getVrijemePovratka())
						.isAfter(LocalTime.parse(objekti.getTrenutnoRadnoVrijeme()))) {
					paketDostavljen(segment);
					obavijestODOstaviPaketa(segment);
					azurirajPodatkeVozilo(ukrcanPaket, segment);
					azurirajPodatkeVoznja(ukrcanPaket, segment);
					azurirajSegment(segment, ukrcanPaket);
				}
			}
		}
		postaviVoziloUMirovanje(ukrcanPaket);
	}

	private void azurirajPodatkeVoznja(UkrcaniPaketi ukrcanPaket, Segmenti segment) {
		Vozilo trazenoVozilo = dohvatiVozilo(ukrcanPaket);
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
		Voznja voznja = new Voznja(ukrcanPaket.getOznakaVozila(), trazenoVozilo.getBrojVoznji(),
				null, null, 0, 0, 0, 0, 0, 0, 0);
		voznja.accept(visitor);
		
	}

	private Vozilo dohvatiVozilo(UkrcaniPaketi ukrcanPaket) {
		for (Vozilo vozilo : objekti.getVozilo()) {
			if (ukrcanPaket.getOznakaVozila().equalsIgnoreCase(vozilo.getRegistracija())) {
				return vozilo;
			}
		}
		return null;
	}

	private void azurirajSegment(Segmenti segment, UkrcaniPaketi ukrcanPaket) {
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
		Segmenti segmenti = new Segmenti(ukrcanPaket.getOznakaVozila(), ukrcanPaket.getBrojVoznje(), null, null, 0, 0,
				segment.getOznakaPaketa(), true);
		segmenti.accept(visitor);
	}

	private void paketDostavljen(Segmenti segment) {
		for (Paketi paketi : objekti.getPaketi()) {
			if (paketi.getOznaka().equalsIgnoreCase(segment.getOznakaPaketa())) {
				paketi.setDostavljen(objekti.getTrenutnoRadnoVrijeme());
			}
		}
	}

	private void postaviVoziloUMirovanje(UkrcaniPaketi ukrcanPaket) {
		String status = null;
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(ukrcanPaket.getOznakaVozila())) {
				VozilaState trenutnoStanje = new UMirovanju();
				trenutnoStanje.promijeniStatus(vozilo);
				status = vozilo.getStatus();
			}
		}

		Vozilo vozilo = new Vozilo(ukrcanPaket.getOznakaVozila(), status, 0, 0, 0, 0, 0,
				0, 0);
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
		vozilo.accept(visitor);
	}

	private void azurirajPodatkeVozilo(UkrcaniPaketi ukrcanPaket, Segmenti segment) {
		float tezina = 0;
		float obujam = 0;
		Vozilo trazenoVozilo = dohvatiVozilo(ukrcanPaket);
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(ukrcanPaket.getOznakaVozila())) {
				tezina = vozilo.getKapacitetTezine();
				obujam = vozilo.getKapacitetProstora();
			}
		}
		if(ukrcanPaket.getSumaObujma() > obujam) {
			ukrcanPaket.setSumaObujma(obujam);
		}
		if(ukrcanPaket.getSumaTezina() > tezina) {
			ukrcanPaket.setSumaTezina(tezina);
		}
		Vozilo vozilo = new Vozilo(ukrcanPaket.getOznakaVozila(), null, segment.getUkupnoKilometara(), 0, 0, 1,
				(ukrcanPaket.getSumaObujma() / obujam)*100, (ukrcanPaket.getSumaTezina() / tezina)*100, 0);
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
		vozilo.accept(visitor);
		Voznja voznja = new Voznja(ukrcanPaket.getOznakaVozila(), trazenoVozilo.getBrojVoznji(),
				null, null, 0, 0, 0, 0, 0, (ukrcanPaket.getSumaObujma()/obujam)*100, (ukrcanPaket.getSumaTezina()/tezina));
		voznja.accept(visitor);
	}

	private void obavijestODOstaviPaketa(Segmenti segment) {
		Paketi dostavljenPaket = null;
		for (Paketi paketi : objekti.getPaketi()) {
			if (paketi.getOznaka().equalsIgnoreCase(segment.getOznakaPaketa())) {
				dostavljenPaket = paketi;
			}
		}
		if (dostavljenPaket != null) {
			ObavijestiPaket obavijest = new ObavijestiPaket();
			if (!obavijest.sadrziOsobu(dostavljenPaket.getPosiljatelj(), dostavljenPaket.getOznaka())) {
				obavijest.obavijestiODostavljenomPaketu(
						new Osoba(dostavljenPaket.getPosiljatelj(), dostavljenPaket.getOznaka()));
			}
			if (!obavijest.sadrziOsobu(dostavljenPaket.getPrimatelj(), dostavljenPaket.getOznaka())) {
				obavijest.obavijestiODostavljenomPaketu(
						new Osoba(dostavljenPaket.getPrimatelj(), dostavljenPaket.getOznaka()));
			}
		}

	}

}
