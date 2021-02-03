import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class WeaponPanel extends JPanel {
	public WeaponPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("Weapon Type Slot 1:");
		label.setBounds(10, 11, 109, 25);
		add(label);
		
		JLabel label_1 = new JLabel("Weapon Name Slot 1:");
		label_1.setBounds(181, 13, 116, 20);
		add(label_1);
		
		JComboBox<Object> comboBox = new JComboBox<Object>(new Object[]{});
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(10, 47, 161, 25);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(181, 48, 168, 23);
		add(comboBox_1);
		
		JLabel label_2 = new JLabel("Weapon Type Slot 2:");
		label_2.setBounds(10, 95, 109, 25);
		add(label_2);
		
		JLabel label_3 = new JLabel("Weapon Name Slot 2:");
		label_3.setBounds(181, 97, 116, 20);
		add(label_3);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 131, 161, 25);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(181, 132, 168, 23);
		add(comboBox_3);
		
		JLabel label_4 = new JLabel("Weapon Type Slot 3:");
		label_4.setBounds(10, 167, 109, 25);
		add(label_4);
		
		JLabel label_5 = new JLabel("Weapon Name Slot 3:");
		label_5.setBounds(181, 169, 116, 20);
		add(label_5);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(10, 203, 161, 25);
		add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(181, 204, 168, 23);
		add(comboBox_5);
	}
}
