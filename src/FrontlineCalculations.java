import java.util.ArrayList;
import java.util.Arrays;

public class FrontlineCalculations {
	
	private GUIUtility utility = new GUIUtility();
	
	final private ArrayList<String> cannonTypes = new ArrayList<String>(Arrays.asList("Destroyer Guns", "Light Cruiser Guns", "Heavy Cruiser Guns", "Large Cruiser Guns", "Battleship Guns"));
	final private ArrayList<String> IJNGuns= new ArrayList<String>(Arrays.asList("Single 100mm (Type 88)", "Single 120mm (10th Year Type)", "Single 120mm (11th Year Type)", "Single 120mm (Type 3)", "Single 127mm (Type 3 Mod B)", "Twin 100mm (Type 98)", "Twin 127mm (Type 3 Mod B)", "Twin 127mm (Type 3)"));
	final private ArrayList<String> massachusettsGunException = new ArrayList<String>(Arrays.asList("Single 127mm (5\"/38 Mk 21)", "Single 127mm (5\"/38 Mk 30)", "Single 76mm (3\"/50 caliber gun)", "Twin 127mm (5\"/38 Mk 32)", "Twin 127mm (5\"/38 Mk 38)"));
	final private ArrayList<String> vanguardFleet = new ArrayList<String>(Arrays.asList("Destroyer", "Light Cruiser", "Heavy Cruiser", "Large Cruiser"));
	final private ArrayList<String> mainFleet = new ArrayList<String>(Arrays.asList("Battlecruiser", "Battleship", "Light Aircraft Carrier", "Aircraft Carrier", "Monitor", "Aviation Battleship"));
	
	
	/**
	 * Calculates the damage done in 1 shot / hit
	 * @param ship
	 * @param mainWeapon - The weapon in the current slot being calculated
	 * @param statsWeapon - The other weapon not being used to calculate its damage output. Stats are being used for certain exceptions
	 * @param aaGun - Only needed to get the aa stats for certain exception skills.
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
	public double getFinalDamage(ShipFile ship, CommonWeapon mainWeapon, CommonWeapon statsWeapon, AAGuns aaGun, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames, AuxGear auxGearOne, AuxGear auxGearTwo, int shipSlot, boolean crit,
	String world, int ammoType, boolean manual, boolean firstSalvo, int dangerLvl, int evenOdd, int removeRandom, boolean armorBreak, String noteColor) {
		
		// Avoid index out of bounds error
		double finalDamage = 0;
		if (!mainWeapon.getWepName().isEmpty() && mainWeapon.getWepName() != null) {
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
			correctedDamageStat = getCorrectedDamage(ship, mainWeapon, statsWeapon, aaGun, auxGearOne, auxGearTwo, skillList, skillNames, shipSlot);
			
			//Scaling Weapons (Weapon Type Mod)
			weaponTypeModStat = getWeaponTypeMod(skillList, skillNames, enemy, mainWeapon, shipSlot, armorBreak);
			
			//Critical Damage
			if (crit || skillNames.contains("Wahrheit")) {
				criticalDamageStat = 1.5 + getCriticalDamage(ship, mainWeapon, skillList, skillNames, evenOdd);
			}
			
			//Armor Modifier
			armorModStat = getArmorModifier(ship, enemy, mainWeapon, skillList, skillNames, ammoType, shipSlot, noteColor);
			
			// Enhancing Damage
			if (firstSalvo) {
				enhancingDmgStat = getEnhanceDamage(mainWeapon, skillList, skillNames, shipSlot, manual);
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
			dmgRatStat = getDamageRatio(skillList, ship,  mainWeapon, statsWeapon, enemy, shipSlot, evenOdd, skillNames);
			
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
	private double getCorrectedDamage(ShipFile ship, CommonWeapon mainWeapon, CommonWeapon statsWeapon, AAGuns aaGun, AuxGear auxGearOne, AuxGear auxGearTwo, ArrayList<Skill> skills, ArrayList<String> skillNames, int shipSlot) {
		double finalDmg = 0;
		double weaponDmg = mainWeapon.getDamage();
		double wepCoff = mainWeapon.getCoefficient();
		double slotEff = 0;
		
		// Get the efficiency of the weapon slot. Available slots are 1 or 2.
		if (shipSlot == 1) {
			slotEff += ship.getEffSlot(1);
			if (skillNames.contains("Special Gunnery Training") && mainWeapon.getWepName().equals("Triple 310mm (Type 0 Prototype")) {
				slotEff += 0.12;
			}
			// Seattle Exception. Not caring for AA efficiency because AA damage is not being calculated.
			if (statsWeapon.getWeaponType().equals("Anti-Air Guns") && skillNames.contains("Dual Nock")) {
				slotEff += 0.15;
			}
			
			if (skillNames.contains("Kitakaze Style - Horizon Splitter")) {
				for (String ijnWeapon : IJNGuns) {
					if (mainWeapon.getWepName().contains(ijnWeapon)) {
						slotEff += 0.15;
						break;
					}
				}
			}
			if (skillNames.contains("Sword or Shield")) {
				slotEff  += 0.20;
			}
			
		} else {
			slotEff = ship.getEffSlot(2);
		}
		
		// Get Firepower/Torepdo depending on what weapon type is being calculated (AA Stat is only needed because of certain skills that scale off it. Flat Numbers).
		double shipWeaponAuxStat = 0;
		// Torpedos
		if (mainWeapon.getWeaponType().equals("Torpedos")) {
			shipWeaponAuxStat = ship.getTorpedo() + mainWeapon.getGenStat();
		} else {
			// Cannons
			shipWeaponAuxStat = ship.getFirepower() + mainWeapon.getGenStat();
			if (skillNames.contains("Taste My Wrath!") && cannonTypes.contains(mainWeapon.getWeaponType())) {
				shipWeaponAuxStat += 240;
			}
		}
		// Exceptions that affect both firepower and torpedo stats
		if (skillNames.contains("A Witch Who Never Admits Defeat")) {
			shipWeaponAuxStat += 40;
		}
		if (skillNames.contains("Fading Memories of Glory") && mainWeapon.getWeaponType().equals("Torpedos")) {
			shipWeaponAuxStat -= ship.getTorpedo();
		}
		
		// Get the stats from the aux gears (Flat Numbers).
		if (mainWeapon.getWeaponType().equals("Torpedos") && auxGearOne != null) {
			shipWeaponAuxStat += auxGearOne.getTorpedo();
			if (auxGearTwo != null) {
				shipWeaponAuxStat += auxGearTwo.getTorpedo();
			}
		} else if (!mainWeapon.getWeaponType().equals("Torpedos") && auxGearOne != null) {
			shipWeaponAuxStat += auxGearOne.getFirepower();
			if (auxGearTwo != null) {
				shipWeaponAuxStat += auxGearTwo.getFirepower();
			}
		}
		
		
		// Get stats from skills (Percentages)
		double skillStat = 0;
		// Check Torpedos first
		if (mainWeapon.getWeaponType().equals("Torpedos")) {
			skillStat = getDmgRatiotoStatBuffs(skills, "Buff To Torpedo", 1, "");
		} else {
			skillStat = getDmgRatiotoStatBuffs(skills, "Buff To Cannon", 1, "");
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////
		// Exceptions
		if (cannonTypes.contains(mainWeapon.getWeaponType())) {
			if (skillNames.contains("AA Firepower")) {
				double tempAA = ship.getAA() + mainWeapon.getAAStat() + statsWeapon.getAAStat() + aaGun.getAAStat() + auxGearOne.getAA() + auxGearTwo.getAA();
				skillStat += tempAA * 0.30;
			}
			
			if (skillNames.contains("Iron Wing Annihilation")) {
				double tempAA = ship.getAA() + mainWeapon.getAAStat() + statsWeapon.getAAStat() + aaGun.getAAStat() + auxGearOne.getAA() + auxGearTwo.getAA();
				skillStat += tempAA * 0.15;
			}
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
	public double getWeaponTypeMod(ArrayList<Skill> skills, ArrayList<String> skillNames, Enemy enemy, CommonWeapon weapon, int shipSlot, boolean armorBreak) {
		double buffDamage = 1;
		if (weapon.getWeaponType().equals("Torpedos")) {
			buffDamage += getMiscStats(skills, "Injure Torpedo", 0) + getMiscStats(skills, "Damage Torpedo", 0); 
		} else {
			buffDamage += getMiscStats(skills, "Injure Cannon", 0) + getMiscStats(skills, "Damage Cannon", 0);
		}
		
		// EXCEPTIONS
		if (skillNames.contains("2,700 Pounds of Justice")) {
			// For this purpose guarantee an armor break
			if (shipSlot == 2 && enemy.getArmor().equals("H") && massachusettsGunException.contains(weapon.getWepName())) {
				buffDamage += 0.08;
			}
		} else if (enemy.getArmor().equals("H") && skillNames.contains("APsolute Ammunition")) {
			buffDamage += 0.08;
		} else if (armorBreak && !weapon.getWeaponType().equals("Torpedos")) {
			buffDamage += 0.08;
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
			if (skillNames.contains("Sonata of Chaos") && evenOdd == 0) {
				critBuff += 0.30;
			}
		}
		return critBuff;
	}
	
	/**
	 * Calculates how much damage will be done to an enemy based off the armor they have.
	 * X/X/X is Light Armor Mod, Medium Armor Mod, Heavy Armor Mod
	 * @param ship
	 * @param enemy
	 * @param weapon
	 * @param skills
	 * @param skillNames
	 * @param ammoType
	 * @param noteColor
	 * @return
	 */
	public double getArmorModifier(ShipFile ship, Enemy enemy, CommonWeapon weapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int ammoType, int shipSlot, String noteColor) {
		double armorMod = 0;
		boolean change = false;
		
		// Check if any of the skills that will change the damage modifier is in the given skill list.
		ArrayList<String> changeToggle = new ArrayList<String>(Arrays.asList("2,700 Pounds of Justice"));
		for (int i = 0; i < changeToggle.size(); i++) {
			if (skillNames.contains(changeToggle.get(i))) {
				change = true;
				break;
			}
		}
		
		
		String enemyArmor = enemy.getArmor();
		if (enemyArmor.equals("L")) {
			armorMod = weapon.getLightDamage();
		} else if (enemyArmor.equals("M")) {
			armorMod = weapon.getMediumDamage();
		} else {
			armorMod = weapon.getHeavyDamage();
		}
		
		if (!change) {
			return armorMod;
		} else { // Exceptions. If there are multiple skills that will change the armor modifier, it will go by alphabetical order.
			if (skillNames.contains("2,700 Pounds of Justice")) {
				// For this purpose guarantee an armor break
				if (shipSlot == 2 && massachusettsGunException.contains(weapon.getWepName())) {
					if (enemy.getArmor().equals("L")) {
						armorMod = .65;
					} else if (enemy.getArmor().equals("M")) {
						armorMod = 1.35;
					} else {
						armorMod = 1.15;
					}
				}
			} else if (shipSlot == 1 && skillNames.contains("APsolute Ammunition")) {
				if (enemyArmor.equals("L")) {
					armorMod = 0.85;
				} else if (enemyArmor.equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 0.85;
				}
			}else if (shipSlot == 1 && skillNames.contains("Expert Loader")) {
				if (ammoType == 1) {
					if (enemyArmor.equals("L")) {
						armorMod = 1.35;
					} else if (enemyArmor.equals("M")) {
						armorMod = .95;
					} else { // Heavy Armor
						armorMod = .70;
					}
				} else { // Ammo is AP
					if (enemyArmor.equals("L")) {
						armorMod = .75;
					} else if (enemyArmor.equals("M")) {
						armorMod = 1.10;
					} else { // Heavy Armor
						armorMod = .75;
					}
				}
			} else if (weapon.getWeaponType().equals("Torpedos") && skillNames.contains("Impartial Destruction")) {
				armorMod = 1.15;
			} else if (shipSlot == 1 && skillNames.contains("Kitakaze Style - Horizon Splitter")) {
				armorMod = 1.15;
			} else if (shipSlot == 1 && skillNames.contains("Tricolo Order")) {
				armorMod = 1;
			}
		}
		return armorMod;
	}
	
