import java.util.ArrayList;
import java.util.Arrays;

public class FrontlineCalculations {
	
	private GUIUtility utility = new GUIUtility();
	
	private ArrayList<String> canons = new ArrayList<String>(Arrays.asList("Destroyer Guns", "Light Cruiser Guns", "Heavy Cruiser Guns", "Large Cruiser Guns", "Battleship Guns"));
	
	/**
	 * Calculates the total damage that will be done in one shot / hit.
	 * @param ship
	 * @param weapon
	 * @param enemy
	 * @param skillList
	 * @param skillNames
	 * @param auxGearOne
	 * @param auxGearTwo
	 * @param shipSlot
	 * @param crit
	 * @param world
	 * @param ammoType
	 * @param manual
	 * @param firstSalvo
	 * @param dangerLvl
	 * @param evenOdd
	 * @param removeRandom
	 * @param armorBreak
	 * @param noteColor
	 * @return
	 */
	public double getFinalDamage(ShipFile ship, CommonWeapon weapon, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames, AuxGear auxGearOne, AuxGear auxGearTwo, int shipSlot, boolean crit,
	String world, int ammoType, boolean manual, boolean firstSalvo, int dangerLvl, int evenOdd, int removeRandom, boolean armorBreak, String noteColor) {
		
		// Avoid index out of bounds error
		double finalDamage = 0;
		if (!weapon.getWepName().isEmpty() && weapon.getWepName() != null) {
			double correctedDamageStat = 0;
			double weaponTypeModStat = 0;
			double criticalDamageStat = 1; // Default at 1
			double armorModStat = 0;
			double airDmgRedStat = 1; // 1 for now. Number will change when carriers and plane damage is added in.
			double enhancingDmgStat = 1; // Default at 1
			double comboStat = 1; // Only ship with combo damage atm is U-47 so if she and her skill is selected, add 0.4.
			double lvlDiffStat = 0;
			double dmgRedStat = 1; // Care about dealing dmg to enemy, not dmg self is taking.
			double injRatStat = 0;
			double dmgRatStat = 0;
			double dmgNatStat = 0;
			double dmgTypeStat = 0;
			double ammoBuffStat = 0;
			
			//Corrected Damage
			correctedDamageStat = getCorrectedDamage(ship, weapon, auxGearOne, auxGearTwo, skillList, skillNames, shipSlot);
			
			//Scaling Weapons (Weapon Type Mod)
			weaponTypeModStat = getWeaponTypeMod(skillList, weapon, armorBreak);
			
			//Critical Damage
			if (crit || skillNames.contains("Wahrheit")) {
				criticalDamageStat = 1.5 + getCriticalDamage(ship, weapon, skillList, skillNames, evenOdd);
			}
			
			//Armor Modifier
			armorModStat = getArmorModifier(ship, enemy, weapon, skillList, skillNames, ammoType, noteColor);
			
			// Enhancing Damage
			if (firstSalvo) {
				enhancingDmgStat = getEnhanceDamage(skillList, manual);
			}
			
			//Combo Damage
			if (ship.getShipName().equals("U-47") && skillNames.contains("The Bull of Scapa Flow")) {
				comboStat += 0.40;
			}
			
			//Level Difference
			lvlDiffStat = getLevelDifference(enemy, dangerLvl);
			
			//Injure Ratio
			injRatStat = getInjureRatio(skillList);
			
			//Damage Ratio
			dmgRatStat = getDamageRatio(skillList, weapon, enemy, evenOdd, skillNames);
			
			//Damage to Nation
			dmgNatStat = factionDamage(enemy, skillList);
			
			//Damage to Type
			dmgTypeStat = typeDamage(enemy, skillList, skillNames);
			
			//Ammo Type Buff
			if ((ammoType == 0) || (ammoType == 1)) {
				ammoBuffStat = ammoBuffs(skillList, ammoType);
			}
			
			double intermediateDmg = (correctedDamageStat + removeRandom) * weaponTypeModStat * criticalDamageStat * armorModStat
					* (1 + injRatStat) * (1 + dmgRatStat) * lvlDiffStat * (1 + dmgNatStat) * (1 + dmgTypeStat) * (1 + ammoBuffStat - 0) * airDmgRedStat * (1 + comboStat);
			double temp1 = Math.max(1, Math.floor(intermediateDmg));
			double temp2 = Math.floor(temp1 * enhancingDmgStat);
			finalDamage = Math.floor(temp2 * dmgRedStat);
			
		}
		
		return finalDamage;
	}
	
