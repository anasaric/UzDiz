package asaric_zadaca_3.templateMethod;

import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.state.UMirovanju;
import asaric_zadaca_3.state.VozilaState;

public class CitacVozila extends CitacDatoteka {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	protected boolean provjeraInfoRetka(String linija) {
		String regex = "^[ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž123456789 ]+$";
		String[] redak = linija.split(";");
		StringBuffer provjera = new StringBuffer();
		for (int i = 0; i < redak.length; i++) {
			provjera.append(redak[i]);
		}
		String str = provjera.toString();
		return Pattern.matches(regex, str);
	}
	
	@Override
	protected void unosPodataka(String[] vozilo) {
		for (int i = 0; i < vozilo.length; i++) {
			if (vozilo[i].contains(",") && i != 6) {
				String[] splitVrsta = vozilo[i].split(",");
				if (splitVrsta.length == 2) {
					vozilo[i] = splitVrsta[0] + "." + splitVrsta[1];
				}
			}
		}

		Vozila vozila = new Vozila(vozilo[0].trim().toString(), vozilo[1].trim().toString(),
				Float.parseFloat(vozilo[2].trim()), Float.parseFloat(vozilo[3].trim()),
				Integer.parseInt(vozilo[4].trim()), Integer.parseInt(vozilo[5].trim()), vozilo[6].trim().toString(),
				vozilo[7].trim().toString(), null);
		VozilaState trenutnoStanje = new UMirovanju();
		trenutnoStanje.promijeniStatus(vozila);
		objekti.getVozila().add(vozila);
	}

	@Override
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] vozila = linija.split(";");

		if (vozila.length > 8) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi " + linija
					+ ". Ovaj redak nije ispravan jer sadrži previse unesenih informacija");
		}

		else if (vozila.length < 8) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi " + linija
					+ ". Ovaj redak nije ispravan jer ima manjak unesenih informacija");
		}
		else{
			if((!provjeriBroj(vozila[2])) || (!provjeriBroj(vozila[3])) || (!provjeriBroj(vozila[4])) || (!provjeriBroj(vozila[5])) || (!provjeriBroj(vozila[6]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesena potrebna brojcana vrijednost");
			}
		}
		if (greske == objekti.getRedniBrojPogreske())
			return true;
		else
			return false;
	}

	private boolean provjeriBroj(String podatak) {
		String regex = "^[0123456789,. ]+$";
		return Pattern.matches(regex, podatak);
	}
}
