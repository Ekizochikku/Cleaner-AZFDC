import java.util.ArrayList;

public class UpdatedCarrierCalculations {
	
	GUIUtility gt = new GUIUtility();
	
	/*
	 * Main calculation method for carriers that will add up all the damage that will done from bombs and torpedos. Multiple calls to getCalculatedDamage is needed because each bomb can do its own damage.
	 * Different from the Cannon and Torpedo calculation method due to different requirements on how air damage is dealt.
	 */
	public double getCarrierFinalDamage(ShipFile ship, Planes weapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
			AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom) {
		
		// Calculating damage that will be done depending on what bomb or torpedo is dropped. This will calculate the damage assuming each ordinance hits.
		double finalDmg = 0;
		if (weapon.getBombOneDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, weapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "bombOne", 1);
		}
		
		if (weapon.getBombTwoDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, weapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "bombTwo", 2);
		}
		
		if (weapon.getTorpDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, weapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "torpedo", 3);
		}
		
		return finalDmg;
		
	}
	
	public double getCalculatedDamage(ShipFile ship, Planes weapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
			AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom, String ordinance, int armorSlot) {
		double estimatedDamage = 0;
		
		if (!weapon.getPlaneName().isEmpty() && weapon.getPlaneName() != null) {
			double correctedDamageStat = 0;
			double weaponTypeModStat = 0;
			double criticalDamageStat = 1; // Default at 1
			double armorModStat = 0;
			//Can't divide using integers must make everything double!
			double airDmgRedStat = 150.0 / (150.0 + enemy.getAA()); // 1 for now. Number will change when carriers and plane damage is added in.
			double enhancingDmgStat = 1; // Default at 1
			double comboStat = 1; // Only ship with combo damage atm is U-47 so if she and her skill is selected, add 0.4.
			double lvlDiffStat = 0;
			double dmgRedStat = 1; // Care about dealing dmg to enemy, not dmg self is taking.
			double injRatStat = 0;
			double dmgRatStat = 0;
			double dmgNatStat = 0;
			double dmgTypeStat = 0;
			double ammoBuffStat = 1;
			
			// Corrected Damage
			correctedDamageStat = getCorrectedDamage(ship, weapon, shipSlot, ordinance);
		}
		
		
		return estimatedDamage;
	}

	private double getCorrectedDamage(ShipFile ship, Planes weapon, int shipSlot, String ordinance) {
		double finalDmg = 0;
		
		
		return finalDmg;
	}
}
