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
		lblSlotDamage.setBounds(406, 55, 187, 39);
		add(lblSlotDamage);
		
		JTextPane damage1Result = new JTextPane();
		damage1Result.setEditable(false);
		damage1Result.setBounds(349, 149, 300, 39);
		add(damage1Result);
		
		JLabel lblSlotDamage_1 = new JLabel("Slot 2 Damage Range:");
		lblSlotDamage_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlotDamage_1.setBounds(406, 243, 187, 39);
		add(lblSlotDamage_1);
		
		JLabel lblSlotDamage_2 = new JLabel("Slot 3 Damage Range:");
		lblSlotDamage_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlotDamage_2.setBounds(406, 431, 187, 39);
		add(lblSlotDamage_2);
		
		JTextPane damage2Result = new JTextPane();
		damage2Result.setEditable(false);
		damage2Result.setBounds(349, 337, 300, 39);
		add(damage2Result);
		
		JTextPane damage3Result = new JTextPane();
		damage3Result.setEditable(false);
		damage3Result.setBounds(349, 525, 300, 39);
		add(damage3Result);
	}
}
