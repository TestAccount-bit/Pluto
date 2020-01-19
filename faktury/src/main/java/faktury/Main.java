package faktury;

import creationDB.DataBase;


public class Main {

	private static final String PRODUCTLIST = "products.txt";	//wejsciowy CSV plik

	public static void main(String[] args) {
		DataBase database = new DataBase("shop");
		//database.insertPeople(0, "Kuba", "Kowalski", 4);
		//database.insertProducts("Kawa rozpuszczalna", 4, 5000);
		Faktura faktura = new Faktura(PRODUCTLIST);
		faktura.print();
		Person person = new Person(database, "vasian", "Pupkin", 23);
		database.showTable("people");
	}
		

}
