import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTextPane;

public class CalculatePanel extends JPanel {
	
	private MainGUI mainGUI;
	private JTextPane damage1Result;
	private JTextPane damage2Result;
	private JTextPane damage3Result;

	
	public CalculatePanel(MainGUI gui) {
		setLayout(null);
		
		mainGUI = gui;
		
		JLabel lblSlotDamage = new JLabel("Slot 1 Damage Range:");
		lblSlotDamage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSlotDamage.setBounds(464, 35, 187, 39);
		add(lblSlotDamage);
		
		damage1Result = new JTextPane();
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
		
		damage2Result = new JTextPane();
		damage2Result.setEditable(false);
		damage2Result.setBounds(407, 250, 300, 39);
		add(damage2Result);
		
		damage3Result = new JTextPane();
		damage3Result.setEditable(false);
		damage3Result.setBounds(407, 419, 300, 39);
		add(damage3Result);

	}
	
	private void performCalculations() {
		FrontlineCalculations finalDamage = new FrontlineCalculations();
		//Checking if all the parameters are correct
		/*System.out.println("Checking all the parameters:  " + "\n Current Ship type: " + mainGUI.getShipType() +  "\n Ship Name: " + currentShipName +  
				"\n Weapon Type Slot 1: " + currentWeaponType +  "\n Weapon Name Slot 1: " + currentWeaponName +  "\n Current Skills: " +
				mainGUI.getSkills() +  "\n is Critical: " + critical +  "\n World number: " + mainGUI.getWorld() + "\n Enemy Name: " + mainGUI.getEnemy()+   "\n damage type int (0 HE, 1 AP) : " + 
				mainGUI.getCurrentDMGType() +  "\n is manual" + manual +  "\n is first salvo: " + firstSalvo +  "\n current max danger Level: " +  mainGUI.getDangerLvl() + "\n current even odd: " + evenOdd);*/
		
		/*hi System.out.println("Checking parameters SLot 2:  " +
				"\n Weapon Type Slot 2: " + mainGUI.getCurrentWeaponTypeSlot2() +  "\n Weapon Name Slot 2: " + mainGUI.getCurrentWeaponNameSlot2());*/
		try {
			//Will add more if statements to check each parameter later to avoid null pointer exceptions especially when slot 1 has a weapon but 2 doesn't
			//Ship slot hard coded in, no idea what that is yet.
			
			//changed the order for null first before is empty
			if (mainGUI.getCurrentWeaponNameSlot1() != null && !mainGUI.getCurrentWeaponNameSlot1().isEmpty()) {
				if(mainGUI.getShipType().equals("CVL") || mainGUI.getShipType().equals("CV")) {
					UpdatedCarrierCalculations plane1 = new UpdatedCarrierCalculations(mainGUI.getSkills(), mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getCurrentWeaponTypeSlot1(), 
							mainGUI.getCurrentWeaponNameSlot1(), mainGUI.getEnemy(), mainGUI.getWorld(), parseInt(textFieldP1B1), parseInt(textFieldP1B2), parseInt(textFieldP1T), currentColorSelected, auxParameters, auxParameters2);
					Double plane1FinalMaxDamage = plane1.getFinalTotalDamage(1, mainGUI.getSkills(), critical, mainGUI.getWorld(), mainGUI.getDangerLvl(), 2);
					String maxDamageSlot1 = Double.toString(plane1FinalMaxDamage);
					damage1Result.setText(maxDamageSlot1);
	
				} else {

					Double finalMaxDamageSlot1 = finalDamage.getFinalDamage(mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getCurrentWeaponTypeSlot1(), mainGUI.getCurrentWeaponNameSlot1(), 1
							,mainGUI.getSkills(), critical, mainGUI.getWorld(), mainGUI.getEnemy(), mainGUI.getmainGUI.getCurrentDMGType()(), manual, firstSalvo,  mainGUI.getDangerLvl(), evenOdd, 2, armorBreak, currentColorSelected, auxParameters, auxParameters2);
					Double finalMinDamageSlot1 = finalDamage.getFinalDamage(mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getCurrentWeaponTypeSlot1(), mainGUI.getCurrentWeaponNameSlot1(), 1
							,mainGUI.getSkills(), critical, mainGUI.getWorld(), mainGUI.getEnemy(), mainGUI.getCurrentDMGType(), manual, firstSalvo,  mainGUI.getDangerLvl(), evenOdd, 0, armorBreak, currentColorSelected, auxParameters, auxParameters2);
					
					System.out.println("The final max damage = " + finalMaxDamageSlot1 );
					System.out.println("The final min damage = " + finalMinDamageSlot1 );

					String displayDamageSlot1 = Double.toString(finalMaxDamageSlot1);
					String displayMinDamageSlot1 = Double.toString(finalMinDamageSlot1);
					
					//Nodes killed test case 
					System.out.println("Current value in the nodes text field: " + 
					nodesKilledTextField.getValue());
					
					damage1Result.setText(displayMinDamageSlot1 + " - " + displayDamageSlot1);
				}
			} else {
				//System.out.println("Null check working!");
				damage1Result.setText("No Gun Selected for this Slot.");
			}
			System.out.println("The weapon name for slot 2: " + mainGUI.getCurrentWeaponNameSlot2());
			if (mainGUI.getCurrentWeaponNameSlot2() != null && !mainGUI.getCurrentWeaponNameSlot2().isEmpty()) {
				//System.out.println("Null check not working!");
				//Nodes killed test case 
				//Very weird bug, cannot do != for CVL and CV for some reason
				if(mainGUI.getShipType().equals("CVL") || mainGUI.getShipType().equals("CV")) {
					UpdatedCarrierCalculations plane2 = new UpdatedCarrierCalculations(mainGUI.getSkills(), mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getCurrentWeaponTypeSlot2(), 
							mainGUI.getCurrentWeaponNameSlot2(), mainGUI.getEnemy(), mainGUI.getWorld(), parseInt(textFieldP2B1), parseInt(textFieldP2B2), parseInt(textFieldP2T), currentColorSelected, auxParameters, auxParameters2); 
					Double finalMaxDamageSlot2 = plane2.getFinalTotalDamage(2, mainGUI.getSkills(), critical, mainGUI.getWorld(),  mainGUI.getDangerLvl(), 2);
					String displayMaxDamageSlot2 = Double.toString(finalMaxDamageSlot2);
					
					System.out.println("The amount of bombs dropped from plane 2" + plane2.getBomb1());
					damage2Result.setText(displayMaxDamageSlot2);
				} 	else {

					Double finalMaxDamageSlot2 = finalDamage.getFinalDamage(mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getmainGUI.getCurrentWeaponTypeSlot2()(), mainGUI.getCurrentWeaponNameSlot2(), 2
							,mainGUI.getSkills(), critical, mainGUI.getWorld(), mainGUI.getEnemy(), mainGUI.getCurrentDMGType(), manual, firstSalvo,  mainGUI.getDangerLvl(), evenOdd, 2, armorBreak, currentColorSelected, auxParameters, auxParameters2);
					
					Double finalMinDamageSlot2 = finalDamage.getFinalDamage(mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getCurrentWeaponTypeSlot2(), mainGUI.getCurrentWeaponNameSlot2(), 2
							,mainGUI.getSkills(), critical, mainGUI.getWorld(), mainGUI.getEnemy(), mainGUI.getCurrentDMGType(), manual, firstSalvo,  mainGUI.getDangerLvl(), evenOdd, 0, armorBreak, currentColorSelected, auxParameters, auxParameters2);
					
					System.out.println("The final damage Slot 2 = " + finalMaxDamageSlot2 );
					String displayDamageSlot2 = Double.toString(finalMaxDamageSlot2);
					String displayMinDamageSlot2 = Double.toString(finalMinDamageSlot2);

					damage2Result.setText(displayMinDamageSlot2 + " - " + displayDamageSlot2);
					
					}
				} else {
				//System.out.println("Null check working!");
				damage2Result.setText("No Gun Selected for this Slot.");
				} 
			
			//Since Calclculations.java will be overridden slot3 will currently just replace slot1&2 weapon damage for now 
			//easy way to just have a if statement if cvl is selected but will make	
			if (currentWeaponNameSlot3 != null && !currentWeaponNameSlot3.isEmpty()) {
				System.out.println("The weapon name for slot 3: " + currentWeaponNameSlot3);

				//Getting the amount of bombs for each plane. 
				//Carrier Calculations might want to be changed to accept an array so this part looks better

				UpdatedCarrierCalculations plane3 = new UpdatedCarrierCalculations(mainGUI.getSkills(), mainGUI.getShipType(), mainGUI.getCurrentShip().getShipName(), mainGUI.getCurrentWeaponTypeSlot3(), 
						currentWeaponNameSlot3, mainGUI.getEnemy(), mainGUI.getWorld(), parseInt(textFieldP3B1), parseInt(textFieldP3B2), parseInt(textFieldP3T) , currentColorSelected, auxParameters, auxParameters2);
				System.out.println("checking npassing color" + currentColorSelected);
				Double plane3FinalMaxDamage = plane3.getFinalTotalDamage(3, mainGUI.getSkills(), critical, mainGUI.getWorld(),  mainGUI.getDangerLvl(), 2);
				System.out.println("The final min damage fro plane3 = " + plane3FinalMaxDamage);
				String displayMaxDamageSlot3 = Double.toString(plane3FinalMaxDamage);
				damage3Result.setText(displayMaxDamageSlot3);
			} else {
				//System.out.println("Null check working!");
				damage3Result.setText("No Gun Selected for this Slot.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	protected void onSwitch() {
		performCalculations();
	}
}
