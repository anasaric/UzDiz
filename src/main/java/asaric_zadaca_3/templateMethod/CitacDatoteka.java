package asaric_zadaca_3.templateMethod;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import asaric_zadaca_3.ObjektiSingleton;

public abstract class CitacDatoteka {
	public final void ucitajDatoteku(String nazivDatoteke) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		int brojac = 0;
		try {
			Path datoteka = Paths.get(nazivDatoteke);
			Scanner skener = new Scanner(datoteka);
			while (skener.hasNextLine()) {

				String linija = skener.nextLine();
				if (brojac == 0) {
					if (!provjeraInfoRetka(linija)) {
						objekti.setRedniBrojPogreske(objekti.getRedniBrojPogreske() + 1);
						System.out.println("Redni broj pogeske je " + objekti.getRedniBrojPogreske() + " jer datoteka "
								+ nazivDatoteke + " nema informativni redak");
						Boolean provjera = provjera(linija);
						if (provjera) {
							unosPodataka(linija.split(";"));
						}
					}
				}
				if (brojac > 0) {
					Boolean provjera = provjera(linija);
					if (provjera) {
						unosPodataka(linija.split(";"));
					}
				}
				brojac++;
			}
			skener.close();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage() + "in class " + CitacMjesta.class.toString());
		}
    }

    protected abstract void unosPodataka(String[] split);

	protected abstract boolean provjeraInfoRetka(String linija);

	protected abstract boolean provjera(String linija);
}
