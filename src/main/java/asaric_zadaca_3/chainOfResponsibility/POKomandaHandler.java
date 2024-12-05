package asaric_zadaca_3.chainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.observer.ObradaKomandePO;

public class POKomandaHandler implements KomandaHandler{

	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandePO(komanda)) {		
			  ObradaKomandePO obrada = new ObradaKomandePO(komanda); 		 
		}
	}
	
	private static boolean provjeraKomandePO(String komanda) {
		String regex = "^PO\\s'\\w+\\s\\w+'\\s\\p{L}+\\d+\\s\\w";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}

}
