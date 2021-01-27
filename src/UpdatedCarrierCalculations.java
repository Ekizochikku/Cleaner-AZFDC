import java.util.ArrayList;

public class UpdatedCarrierCalculations {
	
	GUIUtility gt = new GUIUtility();
	
	/*
	 * Main calculation method for carriers that will add up all the damage that will done from bombs and torpedos. Multiple calls to getCalculatedDamage is needed because each bomb can do its own damage.
	 * Different from the Cannon and Torpedo calculation method due to different requirements on how air damage is dealt.
	 */
	public double getCarrierFinalDamage(ShipFile ship, Planes weapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
			AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom, int bombOneDropped, int bombTwoDropped, int torpedosDropped) {
		
		// Calculating damage that will be done depending on what bomb or torpedo is dropped. This will calculate the damage assuming each ordinance hits.
		double finalDmg = 0;
		if (weapon.getBombOneDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, weapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "bombOne", 1) * bombOneDropped;
		}
		
		if (weapon.getBombTwoDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, weapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "bombTwo", 2) * bombTwoDropped;
		}
		
		if (weapon.getTorpDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, weapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "torpedo", 3) * torpedosDropped;
		}
		
		return finalDmg;
		
	}
	
	public double getCalculatedDamage(ShipFile ship, Planes weapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
			AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom, String ordinance, int armorSlot) {
		double estimatedDamage = 0;
		
		if (!weapon.getPlaneName().isEmpty() && weapon.getPlaneName() != null) {
			double correctedDamageStat = 0;
			double weaponTypeModStat = 0;
			double criticalDamageStat = 1; // Default at 1, 1.5 if crit is triggered.
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
			correctedDamageStat = getCorrectedDamage(ship, weapon, shipSlot, skills, skillNames, ordinance, slotOneAuxGear, slotTwoAuxGear);
			
			// Weapon Type Mod (Scaling Weapon Buffs)
			weaponTypeModStat = getWeaponTypeMod(skills);
			
			// Critical Damage Buff
			if (crit) {
				criticalDamageStat = getCriticalDamage(ship, weapon, skills);
			}
			
			// Armor Modifier
			armorModStat = getArmorModifier(ship, weapon, enemy, skills, ordinance);
			
			//Enhancing Damage
			// Not needed since this only applies to ships with cannons.
			
			// Combo Damage
			// Not requiring weapon to be a torpedo cause why not. More damage for funsies.
			if (skillNames.contains("The Bull of Scapa Flow")) {
				comboStat += .4;
			}
			
			// Level Difference
			lvlDiffStat = getLevelDifference(enemy, dangerLevel);
			
			// Injure Ratio
			injRatStat = getDmgRatiotoStatBuffs(skills, "Injure Ratio", 0, "");
			
			// Damage Ratio
			dmgRatStat = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Aviation");
			
			// Damage to Nation
			dmgNatStat = getDamageToNation(skills, enemy);
			
			// Damage to Ship Type
			dmgTypeStat = getDamageToTypes(ship, enemy, skills);
			
			// Ammo Type Buff
			// Not needed since stats scale off aviation stat
			
			double intermediateDamage = (correctedDamageStat + removeRandom) * weaponTypeModStat * criticalDamageStat * armorModStat * 
					(1 + injRatStat) * (1 + dmgRatStat) * lvlDiffStat * (1 + dmgNatStat) * (1 + dmgTypeStat) * (1 + ammoBuffStat - 0) * airDmgRedStat * (1 + comboStat);
			double temp1 = Math.max(1, Math.floor(intermediateDamage));
			double temp2 = Math.floor(temp1 * enhancingDmgStat);
			estimatedDamage = Math.floor(temp2 * dmgRedStat);
		}
		return estimatedDamage;
	}
	
	/**
	 * Method to determine calculate the corrected damage.
	 * @param ship
	 * @param weapon
	 * @param shipSlot
	 * @param skills
	 * @param skillNames
	 * @param ordinance
	 * @param slotOneAuxGear
	 * @param slotTwoAuxGear
	 * @return
	 */
	private double getCorrectedDamage(ShipFile ship, Planes weapon, int shipSlot, ArrayList<Skill> skills, ArrayList<String> skillNames, String ordinance, AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear) {
		double finalDmg = 0;
		double wepDmg = 0;
		double wepCoff = 1;
		double effSlot = 0;
		
		// Determine ordinance being calculated
		switch (ordinance) {
			case "bombOne":
				wepDmg = weapon.getBombOneDmg();
				break;
			case "bombTwo":
				wepDmg = weapon.getBombTwoDmg();
				break;
			case "torpedo":
				wepDmg = weapon.getTorpDmg();
				break;
			default:
				break;
		}
		
		// Efficiency Slot
		if (shipSlot == 1) {
			effSlot = ship.getEffSlot(1);
		} else if (shipSlot == 2) {
			effSlot = ship.getEffSlot(2);
		} else {
			effSlot = ship.getEffSlot(3);
		}
		
		// Stats from the ship and gear (weapon and auxiliary gear) and skills that grant a flat amount instead of a perentage.
		double basicStatBoost = ship.getAviation() + weapon.getAviation() + slotOneAuxGear.getAviation() + slotTwoAuxGear.getAviation();
		
		// Stats that would be gained from skills
		double statsFromBuff = getDmgRatiotoStatBuffs(skills, "Buff To Aviation", 1, "");
		
		double finalStatAttacker = basicStatBoost * statsFromBuff * 0.80;
		finalDmg = wepDmg * wepCoff * effSlot * (1 + (finalStatAttacker/100));
		return finalDmg;
	}
	
	/**
	 * Method to get weapon type mod stats.
	 * @param skills
	 * @return
	 */
	private double getWeaponTypeMod(ArrayList<Skill> skills) {
		double buffDamage = 1;
		buffDamage += getMiscStats(skills, "Injure Air", 0) + getMiscStats(skills, "Damage Air", 0);
		
		return buffDamage;
	}
	
	/**
	 * 1.5 initial crit will be initiated at the top.
	 * @param ship
	 * @param weapon
	 * @param skills
	 * @return
	 */
	private double getCriticalDamage(ShipFile ship, Planes weapon, ArrayList<Skill> skills) {
		return getMiscStats(skills, "Crit Air", 0);
	}
	
	/**
	 * Method to return the armor modifier.
	 * Should never return 0 since it needs to calculate the amount of damage to be done to an enemy.
	 * @param ship
	 * @param weapon
	 * @param enemy
	 * @param skills
	 * @param ordinance
	 * @return
	 */
	private double getArmorModifier(ShipFile ship, Planes weapon, Enemy enemy, ArrayList<Skill> skills, String ordinance) {
		double armorMod = 0;
		String enemyArmor = enemy.getArmor();
		// Bomb One
		if (ordinance.equals("bombOne")) {
			if (enemyArmor.equals("L")) {
				armorMod = weapon.getOneToL();
			} else if (enemyArmor.equals("M")) {
				armorMod = weapon.getOneToM();
			} else {
				armorMod = weapon.getOneToH();
			}
		// Bomb Two
		} else if (ordinance.equals("bombTwo")) {
			if (enemyArmor.equals("L")) {
				armorMod = weapon.getTwoToL();
			} else if (enemyArmor.equals("M")) {
				armorMod = weapon.getTwoToM();
			} else {
				armorMod = weapon.getTwoToH();
			}
		// Torpedos
		} else {
			if (enemyArmor.equals("L")) {
				armorMod = weapon.getTorpToL();
			} else if (enemyArmor.equals("M")) {
				armorMod = weapon.getTorpToM();
			} else {
				armorMod = weapon.getTorpToH();
			}
		}
		
		return armorMod;
		
	}
	
	/**
	 * Return the damage gained bsaed off of the level difference
	 * @param enemy
	 * @param dangerLvl
	 * @return
	 */
	private double getLevelDifference(Enemy enemy, int dangerLvl) {
		return 1 + Math.min(25, Math.max(-25, 120 - enemy.getEnemyLvl() + dangerLvl)) * 0.02;
	}
	
	/**
	 * Return injure ratio buffs
	 * @param skills
	 * @return
	 */
	private double getInjureRatio(ArrayList<Skill> skills) {
		return getDmgRatiotoStatBuffs(skills, "Injure Ratio", 0, "");
	}
	
	private double getDamageRatio(ShipFile ship, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames) {
		double dmgRatio = 0;
		for (int i = 0; i < skillList.size(); i++) {
			Skill skill = skillList.get(i);
			if (skill.getDmgRatioAviation() == 1) {
				dmgRatio += skill.getDmgRatio();
			}
		}
		
		return dmgRatio;
	}
	
	private double getDamageToNation(ArrayList<Skill> skills, Enemy enemy) {
		double dmgToNation = 0;
		String nation = enemy.getNation();
		if (nation.equals("NULL")) {
			return 1;
		} else {
			switch (nation) {
			case "HMS":
				dmgToNation = getDmgToFactions(skills, "HMS", 0);
				break;
			case "USS":
				dmgToNation = getDmgToFactions(skills, "USS", 0);
				break;
			case "IJN":
				dmgToNation = getDmgToFactions(skills, "IJN", 0);
				break;
			case "KMS":
				dmgToNation = getDmgToFactions(skills, "KMS", 0);
				break;
			case "ROC":
				dmgToNation = getDmgToFactions(skills, "ROC", 0);
				break;
			case "FFNF":
				dmgToNation = getDmgToFactions(skills, "FFNF", 0);
				break;
			case "MNF":
				dmgToNation = getDmgToFactions(skills, "MNF", 0);
				break;
			case "SIREN":
				dmgToNation = getDmgToFactions(skills, "SIREN", 0);
				break;
			default:
				break;
			}
		}
		return dmgToNation;
	}
	
	private double getDamageToTypes(ShipFile ship, Enemy enemy, ArrayList<Skill> skills) {
		double dmgToType = 0;
		String enemyType = enemy.getShipType();
		switch (enemyType) {
			case "DD":
				dmgToType = getDmgToTypes(skills, "DD", 0);
				break;
			case "CL":
				dmgToType = getDmgToTypes(skills, "CL", 0);
				break;
			case "CA":
				dmgToType = getDmgToTypes(skills, "CA", 0);
				break;
			case "LC":
				dmgToType = getDmgToTypes(skills, "LC", 0);
				break;
			case "BC":
				dmgToType = getDmgToTypes(skills, "BC", 0);
				break;
			case "BB":
				dmgToType = getDmgToTypes(skills, "BB", 0);
				break;
			case "BBV":
				dmgToType = getDmgToTypes(skills, "BBV", 0);
				break;
			case "CVL":
				dmgToType = getDmgToTypes(skills, "CVL", 0);
				break;
			case "CV":
				dmgToType = getDmgToTypes(skills, "CV", 0);
				break;
			case "SUB":
				dmgToType = getDmgToTypes(skills, "SUB", 0);
				break;
			default:
				break;
		}
		return dmgToType;
		
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	// RANDOM HELPER METHODS TO NOT CLOG UP STUFF.
	// METHODS ARE COPY OVER FROM THE OTHER CALCULATION METHOD SO SOME THINGS CAN BE DELETED/CHANGED IN THE METHODS THEMSELVES.
	/*
	 * Method that loops to get certain stats from file from index 0 to index 11.
	 * Other stats are left out to be done in its own method.
	 * Section refers to if you want to check the damage or injure ration or you want to get the stats such as firepower, torpedo, aa, or aviation.
	 * DRT (Damage Ratio True) is if damage ratio should be applied to the specific weapon type.
	 */
	public double getDmgRatiotoStatBuffs(ArrayList<Skill> skills, String section, int startValue, String DRT) {
		for (int i = 0; i < skills.size(); i++) {
			if (section.equals("Injure Ratio")) {
				startValue += skills.get(i).getInjureRatio();
			} else if (section.equals("Damage Ratio")) {
				if (DRT.equals("Cannon")) {
					if (skills.get(i).getDmgRatioCannon() == 1) {
						startValue += skills.get(i).getDmgRatio();
					}
				} else if (DRT.equals("Torpedo")) {
					if (skills.get(i).getDmgRatioTorpedo() == 1) {
						startValue += skills.get(i).getDmgRatio();
					}
				} else if (DRT.equals("Aviation")) {
					if (skills.get(i).getDmgRatioAviation() == 1) {
						startValue += skills.get(i).getDmgRatio();
					}
				}
			} else if (section.equals("Buff To Firepower")) {
				startValue += skills.get(i).getBuffToFirepower();
			} else if (section.equals("Buff To Torpedo")) {
				startValue += skills.get(i).getBuffToTorpedo();
			} else if (section.equals("Buff To AA")) {
				startValue += skills.get(i).getBuffToAA();
			} else if (section.equals("Buff To Aviation")) {
				startValue += skills.get(i).getBuffToAviation();
			}
		}
		return startValue;
	}
	
	/*
	 * Method that gets the stats of bonus damage to certain nations
	 */
	public double getDmgToFactions(ArrayList<Skill> skills, String faction, int startValue) {
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			if (faction.equals("HMS")) {
				startValue += skill.getDmgToHMS();
			} else if (faction.equals("USS")) {
				startValue += skill.getDmgToUSS();
			} else if (faction.equals("IJN")) {
				startValue += skill.getDmgToIJN();
			} else if (faction.equals("KMS")) {
				startValue += skill.getDmgToKMS();
			} else if (faction.equals("ROC") ) {
				startValue += skill.getDmgToROC();
			} else if (faction.equals("FFNF")) {
				startValue += skill.getDmgToFFNF();
			} else if (faction.equals("MNF")) {
				startValue += skill.getDmgToMNF();
			} else if (faction.equals("SIREN")) {
				startValue += skill.getDmgToSIREN();
			} else {
				return startValue;
			}
		}
		return startValue;
	}
	
	/*
	 * Method that gets stats of bonus damage to certain ship types.
	 */
	public double getDmgToTypes(ArrayList<Skill> skills, String type, int startValue) {
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			if (type.equals("DD")) {
				startValue += skill.getDmgToDD();
			} else if (type.equals("CL")) {
				startValue += skill.getDmgToCL();
			} else if (type.equals("CA")) {
				startValue += skill.getDmgToCA();
			} else if (type.equals("LC")) {
				startValue += skill.getDmgToLC();
			} else if (type.equals("BC")) {
				startValue += skill.getDmgToBC();
			} else if (type.equals("BB")) {
				startValue += skill.getDmgToBB();
			} else if (type.equals("AB")) {
				startValue += skill.getDmgToAB();
			} else if (type.equals("CVL")) {
				startValue += skill.getDmgToCVL();
			} else if (type.equals("CV")) {
				startValue += skill.getDmgToCV();
			} else if (type.equals("SUB")) {
				startValue += skill.getDmgToSUB();
			}
		}
		return startValue;
	}
	
	/*
	 * Method that gets misc type buffs.
	 */
	public double getMiscStats(ArrayList<Skill> skills, String buff, int startValue) {
		for (int i = 0; i < skills.size(); i++) {
			Skill skill = skills.get(i);
			if (buff.equals("HEBUFF")) {
				startValue += skill.getHEBuff();
			} else if (buff.equals("APBUFF")) {
				startValue += skill.getAPBUff();
			} else if (buff.equals("Initial Enhance")) {
				startValue += skill.getInitialEnhance();
			} else if (buff.equals("Manual Enhance")) {
				startValue += skill.getManualEnhance();
			} else if (buff.equals("Injure Cannon")) {
				startValue += skill.getInjuryByCannon();
			} else if (buff.equals("Injure Torpedo")) {
				startValue += skill.getInjuryByTorpedo();
			} else if (buff.equals("Injure Air")) {
				startValue += skill.getInjuryByAir();
			} else if (buff.equals("Damage Cannon")) {
				startValue += skill.getDmgByCannon();
			} else if (buff.equals("Damage Torpedo")) {
				startValue += skill.getDmgByTorpedo();
			} else if (buff.equals("Damage Air")) {
				startValue += skill.getDmgByAir();
			} else if (buff.equals("Crit Cannon")) {
				startValue += skill.getCritByCannon();
			} else if (buff.equals("Crit Torpedo")) {
				startValue += skill.getCritByTorpedo();
			} else if (buff.equals("Crit Air")) {
				startValue += skill.getCritByAir();
			}
	 	}
		return startValue;
	}
}
