package faktury;



public class Main {

	private static final String PRODUCTLIST = "products.txt";	//wejsciowy CSV plik

	public static void main(String[] args) {
		Faktura faktura = new Faktura(PRODUCTLIST);
		faktura.print();
	}
		

}
