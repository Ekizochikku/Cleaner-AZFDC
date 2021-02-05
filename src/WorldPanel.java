import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;



public class WorldPanel extends JPanel {
	private MainGUI mainGUI;
	private String theCurrentWorld;
	private GUIUtility guiUtil;
	private int currentDangerLevel = 3;
	private String theCurrentEnemy;
	private JComboBox enemySelection;
	private JTextPane dangerLvlText;
	private JTextPane healthText;
	private JTextPane armorText;
	private JTextPane antiAirText;
	private JTextPane typeText;
	private JTextPane nationText;
	
	public WorldPanel(MainGUI gui) {
		setLayout(null);
		mainGUI = gui;
		guiUtil = new GUIUtility();
		currentDangerLevel = 3;
		JLabel lblChapter = new JLabel("Chapter:");
		lblChapter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChapter.setBounds(463, 77, 73, 25);
		add(lblChapter);
		
		JComboBox chapterSelection = new JComboBox();
		chapterSelection.setBounds(463, 113, 73, 25);
		add(chapterSelection);
		
		JLabel lblEnemyName = new JLabel("Enemy Name:");
		lblEnemyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnemyName.setBounds(563, 77, 121, 25);
		add(lblEnemyName);
		
		enemySelection = new JComboBox();
		enemySelection.setBounds(546, 112, 155, 25);
		add(enemySelection);
		
		JLabel lblDangerlvl = new JLabel("Danger Lvl:");
		lblDangerlvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDangerlvl.setBounds(463, 185, 100, 25);
		add(lblDangerlvl);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHealth.setBounds(587, 185, 73, 25);
		add(lblHealth);
		
		JLabel lblArmor = new JLabel("Armor:");
		lblArmor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArmor.setBounds(468, 269, 73, 25);
		add(lblArmor);
		
		JLabel lblAntiAir = new JLabel("Anti Air:");
		lblAntiAir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAntiAir.setBounds(587, 269, 73, 25);
		add(lblAntiAir);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(463, 373, 73, 25);
		add(lblType);
		
		JLabel lblNation = new JLabel("Nation:");
		lblNation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNation.setBounds(587, 373, 73, 25);
		add(lblNation);
		
		dangerLvlText = new JTextPane();
		dangerLvlText.setText("3");
		dangerLvlText.setEditable(false);
		dangerLvlText.setBounds(472, 213, 82, 25);
		add(dangerLvlText);
		
		healthText = new JTextPane();
		healthText.setEditable(false);
		healthText.setBounds(582, 213, 82, 25);
		add(healthText);
		
		armorText = new JTextPane();
		armorText.setEditable(false);
		armorText.setBounds(463, 305, 82, 25);
		add(armorText);
		
		antiAirText = new JTextPane();
		antiAirText.setEditable(false);
		antiAirText.setBounds(582, 305, 82, 25);
		add(antiAirText);
		
		typeText = new JTextPane();
		typeText.setEditable(false);
		typeText.setBounds(463, 409, 82, 25);
		add(typeText);
		
		nationText = new JTextPane();
		nationText.setEditable(false);
		nationText.setBounds(582, 409, 82, 25);
		add(nationText);
		
		theCurrentWorld = "1-1";
		//Populate it with all worlds found in the enemies.tsv file
		guiUtil.addWorldNum(chapterSelection);
		
		//theWorlds = guiUtil.getSpecificFileAndElement("Enemies.tsv", 0);
		//currentWorldCBox.setModel(new DefaultComboBoxModel<Object>(theWorlds.toArray()));
		
		chapterSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theCurrentWorld = (String) chapterSelection.getSelectedItem();
				//System.out.println(theCurrentWorld);
				try {
					//System.out.println("The current world: " + theCurrentWorld);
					guiUtil.insertEnemyNames(enemySelection, theCurrentWorld);
					setUpEnemies();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		ArrayList<String> theEnemies = new ArrayList<String>();
		try {
			theEnemies = guiUtil.getAllEnemiesForSpecificWorld(theCurrentWorld, "Enemies.tsv", 1, 2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Collections.sort(theEnemies);
		enemySelection.setModel(new DefaultComboBoxModel<Object>(theEnemies.toArray()));
		setUpEnemies();
		enemySelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setUpEnemies();
			}
		});
	}
	
	private void setUpEnemies() {
		theCurrentEnemy = (String) enemySelection.getSelectedItem();
		ArrayList<String> enemyParameters;
		//System.out.println("the current enemy is" + theCurrentEnemy);
		try {
			currentDangerLevel = guiUtil.getDangerLevel(theCurrentWorld);
			String theMaxDangerLevel = Integer.toString(currentDangerLevel);
			dangerLvlText.setText(theMaxDangerLevel);
			enemyParameters = new ArrayList<String>();
			enemyParameters = guiUtil.getEnemyParameters(theCurrentEnemy, theCurrentWorld);
			healthText.setText((String) enemyParameters.get(3));
			armorText.setText((String) enemyParameters.get(4));
			antiAirText.setText((String) enemyParameters.get(5));
			typeText.setText((String) enemyParameters.get(6));
			nationText.setText((String) enemyParameters.get(7));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
