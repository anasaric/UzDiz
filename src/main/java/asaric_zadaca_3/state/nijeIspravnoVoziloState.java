package asaric_zadaca_3.state;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.visitor.Vozilo;

public class nijeIspravnoVoziloState implements VozilaState{
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	@Override
	public void promijeniStatus(Vozila vozilo) {
		vozilo.setStatus("NI");
		System.out.println("Vozilu " + vozilo.getRegistracija() + " se postavlja status da nije ispravno");
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
