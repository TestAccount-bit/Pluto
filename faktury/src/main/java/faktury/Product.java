package faktury;

public class Product {
	private final String name;
	private final float price;
	
	public Product(final String name,final float price) {
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
        return name + " - " + price;
               
    }

}
