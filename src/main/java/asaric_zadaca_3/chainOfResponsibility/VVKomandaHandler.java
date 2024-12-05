package asaric_zadaca_3.chainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.visitor.ispisPodataka;

public class VVKomandaHandler implements KomandaHandler{
	ispisPodataka podatciOVozilima = new ispisPodataka();
	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandeVV(komanda)) {		
			  podatciOVozilima.ispisKomandaVV(komanda); 		 
		}
	}
	
	private static boolean provjeraKomandeVV(String komanda) {
		String regex = "VV\\s+.+";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}
	
}
