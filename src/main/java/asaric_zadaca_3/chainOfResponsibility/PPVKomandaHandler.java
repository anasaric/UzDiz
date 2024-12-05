package asaric_zadaca_3.chainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.memento.CuvanjeStanjaCaretaker;
import asaric_zadaca_3.memento.PodatciMemento;
import asaric_zadaca_3.memento.UpravljanjeStanjimaOriginator;

public class PPVKomandaHandler implements KomandaHandler {

	CuvanjeStanjaCaretaker cuvanje;
	public PPVKomandaHandler (CuvanjeStanjaCaretaker cuvanje) {
		this.cuvanje = cuvanje;
	}
	
	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandePPV(komanda)) {
			obradiKomanduPPV(komanda);
		}
	}

	private void obradiKomanduPPV(String komanda) {
		String nazivStanja = dohvatiNazivStanja(komanda);
		if (nazivStanja != null) {
			vratiTrazenoStanje(nazivStanja);
		}

	}

	private void vratiTrazenoStanje(String nazivStanja) {
		UpravljanjeStanjimaOriginator upravljanje = new UpravljanjeStanjimaOriginator();
		upravljanje.postaviStanje(nazivStanja);
		PodatciMemento sacuvanaStanja = cuvanje.dohvatiMemento(nazivStanja);
		if(sacuvanaStanja != null) {
			upravljanje.vratiStanje(sacuvanaStanja);
		}
	}

	private static boolean provjeraKomandePPV(String komanda) {
		String regex = "^PPV\\s+['\"]([^'\"]+)['\"]$";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}

	private String dohvatiNazivStanja(String komanda) {
		String[] komandaSplit = komanda.split(" ");
		if (komandaSplit.length == 2) {
			if (komandaSplit[1] != null) {
				return komandaSplit[1];
			}
		} else {
			return komandaSplit[1] + " " + komandaSplit[2];
		}
		return null;
	}

}
