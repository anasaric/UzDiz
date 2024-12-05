package asaric_zadaca_3.templateMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Podrucja;

public class CitacPodrucja extends CitacDatoteka {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	protected boolean provjeraInfoRetka(String linija) {
		String regex = "^[,.:*ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž ]+$";
		String[] redak = linija.split(";");
		StringBuffer provjera = new StringBuffer();
		for (int i = 0; i < redak.length; i++) {
			provjera.append(redak[i]);
		}
		String str = provjera.toString();
		return Pattern.matches(regex, str);
	}

	private boolean provjeriBroj(String podatak) {
		String regex = "^[*0123456789 ]+$";
		return Pattern.matches(regex, podatak);

	}
	@Override
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] podrucja = linija.split(";");

		if (podrucja.length < 2) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer ima manjak unesenih informacija");
		} else {

			if ((!provjeriBroj(podrucja[0])) || !provjeraGrada(podrucja[1].trim())) {
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

	private boolean provjeraGrada(String unos) {
		String[] podrucje = unos.split(",");
		for (int i = 0; i < podrucje.length; i++) {
			String[] gradUlica = podrucje[i].split(":");
			if (gradUlica.length == 2) {
				if (!provjeriBroj(gradUlica[0]) || !provjeriBroj(gradUlica[1])) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	@Override
	protected void unosPodataka(String[] podrucje) {
		String[] gradUlica= podrucje[1].split(",");
		List <String> unosgradaUlica  = new ArrayList<>();;
		for(int i = 0; i < gradUlica.length; i++) {
			unosgradaUlica.add(gradUlica[i].trim());
		}

		Podrucja podrucja = new Podrucja(Integer.parseInt(podrucje[0].trim()), unosgradaUlica);
		objekti.getPodrucja().add(podrucja);
	}

}
