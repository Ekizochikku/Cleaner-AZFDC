import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 * Panel to display and contain information pertaining to the skills for ships. Allows
 * user to add skills for their ships to be used in damage calculations. 
 * 
 * @author Walter Hanson
 *
 */
public class SkillPanel extends JPanel {

	//List of active skills
	private ArrayList<Skill> currentSkills;
	//JList to display the active skills and allow users to remove
	private JList activeSkillsList;
	//Main application
	private MainGUI gui;
	//Backend methods
	private GUIUtility gUtil;
	//Displays the selected skill description
	private JTextPane skillDescriptionText;
	//Displays the ships that are allowed to use selected skill
	private JTextPane skillUserText;
	//Contains a list of all the skills in the game for user to select
	private JComboBox<String> skillListCBox;
	//The current ship being used.
	private ShipFile currentShip;
	/**
	 * Create the panel.
	 * @param theGui Main application class so information can be transmitted between classes.
	 * @author Walter Hanson
	 * 
	 */
	public SkillPanel(MainGUI theGui) {
		setLayout(null);
		gui = theGui;
		gUtil = new GUIUtility();
		currentSkills = new ArrayList();
		skillDescriptionText = new JTextPane();
		currentShip = null;
		JLabel lblSkillList = new JLabel("Skill List:");
		lblSkillList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSkillList.setBounds(509, 11, 85, 23);
		add(lblSkillList);
		
		skillListCBox = new JComboBox();
		skillListCBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				skillDescriptionText.removeAll();
				updateSkillDescription(0);
			}
		});
		AutoCompletion.enable(skillListCBox);
		skillListCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				skillDescriptionText.removeAll();
				updateSkillDescription(0);
			}
		});
		skillListCBox.setBounds(434, 36, 255, 28);
		skillListCBox.setMaximumRowCount(10);
		skillListCBox.addItem("");
		ArrayList<String> skillNames = null;
		try {
			skillNames = gUtil.getNamesOfSkills();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Collections.sort(skillNames);
		for(String skillName: skillNames) {
			skillListCBox.addItem(skillName);
		}
		add(skillListCBox);
		
		JButton btnAddSkill = new JButton("Add Skill");
		btnAddSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((String) skillListCBox.getSelectedItem() == "") {
					return;
				}
				try {
					currentSkills.add(new Skill((String) skillListCBox.getSelectedItem()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateActiveSkills();
			}
		});
		btnAddSkill.setBounds(700, 39, 113, 25);
		add(btnAddSkill);
		
		JLabel lblSkillDescription = new JLabel("Skill Description:");
		lblSkillDescription.setBounds(493, 88, 136, 28);
		lblSkillDescription.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblSkillDescription);
		
		JScrollPane skillDescriptionScrollPane = new JScrollPane();
		skillDescriptionScrollPane.setBounds(344, 127, 435, 133);
		add(skillDescriptionScrollPane);
		
		skillDescriptionText.setEditable(false);
		skillDescriptionScrollPane.setViewportView(skillDescriptionText);
		
		JLabel lblSkillUsers = new JLabel("Skill Users:");
		lblSkillUsers.setBounds(509, 287, 104, 23);
		lblSkillUsers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblSkillUsers);
		
		JScrollPane skillUserScrollPane = new JScrollPane();
		skillUserScrollPane.setBounds(344, 318, 435, 78);
		add(skillUserScrollPane);
		
		skillUserText = new JTextPane();
		skillUserText.setEditable(false);
		skillUserScrollPane.setViewportView(skillUserText);
		
		JScrollPane activeSkillScrollPane = new JScrollPane();
		activeSkillScrollPane.setBounds(376, 445, 370, 124);
		add(activeSkillScrollPane);
		
		activeSkillsList = new JList();
		activeSkillScrollPane.setViewportView(activeSkillsList);
		activeSkillsList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				updateSkillDescription(0);
			}
		});
		activeSkillsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateSkillDescription(1);
			}
		});
		
		JLabel lblActiveSkills = new JLabel("Active Skills:");
		lblActiveSkills.setBounds(509, 407, 104, 23);
		lblActiveSkills.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblActiveSkills);
		
		JButton btnRemoveSelected = new JButton("Remove Skill");
		btnRemoveSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> skills = activeSkillsList.getSelectedValuesList();
				for(String skillName: skills) {
					for(Skill skill: currentSkills) {
						//java.util.ConcurrentModificationException
						if(skill.getSkillName() == skillName) {
							currentSkills.remove(skill);
							break;
						}
					}
				}
				updateActiveSkills();
				updateSkillDescription(1);
			}
		});
		btnRemoveSelected.setBounds(820, 474, 127, 23);
		add(btnRemoveSelected);
		
		JButton btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentSkills.clear();
				updateActiveSkills();
				updateSkillDescription(0);
			}
		});
		btnRemoveAll.setBounds(820, 508, 127, 23);
		add(btnRemoveAll);

	}
	/**
	 * Method to update the active skill JList.
	 * @author Walter Hanson
	 */

	@SuppressWarnings("unchecked")
	protected void updateActiveSkills() {
		activeSkillsList.removeAll();
		Collections.sort(currentSkills);
		List<String> skillNames = new ArrayList();
		for(Skill s: currentSkills) {
			skillNames.add(s.getSkillName());
		}
		gui.setSkills(currentSkills);
		activeSkillsList.setListData(skillNames.toArray());
	}
	
	/**
	 * Method to update the skill description text.
	 * @author Walter Hanson
	 * @param theOption is which skill location to display description for.
	 */
	protected void updateSkillDescription(int theOption) {
		Skill s = null;
		try {
			if (theOption == 0) {
				if((String) skillListCBox.getSelectedItem() != "") {
					s = new Skill((String) skillListCBox.getSelectedItem());
					skillDescriptionText.setText(s.getSkillDesc());
					skillUserText.setText(s.getSkillUsers());
				}
			}
			else {
				if(currentSkills.isEmpty()) {
					updateSkillDescription(0);
					return;
				}
				if(activeSkillsList.getSelectedIndex() == -1) {
					activeSkillsList.setSelectedIndex(0);
				}
				s = currentSkills.get(activeSkillsList.getSelectedIndex());
				skillDescriptionText.setText(s.getSkillDesc());
				skillUserText.setText(s.getSkillUsers());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * When SkillPanel gets selected, check if a new ship has been selected and reset skills.
	 * Otherwise, keep everything the way it is.
	 * 
	 * @param theShip is the current ship being used for calculations.
	 * @author Walter Hanson
	 */
	public void checkShip(ShipFile activeShip) {
//		System.out.println(theShip + " " + theType);
		if(currentShip == null) {
			currentShip = activeShip;

		}
		else if(activeShip.getShipName().equals(currentShip.getShipName())) {
//			System.out.println("BREAK");
			return;
		}
		currentShip = activeShip;
		activeSkillsList.removeAll();
		currentSkills.clear();
		int skill = 1;
//		System.out.println("Skill is " + currentShip.getSkill(skill));
		while(!currentShip.getSkill(skill).equals("NULL")) {
			try {
//				System.out.println(currentShip.getSkill(skill));
				currentSkills.add(new Skill(currentShip.getSkill(skill)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			skill++;
		}
		updateActiveSkills();
	}
}
