package asaric_zadaca_3.composite;

import java.util.ArrayList;
import java.util.List;

public class MjestoList implements Component {
	private int id;
	private String naziv;
	private List<UliceList> ulice = new ArrayList<>();

	public MjestoList(int id, String naziv) {
		this.naziv = naziv;
		this.id = id;
	}

	public void dodajUlicu(UliceList ulica) {
		ulice.add(ulica);
	}

	@Override
	public void ispisTablice() {
		System.out.printf("\t\t\t\t" + naziv + "\n");
		
		for (UliceList ulica : ulice) {
			System.out.printf("\t\t\t\t\t\t\t\t" + ulica.getNaziv() + "\n");
		}
		System.out.println("\n");
	}
}
