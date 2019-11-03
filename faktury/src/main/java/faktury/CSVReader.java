package faktury;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader{

    public static List<Product> readBooksFromCSV(String fileName) {
        List<Product> products = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            
            while (line != null) {
                String[] attributes = line.split(",");

                Product product = createProduct(attributes);
                products.add(product);
                line = br.readLine();
            }
        } 
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return products;
    }

    private static Product createProduct(String[] data) {
        String name = data[0];
        float price = Float.parseFloat(data[1]);
        return new Product(name, price);
    }
}