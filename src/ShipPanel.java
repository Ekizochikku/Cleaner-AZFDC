
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

public class ShipPanel extends JPanel {
	//The current damage type being applied
	private int currentDMGType = 0; //0 = HE, 1 = AP
	private String shipTypeName;
	private String currentShipName;
	private String currentColorSelected;
	private String currentWeaponType;
	//The current selected weapon name
	private String currentWeaponName = null;
	private JComboBox shipNameCBox, shipTypeCBox;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ShipPanel(MainGUI guiVariables) throws FileNotFoundException, IOException {
		setLayout(null);
		JLabel lblShipType = new JLabel("Ship Type:");
		lblShipType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShipType.setBounds(363, 82, 86, 25);
		add(lblShipType);
		
		
		
		//might be done in the main method not too sure
		//ship types will need to be changed to it's actual name
		String[] shipTypeList = {"Destroyers", "Light Cruisers", "Heavy Cruisers", "Large Cruisers", "Battlecruisers", "Battleships", "Aviation Battleships", "Monitors", "Submarines", "Aircraft Carriers", "Light Aircraft Carriers"};
		shipTypeCBox = new JComboBox<Object>(shipTypeList);
		shipTypeCBox.setBounds(363, 127, 161, 25);
		shipTypeCBox.setMaximumRowCount(10);
		shipTypeCBox.setSelectedIndex(0);
		add(shipTypeCBox);
		shipTypeCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					guiVariables.setShipTypeName((String) shipTypeCBox.getSelectedItem());
					GUIUtility.insertNames(shipNameCBox,true, guiVariables.getShipTypeName());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		});
		
		
		guiVariables.setShipTypeName((String) shipTypeCBox.getSelectedItem());
				
		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShipName.setBounds(534, 82, 95, 25);
		add(lblShipName);
		
		//ship names are added based on the list that is selected
		
		
		shipNameCBox = new JComboBox();
		shipNameCBox.setBounds(534, 127, 191, 25);
		add(shipNameCBox);
		GUIUtility.insertNames(shipNameCBox,true, guiVariables.getShipTypeName());
		
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
		
		JTextPane factionTxt = new JTextPane();
		factionTxt.setEditable(false);
		factionTxt.setBounds(363, 217, 95, 31);
		add(factionTxt);
		
		JTextPane classTxt = new JTextPane();
		classTxt.setEditable(false);
		classTxt.setBounds(534, 217, 95, 31);
		add(classTxt);
		
		JTextPane healthTxt = new JTextPane();
		healthTxt.setEditable(false);
		healthTxt.setBounds(363, 306, 95, 31);
		add(healthTxt);
		
		JTextPane firepowerTxt = new JTextPane();
		firepowerTxt.setEditable(false);
		firepowerTxt.setBounds(534, 306, 95, 31);
		add(firepowerTxt);
		
		JTextPane torpedoTxt = new JTextPane();
		torpedoTxt.setEditable(false);
		torpedoTxt.setBounds(363, 402, 95, 31);
		add(torpedoTxt);
		
		JTextPane antiAirTxt = new JTextPane();
		antiAirTxt.setEditable(false);
		antiAirTxt.setBounds(534, 402, 95, 31);
		add(antiAirTxt);
		
		JTextPane aviationTxt = new JTextPane();
		aviationTxt.setEditable(false);
		aviationTxt.setBounds(682, 402, 95, 31);
		add(aviationTxt);
		//future action listeners for the color stuff
		shipNameCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calculateButton.setEnabled(false);
				guiVariables.setCurrentShipName((String) shipNameCBox.getSelectedItem());
				List<String> attributes = new ArrayList<String>();
				int skillIdx = -1;
				switch(guiVariables.getCurrentShipName()) {
				case "Aviation Battleships":
					skillIdx = 14;
					break;
				case "Aircraft Carriers":
					skillIdx = 15;
					break;
				case "Light Aircraft Carriers":
					skillIdx = 15;
					break;
				default:
					skillIdx = 13;
					break;
				}
				
				//Put this in the skill tab
				/*
				currentSkills.clear();
				if(skillIdx != -1) {
					for(int i = skillIdx; i < skillIdx + 5; i++) {
						String skill = (String) attributes.get(i);
						System.out.println(skill);
						if(!skill.equals("NULL")) {
							currentSkills.add(skill);
						}
					}
				} 
				 * updateActiveSkills();
				boolean skillExist = currentSkills.contains("Just Gettin' Fired Up");
				System.out.println("Skill check " + skillExist + " current ship name " + currentShipName);
				//System.out.println("the current ship name: " + currentShipName);
				 
				*/
				
				//buttons for ship specific skills
				/*
				if(currentShipName.equals("Roon")) {
					//System.out.println("Not entering this check!!!");
					//Need to be changed so they're in array to reduce redundancy
					buttonHE.setEnabled(true);
					buttonAP.setEnabled(true);
					
					buttonHE.setToolTipText(null);
					buttonAP.setToolTipText(null);
				    for (JRadioButton btn : colorButtons) {
				         btn.setSelected(false);
				         btn.setEnabled(false);
				    }
					
					//Test reminder: Friedrich is on BB ship type
				} else if(currentShipName.equals("Friedrich der Grosse")) {
					buttonHE.setEnabled(false);
					buttonAP.setEnabled(false);
					
					//Any default for radio buttons?
					evenRadioButton.setSelected(true);
					evenOdd = 1;
					evenRadioButton.setEnabled(true);
					oddRadioButton.setEnabled(true);
					oddRadioButton.setToolTipText(null);
					evenRadioButton.setToolTipText(null);
				    for (JRadioButton btn : colorButtons) {
				         btn.setSelected(false);
				         btn.setEnabled(false);
				    }
					
				
				//checking for ships that have Muse
				//currently they're aren't any btw
				//this is an else if, i'm assuming there are no muse ships that are named Roon, Friedrich, Alabama
				//will need to be changed if otherwise
				}else if(currentShipName.indexOf("Muse") != -1) {
					
					for (JRadioButton btn : colorButtons) {
				         btn.setEnabled(true);
				    }
					
					
				} else if(currentShipName.equals("Alabama") && skillExist) {
					nodesKilledTextField.setEnabled(true);
					nodeKilledLabel.setEnabled(true);
					isArmorBroken.setEnabled(true);
					
				} else {
					buttonHE.setSelected(true);
					buttonHE.setEnabled(false);
					buttonAP.setEnabled(false);
					buttonGroup.clearSelection();
					
					buttonHE.setToolTipText("HE and AP rounds are only selectable with 'Roon' ");
					buttonAP.setToolTipText("HE and AP rounds are only selectable with 'Roon' ");
					oddRadioButton.setToolTipText("Even and Odd rounds are only selectable with Friedrich der Grosse");
					evenRadioButton.setToolTipText("Even and Odd rounds are only selectable when Friedrich der Grosse");
					
				    for (JRadioButton btn : colorButtons) {
				         btn.setSelected(false);
				         btn.setEnabled(false);
				    }
				    //for some reason setSelected above doesnt work so this has to be done
					colorButtonGroup.clearSelection();
					
					evenRadioButton.setEnabled(false);
					oddRadioButton.setEnabled(false);
					evenOdd = -1;
					//System.out.println("The current even odd:" + evenOdd);
					nodesKilledTextField.setEnabled(false);
					nodesKilledTextField.setText("");
					nodeKilledLabel.setEnabled(false);
					isArmorBroken.setEnabled(false);
					isArmorBroken.setSelected(false);
				}
				*/ 
				if(guiVariables.getCurrentShipName() != "") {//					try {
////						GUIUtility.insertType(weapon1TypeCBox, 4, shipTypeName, currentShipName, 1);
////						currentWeaponType = (String) weapon1TypeCBox.getSelectedItem();
////						GUIUtility.insertNames(weapon1NameCBox, false, currentWeaponType);
//						/*
//						GUIUtility.insertType(weaponTypeCBox2, 5, shipTypeName, currentShipName, 2);
//						currentWeaponTypeSlot2 = (String) weaponTypeCBox2.getSelectedItem();
//						GUIUtility.insertNames(weaponNameSlot2, false, currentWeaponTypeSlot2);
//						
//						if(shipTypeName == "CV" || shipTypeName == "CVL") {
//							GUIUtility.enableDisableSlot3(lblNewLabel, weaponTypeCBox3, lblWeaponTypeSlot, weaponNameSlot3,
//									lblSlotDamage, slot3Pane, true);
//							GUIUtility.insertType(weaponTypeCBox3, 6, shipTypeName, currentShipName, 3);
//							currentWeaponTypeSlot3 = (String) weaponTypeCBox3.getSelectedItem();
//							GUIUtility.insertNames(weaponNameSlot3, false, currentWeaponTypeSlot3);
//
//						} else {
//							GUIUtility.enableDisableSlot3(lblNewLabel, weaponTypeCBox3, lblWeaponTypeSlot, weaponNameSlot3,
//									lblSlotDamage, slot3Pane, false);
//						}	
//						*/					
//						
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}
			}
		});
		
		//Hard Coded initial screen of weapon type will need to change later most likely.
		String[] weaponTypeList1 = {"DDGUNS"};
		
		/*code we'll need to have in the same panel (ammo types) 
		
		// Group for Muse (Notes)
		colorGroup.add(redRadio = new JRadio("Red"));
		colorGroup.add(blueRadio = new JRadio("Blue"));
		colorGroup.add(purpleRadio = new JRadio("Purple"));
		
		//Array to enable disable  group (seriously swing needs to let you interact with  groups instead of doing workarounds like this)
		//now that I know about this might change up code later and add everything into the array
	    colors = new JRadio[] {redRadio, blueRadio, purpleRadio};
	    for (JRadio btn : colors) {
	         btn.setEnabled(false);
	         btn.setToolTipText("Color Ammo Types are only applicable to 'Muse' ships");
	         //radio  action listener to pass in name
	 		 btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentColorSelected = btn.getText();
					System.out.println(currentColorSelected);
				}
			});
	    }
	    
		
		//Music Note label
		label = new JLabel("\u266A");
		
		//Tool Tips
		label.setToolTipText("Color Ammo Types are only applicable to 'Muse' ships");
		HE.setToolTipText("HE and AP rounds are only selectable with 'Roon'");
		AP.setToolTipText("HE and AP rounds are only selectable with 'Roon'");
		
		oddRadio.setToolTipText("Even and Odd rounds are only selectable with Friedrich der Grosse");
		evenRadio.setToolTipText("Even and Odd rounds are only selectable when Friedrich der Grosse");
		 */

	}
	/**
	 * get get method for type and ship names 
	 */
	
	public String getShipTypeName() {
		return shipTypeName;
	}
	
	public String getShipName() {
		return currentShipName;
	}
	public int getDamageType() {
		return currentDMGType;
	}
	public String getCurrentColor() {
		return currentColorSelected;
	}
}

