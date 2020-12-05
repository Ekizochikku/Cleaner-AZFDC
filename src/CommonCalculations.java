import java.util.ArrayList;
import java.util.Arrays;

public class CommonCalculations {
	
	GUIUtility utility = new GUIUtility();
	
	ArrayList<String> canons = new ArrayList<String>(Arrays.asList("Destroyer Guns", "Light Cruiser Guns", "Heavy Cruiser Guns", "Large Cruiser Guns", "Battleship Guns"));
	
	public double getFinalDamage(ShipFile ship, CommonWeapon weapon, Enemy enemy, ArrayList<Skill> skillList, ArrayList<String> skillNames, AuxGear auxGear, int shipSlot, boolean crit,
	String world, int ammoType, boolean manual, boolean firstSalvo, int dangerLvl, int evenOdd, int removeRandom, boolean armorBreak, String noteColor) {
		// Avoid index out of bounds if something is empty
		double finalDmg = 0;
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
			correctedDamageStat = getCorrectedDamage(ship, weapon, skillList, skillNames, shipSlot, auxGear);
			
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
			finalDmg = Math.floor(temp2 * dmgRedStat);
			
		}
		return finalDmg;
	}
	

	

	/*
	 * Method that calculates the corrected damage.
	 * @param ship The ship object.
	 * @param weapon The weapon object.
	 * @param skills Array List containing skill object.
	 * @param skillNames Array List containing the names of all the skills being used.
	 * @param shipSlot The slot the weapon is equip to.
	 */
	public double getCorrectedDamage(ShipFile ship, CommonWeapon weapon, ArrayList<Skill> skills, ArrayList<String> skillNames, int shipSlot, AuxGear auxGear) {
		double finalDmg = 0;
		double wepDmg = weapon.getDamage();
		double wepCoff = weapon.getCoefficient();
		double slotEff = 0;
		
		// Get the ship slot efficiency
		if (shipSlot == 1) {
			
			// Azuma skill Exception
			if (weapon.getWepName().equals("Triple 310mm (Type 0 Prototype") && skillNames.contains("Barrage Gunnery Manual")) {
				slotEff = ship.getEffSlot(1) + 0.12;
			// Seattle skill Exception
			} else if (skillNames.contains("A Bow's String Has 2 Lines!")) {
				slotEff = ship.getEffSlot(1) + 0.15;
			// Le Triomphant skill Exception
			} else if (skillNames.contains("Sword or Shield")) {
				slotEff = ship.getEffSlot(1) + 0.20;
			// Kitakaze skill Exception
			} else if (skillNames.contains("Kitakaze Style - Unanimous Slash")) {
				slotEff = ship.getEffSlot(1) + 0.15;
			} else {
			//Normal
				slotEff = ship.getEffSlot(1);
			}
		} else {
			slotEff = ship.getEffSlot(2);
		}
		
		// Get the Firepower/Torpedo/AA stats depending on what is being inputed from ship object
		double stat = 0;
		if (weapon.getWeaponType().equals("Torpedos")) {
			// Skill Exception
			if (skillNames.contains("Fading Memories of Glory")) {
				stat = 0;
			} else {
				stat = ship.getTorpedo() + weapon.getGenStat();
			}
		} else {
			stat = ship.getFirepower() + weapon.getGenStat();
		}
		
		// Get the stats from the aux gears.
		if (weapon.getWeaponType().equals("Torpedos") && auxGear != null) {
			stat += auxGear.getTorpedo();
		} else if (!weapon.getWeaponType().equals("Torpedos") && auxGear != null) {
			stat += auxGear.getFirepower();
		}
		
		// Get skill stats
		double skillStat = 0;
		// Check Torpedos first
		if (weapon.getWeaponType().equals("Torpedos")) {
			skillStat = getDmgRatiotoStatBuffs(skills, "Buff To Torpedo", 1, "");
		} else {
			skillStat = getDmgRatiotoStatBuffs(skills, "Buff To Cannon", 1, "");
		}
		
		// Other exceptions
		if (skillNames.contains("AA Firepower")) {
			skillStat += ship.getAA() * 0.30;
		} else if (skillNames.contains("Iron Wing Annihilation")) {
			skillStat += ship.getAA() * 0.15;
		}
		
		// Scaling Damage Exceptions
		double scaling = 1;
		if (ship.getShipName().equals("Monarch") || ship.getShipName().equals("Izumo")) {
			scaling = 1.2;
		}
		
		double finalStat = stat * skillStat * scaling;
		finalDmg = wepDmg * wepCoff * slotEff * (1 + finalStat / 100);
		return finalDmg;
	}
	
	/*
	 * Method for getting Injure by x and Damage by x.
	 */
	public double getWeaponTypeMod(ArrayList<Skill> skills, CommonWeapon weapon, boolean armorBreak) {
		double buffDamage = 0;
		if (weapon.getWeaponType().equals("Torpedos")) {
			buffDamage += getMiscStats(skills, "Injure Torpedo", 0) + getMiscStats(skills, "Injure Torpedo", 0); 
		} else {
			buffDamage += getMiscStats(skills, "Injure Cannon", 0) + getMiscStats(skills, "Injure Cannon", 0);
		}
		return buffDamage;
	}
	
	/*
	 * Method to calculate Critical Damage Boost
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
			
			if (skillNames.contains("Final Shot") && evenOdd == 0) {
				critBuff += 0.50;
			}
		}
		return critBuff;
	}
	
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
		
		// Check for certain skills to check if we need to enter another group of if statements.
		// If not, then we return the armorMod.
		boolean toHold = true;
		ArrayList<String> skillExceptions = new ArrayList<>(Arrays.asList("Impartial Destruction", "Expert Loader", "2,700 Pounds of Justice", "Kitakaze Style - Unanimous Slash", "Tricolor Oder", "APsolute Ammunition",
				"Precise Arrow", "Spiritual Chasing", "Coeur Battant", "Passionate Fever"));
		
		// Check if any of the exception skills are in the current list of skills. If there are move on.
		for (int i = 0; i < skillNames.size(); i++) {
			if (skillExceptions.contains(skillNames.get(i))) {
				toHold = false;
				// No need to finish loop if one is found.
				break;
			}
		}
		
		if (toHold) {
			return armorMod;
		} else {
			// Group of if-else statements for exceptions. In case of multiple skills, stats will correspond with alphabetical order.
			if (skillNames.contains("2,700 Pounds of Justice")) {
				if (enemy.getArmor().equals("L")) {
					armorMod = 0.65;
				} else if (enemy.getArmor().equals("M")) {
					armorMod = 1.35;
				} else {
					armorMod = 1.15;
				}
			} else if (skillNames.contains("APsolute Ammunition")) {
				if (enemy.getArmor().equals("L")) {
					armorMod = 0.85;
				} else if (enemy.getArmor().equals("M")) {
					armorMod = 1.20;
				} else {
					armorMod = 0.85;
				}
			} else if (skillNames.contains("Couer Battant")) {
				if (noteColor.equals("Red")) {
					if (enemy.getArmor().equals("L")) {
						armorMod = 1.40;
					} else if (enemy.getArmor().equals("M")) {
						armorMod = 1.15;
					} else {
						armorMod = 1.15;
					}
				} else if (noteColor.equals("Blue")) {
					if (enemy.getArmor().equals("L")) {
						armorMod = 1.00;
					} else if (enemy.getArmor().equals("M")) {
						armorMod = 1.30;
					} else {
						armorMod = 1.30;
					}
				}
			} else if (skillNames.contains("Expert Loader")) {
				if (!weapon.getWeaponType().equals("Torpedos")) {
					if (ammoType == 1) { //HE
						if (enemy.getArmor().equals("L")) {
							armorMod = 1.35;
						} else if (enemy.getArmor().equals("M")) {
							armorMod = 0.95;
						} else {
							armorMod = 0.70;
						}
					} else { // AP
						if (enemy.getArmor().equals("L")) {
							armorMod = .75;
						} else if (enemy.getArmor().equals("M")) {
							armorMod = 1.10;
						} else { // Heavy Armor
							armorMod = .75;
						}
					}
				}

			} else if (skillNames.contains("Impartial Destruction")) {
				if (weapon.getWeaponType().equals("Torpedos")) {
					armorMod = 1.15;
				}
			} else if (skillNames.contains("Kitakaze Style - Unanimous Slash")) {
				if (!weapon.getWeaponType().equals("Torpedos")) {
					armorMod = 1.15;
				}
			} else if (skillNames.contains("Passionate Fever") && noteColor.equals("Red")) {
				if (enemy.getArmor().equals("L")) {
					armorMod = 1.10;
				} else if (enemy.getArmor().equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 1.00;
				}
			} else if (skillNames.contains("Precise Arrow") && noteColor.equals("Blue")) {
				if (enemy.getArmor().equals("L")) {
					armorMod = 1.20;
				} else if (enemy.getArmor().equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 0.90;
				}
			} else if (skillNames.contains("Spiritual Chasing") && noteColor.equals("Purple")) {
				if (enemy.getArmor().equals("L")) {
					armorMod = 1.20;
				} else if (enemy.getArmor().equals("M")) {
					armorMod = 1.20;
				} else { // Heavy Armor
					armorMod = 1.00;
				}
			} else if (skillNames.contains("Tricolor Order")) {
				armorMod = 1.00;
			} else {
				return armorMod;
			}
		}
		return armorMod;
	}
	
	/*
	 * Bonus damage from manual control vs auto control. Uses initial enhance and manual enhance.
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
	
	/*
	 * Returns a double of the bonus damage that will be gained based off the level difference and danger level.
	 */
	public double getLevelDifference(Enemy enemy, int dangerLvl) {
		double lvlDiff = 1 + Math.min(25, Math.max(-25, 120 - enemy.getEnemyLvl() + dangerLvl)) * 0.02;
		return lvlDiff;
	}
	
	/*
	 * Returns the injure ratio stats
	 */
	public double getInjureRatio(ArrayList<Skill> skills) {
		double ratio = getDmgRatiotoStatBuffs(skills, "Injure Ratio", 0, "");
		return ratio;
	}
	
	/*
	 * Return damage ratio stats
	 */
	public double getDamageRatio(ArrayList<Skill> skills, CommonWeapon weapon, Enemy enemy, int evenOdd, ArrayList<String> skillNames) {
		double ratio = 0;
		if (canons.contains(weapon.getWeaponType())) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Cannon");
		} else if (weapon.getWeaponType().equals("Torpedos")) {
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "Torpedo");
		} else { // 
			ratio = getDmgRatiotoStatBuffs(skills, "Damage Ratio", 0, "AA");
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
	
	/*
	 * Returns bonus damage done to a nation.
	 */
	public double factionDamage(Enemy enemy, ArrayList<Skill> skills) {
		double dmg = 0;
		String nation = enemy.getNation();
		dmg = getDmgToFactions(skills, nation, 0);
		return dmg;
	}
	
	public double typeDamage(Enemy enemy, ArrayList<Skill> skills, ArrayList<String> skillNames) {
		double dmg = 0;
		String type = enemy.getShipType();
		dmg = getDmgToTypes(skills, type, 0);
		
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
					if (skills.get(i).getDmgRationCannon() == 1) {
						startValue += skills.get(i).getDmgRatio();
					}
				} else if (DRT.equals("Torpedo")) {
					if (skills.get(i).getDmgRatioTorpedo() == 1) {
						startValue += skills.get(i).getDmgRatio();
					}
				} else if (DRT.equals("AA")) {
					if (skills.get(i).getDmgRatioAA() == 1) {
						startValue = startValue += skills.get(i).getDmgRatio();
					}
				}
				
			} else if (section.equals("Buff To Firepower")) {
				startValue += skills.get(i).getBuffToFirepower();
			} else if (section.equals("Buff To Torpedo")) {
				startValue += skills.get(i).getBuffToTorpedo();
			} else if (section.equals("Buff To AA")) {
				startValue += skills.get(i).getBuffToAA();
			} else {
				return startValue;
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
			} else { // For other things, such as gold ships, torpedos boats, etc.
				return startValue;
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
				startValue += skill.getDmgRationCannon();
			} else if (buff.equals("Damage Torpedo")) {
				startValue += skill.getDmgRatioTorpedo();
			} else if (buff.equals("Crit Cannon")) {
				startValue += skill.getCritByCannon();
			} else if(buff.equals("Crit Torpedo")) {
				startValue += skill.getCritByTorpedo();
			} else if (buff.equals("Salvo Bonus")) {
				startValue += skill.getSalvoBonus();
			} else {
				return startValue;
			}
	 	}
		return startValue;
	}
}
