import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AuxGear {
	
	GUIUtility utility;
	
	private String gearName;
	
	private double health;
	
	private double firepower;
	
	private double torpedo;
	
	private double AA;
	
	private double aviation;
	
	public AuxGear(String auxName) throws FileNotFoundException, IOException {
		utility = new GUIUtility();
		ArrayList<String> list = utility.getAuxParams(auxName);
		setParams(list);
	}
	
	private void setParams(ArrayList<String> list) {
		this.gearName = list.get(0);
		this.health = Double.parseDouble(list.get(1));
		this.firepower = Double.parseDouble(list.get(2));
		this.torpedo = Double.parseDouble(list.get(3));
		this.AA = Double.parseDouble(list.get(4));
		this.aviation = Double.parseDouble(list.get(5));
	}
	
	public String getGearName() {
		return gearName;
	}
	
	public double getHealth () {
		return health;
	}
	
	public double getFirepower() {
		return firepower;
	}
	
	public double getTorpedo() {
		return torpedo;
	}
	
	public double getAA() {
		return AA;
	}
	
	public double getAviation() {
		return aviation;
	}
}
