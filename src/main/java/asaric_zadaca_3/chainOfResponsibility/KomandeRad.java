package asaric_zadaca_3.chainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class KomandeRad {
	private List<KomandaHandler> handlers = new ArrayList<KomandaHandler>();
	
    public void dodajHandler(KomandaHandler handler) {
        handlers.add(handler);
    }

    public void radKomande(String komanda) {
        for (KomandaHandler handler : handlers) {
            handler.izvrsiKomandu(komanda);
        }
    }
}
