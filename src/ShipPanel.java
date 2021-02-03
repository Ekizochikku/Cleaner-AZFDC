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
import java.awt.Component;
import javax.swing.Box;

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
	public ShipPanel() throws FileNotFoundException, IOException {
		setLayout(null);
		
		JLabel lblShipType = new JLabel("Ship Type:");
		lblShipType.setBounds(10, 11, 59, 14);
		add(lblShipType);
		
		
		
		//might be done in the main method not too sure
		//ship types will need to be changed to it's actual name
		String[] shipTypeList = {"Destroyers", "Light Cruisers", "Heavy Cruisers", "Large Cruisers", "Battlecruisers", "Battleships", "Aviation Battleships", "Monitors", "Submarines", "Aircraft Carriers", "Light Aircraft Carriers"};
		shipTypeCBox = new JComboBox<Object>(shipTypeList);
		shipTypeCBox.setBounds(10, 36, 161, 25);
		shipTypeCBox.setMaximumRowCount(10);
		shipTypeCBox.setSelectedIndex(0);
		add(shipTypeCBox);
		shipTypeCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					shipTypeName = (String) shipTypeCBox.getSelectedItem();
					GUIUtility.insertNames(shipNameCBox,true, shipTypeName);
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
		lblShipName.setBounds(181, 11, 86, 14);
		add(lblShipName);
		
		//ship names are added based on the list that is selected
		
		
		shipNameCBox = new JComboBox();
		shipNameCBox.setBounds(181, 36, 168, 25);
		add(shipNameCBox);
		GUIUtility.insertNames(shipNameCBox,true, shipTypeName);
		//future action listeners for the color stuff
		shipNameCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calculateButton.setEnabled(false);
				currentShipName = (String) shipNameCBox.getSelectedItem();
				List<String> attributes = new ArrayList<String>();
				int skillIdx = -1;
				switch(shipTypeName) {
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
				if(currentShipName != "") {
//					try {
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
