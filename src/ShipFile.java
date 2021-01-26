import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ShipFile {
	
	private GUIUtility utility = new GUIUtility();
	
	private String shipName;
	
	private String faction;
	
	private String shipClass;
	
	private String shipType;
	
	private int weapon1type;
	
	private int weapon2type;
	
	private int weapon3type;
	
	private double slot1eff;
	
	private double slot2eff;
	
	private double slot3eff;
	
	private double health;
	
	private double firepower;
	
	private double torpedo;
	
	private double antiair;
	
	private double aviation;
	
	private String skill1;
	
	private String skill2;
	
	private String skill3;
	
	private String skill4;
	
	private String skill5;
	
	public ShipFile(String shipName, String shipType) throws FileNotFoundException, IOException {
		ArrayList<String> sp = utility.getShipParameters(shipType, shipName);
		setVariables(sp, shipType);
	}
	
	/*
	 * Set the parameters for the ship.
	 * -1 or null is set for things that aren't used.
	 */
	private void setVariables(ArrayList<String> sp, String shipType) {
		this.shipName = sp.get(0);
		this.faction = sp.get(1);
		this.shipClass = sp.get(2);
		this.shipType = sp.get(3);
		this.weapon1type = Integer.parseInt(sp.get(4));
		this.weapon2type = Integer.parseInt(sp.get(5));
		if (shipType.equals("Aircraft Carrier") || shipType.equals("Light Aircraft Carrier") || shipType.equals("Submarine")) {
			this.weapon3type = Integer.parseInt(sp.get(6));
			this.slot1eff = Double.parseDouble(sp.get(7));
			this.slot2eff = Double.parseDouble(sp.get(8));
			this.slot3eff = Double.parseDouble(sp.get(9));
			this.health = Double.parseDouble(sp.get(10));
			this.firepower = Double.parseDouble(sp.get(11));
			this.torpedo = Double.parseDouble(sp.get(12));
			this.antiair = Double.parseDouble(sp.get(13));
			this.aviation = Double.parseDouble(sp.get(14));
			this.skill1 = sp.get(15);
			this.skill2 = sp.get(16);
			this.skill3 = sp.get(17);
			this.skill4 = sp.get(18);
			this.skill5 = sp.get(19);
		} else {
			this.weapon3type = -1;
			this.slot1eff = Double.parseDouble(sp.get(6));
			this.slot2eff = Double.parseDouble(sp.get(7));
			this.slot3eff = Double.parseDouble(sp.get(8));
			this.health = Double.parseDouble(sp.get(9));
			this.firepower = Double.parseDouble(sp.get(10));
			this.torpedo = Double.parseDouble(sp.get(11));
			this.antiair = Double.parseDouble(sp.get(12));
			this.aviation = -1.0;
			this.skill1 = sp.get(13);
			this.skill2 = sp.get(14);
			this.skill3 = sp.get(15);
			this.skill4 = sp.get(16);
			this.skill5 = sp.get(17);
		}
	}
	
	public String getShipName() {
		return shipName;
	}
	
	public String getFaction() {
		return faction;
	}
	
	public String getShipClass() {
		return shipClass;
	}
	
	public String getShipType() {
		return shipType;
	}
	
	// Gui will pass in which slot to get the weapon for. Only carriers and submarines will have an active third weapon slot.
	public int getWeaponSlot(int wepSlot) {
		if (wepSlot == 1) {
			return weapon1type;
		} else if (wepSlot == 2) {
			return weapon2type;
		} else if (wepSlot == 3) {
			return weapon3type;
		} else {
			return -1;
		}
	}
	
	// Gui will pass in which slot to get the weapon coefficient for. Only carriers and submarines will have an active third weapon slot.
	public double getEffSlot(int effSlot) {
		if (effSlot == 1) {
			return slot1eff;
		} else if (effSlot == 2) {
			return slot2eff;
		} else if (effSlot == 3) {
			return slot3eff;
		} else {
			return -1;
		}
	}
	
	public double getHealth() {
		return health;
	}
	
	public double getFirepower() {
		return firepower;
	}
	
	public double getTorpedo() {
		return torpedo;
	}
	
	public double getAA() {
		return antiair;
	}
	
	public double getAviation() {
		return aviation;
	}
	
	// Probably won't be used, but will return the given skill in said slot. If the ship has no x skill, null will be returned.
	public String getSkill(int skillNum) {
		if (skillNum == 1) {
			return skill1;
		} else if (skillNum == 2) {
			return skill2;
		} else if (skillNum == 3) {
			return skill3;
		} else if (skillNum == 4) {
			return skill4;
		} else if (skillNum == 5) {
			return skill5;
		} else {
			return null;
		}
	}
}
