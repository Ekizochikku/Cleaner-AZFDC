import java.util.ArrayList;
import java.util.Arrays;

public class FrontlineCalculations {
	final private ArrayList<String> cannonTypes = new ArrayList<String>(Arrays.asList("Destroyer Guns", "Light Cruiser Guns", "Heavy Cruiser Guns", "Large Cruiser Guns", "Battleship Guns"));
	final private ArrayList<String> specialShips = new ArrayList<String>(Arrays.asList("Bearn", "Eagle", "Zeppy"));
	final private ArrayList<String> IJNGuns= new ArrayList<String>(Arrays.asList("Single 100mm (Type 88)", "Single 120mm (10th Year Type)", "Single 120mm (11th Year Type)", "Single 120mm (Type 3)", "Single 127mm (Type 3 Mod B)", "Twin 100mm (Type 98)", "Twin 127mm (Type 3 Mod B)", "Twin 127mm (Type 3)"));
	final private ArrayList<String> KMSGuns = new ArrayList<String>(Arrays.asList("Single 127mm (SK C/34)", "Twin 127mm (KM40)", "Twin 128mm (SK C/41)", "Twin 128mm/45 SK C/41", "Single 150mm (SK C/28)", "Single 150mm (TbtsK C/36)", "Triple 150mm (SK C/25)", "Twin 150mm (SK C/28)", "Twin 150mm (TbtsK C/36)", "Triple 203mm (SK C/34 Prototype)", "Triple 283mm (SK C/28)", "Twin 203mm (SK C/34)", "Triple 305mm (SK C/39 Prototype)", "Twin 380mm (SK C/34)", "Twin 406mm (SK C/34 Prototype)"));
	final private ArrayList<String> SNGuns = new ArrayList<String>(Arrays.asList("Single 130mm (B13 Pattern 1936)", "Twin 130mm (B-2LM)", "Twin 152mm (Pattern 1892)", "Triple 152mm (MK-5)", "Triple 305mm (Pattern 1907)", "Triple 406mm (MK-1)"));
	final private ArrayList<String> massachusettsGunException = new ArrayList<String>(Arrays.asList("Single 127mm (5\"/38 Mk 21)", "Single 127mm (5\"/38 Mk 30)", "Single 76mm (3\"/50 caliber gun)", "Twin 127mm (5\"/38 Mk 32)", "Twin 127mm (5\"/38 Mk 38)"));
	final private ArrayList<String> vanguardFleet = new ArrayList<String>(Arrays.asList("Destroyers", "Light Cruisers", "Heavy Cruisers", "Large Cruisers"));
	final private ArrayList<String> mainFleet = new ArrayList<String>(Arrays.asList("Battlecruisers", "Battleships", "Light Aircraft Carriers", "Aircraft Carriers", "Monitors", "Aviation Battleships"));
	
