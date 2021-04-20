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
		damage1Result.setFont(new Font("Yu Gothic", Font.BOLD, 15));
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
		damage2Result.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		damage2Result.setEditable(false);
		damage2Result.setBounds(407, 250, 300, 39);
		add(damage2Result);
		
		damage3Result = new JTextPane();
		damage3Result.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		damage3Result.setEditable(false);
		damage3Result.setBounds(407, 419, 300, 39);
		add(damage3Result);

	}
	
	private void performCalculations() {
		FrontlineCalculations finalDamage = new FrontlineCalculations();
		UpdatedCarrierCalculations UCC= new UpdatedCarrierCalculations();
		Planes plane;
		int[] planeBombs = mainGUI.getBombsDropped();
		//Random and shipSlot unknown. Need Clarification
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
			if(mainGUI.getAux1() == null || mainGUI.getAux2() == null) {
				return;
			}
			if (mainGUI.getCurrentWeaponNameSlot1() != null && !mainGUI.getCurrentWeaponNameSlot1().isEmpty() && !mainGUI.getCurrentWeaponTypeSlot1().equals("Anti-Air Guns")) {
				System.out.println("Ship type is: " + mainGUI.getShipType());
				if(mainGUI.getShipType().equals("Light Aircraft Carriers") || mainGUI.getShipType().equals("Aircraft Carriers")) {
					plane = new Planes(mainGUI.getCurrentWeaponNameSlot1(), mainGUI.getCurrentWeaponTypeSlot1());
					double carrierCalc1 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
							1, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), 2, planeBombs[0], planeBombs[3], planeBombs[6]);
					String maxDamageSlot1 = Double.toString(carrierCalc1);
					damage1Result.setText(maxDamageSlot1);
	
				} else {
//public double getFinalDamage(ShipFile ship, CommonWeapon mainWeapon, CommonWeapon secondWeapon, AAGuns aaGun, AAGuns seattleGun, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames, 
//AuxGear gearOne, AuxGear gearTwo, int shipSlot, boolean crit, String world, int ammoType, boolean manual, boolean firstSalvo, int dangerLvl, int evenOdd, int removeRandom, boolean armorBreak, String noteColor)
					CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot1(), mainGUI.getCurrentWeaponTypeSlot1());
					AAGuns aaGun = new AAGuns(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
					Double damageSlot1Max = 0.0;
					Double damageSlot1Min = 0.0;
					if(mainGUI.getCurrentShip().getShipName().equals("Seattle (Retrofit)")) {
						damageSlot1Max = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 1, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 2, mainGUI.getArmorBreak(), mainGUI.getColor());
						damageSlot1Min = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 1, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 0, mainGUI.getArmorBreak(), mainGUI.getColor());
					} else {
						CommonWeapon weapon2 = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot2(), mainGUI.getCurrentWeaponTypeSlot2());
						damageSlot1Max = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon2, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 1, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 2, mainGUI.getArmorBreak(), mainGUI.getColor());
						damageSlot1Min = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon2, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 1, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 0, mainGUI.getArmorBreak(), mainGUI.getColor());
					}

					String displayDamageSlotMax1 = Double.toString(damageSlot1Max);
					String displayDamageSlotMin1 = Double.toString(damageSlot1Min);
					damage1Result.setText(displayDamageSlotMin1 + " - " + displayDamageSlotMax1);
				}
			} else {
				//System.out.println("Null check working!");
				damage1Result.setText("No Gun Selected for this Slot.");
			}
			System.out.println("The weapon name for slot 2: " + mainGUI.getCurrentWeaponNameSlot2());
			if (mainGUI.getCurrentWeaponNameSlot2() != null && !mainGUI.getCurrentWeaponNameSlot2().isEmpty()) {
				//System.out.println("Null check not working!");
				if(mainGUI.getCurrentWeaponTypeSlot2().equals("Anti-Air Guns")) {
					damage2Result.setText("0");
				}
				
				else if(mainGUI.getShipType().equals("Light Aircraft Carriers") || mainGUI.getShipType().equals("Aircraft Carriers")) {
					plane = new Planes(mainGUI.getCurrentWeaponNameSlot2(), mainGUI.getCurrentWeaponTypeSlot2());
					double carrierCalc2 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
							2, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), 2, planeBombs[1], planeBombs[4], planeBombs[7]); 
					
					String displayMaxDamageSlot2 = Double.toString(carrierCalc2);
					
