package faktury;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
	private List<Product> products;
	
	public ProductList(List<Product> products) {
		this.products = new ArrayList<Product>(products);
	}
	
	public ProductList() {
		this.products = new ArrayList<Product>();
	}
	
	public ProductList(Product product) {
		this.products = new ArrayList<Product>();
		this.products.add(product);
	}
	


	public boolean isExists(final int productId) {
		boolean exists = false;
		
		for (int i=0;i<this.products.size();i++) {
			if (this.products.get(i).getId()==productId) {
				exists = true;
				break;
			}
		}
		
		if (exists)
			return true;
		return false;
	}
	
	public void addProductToList(Product product) {
		if(this.products == null || this.products.isEmpty()) {
			this.products.add(product);
			System.out.println("Added first product to food basket:");
			System.out.println(product.getName() + ", " + product.getAmount());
		}
		else if(isExists(product.getId())) {
			this.products.get(returnIndexById(product.getId())).addAmount(product.getAmount());
			System.out.println("Added " + product.getAmount() + "more to " + product.getName());
		}
		else {
			this.products.add(product);
			System.out.println("New product added: " + product.getName() + ", " + product.getAmount());
		}	
	}
	
//	public int returnIndexByName(String productName) {
//		for (int i=0;i<this.products.size();i++) {
//			if (this.products.get(i).getName().equals(productName)) {
//				return i;
//			}
//		}
//		return -1;
//	}
	
	public int returnIndexById(int productId) {
		for (int i=0;i<this.products.size();i++) {
			if (this.products.get(i).getId()==(productId)){
				return i;
			}
		}
		return -1;
	}
	
	public int size() {
		return this.products.size();
	}
	
	public Product get(int index) {
		return this.products.get(index);
	}
}
