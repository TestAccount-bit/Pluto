package faktury;

import creationDB.DataBase;

public class Product {
	private int id;
	private String name;
	private int price;
	private int amount;
	
	public Product(DataBase db, final String name,final int price) {
		this.id = db.insertProducts(name, price, 0);
		this.name = name;
		this.price = price;
		this.amount = 0;
	}
	public Product(DataBase db, final String name,final int price, int amount) {
		this.id = db.insertProducts(name, price, amount);
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	public Product(int id, String name, int price, int amount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
	public Product(int id, int amount) {
		this.id = id;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		this.amount += amount;
	}
	
    @Override
    public String toString() {
        return name + " - " + price;
               
    }

}