	/**
	 * Calculate the total final Damage a ship will do. The exceptions will he handled in its own class. (Maybe).
	 * For the methods that have specialShipFound passed in, those ships will only have one weapon being used since places won't be giving firepower stats and are a different object.
	 * Pass in the weapon being calculated into the second weapon to avoid errors. (This second weapon for SPECIAL SHIPS ONLY will have no effect on the calculation since it will be a duplicate.
	 * noteColor 0 - Blue, 1 - Purple, 2 Red
	 * @param ship
	 * @param mainWeapon
	 * @param secondWeapon
	 * @param thirdWeapon
	 * @param aaGun
	 * @param enemy
	 * @param skillList
	 * @param skillNames
	 * @param gearOne
	 * @param gearTwo
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
	public double getFinalDamage(ShipFile ship, CommonWeapon mainWeapon, CommonWeapon secondWeapon, AAGuns aaGun, AAGuns seattleGun, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames, AuxGear gearOne, AuxGear gearTwo, 
			int shipSlot, boolean crit, String world, int ammoType, boolean manual, boolean firstSalvo, int dangerLvl, int evenOdd, int removeRandom, boolean armorBreak, int noteColor) {
		boolean specialShipFound = specialShips.contains(ship.getShipName());

		double finalDamage = 0;
		// Main weapon and second weapon should not be null.
		if (mainWeapon != null) {
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
			
			// Corrected Damage
			correctedDamageStat = getCorrectedDamage(ship, mainWeapon, secondWeapon, aaGun, seattleGun, gearOne, gearTwo, skillList, skillNames, shipSlot, specialShipFound);
			
			// Scaling Weapons (Weapon Type Mod)
			if (specialShipFound) {
				weaponTypeModStat = getWeaponTypeMod(ship, skillList, skillNames,enemy, mainWeapon, mainWeapon, shipSlot, armorBreak, specialShipFound);
			} else {
				weaponTypeModStat = getWeaponTypeMod(ship, skillList, skillNames,enemy, mainWeapon, secondWeapon, shipSlot, armorBreak, specialShipFound);
			}
			
			
			// Critical Damage
			if (crit || skillNames.contains("Wahrheit") || (skillNames.contains("The Iris's vindication") && manual) || (skillNames.contains("Sniper") && ship.getShipType().equals("Battleships") || ship.getShipType().equals("Battlecruisers"))){
				criticalDamageStat = 1.5 + (getCriticalDamage(ship, mainWeapon, skillList, skillNames, evenOdd, shipSlot, gearOne, gearTwo));
			}
			
			// Armor Modifier
			if (specialShipFound) {
				armorModStat = getArmorModifier(ship, enemy, mainWeapon, mainWeapon, skillList, skillNames, ammoType, shipSlot, noteColor, specialShipFound);
			} else {
				armorModStat = getArmorModifier(ship, enemy, mainWeapon, secondWeapon, skillList, skillNames, ammoType, shipSlot, noteColor, specialShipFound);
			}
			
			
			// Enhancing Damage
			if (specialShipFound) {
				enhancingDmgStat = getEnhanceDamage(mainWeapon, mainWeapon, skillList, skillNames, shipSlot, manual, specialShipFound);
			} else {
				enhancingDmgStat = getEnhanceDamage(mainWeapon, secondWeapon, skillList, skillNames, shipSlot, manual, specialShipFound);
			}
			
			
			// Combo Damage
			if (skillNames.contains("Bull of Scapa Flow")) {
				comboStat += 0.40;
			}
			
			// Level Difference
			lvlDiffStat = getLevelDifference(enemy, dangerLvl);
			
			//Injure Ratio
			injRatStat = getInjureRatio(skillList);
			
			//Damage Ratio
			if (specialShipFound) {
				dmgRatStat = getDamageRatio(skillList, ship,  mainWeapon, mainWeapon, enemy, shipSlot, evenOdd, skillNames, specialShipFound);
			} else {
				dmgRatStat = getDamageRatio(skillList, ship,  mainWeapon, secondWeapon, enemy, shipSlot, evenOdd, skillNames, specialShipFound);
			}
			
			
			//Damage to Nation
			dmgNatStat = factionDamage(enemy, skillList);
			
			//Damage to Type
			dmgTypeStat = typeDamage(mainWeapon, enemy, skillList, skillNames, shipSlot);
			
			//Ammo Type Buff
			if ((ammoType == 0) || (ammoType == 1)) {
				ammoBuffStat = ammoBuffs(skillList, ammoType);
			}
			
			double intermediateDmg = (correctedDamageStat + removeRandom) * weaponTypeModStat * criticalDamageStat * armorModStat
					* (1 + injRatStat) * (1 + dmgRatStat) * lvlDiffStat * (1 + dmgNatStat) * (1 + dmgTypeStat) * (1 + ammoBuffStat - 0) * airDmgRedStat * (1 + comboStat);
			double temp1 = Math.max(1, Math.floor(intermediateDmg));
			double temp2 = Math.floor(temp1 * enhancingDmgStat);
			finalDamage = Math.floor(temp2 * dmgRedStat);
			if (gearOne.getGearName().equals("Awakening Pearl") || gearTwo.getGearName().equals("Awakening Pearl")) {
				finalDamage = finalDamage * 1.03;
			}
			
			if (gearOne.getGearName().equals("Frontier Medal") || gearTwo.getGearName().equals("Medal") && (ship.getShipType().equals("Battleships")) || (ship.getShipType().equals("Battlecruisers"))) {
				double lostDamage = finalDamage * 0.10;
				finalDamage -= lostDamage;
			}
		}
		return finalDamage;
	}
	
	/**
	 * METHOD THAT CALCULATES THE CORRECTED DAMAGE.
	 * @param ship
	 * @param mainWeapon
	 * @param secondWeapon
	 * @param aaGun
	 * @param seattleGun
	 * @param gearOne
	 * @param gearTwo
	 * @param skillList
	 * @param skillNames
	 * @param shipSlot
	 * @return
	 */
	public double getCorrectedDamage(ShipFile ship, CommonWeapon mainWeapon, CommonWeapon secondWeapon, AAGuns aaGun, AAGuns seattleGun, AuxGear gearOne, AuxGear gearTwo, ArrayList<Skill> skillList, ArrayList<String> skillNames, int shipSlot, boolean specialShipFound) {
		double finalDamage = 0;
		double weaponDamage = mainWeapon.getDamage();
		if (mainWeapon.getWeaponType().equals("Torpedos") && gearOne.getGearName().contains("533mm Magnetic Torpedo")) {
			weaponDamage = weaponDamage * 1.05;
		}
		if (mainWeapon.getWeaponType().equals("Torpedos") && gearOne.getGearName().contains("533mm Magnetic Torpedo")) {
			weaponDamage = weaponDamage * 1.05;
		}
		double weaponCoefficient = mainWeapon.getCoefficient();
		double slotEfficiency = 0;
		if (shipSlot == 1) {
			slotEfficiency = ship.getEffSlot(1);
			if (skillNames.contains("Special Gunnery Training") && mainWeapon.getWepName().equals("Triple 310mm (Type 0 Prototype")) {
				slotEfficiency += 0.12;
			}
			// Seattle Exception. Not caring for AA efficiency because AA damage is not being calculated.
			if (seattleGun != null && skillNames.contains("Dual Nock")) {
				System.out.println("EXECUTING SEATTLE EXCEPTION!!!!!!!!!");
				slotEfficiency += 0.15;
			}
			
			if (skillNames.contains("Kitakaze Style - Horizon Splitter")) {
				for (String ijnWeapon : IJNGuns) {
					if (mainWeapon.getWepName().contains(ijnWeapon)) {
						slotEfficiency += 0.15;
						break;
					}
				}
			}
			if (skillNames.contains("I'm Not Afraid Anymore!")) {
				boolean nonIjnGun = true;
				for (String ijnWeapon : IJNGuns) {
					if (mainWeapon.getWepName().contains(ijnWeapon)) {
						nonIjnGun = false;
						break;
					}
				}
				if (nonIjnGun) {
					slotEfficiency += 0.20;
				}
			}
			if (skillNames.contains("Sword or Shield")) {
				slotEfficiency  += 0.20;
			}
			if (skillNames.contains("Long Live the Revolution!") && mainWeapon.getWepName().contains("Triple 305mm (Pattern 1907)")) {
				slotEfficiency += 0.80;
			}
			if (skillNames.contains("Reno Barrage") && (mainWeapon.getWeaponType().equals("Destroyer Guns") || secondWeapon.getWeaponType().equals("Destroyer Guns"))) {
				slotEfficiency += 0.10;
			}
			if (skillNames.contains("Dual Nock") || shipSlot == 1 && secondWeapon.getWeaponType().equals("Anti-Air Guns")) {
				slotEfficiency += 0.15;
			}
		} else if (shipSlot == 2) {
			slotEfficiency = ship.getEffSlot(2);
		} else {
			slotEfficiency = ship.getEffSlot(3);
			if (skillNames.contains("Sacrament: Holy Bombardment") || skillNames.contains("Royal Arts: Knight's Arsenal")) {
				if (cannonTypes.contains(mainWeapon.getWeaponType())) {
					slotEfficiency += 0.45;
				}
			}
		}
		
		// Get firepower / torpedo stat depending on the weapon being calculated.
		double shipWeaponStat = 0;
		if (mainWeapon.getWeaponType().equals("Torpedos")) {
			shipWeaponStat = ship.getTorpedo() + mainWeapon.getTorpedo() + secondWeapon.getTorpedo() + aaGun.getFirepower() + gearOne.getTorpedo() + gearTwo.getTorpedo();
		// Special case for Seattle where she can equip an AA gun to her second slot, so the stats would be gathered from that.
		} else if (seattleGun != null) {
			shipWeaponStat = ship.getFirepower() + mainWeapon.getFirepower() + seattleGun.getFirepower()  + aaGun.getFirepower() + gearOne.getFirepower() + gearTwo.getFirepower();
		// Special case where certain ships can use a cannon in their third slot and submarines.
		} else if (specialShips.contains(ship.getShipName()) || ship.getShipType().equals("Submarines")) {
			shipWeaponStat = ship.getFirepower() + mainWeapon.getFirepower() + gearOne.getFirepower() + gearTwo.getFirepower();
		} else {
			shipWeaponStat = ship.getFirepower() + mainWeapon.getFirepower() + secondWeapon.getFirepower()  + aaGun.getFirepower() + gearOne.getFirepower() + gearTwo.getFirepower();
		}
		
		if (skillNames.contains("Taste My Wrath!") && cannonTypes.contains(mainWeapon.getWeaponType())) {
			shipWeaponStat += 240;
		}
		
		// Exceptions that affect both firepower and torpedo stats
		if (skillNames.contains("A Witch Who Never Admits Defeat")) {
			shipWeaponStat += 40;
		}
		if (skillNames.contains("Fading Memories of Glory") && mainWeapon.getWeaponType().equals("Torpedos")) {
			shipWeaponStat -= ship.getTorpedo();
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Get stats from skills (Percentages)
		double statsFromSkills = 0;
		// Check Torpedos first
		if (mainWeapon.getWeaponType().equals("Torpedos")) {
			statsFromSkills = getDmgRatiotoStatBuffs(skillList, "Buff To Torpedo", 1, "");
			if (skillNames.contains("Stalwart Advance") && vanguardFleet.contains(ship.getShipType())) {
				statsFromSkills += 0.10;
			}
			if (skillNames.contains("8th Destroyer Division") && ship.getShipType().equals("Destroyers")) {
				statsFromSkills += 0.08;
			}
			if (skillNames.contains("I Love My Sisters!") || ship.getShipType().equals("Destroyers")) {
				statsFromSkills += 0.15;
			}
			if (skillNames.contains("Eternal Light of Sardegna") || vanguardFleet.contains(ship.getShipType())) {
				statsFromSkills += 0.15;
			}
		} else {
			statsFromSkills = getDmgRatiotoStatBuffs(skillList, "Buff To Cannon", 1, "");
		}
		
		// Exceptions for cannons. (THIS WILL NEED TO CHECK IF THE SPECIAL SHIPS IS TRIGGERED).
		if (cannonTypes.contains(mainWeapon.getWeaponType())) {
			if (skillNames.contains("AA Firepower") && !ship.getShipType().equals("Submarines") && !specialShipFound) {
				double tempAA = ship.getAA() + mainWeapon.getAAStat() + secondWeapon.getAAStat() + aaGun.getAAStat() + gearOne.getAA() + gearTwo.getAA();
				statsFromSkills += tempAA * 0.30;
			// This section is for the special ships that won't have an AA gun.
			} else {
				double tempAA = ship.getAA() + mainWeapon.getAAStat() + gearOne.getAA() + gearTwo.getAA();
				statsFromSkills += tempAA * 0.30;
			}
			
			if (skillNames.contains("Iron Wing Annihilation") && !ship.getShipType().equals("Submarines") && !specialShipFound) {
				double tempAA = ship.getAA() + mainWeapon.getAAStat() + secondWeapon.getAAStat() + aaGun.getAAStat() + gearOne.getAA() + gearTwo.getAA();
				statsFromSkills += tempAA * 0.15;
			} else {
				double tempAA = ship.getAA() + mainWeapon.getAAStat() + gearOne.getAA() + gearTwo.getAA();
				statsFromSkills += tempAA * 0.30;
			}
			
			
			// Firepower stat since the weapon being calculated is a cannon. (No need to worry about special ship since the first and second weapon name will be the same.)
			if (skillNames.contains("Air-Surface Switch")) {
				if (mainWeapon.getWepName().contains("Twin 127mm (5\"/38 Mk 38)") || secondWeapon.getWepName().contains("Twin 127mm (5\"/38 Mk 38)")) {
					statsFromSkills -= 0.15;
				} else {
					statsFromSkills += 0.15;
				}
			}
			// Must be Queen Elizabeth for the other bonus.
			if (skillNames.contains("For the Queen") && ship.getShipName().equals("Queen Elizabeth")) {
				statsFromSkills += 0.07;
			}
			
			if (skillNames.contains("Arbiter of Z") && (ship.getShipType().equals("Light Cruisers") || ship.getShipType().equals("Heavy Cruisers"))) {
				statsFromSkills += 0.08;
			}
			
			if (skillNames.contains("Substitute Mechanism: Holy Thurible")) {
				statsFromSkills += 0.05;
			}
			
			if (skillNames.contains("Protector of the New Moon") || ship.getShipType().equals("Destroyers")) {
				statsFromSkills += 0.15;
			}
			
			if (skillNames.contains("Task Force Leader")) {
				if (ship.getShipType().equals("Destroyers") || ship.getShipType().equals("Battleships")) {
					statsFromSkills += 0.15;
				}
			}
			if (skillNames.contains("8th Destroyer Division") && ship.getShipType().equals("Destroyers")) {
				statsFromSkills += 0.15;
			}
			
			if (skillNames.contains("I Love My Sisters!") || ship.getShipType().equals("Destroyers")) {
				statsFromSkills += 0.10;
			}
			if (skillNames.contains("Royal Alliance") && vanguardFleet.contains(ship.getShipType())) {
				statsFromSkills += 0.12;
			}
			if (skillNames.contains("Eternal Light of Sardegna") || mainFleet.contains(ship.getShipType())) {
				statsFromSkills += 0.15;
			}
			// Exception for auxgear
			if (gearOne.getGearName().equals("Z Flag") || gearTwo.getGearName().equals("Z Flag")) {
				statsFromSkills += 0.05;
			}
			
		}
		
		
		
		// Scaling Damage. Monarach and Izumo have a higher scaling damage.
		double scalingDamageStat = 1;
		if (ship.getShipName().equals("Monarch") || ship.getShipName().equals("Izumo")) {
			scalingDamageStat = 1.2;
		}
		
		double finalStat = shipWeaponStat * statsFromSkills * scalingDamageStat;
		finalDamage = weaponDamage * weaponCoefficient * slotEfficiency * (1 + (finalStat / 100));
		return finalDamage;
	}
	
	/**
	 * METHOD THAT CALCUALTED THE WEAPON TYPE MOD.
	 * @param skills
	 * @param skillNames
	 * @param enemy
	 * @param mainWeapon
	 * @param statsWeapon
	 * @param shipSlot
	 * @param armorBreak
	 * @return
	 */
	public double getWeaponTypeMod(ShipFile ship, ArrayList<Skill> skills, ArrayList<String> skillNames, Enemy enemy, CommonWeapon mainWeapon, CommonWeapon secondWeapon, int shipSlot, boolean armorBreak, boolean specialShipFound) {
		double buffDamage = 1;
		if (mainWeapon.getWeaponType().equals("Torpedos")) {
			buffDamage += getMiscStats(skills, "Injure Torpedo", 0) + getMiscStats(skills, "Damage Torpedo", 0); 
		} else {
			buffDamage += getMiscStats(skills, "Injure Cannon", 0) + getMiscStats(skills, "Damage Cannon", 0);
		}
		
		// Use a method that will return the max between if armorBreak is true or getting the method to see if there's a skill that causes an armor break;
		if (enemy.getArmor().equals("H") && cannonTypes.contains(mainWeapon.getWeaponType()) && armorBreak) {
			buffDamage += 0.08;
		}
		if (skillNames.contains("Poisonous Sting") && ship.getShipType().equals("Destroyers")) {
			buffDamage += 0.12;
		}
				
		return buffDamage;
		
	}
	
	/**
	 * METHOD THAT CALCULATES THE BONUS DAMAGE DONE BY CRITICAL DAMAGE
	 * @param ship
	 * @param weapon
	 * @param skills
	 * @param skillNames
	 * @param evenOdd
	 * @return
	 */
	public double getCriticalDamage(ShipFile ship, CommonWeapon weapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int evenOdd, int shipSlot, AuxGear gearOne, AuxGear gearTwo) {
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
		
		// Overall crit damage buff
		if (skillNames.contains("Wolf Pack Formation - U-96") && ship.getShipType().equals("Submarines")) {
			critBuff += 0.15;
		}
		
		// Aux Gear effects
		if (shipSlot == 1) {
			if (gearOne.getGearName().equals("Type 1 Armor Piercing Shell") || gearTwo.getGearName().equals("Type 1 Armor Piercing Shell")) {
				critBuff += 0.25;
			}
			if (gearOne.getGearName().equals("Type 91 Armor Piercing Shell") || gearTwo.getGearName().equals("Type 91 Armor Piercing Shell")) {
				critBuff += 0.15;
			}
		}
		return critBuff;
	}
	
	/**
	 * METHOD THAT CALCULATES THE PERCENTAGE DAMAGE THAT WILL BE DONE BASED OFF THE ARMOR OF THE ENEMY
	 * @param ship
	 * @param enemy
	 * @param weapon
	 * @param statsWeapon
	 * @param skills
	 * @param skillNames
	 * @param ammoType
	 * @param shipSlot
	 * @param noteColor
	 * @return
	 */
	public double getArmorModifier(ShipFile ship, Enemy enemy, CommonWeapon mainWeapon, CommonWeapon secondWeapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int ammoType, int shipSlot, int noteColor, boolean specialShipFound) {
		double armorMod = 0;
		boolean change = false;
		
		// Check if any of the skills that will change the damage modifier is in the given skill list.
		// UPDATE NAMES AFTER ALL EXCEPTIONS HAVE BEEN ADDED.
		ArrayList<String> changeToggle = new ArrayList<String>(Arrays.asList("2,700 Pounds of Justice", "APsolute Ammunition", "Armor-Penetrating Arrow", "Armor-Piercing Hypercharge", "Expert Loader", "High-Explosive Volley Fever", "Impartial Destruction", 
				"Kitakaze Style - Horizon Splitter", "Tricolo Order", "The Fearless Privateer"));
		for (int i = 0; i < changeToggle.size(); i++) {
			if (skillNames.contains(changeToggle.get(i))) {
				change = true;
				break;
			}
		}
		
		
		String enemyArmor = enemy.getArmor();
		if (enemyArmor.equals("L")) {
			armorMod = mainWeapon.getLightDamage();
		} else if (enemyArmor.equals("M")) {
			armorMod = mainWeapon.getMediumDamage();
		} else {
			armorMod = mainWeapon.getHeavyDamage();
		}
		
		if (!change) {
			return armorMod;
		} else { // Exceptions. If there are multiple skills that will change the armor modifier, it will go by alphabetical order.
			if (skillNames.contains("2,700 Pounds of Justice")) {
				// For this purpose guarantee an armor break.
				// THIS EXCEPTION WILL ONLY TRIGGER IF THE MAIN WEAPON IS BEING CALCULATED SINCE THE SECOND PART OF THIS SKILL CHANGES THE MAIN GUN'S AMMO TYPE, NOT THE SECONDARY GUNS.
				if (shipSlot == 1) {
					for (int i = 0; i < massachusettsGunException.size(); i++) {
						// For special ships, this will not trigger since ship slot would be the third one.
						if (secondWeapon.getWepName().contains(massachusettsGunException.get(i))) {
							if (enemy.getArmor().equals("L")) {
								armorMod = .65;
							} else if (enemy.getArmor().equals("M")) {
								armorMod = 1.35;
							} else {
								armorMod = 1.15;
							}
							break;
						}
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
			} else if (skillNames.contains("Armor-Penetrating Arrow") && noteColor == 0) {
				if (enemyArmor.equals("L")) {
					armorMod = 1.20;
				} else if (enemyArmor.equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 0.90;
				}
				mainWeapon.setAmmoType("AP");
			} else if (skillNames.contains("Spirited Guidance") && noteColor == 1) {
				if (enemyArmor.equals("L")) {
					armorMod = 1.20;
				} else if (enemyArmor.equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 1.00;
				}
			} else if (skillNames.contains("Armor-Piercing Hypercharge")) {
				if (enemyArmor.equals("L")) {
					armorMod = 1.0;
				} else if (enemyArmor.equals("M")) {
					armorMod = 1.10;
				} else { // Heavy Armor
					armorMod = 1.20;
				}
				mainWeapon.setAmmoType("HE");
			} else if (shipSlot == 1 && skillNames.contains("Expert Loader")) {
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
			} else if (skillNames.contains("High-Explosive Volley Fever") && noteColor == 2) {
				if (enemyArmor.equals("L")) {
					armorMod = 1.10;
				} else if (enemyArmor.equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 1.00;
				}
			}
			else if (mainWeapon.getWeaponType().equals("Torpedos") && skillNames.contains("Impartial Destruction")) {
				armorMod = 1.15;
			} else if (shipSlot == 1 && skillNames.contains("Kitakaze Style - Horizon Splitter")) {
				armorMod = 1.15;
			} else if (shipSlot == 1 && skillNames.contains("Tricolo Order")) {
				armorMod = 1;
			} else if (shipSlot == 1 && skillNames.contains("The Fearless Privateer") && mainWeapon.getAmmoType().equals("Normal") || mainWeapon.getAmmoType().contains("HE")) {
				if (enemyArmor.equals("L")) {
					armorMod = 1.25;
				} else if (enemyArmor.equals("M")) {
					armorMod = 1.25;
				} else {
					armorMod = 1.05;
				}
			}
			
			// Exceptions that increases the amount of armor modifier to a certain armor type
			if (skillNames.contains("Substitute Mechanism: Holy Thurible") && enemyArmor.equals("H") && shipSlot == 1 && mainWeapon.getAmmoType().equals("HE")) {
				armorMod += 0.15;
			}
			
			if (skillNames.contains("Rock-Paper-Cannon Salvo") && enemyArmor.equals("L") && cannonTypes.contains(mainWeapon.getWeaponType())) {
				armorMod += 0.15;
			}
			
			if (skillNames.contains("Humble-Part Timer") && enemyArmor.equals("L")) {
				armorMod += 0.15;
			}
		}
		return armorMod;
	}
	
	/**
	 * METHOD THAT CALCULATES THE ENHANCING DAMAGE
	 * @param weapon
	 * @param statsWeapon
	 * @param skills
	 * @param skillNames
	 * @param shipSlot
	 * @param manual
	 * @return
	 */
	public double getEnhanceDamage(CommonWeapon mainWeapon, CommonWeapon secondWeapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int shipSlot, boolean manual, boolean specialShipFound) {
		double enhance = 0;
		if (manual) {
			enhance = 1.20 + getMiscStats(skills, "Initial Enhance", 0) + getMiscStats(skills, "Manual Enhance", 0);
		} else {
			enhance = 1.00 + getMiscStats(skills, "Initial Enhance", 0);
		}
		
		// Special ships will fail since they would be using ship slot 3.
		if (skillNames.contains("2,700 Pounds of Justice")) {
			if (shipSlot == 1) {
				for (int i = 0; i < massachusettsGunException.size(); i++) {
					if (mainWeapon.getWepName().contains(massachusettsGunException.get(i))) {
						enhance += 0.15;
						break;
					}
				}
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
	public double getDamageRatio(ArrayList<Skill> skills, ShipFile ship, CommonWeapon mainWeapon, CommonWeapon secondWeapon, Enemy enemy, int shipSlot, int evenOdd, ArrayList<String> skillNames, boolean specialShipFound) {
		double ratio = 0;
		if (cannonTypes.contains(mainWeapon.getWeaponType())) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Cannon");
		} else if (mainWeapon.getWeaponType().equals("Torpedos")) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Torpedo");
		}
		
		// Skill exceptions area.
		
		// Makes sure weapon is a gun.
		if (!mainWeapon.getWeaponType().equals("Torpedos")) {
			if (skillNames.contains("Sonata of Chaos") && evenOdd == 1) {
				ratio += 0.2;
			}
			if (skillNames.contains("Failen Angel")) {
				ratio += 0.01;
			}
			if (shipSlot == 1 && skillNames.contains("Blazing Peaks")) {
				int calibur = Integer.valueOf(mainWeapon.getWepName().replaceAll("[^0-9]",""));
				if (calibur > 280) {
					ratio += 0.25;
				}
			}
			if (shipSlot == 1 && skillNames.contains("Semi-Armor Piercing High-Explosive")) {
				if (mainWeapon.getAmmoType().contains("HE") || mainWeapon.getAmmoType().contains("SAP")) {
					ratio += 0.12;
				} else if (mainWeapon.getAmmoType().contains("AP")) {
					ratio += 0.15;
				}
			}
			if (skillNames.contains("Fuoco di Copertura!") && vanguardFleet.contains(ship.getShipType())) {
				ratio += 0.15;
			}
			
			if (skillNames.contains("The Iris's Vindication") && (mainWeapon.getAmmoType().contains("HE") || secondWeapon.getAmmoType().contains("HE"))) {
				ratio += 0.12;
			}
			
			if (skillNames.contains("Memorial of Ice and Iron")) {
				CommonWeapon tempWeapon = null;
				if (shipSlot == 1)  {
					tempWeapon = mainWeapon;
				} else if (shipSlot == 2){
					tempWeapon = secondWeapon;
				}
				boolean ratioAdded = false;
				for (int i = 0; i < KMSGuns.size(); i++) {
					if (tempWeapon.getWepName().contains(KMSGuns.get(i))) {
						ratio += 0.12;
						ratioAdded = true;
						break;
					}
				}
				if (!ratioAdded) {
					for (int i = 0; i < SNGuns.size(); i++) {
						if (tempWeapon.getWepName().contains(SNGuns.get(i))) {
							ratio += 0.12;
							break;
						}
					}
				}
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
			if (shipSlot == 1 && secondWeapon.getWeaponType().equals("Light Cruiser Guns") || shipSlot == 2 && mainWeapon.getWeaponType().equals("Light Cruiser Guns")) {
				ratio -= 0.35;
			}
		}
		// Ship is either part of the vanguard fleet or main fleet.
		if (skillNames.contains("Auspice of the Stars")) {
			if (vanguardFleet.contains(ship.getShipType())) {
				ratio += 0.15;
			} else {
				ratio += 0.10;
			}
		}
		if (skillNames.contains("Westward Trident") && enemy.getArmor().equals("L")) {
			ratio += 0.15;
		}
		if (skillNames.contains("Giant Hunter") && enemy.getArmor().equals("M")) {
			ratio += 0.25;
		}
		
		if (skillNames.contains("Predestined Launch") && mainFleet.contains(ship.getShipType())) {
			ratio += 0.10;
		}
		if (skillNames.contains("Scorched Blade (Red)")) {
			ratio += 0.15;
		}
		if (skillNames.contains("Venus Friends")) {
			ratio += 0.15;
		}
		if (skillNames.contains("Shooting Gun-Star") || enemy.getArmor().equals("L")) {
			ratio += 0.15;
		}
		if (skillNames.contains("Whimsical Protector") && vanguardFleet.contains(ship.getShipType())) {
			ratio += 0.10;
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
	public double typeDamage(CommonWeapon mainWeapon, Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames, int shipSlot) {
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
		
		if (shipSlot == 1 && skillNames.contains("Capricious Firing") && mainWeapon.getAmmoType().contains("HE") &&
			(enemy.getShipType().equals("BB") || enemy.getShipType().equals("BC"))) {
			dmg += 0.10;
		}
		
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
	
	/* |--------------------------------------------------------------------------------------------------------------|
	 * | These methods below are used to reduce redundant for loops and if statements in the other methods.           |
	 * | They should not be changed unless something goes wrong and needs correction, such as wrong spelling or index |
	 * |--------------------------------------------------------------------------------------------------------------|
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
