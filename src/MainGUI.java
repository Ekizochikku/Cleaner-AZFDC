import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JPanel shipWeaponPane;
	private JPanel skillPane;
	private JPanel calculatePane;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1071, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		panel = new JPanel();
		shipWeaponPane = new ShipWeaponPanel();
		skillPane = new SkillPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton shipWeaponButton = new JButton("Ships/Weapons");
		shipWeaponButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panel);
				panel = shipWeaponPane;
				contentPane.add(panel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		toolBar.add(shipWeaponButton);
		
		JButton skillsButton = new JButton("Skills");
		skillsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(panel);
				panel = skillPane;
				contentPane.add(panel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		toolBar.add(skillsButton);
		
		
	}

}
