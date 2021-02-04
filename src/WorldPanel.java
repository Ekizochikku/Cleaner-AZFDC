import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class WorldPanel extends JPanel {
	public WorldPanel() {
		setLayout(null);
		
		JLabel lblChapter = new JLabel("Chapter:");
		lblChapter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChapter.setBounds(463, 77, 73, 25);
		add(lblChapter);
		
		JComboBox chapterSelection = new JComboBox();
		chapterSelection.setBounds(463, 113, 73, 25);
		add(chapterSelection);
		
		JLabel lblEnemyName = new JLabel("Enemy Name:");
		lblEnemyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnemyName.setBounds(563, 77, 121, 25);
		add(lblEnemyName);
		
		JComboBox enemySelection = new JComboBox();
		enemySelection.setBounds(546, 112, 155, 25);
		add(enemySelection);
		
		JLabel lblDangerlvl = new JLabel("Danger Lvl:");
		lblDangerlvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDangerlvl.setBounds(463, 185, 100, 25);
		add(lblDangerlvl);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHealth.setBounds(587, 185, 73, 25);
		add(lblHealth);
		
		JLabel lblArmor = new JLabel("Armor:");
		lblArmor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArmor.setBounds(468, 269, 73, 25);
		add(lblArmor);
		
		JLabel lblAntiAir = new JLabel("Anti Air:");
		lblAntiAir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAntiAir.setBounds(587, 269, 73, 25);
		add(lblAntiAir);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(463, 373, 73, 25);
		add(lblType);
		
		JLabel lblNation = new JLabel("Nation:");
		lblNation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNation.setBounds(587, 373, 73, 25);
		add(lblNation);
		
		JTextPane dangerLvlText = new JTextPane();
		dangerLvlText.setEditable(false);
		dangerLvlText.setBounds(472, 213, 82, 25);
		add(dangerLvlText);
		
		JTextPane healthText = new JTextPane();
		healthText.setEditable(false);
		healthText.setBounds(582, 213, 82, 25);
		add(healthText);
		
		JTextPane armorText = new JTextPane();
		armorText.setEditable(false);
		armorText.setBounds(463, 305, 82, 25);
		add(armorText);
		
		JTextPane antiAirText = new JTextPane();
		antiAirText.setEditable(false);
		antiAirText.setBounds(582, 305, 82, 25);
		add(antiAirText);
		
		JTextPane typeText = new JTextPane();
		typeText.setEditable(false);
		typeText.setBounds(463, 409, 82, 25);
		add(typeText);
		
		JTextPane nationText = new JTextPane();
		nationText.setEditable(false);
		nationText.setBounds(582, 409, 82, 25);
		add(nationText);
	}

}
