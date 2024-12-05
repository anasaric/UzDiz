package asaric_zadaca_3.strategy;


public class PaketiContext {
	private VoditeljPaketa voditelj;

    public void postaviOperacijuNadPaketima(VoditeljPaketa voditelj) {
        this.voditelj = voditelj;
    }

    public void izvrsiOperacijuNadPaketom() {
        if (voditelj != null) {
        	voditelj.upravljanjePaketom();
        } else {
            System.out.println("Nije postavljena strategija za izvršavanje operacija.");
        }
    }
}
