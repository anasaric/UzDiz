package asaric_zadaca_3.chainOfResponsibility;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.Pomocnici;
import asaric_zadaca_3.Sat;

public class VRKomandaHandler implements KomandaHandler {

	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandeVR(komanda)) {
			Sat sat = new Sat();
			sat.start();
			postavkeKomandeVR();
		}
	}

	private static boolean provjeraKomandeVR(String komanda) {
		String regex = "^VR (\\d+)";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}

	private static void postavkeKomandeVR() {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		Pomocnici pomoc = new Pomocnici();
		objekti.setKonacnoVrijeme(objekti.getTrenutnoRadnoVrijeme());
		LocalTime vrijemeParse = pomoc.pretvoriStringUTime(objekti.getKonacnoVrijeme());
		String[] provjera = objekti.getKomanda().split(" ");
		objekti.setKonacnoVrijeme(vrijemeParse.plusHours(Long.parseLong(provjera[1])).toString() + ":00");
	}

}
