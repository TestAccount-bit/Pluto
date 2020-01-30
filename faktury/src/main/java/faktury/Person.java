package faktury;

import creationDB.DataBase;

public class Person {

	private int id;
	private String name;
	private String surname;
	private int money;
	
	public Person(DataBase db, String name, String surname, int money) {
		this.id = db.insertPeople(name, surname, money);
		this.name = name;
		this.surname = surname;
		this.money = money;
		System.out.println("Hello, Customer!");
		System.out.println("Your id:" + this.id);
		System.out.println("Your name:" + this.name);
		System.out.println("Your surnamename:" + this.surname);
		System.out.println("Your money:" + this.money);
	}
	public Person(int id, String name, String surname, int money) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public int getMoney() {
		return money;
	}
	
}
