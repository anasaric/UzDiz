package asaric_zadaca_3.templateMethod;

import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Vrste;

public class CitacVrste extends CitacDatoteka {
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
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] vrsta = linija.split(";");

		if (vrsta.length > 10) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi " + linija
					+ ". Ovaj redak nije ispravan jer ima previse unesenih informacija");
		}

		else if (vrsta.length < 10) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi " + linija
					+ ". Ovaj redak nije ispravan jer ima manjak unesenih informacija");
		} else {
			if (!vrsta[0].equals("A") && !vrsta[0].equals("B") && !vrsta[0].equals("C") && !vrsta[0].equals("D")
					&& !vrsta[0].equals("E") && !vrsta[0].equals("X")) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer unesena vrsta nije ispravna");
			}
			if (!vrsta[0].equals("X") && (!vrsta[8].equals("0,00") || !vrsta[9].equals("0,00"))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija
						+ ". Ovaj redak nije ispravan jer CijenaP i CijenaT ne smiju bit unesene za vrstu paketa");
			}
			
			if ((!provjeriBroj(vrsta[2])) || (!provjeriBroj(vrsta[3])) || (!provjeriBroj(vrsta[4])) || (!provjeriBroj(vrsta[5])) || (!provjeriBroj(vrsta[6])) || (!provjeriBroj(vrsta[7])) || (!provjeriBroj(vrsta[8])) || (!provjeriBroj(vrsta[9]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesena potrebna brojcana vrijednost");
			}
		}
		if (greske == objekti.getRedniBrojPogreske())
			return true;
		else return false;
	}
	
	private boolean provjeriBroj(String podatak) {
		String regex = "^[0123456789,. ]+$";
		return Pattern.matches(regex, podatak);
	}
	@Override
	protected void unosPodataka(String[] vrsta) {

		for (int i = 0; i < vrsta.length; i++) {
			if (vrsta[i].contains(",")) {
				String[] splitVrsta = vrsta[i].split(",");
				if (splitVrsta.length == 2) {
					vrsta[i] = splitVrsta[0] + "." + splitVrsta[1];
				}
			}
		}

		Vrste vrste = new Vrste(vrsta[0].trim().toString(), vrsta[1].trim().toString(),
				Float.parseFloat(vrsta[2].trim()), Float.parseFloat(vrsta[3].trim()), Float.parseFloat(vrsta[4].trim()),
				Float.parseFloat(vrsta[5].trim()), Float.parseFloat(vrsta[6].trim()), Float.parseFloat(vrsta[7].trim()),
				Float.parseFloat(vrsta[8].trim()), Float.parseFloat(vrsta[9].trim()));
		objekti.getVrste().add(vrste);
	}
}
