import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.text.NumberFormatter;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
//NOTE: Needs to be changed once Brian says GO AHEAD
public class WeaponPanel extends JPanel implements ActionListener{
	
	JComboBox<Object> weaponName1Combo, weaponName2Combo, weaponName3Combo, 
	weaponType1Combo, weaponType2Combo, weaponType3Combo, aux1Combo, aux2Combo;
	


	//Bomb/plane boxes
	//changes to jformatted text field to restrict to only numbers
	//fudge you Walter
	JFormattedTextField bomb1Plane3Text, bomb1Plane1Text, bomb1Plane2Text,
	bomb2Plane1Text, bomb2Plane2Text, bomb2Plane3Text,
	torpedoPlane1Text, torpedoPlane2Text, torpedoPlane3Text;
	
	//array list for the 3x3
	ArrayList<Object> threeByThreeMouse;
	
	//Auxiliary TextPanes
	JTextPane auxHealthTxt, firepowerTxt, antiAirTxt, torpedoTxt, aviationTxt; 
	
	JRadioButton rdbtnBlue, rdbtnPurple, rdbtnRed, rdbtnHe, rdbtnAP, rdbtnEven, rdbtnOdd;
	
	//First Salvo is Battle ships, battle cruisers, and aviation battleships
	//critical, manual, armor  always on
	JCheckBox chckbxFirstSalvo, chckbxCriticalHit, chckbxManual, chckbxArmor; 
	boolean salvo, critical, manual, armor;
	
	ArrayList<JRadioButton> colorGroupList = new ArrayList<JRadioButton>(); 
	
	String previousName = null;
	//making auxgear here aswell and set it on the main gui one at the end since too lazy to write the long chain of getters and setters
	AuxGear aux1, aux2;
	MainGUI guiVariables;
	GUIUtility guiU;
	
	private int evenOdd = -1; //0 for even, 1 for odd, -1 for non selected
	
	private final ButtonGroup ammoGroup = new ButtonGroup();
	private final ButtonGroup evenOddGroup = new ButtonGroup();
	private final ButtonGroup colorGroup = new ButtonGroup();
	
	private int colorNumber = 0;
	
	public WeaponPanel(MainGUI frame, JComboBox<Object> weaponName1Combo, JComboBox<Object> weaponName2Combo,
			JComboBox<Object> weaponName3Combo, JComboBox<Object> weaponType1Combo, JComboBox<Object> weaponType2Combo,
			JComboBox<Object> weaponType3Combo) {
		super();
		this.weaponName1Combo = weaponName1Combo;
		this.weaponName2Combo = weaponName2Combo;
		this.weaponName3Combo = weaponName3Combo;
		this.weaponType1Combo = weaponType1Combo;
		this.weaponType2Combo = weaponType2Combo;
		this.weaponType3Combo = weaponType3Combo;
	}


