package creationDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;



public class DataBase {
	String name;
	
	public DataBase(String name) {
		this.name = name;
	}

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
			
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		
	}

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
			
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
	}
	
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
	
	public void insertProducts(String name, int price, int amount) {
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
