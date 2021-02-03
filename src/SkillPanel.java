import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;

public class SkillPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SkillPanel() {
		setLayout(null);
		
		JLabel lblSkillList = new JLabel("Skill List:");
		lblSkillList.setBounds(532, 11, 85, 23);
		lblSkillList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblSkillList);
		
		JComboBox<?> comboBox = new JComboBox();
		comboBox.setBounds(489, 36, 172, 28);
		add(comboBox);
		
		JButton btnAddSkill = new JButton("Add Skill");
		btnAddSkill.setBounds(518, 39, 113, 25);
		add(btnAddSkill);
		
		JLabel lblSkillDescription = new JLabel("Skill Description:");
		lblSkillDescription.setBounds(507, 88, 136, 28);
		lblSkillDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblSkillDescription);
		
		JScrollPane skillDescriptionScrollPane = new JScrollPane();
		skillDescriptionScrollPane.setBounds(357, 127, 435, 133);
		add(skillDescriptionScrollPane);
		
		JTextPane skillDescriptionText = new JTextPane();
		skillDescriptionText.setEditable(false);
		skillDescriptionScrollPane.setViewportView(skillDescriptionText);
		
		JLabel lblSkillUsers = new JLabel("Skill Users:");
		lblSkillUsers.setBounds(523, 298, 104, 23);
		lblSkillUsers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblSkillUsers);
		
		JScrollPane skillUserScrollPane = new JScrollPane();
		skillUserScrollPane.setBounds(390, 343, 370, 53);
		add(skillUserScrollPane);
		
		JTextPane skillUserText = new JTextPane();
		skillUserScrollPane.setViewportView(skillUserText);
		
		JScrollPane activeSkillScrollPane = new JScrollPane();
		activeSkillScrollPane.setBounds(390, 516, 370, 53);
		add(activeSkillScrollPane);
		
		JTextPane activeSkillsText = new JTextPane();
		activeSkillsText.setEditable(false);
		activeSkillScrollPane.setViewportView(activeSkillsText);
		
		JLabel lblActiveSkills = new JLabel("Active Skills:");
		lblActiveSkills.setBounds(523, 467, 104, 23);
		lblActiveSkills.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblActiveSkills);

	}
}
