package asaric_zadaca_3.templateMethod;

import java.util.Iterator;
import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.Vrste;

public class CitacPaketi extends CitacDatoteka {
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	
	@Override
	protected void unosPodataka(String[] paket) {

		for (int i = 0; i < paket.length; i++) {
			if (paket[i].contains(",")) {
				String[] splitVrsta = paket[i].split(",");
				if (splitVrsta.length == 2) {
					paket[i] = splitVrsta[0] + "." + splitVrsta[1];
				}
			}
		}

		Paketi paketi = new Paketi(paket[0].trim().toString(), paket[1].trim().toString(), paket[2].trim().toString(),
				paket[3].trim().toString(), paket[4].trim().toString(), Float.parseFloat(paket[5].trim()),
				Float.parseFloat(paket[6].trim()), Float.parseFloat(paket[7].trim()), Float.parseFloat(paket[8].trim()), 0,
				paket[9].trim().toString(), Float.parseFloat(paket[10].trim()), false, null);
		objekti.getPaketi().add(paketi);

	}

	@Override
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] paketi = linija.split(";");
		if (paketi.length > 11) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer sadrži previse unesenih informacija");
		}

		else if (paketi.length < 11) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer ima manjak unesenih informacija u datoteci");
		} else {
			if (!paketi[4].equals("A") && !paketi[4].equals("B") && !paketi[4].equals("C") && !paketi[4].equals("D")
					&& !paketi[4].equals("E") && !paketi[4].equals("X")) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer unesena vrsta paketa nije ispravna");
			}
			if (!paketi[4].equals("X")
					&& (!paketi[5].equals("0,0") || !paketi[6].equals("0,0") || !paketi[7].equals("0,0"))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija
						+ ". Ovaj redak nije ispravan jer visina, širina i dužina ne smiju bit unesene za vrstu paketa");
			}
			if (paketi[4].equals("X")
					&& (paketi[5].equals("0,0") || paketi[6].equals("0,0") || paketi[7].equals("0,0"))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija
						+ ". Ovaj redak nije ispravan jer visina, širina i dužina moraju bit unesene za vrstu paketa");
			}
			if (!paketi[9].equals("S") && !paketi[9].equals("H") && !paketi[9].equals("P") && !paketi[9].equals("R")) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer unesena usluga dostave nije ispravna");
			}
			if ((!provjeriString(paketi[2])) || (!provjeriString(paketi[3]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer je unesena brojcana vrijednost na mjesto gdje je potreban string");
			}
			if ((!provjeriBroj(paketi[6])) || (!provjeriBroj(paketi[7])) || (!provjeriBroj(paketi[8])) || (!provjeriBroj(paketi[5])) || (!provjeriBroj(paketi[10]))) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesena potrebna brojcana vrijednost");
			}
		}
		provjeraTezinePaketa(linija, greske);
		if (greske == objekti.getRedniBrojPogreske())
			return true;
		else
			return false;
	}
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
	
	private boolean provjeriString(String podatak) {
		String regex = "^[ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž. ]+$";
		return Pattern.matches(regex, podatak);
		
	}
	private boolean provjeriBroj(String podatak) {
		String regex = "^[0123456789,. ]+$";
		return Pattern.matches(regex, podatak);
		
	}

	private void provjeraTezinePaketa(String linija, int greske) {
		String[] paketi = linija.split(";");
		for (int i = 0; i < paketi.length; i++) {
			if (paketi[i].contains(",")) {
				String[] splitVrsta = paketi[i].split(",");
				if (splitVrsta.length == 2) {
					paketi[i] = splitVrsta[0] + "." + splitVrsta[1];
				}
			}
		}
		for (Iterator<Vrste> i = objekti.getVrste().iterator(); i.hasNext();) {
			Vrste vrsta = i.next();
			if (paketi[4].equals(vrsta.getOznaka()) && (Float.parseFloat(paketi[8]) > vrsta.getMaksimalnaTezina())
					&& !paketi[4].equals("X")) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija
						+ ". Ovaj redak nije ispravan jer unesena tezina paketa veca od maksimalne tezine paketa.");
			}
		}
	}
}
