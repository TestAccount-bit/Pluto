package creationDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import faktury.Person;
import faktury.Product;
import faktury.ProductList;


public class DataBase {
	String name;
	
	public DataBase(String name) {
		this.name = name;
	}

	
	//database and users
	
	public void create() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
			String drop =
				"drop database if exists " + this.name + ";";
			String create = 
				"create database " + this.name + ";";
			
			stmt.executeUpdate(drop);
			stmt.executeUpdate(create);
			System.out.println("Database "+ this.name +" created");
			conn.close();
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void createSupplier() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			) 
			{
				String drop =
					"drop user if exists 'supplier'@'localhost';";
				
				String create = 
					"create user 'supplier'@'localhost' identified by 'supplier';";
				
				String privileges =
					"grant select, insert, update on shop.products to 'supplier'@'localhost';";
				
				stmt.executeUpdate(drop);
				stmt.executeUpdate(create);
				stmt.executeUpdate(privileges);
				conn.close();				
			} catch(java.sql.SQLException ex) {
				ex.printStackTrace();
			}
	}
		
	public void createConsumer() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			) 
			{
				String drop =
					"drop user if exists 'consumer'@'localhost';";
				
				String create = 
					"create user 'consumer'@'localhost' identified by 'consumer';";
				
				String privileges =
					"grant select, insert, update, execute, trigger on shop.* to 'consumer'@'localhost';";
				
				stmt.executeUpdate(drop);
				stmt.executeUpdate(create);
				stmt.executeUpdate(privileges);
				conn.close();				
			} catch(java.sql.SQLException ex) {
				ex.printStackTrace();
			}
	}

	
	//tables
	
	public void products() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
			String delete = 
					"drop table if exists products;";
			String create = 
					"create table products(" + 
					"id int auto_increment primary key not null," + 
					"name varchar(50) not null," + 
					"price int not null," +
					"amount int not null default 0);";
			
			stmt.executeUpdate(delete);
			stmt.executeUpdate(create);
			System.out.println("Table products created");
			conn.close();
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void temp() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
			String delete = 
					"drop table if exists temp;";
			String create = 
					"create table temp("
					+"id int primary key not null," + 
					"amount int not null);";
			
			stmt.executeUpdate(delete);
			stmt.executeUpdate(create);
			conn.close();
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void people() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/" + this.name + "?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
			String delete = 
					"drop table if exists people;";
			String create = 
					"create table people(" + 
					"id int auto_increment primary key not null," + 
					"name varchar(50) not null," + 
					"surname varchar(70)," +
					"money int not null default 0);";
			
			stmt.executeUpdate(delete);
			stmt.executeUpdate(create);
			System.out.println("Table people created");
			conn.close();
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}

	
	//products
	
	public void createBuyProductTransaction() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			) 
			{
				String drop =
						"drop procedure if exists buy;";
			
				String transaction =
						"create procedure buy (in person int, in product int, in amount_ int, in money_ int) " + 
						"begin " + 
						"	set autocommit=0; " + 
						"	start transaction; " + 
						"			update people set money = money - money_ where id=person; " + 
						"			update products set amount=amount-amount_ where id=product; " + 
						"	if ( " + 
						"		(select money from people where id = person) < 0 " + 
						"	) " + 
						"	then " + 
						"		rollback; " + 
						"	else " + 
						"		commit; " + 
						"	end if; " + 
						"end;";
				
				stmt.executeUpdate(drop);
				stmt.executeUpdate(transaction);

				conn.close();				
			} catch(java.sql.SQLException ex) {
				ex.printStackTrace();
			}
	}
	
	public void buyProduct(Person person, Product product) {
	try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"consumer", "consumer");
			Statement stmt = conn.createStatement();
		) 
		{
			String select =
					"call buy("
					+ person.getId() + " ,"
					+ product.getId() + " ,"
					+ product.getAmount() + " ,"
					+ product.getAmount()*product.getPrice() 
					+ " );";
			
			stmt.executeQuery(select);

			conn.close();				
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void createBuyProductListTransaction() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			) 
			{
				String drop =
						"drop procedure if exists buyProductList;";
			
				String transaction =
						"create procedure buyProductList (in id_of_person int) " + 
						"begin " + 
						"	declare id_of_product int; " + 
						"	declare amount_of_product int; " + 
						"	 " + 
						"	declare not_done boolean default true; " + 
						"	declare cur1 cursor for " + 
						"		select t.id, t.amount " + 
						"		from temp t; " + 
						"		 " + 
						"	declare continue handler for not found set not_done=false; " + 
						"	 " + 
						"	open cur1; " + 
						"	set autocommit=0; " + 
						"	start transaction; " + 
						"		while not_done do " + 
						"			fetch cur1 into id_of_product, amount_of_product; " + 
						"			if (not_done) " + 
						"			then " + 
						"				update people set money =  " + 
						"					money - (select price from products where id=id_of_product)*amount_of_product " + 
						"					where id=id_of_person; " + 
						"				update products set amount = " + 
						"					amount - amount_of_product " + 
						"					where id=id_of_product; " + 
						"			end if; " + 
						"			 " + 
						"		end while; " + 
						"	 " + 
						"	if (  " + 
						"		(select count(*) from products where amount<0) <> 0 or  " + 
						"		(select money from people where id=id_of_person)<0 " + 
						"	 " + 
						"	) " + 
						"	then  " + 
						"		rollback; " + 
						"	else  " + 
						"		commit; " + 
						"	end if; " + 
						"end;";
				
				stmt.executeUpdate(drop);
				stmt.executeUpdate(transaction);

				conn.close();				
			} catch(java.sql.SQLException ex) {
				ex.printStackTrace();
			}
	}
	
	public void buyProductList(Person person, ProductList productList) {
		int id = person.getId();
		int money = person.getMoney();
		clearTemp();
		for (int i=0;i<productList.size();i++) {
			insertIntoTemp(productList.get(i));
		}
		
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"consumer", "consumer");
			Statement stmt = conn.createStatement();
		) 
		{
			String buy =
					"call buyProductList("
					+ person.getId() 
					+ " );";
			
			stmt.executeQuery(buy);
			conn.close();				
		}
		catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		
		if (dbPersonById(id).getMoney() != money) {
			System.out.println("  ");
			System.out.println("----Faktura----");
			System.out.println("You have bought:");
			showInvoice();
			System.out.println("Money spent: " + (money-dbPersonById(id).getMoney()));
		}
		else {
			System.out.println("Not enough money or your food basket is empty!");
		}
		
	}
	
	public void showInvoice() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + this.name + "?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			)
		{
			String sql = "select p.id, p.name, p.price, t.amount "
					+ "from temp t inner join products p on t.id=p.id;";

			ResultSet rs = stmt.executeQuery(sql);
			DBTablePrinter.printResultSet(rs);
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public int insertProducts(String name, int price, int amount) {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
		    String insert_products = 
		    	"insert into products (id, name, price, amount)"
		    	+ " values (?, ?, ?, ?)";
		    String last_insert =
			    	"select last_insert_id();";
		    
		    PreparedStatement preparedStmt = conn.prepareStatement(insert_products);
		    preparedStmt.setInt		(1, 0);
		    preparedStmt.setString	(2, name);
		    preparedStmt.setInt		(3, price);
		    preparedStmt.setInt		(4, amount);

		    preparedStmt.execute();
		    
		    ResultSet res = stmt.executeQuery(last_insert);
		    int last = -1;
		    while(res.next()) {
				last = res.getInt("last_insert_id()");
		    }
		    System.out.println("You just supplied:");
		    System.out.println("Name: " + name);
		    System.out.println("Price: " + price);
		    System.out.println("Amount: " + amount);
		      
		    conn.close();
		    return last;
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}	
	
	public void insertProductList(String name, int price, int amount) {
	try (
		Connection conn = DriverManager.getConnection(
			"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
			+ "&useJDBCCompliantTimezoneShift=true"
			+ "&useLegacyDatetimeCode=false"
			+ "&serverTimezone=UTC",
			"admin", "password");
		Statement stmt = conn.createStatement();
	) 
	{
	    String insert_products = 
	    	"insert into products (id, name, price, amount)"
	    	+ " values (?, ?, ?, ?)";
	    
	    PreparedStatement preparedStmt = conn.prepareStatement(insert_products);
	    preparedStmt.setInt		(1, 0);
	    preparedStmt.setString	(2, name);
	    preparedStmt.setInt		(3, price);
	    preparedStmt.setInt		(4, amount);

	    preparedStmt.execute();
	      
	    conn.close();
	} catch(java.sql.SQLException ex) {
		ex.printStackTrace();
	}
	}
	
	public int amountOfProducts(Product product) {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
		    String select = 
		    	"select amount from products "
		    	+ "where id = " + product.getId() + ";";
		    			      	    
		    ResultSet res = stmt.executeQuery(select);
		    int amount = -1;
		    while(res.next()) {
				amount = res.getInt("amount");
		    }
		    
		    conn.close();
		    return amount;
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}		
	
	public Product productById(int id) throws SQLException {
		int id_=-1;
		String name ="";
		int price=-1;
		int amount=-1;
		
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
		) 
		{
			
			
			String select = 
					"Select id, name, price, amount from products "
					+ "where id=" + id + ";";
			ResultSet res = stmt.executeQuery(select);
			
			while(res.next()) {
				id_ = res.getInt("id");
				name = res.getString("name");
				price = res.getInt("price");
				amount = res.getInt("amount");
			}
			conn.close();
		}
				
		return new Product(id_, name, price,  amount);
		
	}
	
	public Product chooseProduct(int id, int amount) throws SQLException {
		int id_=-1;
		String name ="";
		int price=-1;
		
		
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
		) 
		{
			
			
			String select = 
					"Select id, name, price from products "
					+ "where id=" + id + ";";
			ResultSet res = stmt.executeQuery(select);
			
			while(res.next()) {
				id_ = res.getInt("id");
				name = res.getString("name");
				price = res.getInt("price");
			}
			conn.close();
		}
				
		return new Product(id_, name, price,  amount);
		
	}
	
	
	
	
	
	//people

	public int insertPeople(String name, String surname, int money) {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
		    String insert_people = 
		    	"insert into people (id, name, surname, money)"
		    	+ " values (?, ?, ?, ?)";
		    String last_insert =
			    	"select last_insert_id();";
		    
		    PreparedStatement preparedStmt = conn.prepareStatement(insert_people);
		    preparedStmt.setInt		(1, 0);
		    preparedStmt.setString	(2, name);
		    preparedStmt.setString	(3, surname);
		    preparedStmt.setInt		(4, money);

		    preparedStmt.execute();
		      	    
		    ResultSet res = stmt.executeQuery(last_insert);
		    int last = -1;
		    while(res.next()) {
				last = res.getInt("last_insert_id()");
		    }
		     //int last = ((Number) res).intValue();
		    
		    conn.close();
		    return last;
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public Person dbPersonById(int id) {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
			try {
			    String select = 
			    	"select * from people "
			    	+ "where id = " + id + ";";
			    
			    int id_=0;
			    String name="";
			    String surname="";
			    int money=0;
				ResultSet res = stmt.executeQuery(select);
				while(res.next()) {
					id_ = res.getInt("id");
					name = res.getString("name");
					surname = res.getString("surname");
					money = res.getInt("money");
				}
				return new Person(id_, name, surname, money);
			
			} catch(java.sql.SQLException ex) {
				ex.printStackTrace();
			}
		    conn.close();
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	//temp
	
	public void insertIntoTemp(Product product) {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
		    String insert_temp = 
		    	"insert into temp (id, amount)"
		    	+ " values (?, ?)";
		    
		    PreparedStatement preparedStmt = conn.prepareStatement(insert_temp);
		    preparedStmt.setInt	(1, product.getId());
		    preparedStmt.setInt	(2, product.getAmount());

		    preparedStmt.execute();
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void clearTemp() {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/"+ this.name +"?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			) 
		{
		    String delete = 
		    	"delete from temp;";
		    
		    stmt.executeUpdate(delete);
		} 
		catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}		
	}
	
	
	//show tables
	
	public void showTable (String name) {
		try (
				Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/" + this.name + "?useUnicode=true"
					+ "&useJDBCCompliantTimezoneShift=true"
					+ "&useLegacyDatetimeCode=false"
					+ "&serverTimezone=UTC",
					"admin", "password");
				Statement stmt = conn.createStatement();
			)
		{
			String sql = "select * from " + name + ";";

			ResultSet rs = stmt.executeQuery(sql);
			DBTablePrinter.printResultSet(rs);
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
