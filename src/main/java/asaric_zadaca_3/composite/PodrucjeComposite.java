package asaric_zadaca_3.composite;

import java.util.ArrayList;
import java.util.List;

public class PodrucjeComposite implements Component {
	private String naziv;
	private List<Component> komponente = new ArrayList<>();

	public PodrucjeComposite(int id, String naziv) {
		this.naziv = naziv;
	}

	public void dodajKomponentu(Component komponenta) {
		komponente.add(komponenta);
	}

	@Override
	public void ispisTablice() {
		System.out.printf("\nPodrucje" + "\t\t\t" + "Grad" + "\t\t\t\t" + "Ulice" + "\n");
		System.out.println("-".repeat(100));
		System.out.printf(naziv + "\n");
		for (Component komponenta : komponente) {
			komponenta.ispisTablice();
		}
	}

}
