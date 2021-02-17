import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JTextPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class WeaponPanel extends JPanel {
	
	JComboBox weaponName1Combo, weaponName2Combo, weaponName3Combo, 
	weaponType1Combo, weaponType2Combo, weaponType3Combo;
	
	public WeaponPanel(MainGUI guiVariables) {
		setLayout(null);
		
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
		
		weaponName1Combo = new JComboBox();
		weaponName1Combo.setBounds(181, 48, 168, 23);
		add(weaponName1Combo);
		
		
		JLabel weaponType2Label = new JLabel("Weapon Type Slot 2:");
		weaponType2Label.setBounds(10, 95, 132, 25);
		add(weaponType2Label);
		
		JLabel weaponName2Label = new JLabel("Weapon Name Slot 2:");
		weaponName2Label.setBounds(181, 97, 144, 20);
		add(weaponName2Label);
		
		weaponType2Combo = new JComboBox();
		weaponType2Combo.setBounds(10, 131, 161, 25);
		add(weaponType2Combo);
		
		weaponName2Combo = new JComboBox();
		weaponName2Combo.setBounds(181, 132, 168, 23);
		add(weaponName2Combo);
		
		JLabel weaponType3Label = new JLabel("Weapon Type Slot 3:");
		weaponType3Label.setBounds(10, 167, 132, 25);
		add(weaponType3Label);
		
		JLabel weaponName3Label = new JLabel("Weapon Name Slot 3:");
		weaponName3Label.setBounds(181, 169, 144, 20);
		add(weaponName3Label);
		
		weaponType3Combo = new JComboBox();
		weaponType3Combo.setBounds(10, 203, 161, 25);
		add(weaponType3Combo);
		
		weaponName3Combo = new JComboBox();
		weaponName3Combo.setBounds(181, 204, 168, 23);
		add(weaponName3Combo);
		
		comboBoxTypeAction(weaponType1Combo, weaponType2Combo, weaponType3Combo, weaponName1Combo, 1);
		
		GUIUtility.insertAllWeaponTypeSlots(guiVariables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
				weaponName1Combo, weaponName2Combo, weaponName3Combo, true);
		
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
		
		JTextPane bomb1Plane1Text = new JTextPane();
		bomb1Plane1Text.setEditable(false);
		bomb1Plane1Text.setText("0");
		bomb1Plane1Text.setBounds(177, 286, 49, 25);
		add(bomb1Plane1Text);
		
		JTextPane bomb1Plane2Text = new JTextPane();
		bomb1Plane2Text.setEditable(false);
		bomb1Plane2Text.setText("0");
		bomb1Plane2Text.setBounds(262, 286, 49, 25);
		add(bomb1Plane2Text);
		
		JTextPane bomb1Plane3Text = new JTextPane();
		bomb1Plane3Text.setEditable(false);
		bomb1Plane3Text.setText("0");
		bomb1Plane3Text.setBounds(347, 286, 49, 25);
		add(bomb1Plane3Text);
		
		JTextPane bomb2Plane1Text = new JTextPane();
		bomb2Plane1Text.setEditable(false);
		bomb2Plane1Text.setText("0");
		bomb2Plane1Text.setBounds(177, 326, 49, 25);
		add(bomb2Plane1Text);
		
		JTextPane bomb2Plane2Text = new JTextPane();
		bomb2Plane2Text.setEditable(false);
		bomb2Plane2Text.setText("0");
		bomb2Plane2Text.setBounds(262, 326, 49, 25);
		add(bomb2Plane2Text);
		
		JTextPane bomb2Plane3Text = new JTextPane();
		bomb2Plane3Text.setEditable(false);
		bomb2Plane3Text.setText("0");
		bomb2Plane3Text.setBounds(347, 326, 49, 25);
		add(bomb2Plane3Text);
		
		JTextPane torpedoPlane1Text = new JTextPane();
		torpedoPlane1Text.setEditable(false);
		torpedoPlane1Text.setText("0");
		torpedoPlane1Text.setBounds(177, 365, 49, 25);
		add(torpedoPlane1Text);
		
		JTextPane torpedoPlane2Text = new JTextPane();
		torpedoPlane2Text.setEditable(false);
		torpedoPlane2Text.setText("0");
		torpedoPlane2Text.setBounds(262, 365, 49, 25);
		add(torpedoPlane2Text);
		
		JTextPane torpedoPlane3Text = new JTextPane();
		torpedoPlane3Text.setEditable(false);
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
		
		JTextPane nodesKilledTxt = new JTextPane();
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
		
		JComboBox aux1Combo = new JComboBox();
		aux1Combo.setBounds(577, 49, 187, 25);
		add(aux1Combo);
		
		JComboBox aux2Combo = new JComboBox();
		aux2Combo.setBounds(577, 133, 187, 25);
		add(aux2Combo);
		
		JTextPane auxHealthTxt = new JTextPane();
		auxHealthTxt.setEditable(false);
		auxHealthTxt.setBounds(586, 239, 161, 30);
		add(auxHealthTxt);
		
		JTextPane firepowerTxt = new JTextPane();
		firepowerTxt.setEditable(false);
		firepowerTxt.setBounds(582, 319, 117, 30);
		add(firepowerTxt);
		
		JTextPane antiAirTxt = new JTextPane();
		antiAirTxt.setEditable(false);
		antiAirTxt.setBounds(751, 319, 117, 30);
		add(antiAirTxt);
		
		JTextPane torpedoTxt = new JTextPane();
		torpedoTxt.setEditable(false);
		torpedoTxt.setBounds(582, 384, 117, 30);
		add(torpedoTxt);
		
		JTextPane aviationTxt = new JTextPane();
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
	
	//helper method to set the action listeners for every weapon type box action
	private void comboBoxTypeAction(JComboBox mainBox, JComboBox box2, JComboBox box3, 
			JComboBox nameBox, int theSlotNumber) {
		
		//we can either build the string or have switch cases
		String weaponSlot = "currentWeaponNameSlot" + theSlotNumber; 
		mainBox.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
//			System.out.println("This is a test");
			//need to grab the calculate button
			/*
			if(((String) box2).isEmpty() || box3.isEmpty()) {
				//calculateButton.setEnabled(false);
			}*/
			try {
				String currentWeaponTypeSlot3 = (String) mainBox.getSelectedItem();
				GUIUtility.insertNames(nameBox, false, currentWeaponTypeSlot3);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
}

	
}
