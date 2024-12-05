package asaric_zadaca_3.chainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.state.obradaKomandePS;

public class PSKomandaHandler implements KomandaHandler{

	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandePS(komanda)) {			
			  obradaKomandePS obrada = new obradaKomandePS(komanda);
			  obrada.izvrsiNaredbu(); 
		}
		
	}
	private static boolean provjeraKomandePS(String komanda) {
		String regex = "PS\\s+.+\\w+\\s\\w+";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}
	
}
