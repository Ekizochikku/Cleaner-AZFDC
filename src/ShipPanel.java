
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.JTextPane;

/**
 * Panel to display information of selected ship by user. Allows user to select a ship to be
 * used to determine how it will interact in combat.
 * 
 * @author Walter Hanson
 *
 */
public class ShipPanel extends JPanel {
	//The ship type of the selected ship
	private String shipTypeName;
	//The name of the selected ship
	private String currentShipName;
	//ComboBoxs for user to select the ship type and ship name to use for calculations
	private JComboBox shipNameCBox, shipTypeCBox;
	//The current ship selected
	private ShipFile currentShip;
	//Main application
	private MainGUI gui;
	//Backend methods
	private GUIUtility gUtil;
	//Faction of current ship
	private JTextPane factionTxt;
	//Class of current ship
	private JTextPane classTxt;
	//Health of current ship
	private JTextPane healthTxt;
	//Firepower of current ship
	private JTextPane firepowerTxt;
	//Torpedo stats of current ship
	private JTextPane torpedoTxt;
	//Anti Air of current ship
	private JTextPane antiAirTxt;
	//Aviation stats of current ship
	private JTextPane aviationTxt;
	
	/**
	 * Create the panel.
	 * 
	 * @param guiVariables Main application class so information can be transmitted between classes.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @author Walter Hanson
	 */
	public ShipPanel(MainGUI guiVariables) throws FileNotFoundException, IOException {
		setLayout(null);
		JLabel lblShipType = new JLabel("Ship Type:");
		lblShipType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShipType.setBounds(363, 82, 86, 25);
		add(lblShipType);
		
		gui = guiVariables;
		gUtil = new GUIUtility();
		//might be done in the main method not too sure
		//ship types will need to be changed to it's actual name
		String[] shipTypeList = {"Destroyers", "Light Cruisers", "Heavy Cruisers", "Large Cruisers", "Battlecruisers", "Battleships", "Aviation Battleships", "Monitors", "Submarines", "Aircraft Carriers", "Light Aircraft Carriers"};
		shipTypeCBox = new JComboBox<Object>(shipTypeList);
		shipTypeCBox.setBounds(363, 127, 161, 25);
		shipTypeCBox.setMaximumRowCount(10);
		add(shipTypeCBox);
		shipTypeCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					shipTypeName = (String) shipTypeCBox.getSelectedItem();
					GUIUtility.insertNames(shipNameCBox,true, shipTypeName);
					currentShipName = (String) shipNameCBox.getSelectedItem();
					gui.setCurrentShip(null);
					gui.setShipType(shipTypeName);
					
					setAttributes();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		});
		
		shipTypeName = (String) shipTypeCBox.getSelectedItem();
				
		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShipName.setBounds(534, 82, 95, 25);
		add(lblShipName);
		
		//ship names are added based on the list that is selected
		
		
		shipNameCBox = new JComboBox();
		shipNameCBox.setBounds(534, 127, 191, 25);
		add(shipNameCBox);
		GUIUtility.insertNames(shipNameCBox, true, shipTypeName);
