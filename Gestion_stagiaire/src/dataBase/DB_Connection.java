package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;


public class DB_Connection {
	private static Connection connexion=null;
	private static String url ="jdbc:mysql://localhost:3306/";
	private static String dbName ="stagiaire";
	private static String driver="com.mysql.jdbc.Driver";
	private static String userName="root";
	private static String password ="";
	
	public static void connect(){
		try {
          
            Class.forName(driver);
            connexion= DriverManager.getConnection(url + dbName, userName, password);
		}
		catch(Exception e){
			 e.printStackTrace();
			 JOptionPane.showMessageDialog(null, "Mount DB and Try","Message d’avertissement", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	public static void discconnect(){
		try{
			Class.forName(driver);
			connexion.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Log out impossible","Message d’avertissement", JOptionPane.ERROR_MESSAGE);
		}
    }
	
	public static Connection getConnexion() {
		return connexion;
	}
}


