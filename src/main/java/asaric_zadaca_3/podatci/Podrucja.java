package asaric_zadaca_3.podatci;

import java.util.List;

public class Podrucja {
	private int id;
	private List<String> gradUlica;

    public Podrucja(int id, List<String> gradUlica) {
        this.id = id;
        this.gradUlica = gradUlica;
    }
    

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public List<String>getGradUlica() {
        return gradUlica;
    }

    public void setGradUlica(List<String> gradUlica) {
        this.gradUlica = gradUlica;
    }
}
