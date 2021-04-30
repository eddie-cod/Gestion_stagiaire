package authentication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import boutonColor.ButtonColor;
import dataBase.Gestion_DB_Stagiaire;
import interfaceGS.GestionStagiaire;


public class Authentication extends JFrame {
	
	private JPanel contentPane;
	private JLabel authentication; 
	private JLabel loginPersonnel;
	private JTextField loginField;
	private JLabel passwordPersonnel;
	private JPasswordField passwordField;
	private JButton loginAuth;
	
	GestionStagiaire stagiaire;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Authentication frame = new Authentication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close(){
		this.dispose();
	}
	
	public void verifierEnter(){
		Gestion_DB_Stagiaire stag=new Gestion_DB_Stagiaire();
		String login = null;
		login=loginField.getText();
		char[] passChar=passwordField.getPassword();
		String pass=new String(passChar);
		if((!login.equals(""))&&(!pass.equals(""))){
			if(stag.verifier(login,pass)==true){
				close();
				stagiaire=new GestionStagiaire();
				stagiaire.setVisible(true);
				
			}
			else
				JOptionPane.showMessageDialog(null,"login or password is inncorrect","Alert Message",JOptionPane.ERROR_MESSAGE);
			
		}	
		else
			JOptionPane.showMessageDialog(null,"login or password empty","Alert Message",JOptionPane.ERROR_MESSAGE);
	}
	
	
		
	
	
	/**
	 * Create the frame.
	 */
	public Authentication() {
		setTitle("Authentication");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(361,230);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.white);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		authentication = new JLabel("Authentication");
		authentication.setForeground(Color.BLUE);
		authentication.setFont(new Font("Tahoma", Font.BOLD, 24));
		authentication.setBounds(88, 11, 183, 23);
		contentPane.add(authentication);
		
		loginPersonnel = new JLabel("Login");
		loginPersonnel.setForeground(Color.BLUE);
		loginPersonnel.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginPersonnel.setBounds(27, 78, 52, 17);
		contentPane.add(loginPersonnel);
		
		passwordPersonnel = new JLabel("Password");
		passwordPersonnel.setForeground(Color.BLUE);
		passwordPersonnel.setFont(new Font("Tahoma", Font.BOLD, 14));
		passwordPersonnel.setBounds(27, 118, 75, 14);
		contentPane.add(passwordPersonnel);
		
		loginField = new JTextField();
		loginField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode()==KeyEvent.VK_ENTER)
					verifierEnter();
			}
		});
		loginField.setBounds(116, 75, 123, 23);
		contentPane.add(loginField);
		loginField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode()==KeyEvent.VK_ENTER)
					verifierEnter();
			}
		});
		passwordField.setBounds(116, 112, 123, 23);
		contentPane.add(passwordField);
	
		loginAuth = new ButtonColor("Login",Color.BLACK);
		
		loginAuth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				Object source= evt.getSource();
				if(source==loginAuth)
					verifierEnter();		
			}	
		});
		loginAuth.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginAuth.setBounds(116, 150, 123, 23);
		contentPane.add(loginAuth);
	}
	
	
}
