package asaric_zadaca_3.strategy;

import java.util.ArrayList;
import java.util.List;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.observer.ObavijestiPaket;
import asaric_zadaca_3.observer.Osoba;
import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;
import asaric_zadaca_3.state.NaDostavi;
import asaric_zadaca_3.state.VozilaState;
import asaric_zadaca_3.visitor.AzuriranjePodatakaVisitor;
import asaric_zadaca_3.visitor.Vozilo;
import asaric_zadaca_3.podatci.Mjesta;
import asaric_zadaca_3.podatci.Osobe;
import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.Podrucja;

public class UkrcavanjePaketa implements VoditeljPaketa {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	public void upravljanjePaketom() {

		for (ZaprimljeniPaketi zaprimljeniPaket : objekti.getZaprimljeniPaketi()) {
			if (!zaprimljeniPaket.getNaDostavi()) {
				if (zaprimljeniPaket.getUslugaDostave().equalsIgnoreCase("H")) {
					if (!(objekti.getUkrcaniPaketi().isEmpty())) {
						if (!(pridruziPaketVozilu(zaprimljeniPaket))) {
							odaberiNovoVozilo(zaprimljeniPaket);
						}
					} else {
						odaberiNovoVozilo(zaprimljeniPaket);
					}
				}

			}
		}
		for (ZaprimljeniPaketi zaprimljeniPaket : objekti.getZaprimljeniPaketi()) {
			if (!zaprimljeniPaket.getNaDostavi()) {
				if (!(objekti.getUkrcaniPaketi().isEmpty())) {
					if (!(pridruziPaketVozilu(zaprimljeniPaket))) {
						odaberiNovoVozilo(zaprimljeniPaket);
					}
				} else {
					odaberiNovoVozilo(zaprimljeniPaket);
				}
			}
		}
	}

	private boolean pridruziPaketVozilu(ZaprimljeniPaketi zaprimljeniPaket) {
		int podrucje = otkrijPodrucjePaketa(zaprimljeniPaket);
		boolean podrucjePridruzeno = false;
		if (podrucje != 0) {

			UkrcaniPaketi noviPaket = null;
			for (UkrcaniPaketi ukrcanPaket : objekti.getUkrcaniPaketi()) {
				if (ukrcanPaket.getPodrucje() == podrucje) {
					boolean provjeriVozilo = provjeriVozilo(ukrcanPaket.getOznakaVozila());
					if (provjeriVozilo) {
						float tezina = dohvatiTezinuVozila(ukrcanPaket.getOznakaVozila());
						float obujam = dohvatiKapacitetVozila(ukrcanPaket.getOznakaVozila());
						if ((obujam > ukrcanPaket.getSumaObujma() + zaprimljeniPaket.getObujam())
								&& tezina > ukrcanPaket.getSumaTezina() + zaprimljeniPaket.getTezina()) {
							podrucjePridruzeno = true;
							noviPaket = ukrcanPaket;
						} else {
							pokreniDostavu(ukrcanPaket);
						}
					}
				}
			}

			if (podrucjePridruzeno) {
				zaprimljeniPaket.setNaDostavi(true);
				noviPaket.setSumaCijena(noviPaket.getSumaCijena() + zaprimljeniPaket.getIznosDostave());
				noviPaket.setSumaObujma(noviPaket.getSumaObujma() + zaprimljeniPaket.getObujam());
				noviPaket.setSumaTezina(noviPaket.getSumaTezina() + zaprimljeniPaket.getTezina());
				noviPaket.dodajUkrcaniPaket(zaprimljeniPaket);
				obavijestOUkrcanomPaketu(zaprimljeniPaket);
			}
		}

		if (podrucjePridruzeno)
			return true;
		else
			return false;
	}

