package asaric_zadaca_3.decorator;

import java.text.SimpleDateFormat;
import java.time.LocalTime;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Paketi;

public class KonkretniIspisTablice implements IspisTablice{

	@Override
	public void ispisTablice() {
		SimpleDateFormat ispis = new SimpleDateFormat("dd.MM.yyyy.");
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		LocalTime trenutnoVrijeme = LocalTime.parse(objekti.getTrenutnoRadnoVrijeme());
		String datum = ispis.format(objekti.getVirtualniSat());
		for (Paketi paket : objekti.getPaketi()) {
			String[] datumPaketa = paket.getVrijemePrijema().split(" ");
			if (datum.equalsIgnoreCase(datumPaketa[0])) {
				if (paket.getdostavljen() == null) {
					System.out.println(paket.getVrijemePrijema() + ", " + paket.getVrstaPaketa() + ", "
							+ paket.getUslugaDostave() + ", " + "Nije isporuceno!" + ", " + paket.getdostavljen() + ", "
							+ paket.getCijena() + ", " + paket.getIznosPouzeca());
				} else {
					LocalTime vrijemeDostave = LocalTime.parse(paket.getdostavljen());

					if (vrijemeDostave.isBefore(trenutnoVrijeme)) {
						System.out.println(paket.getVrijemePrijema() + ", " + paket.getVrstaPaketa() + ", "
								+ paket.getUslugaDostave() + ", " + "Isporuceno!" + ", " + paket.getdostavljen() + ", "
								+ paket.getCijena() + ", " + paket.getIznosPouzeca());
					}
				}
			}

		}
	}
	
}
