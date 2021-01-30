import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;

public class ShipWeaponPanel extends JPanel {
	//The current damage type being applied
	private int currentDMGType = 0; //0 = HE, 1 = AP
	private String shipTypeName;
	private String shipName;
	private String currentColorSelected;
	
	/**
	 * Create the panel.
	 */
	public ShipWeaponPanel() {
		setLayout(null);
		
		JLabel lblShipType = new JLabel("Ship Type:");
		lblShipType.setBounds(10, 11, 59, 14);
		add(lblShipType);
		
		
		
		//might be done in the main method not too sure
		//ship types will need to be changed to it's actual name
		String[] shipTypeList = {"DD", "CL", "CA", "LC", "BC", "BB", "AB", "MON", "SUB", "CV", "CVL"};
		JComboBox shipTypeCBox = new JComboBox<Object>(shipTypeList);
		shipTypeCBox.setBounds(10, 36, 161, 25);
		shipTypeCBox.setMaximumRowCount(10);
		shipTypeCBox.setSelectedIndex(0);
		add(shipTypeCBox);
		
		shipTypeName = (String) shipTypeCBox.getSelectedItem();
				
		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setBounds(181, 11, 86, 14);
		add(lblShipName);
		
		//ship names are added based on the list that is selected
		
		
		JComboBox shipNameCBox = new JComboBox();
		shipNameCBox.setBounds(181, 36, 168, 25);
		add(shipNameCBox);
		
		JLabel lblWeaponTypeSlot = new JLabel("Weapon Type Slot 1:");
		lblWeaponTypeSlot.setBounds(10, 72, 109, 25);
		add(lblWeaponTypeSlot);
		
		JComboBox weapon1TypeCBox = new JComboBox();
		weapon1TypeCBox.setBounds(10, 108, 161, 25);
		add(weapon1TypeCBox);
		
		JLabel lblNewLabel = new JLabel("Weapon Name Slot 1:");
		lblNewLabel.setBounds(181, 74, 116, 20);
		add(lblNewLabel);
		
		JComboBox weapon1NameCBox = new JComboBox();
		weapon1NameCBox.setBounds(181, 109, 168, 23);
		add(weapon1NameCBox);
		
		JLabel lblWeaponTypeSlot_1 = new JLabel("Weapon Type Slot 2:");
		lblWeaponTypeSlot_1.setBounds(10, 156, 109, 25);
		add(lblWeaponTypeSlot_1);
		
		JLabel lblWeaponNameSlot_1 = new JLabel("Weapon Name Slot 2:");
		lblWeaponNameSlot_1.setBounds(181, 158, 116, 20);
		add(lblWeaponNameSlot_1);
		
		JComboBox weapon2TypeCBox = new JComboBox();
		weapon2TypeCBox.setBounds(10, 192, 161, 25);
		add(weapon2TypeCBox);
		
		JComboBox weapon2NameCBox = new JComboBox();
		weapon2NameCBox.setBounds(181, 193, 168, 23);
		add(weapon2NameCBox);
		
		JLabel lblWeaponTypeSlot_2 = new JLabel("Weapon Type Slot 3:");
		lblWeaponTypeSlot_2.setBounds(10, 228, 109, 25);
		add(lblWeaponTypeSlot_2);
		
		JLabel lblWeaponNameSlot = new JLabel("Weapon Name Slot 3:");
		lblWeaponNameSlot.setBounds(181, 230, 116, 20);
		add(lblWeaponNameSlot);
		
		JComboBox weapon3TypeCBox = new JComboBox();
		weapon3TypeCBox.setBounds(10, 264, 161, 25);
		add(weapon3TypeCBox);
		
		JComboBox weapon3NameCBox = new JComboBox();
		weapon3NameCBox.setBounds(181, 265, 168, 23);
		add(weapon3NameCBox);
		
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
		return shipName;
	}
	public int getDamageType() {
		return currentDMGType;
	}
	public String getCurrentColor() {
		return currentColorSelected;
	}
}
