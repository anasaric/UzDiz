package asaric_zadaca_3.state;

import asaric_zadaca_3.podatci.Vozila;

public class UMirovanju implements VozilaState{

	@Override
	public void promijeniStatus(Vozila vozilo) {
		vozilo.setDostava("u mirovanju");
	}

}
