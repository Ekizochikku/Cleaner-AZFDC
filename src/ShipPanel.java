
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
	
	private String shipTypeName;
	private String currentShipName;
	private JComboBox shipNameCBox, shipTypeCBox;
	private ShipFile currentShip;
	private MainGUI gui;
	private GUIUtility gUtil;
	private JTextPane factionTxt;
	private JTextPane classTxt;
	private JTextPane healthTxt;
	private JTextPane firepowerTxt;
	private JTextPane torpedoTxt;
	private JTextPane antiAirTxt;
	private JTextPane aviationTxt;
	
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
					guiVariables.setShipTypeName((String) shipTypeCBox.getSelectedItem());
					shipTypeName = (String) shipTypeCBox.getSelectedItem();
					GUIUtility.insertNames(shipNameCBox,true, guiVariables.getShipTypeName());
					guiVariables.setCurrentShipName((String) shipNameCBox.getSelectedItem());
					currentShipName = (String) shipNameCBox.getSelectedItem();
					
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
		
		
		guiVariables.setShipTypeName((String) shipTypeCBox.getSelectedItem());
		shipTypeName = (String) shipTypeCBox.getSelectedItem();
				
		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblShipName.setBounds(534, 82, 95, 25);
		add(lblShipName);
		
		//ship names are added based on the list that is selected
		
		
		shipNameCBox = new JComboBox();
		shipNameCBox.setBounds(534, 127, 191, 25);
		add(shipNameCBox);
		GUIUtility.insertNames(shipNameCBox,true, guiVariables.getShipTypeName());
		currentShipName = (String) shipNameCBox.getSelectedItem();
		
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
		classTxt.setBounds(534, 217, 95, 31);
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
		setAttributes();
		AutoCompletion.enable(shipNameCBox);
		//future action listeners for the color stuff
		shipNameCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//calculateButton.setEnabled(false);
				
				guiVariables.setCurrentShipName((String) shipNameCBox.getSelectedItem());
				currentShipName = (String) shipNameCBox.getSelectedItem();
				System.out.println("Changing the ship name The current ship name is: " 
				+ guiVariables.getCurrentShipName());
				System.out.println("Changing the ship type The current ship type is: " 
						+ guiVariables.getShipTypeName());
				try {
					if(currentShipName != "") {
						currentShip = new ShipFile(currentShipName, shipTypeName);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//might need to change this so that we only need action listeners 
				//for only when the panel buttons and calculate is selected
				setAttributes();

			}
		});
		
		
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
	//method for when panel changes to weapon
	public void sendShipInfo(MainGUI gui) {
		gui.setCurrentShipName((String) shipNameCBox.getSelectedItem());
		gui.setShipTypeName((String) shipTypeCBox.getSelectedItem());
		
	}
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

