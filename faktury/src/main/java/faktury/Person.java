package faktury;

import creationDB.DataBase;

public class Person {

	int id;
	String name;
	String surname;
	int money;
	
	public Person(DataBase db, String name, String surname, int money) {
		this.id = db.insertPeople(name, surname, money);
		this.name = name;
		this.surname = surname;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	
}
