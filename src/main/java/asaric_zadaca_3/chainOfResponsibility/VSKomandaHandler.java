package asaric_zadaca_3.chainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.visitor.ispisPodataka;

public class VSKomandaHandler implements KomandaHandler{
	ispisPodataka podatciOVozilima = new ispisPodataka();
	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandeVS(komanda)) {		
			  podatciOVozilima.ispisKomandaVS(komanda); 
		}
	}
	
	private static boolean provjeraKomandeVS(String komanda) {
		String regex = "VS\\s+.+\\s\\d";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}

}
