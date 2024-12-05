package asaric_zadaca_3.ucitavanjePodataka;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vrste;
import asaric_zadaca_3.templateMethod.Objekti;
import asaric_zadaca_3.templateMethod.ObjektiFactory;

public class ObradaArgumenata {
	
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	public ObradaArgumenata(Properties parametri) {
		String pretvorba = parametri.toString().replace('{', ' ').replace('}', ' ');
		String[] komanda = pretvorba.split(", ");
		
		ucitavanjeDatoteka(komanda);
		ucitavanjeParametaraZaRadSustava(komanda);	
	}

	private void ucitavanjeParametaraZaRadSustava(String[] komanda) {
		for (int i = 0; i < komanda.length; i++) {
			if (komanda[i].startsWith("vs")) {
				try {
					objekti.setVirtualniSat(new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss")
							.parse(komanda[i].substring(komanda[i].indexOf("=") + 1)));
					objekti.setTrenutnoRadnoVrijeme(sdf.format(new Date(objekti.getVirtualniSat().getTime() / 1000 * 1000)));
					objekti.setProsloRadnoVrijeme(objekti.getTrenutnoRadnoVrijeme());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (komanda[i].startsWith("ms")) {
				objekti.setMnoziteljSekundi(Integer.parseInt(komanda[i].substring(komanda[i].indexOf("=") + 1)));
			}

			if (komanda[i].startsWith("mt")) {
				unosTezineZaX(komanda[i].substring(komanda[i].indexOf("=") + 1), objekti);
			}
			if (komanda[i].startsWith("pr")) {
				objekti.setPocetakRadnogVremena(LocalTime.parse(komanda[i].substring(komanda[i].indexOf("=") + 1)));
			}
			if (komanda[i].startsWith("kr")) {
				objekti.setKrajRadnogVremena(LocalTime.parse(komanda[i].substring(komanda[i].indexOf("=") + 1)));
			}
			if (komanda[i].startsWith("isporuka")) {
				objekti.setIsporuka(Integer.parseInt(komanda[i].substring(komanda[i].indexOf("=") + 1)));
			}
			if (komanda[i].startsWith("gps")) {
				objekti.setGps(komanda[i].substring(komanda[i].indexOf("=") + 1));
			}
		}
		
	}

	private void ucitavanjeDatoteka(String[] komanda) {
		for (int i = 0; i < komanda.length; i++) {
			komanda[i] = komanda[i].trim();

			if ((komanda[i].startsWith("vp")) || komanda[i].startsWith("pp") || komanda[i].startsWith("pv")
					|| komanda[i].startsWith("po") || komanda[i].startsWith("pm") || komanda[i].startsWith("pmu") || komanda[i].startsWith("pu")) {
				try {
					Objekti kreiraj = new ObjektiFactory();
					kreiraj.ucitaj(komanda[i].substring(0, komanda[i].indexOf("=")), komanda[i].substring(komanda[i].indexOf("=") + 1));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (komanda[i].startsWith("vi")) {
				objekti.setVrijemeIsporuke(Integer.parseInt(komanda[i].substring(komanda[i].indexOf("=") + 1)));
			}
		}
		
	}

	private void unosTezineZaX(String maksimalnaTezina, ObjektiSingleton objekti) {
		for (Iterator<Vrste> i = objekti.getVrste().iterator(); i.hasNext();) {
			Vrste item = i.next();
			if (item.getOznaka().equals("X")) {
				item.setMaksimalnaTezina(Float.parseFloat(maksimalnaTezina));
			}
		}

	}
}
