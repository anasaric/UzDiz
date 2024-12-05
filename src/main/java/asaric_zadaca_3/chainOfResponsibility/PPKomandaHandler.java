package asaric_zadaca_3.chainOfResponsibility;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.composite.ObradaKomandePP;
import asaric_zadaca_3.composite.PodrucjeComposite;
import asaric_zadaca_3.composite.ProxyPodrucje;
import asaric_zadaca_3.podatci.Podrucja;

public class PPKomandaHandler implements KomandaHandler{
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	@Override
	public void izvrsiKomandu(String komanda) {
		if (provjeraKomandePP(komanda)) {
			ProxyPodrucje proxyPodrucje = new ProxyPodrucje(objekti.getPodrucja(), komanda);
			proxyPodrucje.ispisTablice();
		}
	}
	
	private static void postavkeKomandePP() {
		ObjektiSingleton objekti = ObjektiSingleton.getInstance();
		System.out.println("pp komanda se izvrsava u vrijeme: " + objekti.getTrenutnoRadnoVrijeme());
		List<Podrucja> unosPodrucja = objekti.getPodrucja();
		List<PodrucjeComposite> podrucja = null;
		for (Podrucja unos : unosPodrucja) {
			ObradaKomandePP obrada = ObradaKomandePP.getInstance();
			obrada.obradaPodrucja(unos);
			podrucja = obrada.getPodrucja();
		}
		for (PodrucjeComposite podrucje : podrucja) {
			podrucje.ispisTablice();
		}

	}
	private static boolean provjeraKomandePP(String komanda) {
		String regex = "^PP(\\s+\\d+)?$";
		Pattern patern = Pattern.compile(regex);
		Matcher tocnaKomanda = patern.matcher(komanda);
		return tocnaKomanda.matches();
	}

}