	/**
	 * Calculates the corrected damage.
	 * @param ship
	 * @param weapon
	 * @param auxGearOne
	 * @param auxGearTwo
	 * @param skills
	 * @param skillNames
	 * @param shipSlot
	 * @return
	 */
	private double getCorrectedDamage(ShipFile ship, CommonWeapon weapon, AuxGear auxGearOne, AuxGear auxGearTwo, ArrayList<Skill> skills, ArrayList<String> skillNames, int shipSlot) {
		double finalDmg = 0;
		double weaponDmg = weapon.getDamage();
		double wepCoff = weapon.getCoefficient();
		double slotEff = 0;
		
		// Get the efficiency of the weapon slot. Avaiable slots are 1 or 2.
		if (shipSlot == 1) {
			slotEff = ship.getEffSlot(1);
		} else {
			slotEff = ship.getEffSlot(2);
		}
		
		// Get Firepower/Torepdo depending on what weapon type is being calculated (AA Stat is only needed because of certain skills that scale off it).
		double shipWeaponAuxStat = 0;
		// Torpedos
		if (weapon.getWeaponType().equals("Torpedos")) {
			shipWeaponAuxStat = ship.getTorpedo() + weapon.getGenStat();
		} else {
			// Cannons
			shipWeaponAuxStat = ship.getFirepower() + weapon.getGenStat();
		}
		
		// Get the stats from the aux gears.
		if (weapon.getWeaponType().equals("Torpedos") && auxGearOne != null) {
			shipWeaponAuxStat += auxGearOne.getTorpedo();
			if (auxGearTwo != null) {
				shipWeaponAuxStat += auxGearTwo.getTorpedo();
			}
		} else if (!weapon.getWeaponType().equals("Torpedos") && auxGearOne != null) {
			shipWeaponAuxStat += auxGearOne.getFirepower();
			if (auxGearTwo != null) {
				shipWeaponAuxStat += auxGearTwo.getFirepower();
			}
		}
		
		// Get stats from skills
		double skillStat = 0;
		// Check Torpedos first
		if (weapon.getWeaponType().equals("Torpedos")) {
			skillStat = getDmgRatiotoStatBuffs(skills, "Buff To Torpedo", 1, "");
		} else {
			skillStat = getDmgRatiotoStatBuffs(skills, "Buff To Cannon", 1, "");
		}
		
		// Scaling Damage. Monarach and Izumo have a higher scaling damage.
		double scalingDamageStat = 1;
		if (ship.getShipName().equals("Monarch") || ship.getShipName().equals("Izumo")) {
			scalingDamageStat = 1.2;
		}
		
		double finalStat = shipWeaponAuxStat * skillStat * scalingDamageStat;
		finalDmg = weaponDmg * wepCoff * slotEff * (1 + (finalStat / 100));
		
		return finalDmg;	
	}
	
	/**
	 * Determines how much bonus damage an enemy will take.
	 * @param skills
	 * @param weapon
	 * @param armorBreak
	 * @return
	 */
	public double getWeaponTypeMod(ArrayList<Skill> skills, CommonWeapon weapon, boolean armorBreak) {
		double buffDamage = 1;
		if (weapon.getWeaponType().equals("Torpedos")) {
			buffDamage += getMiscStats(skills, "Injure Torpedo", 0) + getMiscStats(skills, "Damage Torpedo", 0); 
		} else {
			buffDamage += getMiscStats(skills, "Injure Cannon", 0) + getMiscStats(skills, "Damage Cannon", 0);
		}
		return buffDamage;
	}
	
	/**
	 * Get damage bonus from critical damage
	 * @param ship
	 * @param weapon
	 * @param skills
	 * @param skillNames
	 * @param evenOdd
	 * @return
	 */
	public double getCriticalDamage(ShipFile ship, CommonWeapon weapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int evenOdd) {
		double critBuff = 0;
		if (weapon.getWeaponType().equals("Torpedos")) {
			critBuff = getMiscStats(skills, "Crit Torpedo", 0);
		} else {
			critBuff = getMiscStats(skills, "Crit Cannon", 0);
			if (weapon.getWepName().equals("Quadruple 380mm (Mle 1935)") && skillNames.contains("Final Shot")) {
				critBuff += 0.50;
			}
		}
		return critBuff;
	}
	
	/**
	 * Calculates how much damage will be done to an enemy based off the armor they have.
	 * @param ship
	 * @param enemy
	 * @param weapon
	 * @param skills
	 * @param skillNames
	 * @param ammoType
	 * @param noteColor
	 * @return
	 */
	public double getArmorModifier(ShipFile ship, Enemy enemy, CommonWeapon weapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int ammoType, String noteColor) {
		double armorMod = 0;
		String enemyArmor = enemy.getArmor();
		if (enemyArmor.equals("L")) {
			armorMod = weapon.getLightDamage();
		} else if (enemyArmor.equals("M")) {
			armorMod = weapon.getMediumDamage();
		} else {
			armorMod = weapon.getHeavyDamage();
		}
		return armorMod;
	}
	
