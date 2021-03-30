import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.NumberFormatter;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
//NOTE: Needs to be changed once Brian says GO AHEAD
public class WeaponPanel extends JPanel implements ActionListener{
	
	JComboBox<Object> weaponName1Combo, weaponName2Combo, weaponName3Combo, 
	weaponType1Combo, weaponType2Combo, weaponType3Combo, aux1Combo, aux2Combo;
	


	//Bomb/plane boxes
	//changes to jformatted text field to restrict to only numbers
	//fudge you Walter
	JFormattedTextField bomb1Plane3Text, bomb1Plane1Text, bomb1Plane2Text,
	bomb2Plane1Text, bomb2Plane2Text, bomb2Plane3Text,
	torpedoPlane1Text, torpedoPlane2Text, torpedoPlane3Text, nodesKilledTxt;
	
	
	
	//Auxiliary TextPanes
	JTextPane auxHealthTxt, firepowerTxt, antiAirTxt, torpedoTxt, aviationTxt; 
	
	//making auxgear here aswell and set it on the main gui one at the end since too lazy to write the long chain of getters and setters
	AuxGear aux1, aux2;
	MainGUI guiVariables;
	GUIUtility guiU;
	public WeaponPanel(MainGUI frame, JComboBox<Object> weaponName1Combo, JComboBox<Object> weaponName2Combo,
			JComboBox<Object> weaponName3Combo, JComboBox<Object> weaponType1Combo, JComboBox<Object> weaponType2Combo,
			JComboBox<Object> weaponType3Combo) {
		super();
		this.weaponName1Combo = weaponName1Combo;
		this.weaponName2Combo = weaponName2Combo;
		this.weaponName3Combo = weaponName3Combo;
		this.weaponType1Combo = weaponType1Combo;
		this.weaponType2Combo = weaponType2Combo;
		this.weaponType3Combo = weaponType3Combo;
	}


