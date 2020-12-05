import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Planes {
	
	GUIUtility utility;
	
	private String weaponName;
	
	private String weaponType;
	
	private double aviation;
	
	private double bombOneDamage;
	
	private double bombOneToL;
	
	private double bombOneToM;
	
	private double bombOneToH;
	
	private double bombTwoDamage;
	
	private double bombTwoToL;
	
	private double bombTwoToM;
	
	private double bombTwoToH;
	
	private double torpedoDamage;
	
	private double torpedoToL;
	
	private double torpedoToM;
	
	private double torpedoToH;
	
	
	
	public Planes(String weaponName, String weaponType) throws FileNotFoundException, IOException {
		this.utility = new GUIUtility();
		ArrayList<String> list = utility.getWeaponParameters(weaponType, weaponName);
		this.weaponType = weaponType;
		setVariables(list);
	}
	
	private void setVariables(ArrayList<String> list) {
		this.weaponName = list.get(0);
		this.aviation = Double.parseDouble(list.get(1));
		this.bombOneDamage = Double.parseDouble(list.get(2));
		this.bombOneToL = Double.parseDouble(list.get(3));
		this.bombOneToM = Double.parseDouble(list.get(4));
		this.bombOneToH = Double.parseDouble(list.get(5));
		this.bombTwoDamage = Double.parseDouble(list.get(6));
		this.bombTwoToL = Double.parseDouble(list.get(7));
		this.bombTwoToM = Double.parseDouble(list.get(8));
		this.bombTwoToH = Double.parseDouble(list.get(9));
		this.torpedoDamage = Double.parseDouble(list.get(10));
		this.torpedoToL = Double.parseDouble(list.get(11));
		this.torpedoToM = Double.parseDouble(list.get(12));
		this.torpedoToH = Double.parseDouble(list.get(13));
		
	}
	
	public String getPlaneName() {
		return weaponName;
	}
	
	public String getPlaneType() {
		return weaponType;
	}
	
	public double getAviation() {
		return aviation;
	}
	
	public double getBombOneDmg() {
		return bombOneDamage;
	}
	
	public double getOneToL() {
		return bombOneToL;
	}
	
	public double getOneToM() {
		return bombOneToM;
	}
	
	public double getOneToH() {
		return bombOneToH;
	}
	
	public double getBombTwoDmg() {
		return bombTwoDamage;
	}
	
	public double getTwoToL() {
		return bombTwoToL;
	}
	
	public double getTwoToM() {
		return bombTwoToM;
	}
	
	public double getTwoToH() {
		return bombTwoToH;
	}
	
	public double getTorpDmg() {
		return torpedoDamage;
	}
	
	public double getTorpToL() {
		return torpedoToL;
	}
	
	public double getTorpToM() {
		return torpedoToM;
	}
	
	public double getTorpToH() {
		return torpedoToH;
	}
}
