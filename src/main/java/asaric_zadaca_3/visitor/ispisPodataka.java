package asaric_zadaca_3.visitor;

import asaric_zadaca_3.ObjektiSingleton;

public class ispisPodataka {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	public void ispisKomandaSV() {
		System.out.println(
				"Registracija\tStatus\tUkupnoKM\tBr. Hitnih paketa\tBr.Obicnih Paketa\tBr.Isporucenih Paketa\t% Prostora\t% Tezine\tBr. Voznji");
		System.out.println("-".repeat(100));
		for (Vozilo vozilo : objekti.getVozilo()) {
			String zaokruzenBroj = String.format("%.3f", vozilo.getUkupnoKilometara());
			String zaokruzenBrojProstor = String.format("%.3f", vozilo.getTrenutniPostotakZauzetogProstora());
			String zaokruzenBrojTezine = String.format("%.3f", vozilo.getTrenutniPostotakZauzeteTezine());
			System.out.println(vozilo.getRegistracija() + "\t\t" + vozilo.getStatus() + "\t" + zaokruzenBroj + "\t\t\t"
					+ vozilo.getBrojHitnihPaketa() + "\t\t\t" + vozilo.getBrojObicnihPaketa() + "\t\t\t"
					+ vozilo.getBrojIsporucenihPaketa() + "\t\t" + zaokruzenBrojProstor + "\t\t"
					+ zaokruzenBrojTezine + "\t\t" + vozilo.getBrojVoznji());
		}
	}

	public void ispisKomandaVV(String komanda) {
		String[] trazenoVozilo = komanda.split(" ");
		System.out.println(
				"Registracija\tBroj Voznje\tVrijeme pocetka\t\tVrijeme povratka\tTrajanje\tKilometri\t% Br. Hitnih paketa\tBr. Obicnih paketa\tBr. IsporucenihPaketa\t% Zauzetog prostora\t%Zauzete tezine");
		System.out.println("-".repeat(200));
		for (Voznja voznja : objekti.getVoznja()) {
			if(voznja.getBrojVoznje() != 0 && trazenoVozilo[1].equalsIgnoreCase(voznja.getRegistracija())) {
				String zaokruzenBroj = String.format("%.5f", voznja.getUkupnoKilometara());
				System.out.println(voznja.getRegistracija() + "\t\t\t" + voznja.getBrojVoznje() + "\t\t"
						+ voznja.getVrijemePocetka() + "\t\t\t" + voznja.getVrijemePovratka() + "\t\t" + voznja.getTrajanje()
						+ "\t\t" + zaokruzenBroj + "\t\t\t" + voznja.getBrojHitnihPaketa() + "\t\t\t"
						+ voznja.getBrojObicnihPaketa() + "\t\t\t" + voznja.getBrojIsporucenihPaketa() + "\t\t"
						+ voznja.getPocetniPostotakZauzetogProstora() + "\t\t" + voznja.getPocetniPostotakZauzeteTezine());
			}
		}
	}

	public void ispisKomandaVS(String komanda) {
		String[] trazeniSegment = komanda.split(" ");
		System.out.println(
				"Registracija\tBroj Voznje\tVrijeme pocetka\t\tVrijeme povratka\tTrajanje\tKilometri\tOznaka");
		System.out.println("-".repeat(120));
		for (Segmenti segment : objekti.getSegmenti()) {
			if(segment.getBrojVoznje() == Integer.parseInt(trazeniSegment[2]) &&trazeniSegment[1].equalsIgnoreCase(segment.getRegistracija())) {
				String zaokruzenBroj = String.format("%.3f", segment.getUkupnoKilometara());
				System.out.println(segment.getRegistracija() + "\t\t\t" + segment.getBrojVoznje() + "\t"
						+ segment.getVrijemePocetka() + "\t\t" + segment.getVrijemePovratka() + "\t\t" + segment.getTrajanje()
						+ "\t\t" + zaokruzenBroj.trim() + "\t\t" + segment.getOznakaPaketa().trim());
			}
		}
	}
}
