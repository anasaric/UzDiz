package asaric_zadaca_3.ucitavanjePodataka;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProvjeraArgumenata {
	private static final String regex_brojevi = "^\\d*$";
	private static final String regexDatumVrijeme = "^\\d{2}\\.\\d{2}\\.\\d{4}\\.\\s\\d{2}:\\d{2}:\\d{2}";
	private static final String regexKoordinate = "\\d{2}\\.\\d{6},\\d{2}\\.\\d{6}";
	private static final String regexDatoteke = "\\w{3}\\d(\\w{6,7}|\\w{9})\\.\\w{3}";
	private static final String regexVrijeme = "\\d{1,2}:\\d{2}";

	public static boolean provjeriArgument(String argument) {
		if (argument.startsWith("mt=") || argument.startsWith("vi=") || argument.startsWith("isporuka=")
				|| argument.startsWith("ms=") || argument.startsWith("isporuka=")) {
			return provjeriRegex(argument.substring(argument.indexOf("=") + 1), regex_brojevi);
		} else if (argument.startsWith("vp=") || argument.startsWith("pv=") || argument.startsWith("pp=") || argument.startsWith("po=")
				|| argument.startsWith("pm=") || argument.startsWith("pu=") || argument.startsWith("pmu=")) {
			return provjeriRegex(argument.substring(argument.indexOf("=") + 1), regexDatoteke);
		} else if (argument.startsWith("vs=")) {
			if (provjeriRegex(argument.substring(argument.indexOf("=") + 1), regexDatumVrijeme)
					|| provjeriRegex(argument.substring(argument.indexOf("=") + 1), regexDatoteke)) {
				return true;
			}
		} else if (argument.startsWith("gps=")) {
			return provjeriRegex(argument.substring(argument.indexOf("=") + 1), regexKoordinate);
		} 
		else if (argument.startsWith("pr=") || argument.startsWith("kr=")) {
			return provjeriRegex(argument.substring(argument.indexOf("=") + 1), regexVrijeme);
		}
		return false;
	}

	private static boolean provjeriRegex(String vrijednost, String regex) {
		Pattern patern = Pattern.compile(regex);
		Matcher ispravno = patern.matcher(vrijednost);
		return ispravno.matches();
	}
}
