package asaric_zadaca_3;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.strategy.UredZaPakete;

public class Sat extends Thread {
	private volatile boolean pauziran;
	private volatile boolean zaustavi;
	ObjektiSingleton objekti = ObjektiSingleton.getInstance();
	private boolean zastavica = true;
	long trenutnoVrijemeSekunde;

	public Sat() {
		this.pauziran = false;
		this.zaustavi = false;
	}

	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		long pocetak = objekti.getVirtualniSat().getTime() / 1000;
		while (!zaustavi) {
			synchronized (this) {
				while (pauziran) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				long customTimeInSeconds = pocetak + (objekti.getTrenutnoVrijemeSekunde() - pocetak) * objekti.getMnoziteljSekundi();
				objekti.setTrenutnoVrijemeSekunde(objekti.getTrenutnoVrijemeSekunde() + 1);
				objekti.setTrenutnoRadnoVrijeme(sdf.format(new Date(customTimeInSeconds * 1000)));
				if (zastavica) {
					if (LocalTime.parse(objekti.getTrenutnoRadnoVrijeme())
							.isBefore(objekti.getPocetakRadnogVremena())) {
						System.out.println("Trenutno vrijeme je: " + objekti.getTrenutnoRadnoVrijeme()
								+ ", a pocetak radnog vremena nije do: " + objekti.getPocetakRadnogVremena());
					} else {
						UredZaPakete ured = new UredZaPakete();
						if (LocalTime.parse(objekti.getTrenutnoRadnoVrijeme())
								.isAfter(LocalTime.parse(objekti.getKonacnoVrijeme()))) {
							dodavanjeVirtualnomSatuKonacniSat();
							zastavica = false;
							zaustaviSat();
						}
						if (LocalTime.parse(objekti.getTrenutnoRadnoVrijeme()).isAfter(objekti.getKrajRadnogVremena())
								&& !isDostava()) {
							System.out.println("Kraj radnog vremena");
							zastavica = false;
							zaustaviSat();
						}
					}
				}

				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void dodavanjeVirtualnomSatuKonacniSat() {
		objekti.setTrenutnoRadnoVrijeme(objekti.getKonacnoVrijeme());		
		String[] trenutnoVrijemeSplit = objekti.getTrenutnoRadnoVrijeme().split(":");
		Date datum = objekti.getVirtualniSat();
		datum.setHours(Integer.parseInt(trenutnoVrijemeSplit[0]));
		objekti.setVirtualniSat(datum);
		System.out.println("Isteklo je vrijeme zadanog izvrsavanja!");
    }
		
	

	public synchronized void pauzirajSat() {
		pauziran = true;
	}

	public synchronized void nastaviRadSata() {
		pauziran = false;
		notify();
	}

	public synchronized void zaustaviSat() {
		zaustavi = true;
		if (pauziran) {
			nastaviRadSata();
		}
	}

	public void pokreniVRKomandu() {
		this.zastavica = true;
		nastaviRadSata();
	}

	public void povecajSat() {
		trenutnoVrijemeSekunde = trenutnoVrijemeSekunde + 1;
	}

	public boolean isDostava() {
		for (Vozila vozilo : objekti.getVozila()) {
			if (vozilo.getDostava().equalsIgnoreCase("na dostavi")) {
				return true;
			}
		}
		return false;
	}

}
