package asaric_zadaca_3.state;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;

public class obradaKomandePS {
	private VozilaState trenutnoStanje;
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	String komanda;

	public obradaKomandePS(String komanda) {
		this.komanda = komanda;
	}

	public void izvrsiNaredbu() {
		String[] dijeloviKomande = komanda.split(" ");
		String novoStanje = dijeloviKomande[2];
		String imeVozila = dijeloviKomande[1];
		Vozila odabranoVozilo = null;
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getRegistracija().equalsIgnoreCase(imeVozila)) {
				odabranoVozilo = vozilo;
			}
		}

		switch (novoStanje) {
		case "A":
			postaviStatus(new aktivnoVoziloState(), odabranoVozilo);
			return;
		case "NI":
			postaviStatus(new nijeIspravnoVoziloState(), odabranoVozilo);
			return;
		case "NA":
			postaviStatus(new nijeAktivnoVoziloState(), odabranoVozilo);
			return;
		default:
			System.out.println("Uneseno stanje ne postoji!!");
		}
		
	}

	public void postaviStatus(VozilaState novoStanje, Vozila odabranoVozilo) {
		trenutnoStanje = novoStanje;
		trenutnoStanje.promijeniStatus(odabranoVozilo);
	}

}
