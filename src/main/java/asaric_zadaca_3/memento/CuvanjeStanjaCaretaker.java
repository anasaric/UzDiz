package asaric_zadaca_3.memento;

import java.util.HashMap;
import java.util.Map;

public class CuvanjeStanjaCaretaker {
	private Map<String, PodatciMemento> sacuvanaStanja = new HashMap<>();

	public void dodajMemento(String nazivStanja, PodatciMemento podatci) {
		sacuvanaStanja.put(nazivStanja, podatci);
	}

	public PodatciMemento dohvatiMemento(String nazivStanja) {
		PodatciMemento stanje = sacuvanaStanja.get(nazivStanja);
		if(stanje == null) {
			System.out.println("Uneseno stanje ne postoji!");
			return null;
	}
		return new PodatciMemento(stanje.getVirtualniSatSekunde(), stanje.getvirtualniRadniSat(),stanje.getvirtualniSat() , stanje.getPaketi(), stanje.getVozila());
	}	 
}

