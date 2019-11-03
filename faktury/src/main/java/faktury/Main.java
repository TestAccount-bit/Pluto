package faktury;

import java.util.List;
import java.util.Scanner;

public class Main {

	private static final String PRODUCTLIST = "products.txt";	//wejsciowy CSV plik

	public static void main(String[] args) {
		
		final Scanner myObj = new Scanner(System.in);
		String[] faktura = new String[255];
		float sum = 0;
		
		System.out.println("Dostepne artykuly spozywcze:");
        List<Product> products = CSVReader.readBooksFromCSV(PRODUCTLIST);
        for (Product pr : products) {
            System.out.println(pr);
        }
        System.out.println("\nProsze wybrac artykuly:");
        
        String product = myObj.nextLine();
        while(product.equals("exit") == false) {
        	ProductList obj = new ProductList(products);
        	sum = 0;
        	int counter = 0;
        	int amount = 0;
        	while (product.equals("faktura") == false) {
        		if (obj.IsExists(product)) {
        			System.out.println("Amount:");
        			amount = Integer.parseInt(myObj.nextLine());
        			float price = products.get(obj.returnIndex(product)).getPrice();
        			System.out.println(price*amount);
        			faktura[counter] = product + " - " + price + " x" + amount + " " + price*amount;
        			counter++;
        			sum=sum + price*amount;
        		}
        		else {
        			System.out.println("Artykul spozywczy nie jest dostepny.");
        		}
        		product = myObj.nextLine();
        	}
        	
        	System.out.println("\n-------PARAGON FISKALNY-------");
        	for(int i=0;i<counter;i++) {
        		System.out.println(faktura[i]);
        	}
        	System.out.println("\nRazem:" + sum);
        	System.out.println("------------------------------");
        	
        	System.out.println("Nowa faktura:");
        	product = myObj.nextLine();
        }
        myObj.close();
	}
}
