package asaric_zadaca_3.observer;


public interface ObavijestiSubject {
	void dodajOsobu(ObavijestObserver osoba);
	void obrisiOsobu(String ime, String oznaka);

	void obavijestiOZaprimljenomPaketu(ObavijestObserver osoba);	
	void obavijestiOUkrcanomPaketu(ObavijestObserver osoba);
	void obavijestiODostavljenomPaketu(ObavijestObserver osoba);
}
