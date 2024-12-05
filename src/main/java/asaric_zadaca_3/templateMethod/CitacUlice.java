package asaric_zadaca_3.templateMethod;

import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Ulice;

public class CitacUlice extends CitacDatoteka {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	protected boolean provjeraInfoRetka(String linija) {
		String regex = "^[ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž12_ ]+$";
		String[] redak = linija.split(";");
		StringBuffer provjera = new StringBuffer();
		for (int i = 0; i < redak.length; i++) {
			provjera.append(redak[i]);
		}
		String str = provjera.toString();
		return Pattern.matches(regex, str);
	}
	@Override
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] ulica = linija.split(";");

		if (ulica.length > 7) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer ima previse unesenih informacija");
		}

		else if (ulica.length < 7) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer ima manjak unesenih informacija");
		} else {
			if ((!provjeriBroj(ulica[0])) || (!provjeriBroj(ulica[2])) || (!provjeriBroj(ulica[3]))
					|| (!provjeriBroj(ulica[4])) || (!provjeriBroj(ulica[5])) || (!provjeriBroj(ulica[6]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesena potrebna brojcana vrijednost");
			}

			if ((!provjeriString(ulica[1]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesen dobar string");
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

	private boolean provjeriString(String podatak) {
		String regex = "^[-ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzžxyz. ]+$";
		return Pattern.matches(regex, podatak);

	}
	@Override
	protected void unosPodataka(String[] ulica) {

		for (int i = 0; i < ulica.length; i++) {
			if (ulica[i].contains(",")) {
				String[] splitVrsta = ulica[i].split(",");
				if (splitVrsta.length == 2) {
					ulica[i] = splitVrsta[0] + "." + splitVrsta[1];
				}
			}
		}

		Ulice ulice = new Ulice(Integer.parseInt(ulica[0].trim()), ulica[1].trim().toString(),
				Float.parseFloat(ulica[2].trim()), Float.parseFloat(ulica[3].trim()), Float.parseFloat(ulica[4].trim()),
				Float.parseFloat(ulica[5].trim()), Integer.parseInt(ulica[6].trim()));
		objekti.getUlice().add(ulice);
	}
}
