package faktury;

import java.util.List;

public class ProductList {
	//List of products
	private List<Product> products;
	
	public ProductList(List<Product> products) {
		this.products = products;
	}
	
	public boolean isExists(final String productName) {
		
		
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
	
	public int returnIndex(String productName) {
		for (int i=0;i<this.products.size();i++) {
			if (this.products.get(i).getName().equals(productName)) {
				return i;
			}
		}
		return 0;
	}
}
