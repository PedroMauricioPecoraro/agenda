package br.edu.unicapital.agenda.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	
	static {  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (Exception e) {  
            System.out.println("ERRO");  
            e.printStackTrace();  
        }  
    }  
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda", "root", "monicapecoraro22");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void testarConexao() throws SQLException {
		Connection c = getConnection();
		System.out.println("ok!");
		c.close();
	}
	
	public static void main(String[] args) {
		try {
			testarConexao();
		} catch (SQLException e) {
			System.out.println("Deu erro");
		}
	}
}
