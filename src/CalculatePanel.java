import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class CalculatePanel extends JPanel {
	public CalculatePanel() {
		setLayout(null);
		
		JLabel lblSlotDamage = new JLabel("Slot 1 Damage Range:");
		lblSlotDamage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlotDamage.setBounds(464, 35, 187, 39);
		add(lblSlotDamage);
		
		JTextPane damage1Result = new JTextPane();
		damage1Result.setEditable(false);
		damage1Result.setBounds(407, 85, 300, 39);
		add(damage1Result);
		
		JLabel lblSlotDamage_1 = new JLabel("Slot 2 Damage Range:");
		lblSlotDamage_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlotDamage_1.setBounds(464, 187, 187, 39);
		add(lblSlotDamage_1);
		
		JLabel lblSlotDamage_2 = new JLabel("Slot 3 Damage Range:");
		lblSlotDamage_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlotDamage_2.setBounds(464, 346, 187, 39);
		add(lblSlotDamage_2);
		
		JTextPane damage2Result = new JTextPane();
		damage2Result.setEditable(false);
		damage2Result.setBounds(407, 250, 300, 39);
		add(damage2Result);
		
		JTextPane damage3Result = new JTextPane();
		damage3Result.setEditable(false);
		damage3Result.setBounds(407, 419, 300, 39);
		add(damage3Result);
		
		
	}
	/**
	 * Small Method to convert the Jtextfield into a int so it's not too repetitive (avoids calling get text -> parse int on everything).
	 * This might have to be used in the weapon panel and then sent over to calculate as int first
	 * Just call this for every 3x3 object
	 * @author Kevin Nguyen
	 * @param planeOrdinance the bombs or torpedo
	 */
	protected int parseInt(JTextField planeOrdinance) {
		int converted;
		String textFieldAsAString = planeOrdinance.getText();
		converted = Integer.parseInt(textFieldAsAString);
		return converted;
	}
}
