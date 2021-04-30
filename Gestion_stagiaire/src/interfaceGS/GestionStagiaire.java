package interfaceGS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.LayoutStyle;

import boutonColor.ButtonColor;
import dataBase.Gestion_DB_Stagiaire;

public class GestionStagiaire extends JFrame {

	Gestion_DB_Stagiaire stagiaire=new Gestion_DB_Stagiaire();
	private JPanel contentPane;

	private JTextField nomStagiaireField;
	private JTextField prenomStagiaireField;
	private JTextField CINField;
	private JTextField naissanceField;
	private JTextField universiteField;
	private JTextField specialtyField;
	private JTextField encadreurField;
	private JTextField emailField;
	private JTextField telephoneField;
	private JTextField debut_de_stageField;
	private JTextField fin_de_stageField;
	private JTextField CINStagiaireRechField;
	private JTextField horloge;
	private JButton ajouter;
	private JButton supprimer;
	private JButton modifier;
	private JButton valider ;

	private JLabel nomStagiaire;
	private JLabel prenomStagiaire;
	private JLabel CIN; 
	private JLabel naissance;
	private JLabel Specialite;
	private JLabel CINStagiaireRech;
	private JLabel universite;
	private JLabel encadreur;
	private JLabel email;
	private JLabel telephone;
	private JLabel debut_de_stage;
	private JLabel fin_de_stage;
	private JXSearchField searchField;
	private JScrollPane scrollPane;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					GestionStagiaire frame = new GestionStagiaire();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionStagiaire() {
		setTitle("Gestion Stagiaire");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1305, 620);
		setLocationRelativeTo(null);
		contentPane = new Panneau();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		ajouter=new ButtonColor("Ajout",Color.BLACK);
		ajouter.setFont(new Font("Tahoma", Font.BOLD, 14));
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String cin,nom,prenom,naissance,universite,specialty,encadreur,email,telephone,debut_de_stage,fin_de_stage;
				Object source=evt.getSource();
				if(source==ajouter){
					cin=CINField.getText();
					nom=nomStagiaireField.getText().toUpperCase();
					prenom=prenomStagiaireField.getText();
					naissance=naissanceField.getText();
					universite=universiteField.getText();
					specialty=specialtyField.getText();
					encadreur=encadreurField.getText();
					email=emailField.getText();
					telephone=telephoneField.getText();
					debut_de_stage=debut_de_stageField.getText();
					fin_de_stage=fin_de_stageField.getText();
					if(!cin.equals("")) {
						stagiaire.ajouterStagiaire(cin,nom,prenom,naissance,universite,specialty,encadreur, email, telephone, debut_de_stage, fin_de_stage);
						table.setModel(stagiaire.rechercher());
						CINField.setText("");
						nomStagiaireField.setText("");
						prenomStagiaireField.setText("");
						naissanceField.setText("");
						universiteField.setText("");
						specialtyField.setText("");
						encadreurField.setText("");
						emailField.setText("");
						telephoneField.setText("");
						debut_de_stageField.setText("");
						fin_de_stageField.setText("");
					}else
						JOptionPane.showMessageDialog(null,"La CIN ne doit pas être vide","Message d’erreur",JOptionPane.ERROR_MESSAGE);
				}
			}});
		ajouter.setBounds(10, 336+38*5, 227, 23);
		getContentPane().add(ajouter);

		supprimer = new ButtonColor("Supprimer",Color.BLACK);
		supprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object source=evt.getSource();
				if(source==supprimer){
					JOptionPane.showMessageDialog(null,"Vous voulez continuer","Message d’avertissement",JOptionPane.YES_NO_CANCEL_OPTION);
					String cin=CINStagiaireRechField.getText();
					stagiaire.supprimerStagiaire(cin);
					CINStagiaireRechField.setText("");
					table.setModel(stagiaire.rechercher());
				}
			}
		});
		supprimer.setBounds(285, 39, 115, 23);
		getContentPane().add(supprimer);

		horloge = new JTextField();
		horloge.setForeground(Color.LIGHT_GRAY);
		horloge.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 34));
		horloge.setEditable(false);
		horloge.setBounds(10, 11, 156, 36);
		getContentPane().add(horloge);
		horloge.setColumns(5);

		nomStagiaire = new JLabel("Nom Stagiaire");
		nomStagiaire.setBounds(10, 90, 99, 23);
		getContentPane().add(nomStagiaire);

		prenomStagiaire = new JLabel("Prenom Stagiaire");
		prenomStagiaire.setBounds(10, 133, 99, 14);
		getContentPane().add(prenomStagiaire);

		CIN = new JLabel("CIN Stagiaire");
		CIN.setBounds(10, 179, 79, 14);
		getContentPane().add(CIN);

		naissance = new JLabel("Date Naissance");
		naissance.setBounds(10, 221, 89, 14);
		getContentPane().add(naissance);

		universite = new JLabel("Universit\u00E9");
		universite.setBounds(10, 263, 79, 14);
		getContentPane().add(universite);

		Specialite = new JLabel("Sp\u00E9cialit\u00E9");
		Specialite.setBounds(11, 301, 78, 14);
		getContentPane().add(Specialite);

		encadreur = new JLabel("Encadreur");
		encadreur.setBounds(11, 339, 78, 14);
		getContentPane().add(encadreur);

		email = new JLabel("Email");
		email.setBounds(11, 339+38*1, 78, 14);
		getContentPane().add(email);

		telephone = new JLabel("T\u00E9l\u00E9phone");
		telephone.setBounds(11, 339+38*2, 78, 14);
		getContentPane().add(telephone);

		debut_de_stage = new JLabel("D\u00E9but stage");
		debut_de_stage.setBounds(11, 339+38*3, 78, 14);
		getContentPane().add(debut_de_stage);

		fin_de_stage = new JLabel("Fin de stage");
		fin_de_stage.setBounds(11, 339+38*4, 78, 14);
		getContentPane().add(fin_de_stage);

		nomStagiaireField = new JTextField();
		nomStagiaireField.setBounds(109, 91, 128, 23);
		getContentPane().add(nomStagiaireField);
		nomStagiaireField.setColumns(10);

		prenomStagiaireField = new JTextField();
		prenomStagiaireField.setBounds(109, 130, 128, 23);
		getContentPane().add(prenomStagiaireField);
		prenomStagiaireField.setColumns(10);

		CINField = new JTextField();
		CINField.setBounds(109, 176, 128, 23);
		getContentPane().add(CINField);
		CINField.setColumns(10);

		naissanceField = new JTextField();
		naissanceField.setBounds(109, 218, 128, 23);
		getContentPane().add(naissanceField);
		naissanceField.setColumns(10);

		universiteField = new JTextField();
		universiteField.setBounds(109, 260, 128, 23);
		getContentPane().add(universiteField);
		universiteField.setColumns(10);

		specialtyField = new JTextField();
		specialtyField.setBounds(109, 298, 128, 23);
		getContentPane().add(specialtyField);
		specialtyField.setColumns(10);

		encadreurField = new JTextField();
		encadreurField.setBounds(109, 336, 128, 23);
		getContentPane().add(encadreurField);
		encadreurField.setColumns(10);

		emailField = new JTextField();
		emailField.setBounds(109, 336+38*1, 128, 23);
		getContentPane().add(emailField);
		emailField.setColumns(10);

		telephoneField = new JTextField();
		telephoneField.setBounds(109, 336+38*2, 128, 23);
		getContentPane().add(telephoneField);
		telephoneField.setColumns(10);

		debut_de_stageField = new JTextField();
		debut_de_stageField.setBounds(109, 336+38*3, 128, 23);
		getContentPane().add(debut_de_stageField);
		debut_de_stageField.setColumns(10);

		fin_de_stageField = new JTextField();
		fin_de_stageField.setBounds(109, 336+38*4, 128, 23);
		getContentPane().add(fin_de_stageField);
		fin_de_stageField.setColumns(10);

		CINStagiaireRech = new JLabel("CIN Stagiaire");
		CINStagiaireRech.setBounds(284, 14, 79, 14);
		getContentPane().add(CINStagiaireRech);

		CINStagiaireRechField = new JTextField();
		CINStagiaireRechField.setColumns(10);
		CINStagiaireRechField.setBounds(363, 11, 148, 23);
		getContentPane().add(CINStagiaireRechField);

		modifier = new ButtonColor("Modifier",Color.BLACK);
		modifier.setFont(new Font("Tahoma", Font.BOLD, 12));
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cin=CINStagiaireRechField.getText();
				table.setModel(stagiaire.rechercher(cin));
				CINStagiaireRechField.setText("");
			}
		});
		modifier.setBounds(412, 39, 99, 23);
		getContentPane().add(modifier);


		searchField = new JXSearchField("CIN ou Nom/Prenom");
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object source=evt.getSource();
				if(source==searchField){
					String entree=searchField.getText();
					DefaultTableModel sortie = stagiaire.rechercher(entree);
					if(entree.isEmpty() || searchField.getText().equals("CIN ou Nom/Prenom"))
						table.setModel(stagiaire.rechercher());
					else
						table.setModel(sortie);

				}
			}
		});
		searchField.setBounds(595, 11, 156, 25);
		searchField.setColumns(10);
		searchField.setLayoutStyle(LayoutStyle.MAC);
		getContentPane().add(searchField);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 90,539+500,53*8);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowHeight(34);
		table.setFont(new Font("Tahoma", Font.BOLD, 12));
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"CIN", "Nom", "Prenom", "Date naissance", "Université", "Spécialité", "Encadreur", "email", "Téléphone", "Début de stage", "Fin de stage"
				}
				));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/**/	table.setModel(stagiaire.rechercher());
		scrollPane.setViewportView(table);
		Dimension d = table.getSize();
		table.setPreferredScrollableViewportSize(d);

		valider = new ButtonColor("Valider",Color.BLACK);
		valider.setFont(new Font("Tahoma", Font.BOLD, 12));
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object source=evt.getSource();
				if(source==valider){
					String []tabContenu=new String[11];
					for(int i=0;i<table.getColumnCount();i++){
						Object valeur; String contenu;
						try {
							valeur = table.getValueAt(table.getSelectedRow(),i);
							contenu=valeur.toString();
							tabContenu[i]=contenu;

						} catch (NullPointerException e) {
							tabContenu[i]="";
						} catch (ArrayIndexOutOfBoundsException f) {
							break;
						}
					}
					stagiaire.modifierStagiaire(tabContenu);
	/**/			table.setModel(stagiaire.rechercher());
				}
			}
		});
		valider.setBounds(383+200, 110+scrollPane.getHeight(), 227, 25);
		valider.setVisible(true);
		getContentPane().add(valider);

		Timer t = new Timer(1000, new ClockListener());
		t.start();

	}
	class ClockListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			horloge.setText(df.format(Calendar.getInstance().getTime()));
		}
	}
	class Panneau extends JPanel{
		public void paintComponent(Graphics g){
			try {
				super.paintComponents(g);
				Graphics2D g2d = (Graphics2D)g;
				Image image_ecran = ImageIO.read(new File("backGround.jpg"));
				g2d.drawImage(image_ecran, 0, 0,this.getWidth(),this.getHeight(),this);
				Color Snow3 =new Color(205,201,201);
				g2d.setColor(Snow3);
				g2d.fillRoundRect(3,87,240,480,15,15);
				g2d.setColor(Snow3);
				g2d.fillRoundRect(280,7,240,60,15,15);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

