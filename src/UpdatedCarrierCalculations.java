import java.util.ArrayList;
import java.util.Arrays;

public class UpdatedCarrierCalculations {
	
	// USS Planes.
	private final ArrayList<String> USSPlanes = new ArrayList<String>(Arrays.asList("Brewster F2A Buffalo", "Brewster F2A Buffalo (Thach Squadron)", "Brewster XF2A-4 Buffalo (Prototype)", "Grumman F4F Wildcat", 
			"Grumman F6F Hellcat", "Grumman F7F Tigercat", "Grumman F8F Bearcat", "Grumman XF5F Skyrocket", "Vought F4U Corsair", "Vought F4U Corsair", "Douglas TBD Devastator", "Douglas XTB2D-1 Skypirate", "Grumman TBF Avenger", 
			"TBM Avenger (VT-18 Squadron)", "Douglas TBD Devastator (VT-8)", "Grumman TBF Avenger (VT-8)", "Curtiss SB2C Helldiver", "Curtiss XSB3C (Experimental)", "Douglas BTD-1 Destroyer", "Douglas SBD Dauntless", "Douglas SBD Dauntless (McClusky)"));
	// KMS Planes
	private final ArrayList<String> KMSPlanes = new ArrayList<String>(Arrays.asList("Arado Ar 197", "Focke-Wulf Fw 190 A-5 (Carrier-based Prototype)", "Messerschmitt Bf 109G (Carrier-based Prototype", "Messerschmitt BF-109T", "Messerschmitt Me-155A", "Heinkel He 50b", "Junkers Ju-87C", "Arado Ar 195",
			"Fieseler Fi 167", "Junkers Ju-87 D-4"));
	private final ArrayList<String> KMSFighters = new ArrayList<String>(Arrays.asList("Arado Ar 197", "Focke-Wulf Fw 190 A-5 (Carrier-based Prototype)", "Messerschmitt Bf 109G (Carrier-based Prototype", "Messerschmitt BF-109T", "Messerschmitt Me-155A"));
	
	private final ArrayList<String> IJNPlanes = new ArrayList<String>(Arrays.asList("Kawanishi N1K3-A Shiden Kai 2", "Mitsubishi A5M Type 96", "Mitsubishi A6M2 Zero", "Mitsubishi A6M3 Type 0 Model 32", "Mitsubishi A6M5 Zero", "Mitsubishi A7M Reppuu",
			"Aichi D3A Type 99", "Aichi D3A2 Type 99", "Nakajima J5N Tenrai (Dive Bomber Prototype)", "Yokosuka D4Y Suisei", "Yokosuka Suisei Model 12A", "Aichi B7A Ryuusei", "Nakajima B5N Type 97", "Nakajima B5N2 Type 97", "Nakajima B6N Tenzan",
			"Nakajima B6N2 Tenzan Model 12A", "Nakajima C6N Saiun (Model 21 Prototype)", "Aichi E16A Zuiun", "Aichi M6A Seiran", "Kawanishi N1K1 Kyoufuu", "Nakajima A6M2-N", "Yokosuka Suisei Model 21"));
	
	private final ArrayList<String> mainFleet = new ArrayList<String>(Arrays.asList("Battlecruisers", "Battleships", "Light Aircraft Carriers", "Aircraft Carriers", "Monitors", "Aviation Battleships"));
	
	private final ArrayList<String> bombers = new ArrayList<String>(Arrays.asList("Aichi D3A Type 99", "Aichi D3A2 Type 99", "Blackburn Skua", "Curtiss SB2C Helldiver", "Curtiss XSB3C (Experimental)", "Douglas BTD-1 Destroyer", "Douglas SBD Dauntless",
			"Douglas SBD Dauntless (McClusky)", "Fairey Barracuda (831 Squadron)", "Fairey Firefly", "Fairey Fulmar", "Heinkel He 50b", "Junkers Ju-87C", "Yokosuka D4Y Suisei", "Yokosuka Suisei Model 12A"));
	
	// CARRIERS THAT CAN USE CANNONS AND HAVE CANNONS SELECTED WITH BE USING THE OTHER CALUCLATION METHOD.
	