	/**
	 * Calculates the enhancing damage will be done
	 * @param skills
	 * @param manual
	 * @return
	 */
	public double getEnhanceDamage(CommonWeapon weapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int shipSlot, boolean manual) {
		double enhance = 0;
		if (manual) {
			enhance = 1.20 + getMiscStats(skills, "Initial Enhance", 0) + getMiscStats(skills, "Manual Enhance", 0);
		} else {
			enhance = 1.00 + getMiscStats(skills, "Initial Enhance", 0);
		}
		
		// SKILL EXCEPTIONS
		if (skillNames.contains("2,700 Pounds of Justice")) {
			if (shipSlot == 2 && massachusettsGunException.contains(weapon.getWepName())) {
				enhance += .15;
			}
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
	public double getDamageRatio(ArrayList<Skill> skills, ShipFile ship, CommonWeapon weapon, CommonWeapon secondaryWeapon, Enemy enemy, int shipSlot, int evenOdd, ArrayList<String> skillNames) {
		double ratio = 0;
		if (cannonTypes.contains(weapon.getWeaponType())) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Cannon");
		} else if (weapon.getWeaponType().equals("Torpedos")) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Torpedo");
		}
		
		// Skill exceptions area.
		
		// Makes sure weapon is a gun.
		if (!weapon.getWeaponType().equals("Torpedos")) {
			if (skillNames.contains("Sonata of Chaos") && evenOdd == 1) {
				ratio += 0.2;
			}
			if (skillNames.contains("Failen Angel")) {
				ratio += 0.01;
			}
			if (shipSlot == 1 && skillNames.contains("Blazing Peaks")) {
				int calibur = Integer.valueOf(weapon.getWepName().replaceAll("[^0-9]",""));
				if (calibur > 280) {
					ratio += 0.25;
				}
			}
			if (shipSlot == 1 && skillNames.contains("Semi-Armor Piercing High-Explosive")) {
				if (weapon.getAmmoType().contains("HE") || weapon.getAmmoType().contains("SAP")) {
					ratio += 0.12;
				} else if (weapon.getAmmoType().contains("AP")) {
					ratio += 0.15;
				}
			}
			if (skillNames.contains("Fuoco di Copertura!") && vanguardFleet.contains(ship.getShipType())) {
				ratio += 0.15;
			}
			
		}
		
		// Skill exceptions that will boost overall damage and other special requirements.
		if (skillNames.contains("Revolyutsiya") && vanguardFleet.contains(ship.getShipType())) {
			ratio += 0.35;
		}
		if (skillNames.contains("Code: Hikari") && mainFleet.contains(ship.getShipType())) {
			ratio += 0.10;
		}
		if (skillNames.contains("Will of the Iron Blood") && ship.getShipName().equals("Tirpitz")) {
			ratio += 0.40;
		}
		if (skillNames.contains("Wahrheit")) {
			if (shipSlot == 1 && secondaryWeapon.getWeaponType().equals("Light Cruiser Guns") || shipSlot == 2 && weapon.getWeaponType().equals("Light Cruiser Guns")) {
				ratio -= 0.35;
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
	/*
	 * RANDOM HELPER METHODS FOR LOOPING TO CLEAR UP SOME LOOPS IN OTHER METHODS.
	 * ANY SPECIFIC DETAILS WITH NICHES NEEDED TO BE ADDED SHOULD BE ADDED IN THE METHODS ABOVE.
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
