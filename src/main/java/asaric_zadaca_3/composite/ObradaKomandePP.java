package asaric_zadaca_3.composite;

import java.util.ArrayList;
import java.util.List;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.podatci.Mjesta;
import asaric_zadaca_3.podatci.Podrucja;
import asaric_zadaca_3.podatci.Ulice;

public class ObradaKomandePP {
	private static ObradaKomandePP instance = new ObradaKomandePP();
	private List<PodrucjeComposite> podrucja = new ArrayList<>();
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();

	private ObradaKomandePP() {
	}

	public static ObradaKomandePP getInstance() {
		return instance;
	}

	public List<PodrucjeComposite> getPodrucja() {
		return podrucja;
	}

	public void obradaPodrucja(Podrucja redak) {
            int idPodrucja = redak.getId();
            PodrucjeComposite podrucje = new PodrucjeComposite(idPodrucja, "Podrucje " + idPodrucja);
            
            for (String redakMjestaUlice : redak.getGradUlica()) {
            	String mjestaUlice = redakMjestaUlice.replace(" ", "");
                String[] mjestaIUliceRazdvojeni = mjestaUlice.split(":");
                int idMjesta = Integer.parseInt(mjestaIUliceRazdvojeni[0]);
                String pronadiNazivMjesta = pronadiNazivMjesta(idMjesta);
                MjestoList mjesto = new MjestoList(idMjesta, pronadiNazivMjesta);
                if("*".equalsIgnoreCase(mjestaIUliceRazdvojeni[1])) {
                	List<Integer> listaUlica = dodajSveUlice(idMjesta);             	
                	for(Integer redUlica : listaUlica) {
                		String pronadiNazivUlice = pronadiNazivUlice(redUlica);
                		if(pronadiNazivUlice!= null) {
                			UliceList ulica = new UliceList(redUlica, pronadiNazivUlice);
                			mjesto.dodajUlicu(ulica);
                		}
                	}
                	
                } else {
                	 int idUliceInt = Integer.parseInt(mjestaIUliceRazdvojeni[1]);
                	 String pronadiNazivUlice = pronadiNazivUlice(idUliceInt);
                	 UliceList ulica = new UliceList(idUliceInt, pronadiNazivUlice);
                     mjesto.dodajUlicu(ulica);
                }
               
                podrucje.dodajKomponentu(mjesto);
            }
            
            podrucja.add(podrucje); 
    }

	private String pronadiNazivUlice(Integer idUlice) {
		for (Ulice redUlice : objekti.getUlice()) {
			if (redUlice.getId() == idUlice) {
				return redUlice.getNaziv();
			}
		}
		return null;
	}

	private String pronadiNazivMjesta(int idMjesta) {
		for (Mjesta redMjesta : objekti.getMjesta()) {
			if (redMjesta.getId() == idMjesta) {
				return redMjesta.getNaziv();
			}
		}
		return null;
	}

	private List<Integer> dodajSveUlice(int idMjesta) {
		List<Integer> listaUlica = new ArrayList<>();

		for (Mjesta redMjesta : objekti.getMjesta()) {
			if (redMjesta.getId() == idMjesta) {
				listaUlica = redMjesta.getUlice();
			}
		}
		return listaUlica;
	}
}
