import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
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
		UpdatedCarrierCalculations UCC= new UpdatedCarrierCalculations();
		Planes plane;
		List<Integer> planeBombs = mainGUI.getBombsDropped();
		//Random and shipSlot unknown. Need Clarification
		int random = 0;
		int shipSlot = 0;
		ArrayList<String> skillNames = new ArrayList();
		getSkillNames(skillNames);
		//Checking if all the parameters are correct
		/*System.out.println("Checking all the parameters:  " + "\n Current Ship type: " + mainGUI.getShipType() +  "\n Ship Name: " + currentShipName +  
				"\n Weapon Type Slot 1: " + currentWeaponType +  "\n Weapon Name Slot 1: " + currentWeaponName +  "\n Current Skills: " +
				mainGUI.getSkills() +  "\n is mainGUI.getCrit(): " + mainGUI.getCrit() +  "\n World number: " + mainGUI.getWorld() + "\n Enemy Name: " + mainGUI.getEnemy()+   "\n damage type int (0 HE, 1 AP) : " + 
				mainGUI.getCurrentDMGType() +  "\n is mainGUI.getManual()" + mainGUI.getManual() +  "\n is first salvo: " + mainGUI.getFirstSalvo() +  "\n current max danger Level: " +  mainGUI.getDangerLvl() + "\n current even odd: " + mainGUI.getEvenOdd());*/
		
		/*hi System.out.println("Checking parameters SLot 2:  " +
				"\n Weapon Type Slot 2: " + mainGUI.getCurrentWeaponTypeSlot2() +  "\n Weapon Name Slot 2: " + mainGUI.getCurrentWeaponNameSlot2());*/
		try {
			//Will add more if statements to check each parameter later to avoid null pointer exceptions especially when slot 1 has a weapon but 2 doesn't
			//Ship slot hard coded in, no idea what that is yet.
			
			//changed the order for null first before is empty
//getCarrierFinalDamage(ShipFile ship, Planes mainWeapon, Planes secondWeapon, Planes thirdWeapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
//AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom, int bombOneDropped, int bombTwoDropped, int torpedosDropped)
			if (mainGUI.getCurrentWeaponNameSlot1() != null && !mainGUI.getCurrentWeaponNameSlot1().isEmpty()) {
				System.out.println("Ship type is: " + mainGUI.getShipType());
				if(mainGUI.getShipType().equals("Light Aircraft Carriers") || mainGUI.getShipType().equals("Aircraft Carriers")) {
					plane = new Planes(mainGUI.getCurrentWeaponTypeSlot1(), mainGUI.getCurrentWeaponNameSlot1());
					double carrierCalc1 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
							shipSlot, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), random, planeBombs.get(0), planeBombs.get(3), planeBombs.get(6));
					String maxDamageSlot1 = Double.toString(carrierCalc1);
					damage1Result.setText(maxDamageSlot1);
	
				} else {
//public double getFinalDamage(ShipFile ship, CommonWeapon mainWeapon, CommonWeapon secondWeapon, AAGuns aaGun, AAGuns seattleGun, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames, 
//AuxGear gearOne, AuxGear gearTwo, int shipSlot, boolean crit, String world, int ammoType, boolean manual, boolean firstSalvo, int dangerLvl, int evenOdd, int removeRandom, boolean armorBreak, String noteColor)
					CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot1(), mainGUI.getCurrentWeaponTypeSlot1());
					AAGuns aaGun = new AAGuns(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
					Double damageSlot1 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, aaGun, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), shipSlot, 
							mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), random, mainGUI.getArmorBreak(), mainGUI.getColor());
					String displayDamageSlot1 = Double.toString(damageSlot1);
					
					damage1Result.setText(displayDamageSlot1);
				}
			} else {
				//System.out.println("Null check working!");
				damage1Result.setText("No Gun Selected for this Slot.");
			}
			System.out.println("The weapon name for slot 2: " + mainGUI.getCurrentWeaponNameSlot2());
			if (mainGUI.getCurrentWeaponNameSlot2() != null && !mainGUI.getCurrentWeaponNameSlot2().isEmpty()) {
				//System.out.println("Null check not working!");

				if(mainGUI.getShipType().equals("Light Aircraft Carriers") || mainGUI.getShipType().equals("Aircraft Carriers")) {
					plane = new Planes(mainGUI.getCurrentWeaponTypeSlot2(), mainGUI.getCurrentWeaponNameSlot2());
					double carrierCalc2 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
							shipSlot, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), random, planeBombs.get(1), planeBombs.get(4), planeBombs.get(7)); 
					
					String displayMaxDamageSlot2 = Double.toString(carrierCalc2);
					
//					System.out.println("The amount of bombs dropped from plane 2" + plane2.getBomb1());
					damage2Result.setText(displayMaxDamageSlot2);
				} 	else {
					
					CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot2(), mainGUI.getCurrentWeaponTypeSlot2());
					AAGuns aaGun = new AAGuns(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
					Double damageSlot2 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, aaGun, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), shipSlot, 
							mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), random, mainGUI.getArmorBreak(), mainGUI.getColor());
					String displayDamageSlot2 = Double.toString(damageSlot2);
					
					damage2Result.setText(displayDamageSlot2);
					
					}
				} else {
				//System.out.println("Null check working!");
				damage2Result.setText("No Gun Selected for this Slot.");
				} 
			
			if (mainGUI.getCurrentWeaponNameSlot3() != null && !mainGUI.getCurrentWeaponNameSlot3().isEmpty()) {
				if(mainGUI.getShipType().equals("Light Aircraft Carriers") || mainGUI.getShipType().equals("Aircraft Carriers")) {
					plane = new Planes(mainGUI.getCurrentWeaponTypeSlot3(), mainGUI.getCurrentWeaponNameSlot3());
					double carrierCalc3 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
							shipSlot, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), random, planeBombs.get(2), planeBombs.get(5), planeBombs.get(8));
				
					String displayMaxDamageSlot3 = Double.toString(carrierCalc3);
//					System.out.println("The amount of bombs dropped from plane 2" + plane2.getBomb1());
					damage3Result.setText(displayMaxDamageSlot3);
				} 	else {

					CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
					AAGuns aaGun = new AAGuns(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
					Double damageSlot3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, aaGun, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), shipSlot, 
							mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), random, mainGUI.getArmorBreak(), mainGUI.getColor());
					String displayDamageSlot3 = Double.toString(damageSlot3);
					
					damage2Result.setText(displayDamageSlot3);
					
					}
				} else {
				//System.out.println("Null check working!");
				damage3Result.setText("No Gun Selected for this Slot.");
				} 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getSkillNames(List<String> skillNames) {
		for(Skill s: mainGUI.getSkills()) {
			skillNames.add(s.getSkillName());
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
		System.out.println(mainGUI.getShipType());
		performCalculations();
	}
}
