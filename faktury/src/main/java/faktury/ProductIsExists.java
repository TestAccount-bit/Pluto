package faktury;

import java.util.List;

public class ProductIsExists {
	List<Product> products;
	
	public ProductIsExists(List<Product> products) {
		this.products = products;
	}
	
	public boolean IsExists(String productName) {
		
		
		boolean exists = false;
		
		for (int i=0;i<this.products.size();i++) {
			if (this.products.get(i).getName().equals(productName)) {
				exists = true;
				break;
			}
		}
		
		if (exists)
			return true;
		return false;
	}
}