	/**
	 * Calculates the enhancing damage will be done
	 * @param skills
	 * @param manual
	 * @return
	 */
	public double getEnhanceDamage(ArrayList<Skill> skills, boolean manual) {
		double enhance = 0;
		if (manual) {
			enhance = 1.20 + getMiscStats(skills, "Initial Enhance", 0) + getMiscStats(skills, "Manual Enhance", 0);
		} else {
			enhance = 1.00 + getMiscStats(skills, "Initial Enhance", 0);
		}
		
		return enhance;
	}
	
	/**
	 * Returns damage bonus based off of the level difference between ships and the danger level of the chosen world
	 * @param enemy
	 * @param dangerLvl
	 * @return
	 */
	public double getLevelDifference(Enemy enemy, int dangerLvl) {
		return 1 + Math.min(25, Math.max(-25, 120 - enemy.getEnemyLvl() + dangerLvl)) * 0.02;
	}
	
	/**
	 * Calculates damage from Injure ratio bonus
	 * @param skills
	 * @return
	 */
	public double getInjureRatio(ArrayList<Skill> skills) {
		double ratio = getDmgRatiotoStatBuffs(skills, "Injure Ratio", 0, "");
		return ratio;
	}
	
	/**
	 * Calculates bonus damage from the damage ratio bonuses
	 * @param skills
	 * @param weapon
	 * @param enemy
	 * @param evenOdd
	 * @param skillNames
	 * @return
	 */
	public double getDamageRatio(ArrayList<Skill> skills, CommonWeapon weapon, Enemy enemy, int evenOdd, ArrayList<String> skillNames) {
		double ratio = 0;
		if (canons.contains(weapon.getWeaponType())) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Cannon");
		} else if (weapon.getWeaponType().equals("Torpedos")) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Torpedo");
		}
		
		//Exceptions for clarity here
		// Makes sure weapon is a gun.
		if (!weapon.getWeaponType().equals("Torpedos")) {
			if (skillNames.contains("Sonata of Chaos") && evenOdd == 1) {
				ratio += 0.2;
			}
			
			if (skillNames.contains("Failen Angel")) {
				ratio += 0.01;
			}
		}
		return ratio;
	}
	
	/**
	 * Calculates bonus damage against factions.
	 * @param enemy
	 * @param skills
	 * @return
	 */
	public double factionDamage(Enemy enemy, ArrayList<Skill> skills) {
		double dmg = 0;
		String nation = enemy.getNation();
		dmg = getDmgToFactions(skills, nation, 0);
		return dmg;
	}
	
	/**
	 * Calculates damage based of enemy ship type.
	 * @param enemy
	 * @param skills
	 * @param skillNames
	 * @return
	 */
	public double typeDamage(Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames) {
		double dmg = 0;
		String type = enemy.getShipType();
		dmg = getDmgToTypes(skills, type, 0);
		
		// Used for the skills that target special ships and others. (Torpedo boat, Suicide Ship, Gold Ship, Destroyers)
		ArrayList<String> outsiders = new ArrayList<>(Arrays.asList("TB", "SS", "GS", "DD"));
		
		//Exceptions
		if (skillNames.contains("Disturbance Strategy")) {
			if (outsiders.contains(type)) {
				dmg += 0.25;
			}
		}
		
		if (skillNames.contains("Silver Phantom")) {
			if (outsiders.contains(type)) {
				dmg += 0.25;
			}
		}
		// Ignore Monitors for now
		return dmg;
	}
	
	/**
	 * Calculates bonus damage from ammo type buffs.
	 * @param skills
	 * @param ammoType
	 * @return
	 */
	public double ammoBuffs(ArrayList<Skill> skills, int ammoType) {
		double btaDmg = 0;
		if (ammoType == 0) {
			btaDmg = getMiscStats(skills, "HEBUFF", 0);
		} else {
			btaDmg = getMiscStats(skills, "APBUFF", 0);
		}
		return btaDmg;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//RANDOM HELPER METHODS TO NOT CLOG UP STUFF.
	// These methods are meant to be loops unlike the others above
	/*
	 * Method that loops to get certain stats from file from index 0 to index 11.
	 * Other stats are left out to be done in its own method.
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
			} else if (buff.equals("Damage Cannon")) {
				startValue += skill.getDmgByCannon();
			} else if (buff.equals("Damage Torpedo")) {
				startValue += skill.getDmgByTorpedo();
			} else if (buff.equals("Crit Cannon")) {
				startValue += skill.getCritByCannon();
			} else if(buff.equals("Crit Torpedo")) {
				startValue += skill.getCritByTorpedo();
			}
	 	}
		return startValue;
	}
}