	/**
	 * @wbp.parser.constructor
	 */
	public WeaponPanel(MainGUI guiVairables) throws FileNotFoundException, IOException {
		setLayout(null);
		this.guiVariables = guiVairables;
		JLabel weaponType1Label = new JLabel("Weapon Type Slot 1:");
		weaponType1Label.setBounds(10, 11, 132, 25);
		add(weaponType1Label);
		
		JLabel weaponName1Label = new JLabel("Weapon Name Slot 1:");
		weaponName1Label.setBounds(287, 11, 144, 20);
		add(weaponName1Label);
		
		weaponType1Combo = new JComboBox<Object>(new Object[]{});
		weaponType1Combo.setMaximumRowCount(5);
		weaponType1Combo.setBounds(10, 47, 217, 25);
		add(weaponType1Combo);
		weaponType1Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

			}
	});
		//carriers, aviation 3rd slot only, i13 third slot only 
		weaponName1Combo = new JComboBox<Object>();
		weaponName1Combo.setBounds(287, 46, 364, 23);
		add(weaponName1Combo);
		
		
		JLabel weaponType2Label = new JLabel("Weapon Type Slot 2:");
		weaponType2Label.setBounds(10, 95, 132, 25);
		add(weaponType2Label);
		
		JLabel weaponName2Label = new JLabel("Weapon Name Slot 2:");
		weaponName2Label.setBounds(287, 95, 144, 20);
		add(weaponName2Label);
		
		weaponType2Combo = new JComboBox<Object>();
		weaponType2Combo.setBounds(10, 131, 217, 25);
		add(weaponType2Combo);
		
		weaponType2Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

			}
	});
		
		weaponName2Combo = new JComboBox<Object>();
		weaponName2Combo.setBounds(287, 130, 364, 23);
		add(weaponName2Combo);
		
		JLabel weaponType3Label = new JLabel("Weapon Type Slot 3:");
		weaponType3Label.setBounds(10, 167, 132, 25);
		add(weaponType3Label);
		
		JLabel weaponName3Label = new JLabel("Weapon Name Slot 3:");
		weaponName3Label.setBounds(287, 167, 144, 20);
		add(weaponName3Label);
		
		weaponType3Combo = new JComboBox<Object>();
		weaponType3Combo.setBounds(10, 203, 217, 25);
		add(weaponType3Combo);
		weaponType3Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

			}
	});
		
		
		weaponName3Combo = new JComboBox<Object>();
		weaponName3Combo.setBounds(287, 202, 364, 23);
		add(weaponName3Combo);
		
		
		System.out.println("inserting weapon type slots");
		GUIUtility.insertAllWeaponTypeSlots(guiVairables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
				weaponName1Combo, weaponName2Combo, weaponName3Combo, false);
		
		JLabel lblBombsDropped = new JLabel("Bombs 1 Dropped:");
		lblBombsDropped.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBombsDropped.setBounds(10, 278, 144, 30);
		add(lblBombsDropped);
		
		JLabel lblBombsDropped_1 = new JLabel("Bombs 2 Dropped:");
		lblBombsDropped_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBombsDropped_1.setBounds(10, 319, 144, 30);
		add(lblBombsDropped_1);
		
		JLabel lblTorpedosDropped = new JLabel("Torpedos Dropped:");
		lblTorpedosDropped.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTorpedosDropped.setBounds(10, 360, 144, 30);
		add(lblTorpedosDropped);
		
		JLabel lblPlane = new JLabel("Plane 1");
		lblPlane.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlane.setBounds(164, 257, 75, 25);
		add(lblPlane);
		
		JLabel lblPlane_1 = new JLabel("Plane 2");
		lblPlane_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlane_1.setBounds(249, 257, 75, 25);
		add(lblPlane_1);
		
		JLabel lblPlane_2 = new JLabel("Plane 3");
		lblPlane_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlane_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPlane_2.setBounds(334, 257, 75, 25);
		add(lblPlane_2);
		
		/**
		 * @author Kevin Nguyen
		 * Number formatter for the 3x3 bombs
		 */
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format) {
	    	/**
			 * no idea what this is for but it told me to
			 */
			private static final long serialVersionUID = 1L;

			//Overrides the number formatter so that it allows blanks
	    	//Before it didn't allow you to delete the last digit which was stupid
	    	@Override
	    	public Object stringToValue(String string)
                    throws ParseException {
                    if (string == null || string.length() == 0) {
                        return null;
                    }
						return super.stringToValue(string);
	    	}
        };
	    
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(99);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		
		bomb1Plane1Text = new JFormattedTextField(formatter);
		bomb1Plane1Text.setHorizontalAlignment(SwingConstants.CENTER);
		bomb1Plane1Text.setColumns(10);
		bomb1Plane1Text.setText("0");
		bomb1Plane1Text.setBounds(177, 286, 49, 25);
		add(bomb1Plane1Text);


		bomb1Plane2Text = new JFormattedTextField(formatter);
		bomb1Plane2Text.setHorizontalAlignment(SwingConstants.CENTER);
		bomb1Plane2Text.setText("0");
		bomb1Plane2Text.setBounds(262, 286, 49, 25);
		add(bomb1Plane2Text);
		
		bomb1Plane3Text = new JFormattedTextField(formatter);
		bomb1Plane3Text.setHorizontalAlignment(SwingConstants.CENTER);
		bomb1Plane3Text.setText("0");
		bomb1Plane3Text.setBounds(347, 286, 49, 25);
		add(bomb1Plane3Text);
		
		bomb2Plane1Text = new JFormattedTextField(formatter);
		bomb2Plane1Text.setHorizontalAlignment(SwingConstants.CENTER);
		bomb2Plane1Text.setText("0");
		bomb2Plane1Text.setBounds(177, 326, 49, 25);
		add(bomb2Plane1Text);
		
		bomb2Plane2Text = new JFormattedTextField(formatter);
		bomb2Plane2Text.setHorizontalAlignment(SwingConstants.CENTER);
		bomb2Plane2Text.setText("0");
		bomb2Plane2Text.setBounds(262, 326, 49, 25);
		add(bomb2Plane2Text);
		
		bomb2Plane3Text = new JFormattedTextField(formatter);
		bomb2Plane3Text.setHorizontalAlignment(SwingConstants.CENTER);
		bomb2Plane3Text.setText("0");
		bomb2Plane3Text.setBounds(347, 326, 49, 25);
		add(bomb2Plane3Text);
		
		torpedoPlane1Text = new JFormattedTextField(formatter);
		torpedoPlane1Text.setHorizontalAlignment(SwingConstants.CENTER);
		torpedoPlane1Text.setText("0");
		torpedoPlane1Text.setBounds(177, 365, 49, 25);
		add(torpedoPlane1Text);
		
		torpedoPlane2Text = new JFormattedTextField(formatter);
		torpedoPlane2Text.setHorizontalAlignment(SwingConstants.CENTER);
		torpedoPlane2Text.setText("0");
		torpedoPlane2Text.setBounds(262, 365, 49, 25);
		add(torpedoPlane2Text);
		
		torpedoPlane3Text = new JFormattedTextField(formatter);
		torpedoPlane3Text.setHorizontalAlignment(SwingConstants.CENTER);
		torpedoPlane3Text.setText("0");
		torpedoPlane3Text.setBounds(347, 365, 49, 25);
		add(torpedoPlane3Text);
		
		//might move this to the guiUtil
		//adding all elements to the array
		 threeByThreeMouse = new ArrayList<Object>();
		 //all elements of the 3x3 thingy
		 threeByThreeMouse.add(bomb1Plane1Text);
		 threeByThreeMouse.add(bomb1Plane2Text);
		 threeByThreeMouse.add(bomb1Plane3Text);
		 threeByThreeMouse.add(bomb2Plane1Text);
		 threeByThreeMouse.add(bomb2Plane2Text);
		 threeByThreeMouse.add(bomb2Plane3Text);
		 threeByThreeMouse.add(torpedoPlane1Text);
		 threeByThreeMouse.add(torpedoPlane2Text);
		 threeByThreeMouse.add(torpedoPlane3Text);
		 
		
		//focus listener for the 3x3 to erase the 0 values when clicked
		FocusListener eraseZero = new FocusAdapter(){
	           public void focusGained(FocusEvent e){
		            JFormattedTextField source = (JFormattedTextField)e.getComponent();
		            source.setText("");
		            source.removeFocusListener(this);
	           }
	        };
	    boolean carrier = false;
	    //get from main gui or ship file?
		//System.out.println(guiVariables.getShipType());
	    //we do this outside so we don't have to do .contains on every single element
	    if(guiVariables.getShipType().contains("Destroyers") ) {
	    	carrier = true;
	    }
	    
	    for(int i = 0; i < threeByThreeMouse.size(); i++) {
			((JFormattedTextField)threeByThreeMouse.get(i)).addFocusListener(eraseZero);
			//get from main gui or ship file?
			//System.out.println(guiVariables.getShipType());
			if(carrier) {
				((JFormattedTextField)threeByThreeMouse.get(i)).setEnabled(true);
			} else {
				((JFormattedTextField)threeByThreeMouse.get(i)).setEnabled(false);
			}
	    }
		
		JLabel lblNewLabel = new JLabel("Ammo Type:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 426, 109, 25);
		add(lblNewLabel);
		
		rdbtnHe = new JRadioButton("HE");
		rdbtnHe.setEnabled(false);
		ammoGroup.add(rdbtnHe);
		rdbtnHe.setBounds(10, 453, 49, 23);
		add(rdbtnHe);
		
		//move these to sendInfo
		rdbtnHe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiVariables.setHeAp(0);
				System.out.println("HE is selected should display 0 :" + guiVariables.getHeAp());
			}
		});
		
		
		rdbtnAP = new JRadioButton("AP");
		rdbtnAP.setEnabled(false);
		ammoGroup.add(rdbtnAP);
		rdbtnAP.setBounds(71, 453, 48, 23);
		add(rdbtnAP);
		
		rdbtnAP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiVariables.setHeAp(1);
				System.out.println("Ap is selected should display 1 :" + guiVariables.getHeAp());
			}
		});
		
		rdbtnEven = new JRadioButton("Even");
		rdbtnEven.setEnabled(false);
		evenOddGroup.add(rdbtnEven);
		rdbtnEven.setBounds(134, 453, 66, 23);
		add(rdbtnEven);
		
		rdbtnEven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiVariables.setEvenOdd(0);
				evenOdd = 0;
				System.out.println("even is selected should display 0: " + evenOdd + 
						" Getter:" + guiVariables.getEvenOdd());
			}
		});
		
		
		rdbtnOdd = new JRadioButton("Odd");
		rdbtnOdd.setEnabled(false);
		evenOddGroup.add(rdbtnOdd);
		rdbtnOdd.setBounds(215, 453, 49, 23);
		add(rdbtnOdd);
		
		rdbtnOdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiVariables.setEvenOdd(1);
				evenOdd = 1;
				System.out.println("Odd is selected should display 1 :" + evenOdd +
						" Getter:" + guiVariables.getEvenOdd());
			}
		});
		
		//adding tool tip to both manually since no point for loop for 2
		rdbtnHe.setToolTipText("HE and AP rounds are only selectable with 'Roon' ");
		rdbtnAP.setToolTipText("HE and AP rounds are only selectable with 'Roon' ");
		rdbtnEven.setToolTipText("Even and Odd rounds are only selectable with Friedrich der Grosse");
		rdbtnOdd.setToolTipText("Even and Odd rounds are only selectable when Friedrich der Grosse");
				
		rdbtnBlue = new JRadioButton("Blue");
		colorGroup.add(rdbtnBlue);
		colorGroupList.add(rdbtnBlue);
		rdbtnBlue.setBounds(437, 426, 58, 23);
		add(rdbtnBlue);
		
			
		rdbtnPurple = new JRadioButton("Purple");
		colorGroup.add(rdbtnPurple);
		colorGroupList.add(rdbtnPurple);
		rdbtnPurple.setBounds(500, 426, 76, 23);
		add(rdbtnPurple);
		
		rdbtnRed = new JRadioButton("Red");
		colorGroup.add(rdbtnRed);
		colorGroupList.add(rdbtnRed);

		rdbtnRed.setBounds(583, 426, 49, 23);
		add(rdbtnRed);
				
		Enumeration<AbstractButton> elements = colorGroup.getElements();
		//enumeration to reduce redundancy for colors
		//need to test more for bugs
	    while (elements.hasMoreElements()) {
	    	JRadioButton theButton = (JRadioButton) elements.nextElement();
	    	theButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guiVariables.setColor(colorNumber);
					System.out.println("A Color is selected, 0-2 :" + guiVariables.getColor());
					colorNumber++;
				}
			});
	    	theButton.setEnabled(false);
	    	theButton.setToolTipText("Color Ammo Types are only applicable to 'Muse' ships");	    	
	    }

		
		chckbxFirstSalvo = new JCheckBox("First Salvo");
		chckbxFirstSalvo.setBounds(407, 463, 97, 23);
		add(chckbxFirstSalvo);
		
		chckbxCriticalHit = new JCheckBox("Critical Hit");
		chckbxCriticalHit.setBounds(525, 463, 97, 23);
		add(chckbxCriticalHit);
		
		chckbxManual = new JCheckBox("Manual");
		chckbxManual.setBounds(407, 503, 97, 23);
		add(chckbxManual);
		
		chckbxArmor = new JCheckBox("Armor Broken");
		chckbxArmor.setBounds(525, 503, 107, 23);
		add(chckbxArmor);
		
		//action listeners for the checkbox stuff, might be bad if user spams it
		//since we constantly keep updating main gui
		/*
		chckbxFirstSalvo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxFirstSalvo.isSelected()) {
					guiVariables.setFirstSalvo(true);
				} else {
					guiVariables.setFirstSalvo(false);
				}
			}
		});
		chckbxCriticalHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxCriticalHit.isSelected()) {
					guiVariables.setCrit(true);
				} else {
					guiVariables.setCrit(false);
				}
			}
		});
		chckbxArmor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxArmor.isSelected()) {
					guiVariables.setArmorBreak(true);
				} else {
					guiVariables.setArmorBreak(false);
				}
			}
		}); 
		*/
		
		
		
		JLabel lblAuxiliary = new JLabel("Auxiliary 1:");
		lblAuxiliary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuxiliary.setBounds(760, 13, 109, 25);
		add(lblAuxiliary);
		
		JLabel lblAuxiliary_1 = new JLabel("Auxiliary 2:");
		lblAuxiliary_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuxiliary_1.setBounds(760, 97, 109, 25);
		add(lblAuxiliary_1);
		
		JLabel lblAuxHealth = new JLabel("Aux Health:");
		lblAuxHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAuxHealth.setBounds(755, 185, 109, 25);
		add(lblAuxHealth);
		
		JLabel lblFirepower = new JLabel("Firepower:");
		lblFirepower.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFirepower.setBounds(755, 270, 109, 25);
		add(lblFirepower);
		
		JLabel lblAntiAir = new JLabel("Anti Air:");
		lblAntiAir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAntiAir.setBounds(924, 270, 109, 25);
		add(lblAntiAir);
		
		JLabel lblTorpedo = new JLabel("Torpedo:");
		lblTorpedo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTorpedo.setBounds(755, 341, 109, 25);
		add(lblTorpedo);
		
		JLabel lblAviation = new JLabel("Aviation:");
		lblAviation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAviation.setBounds(924, 342, 109, 25);
		add(lblAviation);
		
		aux1Combo = new JComboBox<Object>();
		aux1Combo.setBounds(751, 46, 330, 25);
		add(aux1Combo);
		
		List<String> auxNames = new ArrayList<String>();
		auxNames = GUIUtility.getAuxNames();
		//populating combo box with aux names
		Collections.sort(auxNames);
		aux1Combo.setModel(new DefaultComboBoxModel<Object>(auxNames.toArray()));
		aux1Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//aux string
				try {
					aux1 = new AuxGear((String) aux1Combo.getSelectedItem());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//JTextPane auxHealthTxt, firepowerTxt, antiAirTxt, torpedoTxt, aviationTxt; 
				if(aux2 == null) {
					//why call getters when we can get directly from list? :(
					//especially when I have to parse the getters anyways? 
					auxHealthTxt.setText("1: " + String.valueOf(aux1.getHealth()));
					firepowerTxt.setText("1: " + String.valueOf(aux1.getFirepower()));
					torpedoTxt.setText("1: " + String.valueOf(aux1.getTorpedo()));
					antiAirTxt.setText("1: " + String.valueOf(aux1.getAA()));
					aviationTxt.setText("1: " + String.valueOf(aux1.getAviation()));
				} else {
					auxHealthTxt.setText("1: " + String.valueOf(aux1.getHealth()) + "   2: " + String.valueOf(aux2.getHealth()));
					firepowerTxt.setText("1: " + String.valueOf(aux1.getFirepower())+ "   2: " + String.valueOf(aux2.getFirepower()));
					torpedoTxt.setText("1: " + String.valueOf(aux1.getTorpedo())+ "   2: " + String.valueOf(aux2.getTorpedo()) );
					antiAirTxt.setText("1: " + String.valueOf(aux1.getAA())+ "   2: " + String.valueOf(aux2.getAA()));
					aviationTxt.setText("1: " + String.valueOf(aux1.getAviation())+ "   2: " + String.valueOf(aux2.getAviation()));
				}
				guiVariables.setAux1(aux1);

			}
		});

		
		aux2Combo = new JComboBox<Object>();
		aux2Combo.setBounds(751, 130, 330, 25);
		add(aux2Combo);
		aux2Combo.setModel(new DefaultComboBoxModel<Object>(auxNames.toArray()));
		aux2Combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//aux string
				try {
					aux2 = new AuxGear((String) aux2Combo.getSelectedItem());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				//JTextPane auxHealthTxt, firepowerTxt, antiAirTxt, torpedoTxt, aviationTxt; 
				if(aux1 == null) {
					//why call getters when we can get directly from list? :(
					//especially when I have to parse the getters anyways? 
					auxHealthTxt.setText("2: " + String.valueOf(aux2.getHealth()));
					firepowerTxt.setText("2: " + String.valueOf(aux2.getFirepower()));
					torpedoTxt.setText("2: " + String.valueOf(aux2.getTorpedo()));
					antiAirTxt.setText("2: " + String.valueOf(aux2.getAA()));
					aviationTxt.setText("2: " + String.valueOf(aux2.getAviation()));
				} else {
					auxHealthTxt.setText("1: " + String.valueOf(aux1.getHealth()) + "   2: " + String.valueOf(aux2.getHealth()));
					firepowerTxt.setText("1: " + String.valueOf(aux1.getFirepower())+ "   2: " + String.valueOf(aux2.getFirepower()));
					torpedoTxt.setText("1: " + String.valueOf(aux1.getTorpedo())+ "   2: " + String.valueOf(aux2.getTorpedo()) );
					antiAirTxt.setText("1: " + String.valueOf(aux1.getAA())+ "   2: " + String.valueOf(aux2.getAA()));
					aviationTxt.setText("1: " + String.valueOf(aux1.getAviation())+ "   2: " + String.valueOf(aux2.getAviation()));
				}
				guiVariables.setAux2(aux2);
			}
		});
		
		auxHealthTxt = new JTextPane();
		auxHealthTxt.setEditable(false);
		auxHealthTxt.setBounds(755, 221, 161, 30);
		add(auxHealthTxt);
		
		firepowerTxt = new JTextPane();
		firepowerTxt.setEditable(false);
		firepowerTxt.setBounds(751, 301, 117, 30);
		add(firepowerTxt);
		
		antiAirTxt = new JTextPane();
		antiAirTxt.setEditable(false);
		antiAirTxt.setBounds(920, 301, 117, 30);
		add(antiAirTxt);
		
		torpedoTxt = new JTextPane();
		torpedoTxt.setEditable(false);
		torpedoTxt.setBounds(751, 366, 117, 30);
		add(torpedoTxt);
		
		aviationTxt = new JTextPane();
		aviationTxt.setEditable(false);
		aviationTxt.setBounds(920, 366, 117, 30);
		add(aviationTxt);
		
		
		JLabel lblNote = new JLabel("Note:");
		lblNote.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNote.setBounds(385, 430, 46, 14);
		add(lblNote);
		
	}
	   
	 
	//helper method for every weapon name, action listener to set the current Weapon Name
	/*
	private void addActionName(MainGUI guiVariables, JComboBox mainBox, String slotNumber) {
		//invoking a method with a string might be better to manually do it
		//initial screen
		mainBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				GUIUtility.insertType(mainBox, false, guiVariables.getCurrentShipName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
}*/

	private void populateCombo(JComboBox box, ArrayList list) {
		DefaultComboBoxModel dml= new DefaultComboBoxModel();
		for (int i = 0; i < list.size(); i++) {
//		  dml.addElement(((Object) list.get(i)).getField());
		}

		box.setModel(dml);
	}

	//made this method on accident leaving for just in case we need weapon info on other panels in the future
	public void sendInfo() {
		String[] weaponInfo = new String[6];
		
		weaponInfo[0] = (String) weaponName1Combo.getSelectedItem(); 
		weaponInfo[1] = (String) weaponName2Combo.getSelectedItem();
		weaponInfo[2] = (String) weaponName3Combo.getSelectedItem(); 
		weaponInfo[3] = (String) weaponType1Combo.getSelectedItem(); 
		weaponInfo[4] = (String) weaponType2Combo.getSelectedItem(); 
		weaponInfo[5]	= (String) weaponType3Combo.getSelectedItem();
		
		if(chckbxCriticalHit.isSelected()) {
			guiVariables.setCrit(true);
		} else {
			guiVariables.setCrit(false);
		}
		
		if(chckbxArmor.isSelected()) {
			guiVariables.setArmorBreak(true);
		} else {
			guiVariables.setArmorBreak(false);
		}
		if(chckbxFirstSalvo.isSelected()) {
			guiVariables.setFirstSalvo(true);
		} else {
			guiVariables.setFirstSalvo(false);
		}
		
		if(rdbtnHe.isSelected()) {
			guiVariables.setHeAp(0);
		} else {
			guiVariables.setHeAp(1);
		}
		
		if(rdbtnEven.isSelected()) {
			guiVariables.setEvenOdd(0);
		} else {
			guiVariables.setEvenOdd(1);
		}
		
		
		guiVariables.setWeaponNamesAndTypes(weaponInfo);
		
		//this is the 3x3, needs to be tested (especially the parsing)
		int[] threeByThreeArray = new int[9];
		for(int i = 0; i < threeByThreeMouse.size(); i++) {
			if((JFormattedTextField) threeByThreeMouse.get(i) != null) {
				JFormattedTextField textField = (JFormattedTextField)threeByThreeMouse.get(i);
				String theString = textField.getText();
				int theNumber = Integer.parseInt(theString);
				threeByThreeArray[i] =  theNumber;
			} else {
				threeByThreeArray[i] =  0;
			}
	    }
		guiVariables.threeByThree(threeByThreeArray);
		
		System.out.println("The send info test for Radio Buttons : " + guiVariables.getHeAp() + " EvenOdd: " + guiVariables.getEvenOdd());
		//will move and replace rest of the action listeners (like Aux) down here once we know rest is working
		
		
		
	}
	//helper method to set the action listeners for every weapon type box action
	public void comboBoxTypeAction(MainGUI guiVariables) {
			ShipFile currentShip = guiVariables.getCurrentShip();
			System.out.println("Hello we entered here in combo box type action! " + currentShip.getShipName());
			GUIUtility.insertAllWeaponTypeSlots(guiVariables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);

	}

	
	public void onSwitch() {
		
		
		boolean carrier = false;
		String type = null;
		String name = null;
		
		
		if(guiVariables.getShipType() != null && guiVariables.getCurrentShip() != null && guiVariables.getCurrentShip().getShipName() != "") {
			name = guiVariables.getCurrentShip().getShipName();
			type = guiVariables.getShipType();
			//check to see if the ship name has changed so we only insert if new ship
			if(previousName != name) {
				previousName = name;
				GUIUtility.insertAllWeaponTypeSlots(guiVariables, weaponType1Combo, weaponType2Combo,  weaponType3Combo, 
						weaponName1Combo, weaponName2Combo, weaponName3Combo, false);
				//default values
				guiVariables.setEvenOdd(0);
				guiVariables.setHeAp(0);
				guiVariables.setColor(0);
				rdbtnBlue.setSelected(true);
				chckbxArmor.setSelected(false);
				chckbxCriticalHit.setSelected(false);
				chckbxFirstSalvo.setSelected(false);
				
				//have to change this to only be the skill expert loader
				if(name.equals("Roon")) {
					//System.out.println("Not entering this check!!!");
					//Need to be changed so they're in array to reduce redundancy
					rdbtnHe.setEnabled(true);
					rdbtnAP.setEnabled(true);
					
					rdbtnHe.setToolTipText(null);
					rdbtnAP.setToolTipText(null);
					rdbtnBlue.setSelected(true);

					//will put this in it's own method to reduce redundancy later or for loop
					
					for (JRadioButton btn : colorGroupList) {
				         btn.setSelected(false);
				         btn.setEnabled(false);
				         btn.setToolTipText("Color Ammo Types are only applicable to 'Muse' ships");
				    }
				//needs to be changed to a skill and ship type later
				//needs Sonata of chaos instead of friedrich the grosse
				} else if(name.equals("Friedrich der Grosse (Retrofit)")) {
					rdbtnHe.setEnabled(false);
					rdbtnAP.setEnabled(false);
					rdbtnBlue.setSelected(true);

					//Any default for radio buttons?
					rdbtnEven.setSelected(true);
					rdbtnEven.setEnabled(true);
					rdbtnOdd.setEnabled(true);
					rdbtnOdd.setToolTipText(null);
					rdbtnEven.setToolTipText(null);
					for (JRadioButton btn : colorGroupList) {
				         btn.setSelected(false);
				         btn.setEnabled(false);
				         btn.setToolTipText("Color Ammo Types are only applicable to 'Muse' ships");
				    }
					
				//checking for ships that have Muse
				//this is an else if, i'm assuming there are no muse ships that are named Roon, Friedrich, Alabama
				//will need to be changed if otherwise
					
				}else if(name.indexOf("Muse") != -1) {
					
					for (JRadioButton btn : colorGroupList) {
				         btn.setEnabled(true);
				         btn.setToolTipText(null);
				    }
					
				}
				
				else {
					rdbtnHe.setSelected(true);
					rdbtnHe.setEnabled(false);
					rdbtnAP.setEnabled(false);
					//buttonGroup.clearSelection();
					rdbtnBlue.setSelected(true);

					rdbtnHe.setToolTipText("HE and AP rounds are only selectable with the skill 'Expert Loader' ");
					rdbtnAP.setToolTipText("HE and AP rounds are only selectable with 'Expert Loader' ");
					rdbtnEven.setToolTipText("Even and Odd rounds are only selectable with Friedrich der Grosse");
					rdbtnOdd.setToolTipText("Even and Odd rounds are only selectable when Friedrich der Grosse");
					
					for (JRadioButton btn : colorGroupList) {
				         btn.setSelected(false);
				         btn.setEnabled(false);
				         btn.setToolTipText("Color Ammo Types are only applicable to 'Muse' ships");
				    }
				    //for some reason setSelected above doesnt work so this has to be done
					colorGroup.clearSelection();
					
					rdbtnEven.setEnabled(false);
					//default value
					rdbtnEven.setSelected(true);
					rdbtnOdd.setEnabled(false);
					
				}
			}
			
			
		}
		//adding a checker if skills changed later
		//well fuck
		/*
		ArrayList<Skill> theSkills = guiVariables.getSkills();
		boolean skillExist = false;
		for( int i = 0; i <  theSkills.size(); i++){
		     if(theSkills.get(i).getSkillName().equals("Just Gettin' Fired Up")) {
		    	 skillExist = true;
		    	 nodesKilledTxt.setEnabled(true);
		    	 nodesKilledTxt.setEditable(true);
		    	 lblNodesKilled.setEnabled(true);
				 chckbxArmor.setEnabled(true);
				 break;
		     } 
		}
		
		if(!skillExist) {
			nodesKilledTxt.setEnabled(false);
			nodesKilledTxt.setText("");
			lblNodesKilled.setEnabled(false);
			chckbxArmor.setEnabled(false);
			chckbxArmor.setSelected(false);
		}
		
		*/
	    //get from main gui or ship file?
	    //we do this outside so we don't have to do .contains on every single element
	    if(type != null && (type.contains("Carriers") || type.equals("Aviation Battleships")) || (name != null && name.equals("I-13")) ) {
	    	carrier = true;
	    } else {
	    	carrier = false;
	    }
	    //First Salvo Battle ships, battle cruisers, and aviation battleships
	    if(type != null && (type.contains("Battle"))) {
	    	chckbxFirstSalvo.setEnabled(true);
	    } else {
	    	chckbxFirstSalvo.setEnabled(false);
	    }
	    
	    
	    for(int i = 0; i < threeByThreeMouse.size(); i++) {
			//get from main gui or ship file?
			//System.out.println(guiVariables.getShipType());
			if(carrier) {
				((JFormattedTextField)threeByThreeMouse.get(i)).setEnabled(true);
			} else {
				((JFormattedTextField)threeByThreeMouse.get(i)).setEnabled(false);
				((JFormattedTextField)threeByThreeMouse.get(i)).setText("0");
			}
	    }
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	