	// FOR AVIATION BATTLE SHIPS MAIN WEAPON WILL BE THE PLANE, SECOND AND THIRD WEAPON WILL BE THE SAME THING AS THE MAIN WEAPON TO AVOID ERRORS.
	
	/*
	 * Main calculation method for carriers that will add up all the damage that will done from bombs and torpedos. Multiple calls to getCalculatedDamage is needed because each bomb can do its own damage.
	 * Different from the Cannon and Torpedo calculation method due to different requirements on how air damage is dealt.
	 */
	public double getCarrierFinalDamage(ShipFile ship, Planes mainWeapon, Planes secondWeapon, Planes thirdWeapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
			AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom, int bombOneDropped, int bombTwoDropped, int torpedosDropped, boolean removeBias) {
		
		// Calculating damage that will be done depending on what bomb or torpedo is dropped. This will calculate the damage assuming each ordinance hits.
		double finalDmg = 0;
		if (mainWeapon.getBombOneDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, mainWeapon, secondWeapon, thirdWeapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "bombOne", 1) * bombOneDropped;
		}
		
		if (mainWeapon.getBombTwoDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, mainWeapon, secondWeapon, thirdWeapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "bombTwo", 2) * bombTwoDropped;
		}
		
		if (mainWeapon.getTorpDmg() != 0) {
			finalDmg += getCalculatedDamage(ship, mainWeapon, secondWeapon, thirdWeapon, enemy, skills, skillNames, slotOneAuxGear, slotTwoAuxGear, shipSlot, crit, dangerLevel, armorBreak, removeRandom, "torpedo", 3) * torpedosDropped;
		}
		
		// Special exceptions from aux gear
		if (slotOneAuxGear.getGearName().equals("Awakening Pearl") || slotTwoAuxGear.getGearName().equals("Awakening Pearl")) {
			finalDmg = finalDmg * 1.03;
		}
		if (slotOneAuxGear.getGearName().equals("Frontier Medal") || slotTwoAuxGear.getGearName().equals("Frontier Medal")) {
			finalDmg = finalDmg * 1.10;
		}
		
		System.out.println("Final Damage is: " + finalDmg);
		return finalDmg;
		
	}
	
	public double getCalculatedDamage(ShipFile ship, Planes mainWeapon, Planes secondWeapon, Planes thirdWeapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames,
			AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, int shipSlot, boolean crit, int dangerLevel, boolean armorBreak, int removeRandom, String ordinance, int armorSlot) {
		double estimatedDamage = 0;
		boolean isAviationBB = ship.getShipType().equals("Aviation Battleship");
		
		if (!mainWeapon.getPlaneName().isEmpty() && mainWeapon.getPlaneName() != null) {
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
			correctedDamageStat = getCorrectedDamage(ship, mainWeapon, secondWeapon, thirdWeapon, shipSlot, skills, skillNames, ordinance, slotOneAuxGear, slotTwoAuxGear, isAviationBB);
			
			// Weapon Type Mod (Scaling Weapon Buffs)
			weaponTypeModStat = getWeaponTypeMod(skills, skillNames, enemy);
			
			// Critical Damage Buff
			if (crit) {
				criticalDamageStat = getCriticalDamage(ship, mainWeapon, skills);
			}
			
			// Armor Modifier
			armorModStat = getArmorModifier(ship, mainWeapon, enemy, skills, ordinance);
			
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
			injRatStat = getInjureRatio(skills, skillNames);
			
			// Damage Ratio
			dmgRatStat = getDamageRatio(ship, enemy, mainWeapon, secondWeapon, thirdWeapon, shipSlot, skills, skillNames);
			
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
	private double getCorrectedDamage(ShipFile ship, Planes mainWeapon, Planes secondWeapon, Planes thirdWeapon, int shipSlot, ArrayList<Skill> skills, ArrayList<String> skillNames, String ordinance, AuxGear slotOneAuxGear, AuxGear slotTwoAuxGear, boolean isAviationBB) {
		double finalDmg = 0;
		double wepDmg = 0;
		double wepCoff = 1;
		double effSlot = 0;
		
		// Determine ordinance being calculated
		switch (ordinance) {
			case "bombOne":
				wepDmg = mainWeapon.getBombOneDmg();
				break;
			case "bombTwo":
				wepDmg = mainWeapon.getBombTwoDmg();
				break;
			case "torpedo":
				wepDmg = mainWeapon.getTorpDmg();
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
			if (skillNames.contains("Sacrament: Holy Bombardment")) {
				effSlot += 0.45;
			}
		}
		// Exceptions that can effect the efficiency of any slot
		if (skillNames.contains("Eagle Sky")) {
			for (String plane: USSPlanes) {
				if (mainWeapon.getPlaneName().contains(plane)) {
					effSlot += 0.15;
					break;
				}
			}
		}
		
		if (skillNames.contains("Hellcat's Roar") && (mainWeapon.getPlaneName().contains("Grumman F6F Hellcat") || secondWeapon.getPlaneName().contains("Grumman F6F Hellcat") || thirdWeapon.getPlaneName().contains("Grumman F6F Hellcat"))
				&& mainWeapon.getPlaneType().equals("Fighter Planes")) {
			effSlot += .30;
		}
		
		if (skillNames.contains("Ironblood Hawk")) {
			for (String plane: KMSPlanes) {
				if (mainWeapon.getPlaneName().contains(plane) || secondWeapon.getPlaneName().contains(plane) || thirdWeapon.getPlaneName().contains(plane)) {
					effSlot += 0.15;
					break;
				}
			}
		}
		
		if (skillNames.contains("Supporting Wings") && mainWeapon.getPlaneName().contains("Fairey Albacore")) {
			effSlot += 0.15;
		}
		
		if (skillNames.contains("Hex-Principle of Dominance")) {
			for (int i = 0; i < KMSFighters.size(); i++) {
				String name = KMSFighters.get(i);
				if (mainWeapon.getPlaneName().contains(name) || secondWeapon.getPlaneName().contains(name)|| thirdWeapon.getPlaneName().contains(name)) {
					effSlot += 0.10;
					break;
				}
			}
		}
		
		// Stats from the ship and gear (weapon and auxiliary gear) and skills that grant a flat amount instead of a percentage.
		double basicStatBoost = 0;
		if (isAviationBB || ship.getShipName().equals("I-13")) {
			basicStatBoost = ship.getAviation() + mainWeapon.getAviation() + slotOneAuxGear.getAviation() + slotTwoAuxGear.getAviation();
		} else {
			basicStatBoost = ship.getAviation() + mainWeapon.getAviation() + secondWeapon.getAviation() + thirdWeapon.getAviation() + slotOneAuxGear.getAviation() + slotTwoAuxGear.getAviation();
		}
		
		// Stats that would be gained from skills (Aviation Buff)
		double statsFromBuff = getDmgRatiotoStatBuffs(skills, "Buff To Aviation", 1, "");
		if (skillNames.contains("Mark of Sirius")) {
			statsFromBuff += 0.10;
		}
		if (skillNames.contains("Royal Alliance") && mainFleet.contains(ship.getShipType())) {
			statsFromBuff += 0.12;
		}
		if (skillNames.contains("Freedom Through Firepower") || (ship.getShipType().equals("Light Aircraft Carriers") || (ship.getShipType().equals("Aircraft Carriers")))) {
			statsFromBuff += 0.15;
		}
		if (skillNames.contains("Hex-Principle of Dominance")) {
			for (int i = 0; i < KMSPlanes.size(); i++) {
				String name = KMSPlanes.get(i);
				if (mainWeapon.getPlaneName().contains(name) || secondWeapon.getPlaneName().contains(name)|| thirdWeapon.getPlaneName().contains(name)) {
					statsFromBuff += 0.12;
					break;
				}
			}
		}
		if (skillNames.contains("The Great One's Shadow")) {
			for (int i = 0; i < IJNPlanes.size(); i++) {
				String name = IJNPlanes.get(i);
				if (mainWeapon.getPlaneName().contains(name) || secondWeapon.getPlaneName().contains(name)|| thirdWeapon.getPlaneName().contains(name)) {
					statsFromBuff += 0.15;
					break;
				}
			}
		}
		
		double finalStatAttacker = basicStatBoost * statsFromBuff * 0.80;
		finalDmg = wepDmg * wepCoff * effSlot * (1 + (finalStatAttacker/100));
		return finalDmg;
	}
	
	/**
	 * Method to get weapon type mod stats.
	 * @param skills
	 * @return
	 */
	private double getWeaponTypeMod(ArrayList<Skill> skills, ArrayList<String> skillNames, Enemy enemy) {
		double buffDamage = 1;
		buffDamage += getMiscStats(skills, "Injure Air", 0) + getMiscStats(skills, "Damage Air", 0);
		if (skillNames.contains("Beckoning of Ice")) {
			buffDamage += 0.05;
		}
		if (skillNames.contains("Hex-Principle of Shattering")) {
			if (enemy.getArmor().equals("L") || enemy.getArmor().equals("M")) {
				buffDamage += 0.08;
			}
		}
		
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
	 * Return the damage gained based off of the level difference
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
	private double getInjureRatio(ArrayList<Skill> skills, ArrayList<String> skillNames) {
		double injBuff = getDmgRatiotoStatBuffs(skills, "Injure Ratio", 0, "");
		if (skillNames.contains("Crane's Endeavor")) {
			injBuff += 0.60;
		}
		return injBuff;
	}
	
	private double getDamageRatio(ShipFile ship, Enemy enemy, Planes mainWeapon, Planes secondWeapon, Planes thirdWeapon, int shipSlot, ArrayList<Skill> skillList, ArrayList<String> skillNames) {
		double dmgRatio = 0;
		for (int i = 0; i < skillList.size(); i++) {
			Skill skill = skillList.get(i);
			if (skill.getDmgRatioAviation() == 1) {
				dmgRatio += skill.getDmgRatio();
			}
		}
		
		if (skillNames.contains("Auspice of the Stars")) {
			dmgRatio += 0.10;
		}
		
		if (skillNames.contains("4th Combined Fleet Flagship") && ship.getShipType().equals("Light Aircaft Carriers") || ship.getShipType().equals("Aircraft Carriers")) {
			dmgRatio += 0.20;
		}
		
		if (skillNames.contains("Unwavering Strength") && ship.getShipType().equals("Light Aircaft Carriers") || ship.getShipType().equals("Aircraft Carriers")) {
			dmgRatio += 0.20;
		}
		if (skillNames.contains("Victorious Song") && (ship.getShipType().equals("Light Aircaft Carriers") || ship.getShipType().equals("Aircraft Carriers")) && enemy.getShipType().equals("BB")) {
			dmgRatio += 0.10;
		}
		if (skillNames.contains("Predestined Launch") && mainFleet.contains(ship.getShipType())) {
			dmgRatio += 0.10;
		}
		if (skillNames.contains("Scorched Blade (Red)")) {
			dmgRatio += 0.15;
		}
		if (skillNames.contains("Venus Friends")) {
			dmgRatio += 0.15;
		}
		if (skillNames.contains("Shooting Gun-Star") || enemy.getArmor().equals("L")) {
			dmgRatio += 0.15;
		}
		if (skillNames.contains("Rain of Starlight")) {
			if ((shipSlot == 1 || bombers.contains(secondWeapon.getPlaneName()) || (shipSlot == 2 || bombers.contains(mainWeapon.getPlaneName()) || (shipSlot == 3 && bombers.contains(thirdWeapon.getPlaneName()))))) {
				dmgRatio += 0.08;
			}
		}
		if (skillNames.contains("Eternal Light of Sardegna")) {
			dmgRatio += 0.12;
		}
		if (skillNames.contains("Kazagumo's Air Raid Assistance")) {
			if (ship.getShipType().equals("Aircraft Carriers") || ship.getShipType().equals("Light Aircraft Carriers")) {
				dmgRatio += 0.15;
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
