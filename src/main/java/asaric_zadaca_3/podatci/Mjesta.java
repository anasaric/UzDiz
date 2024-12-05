package asaric_zadaca_3.podatci;

import java.util.List;

public class Mjesta {
	private int id;
    private String naziv;
    private List<Integer> ulice;

    public Mjesta(int id, String naziv, List<Integer> ulice) {
        this.id = id;
        this.naziv = naziv;
        this.ulice = ulice;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }
    
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Integer> getUlice() {
        return ulice;
    }
    
    public void setUlice(List<Integer> ulice) {
        this.ulice = ulice;
    }
}