	/**
	 * @wbp.parser.constructor
	 */
	public WeaponPanel(MainGUI guiVairables) throws FileNotFoundException, IOException {
		setLayout(null);
		this.guiVariables = guiVairables;
		JLabel weaponType1Label = new JLabel("Weapon Type Slot 1:");
		weaponType1Label.setBounds(10, 11, 132, 25);
		add(weaponType1Label);
		
		JLabel weaponName1Label = new JLabel("Weapon Name Slot 1:");
		weaponName1Label.setBounds(181, 13, 144, 20);
		add(weaponName1Label);
		
		weaponType1Combo = new JComboBox<Object>(new Object[]{});
		weaponType1Combo.setMaximumRowCount(5);
		weaponType1Combo.setBounds(10, 47, 161, 25);
		add(weaponType1Combo);
		weaponType1Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

			}
	});
		//carriers, aviation 3rd slot only, i13 third slot only 
		weaponName1Combo = new JComboBox<Object>();
		weaponName1Combo.setBounds(181, 48, 168, 23);
		add(weaponName1Combo);
		
		
		JLabel weaponType2Label = new JLabel("Weapon Type Slot 2:");
		weaponType2Label.setBounds(10, 95, 132, 25);
		add(weaponType2Label);
		
		JLabel weaponName2Label = new JLabel("Weapon Name Slot 2:");
		weaponName2Label.setBounds(181, 97, 144, 20);
		add(weaponName2Label);
		
		weaponType2Combo = new JComboBox<Object>();
		weaponType2Combo.setBounds(10, 131, 161, 25);
		add(weaponType2Combo);
		
		weaponType2Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

			}
	});
		
		weaponName2Combo = new JComboBox<Object>();
		weaponName2Combo.setBounds(181, 132, 168, 23);
		add(weaponName2Combo);
		
		JLabel weaponType3Label = new JLabel("Weapon Type Slot 3:");
		weaponType3Label.setBounds(10, 167, 132, 25);
		add(weaponType3Label);
		
		JLabel weaponName3Label = new JLabel("Weapon Name Slot 3:");
		weaponName3Label.setBounds(181, 169, 144, 20);
		add(weaponName3Label);
		
		weaponType3Combo = new JComboBox<Object>();
		weaponType3Combo.setBounds(10, 203, 161, 25);
		add(weaponType3Combo);
		weaponType3Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

			}
	});
		
		
		weaponName3Combo = new JComboBox<Object>();
		weaponName3Combo.setBounds(181, 204, 168, 23);
		add(weaponName3Combo);
		
		
		System.out.println("inserting weapon type slots");
		GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
				weaponName1Combo, weaponName2Combo, weaponName3Combo, false);
		
		JLabel lblBombsDropped = new JLabel("Bombs 1 Dropped:");
		lblBombsDropped.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBombsDropped.setBounds(10, 278, 144, 30);
		add(lblBombsDropped);
		
		JLabel lblBombsDropped_1 = new JLabel("Bombs 2 Dropped:");
		lblBombsDropped_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBombsDropped_1.setBounds(10, 319, 144, 30);
		add(lblBombsDropped_1);
		
		JLabel lblTorpedosDropped = new JLabel("Torpedos Dropped:");
		lblTorpedosDropped.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTorpedosDropped.setBounds(10, 360, 144, 30);
		add(lblTorpedosDropped);
		
		JLabel lblPlane = new JLabel("Plane 1");
		lblPlane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlane.setBounds(164, 257, 75, 25);
		add(lblPlane);
		
		JLabel lblPlane_1 = new JLabel("Plane 2");
		lblPlane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlane_1.setBounds(249, 260, 75, 25);
		add(lblPlane_1);
		
		JLabel lblPlane_2 = new JLabel("Plane 3");
		lblPlane_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlane_2.setBounds(334, 258, 75, 25);
		add(lblPlane_2);
		
		/**
		 * @author Kevin Nguyen
		 * Number formatter for the 3x3 bombs
		 */
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format) {
	    	/**
			 * no idea what this is for but it told me to
			 */
			private static final long serialVersionUID = 1L;

			//Overrides the number formatter so that it allows blanks
	    	//Before it didn't allow you to delete the last digit which was stupid
	    	@Override
	    	public Object stringToValue(String string)
                    throws ParseException {
                    if (string == null || string.length() == 0) {
                        return null;
                    }
						return super.stringToValue(string);
	    	}
        };
	    
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(99);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		
		bomb1Plane1Text = new JFormattedTextField(formatter);
		bomb1Plane1Text.setColumns(10);
		bomb1Plane1Text.setText("0");
		bomb1Plane1Text.setBounds(177, 286, 49, 25);
		add(bomb1Plane1Text);


		bomb1Plane2Text = new JFormattedTextField(formatter);
		bomb1Plane2Text.setText("0");
		bomb1Plane2Text.setBounds(262, 286, 49, 25);
		add(bomb1Plane2Text);
		
		bomb1Plane3Text = new JFormattedTextField(formatter);
		bomb1Plane3Text.setText("0");
		bomb1Plane3Text.setBounds(347, 286, 49, 25);
		add(bomb1Plane3Text);
		
		bomb2Plane1Text = new JFormattedTextField(formatter);
		bomb2Plane1Text.setText("0");
		bomb2Plane1Text.setBounds(177, 326, 49, 25);
		add(bomb2Plane1Text);
		
		bomb2Plane2Text = new JFormattedTextField(formatter);
		bomb2Plane2Text.setText("0");
		bomb2Plane2Text.setBounds(262, 326, 49, 25);
		add(bomb2Plane2Text);
		
		bomb2Plane3Text = new JFormattedTextField(formatter);
		bomb2Plane3Text.setText("0");
		bomb2Plane3Text.setBounds(347, 326, 49, 25);
		add(bomb2Plane3Text);
		
		torpedoPlane1Text = new JFormattedTextField(formatter);
		torpedoPlane1Text.setText("0");
		torpedoPlane1Text.setBounds(177, 365, 49, 25);
		add(torpedoPlane1Text);
		
		torpedoPlane2Text = new JFormattedTextField(formatter);
		torpedoPlane2Text.setText("0");
		torpedoPlane2Text.setBounds(262, 365, 49, 25);
		add(torpedoPlane2Text);
		
		torpedoPlane3Text = new JFormattedTextField(formatter);
		torpedoPlane3Text.setText("0");
		torpedoPlane3Text.setBounds(347, 365, 49, 25);
		add(torpedoPlane3Text);
		
		JLabel lblNewLabel = new JLabel("Ammo Type:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 399, 109, 25);
		add(lblNewLabel);
		
		JRadioButton rdbtnHe = new JRadioButton("HE");
		rdbtnHe.setBounds(20, 426, 49, 23);
		add(rdbtnHe);
		
		JRadioButton rdbtnAp = new JRadioButton("AP");
		rdbtnAp.setBounds(71, 426, 48, 23);
		add(rdbtnAp);
		
		JRadioButton rdbtnEven = new JRadioButton("Even");
		rdbtnEven.setBounds(134, 426, 66, 23);
		add(rdbtnEven);
		
		JRadioButton rdbtnOdd = new JRadioButton("Odd");
		rdbtnOdd.setBounds(207, 426, 49, 23);
		add(rdbtnOdd);
		
		JRadioButton rdbtnBlue = new JRadioButton("Blue");
		rdbtnBlue.setBounds(61, 469, 58, 23);
		add(rdbtnBlue);
		
		JRadioButton rdbtnPurple = new JRadioButton("Purple");
		rdbtnPurple.setBounds(124, 469, 76, 23);
		add(rdbtnPurple);
		
		JRadioButton rdbtnRed = new JRadioButton("Red");
		rdbtnRed.setBounds(207, 469, 49, 23);
		add(rdbtnRed);
		
		JCheckBox chckbxFirstSalvo = new JCheckBox("First Salvo");
		chckbxFirstSalvo.setBounds(31, 506, 97, 23);
		add(chckbxFirstSalvo);
		
		JCheckBox chckbxCriticalHit = new JCheckBox("Critical Hit");
		chckbxCriticalHit.setBounds(124, 506, 97, 23);
		add(chckbxCriticalHit);
		
		JCheckBox chckbxManual = new JCheckBox("Manual");
		chckbxManual.setBounds(31, 546, 97, 23);
		add(chckbxManual);
		
		JCheckBox chckbxArmor = new JCheckBox("Armor Broken");
		chckbxArmor.setBounds(124, 546, 107, 23);
		add(chckbxArmor);
		
		JLabel lblNodesKilled = new JLabel("Nodes Killed:");
		lblNodesKilled.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNodesKilled.setBounds(272, 532, 97, 30);
		add(lblNodesKilled);
		
		nodesKilledTxt = new JFormattedTextField(formatter);
		nodesKilledTxt.setEditable(false);
		nodesKilledTxt.setBounds(395, 532, 87, 30);
		add(nodesKilledTxt);
		
		JLabel lblAuxiliary = new JLabel("Auxiliary 1:");
		lblAuxiliary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuxiliary.setBounds(586, 16, 109, 25);
		add(lblAuxiliary);
		
		JLabel lblAuxiliary_1 = new JLabel("Auxiliary 2:");
		lblAuxiliary_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuxiliary_1.setBounds(586, 100, 109, 25);
		add(lblAuxiliary_1);
		
		JLabel lblAuxHealth = new JLabel("Aux Health:");
		lblAuxHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuxHealth.setBounds(586, 203, 109, 25);
		add(lblAuxHealth);
		
		JLabel lblFirepower = new JLabel("Firepower:");
		lblFirepower.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirepower.setBounds(586, 288, 109, 25);
		add(lblFirepower);
		
		JLabel lblAntiAir = new JLabel("Anti Air:");
		lblAntiAir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAntiAir.setBounds(755, 288, 109, 25);
		add(lblAntiAir);
		
		JLabel lblTorpedo = new JLabel("Torpedo:");
		lblTorpedo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTorpedo.setBounds(586, 359, 109, 25);
		add(lblTorpedo);
		
		JLabel lblAviation = new JLabel("Aviation:");
		lblAviation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAviation.setBounds(755, 360, 109, 25);
		add(lblAviation);
		
		aux1Combo = new JComboBox<Object>();
		aux1Combo.setBounds(577, 49, 187, 25);
		add(aux1Combo);
		
		List<String> auxNames = new ArrayList<String>();
		auxNames = GUIUtility.getAuxNames();
		//populating combo box with aux names
		Collections.sort(auxNames);
		aux1Combo.setModel(new DefaultComboBoxModel<Object>(auxNames.toArray()));
		aux1Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//aux string
				try {
					aux1 = new AuxGear((String) aux1Combo.getSelectedItem());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//JTextPane auxHealthTxt, firepowerTxt, antiAirTxt, torpedoTxt, aviationTxt; 
				if(aux2 == null) {
					//why call getters when we can get directly from list? :(
					//especially when I have to parse the getters anyways? 
					auxHealthTxt.setText("1: " + String.valueOf(aux1.getHealth()));
					firepowerTxt.setText("1: " + String.valueOf(aux1.getFirepower()));
					torpedoTxt.setText("1: " + String.valueOf(aux1.getTorpedo()));
					antiAirTxt.setText("1: " + String.valueOf(aux1.getAA()));
					aviationTxt.setText("1: " + String.valueOf(aux1.getAviation()));
				} else {
					auxHealthTxt.setText("1: " + String.valueOf(aux1.getHealth()) + "   2: " + String.valueOf(aux2.getHealth()));
					firepowerTxt.setText("1: " + String.valueOf(aux1.getFirepower())+ "   2: " + String.valueOf(aux2.getFirepower()));
					torpedoTxt.setText("1: " + String.valueOf(aux1.getTorpedo())+ "   2: " + String.valueOf(aux2.getTorpedo()) );
					antiAirTxt.setText("1: " + String.valueOf(aux1.getAA())+ "   2: " + String.valueOf(aux2.getAA()));
					aviationTxt.setText("1: " + String.valueOf(aux1.getAviation())+ "   2: " + String.valueOf(aux2.getAviation()));
				}
				guiVariables.setAux1(aux1);

			}
		});

		
		aux2Combo = new JComboBox<Object>();
		aux2Combo.setBounds(577, 133, 187, 25);
		add(aux2Combo);
		aux2Combo.setModel(new DefaultComboBoxModel<Object>(auxNames.toArray()));
		aux2Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//aux string
				try {
					aux2 = new AuxGear((String) aux2Combo.getSelectedItem());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//JTextPane auxHealthTxt, firepowerTxt, antiAirTxt, torpedoTxt, aviationTxt; 
				if(aux1 == null) {
					//why call getters when we can get directly from list? :(
					//especially when I have to parse the getters anyways? 
					auxHealthTxt.setText("2: " + String.valueOf(aux2.getHealth()));
					firepowerTxt.setText("2: " + String.valueOf(aux2.getFirepower()));
					torpedoTxt.setText("2: " + String.valueOf(aux2.getTorpedo()));
					antiAirTxt.setText("2: " + String.valueOf(aux2.getAA()));
					aviationTxt.setText("2: " + String.valueOf(aux2.getAviation()));
				} else {
					auxHealthTxt.setText("1: " + String.valueOf(aux1.getHealth()) + "   2: " + String.valueOf(aux2.getHealth()));
					firepowerTxt.setText("1: " + String.valueOf(aux1.getFirepower())+ "   2: " + String.valueOf(aux2.getFirepower()));
					torpedoTxt.setText("1: " + String.valueOf(aux1.getTorpedo())+ "   2: " + String.valueOf(aux2.getTorpedo()) );
					antiAirTxt.setText("1: " + String.valueOf(aux1.getAA())+ "   2: " + String.valueOf(aux2.getAA()));
					aviationTxt.setText("1: " + String.valueOf(aux1.getAviation())+ "   2: " + String.valueOf(aux2.getAviation()));
				}
				guiVariables.setAux2(aux2);
			}
		});
		
		auxHealthTxt = new JTextPane();
		auxHealthTxt.setEditable(false);
		auxHealthTxt.setBounds(586, 239, 161, 30);
		add(auxHealthTxt);
		
		firepowerTxt = new JTextPane();
		firepowerTxt.setEditable(false);
		firepowerTxt.setBounds(582, 319, 117, 30);
		add(firepowerTxt);
		
		antiAirTxt = new JTextPane();
		antiAirTxt.setEditable(false);
		antiAirTxt.setBounds(751, 319, 117, 30);
		add(antiAirTxt);
		
		torpedoTxt = new JTextPane();
		torpedoTxt.setEditable(false);
		torpedoTxt.setBounds(582, 384, 117, 30);
		add(torpedoTxt);
		
		aviationTxt = new JTextPane();
		aviationTxt.setEditable(false);
		aviationTxt.setBounds(751, 384, 117, 30);
		add(aviationTxt);
		
		
		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNote.setBounds(9, 473, 46, 14);
		add(lblNote);
		
	}
	   
	 
	//helper method for every weapon name, action listener to set the current Weapon Name
	/*
	private void addActionName(MainGUI guiVariables, JComboBox mainBox, String slotNumber) {
		//invoking a method with a string might be better to manually do it
		//initial screen
		mainBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				GUIUtility.insertType(mainBox, false, guiVariables.getCurrentShipName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
}*/

	private void populateCombo(JComboBox box, ArrayList list) {
		DefaultComboBoxModel dml= new DefaultComboBoxModel();
		for (int i = 0; i < list.size(); i++) {
//		  dml.addElement(((Object) list.get(i)).getField());
		}

		box.setModel(dml);
	}
	//made this method on accident leaving for just in case we need weapon info on other panels in the future
	public String[] sendInformation() {
		String[] info = new String[5];
		
		info[0] = (String) weaponName1Combo.getSelectedItem(); 
		info[1] = (String) weaponName2Combo.getSelectedItem();
		info[2] = (String) weaponName3Combo.getSelectedItem(); 
		info[3] = (String) weaponType1Combo.getSelectedItem(); 
		info[4] = (String) weaponType2Combo.getSelectedItem(); 
		info[5]	= (String) weaponType3Combo.getSelectedItem();
		return info;
	}
	//helper method to set the action listeners for every weapon type box action
	public void comboBoxTypeAction(MainGUI guiVariables) {
			ShipFile currentShip = guiVariables.getCurrentShip();
			System.out.println("Hello we entered here in combo box type action! " + currentShip.getShipName());
			GUIUtility.insertAllWeaponTypeSlots(guiVariables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

	}

	
	public void onSwitch() {
		GUIUtility.insertAllWeaponTypeSlots(guiVariables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
				weaponName1Combo, weaponName2Combo, weaponName3Combo, false);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	
