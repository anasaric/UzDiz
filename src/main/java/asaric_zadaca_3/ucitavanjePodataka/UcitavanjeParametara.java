package asaric_zadaca_3.ucitavanjePodataka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UcitavanjeParametara {
	public static Properties ucitajParametre(String nazivDatoteke) {
		Properties parametri = new Properties();

		try (BufferedReader reader = new BufferedReader(new FileReader(nazivDatoteke))) {
			String linija;
			while ((linija = reader.readLine()) != null) {
				String[] dijelovi = linija.split("=", 2);
				if (dijelovi.length == 2) {
					if(!dijelovi[0].contains("vs")) {
						parametri.setProperty(dijelovi[0].trim().replace(" ", ""), dijelovi[1].trim().replace(" ", ""));
					} else {
						parametri.setProperty(dijelovi[0].trim(), dijelovi[1].trim());
					}
					
				} else {
					System.out.println(linija + " linija nije dobro unesena!");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return parametri;
	}
}
