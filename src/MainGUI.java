import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JPanel shipPane;
	private JPanel weaponPane;
	private JPanel skillPane;
	private JPanel calculatePane;
	private JPanel currentPanel;
	private JPanel worldPane;
	//The current damage type being applied
	private int currentDMGType = 0; //0 = HE, 1 = AP
	private String shipTypeName;
	private String currentShipName;
	private String currentColorSelected;
	private String currentWeaponType;
	
	//The current selected weapon name
	private String currentWeaponName = null;
	private String currentWeaponNameSlot2 = null;
	private String curentWeaponNameSlot3 = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.getContentPane().setBackground(Color.GRAY);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public MainGUI() throws FileNotFoundException, IOException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		shipPane = new ShipPanel(this);
		shipPane.setOpaque(true);
		shipPane.setBackground(new Color(210, 210, 210));
		
		shipPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		weaponPane = new WeaponPanel(this);
		weaponPane.setOpaque(true);
		weaponPane.setBackground(new Color(210, 210, 210));
		
		skillPane = new SkillPanel(this);
		
		skillPane.setOpaque(true);
		skillPane.setBackground(new Color(210, 210, 210));
		
		worldPane = new WorldPanel(this);
		worldPane.setOpaque(true);
		worldPane.setBackground(new Color(210, 210, 210));
		
		calculatePane = new CalculatePanel();
		calculatePane.setOpaque(true);
		calculatePane.setBackground(new Color(210, 210, 210));
		currentPanel = shipPane;
		
		currentPanel.setOpaque(true);
		currentPanel.setBackground(new Color(210, 210, 210));
		
		contentPane.add(currentPanel, BorderLayout.CENTER);
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton shipButton = new JButton("Ship");
		shipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(currentPanel);
				currentPanel = shipPane;
				contentPane.add(currentPanel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		toolBar.add(shipButton);
		
		JButton skillsButton = new JButton("Skills");
		skillsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.remove(currentPanel);
				currentPanel = skillPane;
				contentPane.add(currentPanel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		
		JButton btnWeaponsgear = new JButton("Weapons/Gear");
		btnWeaponsgear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(currentPanel);
				currentPanel = weaponPane;
				contentPane.add(currentPanel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		toolBar.add(btnWeaponsgear);
		toolBar.add(skillsButton);
		
		JButton worldButton = new JButton("World");
		worldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(currentPanel);
				currentPanel = worldPane;
				contentPane.add(currentPanel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		toolBar.add(worldButton);
		
		JButton calculateButton = new JButton("Calculate");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.remove(currentPanel);
				currentPanel = calculatePane;
				contentPane.add(currentPanel, BorderLayout.CENTER);
				contentPane.validate();
				contentPane.repaint();
				setVisible(true);
			}
		});
		contentPane.add(calculateButton, BorderLayout.SOUTH);
		
		
	}
	public int getCurrentDMGType() {
		return currentDMGType;
	}

	public void setCurrentDMGType(int currentDMGType) {
		this.currentDMGType = currentDMGType;
	}

	public String getShipTypeName() {
		return shipTypeName;
	}

	public void setShipTypeName(String shipTypeName) {
		this.shipTypeName = shipTypeName;
	}

	public String getCurrentShipName() {
		return currentShipName;
	}

	public void setCurrentShipName(String currentShipName) {
		this.currentShipName = currentShipName;
	}

	public String getCurrentWeaponTypeSlot1() {
		return currentWeaponType;
	}

	public void setCurrentWeaponTypeSlot1(String currentWeaponType) {
		this.currentWeaponType = currentWeaponType;
	}

	public String getCurrentWeaponNameSlot1() {
		return currentWeaponName;
	}
	public String getCurrentWeaponTypeSlot2() {
		return currentWeaponType;
	}

	public void setCurrentWeaponTypeSlot2(String currentWeaponType) {
		this.currentWeaponType = currentWeaponType;
	}
	public String getCurrentWeaponTypeSlot3() {
		return currentWeaponType;
	}

	public void setCurrentWeaponTypeSlot3(String currentWeaponType) {
		this.currentWeaponType = currentWeaponType;
	}
	
	public void setCurrentWeaponNameSlot1(String currentWeaponName) {
		this.currentWeaponName = currentWeaponName;
	}

	public String getCurrentWeaponNameSlot2() {
		return currentWeaponNameSlot2;
	}

	public void setCurrentWeaponNameSlot2(String currentWeaponNameSlot2) {
		this.currentWeaponNameSlot2 = currentWeaponNameSlot2;
	}

	public String getCurentWeaponNameSlot3() {
		return curentWeaponNameSlot3;
	}

	public void setCurentWeaponNameSlot3(String curentWeaponNameSlot3) {
		this.curentWeaponNameSlot3 = curentWeaponNameSlot3;
	}
	
	public void setDangerLvl(int dangerlvl) {
//		System.out.println("Danger Level set to: " + dangerlvl);
		this.currentDangerLevel = dangerlvl;
	}
	
	public int getDangerLvl() {
		return currentDangerLevel;
	}
	
	public void setEnemy(String enemyName) {
//		System.out.println("Enemy set to: " + enemyName);
		this.theCurrentEnemy = enemyName;
	}
	
	public String getEnemy() {
		return theCurrentEnemy;
	}
	
	public void setWorld(String world) {
//		System.out.println("World set to: " + world);
		this.theCurrentWorld = world;
	}
	
	public String getWorld() {
		return theCurrentWorld;
	}
}
