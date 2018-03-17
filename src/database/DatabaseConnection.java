package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DatabaseConnection {
	private String path="jdbc:mysql://localhost/HomeworkUpload?useUnicode=true&characterEncoding=utf-8";
	private String user="root";
	private String password="123456";
	private Connection connection;
	public  DatabaseConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection=DriverManager.getConnection(path,user,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} 
		
	}
	public Connection connection(){
		return this.connection;
	}
}
