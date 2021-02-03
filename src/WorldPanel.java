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
		lblChapter.setBounds(365, 74, 73, 25);
		add(lblChapter);
		
		JComboBox chapterSelection = new JComboBox();
		chapterSelection.setBounds(365, 110, 73, 25);
		add(chapterSelection);
		
		JLabel lblEnemyName = new JLabel("Enemy Name:");
		lblEnemyName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEnemyName.setBounds(514, 75, 121, 25);
		add(lblEnemyName);
		
		JComboBox enemySelection = new JComboBox();
		enemySelection.setBounds(497, 110, 155, 25);
		add(enemySelection);
		
		JLabel lblDangerlvl = new JLabel("Danger Lvl:");
		lblDangerlvl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDangerlvl.setBounds(365, 183, 100, 25);
		add(lblDangerlvl);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHealth.setBounds(538, 183, 73, 25);
		add(lblHealth);
		
		JLabel lblArmor = new JLabel("Armor:");
		lblArmor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblArmor.setBounds(365, 267, 73, 25);
		add(lblArmor);
		
		JLabel lblAntiAir = new JLabel("Anti Air:");
		lblAntiAir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAntiAir.setBounds(538, 267, 73, 25);
		add(lblAntiAir);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblType.setBounds(365, 371, 73, 25);
		add(lblType);
		
		JLabel lblNation = new JLabel("Nation:");
		lblNation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNation.setBounds(538, 371, 73, 25);
		add(lblNation);
		
		JTextPane dangerLvlText = new JTextPane();
		dangerLvlText.setEditable(false);
		dangerLvlText.setBounds(365, 211, 82, 25);
		add(dangerLvlText);
		
		JTextPane healthText = new JTextPane();
		healthText.setEditable(false);
		healthText.setBounds(534, 211, 82, 25);
		add(healthText);
		
		JTextPane armorText = new JTextPane();
		armorText.setEditable(false);
		armorText.setBounds(365, 303, 82, 25);
		add(armorText);
		
		JTextPane antiAirText = new JTextPane();
		antiAirText.setEditable(false);
		antiAirText.setBounds(534, 303, 82, 25);
		add(antiAirText);
		
		JTextPane typeText = new JTextPane();
		typeText.setEditable(false);
		typeText.setBounds(365, 407, 82, 25);
		add(typeText);
		
		JTextPane nationText = new JTextPane();
		nationText.setEditable(false);
		nationText.setBounds(534, 407, 82, 25);
		add(nationText);
	}

}
