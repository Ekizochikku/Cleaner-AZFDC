import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class SkillPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SkillPanel() {
		setLayout(null);
		
		JLabel lblSkillList = new JLabel("Skill List:");
		lblSkillList.setBounds(22, 11, 85, 23);
		add(lblSkillList);
		
		JComboBox<?> comboBox = new JComboBox();
		comboBox.setBounds(22, 36, 172, 28);
		add(comboBox);
		
		JButton btnAddSkill = new JButton("Add Skill");
		btnAddSkill.setBounds(215, 39, 113, 25);
		add(btnAddSkill);
		
		JLabel lblSkillDescription = new JLabel("Skill Description:");
		lblSkillDescription.setBounds(22, 88, 136, 28);
		add(lblSkillDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 127, 345, 174);
		add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);

	}
}
