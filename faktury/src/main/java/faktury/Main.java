package faktury;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("Dostepne artykuly spozywcze:");
        List<Product> products = CSVReader.readBooksFromCSV("products.txt");
        for (Product b : products) {
            System.out.println(b);
        }
        System.out.println("\nProsze wybrac artykuly:");

	}

}
