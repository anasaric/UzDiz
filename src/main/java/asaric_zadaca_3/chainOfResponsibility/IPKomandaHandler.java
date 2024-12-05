package asaric_zadaca_3.chainOfResponsibility;

import asaric_zadaca_3.Pomocnici;
import asaric_zadaca_3.decorator.CrveniIspisTablice;
import asaric_zadaca_3.decorator.IspisTablice;
import asaric_zadaca_3.decorator.KonkretniIspisTablice;

public class IPKomandaHandler implements KomandaHandler{
	Pomocnici ispis = new Pomocnici();
	@Override
	public void izvrsiKomandu(String komanda) {
		if ("IP".equalsIgnoreCase(komanda)) {
			IspisTablice originalniIspis = new KonkretniIspisTablice();
	        IspisTablice obojenIspis = new CrveniIspisTablice(originalniIspis);
	        
	        obojenIspis.ispisTablice();
		}	
	}

}
