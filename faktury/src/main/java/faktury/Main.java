package faktury;

import java.sql.SQLException;

import creationDB.DataBase;


public class Main {

	//private static final String PRODUCTLIST = "products.txt";	//wejsciowy CSV plik

	public static void main(String[] args) throws SQLException {
		DataBase database = new DataBase("shop");
		//database.insertPeople(0, "Kuba", "Kowalski", 4);
		//int k = database.insertProducts("chleb", 3, 500);
		//Person person = new Person(database, "vasian", "Pupkin", 23);
		database.showTable("people");
		database.showTable("products");
		database.showTable("temp");
		//int n = database.amountOfProducts(database.productById(k));
		//System.out.println(n);

		//Person person = database.dbPersonById(26);
		//Product product = database.productById(30);
		//product.setAmount(1);
		//database.buyProduct(person, product);
		//database.clearTemp();
		ProductList productList = new ProductList();
		productList.addProductToList(database.chooseProduct(28, 1));
		productList.addProductToList(database.chooseProduct(23, 4));
		productList.addProductToList(database.chooseProduct(23, 5));
		productList.addProductToList(database.chooseProduct(30, 2));
		productList.addProductToList(database.chooseProduct(25, 1));
		database.buyProductList(database.dbPersonById(26), productList);
		
		database.showTable("people");
		database.showTable("products");
		database.showTable("temp");
	}
}
