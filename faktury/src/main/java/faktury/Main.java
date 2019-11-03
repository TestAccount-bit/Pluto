package faktury;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static final String PRODUCTLIST = "products.txt";

	public static void main(String[] args) {
		
		Scanner myObj = new Scanner(System.in);
		
		float Sum = 0;
		
		System.out.println("Dostepne artykuly spozywcze:");
        List<Product> products = CSVReader.readBooksFromCSV(PRODUCTLIST);
        for (Product pr : products) {
            System.out.println(pr);
        }
        System.out.println("\nProsze wybrac artykuly:");
        
        while(true) {
        	ProductIsExists obj = new ProductIsExists(products);
        	String product = myObj.nextLine();
        	while (product.equals("faktura") == false) {
        		if (obj.IsExists(product)) {
        			System.out.println("OK");
        		}
        		product = myObj.nextLine();
        	}
        	
        	System.out.println("Nowa faktura");
        }

	}

}
