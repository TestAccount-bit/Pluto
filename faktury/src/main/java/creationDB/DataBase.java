package creationDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBase {
	String name;
	
	

	public DataBase() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/shop?useUnicode=true"
				+ "&useJDBCCompliantTimezoneShift=true"
				+ "&useLegacyDatetimeCode=false"
				+ "&serverTimezone=UTC",
				"admin", "password");
			Statement stmt = conn.createStatement();
		) 
		{
			String drop =
				"drop database if exists shop;";
			String create = 
				"create database shop;";
			
			stmt.executeUpdate(drop);
			stmt.executeUpdate(create);
			System.out.println("Database \"shop\" created");
			
		} catch(java.sql.SQLException ex) {
			ex.printStackTrace();
		}
		
		
		this.name = "MySQLDB";
	}

	public void products() {
		try (
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/shop?useUnicode=true"
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
				"jdbc:mysql://localhost/shop?useUnicode=true"
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
	
	
}
