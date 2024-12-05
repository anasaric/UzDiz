package asaric_zadaca_3.observer;


import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Paketi;

public class ObradaKomandePO {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	public ObradaKomandePO(String komanda) {
		String komandaReplace = komanda.replace("'", "");
		String dijeloviNaredbe[] = komandaReplace.split(" ");
		String oznaka = provjeriPaket(dijeloviNaredbe[3]);
		String ime = dijeloviNaredbe[1] + " " + dijeloviNaredbe[2];
		String osoba = provjeriOsobu(ime, dijeloviNaredbe[3]);
		if (oznaka == null || osoba == null) {
			System.out.println(
					"Unesena oznaka vozila " + dijeloviNaredbe[3] + " nije pronađena ili ne sadrzi osobu " + ime);
		} else {
			ObavijestiPaket odabraniPaket = new ObavijestiPaket();
			
			switch (dijeloviNaredbe[4]) {
			case "D":
				if (osoba.equalsIgnoreCase("posiljatelj")) {
					odabraniPaket.obrisiOsobu(ime, oznaka);
					System.out.println("Pošiljatelj " + ime + " ipak želi primati obavijesti za paket " + oznaka);
				} else {
					odabraniPaket.obrisiOsobu(ime, oznaka);
					System.out.println("Primatelj " + ime + " ipak želi primati obavijesti za paket " + oznaka);
				}
				break;
			case "N":
				if (osoba.equalsIgnoreCase("posiljatelj")) {
					odabraniPaket.dodajOsobu(new Osoba(ime, oznaka));
					System.out.println("Pošiljatelj " + ime + " ne želi primati obavijesti za paket " + oznaka);
				} else {
					odabraniPaket.dodajOsobu(new Osoba(ime, oznaka));
					System.out.println("Primatelj " + ime + " ne želi primati obavijesti za paket " + oznaka);
				}
				break;
			default:
				System.out.println("Točan unos je D ili N, a ne  " + dijeloviNaredbe[4]);
			}
		}
	}

	private String provjeriOsobu(String ime, String oznaka) {
		for (Paketi paket : objekti.getPaketi()) {
			if (oznaka.equalsIgnoreCase(paket.getOznaka()) && ime.equalsIgnoreCase(paket.getPosiljatelj())) {
				return "posiljatelj";
			}
			if (oznaka.equalsIgnoreCase(paket.getOznaka()) && ime.equalsIgnoreCase(paket.getPrimatelj())) {
				return "primatelj";
			}
		}
		return null;
	}

	private String provjeriPaket(String oznaka) {
		for (Paketi paket : objekti.getPaketi()) {
			if (oznaka.equalsIgnoreCase(paket.getOznaka())) {
				return paket.getOznaka();
			}
		}
		return null;
	}
}
