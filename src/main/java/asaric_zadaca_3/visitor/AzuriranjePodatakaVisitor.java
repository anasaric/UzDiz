package asaric_zadaca_3.visitor;

import java.time.Duration;

import asaric_zadaca_3.ObjektiSingleton;

public class AzuriranjePodatakaVisitor implements VoznjeVisitor {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	public void azurirajVozilo(Vozilo vozilo) {
		int indeks = -1;
		for (int i = 0; i < objekti.getVozilo().size(); i++) {
			if (objekti.getVozilo().get(i).getRegistracija().equals(vozilo.getRegistracija())) {
				indeks = i;
				break;
			}
		}
		if (indeks != -1) {
			Vozilo azuriranoVozilo = objekti.getVozilo().get(indeks);
			if (vozilo.getStatus() != null) {
				azuriranoVozilo.setStatus(vozilo.getStatus());
			}
			azuriranoVozilo.setUkupnoKilometara(vozilo.getUkupnoKilometara() + azuriranoVozilo.getUkupnoKilometara());
			azuriranoVozilo.setBrojHitnihPaketa(vozilo.getBrojHitnihPaketa() + azuriranoVozilo.getBrojHitnihPaketa());
			azuriranoVozilo
					.setBrojObicnihPaketa(vozilo.getBrojObicnihPaketa() + azuriranoVozilo.getBrojObicnihPaketa());
			azuriranoVozilo.setBrojIsporucenihPaketa(
					vozilo.getBrojHitnihPaketa() + azuriranoVozilo.getBrojObicnihPaketa());
			if (vozilo.getTrenutniPostotakZauzeteTezine() != 0 && vozilo.getTrenutniPostotakZauzetogProstora() != 0) {
				azuriranoVozilo.setTrenutniPostotakZauzetogProstora(vozilo.getTrenutniPostotakZauzetogProstora());
				azuriranoVozilo.setTrenutniPostotakZauzeteTezine(vozilo.getTrenutniPostotakZauzeteTezine());
			}			
			azuriranoVozilo.setBrojVoznji(vozilo.getBrojVoznji() + azuriranoVozilo.getBrojVoznji());
		} else {
			Vozilo novoVozilo = new Vozilo(vozilo.getRegistracija(), vozilo.getStatus(), vozilo.getUkupnoKilometara(),
					vozilo.getBrojHitnihPaketa(), vozilo.getBrojObicnihPaketa(), vozilo.getBrojIsporucenihPaketa(),
					vozilo.getTrenutniPostotakZauzetogProstora(), vozilo.getTrenutniPostotakZauzeteTezine(),
					vozilo.getBrojVoznji());
			objekti.getVozilo().add(novoVozilo);
		}
	}

	@Override
	public void azurirajVoznju(Voznja voznja) {
		int indeks = -1;

		for (int i = 0; i < objekti.getVoznja().size(); i++) {
			if (objekti.getVoznja().get(i).getRegistracija().equals(voznja.getRegistracija())
					&& objekti.getVoznja().get(i).getBrojVoznje() == voznja.getBrojVoznje()) {
				indeks = i;
			}
		}
		if (indeks != -1) {
			Voznja azuriranaVoznja = objekti.getVoznja().get(indeks);
			if (voznja.getVrijemePocetka() != null) {
				azuriranaVoznja.setVrijemePocetka(voznja.getVrijemePocetka());
			} else if (voznja.getVrijemePovratka() != null) {
				azuriranaVoznja.setVrijemePocetka(voznja.getVrijemePovratka());
			} else if (voznja.getPocetniPostotakZauzeteTezine() != 0) {
				azuriranaVoznja.setPocetniPostotakZauzeteTezine(voznja.getPocetniPostotakZauzeteTezine());
			} else if (voznja.getPocetniPostotakZauzetogProstora() != 0) {
				azuriranaVoznja.setPocetniPostotakZauzetogProstora(voznja.getPocetniPostotakZauzetogProstora());
			}
			azuriranaVoznja.setUkupnoKilometara(azuriranaVoznja.getUkupnoKilometara() + voznja.getUkupnoKilometara());
			azuriranaVoznja.setBrojHitnihPaketa(azuriranaVoznja.getBrojHitnihPaketa() + voznja.getBrojHitnihPaketa());
			azuriranaVoznja
					.setBrojObicnihPaketa(azuriranaVoznja.getBrojObicnihPaketa() + voznja.getBrojObicnihPaketa());
			azuriranaVoznja.setBrojIsporucenihPaketa(
					azuriranaVoznja.getBrojHitnihPaketa() + voznja.getBrojObicnihPaketa());
			if(voznja.getVrijemePocetka() != null && voznja.getVrijemePovratka() != null) {
				Duration trajanje = Duration.between(voznja.getVrijemePocetka(), voznja.getVrijemePovratka());
				azuriranaVoznja.setTrajanje(trajanje.toMinutes());
			}
		} else {
			Voznja novaVoznja = new Voznja(voznja.getRegistracija(), voznja.getBrojVoznje(), voznja.getVrijemePocetka(),
					voznja.getVrijemePovratka(), voznja.getTrajanje(), voznja.getUkupnoKilometara(),
					voznja.getBrojHitnihPaketa(), voznja.getBrojObicnihPaketa(), voznja.getBrojIsporucenihPaketa(),
					voznja.getPocetniPostotakZauzetogProstora(), voznja.getPocetniPostotakZauzeteTezine());
			objekti.getVoznja().add(novaVoznja);
		}
	}

	@Override
	public void azurirajSegment(Segmenti segmenti) {
		if (segmenti.isDostavljen()) {
			segmenti.setDostavljen(true);
		} else {
			Segmenti noviSegment = new Segmenti(segmenti.getRegistracija(), segmenti.getBrojVoznje(),
					segmenti.getVrijemePocetka(), segmenti.getVrijemePovratka(), segmenti.getTrajanje(),
					segmenti.getUkupnoKilometara(), segmenti.getOznakaPaketa(), segmenti.isDostavljen());
			objekti.getSegmenti().add(noviSegment);
		}
	}
}
