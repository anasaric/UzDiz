package asaric_zadaca_3;

import java.time.LocalTime;

import asaric_zadaca_3.chainOfResponsibility.IPKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.KomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.KomandeRad;
import asaric_zadaca_3.chainOfResponsibility.POKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.PPKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.PPVKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.PSKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.SPVKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.SVKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.VRKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.VSKomandaHandler;
import asaric_zadaca_3.chainOfResponsibility.VVKomandaHandler;
import asaric_zadaca_3.memento.CuvanjeStanjaCaretaker;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.visitor.Vozilo;

public class Pomocnici {
	public LocalTime pretvoriStringUTime(String trenutnoVirtualnoVrijeme) {
		String[] vrijeme = trenutnoVirtualnoVrijeme.split(":");
		if (vrijeme.length >= 3) {
			String sat = vrijeme[0];
			String minute = vrijeme[1];
			String sekunde = vrijeme[2];
			LocalTime vrijemeParse = LocalTime.of(Integer.parseInt(sat), Integer.parseInt(minute),
					Integer.parseInt(sekunde));
			return vrijemeParse;
		} else {
			System.out.println("Krivi unos pretvorbe! U klasi pomocnici! ");
			return null;
		}

	}

	

	public void inicijalizacijaKomandi(KomandeRad rad, CuvanjeStanjaCaretaker cuvanje) {
		KomandaHandler ipKomanda = new IPKomandaHandler();
		KomandaHandler vrKomanda = new VRKomandaHandler();
		KomandaHandler poKomanda = new POKomandaHandler();
		KomandaHandler ppKomanda = new PPKomandaHandler();
		KomandaHandler psKomanda = new PSKomandaHandler();
		KomandaHandler svKomanda = new SVKomandaHandler();
		KomandaHandler vsKomanda = new VSKomandaHandler();
		KomandaHandler vvKomanda = new VVKomandaHandler();
		KomandaHandler spvkomanda = new SPVKomandaHandler(cuvanje);
		KomandaHandler ppvkomanda = new PPVKomandaHandler(cuvanje);

		rad.dodajHandler(ipKomanda);
		rad.dodajHandler(vrKomanda);
		rad.dodajHandler(vvKomanda);
		rad.dodajHandler(poKomanda);
		rad.dodajHandler(ppKomanda);
		rad.dodajHandler(psKomanda);
		rad.dodajHandler(svKomanda);
		rad.dodajHandler(vsKomanda);
		rad.dodajHandler(ppvkomanda);
		rad.dodajHandler(spvkomanda);

	}

	public void postaviSVKomandu() {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		for (Vozila vozilo : objekti.getVozila()) {
			Vozilo novoVozilo = new Vozilo(vozilo.getRegistracija(), vozilo.getStatus(), 0, 0, 0, 0, 0, 0, 0);
			objekti.getVozilo().add(novoVozilo);
		}
	}

}
