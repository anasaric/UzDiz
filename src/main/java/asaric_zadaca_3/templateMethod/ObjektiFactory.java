package asaric_zadaca_3.templateMethod;

public class ObjektiFactory extends Objekti{
	public CitacDatoteka kreirajDatoteke(String tip)
    {
        switch (tip) {
        case "vp":
        	return new CitacVrste();
        case "pv":
        	return new CitacVozila();
        case "pp":
        	return new CitacPaketi();
        case "po":
        	return new CitacOsobe();      	
        case "pm":
        	return new CitacMjesta();
        case "pmu":
        	return new CitacPodrucja();
        case "pu":
        	return new CitacUlice();
        default:
            throw new IllegalArgumentException("Nepoznata datoteka "+ tip);
        }
    }

	@Override
	public CitacDatoteka kreirajCitac(String tipDatoteke) {
		switch (tipDatoteke) {
        case "vp":
        	return new CitacVrste();
        case "pv":
        	return new CitacVozila();
        case "pp":
        	return new CitacPaketi();
        case "po":
        	return new CitacOsobe();      	
        case "pm":
        	return new CitacMjesta();
        case "pmu":
        	return new CitacPodrucja();
        case "pu":
        	return new CitacUlice();
        default:
            throw new IllegalArgumentException("Nepoznata datoteka "+ tipDatoteke);
		}
	}

	@Override
	public void ucitaj(String tipDatoteke, String nazivDatoteke) {
		CitacDatoteka citac = kreirajCitac(tipDatoteke);
        citac.ucitajDatoteku(nazivDatoteke);
	}

}
