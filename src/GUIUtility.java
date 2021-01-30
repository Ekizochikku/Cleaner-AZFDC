import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class GUIUtility {

	private WeaponTypesForSlot weaponType;
	
	/**
	 * CONSTRUCTOR
	 */
	public GUIUtility() {
		weaponType = new WeaponTypesForSlot();
	}
	
	////////////////////////////////////////SHIP AND WEAPON FILTERING////////////////////////////////////////
	
	/**
	 * Checks the given ship type and returns a file containing all ships of the given type.
	 * @param shipType
	 * @return
	 */
	public String getShipTypeFile(String shipType) {
		String fileName = "";
		switch (shipType) {
			case "Destroyers":
				fileName = "./Ship Files/Destroyers.tsv";
				break;
			case "Light Cruisers":
				fileName = "./Ship Files/Light Cruisers.tsv";
				break;
			case "Heavy Cruisers":
				fileName = "./Ship Files/Heavy Cruisers.tsv";
				break;
			case "Large Cruisers":
				fileName = "./Ship Files/Large Cruisers.tsv";
				break;
			case "Battlecruisers":
				fileName = "./Ship Files/Battlecruisers.tsv";
				break;
			case "Battleships":
				fileName = "./Ship Files/Battleships.tsv";
				break;
			case "Aviation Battleships":
				fileName = "./Ship Files/Aviation Battleships.tsv";
				break;
			case "Monitors":
				fileName = "./Ship Files/Monitors.tsv";
				break;
			case "Light Aircraft Carriers":
				fileName = "./Ship Files/Light Aircraft Carriers.tsv";
				break;
			case "Aircraft Carriers":
				fileName = "./Ship Files/Carriers.tsv";
				break;
			case "Submarines":
				fileName = "./Ship Files/Submarines.tsv";
				break;
			default:
				break;
		}
		return fileName;
	}
	
	/**
	 * Checks the given weapon type and returns a file containing all weapons of the given type.
	 * @param weaponType
	 * @return
	 */
	public String getWeaponTypeFile(String weaponType) {
		String fileName = "";
		switch (weaponType) {
			case "Destroyer Guns":
				fileName = "./Weapons/DestroyerGuns.tsv";
				break;
			case "Light Cruiser Guns":
				fileName = "./Weapons/Light Cruisers Guns.tsv";
				break;
			case "Heavy Cruiser Guns":
				fileName = "./Weapons/HeavyCruiserGuns.tsv";
				break;
			case "Large Cruiser Guns":
				fileName = "./Weapons/Large Cruiser Guns.tsv";
				break;
			case "Battleship Guns":
				fileName = "./Weapons/BattleshipGuns.tsv";
				break;
			case "Anti-Air Guns":
				fileName = "./Weapons/GunTypeExceptions.csv";
				break;
			case "Torpedos":
				fileName = "./Weapons/Torpedos.tsv";
				break;
			case "Submarine Torpedos":
				fileName = "./Weapons/Submarine Torpedos.tsv";
				break;
			case "Fighter Planes":
				fileName = "./Weapons/Fighter Planes.tsv";
				break;
			case "Bomber Planes":
				fileName = "./Weapons/Dive Bombers.tsv";
				break;
			case "Torpedo Planes":
				fileName = "./Weapons/Torpedo Bombers.tsv";
				break;
			case "Seaplanes":
				fileName = "./Weapons/Seaplanes.tsv";
				break;
			default:
				break;
		}
		return fileName;
	}
	
	////////////////////////////////////////GUI ELEMENTS FILLUP AREA////////////////////////////////////////
	
	/**
	 * Returns all the names of the ships of the chosen ship type
	 * @param shipType
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getNamesOfShipsOfChosenShipType(String shipType) throws FileNotFoundException, IOException {
		String shipTypeFile = getShipTypeFile(shipType);
		ArrayList<String> shipNames = new ArrayList<String>();
		shipNames = getEntityNames(shipTypeFile);
		return shipNames;
	}
	
	/**
	 * Returns all the names of the weapons of the chosen type.
	 * @param weaponType
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getNamesOfWeaponsOfChosenWeaponType(String weaponType) throws FileNotFoundException, IOException {
		String weaponTypeFile = getWeaponTypeFile(weaponType);
		ArrayList<String> weaponNames = new ArrayList<String>();
		weaponNames = getEntityNames(weaponTypeFile);
		return weaponNames;
	}
	
	/**
	 * Returns the names of all the available skills
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getNamesOfSkills() throws FileNotFoundException, IOException {
		ArrayList<String> skillNames = new ArrayList<String>();
		skillNames = getEntityNames("./Skill Files/Skill Stats.tsv");
		return skillNames;
	}
	
	/**
	 * Returns the names of all the available auxiliary equipment
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getAuxNames() throws FileNotFoundException, IOException {
		ArrayList<String> auxNames = getEntityNames("Aux Equipment.tsv");
		return auxNames;
	}
	
	/**
	 * Returns all the names of the given enemies in the current world
	 * @param world
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getWorldEnemies(String world) throws FileNotFoundException, IOException {
		ArrayList<String> theEnemies = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("Enemies.tsv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].contentEquals(world)) {
				String temp = fields[1] + "," + fields[2];
				theEnemies.add(temp);
			}
		}
		br.close();
		return theEnemies;
	}
	
	/**
	 * Returns the parameters of a skill
	 * @param skillName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getSkillParameters(String skillName) throws FileNotFoundException, IOException {
		ArrayList<String> theList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("./Skill Files/Skill Stats.tsv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].equals(skillName)) {
				for (int i = 0; i < fields.length; i++) {
					theList.add(fields[i]);
				}
			}
		}
		br.close();
		return theList;
	}
	
	/**
	 * Returns the skill description or user depending on what is passed through.
	 * Maybe instead of going through each time, have the list hold all the Skill entites and check on it from inside.
	 * @param skillName
	 * @param choice DESC is the description and other option is the user.
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getSkillDescOrUser(String skillName, String choice) throws FileNotFoundException, IOException {
		String holding = "";
		BufferedReader br = new BufferedReader(new FileReader("Skill Stats.tsv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].equals(skillName)) {
				if (choice.equals("DESC")) {
					holding = fields[1];
				} else {
					holding = fields[2];
				}
				
			}
		}
		br.close();
		return holding;
	}
	
	////////////////////////////////////////CREATE SHIP AND WEAPON ENTITY////////////////////////////////////////
	public ShipFile setAndReturnShip(String shipName, String shipType) throws FileNotFoundException, IOException {
		ShipFile theShip = new ShipFile(shipName, shipType);
		return theShip;
	}
	
	////////////////////////////////////////PARAMETER GETTERS////////////////////////////////////////
	
	/*
	 * Returns an array list containing the names of ships/weapons
	 * @param theFile
	 * @return theList
	 */
	public ArrayList<String> getEntityNames(String theFile) throws FileNotFoundException, IOException {
		ArrayList<String> theList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(theFile));
		String line = br.readLine(); //Skip the Header Line
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			theList.add(fields[0]);
		}
		br.close();
		return theList;
	}
	
	/*
	 * Returns a ship's level 120 stats.
	 * @param shiptype
	 * @param shipname
	 * @return theParams
	 */
	public ArrayList<String> getShipParameters(String shiptype, String shipname) throws FileNotFoundException, IOException {
		ArrayList<String> theParams = new ArrayList<String>();
		String theFile = getShipTypeFile(shiptype);
		BufferedReader br = new BufferedReader(new FileReader(theFile));
		String line = br.readLine(); //Skip Header Line
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].equals(shipname)) {
				for (int i = 0; i < fields.length; i++) {
					theParams.add(fields[i]);
				}
			}
			
		}
		br.close();
		return theParams;
	}
	
	/**
	 * Returns the given weapon's stats.
	 * @param weaponType
	 * @param weaponName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getWeaponParameters (String weaponType, String weaponName) throws FileNotFoundException, IOException {
		ArrayList<String> theParams = new ArrayList<String>();
		String theFile = getWeaponTypeFile(weaponType);
		BufferedReader br = new BufferedReader(new FileReader(theFile));
		String line = br.readLine(); //Skip Header Line
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].equals(weaponName)) {
				for (int i = 0; i < fields.length; i++) {
					theParams.add(fields[i]);
				}
			}
			
		}
		br.close();
		return theParams;
	}
	
	/**
	 * Returns the given auxiliary gear's stats.
	 * @param auxName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getAuxParams(String auxName) throws FileNotFoundException, IOException {
		ArrayList<String> auxParams = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("Aux Equipment.tsv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].contentEquals(auxName)) {
				for (int i = 0; i < fields.length; i++) {
					auxParams.add(fields[i]);
				}
			}
		}
		br.close();
		return auxParams;
	}
	
	/**
	 * Returns the enemy's stats.
	 * @param enemy
	 * @param world
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ArrayList<String> getEnemyParameters(String enemy, String world) throws FileNotFoundException, IOException{
		//Adding in print statements to debug
		//System.out.println("In the enemy parameters method:  "+ enemy + " " + world);
		ArrayList<String> theParams = new ArrayList<String>();
		String[] enemyNameAndLevel = enemy.split(","); //Name and Level
		
		String parseOutLvl = enemyNameAndLevel[1].replaceAll("[^\\d.]", "");
		enemyNameAndLevel[1] = parseOutLvl;
		//System.out.println("the enemy name and level fields: " + Arrays.toString(enemyNameAndLevel));

		BufferedReader br = new BufferedReader(new FileReader("Enemies.tsv"));
		String line = br.readLine();
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			//System.out.println("the fields: " + Arrays.toString(fields));
			if (fields[0].equals(world) && fields[1].equals(enemyNameAndLevel[0]) && fields[2].equals(enemyNameAndLevel[1])) {
				for (int i = 0; i < fields.length; i++) {
					theParams.add(fields[i]);
				}
			}
		}
		br.close();
		return theParams;
	}
	
	/* @author Kevin Nguyen
	 * Returns the parameter mainly to use the check weapon type methods
	 * Basically Brains code except you return i == 4 or 5
	 * Will cause errors on certain ship types easy to fix but right now i'm lazy
	 * Can change to make it better by changing the third parameter to the name of the parameter you want but again i'm lazy right now
	 * @param weaponSlot always either 4 or 5 to get the weaponNum
	 * @return theParams;
	 */
	public String getGetSpecificWeaponParam(String shiptype, String shipname, int weaponSlot) throws FileNotFoundException, IOException {
		String theParams = "";
		String theFile = getShipTypeFile(shiptype);
		BufferedReader br = new BufferedReader(new FileReader(theFile));
		String line = br.readLine(); //Skip Header Line
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] fields = line.split("	");
			if (fields[0].equals(shipname)) {
				for (int i = 0; i < fields.length; i++) {
					if(i == weaponSlot) {
						theParams += fields[i];
					}
				}
			}
			
		}
		br.close();
		return theParams;
	}
	
	////////////////////////////////////////VALID WEAPONS PER SLOT CHECKER////////////////////////////////////////
	
	/**
	 * Checks what weapons can be used for said ship in the designated slot. If there is more than one weapon type, it will be split with a / and will need to be split in the GUI.
	 * @param shipType
	 * @param weaponNumber
	 * @return
	 */
	public String checkValidWeaponsForSlotOne(String shipType, int weaponNumber) {
		String slottedWep = "";
		System.out.println("The ship type is:" + shipType);
		switch (shipType) {
			case "Destroyer":
				slottedWep = weaponType.slotOneDestroyers(weaponNumber);
				break;
			case "Light Cruiser":
				slottedWep = weaponType.slotOneLightCruisers(weaponNumber);
				break;
			case "Heavy Cruiser":
				slottedWep = weaponType.slotOneHeavyCruisers(weaponNumber);
				break;
			case "Large Cruiser":
				slottedWep = weaponType.slotOneLargeCruisers(weaponNumber);
				break;
			case "Battlecruiser":
				slottedWep = weaponType.slotOneBattlecruisers(weaponNumber);
				break;
			case "Battleship":
				slottedWep =  weaponType.slotOneBattleships(weaponNumber);
				break;
			case "Aviation Battleship":
				slottedWep = weaponType.slotOneAviationBattleShips(weaponNumber);
				break;
			case "Monitor":
				slottedWep = weaponType.slotOneMonitors(weaponNumber);
				break;
			case "Light Aircraft Carrier":
				slottedWep = weaponType.slotOneAndTwoCarriers(weaponNumber);
				break;
			case "Aircraft Carrier":
				slottedWep = weaponType.slotOneAndTwoCarriers(weaponNumber);
				break;
			case "Submarine":
				slottedWep = weaponType.slotOneAndTwoSubmarines(weaponNumber);
				break;
			default:
				break;
		}
		return slottedWep;
	}
	
	/**
	 * Checks what weapons can be used for said ship in the designated slot. If there is more than one weapon type, it will be split with a / and will need to be split in the GUI.
	 * @param shipType
	 * @param weaponNumber
	 * @return
	 */
	public String checkValidWeaponsForSlotTwo(String shipType, int weaponNumber) {
		String slottedWep = "";
		System.out.println("The ship type is:" + shipType);
		switch (shipType) {
			case "Destroyer":
				slottedWep = weaponType.slotTwoDestroyers(weaponNumber);
				break;
			case "Light Cruiser":
				slottedWep = weaponType.slotTwoLightCruisers(weaponNumber);
				break;
			case "Heavy Cruiser":
				slottedWep = weaponType.slotTwoHeavyCruisers(weaponNumber);
				break;
			case "Large Cruiser":
				slottedWep = weaponType.slotTwoLargeCruisers(weaponNumber);
				break;
			case "Battlecruiser":
				slottedWep = weaponType.slotTwoBattlecruisers(weaponNumber);
				break;
			case "Battleship":
				slottedWep =  weaponType.slotTwoBattleships(weaponNumber);
				break;
			case "Aviation Battleship":
				slottedWep = weaponType.slotTwoAviationBattleShips(weaponNumber);
				break;
			case "Monitor":
				slottedWep = weaponType.slotTwoMonitors(weaponNumber);
				break;
			case "Light Aircraft Carrier":
				slottedWep = weaponType.slotOneAndTwoCarriers(weaponNumber);
				break;
			case "Aircraft Carrier":
				slottedWep = weaponType.slotOneAndTwoCarriers(weaponNumber);
				break;
			case "Submarine":
				slottedWep = weaponType.slotOneAndTwoSubmarines(weaponNumber);
				break;
			default:
				break;
		}
		return slottedWep;
	}
	
	/**
	 * Checks what weapons can be used for said ship in the designated slot. If there is more than one weapon type, it will be split with a / and will need to be split in the GUI.
	 * Slot three is only available for carriers and submarines.
	 * @param shipType
	 * @param weaponNumber
	 * @return
	 */
	public String checkValidWeaponsForSlotThree(String shipType, int weaponNumber) {
		String slottedWep = "";
		System.out.println("The ship type is:" + shipType);
		switch (shipType) {
			case "Light Aircraft Carrier":
				slottedWep = weaponType.slotThreeCarriers(weaponNumber);
				break;
			case "Aircraft Carrier":
				slottedWep = weaponType.slotThreeCarriers(weaponNumber);
				break;
			case "Submarine":
				slottedWep = weaponType.slotThreeSubmarines(weaponNumber);
				break;
			default:
				break;
		}
		return slottedWep;
	}
	
	////////////////////////////////////////MISCELLENOUS////////////////////////////////////////
	
	public int getDangerLevel(String world) {
		System.out.println("The current world is:" + world);

		int maxDangerLevel = 0;
		int currentChapter = 0;
		char worldNum = world.charAt(0);
		if(world.charAt(1) != '-') {
			maxDangerLevel = 10;
		} else {
				//gets the int via ascii conversion.
				currentChapter = worldNum - '0';
				//System.out.println("The current chapter is:" + currentChapter);
				if (currentChapter <= 4) {
					maxDangerLevel = 3;
				} else if (currentChapter <= 7) {
					maxDangerLevel = 5;
				} else if (currentChapter <= 9) {
					maxDangerLevel = 8;
				} else {
					maxDangerLevel = 10;
				}
			}
		return maxDangerLevel;
	}
	
	/**
	 * A method to get all the ship names onto the jcombo box
	 * Need help specifying which names for which ships
	 * @author Kevin Nguyen
	 * @param comboBox The first button for the ship/weapon names
	 * @param isShip a boolean variable to determine if we're getting the ship or weapon names
	 * @param theType string that's passed into the getWeaponList/getShipList method and gets the list of names from csv
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void insertNames(JComboBox comboBox,boolean isShip, String theType) throws FileNotFoundException, IOException {
		ArrayList<String> initialUserChoice = null; 
		GUIUtility theList;
		theList = new GUIUtility();
		//true = we're getting a ship name, false is a weapon
		if(isShip) {
			initialUserChoice = theList.getNamesOfShipsOfChosenShipType(theType);
		} else {
			//Weapon lists method doesn't exist yet but i'm assuming it will be this.
			//Remember to uncomment this
			System.out.println("Inserting name for weapon type: " + theType);
			initialUserChoice = theList.getNamesOfWeaponsOfChosenWeaponType(theType);
		}
		initialUserChoice.add("");
		Collections.sort(initialUserChoice);
		comboBox.setModel(new DefaultComboBoxModel<Object>(initialUserChoice.toArray()));
	}
	
	/**
	 * Method to insert the ship or weapon type into the appropriate combo box. 
	 * @author Kevin Nguyen
	 * @param comboBox the combo box
	 * @param weaponParamNum the weapon parameter number to get the appropriate type 
	 * @param shipType 
	 * @param shipName
	 * @param firstSlot slot check
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void insertType(JComboBox<Object> comboBox, int weaponParamNum, String shipType, String shipName, int Slot) throws FileNotFoundException, IOException {
		ArrayList<String> weaponTypes = null; 
		String weaponNumString = null; 
		//String exceptionCheck = "";
		int currentWeaponNum;
		GUIUtility theList;
		theList = new GUIUtility();
		weaponNumString = theList.getGetSpecificWeaponParam(shipType, shipName, weaponParamNum);
		currentWeaponNum = Integer.parseInt(weaponNumString);
		
		//exceptionCheck = theList.checkSlotTwoWeps("BB", 2);
		//System.out.println("Grabbing for hardcoded Battleships " + exceptionCheck);
		if(Slot == 1) {
			System.out.println("Current weapon num for slot 1 is: " + currentWeaponNum);
			System.out.println("The ship type for slot 1 is: " + shipType);
			weaponTypes = createWeaponTypeList(theList.checkValidWeaponsForSlotOne(shipType, currentWeaponNum));
			
		} else if(Slot == 2) {
			System.out.println("Current weapon num for slot 2 is: " + currentWeaponNum);
			System.out.println("The ship type for slot 2 is: " + shipType);
			weaponTypes = createWeaponTypeList(theList.checkValidWeaponsForSlotTwo(shipType, currentWeaponNum));
		} else {
			System.out.println("Current weapon num for slot 3 is: " + currentWeaponNum);
			System.out.println("The ship type for slot 3 is: " + shipType);
			weaponTypes = createWeaponTypeList(theList.checkValidWeaponsForSlotThree(shipType, currentWeaponNum));
		}
		
		comboBox.setModel(new DefaultComboBoxModel<Object>(weaponTypes.toArray()));
	} 
	
	/**
	 * Simple Method to enable the third weapon slot and all associated attributes outside 3x3
	 * @author Kevin Nguyen
	 * @param same names just check the window builder for what is what. 
	 */
	public static void enableDisableSlot3(JLabel lblNewLabel, JComboBox weaponTypeCBox3, JLabel lblWeaponTypeSlot, JComboBox weaponNameSlot3,
			JLabel lblSlotDamage, JTextPane slot3Pane ,boolean carrier) {
		if(carrier) {
			//Just in case removeAllItems crashes the code if the combo box is empty
			if(weaponNameSlot3.getItemCount() != 1) {
				weaponNameSlot3.removeAllItems();
			}
			weaponTypeCBox3.setEnabled(true);
			weaponNameSlot3.setEditable(true);
			weaponNameSlot3.setEnabled(true);
			lblWeaponTypeSlot.setEnabled(true);
			slot3Pane.setEnabled(true);
			lblNewLabel.setEnabled(true);
			AutoCompletion.enable(weaponNameSlot3);
			lblSlotDamage.setEnabled(true);
		} else {
			weaponTypeCBox3.setEditable(false);
			weaponNameSlot3.insertItemAt("", 0);
			weaponNameSlot3.setEnabled(false);
			System.out.println("The current item count to be removed" + weaponNameSlot3.getItemCount());
			System.out.println(weaponNameSlot3.getItemAt(0));
				weaponTypeCBox3.removeAllItems();
				weaponNameSlot3.removeAllItems();
			lblWeaponTypeSlot.setEnabled(false);
			slot3Pane.setEnabled(false);
			lblNewLabel.setEnabled(false);
			lblSlotDamage.setEnabled(false);
		}
		
	}
	
	
	
	/**
	 * Using Brians methods we check what types of weapons can be used in what slot. Whatever string it returns we convert that into
	 * an array to insert it into the combo box.
	 * Since he didn't want arrays in wepTypes this is the ghetto way. 
	 * @author Kevin Nguyen
	 * @param compatibleWeapons the string that is returned from checkWeapon methods.
	 * @return the parsed string for our methods. 
	 */
	public static ArrayList<String> createWeaponTypeList(String compatibleWeapons) {
		System.out.println("string is: " +compatibleWeapons + " Length is " + compatibleWeapons.length());
		ArrayList<String> weaponTypeArray = new ArrayList<String>();		
		String weaponType = "";
		String weaponType2 = "";
		//System.out.println("Current word for type list " + compatibleWeapons);
		if((compatibleWeapons.length() > 2) && ((!(compatibleWeapons.equals("SEAPLANE"))) && (!(compatibleWeapons.equals("TORPEDOS")) 
				&& (!(compatibleWeapons.equals("SUBTORPEDOS"))&& (!(compatibleWeapons.equals("FIGHTERP"))&& (!(compatibleWeapons.equals("TORPEDOP"))
						&& (!(compatibleWeapons.equals("BOMBERP"))&& (!(compatibleWeapons.equals("BOMBERP/CL")))))))))) {
				
			for (int i = 0; i <= compatibleWeapons.length() - 1; i++) {
				if(i < 2) {
					weaponType += compatibleWeapons.charAt(i);
				}
				else if (i > 2) {
					weaponType2 += compatibleWeapons.charAt(i);
				}
			
			} 
				
			//System.out.println("Two weapon checks types" + weaponType + " " + weaponType2);
			weaponTypeArray.add((weaponType += "GUNS"));
			weaponTypeArray.add((weaponType2 += "GUNS"));
			} else {
				//System.out.println("hit else statement" + compatibleWeapons);
				if((compatibleWeapons.length() == 2)) {
					compatibleWeapons += "GUNS";
					weaponTypeArray.add(compatibleWeapons);
				} else {
					weaponTypeArray.add(compatibleWeapons);
				}
			}
		//System.out.println(weaponTypeArray);

		return weaponTypeArray;
	}
	
	/*
	 * Simple method to add all the worlds to comboBox
	 */
	public void addWorldNum(JComboBox<String> theWorldBox) {
		String theWorld = null;
		for (int i = 1; i <= 13; i++) {
			for(int d = 1; d <= 4; d++) {
				theWorld = (i+"-"+d);
				theWorldBox.addItem(theWorld);
			}
		}
	}
	
	/*
	 */
	public void insertEnemyNames(JComboBox<Object> comboBox, String theCurrentWorld) throws FileNotFoundException, IOException {
		ArrayList<String> initialUserChoice = null; 
		//System.out.println("Inserting name for weapon type: " + theType);
		initialUserChoice = getAllEnemiesForSpecificWorld(theCurrentWorld, "Enemies.tsv", 1, 2);
		Collections.sort(initialUserChoice);
		comboBox.setModel(new DefaultComboBoxModel<Object>(initialUserChoice.toArray()));
	}
	
	/* Returns all the elements of whatever column from whatever file you want. Used for the enmies.tsv file mainly
	 * If it doesn't look god the level can be in it's own jlist 
	 * 
	 * Do we show all the enemies of that world, or only unique names (with unique levels)?
	 * The method currently does the latter
	 */
	public static ArrayList<String> getAllEnemiesForSpecificWorld(String theElement, String theFile, Integer theField, Integer theLevel) throws FileNotFoundException, IOException {
			ArrayList<String> theList = new ArrayList<String>();
			String enemyPlusLevel = "";
			//For enemies with same level, remove from the enemy list since their health isn't factored
			BufferedReader br = new BufferedReader(new FileReader(theFile));
			String line = br.readLine();
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String[] fields = line.split("	");
				if (fields[0].equals(theElement)) {
					enemyPlusLevel = fields[theField] + ", Lvl " + fields[theLevel]; 
					theList.add(enemyPlusLevel);
				}
			}
			Set<String> nonDupedEnemySet = new LinkedHashSet<>(theList);
			theList.clear();
			theList.addAll(nonDupedEnemySet);
			br.close();
			return theList;
	}
	
	/*
	 * Very Simple method to read the file to help with change log,
	 * will change if we need more persistent variables outside popup
	 * If you want to options the file variable + change log can be a tsv folder
	 * 
	 * REMINDER TO CHANGE PersitentVariables.txt to true EVERY NEW UPDATE!!!  
	 * @author Kevin Nguyen
	 */
	public static boolean readPersistentVariables(int variableNum) throws IOException{
		boolean willPopUp = false;
		BufferedReader br = new BufferedReader(new FileReader("PersistentVariables.txt"));
		String line;
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			String[] variables = line.split(";");
			System.out.println(variables);
			if (variables[variableNum].equals("popUpMSG=true")) {
				willPopUp = true;
			} 
		}
		//Overwrite the file so that the pop up no longer pops up on subsequent openings
		//these 3 lines will need 2b changed if we ever have/need more persistent stuff
		//done via:           .write(variables[i])
		Writer overWriteVariables = new FileWriter("PersistentVariables.txt", false);
		overWriteVariables.write("popUpMSG=false;");
		overWriteVariables.close();
		br.close();
		
		return willPopUp;
	}
	/*
	 * Method to write the version history to the joptionpane, will finish later
	 * need to make a joptionpane with jframe, jlist(?), and jscrollpane.  
	 * 
	 * If we're lazy we can manually write into the joptionpane or manually have line breaks in txt file.
	 */
	public static JScrollPane overWritePopUpWithVersionHistory() throws IOException{
		String theTxt = "";
		BufferedReader br = new BufferedReader(new FileReader("VersionHistory.txt"));
		String line;
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			theTxt += line + "\n";
		}
		JTextArea text = new JTextArea(12, 40);
		text.setText(theTxt);
		text.setEditable(false);
		text.setCaretPosition(0);
		JScrollPane versionText = new JScrollPane(text);
		br.close();
		return versionText;
	}

}
