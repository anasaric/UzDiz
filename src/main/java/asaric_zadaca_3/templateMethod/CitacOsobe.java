package asaric_zadaca_3.templateMethod;

import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Osobe;

public class CitacOsobe extends CitacDatoteka {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	
	@Override
	protected boolean provjeraInfoRetka(String linija) {
		String regex = "^[ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž ]+$";
		String[] redak = linija.split(";");
		StringBuffer provjera = new StringBuffer();
		for (int i = 0; i < redak.length; i++) {
			provjera.append(redak[i]);
		}
		String str = provjera.toString();
		return Pattern.matches(regex, str);
	}

	private boolean provjeriString(String podatak) {
		String regex = "^[ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž. ]+$";
		return Pattern.matches(regex, podatak);

	}

	private boolean provjeriBroj(String podatak) {
		String regex = "^[0123456789,. ]+$";
		return Pattern.matches(regex, podatak);

	}
	@Override
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] osobe = linija.split(";");

		if (osobe.length > 4) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer sadrži previse unesenih informacija");
		} else if (osobe.length < 4) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer ima manjak unesenih informacija");
		} else {

			if ((!provjeriBroj(osobe[1])) || (!provjeriBroj(osobe[2])) || (!provjeriBroj(osobe[3]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesena potrebna brojcana vrijednost");
			}

			if ((!provjeriString(osobe[0]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer ne sadrzi potpuni string");
			}
		}
		if (greske == objekti.getRedniBrojPogreske())
			return true;
		else
			return false;
	}
	@Override
	protected void unosPodataka(String[] osoba) {
		Osobe osobe = new Osobe(osoba[0].trim().toString(), Integer.parseInt(osoba[1].trim()),
				Integer.parseInt(osoba[2].trim()), Integer.parseInt(osoba[3].trim()));
		objekti.getOsobe().add(osobe);
	}
}
