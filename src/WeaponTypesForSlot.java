
public class WeaponTypesForSlot {
	
	public String weaponType;
	
	public WeaponTypesForSlot() {
		weaponType = "";
	}
	
	////////////////////////////////////////DESTROYERS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by destroyers in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneDestroyers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			default:
				break;
		}
		
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by destroyers in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoDestroyers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Torpedos";
				break;
			default:
				break;
		}
		
		return weaponType;
	}
	
	public String slotThreeDestroyers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Anti-Air Guns";
				break;
			default:
				break;
		}
		
		return weaponType;
	}
	
	////////////////////////////////////////LIGHT CRUISERS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by light cruisers in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneLightCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Light Cruiser Guns";
				break;
			case 2:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by light cruisers in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoLightCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Torpedos";
				break;
			case 1:
				weaponType = "Destroyer Guns";
				break;
			case 2:
				weaponType = "Light Cruiser Guns";
				break;
			case 3:
				weaponType = "Anti-Air Guns";
				break;
			case 4:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns/Anti-Air Guns";
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeLightCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Torpedos";
				break;
			case 1:
				weaponType = "Destroyer Guns";
				break;
			case 2:
				weaponType = "Light Cruiser Guns";
				break;
			case 3:
				weaponType = "Anti-Air Guns";
				break;
			case 4:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns/Anti-Air Guns";
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////HEAVY CRUISERS SECTION////////////////////////////////////////
	/**
	 * Weapons that will be used by heavy cruisers in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneHeavyCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Heavy Cruiser Guns";
				break;
			case 1:
				weaponType = "Light Cruiser Guns";
				break;
			case 2:
				weaponType = "Heavy Cruiser Guns/Light Cruiser Guns";
				break;
			case 3:
				weaponType = "Heavy Cruiser Guns/Large Cruiser Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by heavy cruisers in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoHeavyCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Torpedos";
				break;
			case 1:
				weaponType = "Destroyer Guns";
				break;
			case 2:
				weaponType = "Light Cruiser Guns";
				break;
			case 3:
				weaponType = "Anti-Air Guns";
				break;
			case 4:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns/Anti-Air Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeHeavyCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Torpedos";
				break;
			case 1:
				weaponType = "Destroyer Guns";
				break;
			case 2:
				weaponType = "Light Cruiser Guns";
				break;
			case 3:
				weaponType = "Anti-Air Guns";
				break;
			case 4:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns/Anti-Air Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////LARGE CRUISERS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by large cruisers in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneLargeCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Large Cruiser Guns";
				break;
			case 1:
				weaponType = "Heavy Cruiser Guns";
				break;
			case 2:
				weaponType = "Large Cruiser Guns/Heavy Cruiser Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by large cruisers in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoLargeCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Light Cruiser Guns";
				break;
			case 2:
				weaponType = "Torpedos";
				break;
			case 3:
				weaponType = "Anti-Air Guns";
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeLargeCruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Light Cruiser Guns";
				break;
			case 2:
				weaponType = "Torpedos";
				break;
			case 3:
				weaponType = "Anti-Air Guns";
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////BATTLECRUISERS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by battlecruisers in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneBattlecruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Battleship Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by battlecruisers in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoBattlecruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Light Cruiser Guns";
				break;
			case 2:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			case 3:
				weaponType = "Torpedos";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeBattlecruisers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Anti-Air Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////BATTLESHIPS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by battleships in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneBattleships(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Battleship Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by battleships in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoBattleships(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Light Cruiser Guns";
				break;
			case 2:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeBattleships(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Anti-Air Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////AVIATION BATTLESHIPS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by aviation battleships in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneAviationBattleShips(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Battleship Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by aviation battleships in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoAviationBattleShips(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Seaplanes";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeAviationBattleShips(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Seaplanes";
				break;
			case 1:
				weaponType = "Anti-Air Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////MONITORS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by monitors in their first weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneMonitors(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Battleship Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by monitors in their second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotTwoMonitors(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotThreeMonitors(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Anti-Air Guns";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////SUBMARINES SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by submarines in their first and second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneAndTwoSubmarines(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Submarine Torpedos";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by submarines in their third weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotThreeSubmarines(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Destroyer Guns";
				break;
			case 1:
				weaponType = "Seaplanes";
				break;
			case 2:
				weaponType = "Destroyer Guns/Light Cruiser Guns";
			default:
				break;
		}
		return weaponType;
	}
	
	////////////////////////////////////////CARRIERS SECTION////////////////////////////////////////
	
	/**
	 * Weapons that will be used by carriers in their first and second weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotOneCarriers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Fighter Planes";
				break;
			case 1:
				weaponType = "Bomber Planes";
				break;
			case 2:
				weaponType = "Torpedo Planes";
				break;
			case 3:
				weaponType = "Seaplanes";
				break;
			case 4:
				weaponType = "Anti-Air Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns";
				break;
			case 6:
				weaponType = "Fighter Planes/Bomber Planes/Torpedo Planes";
				break;
			case 7:
				weaponType = "Fighter Planes/Bomber Planes";
			default:
				break;
		}
		return weaponType;
	}
	
	public String slotTwoCarriers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Fighter Planes";
				break;
			case 1:
				weaponType = "Bomber Planes";
				break;
			case 2:
				weaponType = "Torpedo Planes";
				break;
			case 3:
				weaponType = "Seaplanes";
				break;
			case 4:
				weaponType = "Anti-Air Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns";
				break;
			case 6:
				weaponType = "Fighter Planes/Bomber Planes";
				break;
			default:
				break;
		}
		return weaponType;
	}
	
	/**
	 * Weapons that will be used by carriers in their third weapon slot
	 * @param weaponNumber
	 * @return
	 */
	public String slotThreeCarriers(int weaponNumber) {
		switch (weaponNumber) {
			case 0:
				weaponType = "Fighter Planes";
				break;
			case 1:
				weaponType = "Bomber Planes";
				break;
			case 2:
				weaponType = "Torpedo Planes";
				break;
			case 3:
				weaponType = "Seaplanes";
				break;
			case 4:
				weaponType = "Anti-Air Guns";
				break;
			case 5:
				weaponType = "Light Cruiser Guns";
				break;
			case 6:
				weaponType = "Bomber Planes/Light Cruiser Guns";
				break;
			case 7:
				weaponType = "Bomber Planes/Torpedo Planes";
				break;
			default:
				break;
		}
		return weaponType;
	}
}
