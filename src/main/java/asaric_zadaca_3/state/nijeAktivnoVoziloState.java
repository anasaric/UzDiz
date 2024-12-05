package asaric_zadaca_3.state;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.visitor.Vozilo;

public class nijeAktivnoVoziloState implements VozilaState{
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();


	private void promijeniStatusVozila(Vozila vozila) {
		for (Vozilo vozilo : objekti.getVozilo()) {
			if(vozilo.getRegistracija().equalsIgnoreCase(vozila.getRegistracija())) {
				vozilo.setStatus(vozila.getStatus());
			}
		}
	}

	@Override
	public void promijeniStatus(Vozila vozilo) {
		vozilo.setStatus("NA");
		System.out.println("Vozilu " + vozilo.getRegistracija() + " se postavlja status da nije aktivno");
		promijeniStatusVozila(vozilo);
	}

}