	private void pokreniDostavu(UkrcaniPaketi ukrcanPaket) {

		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(ukrcanPaket.getOznakaVozila())
					&& vozilo.getDostava().equalsIgnoreCase("u mirovanju")) {
				VozilaState trenutnoStanje = new NaDostavi();
				trenutnoStanje.promijeniStatus(vozilo);
			}
		}
	}

	private boolean provjeriVozilo(String oznakaVozila) {
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(oznakaVozila)
					&& vozilo.getDostava().equalsIgnoreCase("u mirovanju")) {
				;
				return true;
			}
		}
		return false;
	}

	private void odaberiNovoVozilo(ZaprimljeniPaketi zaprimljeniPaket) {
		int podrucje = otkrijPodrucjePaketa(zaprimljeniPaket);
		Vozila odabranoVozilo = null;
		int odabraniRang = 100;
		if (podrucje != 0) {
			for (Vozila vozilo : objekti.getVozila()) {
				if (vozilo.getStatus().equalsIgnoreCase("A") && vozilo.getDostava().equalsIgnoreCase("u mirovanju")) {
					String[] rangoviVozila = vozilo.getPodrucjaPoRangu().split(",");
					int brojac = 0;
					for (String rang : rangoviVozila) {
						if (Integer.parseInt(rang) == podrucje && brojac < odabraniRang) {
							odabranoVozilo = vozilo;
							odabraniRang = brojac;
						}
						brojac++;
					}
				}
			}
			if (odabranoVozilo != null) {
				zaprimljeniPaket.setNaDostavi(true);
				int brojVoznja = dohvatiBrojRadaVozila(odabranoVozilo.getRegistracija());
				UkrcaniPaketi paket = new UkrcaniPaketi(odabranoVozilo.getRegistracija(), brojVoznja,
						odabranoVozilo.getStatus(), podrucje, zaprimljeniPaket.getObujam(),
						zaprimljeniPaket.getTezina(), zaprimljeniPaket.getIznosDostave(), false, zaprimljeniPaket);
				objekti.getUkrcaniPaketi().add(paket);
				obavijestOUkrcanomPaketu(zaprimljeniPaket);
				azurirajPodatkeOVozilu(paket, zaprimljeniPaket);
			}
		}
	}

	private int dohvatiBrojRadaVozila(String OznakaVozila) {
		int brojac = 1;
		for (UkrcaniPaketi paket : objekti.getUkrcaniPaketi()) {
			if (paket.getOznakaVozila().equals(OznakaVozila)) {
				brojac++;
			}
		}
		return brojac;
	}

	private void azurirajPodatkeOVozilu(UkrcaniPaketi paket, ZaprimljeniPaketi zaprimljeniPaket) {
		String status = null;
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(paket.getOznakaVozila())) {
				status = vozilo.getStatus();
			}
		}
		Vozilo vozilo = new Vozilo(paket.getOznakaVozila(), status, 0, 0, 0, 0, 0, 0, 1);
		AzuriranjePodatakaVisitor visitor = new AzuriranjePodatakaVisitor();
		vozilo.accept(visitor);
	}

	private int otkrijPodrucjePaketa(ZaprimljeniPaketi zaprimljeniPaket) {
		Boolean pronadenPrimatelj = false;
		for (Osobe osoba : objekti.getOsobe()) {
			if (osoba.getOsoba().equalsIgnoreCase(zaprimljeniPaket.getPrimatelj())) {
				pronadenPrimatelj = true;
				for (Podrucja podrucje : objekti.getPodrucja()) {
					for (String redakMjestaUlice : podrucje.getGradUlica()) {
						String mjestaUlice = redakMjestaUlice.replace(" ", "");
						String[] mjestaIUliceRazdvojeni = mjestaUlice.split(":");
						if (osoba.getGrad() == Integer.parseInt(mjestaIUliceRazdvojeni[0])) {
							if ("*".equalsIgnoreCase(mjestaIUliceRazdvojeni[1])) {
								List<Integer> listaUlica = dodajSveUlice(Integer.parseInt(mjestaIUliceRazdvojeni[0]));
								for (Integer redUlica : listaUlica) {
									if (redUlica == osoba.getUlica()) {
										return podrucje.getId();
									}
								}
							} else if (Integer.parseInt(mjestaIUliceRazdvojeni[1]) == osoba.getUlica()) {
								return podrucje.getId();
							}
						}
					}
				}
			}
		}
		if (!pronadenPrimatelj && oznaciPaketObraden(zaprimljeniPaket)) {
			System.out.println("Za primatelja " + zaprimljeniPaket.getPrimatelj() + " paketa "
					+ zaprimljeniPaket.getOznaka() + " ne postoje informacije u .csv datoteci!");
			oznaciPaketObraden(zaprimljeniPaket);
		}
		return 0;
	}

	private boolean oznaciPaketObraden(ZaprimljeniPaketi zaprimljeniPaket) {
		for (Paketi paket : objekti.getPaketi()) {
			if (paket.getOznaka().equals(zaprimljeniPaket.getOznaka()) && !paket.isObraden()) {
				paket.setObraden(true);
				return true;
			}
		}
		return false;
	}

	private List<Integer> dodajSveUlice(int idMjesta) {
		List<Integer> listaUlica = new ArrayList<>();

		for (Mjesta redMjesta : objekti.getMjesta()) {
			if (redMjesta.getId() == idMjesta) {
				listaUlica = redMjesta.getUlice();
			}
		}
		return listaUlica;
	}

	private void obavijestOUkrcanomPaketu(ZaprimljeniPaketi zaprimljeniPaket) {
		ObavijestiPaket obavijest = new ObavijestiPaket();
		if (!obavijest.sadrziOsobu(zaprimljeniPaket.getPosiljatelj(), zaprimljeniPaket.getOznaka())) {
			obavijest.obavijestiOUkrcanomPaketu(
					new Osoba(zaprimljeniPaket.getPosiljatelj(), zaprimljeniPaket.getOznaka()));
		}
		if (!obavijest.sadrziOsobu(zaprimljeniPaket.getPrimatelj(), zaprimljeniPaket.getOznaka())) {
			obavijest.obavijestiOUkrcanomPaketu(
					new Osoba(zaprimljeniPaket.getPrimatelj(), zaprimljeniPaket.getOznaka()));
		}
	}

	private float dohvatiKapacitetVozila(String oznakaVozila) {
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(oznakaVozila)) {
				return vozilo.getKapacitetProstora();
			}
		}
		return 0;
	}

	private float dohvatiTezinuVozila(String oznakaVozila) {
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(oznakaVozila)) {
				return vozilo.getKapacitetTezine();
			}
		}
		return 0;
	}
}
