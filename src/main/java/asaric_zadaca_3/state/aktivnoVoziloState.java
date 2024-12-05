package asaric_zadaca_3.state;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.visitor.Vozilo;

public class aktivnoVoziloState implements VozilaState{
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	@Override
	public void promijeniStatus(Vozila vozilo) {
		vozilo.setStatus("A");	
		System.out.println("Vozilu " + vozilo.getRegistracija() + " se postavlja status da je aktivno");
		promijeniStatusVozila(vozilo);
	}
	private void promijeniStatusVozila(Vozila vozila) {
		for (Vozilo vozilo : objekti.getVozilo()) {
			if(vozilo.getRegistracija().equalsIgnoreCase(vozila.getRegistracija())) {
				vozilo.setStatus(vozila.getStatus());
			}
		}
	}


}
