import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

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
		lblShipName.setBounds(227, 11, 86, 14);
		add(lblShipName);
		
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(209, 36, 135, 25);
		add(comboBox_1);
		
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