//					System.out.println("The amount of bombs dropped from plane 2" + plane2.getBomb1());
					damage2Result.setText(displayMaxDamageSlot2);
				} 	else {
					CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot1(), mainGUI.getCurrentWeaponTypeSlot1());
					CommonWeapon weapon2 = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot2(), mainGUI.getCurrentWeaponTypeSlot2());
					AAGuns aaGun = new AAGuns(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
					Double damageSlotMax2 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon2, weapon, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 2, 
							mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 2, mainGUI.getArmorBreak(), mainGUI.getColor());
					Double damageSlotMin2 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon2, weapon, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 2, 
							mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 0, mainGUI.getArmorBreak(), mainGUI.getColor());
					String displayDamageSlotMax2 = Double.toString(damageSlotMax2);
					String displayDamageSlotMin2 = Double.toString(damageSlotMin2);
					damage2Result.setText(displayDamageSlotMin2 + " - " + displayDamageSlotMax2);
					
					}
				} else {
				//System.out.println("Null check working!");
				damage2Result.setText("No Gun Selected for this Slot.");
				} 
			
			if (mainGUI.getCurrentWeaponNameSlot3() != null && !mainGUI.getCurrentWeaponNameSlot3().isEmpty()) {
				if(mainGUI.getCurrentWeaponTypeSlot3().equals("Anti-Air Guns")) {
					damage3Result.setText("0");
				}
				else if(mainGUI.getShipType().equals("Light Aircraft Carriers") || mainGUI.getShipType().equals("Aircraft Carriers")) {
					String shipName = mainGUI.getCurrentShip().getShipName();
					if(shipName.equals("Bearn") || shipName.equals("Eagle") || shipName.equals("Zeppy") && mainGUI.getCurrentWeaponTypeSlot3().equals("Light Cruiser Guns")) {
						CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
						//Can't have null AAGun
						Double damageSlotMax3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, null, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 3, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 2, mainGUI.getArmorBreak(), mainGUI.getColor());
						Double damageSlotMin3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, null, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 3, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 0, mainGUI.getArmorBreak(), mainGUI.getColor());
						String displayDamageSlotMax3 = Double.toString(damageSlotMax3);
						String displayDamageSlotMin3 = Double.toString(damageSlotMin3);
						damage3Result.setText(displayDamageSlotMin3 + " - " + displayDamageSlotMax3);
					}
					else {
						plane = new Planes(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
						double carrierCalc3 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
								3, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), 2, planeBombs[2], planeBombs[5], planeBombs[8]);
					
						String displayMaxDamageSlot3 = Double.toString(carrierCalc3);
//						System.out.println("The amount of bombs dropped from plane 2" + plane2.getBomb1());
						damage3Result.setText(displayMaxDamageSlot3);
					}

				} 	else if (mainGUI.getShipType().equals("Submarines")) {
						if(mainGUI.getCurrentShip().getShipName().equals("I-13")) {
							//Carrier Calc
							System.out.println("Calculate I-13 Planes slot 3");
							plane = new Planes(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
							double carrierCalc3 = UCC.getCarrierFinalDamage(mainGUI.getCurrentShip(), plane, plane, plane, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(),
									3, mainGUI.getCrit(), mainGUI.getDangerLvl(), mainGUI.getArmorBreak(), 2, planeBombs[2], planeBombs[5], planeBombs[8]);
						
							String displayMaxDamageSlot3 = Double.toString(carrierCalc3);
//							System.out.println("The amount of bombs dropped from plane 2" + plane2.getBomb1());
							damage3Result.setText(displayMaxDamageSlot3);
						} else {
							CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
							Double damageSlotMax3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, null, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 3, 
									mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 2, mainGUI.getArmorBreak(), mainGUI.getColor());
							Double damageSlotMin3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon, null, null, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 3, 
									mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 0, mainGUI.getArmorBreak(), mainGUI.getColor());
							String displayDamageSlotMax3 = Double.toString(damageSlotMax3);
							String displayDamageSlotMin3 = Double.toString(damageSlotMin3);
							damage3Result.setText(displayDamageSlotMin3 + " - " + displayDamageSlotMax3);
						}
					} else {
						CommonWeapon weapon = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot1(), mainGUI.getCurrentWeaponTypeSlot1());
						CommonWeapon weapon2 = new CommonWeapon(mainGUI.getCurrentWeaponNameSlot2(), mainGUI.getCurrentWeaponTypeSlot2());
						AAGuns aaGun = new AAGuns(mainGUI.getCurrentWeaponNameSlot3(), mainGUI.getCurrentWeaponTypeSlot3());
						Double damageSlotMax3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon2, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 3, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 2, mainGUI.getArmorBreak(), mainGUI.getColor());
						Double damageSlotMin3 = finalDamage.getFinalDamage(mainGUI.getCurrentShip(), weapon, weapon2, aaGun, aaGun, mainGUI.getEnemy(), mainGUI.getSkills(), skillNames, mainGUI.getAux1(), mainGUI.getAux2(), 3, 
								mainGUI.getCrit(), mainGUI.getWorld(), mainGUI.getHeAp(), mainGUI.getManual(), mainGUI.getFirstSalvo(), mainGUI.getDangerLvl(), mainGUI.getEvenOdd(), 0, mainGUI.getArmorBreak(), mainGUI.getColor());
						String displayDamageSlotMax3 = Double.toString(damageSlotMax3);
						String displayDamageSlotMin3 = Double.toString(damageSlotMin3);
						damage3Result.setText(displayDamageSlotMin3 + " - " + displayDamageSlotMax3);
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
