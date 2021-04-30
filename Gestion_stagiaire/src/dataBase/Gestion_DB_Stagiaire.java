package dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class Gestion_DB_Stagiaire {
	String query;
	Statement statement;
	ResultSet rset;
	Connection connexion;
	public Gestion_DB_Stagiaire(){
		DB_Connection.connect();
		connexion=DB_Connection.getConnexion();
		rset=null;
	}
	public DefaultTableModel rechercher(String entree){
		
		DefaultTableModel dt = new DefaultTableModel();
		
		try{
			dt.addColumn("CIN");
			dt.addColumn("Nom");
			dt.addColumn("Prenom");
			dt.addColumn("Date naissance");
			dt.addColumn("Université");
			dt.addColumn("Specialité");
			dt.addColumn("Encadreur");
			dt.addColumn("email");
			dt.addColumn("Téléphone");
			dt.addColumn("Début de stage");
			dt.addColumn("Fin de stage");
			String[] tab=entree.split(" ");
			statement=connexion.createStatement();
			
			for(int i=0,longeur=tab.length;i<longeur;i++){
				query="SELECT * FROM ajout WHERE CINStagiaire='"+tab[i]+"' OR nomStagiaire='"+tab[i]+"' OR prenomStagiaire='"+tab[i]+"'";
				rset=statement.executeQuery(query);
			}
			
			while(rset.next()){
				Object []tableau={rset.getString("CINStagiaire"),
						rset.getString("nomStagiaire"),
						rset.getString("prenomStagiaire"),
						rset.getString("naissanceStagiaire"),
						rset.getString("universiteStagiaire"),
						rset.getString("specialtyStagiaire"),
						rset.getString("encadreurStagiaire"),
						rset.getString("email"),
						rset.getString("telephone"),
						rset.getString("debut_de_stage"),
						rset.getString("fin_de_stage")
			};
				dt.addRow(tableau);
				
				
			}
			
			
		}
		
		catch(SQLException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);
			
		}
		
		return dt;
	}
	public DefaultTableModel rechercher(){
		
		DefaultTableModel dt = new DefaultTableModel();
		
		try{
			dt.addColumn("CIN");
			dt.addColumn("Nom");
			dt.addColumn("Prenom");
			dt.addColumn("Date naissance");
			dt.addColumn("Université");
			dt.addColumn("Specialité");
			dt.addColumn("Encadreur");
			dt.addColumn("email");
			dt.addColumn("Téléphone");
			dt.addColumn("Début de stage");
			dt.addColumn("Fin de stage");
			
			statement=connexion.createStatement();
			rset=statement.executeQuery("SELECT * FROM ajout");
			
			while(rset.next()){
				Object []tableau={
						rset.getString("CINStagiaire"),
						rset.getString("nomStagiaire"),
						rset.getString("prenomStagiaire"),
						rset.getString("naissanceStagiaire"),
						rset.getString("universiteStagiaire"),
						rset.getString("specialtyStagiaire"),
						rset.getString("encadreurStagiaire"),
						rset.getString("email"),
						rset.getString("telephone"),
						rset.getString("debut_de_stage"),
						rset.getString("fin_de_stage")
				};
				dt.addRow(tableau);
			}
			
			
		}
		
		catch(SQLException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"Not Found","Message d’avertissement",JOptionPane.ERROR_MESSAGE);
		}
		
		return dt;
	}
	public void ajouterStagiaire(String cin,String nom,String prenom,String naissance,String universite,String specialty, String encadreur, String email, String telephone, String debut_de_stage, String fin_de_stage){
		try{
			statement=connexion.createStatement();
			query="INSERT INTO ajout  VALUES('"+cin+"','"+nom+"','"+prenom+"','"+naissance+"','"+universite+"','"+specialty+"','"+encadreur+"', '"+email+"','"+telephone+"','"+debut_de_stage+"','"+fin_de_stage+"' )";
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Successfull Add","Message d’avertissement",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null,"CIN déjà existante","Message d’erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void supprimerStagiaire(String cin){
		try{
			statement=connexion.createStatement();
			query="DELETE FROM ajout WHERE CINStagiaire='"+cin+"'";
			statement.executeUpdate(query);
			if(query.isEmpty())
				JOptionPane.showMessageDialog(null,"Unfortunately Failed","Message d’avertissement",JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"Successfull DELETE","Message d’avertissement",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"ERROR","Message d’avertissement",JOptionPane.ERROR_MESSAGE);
			
		}
	}
	public void modifierStagiaire(String[] contenu){
		try{
			statement=connexion.createStatement();
			query="UPDATE ajout SET nomStagiaire='"+contenu[1]+"',prenomStagiaire='"+contenu[2]+"' ,naissanceStagiaire='"+contenu[3]+"',universiteStagiaire='"+contenu[4]+"',specialtyStagiaire='"+contenu[5]+"', naissanceStagiaire='"+contenu[6]+"',email='"+contenu[7]+"',telephone='"+contenu[8]+"',debut_d_stage='"+contenu[9]+"',fin_de_stage='"+contenu[10]+"' WHERE CINStagiaire='"+contenu[0]+"'";
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Successfull Modification","Message d’avertissement",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null,"ERROR","Message d’avertissement",JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean verifier(String login,String password){
		boolean test=false;
		String log = null,pass=null;
		try{
			statement=connexion.createStatement();
			query="SELECT login,password FROM personnel";
			rset=statement.executeQuery(query);
			while(rset.next()|| (test==true)){
				log=rset.getString("login");
				pass=rset.getString("password");
				if(log.equals(login) && pass.equals(password))
					test=true;		
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "ERROR", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
		}
		return test;

	}
	
}
