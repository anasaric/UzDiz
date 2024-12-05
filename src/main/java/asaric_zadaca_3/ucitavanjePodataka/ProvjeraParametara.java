package asaric_zadaca_3.ucitavanjePodataka;

import java.util.Properties;


public class ProvjeraParametara {
	ProvjeraArgumenata provjeraArgumenata = new ProvjeraArgumenata();
	public static int brojac = 0;
	public static boolean provjeriParametre(Properties parametri) {
        provjeriAtribut(parametri, "vp");
        provjeriAtribut(parametri, "pv");
        provjeriAtribut(parametri, "pp");
        provjeriAtribut(parametri, "po");
        provjeriAtribut(parametri, "pm");
        provjeriAtribut(parametri, "pu");
        provjeriAtribut(parametri, "pmu");
        provjeriAtribut(parametri, "mt");
        provjeriAtribut(parametri, "vi");
        provjeriAtribut(parametri, "vs");
        provjeriAtribut(parametri, "ms");
        provjeriAtribut(parametri, "pr");
        provjeriAtribut(parametri, "kr");
        provjeriAtribut(parametri, "gps");
        provjeriAtribut(parametri, "isporuka");
        
        if(brojac>0) return false;
        else return true;
    }

    private static void provjeriAtribut(Properties parametri, String kljuc) {
    	ProvjeraArgumenata provjeraArgumenata = new ProvjeraArgumenata();
        if (!parametri.containsKey(kljuc)) {
            System.out.println("Parametar '" + kljuc + "' nedostaje.");
        } else {
            String vrijednost = parametri.getProperty(kljuc);
            if (kljuc.equals("mt") || kljuc.equals("vi") || kljuc.equals("isporuka") || kljuc.equals("ms")) {
                if (!provjeraArgumenata.provjeriArgument(kljuc + "=" + vrijednost)) {
                    System.out.println("Nevažeća brojcana vrijednost za parametar '" + kljuc + "': " + vrijednost);
                    brojac++;
                }
            } else if (kljuc.equals("vp") || kljuc.equals("pv") || kljuc.equals("pp") ||
                    kljuc.equals("po") || kljuc.equals("pm") || kljuc.equals("pu") ||
                    kljuc.equals("pmu")) {
            	if (!provjeraArgumenata.provjeriArgument(kljuc + "=" + vrijednost)) {
                    System.out.println("Nevažeća vrijednost datoteke za parametar '" + kljuc + "': " + vrijednost);
                    brojac++;
                }
            } else if (kljuc.equals("vs")) {
                if (!provjeraArgumenata.provjeriArgument(kljuc + "=" + vrijednost)) {
                    System.out.println("Nevažeći format datuma i vremena za parametar '" + kljuc + "': " + vrijednost);
                    brojac++;
                }
            } else if (kljuc.equals("gps")) {
                if (!provjeraArgumenata.provjeriArgument(kljuc + "=" + vrijednost)) {
                    System.out.println("Nevažeće koordinate za parametar '" + kljuc + "': " + vrijednost);
                    brojac++;
                }
            }
            else if (kljuc.equals("pr") || kljuc.equals("kr")) {
                if (!provjeraArgumenata.provjeriArgument(kljuc + "=" + vrijednost)) {
                    System.out.println("Nevažeći format vremena za parametar '" + kljuc + "': " + vrijednost);
                    brojac++;
                }
            } 
        }
    }
}

