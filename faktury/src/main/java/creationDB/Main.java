package creationDB;



public class Main {

	public static void main(String[] args) {
		
		DataBase database = new DataBase("shop");
		database.create();
		
		database.createSupplier();
		database.createConsumer();
		
		database.products();
		database.people();	
		database.temp();
		
		database.createBuyProductTransaction();
		database.createBuyProductListTransaction();
		
	}

}
