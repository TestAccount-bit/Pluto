package faktury;

public class Product {
	private String name;
	private float price;
	
	public Product(String name, float price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	
    @Override
    public String toString() {
        return (name + " - " + price);
               
    }

}
