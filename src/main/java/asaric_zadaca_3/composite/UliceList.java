package asaric_zadaca_3.composite;


public class UliceList implements Component {
	private int id;
	private String naziv;

	public UliceList(int id, String naziv) {
		this.naziv = naziv;
		this.id = id;
	}
	
	public String getNaziv() {
        return naziv;
    }

	@Override
	public void ispisTablice() {
		System.out.printf("%-15s%s%n", "", naziv);
	}
}
