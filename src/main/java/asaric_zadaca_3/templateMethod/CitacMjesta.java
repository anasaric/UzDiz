package asaric_zadaca_3.templateMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Mjesta;

public class CitacMjesta extends CitacDatoteka{
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	@Override
	protected boolean provjeraInfoRetka(String linija) {
		String regex = "^[ABCČĆDĐDŽEFGHIJKLLJMNNJOPRSŠTUVZŽabcćčdđdžefghijklljmnnjoprsštuvzž,. ]+$";
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
		String regex = "^[0123456789 ]+$";
		return Pattern.matches(regex, podatak);

	}
	@Override
	protected boolean provjera(String linija) {
		int greske = objekti.getRedniBrojPogreske();
		String[] mjesta = linija.split(";");

		if (mjesta.length < 2) {
			objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
			System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
					+ linija + ". Ovaj redak nije ispravan jer ima manjak unesenih informacija");
		} else {

			if ((!provjeriBroj(mjesta[0])) || !provjeraUlica(mjesta[2])) {
				objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
				System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " u retku koji sadrzi "
						+ linija + ". Ovaj redak nije ispravan jer nije unesena potrebna brojcana vrijednost");
			}

			if ((!provjeriString(mjesta[1]))) {
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
	
	private boolean provjeraUlica(String unos) {
		String[] ulice= unos.split(",");
		for(int i = 0; i < ulice.length; i++) {
			if(!provjeriBroj(ulice[i])){
				return false;
			}
		}
		return true;
	}
	@Override
	protected void unosPodataka(String[] mjesto) {
		String[] ulice= mjesto[2].split(",");
		List <Integer> unosUlica  = new ArrayList<>();;
		for(int i = 0; i < ulice.length; i++) {
			unosUlica.add(Integer.parseInt(ulice[i].trim()));
		}
		
		Mjesta naselje = new Mjesta(Integer.parseInt(mjesto[0].trim()), mjesto[1].trim().toString(), unosUlica);
		objekti.getMjesta().add(naselje);
	}
}