//		currentShipName = (String) shipNameCBox.getSelectedItem();
		
		JLabel lblFaction = new JLabel("Faction:");
		lblFaction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFaction.setBounds(363, 181, 86, 25);
		add(lblFaction);
		
		JLabel lblClass = new JLabel("Class:");
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClass.setBounds(534, 181, 86, 25);
		add(lblClass);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHealth.setBounds(363, 270, 86, 25);
		add(lblHealth);
		
		JLabel lblFirepower = new JLabel("Firepower:");
		lblFirepower.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirepower.setBounds(534, 270, 86, 25);
		add(lblFirepower);
		
		JLabel lblTorpedo = new JLabel("Torpedo:");
		lblTorpedo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTorpedo.setBounds(363, 366, 86, 25);
		add(lblTorpedo);
		
		JLabel lblAntiAir = new JLabel("Anti Air:");
		lblAntiAir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAntiAir.setBounds(534, 366, 86, 25);
		add(lblAntiAir);
		
		JLabel lblAviation = new JLabel("Aviation:");
		lblAviation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAviation.setBounds(682, 366, 86, 25);
		add(lblAviation);
		
		factionTxt = new JTextPane();
		factionTxt.setEditable(false);
		factionTxt.setBounds(363, 217, 95, 31);
		add(factionTxt);
		
		classTxt = new JTextPane();
		classTxt.setEditable(false);
		classTxt.setBounds(534, 217, 140, 31);
		add(classTxt);
		
		healthTxt = new JTextPane();
		healthTxt.setEditable(false);
		healthTxt.setBounds(363, 306, 95, 31);
		add(healthTxt);
		
		firepowerTxt = new JTextPane();
		firepowerTxt.setEditable(false);
		firepowerTxt.setBounds(534, 306, 95, 31);
		add(firepowerTxt);
		
		torpedoTxt = new JTextPane();
		torpedoTxt.setEditable(false);
		torpedoTxt.setBounds(363, 402, 95, 31);
		add(torpedoTxt);
		
		antiAirTxt = new JTextPane();
		antiAirTxt.setEditable(false);
		antiAirTxt.setBounds(534, 402, 95, 31);
		add(antiAirTxt);
		
		aviationTxt = new JTextPane();
		aviationTxt.setEditable(false);
		aviationTxt.setBounds(682, 402, 95, 31);
		add(aviationTxt);
		AutoCompletion.enable(shipNameCBox);
		//future action listeners for the color stuff
		shipNameCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calculateButton.setEnabled(false);
				
				currentShipName = (String) shipNameCBox.getSelectedItem();
//				System.out.println("Changing the ship name The current ship name is: " 
//				+ guiVariables.getCurrentShipName());
//				System.out.println("Changing the ship type The current ship type is: " 
//						+ guiVariables.getShipTypeName());
				try {
					if(currentShipName != "") {
						currentShip = new ShipFile(currentShipName, shipTypeName);
						gui.setCurrentShip(currentShip);
						gui.setShipType(shipTypeName);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int skill = 1;
//				System.out.println("Skill is " + currentShip.getSkill(skill));
//							******TESTING PURPOSE**************
//				while(!currentShip.getSkill(skill).equals("NULL")) {
//					System.out.println(currentShip.getShipName() + ": " + currentShip.getSkill(skill));
//					try {
//						if(currentShip.getSkill(skill).equals("All Out Assault, Open Fire!")) {
//							skill++;
//							continue;
//						}
//						new Skill(currentShip.getSkill(skill));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					skill++;
//				}
				
				setAttributes();

			}
		});
		shipNameCBox.setSelectedIndex(0);
		gui.setCurrentShip(null);
	}
	//method for when panel changes to weapon
	public void sendShipInfo(MainGUI gui) {
		gui.setCurrentShip(currentShip);
		
	}
	
	/**
	 * Set up text fields to display the stats of the current selected ship.
	 * 
	 * @author Walter Hanson
	 */
	private void setAttributes() {
		if(currentShipName == "" || shipTypeName == null) {
			factionTxt.setText("");
			classTxt.setText("");
			healthTxt.setText("");
			firepowerTxt.setText("");
			torpedoTxt.setText("");
			antiAirTxt.setText("");
			aviationTxt.setText("");
			return;
		}
		factionTxt.setText(currentShip.getFaction());
		classTxt.setText(currentShip.getShipClass());
		healthTxt.setText(Double.toString(currentShip.getHealth()));
		firepowerTxt.setText(Double.toString(currentShip.getFirepower()));
		torpedoTxt.setText(Double.toString(currentShip.getTorpedo()));
		antiAirTxt.setText(Double.toString(currentShip.getAA()));
		aviationTxt.setText(Double.toString(currentShip.getAviation()));

	}

}

