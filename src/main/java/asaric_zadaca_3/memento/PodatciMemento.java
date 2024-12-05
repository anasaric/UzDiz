package asaric_zadaca_3.memento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import asaric_zadaca_3.podatci.Paketi;
import asaric_zadaca_3.podatci.Vozila;

public class PodatciMemento {
	private long virtualniSatSekunde;
	private String virtualniRadniSat;
	private Date virtualniSat;
    private List<Paketi> paketi;
    private List<Vozila> vozila;

    public PodatciMemento(long virtualniSatSekunde, String virtualniRadniSat, Date virtualniSat, List<Paketi> paketi, List<Vozila> vozila) {
    	this.virtualniSatSekunde = virtualniSatSekunde; 
        this.virtualniRadniSat = virtualniRadniSat;
        this.virtualniSat = (Date) virtualniSat.clone();
        this.paketi = new ArrayList<Paketi>();
        for (Paketi paket : paketi) {
            this.paketi.add(new Paketi(paket)); 
        }
        this.vozila = new ArrayList<Vozila>();
        for (Vozila vozilo : vozila) {
            this.vozila.add(new Vozila(vozilo));
        }
    }
    
    public long getVirtualniSatSekunde() {
        return virtualniSatSekunde;
    }
    
    public String getvirtualniRadniSat() {
        return virtualniRadniSat;
    }
    
    public Date getvirtualniSat() {
        return virtualniSat;
    }
    
    public List<Paketi> getPaketi() {
        return paketi;
    }
    
    public List<Vozila>  getVozila() {
        return vozila;
    }
}
