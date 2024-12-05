package asaric_zadaca_3;

import java.util.Properties;
import java.util.Scanner;
import asaric_zadaca_3.chainOfResponsibility.KomandeRad;
import asaric_zadaca_3.memento.CuvanjeStanjaCaretaker;
import asaric_zadaca_3.ucitavanjePodataka.ObradaArgumenata;
import asaric_zadaca_3.ucitavanjePodataka.ProvjeraParametara;
import asaric_zadaca_3.ucitavanjePodataka.UcitavanjeParametara;

public class Poduzece {
	public static void main(String[] args) {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		UcitavanjeParametara podatci = new UcitavanjeParametara();
		ProvjeraParametara provjeri = new ProvjeraParametara();
		CuvanjeStanjaCaretaker cuvanje = new CuvanjeStanjaCaretaker();
		
		
		if (args.length == 1) {
			inizijalizacijaSustava(args, podatci, provjeri);
		} else {
			System.out.println("Netocan broj parametara");
		}
		
		long pocetak = objekti.getVirtualniSat().getTime() / 1000;
		objekti.setTrenutnoVrijemeSekunde(pocetak);
		Pomocnici pomocnici = new Pomocnici();
        KomandeRad rad = new KomandeRad();
        pomocnici.inicijalizacijaKomandi(rad, cuvanje);
        pomocnici.postaviSVKomandu();
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String komanda = scanner.nextLine();
            objekti.setKomanda(komanda);

            if ("q".equalsIgnoreCase(komanda)) {
                System.out.println("Izlaz iz programa.");
                break;
            }

            rad.radKomande(komanda);
        }

        scanner.close();
	}

	private static void inizijalizacijaSustava(String[] args, UcitavanjeParametara podatci,
			ProvjeraParametara provjeri) {
		Properties parametri = podatci.ucitajParametre(args[0]);
		if (provjeri.provjeriParametre(parametri)) {
			ObradaArgumenata obrada = new ObradaArgumenata(parametri);
		} else {
			System.exit(0);
		}	
	}
}
