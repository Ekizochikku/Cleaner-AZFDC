import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ShipWeaponPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShipWeaponPanel() {
		setLayout(null);
		
		JLabel lblShipType = new JLabel("Ship Type:");
		lblShipType.setBounds(10, 11, 59, 14);
		add(lblShipType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 36, 161, 25);
		add(comboBox);
		
		JLabel lblShipName = new JLabel("Ship Name:");
		lblShipName.setBounds(227, 11, 86, 14);
		add(lblShipName);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(209, 36, 135, 25);
		add(comboBox_1);

	}
}
