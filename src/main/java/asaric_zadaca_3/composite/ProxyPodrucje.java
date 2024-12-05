package asaric_zadaca_3.composite;

import java.util.List;

import asaric_zadaca_3.podatci.Podrucja;

public class ProxyPodrucje implements Component{
	List<Podrucja> unosPodrucja;
	private List<PodrucjeComposite> podrucja = null;
	private String komanda;
	public ProxyPodrucje(List<Podrucja> unosPodrucja, String komanda) {
		this.unosPodrucja = unosPodrucja;
		this.komanda = komanda;
	}

	@Override
	public void ispisTablice() {
		if ("PP".equalsIgnoreCase(komanda)) {
			for (Podrucja unos : unosPodrucja) {
				ObradaKomandePP obrada = ObradaKomandePP.getInstance();
				obrada.obradaPodrucja(unos);
				podrucja = obrada.getPodrucja();
			}
			for (PodrucjeComposite podrucje : podrucja) {
				podrucje.ispisTablice();
			}
		} else {
			int brojPodrucja = Integer.parseInt(komanda.split(" ")[1]);
			for (Podrucja unos : unosPodrucja) {
				if(brojPodrucja == unos.getId()) {
					ObradaKomandePP obrada = ObradaKomandePP.getInstance();
					obrada.obradaPodrucja(unos);
					podrucja = obrada.getPodrucja();
				}
			}
			if(podrucja != null) {
				for (PodrucjeComposite podrucje : podrucja) {
					podrucje.ispisTablice();
				}
			} else {
				System.out.println("uneseno podrucje ne postoji!!");
			}
		}
	}
}
