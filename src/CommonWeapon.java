import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CommonWeapon {

	private GUIUtility utility;
	
	private String wepName;
	
	private String weaponType;
	
	private double firepower;
	
	private double torpedo;
	
	private double aaStat;
	
	private double damage;
	
	private double coefficient;
	
	private String ammoType;
	
	private double lDamage;
	
	private double mDamage;
	
	private double hDamage;
	
	public CommonWeapon(String weaponName, String weaponType) throws FileNotFoundException, IOException {
		this.utility = new GUIUtility();
		ArrayList<String> list = utility.getWeaponParameters(weaponType, weaponName);
		this.weaponType = weaponType;
		setVariables(list);
	}
	
	// Gen stat can be firepower or torpdo stat because the weapon will only use one or the other
	private void setVariables(ArrayList<String> list) {
		this.wepName = list.get(0);
		this.firepower = Double.parseDouble(list.get(1));
		this.torpedo = Double.parseDouble(list.get(2));
		this.aaStat = Double.parseDouble(list.get(3));
		this.damage = Double.parseDouble(list.get(4));
		this.coefficient = Double.parseDouble(list.get(5));
		this.ammoType = list.get(6);
		this.lDamage = Double.parseDouble(list.get(7));
		this.mDamage = Double.parseDouble(list.get(8));
		this.hDamage = Double.parseDouble(list.get(9));
	}
	
	public String getWepName() {
		return wepName;
	}
	
	public String getWeaponType() {
		return weaponType;
	}
	
	public double getFirepower () {
		return firepower;
	}
	
	public double getTorpedo() {
		return torpedo;
	}
	
	public double getAAStat() {
		return aaStat;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public double getCoefficient() {
		return coefficient;
	}
	
	public String getAmmoType() {
		return ammoType;
	}
	
	public double getLightDamage() {
		return lDamage;
	}
	
	public double getMediumDamage() {
		return mDamage;
	}
	
	public double getHeavyDamage() {
		return hDamage;
	}
	
	public void setAmmoType(String ammoType) {
		this.ammoType = ammoType;
	}
}
