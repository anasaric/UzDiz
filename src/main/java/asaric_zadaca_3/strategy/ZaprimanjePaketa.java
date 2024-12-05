package asaric_zadaca_3.strategy;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.observer.ObavijestiPaket;
import asaric_zadaca_3.observer.Osoba;
import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.Vrste;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;

public class ZaprimanjePaketa implements VoditeljPaketa {

	@Override
	public void upravljanjePaketom() {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		SimpleDateFormat ispis = new SimpleDateFormat("dd.MM.yyyy.");
		String datum = ispis.format(objekti.getVirtualniSat());

		for (Paketi paket : objekti.getPaketi()) {
			String[] datumPaketa = paket.getVrijemePrijema().split(" ");
			if (!paket.isObraden()) {
				String[] datumVrijemePaketa = paket.getVrijemePrijema().split(" ");
				if (!datum.equalsIgnoreCase(datumPaketa[0])) {
					zaprimiPaket(paket);
				}
				if (LocalTime.parse(objekti.getTrenutnoRadnoVrijeme()).isAfter(LocalTime.parse(datumVrijemePaketa[1]))
						&& datum.equalsIgnoreCase(datumPaketa[0])) {
					zaprimiPaket(paket);
				}
			}
		}
		
	}
	
		private void zaprimiPaket(Paketi paket) {
			ObjektiSingleton objekti = ObjektiSingleton.getInstance();
			paket.setObraden(true);
			Float cijenaPaketa;
			float obujam;
			if (paket.getIznosPouzeca() > 0.00) {
				cijenaPaketa = paket.getIznosPouzeca();

			} else {
				cijenaPaketa = obradiCijenuPaketa(paket);
			}
			if (cijenaPaketa != null) {
				obavijestOZaprimljenomPaketu(paket);
				if (paket.getVrstaPaketa().equalsIgnoreCase("X")) {
					obujam = paket.getVisina() * paket.getSirina() * paket.getDuzina();
				} else {
					obujam = izracunajObujamPaketa(paket);
				}
				paket.setCijena(cijenaPaketa);
				ZaprimljeniPaketi zaprimljeniPaket = new ZaprimljeniPaketi(paket.getOznaka(),
						paket.getVrijemePrijema(), paket.getPosiljatelj(), paket.getPrimatelj(),
						paket.getVrstaPaketa(), obujam, paket.getTezina(), paket.getUslugaDostave(),
						cijenaPaketa, false, null, 0);
				objekti.getZaprimljeniPaketi().add(zaprimljeniPaket);	
				
			}
	}
		private void obavijestOZaprimljenomPaketu(Paketi paket) {
			ObavijestiPaket obavijest = new ObavijestiPaket();
			if (!obavijest.sadrziOsobu(paket.getPosiljatelj(), paket.getOznaka())) {
				obavijest.obavijestiOZaprimljenomPaketu(
						new Osoba(paket.getPosiljatelj(), paket.getOznaka()));			
			}
			if (!obavijest.sadrziOsobu(paket.getPrimatelj(), paket.getOznaka())) {
				obavijest.obavijestiOZaprimljenomPaketu(new Osoba(paket.getPrimatelj(), paket.getOznaka()));
			}
		
	}
		private float izracunajObujamPaketa(Paketi paket) {
			ObjektiSingleton objekti = ObjektiSingleton.getInstance();
			for (Vrste vrsta : objekti.getVrste()) {
				if (vrsta.getOznaka().equalsIgnoreCase(paket.getVrstaPaketa())) {
					return vrsta.getVisina() * vrsta.getSirina() * vrsta.getDuzina();
				}
			}
			return 0;
		}

		private Float obradiCijenuPaketa(Paketi paket) {
			ObjektiSingleton objekti = ObjektiSingleton.getInstance();
			for (Vrste vrsta : objekti.getVrste()) {
				if (vrsta.getOznaka().equalsIgnoreCase(paket.getVrstaPaketa())) {
					if (vrsta.getOznaka().equalsIgnoreCase("X")) {
						if ((vrsta.getVisina() < paket.getVisina()) || (vrsta.getSirina() < paket.getSirina())
								|| (vrsta.getDuzina() < paket.getDuzina())) {
							System.out.println("Paket s oznakon " + paket.getOznaka()
									+ " premasuje volumen paketa za vrstu paketa " + paket.getVrstaPaketa());
						} else if (paket.getTezina() > vrsta.getMaksimalnaTezina()) {
							System.out.println("Paket s oznakon " + paket.getOznaka()
									+ " premasuje maksimalnu tezinu paketa za vrstu paketa " + paket.getVrstaPaketa());
						} else {
							if (paket.getUslugaDostave().equalsIgnoreCase("H")) {
								return vrsta.getCijenaHitno();
							} else {
								float cijena = (vrsta.getCijenaP()
										* (paket.getVisina() * paket.getSirina() * paket.getDuzina()))
										+ (vrsta.getCijenaT() * paket.getTezina());
								return cijena;
							}

						}

					} else {
						if (paket.getUslugaDostave().equalsIgnoreCase("H")) {
							return vrsta.getCijenaHitno();
						} else {
							return vrsta.getCijena();
						}
					}
				}
			}
			return null;
		}

}
