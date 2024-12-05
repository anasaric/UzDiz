package asaric_zadaca_3.chainOfResponsibility;

import asaric_zadaca_3.visitor.ispisPodataka;

public class SVKomandaHandler implements KomandaHandler{
	ispisPodataka podatciOVozilima = new ispisPodataka();
	@Override
	public void izvrsiKomandu(String komanda) {
		if ("SV".equalsIgnoreCase(komanda)) {		
			  podatciOVozilima.ispisKomandaSV();	 
		}
	}

}
