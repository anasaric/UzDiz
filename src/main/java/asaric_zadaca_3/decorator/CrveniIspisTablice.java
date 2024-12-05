package asaric_zadaca_3.decorator;

public class CrveniIspisTablice implements IspisTablice{
	private IspisTablice originalniIspisTablice;

    public CrveniIspisTablice(IspisTablice originalniIspisTablice) {
        this.originalniIspisTablice = originalniIspisTablice;
    }

    @Override
    public void ispisTablice() {
        System.out.print("\u001B[31m");
        originalniIspisTablice.ispisTablice();
        System.out.print("\u001B[0m");
    }
}
