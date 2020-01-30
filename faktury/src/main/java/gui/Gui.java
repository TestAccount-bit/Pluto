package gui;
 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Date;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import creationDB.DataBase;
import faktury.Person;
import faktury.ProductList;
 
public class Gui extends JFrame {
	
	DataBase database = new DataBase("shop");
    private ProductList productList;
    private Person person;	
	
    private JTextArea textArea;

    private JButton buttonClear = new JButton("Clear");
    private JButton showProducts = new JButton("List of Products");
    
    private JLabel cust = new JLabel("For customer:");
    private JButton createProductList = new JButton("New List of products");
    private JLabel id = new JLabel("Type id:");
    private JTextField product_id = new JTextField(40);
    private JLabel amount = new JLabel("Type amount:");
    private JTextField product_amount = new JTextField(40);
    private JButton addProduct = new JButton("Add product"); 
    private JLabel separator = new JLabel("-------------------------------");
    private JLabel typeYourId = new JLabel("Your id:");
    private JTextField customer_id = new JTextField(40);
    private JButton buy = new JButton("Buy List");
    
    private JButton addPerson = new JButton("Add person");
    private JLabel pName = new JLabel("Your name:");
    private JTextField name = new JTextField(40);
    private JLabel pSurname = new JLabel("Your surname:");
    private JTextField surname = new JTextField(40);
    private JLabel pMoney = new JLabel("Your money:");
    private JTextField money = new JTextField(40);
    
    private JLabel suppl = new JLabel("For Supplier:");
    private JLabel nameOfProduct = new JLabel("Name of product:");
    private JTextField productName = new JTextField(40);
    private JLabel priceOfProduct = new JLabel("Price per product:");
    private JTextField productPrice = new JTextField(40);
    private JLabel amountOfProducts = new JLabel("Amount to supply:");
    private JTextField productAmount = new JTextField(40);
    private JButton supply = new JButton("Supply");
    
    private JLabel admin = new JLabel("For Admin:");
    private JButton showCustomers = new JButton("Customers");
    
    private PrintStream standardOut;
    
    public Gui() {
        super("Shop");
         
        textArea = new JTextArea(250, 10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
         
        // keeps reference of standard output stream
        standardOut = System.out;
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
 
        // creates the GUI
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
         
        add(buttonClear, constraints);
         
        constraints.gridx = 0;
        add(addPerson, constraints);
        
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.BOTH;
        add(pName, constraints);
        
        constraints.gridy = 2;
        add(name, constraints);
        
        constraints.gridy = 3;
        add(pSurname, constraints);
        
        constraints.gridy = 4;
        add(surname, constraints);
        
        constraints.gridy = 5;
        add(pMoney, constraints);
        
        constraints.gridy = 6;
        add(money, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(showProducts, constraints);
        
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(cust, constraints);
        
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        add(createProductList, constraints);
        
        constraints.gridy = 2;
        add(id, constraints);
        
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        add(product_id, constraints);
        
        constraints.gridy = 4;
        add(amount, constraints);
        
        constraints.gridy = 5;
        add(product_amount, constraints);
        
        constraints.gridy = 6;
        add(addProduct, constraints);
        
        constraints.gridy = 7;
        add(separator, constraints);
        
        constraints.gridy = 8;
        add(typeYourId, constraints);
        
        constraints.gridy = 9;
        add(customer_id, constraints);
        
        constraints.gridy = 10;
        add(buy, constraints);
        
        constraints.gridx = 6;
        constraints.gridy = 0;
        add(suppl, constraints);
        
        constraints.gridy = 1;
        add(nameOfProduct, constraints);
        
        constraints.gridy = 2;
        add(productName, constraints);
        
        constraints.gridy = 3;
        add(priceOfProduct, constraints);
        
        constraints.gridy = 4;
        add(productPrice, constraints);
        
        constraints.gridy = 5;
        add(amountOfProducts, constraints);
        
        constraints.gridy = 6;
        add(productAmount, constraints);
        
        constraints.gridy = 7;
        add(supply, constraints);
        
        constraints.gridx = 7;
        constraints.gridy = 0;
        add(admin, constraints);
        
        constraints.gridy = 1;
        add(showCustomers, constraints);
        
         
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.gridheight = 16;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        add(new JScrollPane(textArea), constraints);
         
         
        // adds event handler for button Clear
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    textArea.getDocument().remove(0, textArea.getDocument().getLength());
                    standardOut.println("Text area cleared");
                }
                catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        showProducts.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		database.showTable("products");
        	}
        });
         
        createProductList.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		productList = new ProductList();
        	}
        });
        
        addProduct.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		try {
					productList.addProductToList(database.chooseProduct( Integer.parseInt(product_id.getText()), Integer.parseInt(product_amount.getText()) ));
				} 
        		catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        });
        
        addPerson.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		person = new Person(database, name.getText(), surname.getText(), Integer.parseInt(money.getText()));
        		
        	}
        });
        
        buy.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		database.buyProductList(database.dbPersonById(Integer.parseInt(customer_id.getText())), productList);
        		
        	}
        });
        
        supply.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		database.insertProducts(productName.getText(), Integer.parseInt(productPrice.getText()), Integer.parseInt(productAmount.getText()));
        		
        	}
        });
        
        showCustomers.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		database.showTable("people");
        	}
        });
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
    }
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
}