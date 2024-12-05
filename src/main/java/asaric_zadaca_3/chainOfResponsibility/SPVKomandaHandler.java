package asaric_zadaca_3.chainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.memento.CuvanjeStanjaCaretaker;
import asaric_zadaca_3.memento.UpravljanjeStanjimaOriginator;

public class SPVKomandaHandler implements KomandaHandler {
	CuvanjeStanjaCaretaker cuvanje;
	public SPVKomandaHandler (CuvanjeStanjaCaretaker cuvanje) {
		this.cuvanje = cuvanje;
	}
	
	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandeSPV(komanda)) {
			obradiKomanduSPV(komanda);
		}
	}

	private void obradiKomanduSPV(String komanda) {
		String nazivStanja = dohvatiNazivStanja(komanda);
		UpravljanjeStanjimaOriginator upravljanje = new UpravljanjeStanjimaOriginator();
		
		upravljanje.postaviStanje(nazivStanja);
        cuvanje.dodajMemento(nazivStanja, upravljanje.spremiStanje());
	}

	private String dohvatiNazivStanja(String komanda) {
		String[] komandaSplit = komanda.split(" ");
		if(komandaSplit.length == 2) {
			if(komandaSplit[1] != null) {
				return komandaSplit[1];
			}
		} else {
			return komandaSplit[1] + " " + komandaSplit[2];
		}
		return null;
	}

	private static boolean provjeraKomandeSPV(String komanda) {
		String regex = "^SPV\\s+['\"]([^'\"]+)['\"]$";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}

}
