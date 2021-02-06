import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AAGuns {

	private GUIUtility utility;
	
	private String wepName;
	
	private String weaponType;
	
	private int AAStat;
	
	public AAGuns(String weaponName, String weaponType) throws FileNotFoundException, IOException {
		this.utility = new GUIUtility();
		ArrayList<String> list = utility.getWeaponParameters(weaponType, weaponName);
		this.weaponType = weaponType;
		setVariables(list);
	}
	
	private void setVariables(ArrayList<String> list) {
		this.wepName = list.get(0);
		this.AAStat = Integer.parseInt(list.get(1));
	}
	
	public String getWeaponName() {
		return wepName;
	}
	
	public int getAAStat() {
		return AAStat;
	}
		
}
