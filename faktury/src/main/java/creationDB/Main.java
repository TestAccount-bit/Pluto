package creationDB;



public class Main {

	public static void main(String[] args) {
		DataBase database = new DataBase("shop");
		
		database.create();
		database.products();
		database.people();
		database.insertPeople("Mao", "Dz.", 9001);
	}

}
