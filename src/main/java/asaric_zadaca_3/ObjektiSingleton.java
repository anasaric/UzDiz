package asaric_zadaca_3;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import asaric_zadaca_3.ObjektiSingleton;
import asaric_zadaca_3.observer.ObavijestObserver;
import asaric_zadaca_3.podatci.Mjesta;
import asaric_zadaca_3.podatci.Osobe;
import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.Podrucja;
import asaric_zadaca_3.podatci.UkrcaniPaketi;
import asaric_zadaca_3.podatci.Ulice;
import asaric_zadaca_3.podatci.Vozila;
import asaric_zadaca_3.podatci.Vrste;
import asaric_zadaca_3.podatci.ZaprimljeniPaketi;
import asaric_zadaca_3.visitor.Segmenti;
import asaric_zadaca_3.visitor.Vozilo;
import asaric_zadaca_3.visitor.Voznja;

public class ObjektiSingleton {
	private static volatile ObjektiSingleton INSTANCE;

	private ObjektiSingleton() {
	}

	public static ObjektiSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ObjektiSingleton();
		}
		return INSTANCE;
	}

	private Date virtualniSat = null;
	private int mnoziteljSekundi = 0;
	private int vrijemeIsporuke = 0;
	private LocalTime pocetakRadnogVremena = null;
	private LocalTime krajRadnogVremena = null;
	private int isporuka = 0;
	private String gps;

	private List<Paketi> paketi = new ArrayList<Paketi>();
	private List<Vozila> vozila = new ArrayList<Vozila>();
	private List<Vrste> vrste = new ArrayList<Vrste>();
	private List<UkrcaniPaketi> ukrcaniPaketi = new ArrayList<UkrcaniPaketi>();
	private List<Osobe> osobe = new ArrayList<Osobe>();
	private List<Mjesta> mjesto = new ArrayList<Mjesta>();
	private List<Podrucja> podrucje = new ArrayList<Podrucja>();
	private List<Ulice> ulica = new ArrayList<Ulice>();
	private List<ObavijestObserver> bezObavijesti = new ArrayList<>();
	private List<ZaprimljeniPaketi> zaprimljeniPaketi = new ArrayList<>();

	private List<Vozilo> vozilo = new ArrayList<>();
	private List<Voznja> voznja = new ArrayList<>();
	private List<Segmenti> segmenti = new ArrayList<>();

	private int redniBrojPogreske = 0;
	private String komanda;
	private String trenutnoRadnoVrijeme = null;
	private String prosloRadnoVrijeme = null;
	private boolean rad;

	private String konacnoVrijeme;
	private long trenutnoVrijemeSekunde;


	public Date getVirtualniSat() {
		return virtualniSat;
	}
	
	public long getTrenutnoVrijemeSekunde() {
		return trenutnoVrijemeSekunde;
	}
	
	public void setTrenutnoVrijemeSekunde(long trenutnoVrijemeSekunde) {
		this.trenutnoVrijemeSekunde = trenutnoVrijemeSekunde;
	}

	public void setVirtualniSat(Date virtualniSat) {
		this.virtualniSat = virtualniSat;
	}

	public int getMnoziteljSekundi() {
		return mnoziteljSekundi;
	}

	public void setMnoziteljSekundi(int mnoziteljSekundi) {
		this.mnoziteljSekundi = mnoziteljSekundi;
	}

	public int getVrijemeIsporuke() {
		return vrijemeIsporuke;
	}

	public void setVrijemeIsporuke(int vrijemeIsporuke) {
		this.vrijemeIsporuke = vrijemeIsporuke;
	}

	public LocalTime getPocetakRadnogVremena() {
		return pocetakRadnogVremena;
	}

	public void setPocetakRadnogVremena(LocalTime pocetakRadnogVremena) {
		this.pocetakRadnogVremena = pocetakRadnogVremena;
	}

	public LocalTime getKrajRadnogVremena() {
		return krajRadnogVremena;
	}

	public void setKrajRadnogVremena(LocalTime krajRadnogVremena) {
		this.krajRadnogVremena = krajRadnogVremena;
	}

	public int getIsporuka() {
		return isporuka;
	}

	public void setIsporuka(int isporuka) {
		this.isporuka = isporuka;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public List<Paketi> getPaketi() {
		return paketi;
	}

	public void setPaketi(List<Paketi> paketi) {
		this.paketi = paketi;
	}

	public List<Vozila> getVozila() {
		return vozila;
	}

	public void setVozila(List<Vozila> vozila) {
		this.vozila = vozila;
	}

	public List<Vrste> getVrste() {
		return vrste;
	}

	public void setVrste(List<Vrste> vrste) {
		this.vrste = vrste;
	}

	public List<UkrcaniPaketi> getUkrcaniPaketi() {
		return ukrcaniPaketi;
	}

	public void setUkrcaniPaketi(List<UkrcaniPaketi> ukrcaniPaketi) {
		this.ukrcaniPaketi = ukrcaniPaketi;
	}

	public List<Osobe> getOsobe() {
		return osobe;
	}

	public void setOsobe(List<Osobe> osobe) {
		this.osobe = osobe;
	}

	public List<Mjesta> getMjesta() {
		return mjesto;
	}

	public void setMjesta(List<Mjesta> mjesto) {
		this.mjesto = mjesto;
	}

	public List<Podrucja> getPodrucja() {
		return podrucje;
	}

	public void setPodrucja(List<Podrucja> podrucje) {
		this.podrucje = podrucje;
	}

	public List<Ulice> getUlice() {
		return ulica;
	}

	public void setUlice(List<Ulice> ulica) {
		this.ulica = ulica;
	}

	public List<ObavijestObserver> getBezObavijest() {
		return bezObavijesti;
	}

	public void setBezObavijesti(List<ObavijestObserver> bezObavijesti) {
		this.bezObavijesti = bezObavijesti;
	}

	public List<ZaprimljeniPaketi> getZaprimljeniPaketi() {
		return zaprimljeniPaketi;
	}

	public void setZaprimljeniPaketi(List<ZaprimljeniPaketi> zaprimljeniPaketi) {
		this.zaprimljeniPaketi = zaprimljeniPaketi;
	}
	
	public List<Vozilo> getVozilo() {
		return vozilo;
	}

	public void setVozilo(List<Vozilo> vozilo) {
		this.vozilo = vozilo;
	}
	
	public List<Voznja> getVoznja() {
		return voznja;
	}

	public void setVoznja(List<Voznja> voznja) {
		this.voznja = voznja;
	}
	
	public List<Segmenti> getSegmenti() {
		return segmenti;
	}

	public void setSegmenti(List<Segmenti> segmenti) {
		this.segmenti = segmenti;
	}
	
	
	
	public int getRedniBrojPogreske() {
		return redniBrojPogreske;
	}

	public void setRedniBrojPogreske(int redniBrojPogreske) {
		this.redniBrojPogreske = redniBrojPogreske;
	}

	public String getKomanda() {
		return komanda;
	}

	public void setKomanda(String komanda) {
		this.komanda = komanda;
	}

	public String getTrenutnoRadnoVrijeme() {
		return trenutnoRadnoVrijeme;
	}

	public void setTrenutnoRadnoVrijeme(String trenutnoRadnoVrijeme) {
		this.trenutnoRadnoVrijeme = trenutnoRadnoVrijeme;

	}

	public String getProsloRadnoVrijeme() {
		return prosloRadnoVrijeme;
	}

	public void setProsloRadnoVrijeme(String prosloRadnoVrijeme) {
		this.prosloRadnoVrijeme = prosloRadnoVrijeme;

	}

	public boolean isRad() {
		return rad;
	}

	public void setRad(boolean rad) {
		this.rad = rad;
	}

	public String getKonacnoVrijeme() {
		return konacnoVrijeme;
	}

	public void setKonacnoVrijeme(String konacnoVrijeme) {
		this.konacnoVrijeme = konacnoVrijeme;
	}
}
